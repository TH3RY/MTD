import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import Jama.*;

public class M {
    private double [][] composantes;
    public M(double[][] composantes) {
        setComposantes(composantes);
    }
    public M(int colonne, int ligne, double valeur) {
        double [][] tmp = new double[colonne][ligne];
        for (int i = 0; i < tmp.length;i++)
            for (int j = 0; j < tmp[i].length;j++)
                tmp[i][j] = valeur;
        setComposantes(tmp);
    }
    public M(int colonne, int ligne, double... valeurs) {
        int x = 0;
        double [][] tmp = new double[colonne][ligne];
        for (int i = 0; i < tmp.length;i++)
            for (int j = 0; j < tmp[i].length;j++){
                tmp[i][j] = valeurs[x];
                x++;
            }
        setComposantes(tmp);
    }
    private boolean estColonneMemeTaille(double[][] composantes) {
        int tailleColonne = composantes[0].length;
        for(int i =0; i < composantes.length;i++) {
            if(composantes[i].length != tailleColonne) {
                return false;
            }
        }
        return true;
    }
    private boolean aMinUneLigne(double[][] composantes) {
        return composantes.length > 0;
    }
    public void setComposantes(double[][] composantes) {
        if (!(aMinUneLigne(composantes) && estColonneMemeTaille(composantes) && composantes[0].length > 0))
            throw new RuntimeException();
        this.composantes = composantes;
    }
    public double[][] getComposantes() {
        return composantes;
    }
    public void setComposante(int colonne, int ligne, double valeur) {
        this.composantes[colonne][ligne] = valeur;
    }
    public static M identite (int ordre) {
        M tmp = new M(ordre,ordre,0);
        for (int i = 0; i < tmp.composantes.length;i++)
            tmp.setComposante(i,i,1);
        return tmp;
    }
    public static M clone (M mAClone) {
        double [][] clone = new double[mAClone.composantes.length][mAClone.composantes[0].length];
        for (int i = 0; i < clone.length;i++)
            System.arraycopy(mAClone.composantes[i], 0, clone[i], 0, clone[i].length);
        return new M(clone);
    }
    public int getNBLigne() {
        return composantes[0].length;
    }
    public int getNBColonne() {
        return composantes.length;
    }
    public Boolean estCarre() {
        return composantes.length == composantes[0].length;
    }
    public Boolean estLigneValide(int ligne) {
        return ligne > 0 && ligne <= getNBLigne();
    }
    public Boolean estColonneValide(int colonne) {
        return colonne > 0 && colonne <= getNBColonne();
    }
    public double composante(int colonne, int ligne){
        if (!estColonneValide(colonne) || !estLigneValide(ligne)) {
            throw new RuntimeException();
        }
        return composantes[colonne-1][ligne-1];
    }
    public int [] taille () {
        int [] x = {getNBColonne(), getNBLigne()};
        return x;
    }
    public static M addidtion(M m1, M m2) {
        M resultat = null;
        if (Arrays.equals(m1.taille(), m2.taille())){
            resultat = new M(new double[m1.getNBColonne()][m1.getNBLigne()]);
            for (int i = 0; i < resultat.getNBColonne();i++)
                for (int j = 0; j < resultat.getNBLigne();j++)
                    resultat.composantes[i][j] = m1.composantes[i][j] + m2.composantes[i][j];
        }
        return resultat;
    }
    public static M soustraction(M m1, M m2) {
        M resultat = null;
        if (Arrays.equals(m1.taille(), m2.taille())){
            resultat = new M(new double[m1.getNBColonne()][m1.getNBLigne()]);
            for (int i = 0; i < resultat.getNBColonne();i++)
                for (int j = 0; j < resultat.getNBLigne();j++)
                    resultat.composantes[i][j] = m1.composantes[i][j] - m2.composantes[i][j];
        }
        return resultat;
    }
    public static M transposee(M m) {
        M resultat = new M(new double[m.getNBLigne()][m.getNBColonne()]);
        for (int i = 0; i < m.getNBColonne();i++)
            for (int j = 0; j < m.getNBLigne();j++)
                resultat.composantes[j][i] = m.composantes[i][j];
        return resultat;
    }
    public static M multiplicationExterne (double x, M m) {
        M resultat = new M(new double[m.getNBColonne()][m.getNBLigne()]);
        for (int i = 0; i < resultat.getNBColonne();i++)
            for (int j = 0; j < resultat.getNBLigne();j++)
                resultat.composantes[i][j] = m.composantes[i][j] * x;
        return resultat;
    }
    public static M multiplicationInterne (M m1, M m2) {
        if (m1.getNBLigne() != m2.getNBColonne()) throw new RuntimeException();
        M resultat = new M(new double[m1.getNBColonne()][m2.getNBLigne()]);
        for (int i = 0; i < resultat.getNBColonne();i++)
            for (int j = 0; j < resultat.getNBLigne();j++)
                for (int k = 0; k < m1.getNBLigne(); k++)
                    resultat.composantes[i][j] += m1.composantes[i][k]*m2.composantes[k][j];
        return resultat;
    }
    public static Boolean estEgal(M m1, M m2) {
        for (int i = 0; i < m1.getNBColonne();i++)
            for (int j = 0; j < m1.getNBLigne();j++)
                if (m1.composantes[i][j] != m2.composantes[i][j]) return false;
        return true;
    }
    public static M opposee(M m) {
        return multiplicationExterne(-1, m);
    }
    public String genre() {
        return "m = " + getNBColonne() + "x" + getNBLigne() + " = n m = colonne n = ligne";
    }
    public String systemeEquation() {
        if (getNBLigne() < 2) return "minimum de 2 ligne";
        if (getNBColonne() < 2) return "pas systeme";
        char variable[] = {'x', 'y', 'z' ,' '};
        String x = "";
        for (int i = 0; i < getNBColonne();i++) {
            x = x.concat("{ ");
            int var = 0;
            for (int j = 0; j < getNBLigne();j++){
                Double y = BigDecimal.valueOf(composante(i+1,j+1)).setScale(2, RoundingMode.CEILING).doubleValue();

                if (var < getNBLigne()-2) {
                        x = x.concat(y.toString() + variable[var] + " + ");
                } else if (var < getNBLigne()-1){
                        x = x.concat(y.toString() + variable[var]);
                } else {
                    x = x.concat(" = " + y.toString());
                }
                var++;
            }
            x = x.concat("\n");
        }
        return x;
    }
    public static M inverse(M m){
        Matrix tmp = new Matrix(m.composantes);
        return new M(tmp.inverse().getArray());
    }
    public Boolean estPossible(){
        for (int i = 0; i < getNBColonne();i++) {
            int nb0 = 0;
            for (int j = 0; j < getNBLigne();j++) {
                if (composantes[i][j] == 0) nb0++;
                if (nb0 == getNBLigne()-1 && composantes[i][getNBLigne()-1] != 0) return false;
            }
        }
        return true;
    }

