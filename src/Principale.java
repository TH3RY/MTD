import Jama.Matrix;
import Jama.*;
import java.math.MathContext;
import java.math.*;
public class Principale {
    public static void main(String[] args) {
        M A = new M(3,3,1, 1.8, 2.4, // vendeur 1
                1.2, 1.8, 2.2,  // vendeur 2
                1.1, 1.7, 2.2); // vendeur 3
        M B = new M(3,1,3,2,4);
        M P = new M(5,5,
                4 , 9 , 16, 25, 0,
                        9 , 16, 25, 36, -1,
                        16, 25, 36, 49, -2,
                        25, 36, 49, 64, -3,
                        -4, 4 ,-4 ,4,-4);
        M X = new M(4,4,-5, 0, 4, -5, 5, 2, -1, -3, 4, 0, -3, 4, -2, 0, 0, -6);

        //System.out.println(A.getComposantes()[2][1]);

        double sum = 0;
        int k = 0;
        for (int i = 0; i < 3;i++)
            sum += A.getComposantes()[i][k];
        sum /= 3;
        // System.out.printf("la moyenne des prix des 3 vendeurs des glaces de k boules %.2f\n", sum);
        // System.out.println(B.genre());

        //System.out.println(M.multiplicationInterne(A,B));
        //System.out.println(M.multiplicationInterne(A,B).genre());

        /*
            a11 a12 a13 a14 a15
            a21 a22 a23 a24 a25
            a31 a32 a33 a34 a35
            a41 a42 a43 a44 a45
            a51 a52 a53 a54 a55
         */
        double x = 0;
        M Pbis = new M(5,5,0);
        for (int i = 1; i < 6; i++)
            for (int j = 1; j < 6; j++) {
                x = Math.pow(i+j,2);
                if (j == 5) x = -1 * (i-1);
                if (i == 5) x = 4*Math.pow(-1,j%2);
                Pbis.setComposante(i-1,j-1,x);
            }
        //System.out.println(Pbis);

        Matrix detX = new Matrix(X.getComposantes());
        System.out.println(detX.det());
        // System.out.println(X);
        //System.out.println(M.transposee(X));
        //System.out.println(M.inverse(X));
        M invX = M.multiplicationExterne(1/8.,X);
        // System.out.println(M.inverse(X));
        // System.out.println(M.multiplicationExterne(1/X.getDet(),M.transposee(X)));

        M sys = new M(3, 4,1, 1, 2, -1, 5, -3, 2, 11, 3, -5, -2, 13);
        System.out.println(sys.systemeEquation());
        // System.out.println(M.resoudreSystemeEquation(sys));
        M sysdet = new M(3, 3,1, 1, 2, 5, -3, 2, 3, -5, -2);
        Matrix tmp = new Matrix(sysdet.getComposantes());


       // System.out.println(tmp.det());

        M boule = new M(3,4,1, 2, 3, 25, 1, 1, 1, 14, 1, 0, 0, 7);
        System.out.println(boule.systemeEquation());
        System.out.println(M.resoudreSystemeEquation(boule));
        //System.out.println(M.resoudreSystemeEquation(sys));
    }
}