package Model;
import java.util.ArrayList;

public class MystereProchainCamembert extends Mystere{

	//Constructeur
	public MystereProchainCamembert() {
		super();
	
	}

	//Methode classe MystereProchainCamembert
	@Override
	String Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {
		
		boolean trouveCaseCamembert = false;
		Case caseJoueur = joueurcourant.getCaseCourant();
		int i = caseJoueur.getNumero();
		
		while(trouveCaseCamembert != true && i < listecase.size()){
			if(listecase.get(i).isSuperCamembert()){
				joueurcourant.setCaseCourante(listecase.get(i));
				trouveCaseCamembert = true;
			}
			else{
				trouveCaseCamembert = false;
			}
			i++;
		}
		return "Vous vous dirigez vers la prochaine case Supper camembert";
	}

}
