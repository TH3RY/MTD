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
}
