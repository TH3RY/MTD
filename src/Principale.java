import Jama.Matrix;
import Jama.*;
import java.math.MathContext;
import java.math.*;
public class Principale {
    public static void main(String[] args) {
        Ratio2 x1 = new Ratio2(2,3);
        Ratio2 x2 = new Ratio2(5,6);
        System.out.println("resultat attendu :  3/2 ---- " + x1.plus(x2)); //

        Ratio2 x3 = new Ratio2(5,3);
        System.out.println("resultat attendu :  25/9 ---- " + x3.times(x3));

        Ratio2 sum1 = new Ratio2(0,1);
        for (int i = 1; i <= 6; i++)
            sum1 = sum1.plus(new Ratio2(1,(int)Math.pow(i,2)));
        System.out.println("resultat attendu :  5369/3600---- " + sum1);

        Ratio2 x5 = new Ratio2(-10,17);
        System.out.println("resultat attendu :  -389/170---- " + x5.plus(x5.reciprocal()));

        Ratio2 x6 = new Ratio2(3,4);
        Ratio2 x7 = new Ratio2(5,6);
        Ratio2 x8 = new Ratio2(7,8);
        System.out.println("resultat attendu :  1/40 ---- " + x6.divides(x7).minus(x8));

        Ratio2 sum2 = new Ratio2(0,1);
        for (int i = 1; i <= 13; i++)
            sum2 = sum2.plus(new Ratio2(i,i+1));
        System.out.println("resultat attendu :  3873307/360360 ---- " + sum2);
    }
}