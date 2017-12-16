package Model;

public class Joueur {

	private Pion pion;
	private Camembert camembert;
	private String nom;
	private Case caseCourante;
	private Score score;
	
	public Joueur(CouleurPion couleurpion, String nom, Case casejoueur) {
		
		this.pion = new Pion(couleurpion);
		this.camembert = new Camembert();
		this.nom = nom;
		this.caseCourante = casejoueur;
		this.score= new Score();
	}

	
	//Methode classe Joueur
	public Pion getPion(){
		return this.pion;
	}
	

	
	
	// Score
	public void estGagnant() {
		this.score.estGagnant();
	}
	
	public void estPerdant() {
		this.score.estPerdant();
	}
	
	public int getNbPartgagner() {
		return this.score.getNbPartCamembert();
	}
	
	public String getGagnant() {
		return this.score.getGagnant();
	}
	
	public String getNom() {
		return nom;
	}

		
	
	
	//camembert
	public boolean camVide(){
		return this.camembert.Vide();
	}
	
	public boolean camPlein(){
		return this.camembert.Plein();
	}
	
	public boolean ContientPart(Couleur couleur){
		return this.camembert.ContientPart(couleur);
	}
	
	
	public boolean AjoutPartCamembert(Couleur couleur){
		this.score.ajoutPart();
		return this.camembert.AjoutPartCamembert(couleur);
		
	}
	
	public boolean RetirerPartCamemebert() {
		this.score.supprimePart();
		return this.camembert.RetirerPartCamemebert();
	}
	
	public int getNbPart() {
		return this.camembert.getNbPart();
	}
	
	public boolean CheckPartCamembert(Couleur couleur){
		
		return this.camembert.ContientPart(couleur);
		
	}
	
	
	// Autres
	public Case getCaseCourant(){
		return this.caseCourante;
	}
	
	
	public void setCaseCourante(Case newCase) {
		this.caseCourante = newCase;
	}

	

	@Override
	public String toString() {
		
		String returnvalue ="" ;
		returnvalue = this.getNom() +":\n";
		returnvalue = returnvalue + this.camembert.toString();
		return returnvalue;
	}
	
}

