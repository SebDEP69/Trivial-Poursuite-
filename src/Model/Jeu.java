package Model;
import java.util.ArrayList;

public class Jeu {

	private Joueur joueurCourant;
	private Plateau plateau;
	private ArrayList<Joueur> listeJoueur = new ArrayList<>();
	private int indexDesDemo;
	//Constructeur
	public Jeu(){
		this.plateau = new Plateau();
		indexDesDemo =0;

	}
	public ArrayList<Joueur> getListeJoueur(){
		return this.listeJoueur;
	}

	//Methode classe Jeu
	public int LanceDeDes(){
		//return 4;
		return (int) ((Math.random() * 6)+1);

	}
	// DEs pour la demo
	public int LanceDeDesDemo(){
		int listeLancer[] = {5,2,2,5,6,3,4,3,1,3,5};
		int val = listeLancer[indexDesDemo];
		indexDesDemo++;
		return val;

	}
	/**
	 * Fonction qui permet d'avancer les joueurs 
	 * @param lancerdes
	 */
	public void AvancerJoueur(int lancerdes){
		int indiceCaseCourante = this.joueurCourant.getCaseCourant().getNumero();
		int resultatDes = indiceCaseCourante+lancerdes;
		if(resultatDes > 23){
			resultatDes = resultatDes - 24;
		}
		Case newCase = this.plateau.getCasePosition(resultatDes);
		this.joueurCourant.setCaseCourante(newCase);
	}

	/**
	 * Fonction qui change le joueur courant 
	 */
	public void ChangementJoueur(){

		if (this.joueurCourant == this.listeJoueur.get(0)) {
			this.joueurCourant = this.listeJoueur.get(1);
		}else {
			this.joueurCourant = this.listeJoueur.get(0);
		}
	}
	/**
	 * boolean isCamembertPlein 
	 * Joueur j 
	 * int Indice J 
	 * @return isCamembertPlein
	 * 
	 * Fonction qui détermine la fin de la partie 
	 */
	public boolean FinDuJeu(){
		boolean isCamenbertPlein = false;
		Joueur j ;
		int indiceJ=0;
		while (!isCamenbertPlein && (indiceJ < listeJoueur.size()))
		{
			j = listeJoueur.get(indiceJ);
			isCamenbertPlein = j.camPlein();
			indiceJ++;
		}
		return isCamenbertPlein;
	}

	public Joueur getJoueurCourant(){
		return this.joueurCourant;
	}

	public String OrdreJoueurDebut(){
		int indiceJoueur =1;
		//int indiceJoueur = (int) ((Math.random() * 2)+1);
		String message ="";
		if (indiceJoueur == 1) {
			message ="C'est "+ this.listeJoueur.get(0).getNom()+" qui commence! Vous pouvez lancer les dés";
			this.joueurCourant = this.listeJoueur.get(0);
		} else {
			message ="C'est "+ this.listeJoueur.get(1).getNom()+" qui commence! Vous pouvez lancer les dés";
			this.joueurCourant = this.listeJoueur.get(1);
		}
		return message;
	}


	public Question ActionCaseQuestion() {

		Couleur couleurquestion = this.joueurCourant.getCaseCourant().getCouleur();
		Carte carte = this.plateau.TirerCarte(couleurquestion);
		joueurCourant.augmenteTotalQuestion(couleurquestion);
		return (Question) carte;
	}

	/**
	 * Focntion qui retourne le message affiché par la carte mystère 
	 * @return message 
	 */
	public String ActionCaseMystere(){

		Carte carte = this.plateau.TirerCarte(Couleur.NOIR);
		String message ="";
		if (carte.getTypeCarte() == TypeCarte.Mystere) {
			message= ((Mystere) carte).Action(this.joueurCourant, this.plateau.getListeCase(), listeJoueur); // ici peut faire retourner un message de l'action
		}
		return message;

	}


	/**
	 * Fonction de création des joueurs 
	 * 
	 * @param nomjoueurun
	 * @param nomJoueurdeux
	 * @param couleurJoueurun
	 * @param couleurJoueurdeux
	 */
	public void CreationJoueur(String nomjoueurun, String nomJoueurdeux, CouleurPion couleurJoueurun, CouleurPion couleurJoueurdeux) {


		Case caseDepart = this.plateau.getListeCase().get(0);

		this.listeJoueur.add(new Joueur(couleurJoueurun, nomjoueurun,caseDepart));
		this.listeJoueur.add(new Joueur(couleurJoueurdeux, nomJoueurdeux,caseDepart));

	}

	public void enregistrementDesScore() {

		BaseScroreCSV BDD = new BaseScroreCSV();
		BDD.EnregistrementDesScoreDeLaPartie(listeJoueur);

	}

	/*public static void main(String[] args) {

		Jeu monJeu = new Jeu();
		int lancer = monJeu.LanceDeDes();
		Case caseJoueur = monJeu.getJoueurCourant().getCaseCourant();
		System.out.println(caseJoueur.getNumero() + " " + caseJoueur.getCouleur());
		System.out.println(lancer);

		monJeu.AvancerJoueur(lancer);
		 caseJoueur = monJeu.getJoueurCourant().getCaseCourant();
		System.out.println(caseJoueur.getNumero() + " " + caseJoueur.getCouleur());





	}*/

}
