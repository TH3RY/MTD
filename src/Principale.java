import Jama.Matrix;
import Jama.*;
import java.math.MathContext;

public class Principale {
    public static void main(String[] args) {
        M x = new M(3,4,1,3,-1,0,
                                            2,5,1,5,
                                            5,13,1,10);
        //System.out.println(x);
        //System.out.println(M.inverse(x));
        // System.out.println(x.systemeEquation());
        System.out.println(M.resoudreSystemeEquation(x));
    }
}