package Model;
import java.util.ArrayList;

public class MystereProchainCamembert extends Mystere{

	//Constructeur
	public MystereProchainCamembert() {
		super();
	
	}

	//Methode classe MystereProchainCamembert
	@Override
	void Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {
		
		boolean trouveCaseCamembert = false;
		Case caseJoueur = joueurcourant.getCaseCourant();
		int i = caseJoueur.getNumero();
		
		while(trouveCaseCamembert != true && i < listecase.size()){
			if(listecase.get(i).isSuperCamembert()){
				caseJoueur.setNumero(i);
				trouveCaseCamembert = true;
				
			}
			else{
				trouveCaseCamembert = false;
			}
			i++;
		}
		
	}

}
