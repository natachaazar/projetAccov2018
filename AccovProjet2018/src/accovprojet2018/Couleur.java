package accovprojet2018;

public class Couleur {
    public int couleurInterne;
    
private static final int numeroBleu = 0;
private static final int numeroRouge = 1;
private static final int numeroJaune = 2;

public static final Couleur BLEU = new Couleur(numeroBleu );
public static final Couleur ROUGE = new Couleur(numeroRouge);
public static final Couleur JAUNE = new Couleur(numeroRouge);

private Couleur(int valeur){
    couleurInterne = valeur % 3;
}

public Couleur CouleurComplementaire(Couleur C){
    if(couleurInterne == C.couleurInterne){
        return new Couleur(couleurInterne);
    }
    else{
        return new Couleur(3 - couleurInterne - C.couleurInterne);
    }
}
    @Override
    public String toString(){
    if(couleurInterne == numeroBleu) return "Bleu";
            else if(couleurInterne == numeroRouge) return "Rouge";
                    else return "Jaune";
}
}
