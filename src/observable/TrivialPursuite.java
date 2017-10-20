package observable;

import java.util.ArrayList;
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
	
	// s'occupe de faire toute les �tapes d'un tour
	public int lancerLesDes() {
		
		int lancerDes = this.jeu.LanceDeDes();
		//int lancerDes = 4;
		System.out.println("###################");
		System.out.println("JOUEUR : "+this.jeu.getJoueurCourant().getNom());
		System.out.println("lancer : "+ lancerDes);
		this.jeu.AvancerJoueur(lancerDes);
		System.out.println("Case COURANTE num�ro :"+this.jeu.getJoueurCourant().getCaseCourant().getNumero() + " couleur : "
						+this.jeu.getJoueurCourant().getCaseCourant().getCouleur());
		
		
		String messageQuestionMyst�re;
		String reponse= "pas de reponse";
		if (this.jeu.getJoueurCourant().getCaseCourant().isQuestion()) { // si c'est une question on pose la question
			
			System.out.println("pose une question");
			//System.out.println(this.jeu.getJoueurCourant().getCaseCourant().getCouleur());
			
			Question question = this.jeu.ActionCaseQuestion();
			messageQuestionMyst�re = question.getQuestion();
			reponse = question.getReponse();
			System.out.println(question.getQuestion());
			
			
		}else {// si c'est une case myst�re
			
			System.out.println("action case myst�re");
			this.jeu.ActionCaseMystere();
			messageQuestionMyst�re = "action case myst�re"; // r�cupe le message de la fonction action myst�re
			System.out.println(" nouvelle case" + this.jeu.getJoueurCourant().getCaseCourant().getNumero());
			
		}
		
		/*
		 * 0 : Position joueur 1
		 * 1 : Position joueur 2
		 * 2 : Nombre lancerDes
		 * 3 : Question / message action myst�re
		 * 4-7 : R�ponse
		 * 
		 * 
		 * 
		 */
		
		String positionj1 = ((Integer) this.jeu.getListeJoueur().get(0).getCaseCourant().getNumero()).toString();
		String positionj2 = ((Integer) this.jeu.getListeJoueur().get(1).getCaseCourant().getNumero()).toString();
		String lancer = ((Integer) lancerDes).toString();
		ArrayList<String> infoForIHM = new ArrayList<String>();
		infoForIHM.add(positionj1);
		infoForIHM.add(positionj2);
		infoForIHM.add(lancer);
		infoForIHM.add(messageQuestionMyst�re);
		infoForIHM.add(reponse);
		
		
		this.jeu.ChangementJoueur();
		
		this.notifyObservers(infoForIHM);
		return 0; // return le lancer de d�s
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
