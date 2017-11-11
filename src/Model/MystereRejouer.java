package Model;
import java.util.ArrayList;

public class MystereRejouer extends Mystere{

	public MystereRejouer() {
		super();
	}

	@Override
	String Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {
		
		return "Vous pouvez rejouer";
	}

}
