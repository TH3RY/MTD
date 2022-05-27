public class Graph {

    public static double nbValeurMaxArbreBinaire(double hauteurArbre) {
        return Math.pow(2, hauteurArbre) - 1;
    }

    public static double hauteurMinArbreBinaire(int nbValeur) {
        nbValeur++;
        return Math.ceil(Math.log(nbValeur) / Math.log(2));
    }

    public static String synthese() {
        return "Point = Sommet\n" +
                "Ligne = arête (arcs si orienté) \n" +
                "    => Une arête relie \uD835\uDC63\uD835\uDC56 et \uD835\uDC63\uD835\uDC57 qui sont ses extrémités.\n" +
                "    => Un arc va de \uD835\uDC63\uD835\uDC56 (origine) à un sommet \uD835\uDC63\uD835\uDC57 (destination)\n" +
                "\n" +
                "notation non orienté:\n" +
                "    ________\n" +
                "    (vi ,vj)\n" +
                "\n" +
                "notation orienté:\n" +
                "    (vi ,vj) => vi = parent de vj = fils de vi\n" +
                "\n" +
                "Degré :\n" +
                "    nonO => d(vi) = nbAretes qui touche vi, d(sommet) = 4\n" +
                "    O => d+(vi) = nbAretes qui sortent de vi\n" +
                "         d-(vi) = nbAretes qui entre de vi\n" +
                "         d(vi) = d+ + d-\n" +
                "\n" +
                "Connectivite :\n" +
                "    nonO => chaine\n" +
                "        si boucle : cycle\n" +
                "        si chaine entre vi & vj => simplement connecté\n" +
                "    O => chemin\n" +
                "        si revient a départ => circuit\n" +
                "        descendant >< ancetre\n" +
                "        si chemin entre vi -> vj && vj -> vi fortement connecté\n" +
                "\n" +
                "Arborecense :\n" +
                "    racine | noeuds | feuille (tt en bas)\n" +
                "    enfant, grand-parent, petits-enfants, frères/sœurs\n" +
                "    niveau 0 a n\n" +
                "    Hauteur = niveau n + 1\n" +
                "    sous-arborescence \n" +
                "\n" +
                "ArbreBinaireRecherche\n" +
                "    2 enfants max\n" +
                "    SAG : vi < vj\n" +
                "    SAD : vi > vj\n" +
                "    nbValeurMax = 2^N -1 : n = hauteur\n" +
                "    hauteurMin = log2(n + 1) n = nbValeur";
    }
}
