public class Rationnel {

    private int num;
    private int denom;

    public Rationnel(int num, int denom) {
        this.num = num;
        setDenom(denom);
        normalise();
    }
    public Rationnel(int num) {
        this.num = num;
        setDenom(1);
    }

    public double valeurRéelle(){
        return (double) num/ (double) denom;
    }

    public static int ppcm (int x1, int x2){
        int s = 1, t = 1;
        for (int n = 1;; n++) {
            s = x1 * n;
            for (int x = 1; t < s; x++) {
                t = x2 * x;
            }
            if (s == t)
                break;
        }
        return s;
    }

    public static Rationnel addition(Rationnel x1, Rationnel x2){
        int resultatDenom = Rationnel.ppcm(x1.getDenom(), x2.getDenom());
        int resultatNom = ((resultatDenom/x1.getDenom())*x1.getNum())+((resultatDenom/x2.getDenom())*x2.getNum());
        Rationnel resul =  new Rationnel(resultatNom, resultatDenom);
        resul.normalise();
        return resul;
    }

    private void normalise(){
        if(denom < 0){
            denom *= -1;
            num *= -1;
        }

        int diviseur = pgcd(denom, num);

        denom /= diviseur;
        num /= diviseur;
    }

    private static int pgcd (int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        while(a != b){
            if(a < b){
                int tmp = a;
                a = b;
                b = tmp;
            }
            a = a - b;
        }
        return a;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDenom(int denom) {
        if (denom == 0){
            throw new RuntimeException ("Dénominateur nul dans le constructeur !");
        }
        this.denom = denom;
    }

    public int getNum() {
        return num;
    }

    public int getDenom() {
        return denom;
    }

    // version 1 : « en place »
    public void multipliePar (Rationnel r){
        this.num *= r.num;
        this.denom *= r.denom;
    }
    // version 2 : « pas en place »
    public Rationnel multipliéPar (Rationnel r){
        int num = this.num * r.num;
        int denom = this.denom * r.denom;
        return new Rationnel(num, denom);
    }
    // version 3 : statique
    public static Rationnel produit (Rationnel r1, Rationnel r2){
        int num = r1.num * r2.num;
        int denom = r1.denom * r2.denom;
        return new Rationnel(num, denom);
    }

    @Override
    public boolean equals (Object o){
        boolean equals;
        if (!(o instanceof Rationnel))
            return false;
        Rationnel r = (Rationnel) o;
        return this.num * r.denom == this.denom * r.num;
    }

    public String toString() {
        return denom == 1 ? String.valueOf(num) : num + "/" + denom;
    }
}