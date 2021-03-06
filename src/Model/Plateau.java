package Model;

import java.io.IOException;
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
		this.listeCase = new ArrayList<Case>();
		InitListCase();

	}
	public Plateau () {

		this.listeCarteRouge = new ArrayList<Carte>();
		this.listeCarteOrange = new ArrayList<Carte>();
		this.listeCarteBleu = new ArrayList<Carte>();
		this.listeCarteVert = new ArrayList<Carte>();
		this.listeCarteMystere = new ArrayList<Carte>();
		this.listeCase = new ArrayList<Case>();

		/*String[] choixRougeun = {"repRed1","repRed2","repRed3","repRed4"};
		String[] choixRougedeux = {"repRed5","repRed6","repRed7","repRed8"};
		listeCarteRouge.add(new Question("question rouge1", 1,choixRougeun ,Couleur.ROUGE));
		listeCarteRouge.add(new Question("question rouge2", 1, choixRougedeux ,Couleur.ROUGE));


		String[] choixOrangeun = {"repOrange1","repOrange2","repOrange3","repOrange4"};
		String[] choixOrangedeux = {"repOrange5","repOrange6","repOrange7","repOrange8"};
		listeCarteOrange.add(new Question("question orange1", 1, choixOrangeun ,Couleur.ORANGE));
		listeCarteOrange.add(new Question("question orange2", 1, choixOrangedeux ,Couleur.ORANGE));


		String[] choixBleueun = {"repBleu1","repBleu2","repBleu3","repBleu4"};
		String[] choixBleudeux = {"repBleu5","repBleu6","repBleu7","repBleu8"};
		listeCarteBleu.add(new Question("question bleu1", 1, choixBleueun ,Couleur.BLEU));
		listeCarteBleu.add(new Question("question bleu2", 1, choixBleudeux ,Couleur.BLEU));


		String[] choixVertun = {"repert1","reperte2","repert3","repert4"};
		String[] choixVertdeux = {"repert5","repert6","repert7","repert8"};
		listeCarteVert.add(new Question("question vert1", 1, choixVertun ,Couleur.VERT));
		listeCarteVert.add(new Question("question vert2", 1, choixVertdeux ,Couleur.VERT));
		 */
		BaseQuestionCSV BDD ;
		try {
			BDD = new BaseQuestionCSV();
			BDD.EnregistrementQuestion();
			this.listeCarteRouge = BDD.getQuestionROUGE();
			this.listeCarteOrange = BDD.getQuestionORANGE();
			this.listeCarteBleu = BDD.getQuestionBLEU();
			this.listeCarteVert = BDD.getQuestionVERT();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//listeCarteMystere.add(new MysterePerteCamembert());
		//#################
		// DEMO
		//################
		listeCarteMystere.add(new MystereGainCamembert()); // use for demo
		listeCarteMystere.add(new MystereEnleverCamembert()); // use for demo
		listeCarteMystere.add(new MystereProchainCamembert());
		listeCarteMystere.add(new MysterePerteCamembert());
		listeCarteMystere.add(new MystereRejouer());
		listeCarteMystere.add(new MystereRetourCaseDepart());
		

		this.nombreCasePlateau = 24;
		InitListCase();
	}

	//##########################
	//MODE AVEC PILLE
	//##########################
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

		index = 0 ;

		if (listeTEMP.size()>=1) {
			carteRetour = listeTEMP.get(index);

			switch (couleur) {
			case ROUGE : 
				listeCarteRouge.remove(index);
				listeCarteRouge.add(carteRetour);
				break;
			case BLEU: 
				listeCarteBleu.remove(index);
				listeCarteBleu.add(carteRetour);
				break;
			case VERT: 
				listeCarteVert.remove(index);
				listeCarteVert.add(carteRetour);
				break;
			case ORANGE: 
				listeCarteOrange.remove(index);
				listeCarteOrange.add(carteRetour);
				break;
			case NOIR: 
				listeCarteMystere.remove(index);
				listeCarteMystere.add(carteRetour);
				break;
			}
		}else{
			carteRetour = null;
		}

		return carteRetour;
	}

	//##########################
	//MODE AVEC SUPPRESION DES CARTES
	//##########################
	// renvoie une carte de la couleur demander
	/*public Carte TirerCarte(Couleur couleur) {

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

		if (listeTEMP.size()>=1) {
			carteRetour = listeTEMP.get(index);

			switch (couleur) {
			case ROUGE : listeCarteRouge.remove(index);
			break;
			case BLEU: listeCarteBleu.remove(index);
			break;
			case VERT: listeCarteVert.remove(index);
			break;
			case ORANGE: listeCarteOrange.remove(index);
			break;
			default:
				break;
			}
		}else{
			carteRetour = null;
		}

		return carteRetour;
	}*/


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



	public Case getCasePosition(int position) {
		return this.listeCase.get(position);		
	}




	public ArrayList<Case> getListeCase(){	
		return listeCase;
	}



	private int getNombreCasePlateau() {
		return nombreCasePlateau;
	}
	
}
