package partie2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cameneon{
    private String cameneon_id;
    private Couleur maCouleur,autreCouleur;
    private final Lock lock = new ReentrantLock();
    final Condition workCompleted = lock.newCondition();
    private Boolean enAttente=false;
    public Cameneon(String id, Couleur C){
        this.cameneon_id=id;
        this.maCouleur=C;
    }
    
     public void demandeMutation(Cameneon demandeur){
            Boolean myLock = false;
            Boolean yourLock = false;
            try {
                myLock = lock.tryLock();
                yourLock = demandeur.lock.tryLock();
            } finally {
                System.out.println("><><><>< my lock " + myLock);
                System.out.println("><><>< Your lock " + yourLock);
                System.out.println("><><><>< my atte " + enAttente);
                System.out.println("><><>< Your atte " + demandeur.enAttente);
//                if(!(myLock && yourLock) && demandeur.enAttente){
                    if(demandeur.enAttente){
                try {
                    System.out.println("nous somme 2 "+cameneon_id);
                    System.out.println("nous somme 2 "+demandeur.cameneon_id);
//                    System.out.println(cameneon_id + " Je suis " + maCouleur.toString() + 
//                            " et Je demande une mutation de "+ demandeur.cameneon_id +" qui est de couleur "+ demandeur.maCouleur.toString());
                }
//                    wait();
//                } catch(InterruptedException e){
//                    System.out.println("catch here");
//                }
                finally {
                     if (!(myLock && yourLock)) {
                    if(myLock){
                      lock.unlock();   
                    }
                    if(yourLock){
                      demandeur.lock.unlock();
                    }
                    enAttente=false;
                    demandeur.enAttente=false;
                     }
                    
                }
                }else{
                    System.out.println("je suis seule");
                     enAttente=true;
                }
                
            }
        }
      
    public void mutation(Cameneon demandeur){
        demandeMutation(demandeur);
//            if (demandeMutation(demandeur)) {
//                try {
//                    System.out.println("nous somme 2");
////                    System.out.println(cameneon_id + " Je suis " + maCouleur.toString() + 
////                            " et Je demande une mutation de "+ demandeur.cameneon_id +" qui est de couleur "+ demandeur.maCouleur.toString());
//                }
////                    wait();
////                } catch(InterruptedException e){
////                    System.out.println("catch here");
////                }
//                finally {
//                    lock.unlock();
//                    demandeur.lock.unlock();
//                }
//            } else {
//                System.out.println("je suis seule");
////                System.out.println(demandeur.cameneon_id + " de couleur  " + demandeur.maCouleur.toString() + 
////                            " et me demande une mutation et je suis "+ cameneon_id +" de couleur "+ maCouleur.toString());
////                notify();
//                
//                
//            }
        }
    
    private void Manger(){
        System.out.println(cameneon_id + " Je suis " + maCouleur.toString() + " et Je mange du chevre feuille");
    }
    private void Sentrainer(){
        System.out.println(cameneon_id + " Je suis " + maCouleur.toString() + " et Je m'entraine");
    }
    public synchronized void Mutation(Cameneon demandeur){
        if(!enAttente){
        System.out.println(cameneon_id + " Je suis " + maCouleur.toString() + " et Je demande une mutation");
        mutation(demandeur);
        if(!enAttente){
            System.out.println(cameneon_id + " Je suis " + maCouleur.toString() + " et ma mutation est terminée");
        }else{
            System.out.println(cameneon_id+" Jai depose une demande et je suis en attente11");
        }
        }
        else{
            System.out.println(cameneon_id+ "Jai depose une demande et je suis en attente");
        }
        
    }
    
}
