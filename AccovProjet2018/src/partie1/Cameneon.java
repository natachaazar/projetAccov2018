package partie1;

public class Cameneon extends Thread {

    private Agora agora;
    private String cameneon_id;
    private Couleur maCouleur, autreCouleur;

    public Cameneon(Agora a, String id, Couleur C) {
        this.agora = a;
        this.cameneon_id = id;
        this.maCouleur = C;
    }

    private void Manger() {
        System.out.println(cameneon_id + "Je suis " + maCouleur.toString() + " et Je mange du chevre feuille");
    }

    private void Sentrainer() {
        System.out.println(cameneon_id + "Je suis " + maCouleur.toString() + " et Je m'entraine");
    }

    private void Mutation() {
        System.out.println(cameneon_id + "Je suis " + maCouleur.toString() + " et Je vais pour une mutation vers l'agora");
        autreCouleur = agora.Rencontre(cameneon_id, maCouleur);
        maCouleur = maCouleur.CouleurComplementaire(autreCouleur);
        System.out.println(cameneon_id + "Je suis " + maCouleur.toString() + " et ma mutation est terminée");
    }

    @Override
    public void run() {
        while (true) {
            Manger();
            Sentrainer();
            Mutation();
        }
    }
}
