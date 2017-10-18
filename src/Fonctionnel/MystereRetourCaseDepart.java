import java.util.ArrayList;

public class MystereRetourCaseDepart extends Mystere {

	//Constructeur
	public MystereRetourCaseDepart() {
		super();
		
	}

	//Méthode classe MystereRetourCaseDepart
	@Override
	void Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {
		
			Case caseJoueur = joueurcourant.getCaseCourant();
			
			if(caseJoueur.getNumero() == 0){
				System.out.println("Vous êtes déja sur la case départ");
			}
			else{
				joueurcourant.setCaseCourante();
			}
	}

}
