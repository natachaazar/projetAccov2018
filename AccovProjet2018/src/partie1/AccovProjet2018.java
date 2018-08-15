package partie1;

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
        Agora agora = new Agora();
        for (int i = 0; i < couleurs.length; i++) {
            cameneons[i] = new Cameneon(agora, i + " ", couleurs[i]);
        }
        for (int j = 0; j < couleurs.length; j++) {
            cameneons[j].start();
        }
    }
}
