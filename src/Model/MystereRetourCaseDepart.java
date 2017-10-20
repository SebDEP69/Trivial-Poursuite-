package Model;
import java.util.ArrayList;

public class MystereRetourCaseDepart extends Mystere {

	//Constructeur
	public MystereRetourCaseDepart() {
		super();
		
	}

	//Methode classe MystereRetourCaseDepart
	@Override
	void Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {
		
			Case caseJoueur = joueurcourant.getCaseCourant();
			
			if(caseJoueur.getNumero() == 0){
				System.out.println("Vous etes deja sur la case depart");
			}
			else{
				joueurcourant.setCaseCourante(listecase.get(0));
			}
	}

}
