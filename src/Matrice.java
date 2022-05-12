import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Matrice {
    private double [][] composantes;

    public Matrice(double[][] composantes) {
        setComposantes(composantes);
    }

    public Matrice(int colonne, int ligne, double valeur) {
        double [][] tmp = new double[colonne][ligne];
        for (int i = 0; i < tmp.length;i++)
            for (int j = 0; j < tmp[i].length;j++)
                tmp[i][j] = valeur;
        setComposantes(tmp);
    }

    public void setComposantes(double[][] composantes) {
        if (composantes.length == 0) {
            System.out.println("Erreur");
        } else {
            this.composantes = composantes;
        }
    }

    public double[][] getComposantes() {
        return composantes;
    }

    public void setComposante(int colonne, int ligne, int valeur) {
        this.composantes[colonne][ligne] = valeur;
    }

    public static Matrice identite (int ordre) {
        Matrice tmp = new Matrice(ordre,ordre,0);
        for (int i = 0; i < tmp.composantes.length;i++)
            tmp.setComposante(i,i,1);
        return tmp;
    }

    public static Matrice clone (Matrice matriceAClone) {
        double [][] clone = new double[matriceAClone.composantes.length][matriceAClone.composantes[0].length];
        for (int i = 0; i < clone.length;i++)
            System.arraycopy(matriceAClone.composantes[i], 0, clone[i], 0, clone[i].length);

        return new Matrice(clone);
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

    public static Matrice addidtion(Matrice m1, Matrice m2) {
        Matrice resultat = null;
        if (Arrays.equals(m1.taille(), m2.taille())){
            resultat = new Matrice(new double[m1.getNBColonne()][m1.getNBLigne()]);
            for (int i = 0; i < resultat.getNBColonne();i++)
                for (int j = 0; j < resultat.getNBLigne();j++)
                    resultat.composantes[i][j] = m1.composantes[i][j] + m2.composantes[i][j];
        }
        return resultat;
    }

    public static Matrice soustraction(Matrice m1, Matrice m2) {
        Matrice resultat = null;
        if (Arrays.equals(m1.taille(), m2.taille())){
            resultat = new Matrice(new double[m1.getNBColonne()][m1.getNBLigne()]);
            for (int i = 0; i < resultat.getNBColonne();i++)
                for (int j = 0; j < resultat.getNBLigne();j++)
                    resultat.composantes[i][j] = m1.composantes[i][j] - m2.composantes[i][j];
        }
        return resultat;
    }

    public static Matrice multiplication(double x, Matrice m) {
        Matrice resultat = new Matrice(new double[m.getNBColonne()][m.getNBLigne()]);
        for (int i = 0; i < resultat.getNBColonne();i++)
            for (int j = 0; j < resultat.getNBLigne();j++)
                resultat.composantes[i][j] = m.composantes[i][j] * x;
        return resultat;
    }

    public static Matrice opposee(Matrice m) {
        return multiplication(-1, m);
    }

    public static Boolean estEgal(Matrice m1, Matrice m2) {
        for (int i = 0; i < m1.getNBColonne();i++)
            for (int j = 0; j < m1.getNBLigne();j++)
                if (m1.composantes[i][j] != m2.composantes[i][j]) return false;
        return true;
    }
    public static Matrice transposee(Matrice m) {
        Matrice resultat = new Matrice(new double[m.getNBLigne()][m.getNBColonne()]);
        for (int i = 0; i < m.getNBColonne();i++)
            for (int j = 0; j < m.getNBLigne();j++)
                resultat.composantes[j][i] = m.composantes[i][j];
        return resultat;
    }
    public String toString() {
        String x = "";
        for (int i = 0; i < getNBColonne();i++) {
            x = x.concat("( | ");
            for (int j = 0; j < getNBLigne();j++){
                Double y = BigDecimal.valueOf(composante(i+1,j+1)).setScale(2, RoundingMode.CEILING).doubleValue();
                x = x.concat(y.toString() + " | ");
            }
            x = x.concat(")\n");
        }

        return x;
    }
}