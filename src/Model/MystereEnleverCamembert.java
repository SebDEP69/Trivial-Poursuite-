package Model;
import java.util.ArrayList;

public class MystereEnleverCamembert extends Mystere {

	//Constructeur
	public MystereEnleverCamembert() {
		super();
	}

	//Methode classe MystereEnleverCamembert
	@Override
	String Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {
		
		Joueur joueurAdverse = null;
		if (joueurcourant == listejoueur.get(0)) {
			joueurAdverse = listejoueur.get(1);
		}else if (joueurcourant == listejoueur.get(1)) {
			joueurAdverse = listejoueur.get(0);
		}else {
			System.out.println("fuck");
		}
		
		Camembert camembertJoueurAdverse = joueurAdverse.getCamembert();		
		String message="";
		if (camembertJoueurAdverse.RetirerPartCamemebert()) {
			message = "Vous avez retirer une part de camembert a "+joueurAdverse.getNom();
		}else {
			message = joueurAdverse.getNom()+" ne possède pas de part de camembert à retirer";
		}
		return message;
	}
	
}
