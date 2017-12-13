package Model;
import java.util.ArrayList;

public class MystereGainCamembert extends Mystere{

	//Constructeur
	public MystereGainCamembert() {
		super();

	}

	//Methode classe MystereGainCamembert
	@Override
	String Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {

		Camembert camembertjoueur = joueurcourant.getCamembert();
		Couleur serie[] = {Couleur.VERT, Couleur.ORANGE,Couleur.BLEU, Couleur.ROUGE};
		Boolean ajout = false;
		int i = 0;
		String message="";
		Couleur couleur = null;
		while(!ajout && i < serie.length) {
			couleur = serie[i];
			if (!camembertjoueur.ContientPart(couleur)) {
				if (camembertjoueur.AjoutPartCamembert(couleur)) {
					ajout = true;
					message = "Vous avez gagnez un camembert "+couleur.toString();
				}else {
					message = "Erreur quand le gain de camembert";
				}
			}	
		}
		return message;
	}

}