    public static M resoudreSystemeEquation(M m) {
        double [][] compoA = new double[m.getNBColonne()][m.getNBLigne()-1];
        double [][] compoB = new double[m.getNBColonne()][1];
        for (int i = 0;i < m.getNBColonne();i++)
            for (int j = 0;j < m.getNBLigne() ;j++) {
                if (j == m.getNBLigne()-1){
                    compoB[i][0] = m.composantes[i][j];
                } else {
                    compoA[i][j] = m.composantes[i][j];
                }
            }
        Matrix A = new Matrix(compoA);
        Matrix B = new Matrix(compoB);
        return new M(A.solve(B).getArray());
    }
    public static String theorie(){
        return "• deux lignes qui sont identiques mais ont des termes indépendants différents\n" +
                "• une ligne remplie de 0 avec un terme indépendant différent de 0";
    }


    public String toString() {
        String x = "";
        for (int i = 0; i < getNBColonne();i++) {
            x = x.concat("( | ");
            for (int j = 0; j < getNBLigne();j++){
                Double y = BigDecimal.valueOf(composante(i+1,j+1)).setScale(3, RoundingMode.HALF_UP).doubleValue();
                x = x.concat(y.toString() + " | ");
            }
            x = x.concat(")\n");
        }
        return x;
    }
}