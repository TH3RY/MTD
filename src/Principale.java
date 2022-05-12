import java.util.*;
public class Principale {
    public static void main(String[] args) {
        double compo1[][] = {{1,0,1},{0,2,1},{6,5,0}};
        double compo2[][] = {{5,1,0},{0,3,5},{1,1,1}};
        double compo3[][] = {{7,6},{2,1},{2,-2}};
        double compo4[][] = {{1,4},{3,2},{3,5}};
        Matrice m1 = new Matrice(compo1);
        Matrice m2 = new Matrice(compo2);
        Matrice m3 = new Matrice(compo3);
        Matrice m4 = new Matrice(compo4);

        Matrice x = Matrice.addidtion(Matrice.multiplication(3, m1), Matrice.multiplication(2, m2));
        System.out.println(m3);
        System.out.println(Matrice.transposee(m3));




    }
}