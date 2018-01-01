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
		
		//Camembert camembertJoueurAdverse = joueurAdverse.getCamembert();		
		String message="";
		if (joueurAdverse.RetirerPartCamemebert()) {
			message = "Vous avez retirer une part de camembert à "+joueurAdverse.getNom()+".";
		}else {
			//message = "Vous etez tomber sur une carte mystère perte de part de camembert, cependant vous ne possédez pas de part. Vous ne perdez pas de part.";
			message = joueurAdverse.getNom()+" ne possède pas de part de camembert à retirer.";
		}
		return message;
	}
	
}
