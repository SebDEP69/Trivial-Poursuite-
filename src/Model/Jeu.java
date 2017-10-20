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
		this.CreationJoueur();
		this.OrdreJoueurDebut();
		
	}
	
	//Methode classe Jeu
	public int LanceDeDes(){
		return 0;
		
	}
	
	private boolean AvancerJoueur(){
		return false;
		
	}
	
	public void TourJoueur(){
		
	}
	
	private Carte TirerUneCarte(){
		return null;
		
	}
	
	private boolean ChangementJoueur(){
		return false;
		
	}
	
	public boolean FinDuJeu(){
		return false;
		
	}
	
	private Joueur getJoueurCourant(){
		return this.joueurCourant;
	}
	
	private boolean GainCamembert(){
		return false;
		
	}
	
	private boolean PoserUneQuestion(Carte carte){
		return false;
		
	}
	
	public void DebutDuJeu(ArrayList<Carte> listeCarteRouge, ArrayList<Carte> listeCarteOrange, ArrayList<Carte> listeCarteVerte, ArrayList<Carte> listeCarteBleue, ArrayList<Carte> listeCarteMystere ){
		
	}
	
	private void OrdreJoueurDebut(){
		
		int indiceJoueur = (int) (Math.random() * 2);
		
		if (indiceJoueur == 1) {
			System.out.println("le joueur 1 commence");
			this.joueurCourant = this.listeJoueur.get(0);
		} else {
			System.out.println("le joueur 2 commence");
			this.joueurCourant = this.listeJoueur.get(1);
		}
		
	}
	
	private boolean ActionCaseMystere(){
		return false;
		
	}
	
	
	
	private void CreationJoueur() {
		
		
		Case caseDepart = this.plateau.getListeCase().get(0);
		Scanner sc = new Scanner(System.in);
		String nomjoueur;
		String choixCouleurPionjoueur;
		CouleurPion couleurPion;
		for (int i = 0; i < 2; i++) {
			
			System.out.println("Nom du joueur "+(i+1));
		
			nomjoueur  = sc.nextLine();
			
			System.out.println("Couleur du joueur1"+(i+1));
			
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
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		
		
		
		Jeu monJeu = new Jeu();
		
		
	
	}

}
