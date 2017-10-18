package Fonctionnel;
import java.util.ArrayList;

public class MysterePerteCamembert extends Mystere{

	//Constructeur
	public MysterePerteCamembert() {
		super();

	}

	//Méthode classe MysterePerteCamembert
	@Override
	void Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {
		
		if (joueurcourant.getCamembert().RetirerPartCamemebert()) {
			System.out.println("un camembert à été retirer");
		}else {
			System.out.println("impossible de retirer un camembert du joueur car il ne possède pas de camembert");
		}
		
	}

	
	
}
