package Fonctionnel;

public abstract class Carte {

	private TypeCarte typeCarte;
	private Couleur couleur;
		
	//Constructeur
	public Carte(TypeCarte typeCarte, Couleur couleur) {
		this.typeCarte = typeCarte;
		this.couleur = couleur;
	}
	
	//Méthode classe Carte
	public TypeCarte getTypeCarte() {
		return typeCarte;
	}
	public Couleur getCouleur() {
		return couleur;
	}
	
	
	
	
	
}
