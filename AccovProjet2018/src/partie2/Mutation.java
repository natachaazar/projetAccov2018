package partie2;

import java.util.Random;

public class Mutation extends Thread {

    private final Cameneon demandeur;
    private final Cameneon recepteur;

    public Mutation(Cameneon demandeur, Cameneon recepteur) {
        this.demandeur = demandeur;
        this.recepteur = recepteur;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (;;) {
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
            }
            demandeur.Mutation(recepteur);
        }
    }
}
