package Model;
import java.util.ArrayList;

public class Jeu {
	
	private Joueur joueurCourant;
	private Plateau plateau;
	private ArrayList<Joueur> listeJoueur = new ArrayList<>();
	
	//Constructeur
	public Jeu(){
		this.plateau = new Plateau();
		
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
		boolean isCamenbertPlein = false;
		Joueur j ;
		int indiceJ=0;
		while (!isCamenbertPlein && (indiceJ < listeJoueur.size()))
		{
			j = listeJoueur.get(indiceJ);
			isCamenbertPlein = j.getCamembert().Plein();
			indiceJ++;
		}
		return isCamenbertPlein;
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
			message ="C'est "+ this.listeJoueur.get(0).getNom()+" qui commence";
			this.joueurCourant = this.listeJoueur.get(0);
		} else {
			message ="C'est "+ this.listeJoueur.get(1).getNom()+" qui commence";
			this.joueurCourant = this.listeJoueur.get(1);
		}
		return message;
	}
	
	
	public Question ActionCaseQuestion() {
		
		Couleur couleurquestion = this.joueurCourant.getCaseCourant().getCouleur();
		Carte carte = this.plateau.TirerCarte(couleurquestion);
	
		return (Question) carte;
	}
	
		
	public String ActionCaseMystere(){
		
		Carte carte = this.plateau.TirerCarte(Couleur.NOIR);
		String message ="";
		if (carte.getTypeCarte() == TypeCarte.Mystere) {
			message= ((Mystere) carte).Action(this.joueurCourant, this.plateau.getListeCase(), listeJoueur); // ici peut faire retourner un message de l'action
		}
		return message;
		
	}
	
	
	
	public void CreationJoueur(String nomjoueurun, String nomJoueurdeux, CouleurPion couleurJoueurun, CouleurPion couleurJoueurdeux) {
		
		
		Case caseDepart = this.plateau.getListeCase().get(0);
		
		this.listeJoueur.add(new Joueur(couleurJoueurun, nomjoueurun,caseDepart));
		this.listeJoueur.add(new Joueur(couleurJoueurdeux, nomJoueurdeux,caseDepart));
		
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
