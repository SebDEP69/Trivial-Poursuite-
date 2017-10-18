package Model;
import java.util.ArrayList;

public class Plateau {

	private ArrayList<Carte> listeCarteRouge ;
	private ArrayList<Carte> listeCarteOrange;
	private ArrayList<Carte> listeCarteBleu;
	private ArrayList<Carte> listeCarteVert;
	private ArrayList<Carte> listeCarteMystere;
	private ArrayList<Case> listeCase;
	private  int nombreCasePlateau;
	
	//Constructeur
	public Plateau(ArrayList<Carte> listeCarteRouge, ArrayList<Carte> listeCarteOrange, ArrayList<Carte> listeCarteBleu,
			ArrayList<Carte> listeCarteVert, ArrayList<Carte> listeCarteMystere, int nombreCasePlateau) {
	
		this.listeCarteRouge = listeCarteRouge;
		this.listeCarteOrange = listeCarteOrange;
		this.listeCarteBleu = listeCarteBleu;
		this.listeCarteVert = listeCarteVert;
		this.listeCarteMystere = listeCarteMystere;
		this.nombreCasePlateau = nombreCasePlateau;
		InitListCase();
		
	}
	
	
	public Plateau () {
		
		this.listeCarteRouge = new ArrayList<Carte>();
		this.listeCarteOrange = new ArrayList<Carte>();
		this.listeCarteBleu = new ArrayList<Carte>();
		this.listeCarteVert = new ArrayList<Carte>();
		this.listeCarteMystere = new ArrayList<Carte>();
		this.listeCase = new ArrayList<Case>();
		
		
		listeCarteRouge.add(new Question("question rouge1", "reponse rouge1", TypeCarte.Question ,Couleur.ROUGE));
		listeCarteRouge.add(new Question("question rouge2", "reponse rouge2", TypeCarte.Question ,Couleur.ROUGE));
		
		listeCarteOrange.add(new Question("question orange1", "reponse orange1", TypeCarte.Question ,Couleur.ORANGE));
		listeCarteOrange.add(new Question("question orange2", "reponse orange2", TypeCarte.Question ,Couleur.ORANGE));
		
		listeCarteBleu.add(new Question("question bleu1", "reponse bleu1", TypeCarte.Question ,Couleur.BLEU));
		listeCarteBleu.add(new Question("question bleu2", "reponse bleu2", TypeCarte.Question ,Couleur.BLEU));
		
		listeCarteVert.add(new Question("question vert1", "reponse vert1", TypeCarte.Question ,Couleur.VERT));
		listeCarteVert.add(new Question("question vert2", "reponse vert2", TypeCarte.Question ,Couleur.VERT));
		
		listeCarteMystere.add(new MysterePerteCamembert());
		listeCarteMystere.add(new MystereGainCamembert());
		
		
		this.nombreCasePlateau = 24;
		InitListCase();
	}
	
	
	
	// renvoie une carte de la couleur demander
	public Carte TirerCarte(Couleur couleur) {
		
		Carte carteRetour = null;
				
		int index =0;
		ArrayList<Carte> listeTEMP= new ArrayList<Carte>();
		
		  switch (couleur) {
	          case ROUGE : listeTEMP = listeCarteRouge;
	                   break;
	          case BLEU:    listeTEMP = listeCarteBleu;
	                   break;
	          case VERT: listeTEMP = listeCarteVert;
	                   break;
	          case ORANGE:  listeTEMP = listeCarteOrange;
	                   break;
	          case NOIR:  listeTEMP = listeCarteMystere;
	                   break;
		  }
		  
		  index = (int) (Math.random() * listeTEMP.size()) ;
    	  carteRetour = listeTEMP.get(index);
		
		return carteRetour;
	}
	
	
	// initialise la liste des cases du plateau
	private void InitListCase() {
		
		Couleur serie[] = {Couleur.VERT, Couleur.ORANGE,Couleur.BLEU, Couleur.ROUGE,Couleur.NOIR};
		
		int indexSerie=0;
		for (int numCase = 0; numCase < getNombreCasePlateau(); numCase++) {
			
			if ( ((numCase+1)%6 )==0 ) {
				this.listeCase.add(new Case(serie[indexSerie], numCase, true));
			}else {
				this.listeCase.add(new Case(serie[indexSerie], numCase, false));
			}
			
			if (indexSerie==4) {
				indexSerie=0;
			}else {
				indexSerie++;
			}
		}		
	}
	
	public ArrayList<Case> getListeCase(){
		
		return listeCase;
			
	}

	private int getNombreCasePlateau() {
		return nombreCasePlateau;
	}
}
