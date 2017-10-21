package Model;
import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
	
	private Joueur joueurCourant;
	private Plateau plateau;
	private ArrayList<Joueur> listeJoueur = new ArrayList<>();
	
	//Constructeur
	public Jeu(){
		this.plateau = new Plateau();
		//this.CreationJoueur();
		//this.OrdreJoueurDebut();
		
	}
	
	
	
	public ArrayList<Joueur> getListeJoueur(){
		
		return this.listeJoueur;
	}
	
	//Methode classe Jeu
	public int LanceDeDes(){
		return (int) ((Math.random() * 6)+1);
		
	}
	
	public void AvancerJoueur(int lancerdes){
		
		
		int indiceCaseCourante = this.joueurCourant.getCaseCourant().getNumero();
		int resultatDes = indiceCaseCourante+lancerdes;

		
		if(resultatDes > 23){
			resultatDes = resultatDes - 24;
		}
			Case newCase = this.plateau.getCasePosition(resultatDes);
			this.joueurCourant.setCaseCourante(newCase);

		
		
	}
	
	public void TourJoueur(){
		
	}
	
	public Carte TirerUneCarte(){
		return null;
		
	}
	
	public void ChangementJoueur(){
		
		if (this.joueurCourant == this.listeJoueur.get(0)) {
			this.joueurCourant = this.listeJoueur.get(1);
		}else {
			this.joueurCourant = this.listeJoueur.get(0);
		}
		
	}
	
	public boolean FinDuJeu(){
		return false;
		
	}
	
	public Joueur getJoueurCourant(){
		return this.joueurCourant;
	}
	
	public boolean GainCamembert(){
		return false;
		
	}
	
	public boolean PoserUneQuestion(Carte carte){
		return false;
		
	}
	
	public void DebutDuJeu(ArrayList<Carte> listeCarteRouge, ArrayList<Carte> listeCarteOrange, ArrayList<Carte> listeCarteVerte, ArrayList<Carte> listeCarteBleue, ArrayList<Carte> listeCarteMystere ){
		
	}
	
	public String OrdreJoueurDebut(){
		
		int indiceJoueur = (int) ((Math.random() * 2)+1);
		String message ="";
		if (indiceJoueur == 1) {
			message ="C'est "+ this.listeJoueur.get(0).getNom()+" qui commence commence";
			this.joueurCourant = this.listeJoueur.get(0);
		} else {
			message ="C'est "+ this.listeJoueur.get(1).getNom()+" qui commence commence";
			this.joueurCourant = this.listeJoueur.get(1);
		}
		return message;
	}
	
	
	public Question ActionCaseQuestion() {
		
		Couleur couleurquestion = this.joueurCourant.getCaseCourant().getCouleur();
		Carte carte = this.plateau.TirerCarte(couleurquestion);
	
		return (Question) carte;
	}
	
		
	public void ActionCaseMystere(){
		
		Carte carte = this.plateau.TirerCarte(Couleur.NOIR);
		
		if (carte.getTypeCarte() == TypeCarte.Mystere) {
			((Mystere) carte).Action(this.joueurCourant, this.plateau.getListeCase(), listeJoueur);
		}
		
		
	}
	
	
	
	public void CreationJoueur(String nomjoueurun, String nomJoueurdeux, CouleurPion couleurJoueurun, CouleurPion couleurJoueurdeux) {
		
		
		Case caseDepart = this.plateau.getListeCase().get(0);
		
		this.listeJoueur.add(new Joueur(couleurJoueurun, nomjoueurun,caseDepart));
		this.listeJoueur.add(new Joueur(couleurJoueurdeux, nomJoueurdeux,caseDepart));
		
		/*Scanner sc = new Scanner(System.in);
		String nomjoueur;
		String choixCouleurPionjoueur;
		CouleurPion couleurPion;
		for (int i = 0; i < 2; i++) {
			
			System.out.println("Nom du joueur "+(i+1)+"?");
		
			nomjoueur  = sc.nextLine();
			
			System.out.println("Couleur du joueur1"+(i+1)+"?");
			
			choixCouleurPionjoueur  = sc.nextLine();
			
			switch (choixCouleurPionjoueur) {
	          case "rouge" : couleurPion = CouleurPion.ROUGE;
	                   break;
	          case "bleu":    couleurPion = CouleurPion.BLEU;
	                   break;
	          case "orange": couleurPion = CouleurPion.ORANGE;
	                   break;
	          case "vert":  couleurPion = CouleurPion.VERT;
	                   break;
	          default : couleurPion = CouleurPion.ROUGE;
		  }
			
			this.listeJoueur.add(new Joueur(couleurPion, nomjoueur,caseDepart));
		}
		*/
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		
		Jeu monJeu = new Jeu();
		int lancer = monJeu.LanceDeDes();
		Case caseJoueur = monJeu.getJoueurCourant().getCaseCourant();
		System.out.println(caseJoueur.getNumero() + " " + caseJoueur.getCouleur());
		System.out.println(lancer);
		
		monJeu.AvancerJoueur(lancer);
		 caseJoueur = monJeu.getJoueurCourant().getCaseCourant();
		System.out.println(caseJoueur.getNumero() + " " + caseJoueur.getCouleur());
	}

}
