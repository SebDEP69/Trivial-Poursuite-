package Model;
import java.util.ArrayList;

public abstract class Mystere extends Carte{

	//Constructeur
	public Mystere() {
		super(TypeCarte.Mystere,Couleur.NOIR);
	}
	
	//Methode classe Mystere
	abstract String Action(Joueur joueurcourant ,  ArrayList<Case> listecase, ArrayList<Joueur> listejoueur);
	
	
}
