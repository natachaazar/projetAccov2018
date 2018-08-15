package partie2;

public class AccovProjet2018 {

    static Couleur[] couleurs = {
        Couleur.JAUNE,
        Couleur.BLEU,
        Couleur.ROUGE,
        Couleur.BLEU,
        Couleur.JAUNE,
        Couleur.BLEU
    };
    static Cameneon[] cameneons = new Cameneon[couleurs.length];

    public static void main(String[] args) {
        for (int i = 0; i < couleurs.length; i++) {
            cameneons[i] = new Cameneon(i + " ", couleurs[i]);
        }
        for (int j = 0; j < couleurs.length; j++) {
            int index = j + 1;
            while (index < couleurs.length) {
                new Mutation(cameneons[j], cameneons[index]).start();
                new Mutation(cameneons[index], cameneons[j]).start();
                index++;
            }
        }
    }
}
