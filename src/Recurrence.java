public class Recurrence {
    public static int fibonacci(int k) {
        if (k == 1 || k == 2) return 1;
        return fibonacci(k-1) + fibonacci(k-2);
    }

    public static int fonctionA (int nombre) {
        if (nombre < 10)
            return nombre;
        return (nombre % 10) + fonctionA(nombre / 10);
    }

    public static int sommeJusque(int n){
        if (n == 0){
            return 0;
        } else {
            return n + sommeJusque(n-1);
        }
    }

    public static int termeEn (int n){
        if (n == 0){
            return 1;
        } else {
            return termeEn(n-1) +2;
        }
    }

    public static int factorielle (int n){
        if (n == 0){
            return 1;
        } else {
            return factorielle(n-1)*n;
        }
    }

    public static double puissance3 (double n){
        return Math.pow(3, n);
    }

    public static Boolean sansDoublons(int [] tab, int deb, int fin) {
        if (fin == deb) return true;
        if(estPresentDans(tab[fin], tab,deb,fin-1))
            return false;
        return sansDoublons(tab, deb, fin-1);
    }

    public static Boolean estPresentDans(int x, int [] tab, int deb, int fin){
        if (tab[fin] == x) return true;
        if (fin == deb) return false;
        return estPresentDans(x, tab,deb,fin-1);
    }

    public static int hotel(int [] tab, int deb, int fin){
        int x = 0;
        if (fin == deb+1) {
            return tab[fin-1] * tab[fin];
        } else {                 // tab[fin-1] * tab[fin]
            x += tab[fin-1] * tab[fin];
        }
        x += hotel(tab, deb,fin-2);
        return x;
    }

    public static int dallage(int n,int x1 ,int x2) {
        if (n == 1) return x1;
        if (n == 2) return x2;
        return dallage(n-1, x2, x1+x2);
    }

    public static int activ(int k) {
        if (k < 2) return 3;
        if (k < 3) return 2;
        return activ(4);
    }
    public int getRandom(int n) {
        // int x = "getRandom" % n + 1;
        int x = 1;
        if (x == 0) x = 1;
        return x;
    }
    /*
        Pn
        P1 = 1
        P2 = 2
        P3 = 3
        P4 = 5
        P5 =
    
    */
    public int dallage1(int n) {
        if (n < 2) return 1;
        return dallage1(n - 1) + dallage1(n - 2);
    }
    /*
        p1 = 0
        p2 = 1
        p3 = 1
        p4 = 1
        p5 = 2
        p6 = 2
    */
    public int dallage2(int n) {
        if (n < 4) {
            if (n < 2)  return 0;
            return 1;
        }
        return dallage2(n - 2) + dallage2(n - 3);
    }
    /*
        5m x 6(n)m
        dalle = 2x3
    
    
    */
    public int dallage3(int n) {
        if (n < 2) return 2;
        return 2 * dallage3(n - 1);
    }
    /*
        p1 = 2
        p2 = 5
        p3 = 20
    */
    public int dallage4(int n) {
        if (n < 2) return 2;
        return 3 * dallage4(n - 1) + (n % 2 == 0 ? 1 : -1);
    }

/*
    p1 = 1
    p2 = 3
    p3 = 6
    p4 = 10
*/

    public int bitstring(int n) {
        if (n < 4) {
            if (n == 1) return 1;
            if (n == 2) return 3;
            if (n == 3) return 6;
        }
        return (bitstring(n - 1) + bitstring(n - 2));
    }

    public void chimay(int n) {
        if (n == 1) System.out.println("fin");
        else {
            int x = getRandom(n - 1);
            System.out.println(x);
            chimay(x);
            System.out.println("boire");
            chimay(n - x);
        }
    }

    public void printBinary(int n) {
        if (n > 0) {
            //System.out.printf(("%d", n % 2);
            printBinary(n / 2);
        }
    }
    public Boolean estDans(int n, int d) {
        if (n == 0) return false;
        if (n % 10 == d) return true;
        return estDans((int)(n / 10), d);
    }
    void main() {
        System.out.println((estDans(171, 8) ? "1" : "0"));
    }
}
