package observable;

import java.util.Observable;
import java.util.Observer;

import Model.Jeu;
import Model.Question;

public class TrivialPursuite extends Observable {

	private Jeu jeu;

	public TrivialPursuite() {
		super();
		this.jeu = new Jeu();
		//this.notifyObservers(jeu.getListeJoueur());
		// ajouter la notifyObservers ici
	}
	
	
	public boolean isEnd() {
		
		return this.jeu.FinDuJeu();
	}
	
	// s'occupe de faire toute les étapes d'un tour
	public int lancerLesDes() {
		
		int lancerDes = this.jeu.LanceDeDes();
		//int lancerDes = 4;
		System.out.println("###################");
		System.out.println("JOUEUR : "+this.jeu.getJoueurCourant().getNom());
		System.out.println("lancer : "+ lancerDes);
		this.jeu.AvancerJoueur(lancerDes);
		System.out.println("Case COURANTE numéro :"+this.jeu.getJoueurCourant().getCaseCourant().getNumero() + " couleur : "
						+this.jeu.getJoueurCourant().getCaseCourant().getCouleur());
		
		if (this.jeu.getJoueurCourant().getCaseCourant().isQuestion()) { // si c'est une question on pose la question
			
			System.out.println("pose une question");
			//System.out.println(this.jeu.getJoueurCourant().getCaseCourant().getCouleur());
			
			Question question = this.jeu.ActionCaseQuestion();
			
			System.out.println(question.getQuestion());
			
			
		}else {// si c'est une case mystère
			
			System.out.println("action case mystère");
			this.jeu.ActionCaseMystere();
			System.out.println(" nouvelle case" + this.jeu.getJoueurCourant().getCaseCourant().getNumero());
			
		}
		
		
		
		this.jeu.ChangementJoueur();
		
		this.notifyObservers();
		return 0; // return le lancer de dés
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void	notifyObservers(Object arg) {
		super.setChanged();
		super.notifyObservers(arg); 
	}

	/* (non-Javadoc)
	 * @see java.util.Observable#addObserver(java.util.Observer)
	 */
	@Override
	public void addObserver(Observer o){
		super.addObserver(o);
	//	this.notifyObservers(jeu.getListeJoueur());
		
	}
	
	
	
	
}
