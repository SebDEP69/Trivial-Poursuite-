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
		if (joueurcourant.getCamembert().RetirerPartCamemebert()) {
			message = "un camembert a ete retirer";
		}else {
			message = "impossible de retirer un camembert du joueur car il ne possede pas de camembert";
		}
		return message;
	}

	
	
}
