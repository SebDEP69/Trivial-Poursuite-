package Model;
import java.util.ArrayList;

public abstract class Mystere extends Carte{

	//Constructeur
	public Mystere() {
		super(TypeCarte.Mystere,Couleur.NOIR);
	}
	
	//M�thode classe Mystere
	abstract void Action(Joueur joueurcourant ,  ArrayList<Case> listecase, ArrayList<Joueur> listejoueur);
	

}
