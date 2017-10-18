package Model;

public class Joueur {

	private Pion pion;
	private Camembert camembert;
	private String nom;
	private Case caseCourante;
	
	
	
	public Joueur(CouleurPion couleurpion, String nom, Case casejoueur) {
		
		this.pion = new Pion(couleurpion);
		this.camembert = new Camembert();
		this.nom = nom;
		this.caseCourante = casejoueur;
	}

	//Methode classe Joueur
	public Pion getPion(){
		return this.pion;
	}
	
	public Camembert getCamembert(){
		return this.camembert;
	}
	
	public Case getCaseCourant(){
		return this.caseCourante;
	}
	
	
	public void setCaseCourante(Case newCase) {
		this.caseCourante = newCase;
	}

	public boolean CheckPartCamembert(Couleur couleur){
		
		return this.getCamembert().ContientPart(couleur);
		
	}
	
}

