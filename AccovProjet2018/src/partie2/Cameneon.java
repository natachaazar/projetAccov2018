package partie2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cameneon {

    private String cameneon_id;
    private Couleur maCouleur, autreCouleur;
    private final Lock lock = new ReentrantLock();
    final Condition workCompleted = lock.newCondition();
    private Boolean enAttente = false;

    public Cameneon(String id, Couleur C) {
        this.cameneon_id = id;
        this.maCouleur = C;
    }

    private void demandeMutation(Cameneon demandeur) {
        Boolean myLock = false;
        Boolean yourLock = false;
        try {
            myLock = lock.tryLock();
            yourLock = demandeur.lock.tryLock();
        } finally {
            synchronized (demandeur.enAttente) {
                if (!(myLock && yourLock) && demandeur.enAttente) {
                    try {
                        System.out.println("Mutation entre " + cameneon_id + "qui est " + maCouleur + " et "
                                + demandeur.cameneon_id + "qui est " + demandeur.maCouleur);
                        autreCouleur = demandeur.maCouleur;
                        maCouleur = maCouleur.CouleurComplementaire(autreCouleur);
                    } finally {
                        if (!(myLock && yourLock)) {
                            if (myLock) {
                                lock.unlock();
                            }
                            if (yourLock) {
                                demandeur.lock.unlock();
                            }
                            enAttente = false;
                            demandeur.enAttente = false;
                        }
                    }
                } else {
                    try {
                        synchronized (enAttente) {
                            enAttente = true;
                        }

                    } finally {
                        if (yourLock) {
                            demandeur.lock.unlock();
                        }
                    }

                }

            }
        }
    }

    private void Manger() {
        System.out.println(cameneon_id + "Je suis " + maCouleur.toString() + " et Je mange du chevre feuille");
    }

    private void Sentrainer() {
        System.out.println(cameneon_id + "Je suis " + maCouleur.toString() + " et Je m'entraine");
    }

    public synchronized void Mutation(Cameneon demandeur) {
        Manger();
        Sentrainer();
        if (!enAttente) {
            System.out.println(cameneon_id + "Je suis " + maCouleur.toString()
                    + " et Je demande une mutation de " + demandeur.cameneon_id + "qui est de couleur " + demandeur.maCouleur.toString());
            demandeMutation(demandeur);
            if (!enAttente) {
                System.out.println(cameneon_id + "Je suis transformé en " + maCouleur.toString()
                        + " car ma mutation avec " + demandeur.cameneon_id + "est terminée");
            }
        } else {
            System.out.println(cameneon_id + "Jai deja depose une demande de mutation et j'attends que quelqu'un me demande");
        }

    }

}
