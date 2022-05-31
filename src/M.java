import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import Jama.*;

public class M {
    private double [][] composantes;
    public M(double[][] composantes) {
        setComposantes(composantes);
    }
    public M(int ligne, int Colonne, double valeur) {
        double [][] tmp = new double[ligne][Colonne];
        for (int i = 0; i < tmp.length;i++)
            for (int j = 0; j < tmp[i].length;j++)
                tmp[i][j] = valeur;
        setComposantes(tmp);
    }
    public M(int ligne, int Colonne, double... valeurs) {
        int x = 0;
        double [][] tmp = new double[ligne][Colonne];
        for (int i = 0; i < tmp.length;i++)
            for (int j = 0; j < tmp[i].length;j++){
                tmp[i][j] = valeurs[x];
                x++;
            }
        setComposantes(tmp);
    }
    private boolean estligneMemeTaille(double[][] composantes) {
        int tailleligne = composantes[0].length;
        for(int i =0; i < composantes.length;i++) {
            if(composantes[i].length != tailleligne) {
                return false;
            }
        }
        return true;
    }
    private boolean aMinUneColonne(double[][] composantes) {
        return composantes.length > 0;
    }
    public void setComposantes(double[][] composantes) {
        if (!(aMinUneColonne(composantes) && estligneMemeTaille(composantes) && composantes[0].length > 0))
            throw new RuntimeException();
        this.composantes = composantes;
    }
    public double[][] getComposantes() {
        return composantes;
    }
    public void setComposante(int ligne, int Colonne, double valeur) {
        this.composantes[ligne][Colonne] = valeur;
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
    public int getNBColonne() {
        return composantes[0].length;
    }
    public int getNBligne() {
        return composantes.length;
    }
    public Boolean estCarre() {
        return composantes.length == composantes[0].length;
    }
    public Boolean estColonneValide(int Colonne) {
        return Colonne > 0 && Colonne <= getNBColonne();
    }
    public Boolean estligneValide(int ligne) {
        return ligne > 0 && ligne <= getNBligne();
    }
    public double composante(int ligne, int Colonne){
        if (!estligneValide(ligne) || !estColonneValide(Colonne)) {
            throw new RuntimeException();
        }
        return composantes[ligne-1][Colonne-1];
    }
    public int [] taille () {
        int [] x = {getNBligne(), getNBColonne()};
        return x;
    }
    public static M addidtion(M m1, M m2) {
        M resultat = null;
        if (Arrays.equals(m1.taille(), m2.taille())){
            resultat = new M(new double[m1.getNBligne()][m1.getNBColonne()]);
            for (int i = 0; i < resultat.getNBligne();i++)
                for (int j = 0; j < resultat.getNBColonne();j++)
                    resultat.composantes[i][j] = m1.composantes[i][j] + m2.composantes[i][j];
        }
        return resultat;
    }
    public static M soustraction(M m1, M m2) {
        M resultat = null;
        if (Arrays.equals(m1.taille(), m2.taille())){
            resultat = new M(new double[m1.getNBligne()][m1.getNBColonne()]);
            for (int i = 0; i < resultat.getNBligne();i++)
                for (int j = 0; j < resultat.getNBColonne();j++)
                    resultat.composantes[i][j] = m1.composantes[i][j] - m2.composantes[i][j];
        }
        return resultat;
    }
    public static M transposee(M m) {
        M resultat = new M(new double[m.getNBColonne()][m.getNBligne()]);
        for (int i = 0; i < m.getNBligne();i++)
            for (int j = 0; j < m.getNBColonne();j++)
                resultat.composantes[j][i] = m.composantes[i][j];
        return resultat;
    }
    public static M multiplicationExterne (double x, M m) {
        M resultat = new M(new double[m.getNBligne()][m.getNBColonne()]);
        for (int i = 0; i < resultat.getNBligne();i++)
            for (int j = 0; j < resultat.getNBColonne();j++)
                resultat.composantes[i][j] = m.composantes[i][j] * x;
        return resultat;
    }
    public static M multiplicationInterne (M m1, M m2) {
        if (m1.getNBColonne() != m2.getNBligne()) throw new RuntimeException("genre incompatible");
        M resultat = new M(new double[m1.getNBligne()][m2.getNBColonne()]);
        for (int i = 0; i < resultat.getNBligne();i++)
            for (int j = 0; j < resultat.getNBColonne();j++)
                for (int k = 0; k < m1.getNBColonne(); k++)
                    resultat.composantes[i][j] += m1.composantes[i][k]*m2.composantes[k][j];
        return resultat;
    }
    public static Boolean estEgal(M m1, M m2) {
        for (int i = 0; i < m1.getNBligne();i++)
            for (int j = 0; j < m1.getNBColonne();j++)
                if (m1.composantes[i][j] != m2.composantes[i][j]) return false;
        return true;
    }
    public static M opposee(M m) {
        return multiplicationExterne(-1, m);
    }
    public String genre() {
        return "m = " + getNBligne() + "x" + getNBColonne() + " = n m = ligne n = Colonne"+"\n" +
                "pour une transposée : bij = aji";
    }
    public String systemeEquation() {
        if (getNBColonne() < 2) return "minimum de 2 Colonne";
        if (getNBligne() < 2) return "pas systeme";
        char variable[] = {'x', 'y', 'z' ,' '};
        String x = "";
        for (int i = 0; i < getNBligne();i++) {
            x = x.concat("{ ");
            int var = 0;
            for (int j = 0; j < getNBColonne();j++){
                Double y = BigDecimal.valueOf(composante(i+1,j+1)).setScale(2, RoundingMode.CEILING).doubleValue();

                if (var < getNBColonne()-2) {
                        x = x.concat(y.toString() + variable[var] + " + ");
                } else if (var < getNBColonne()-1){
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
    public double getDet() {
        Matrix tmp = new Matrix(this.getComposantes());
        return tmp.det();
    }
    public Boolean estPossible(){
        for (int i = 0; i < getNBligne();i++) {
            int nb0 = 0;
            for (int j = 0; j < getNBColonne();j++) {
                if (composantes[i][j] == 0) nb0++;
                if (nb0 == getNBColonne()-1 && composantes[i][getNBColonne()-1] != 0) return false;
            }
        }
        return true;
    }
    public static M resoudreSystemeEquation(M m) {
        double [][] compoA = new double[m.getNBligne()][m.getNBColonne()-1];
        double [][] compoB = new double[m.getNBligne()][1];
        for (int i = 0;i < m.getNBligne();i++)
            for (int j = 0;j < m.getNBColonne() ;j++) {
                if (j == m.getNBColonne()-1){
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
                "• une lignes remplie de 0 avec un terme indépendant différent de 0";
    }
    public String toString() {
        String x = "";
        for (int i = 0; i < getNBligne();i++) {
            x = x.concat("( | ");
            for (int j = 0; j < getNBColonne();j++){
                Double y = BigDecimal.valueOf(composante(i+1,j+1)).setScale(3, RoundingMode.HALF_UP).doubleValue();
                x = x.concat(y.toString() + " | ");
            }
            x = x.concat(")\n");
        }
        return x;
    }
}