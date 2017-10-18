import java.util.ArrayList;

public class MystereRetourCaseDepart extends Mystere {

	//Constructeur
	public MystereRetourCaseDepart() {
		super();
		
	}

	//M�thode classe MystereRetourCaseDepart
	@Override
	void Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {
		
			Case caseJoueur = joueurcourant.getCaseCourant();
			
			if(caseJoueur.getNumero() == 0){
				System.out.println("Vous �tes d�ja sur la case d�part");
			}
			else{
				joueurcourant.setCaseCourante();
			}
	}

}
