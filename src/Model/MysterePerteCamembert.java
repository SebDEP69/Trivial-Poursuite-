package Model;
import java.util.ArrayList;

public class MysterePerteCamembert extends Mystere{

	//Constructeur
	public MysterePerteCamembert() {
		super();

	}

	//M�thode classe MysterePerteCamembert
	@Override
	void Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {
		
		if (joueurcourant.getCamembert().RetirerPartCamemebert()) {
			System.out.println("un camembert � �t� retirer");
		}else {
			System.out.println("impossible de retirer un camembert du joueur car il ne poss�de pas de camembert");
		}
		
	}

	
	
}
