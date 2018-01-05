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
	public String getNom() {
		return nom;
	}
	// Autres
	public Case getCaseCourant(){
		return this.caseCourante;
	}


	public void setCaseCourante(Case newCase) {
		this.caseCourante = newCase;
	}


	// Score

	public Boolean isGagnant() {
		return this.score.isGagnant();
	}
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





	public int getTotalBleu() {
		return this.score.getTotalBleu();
	}


	public int getTotalRouge() {
		return this.score.getTotalRouge();
	}

	public int getTotalVert() {
		return this.score.getTotalVert();
	}

	public int getTotalOrange() {
		return this.score.getTotalOrange();
	}

	public int[] getTottalAll(){

		int val[]={0,0,0,0};
		val[0]= this.score.getTotalBleu();
		val[1]= this.score.getTotalRouge();
		val[2]= this.score.getTotalVert();
		val[3]= this.score.getTotalOrange();
		return val;
	}



	public int getRepJusteRouge() {
		return this.score.getRepJusteRouge();
	}

	public int getRepJusteBleu() {
		return this.score.getRepJusteBleu();
	}

	public int getRepJusteVert() {
		return this.score.getRepJusteVert();
	}

	public int getRepJusteOrange() {
		return this.score.getRepJusteOrange();
	}

	public int[] getReponseJusteAll(){

		int val[]={0,0,0,0};
		val[0]= this.score.getRepJusteBleu();
		val[1]= this.score.getRepJusteRouge();
		val[2]= this.score.getRepJusteVert();
		val[3]= this.score.getRepJusteOrange();
		return val;
	}





	public void augmenteTotalQuestion(Couleur couleur){

		switch (couleur) {
		case ROUGE:
			this.score.augmenteTotalRouge();
			break;
		case BLEU:
			this.score.augmenteTotalBleu();
			break;
		case ORANGE:
			this.score.augmenteTotalOrange();
			break;
		case VERT:
			this.score.augmenteTotalVert();
			break;
		default:
			break;
		}
	}

	public void augmenteReponseJuste(Couleur couleur){

		switch (couleur) {
		case ROUGE:
			this.score.augmenteRepJusteRouge();
			break;
		case BLEU:
			this.score.augmenteRepJusteBleu();
			break;
		case ORANGE:
			this.score.augmenteRepJusteOrange();
			break;
		case VERT:
			this.score.augmenteRepJusteVert();
			break;
		default:
			break;
		}
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
	public int getNbPart() {
		return this.camembert.getNbPart();
	}

	public boolean CheckPartCamembert(Couleur couleur){

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





	@Override
	public String toString() {

		String returnvalue ="" ;
		returnvalue = this.getNom() +":\n";
		returnvalue = returnvalue + this.camembert.toString();
		returnvalue = returnvalue + "\n"+ this.score.toString();
		return returnvalue;
	}

}

