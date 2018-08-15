package partie1;

public class Agora {

    private Couleur Couleur1, Couleur2;
    private boolean PremierAppel = true;
    private boolean DoitAttendre = false;

    public synchronized Couleur Rencontre(String cameneonId, Couleur couleur) {
        Couleur resultat;

        while (DoitAttendre) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("erreur catch 1");
            }
        }
        if (PremierAppel) {
            Couleur1 = couleur;
            PremierAppel = false;
            while (!PremierAppel) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("erreur catch 2");
                }
            }
            DoitAttendre = false;
            resultat = Couleur2;
            notifyAll();
        } else {
            Couleur2 = couleur;
            resultat = Couleur1;
            PremierAppel = true;
            DoitAttendre = true;
            notifyAll();
        }
        return resultat;
    }
}
