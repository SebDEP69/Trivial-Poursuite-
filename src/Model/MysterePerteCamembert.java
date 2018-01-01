package Model;
import java.util.ArrayList;

public class MysterePerteCamembert extends Mystere{

	//Constructeur
	public MysterePerteCamembert() {
		super();

	}

	//Methode classe MysterePerteCamembert
	@Override
	String Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {
		String message ="";
		if (joueurcourant.RetirerPartCamemebert()) {
			message = "Vous perdez une part de camembert.";
		}else {
			message = "Vous etez tomber sur une carte mystère perte de part de camembert, cependant vous ne possédez pas de part. Vous ne perdez pas de part.";
		}
		return message;
	}

	
	
}
