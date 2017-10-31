package observable;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Model.CouleurPion;
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
	
	public void creationJoueur(String nomjoueurun, String nomJoueurdeux, CouleurPion couleurJoueurun, CouleurPion couleurJoueurdeux) {
		
		System.out.println("je cr�e les joueur ");
		
		
		/*
		 * 0 : Position joueur 1
		 * 1 : Position joueur 2
		 * 2 : Message action myst�re 
		 * 3 : Nombre lancerDes
		 * 4 : Object Question
		 * 
		 * 
		 * 
		 */
		this.jeu.CreationJoueur(nomjoueurun,nomJoueurdeux,couleurJoueurun,couleurJoueurdeux);
		String joueurCommence = this.jeu.OrdreJoueurDebut();
		
		int positionj1 = this.jeu.getListeJoueur().get(0).getCaseCourant().getNumero();
		int positionj2 = this.jeu.getListeJoueur().get(1).getCaseCourant().getNumero();
		
		ArrayList<Object> infoForIHM = new ArrayList<Object>();
		infoForIHM.add(positionj1);//0
		infoForIHM.add(positionj2);//1
		infoForIHM.add(joueurCommence); // utiliser ca pour dire le joueur qui commence //2
		infoForIHM.add("");//3
		this.notifyObservers(infoForIHM);
	}
	
	// s'occupe de faire toute les �tapes d'un tour
	public void lancerLesDes() {
		
		int lancerDes = this.jeu.LanceDeDes();
		//int lancerDes = 4;
		System.out.println("###################");
		System.out.println("JOUEUR : "+this.jeu.getJoueurCourant().getNom());
		System.out.println("lancer : "+ lancerDes);
		this.jeu.AvancerJoueur(lancerDes);
		System.out.println("Case COURANTE num�ro :"+this.jeu.getJoueurCourant().getCaseCourant().getNumero() + " couleur : "
						+this.jeu.getJoueurCourant().getCaseCourant().getCouleur());
		
		Question question = null;
		String messageMyst�re= "";
		String[] choix= null;
		if (this.jeu.getJoueurCourant().getCaseCourant().isQuestion()) { // si c'est une question on pose la question
			
			System.out.println("pose une question");
			//System.out.println(this.jeu.getJoueurCourant().getCaseCourant().getCouleur());
			
			question = this.jeu.ActionCaseQuestion();
			System.out.println(question.getQuestion());			
		}else {// si c'est une case myst�re
			
			System.out.println("action case myst�re");
			this.jeu.ActionCaseMystere();
			messageMyst�re = "action case myst�re"; // r�cupe le message de la fonction action myst�re
			System.out.println(" nouvelle case" + this.jeu.getJoueurCourant().getCaseCourant().getNumero());
			this.jeu.ChangementJoueur();
		}
		
		/*
		 * 0 : Position joueur 1
		 * 1 : Position joueur 2
		 * 2 : Message action myst�re 
		 * 3 : Nombre lancerDes
		 * 4 : Object Question
		 * 
		 * 
		 * 
		 */
		
		int positionj1 = this.jeu.getListeJoueur().get(0).getCaseCourant().getNumero();
		int positionj2 = this.jeu.getListeJoueur().get(1).getCaseCourant().getNumero();
		String lancer = ((Integer) lancerDes).toString();
		ArrayList<Object> infoForIHM = new ArrayList<Object>();
		infoForIHM.add(positionj1);//0
		infoForIHM.add(positionj2);//1
		infoForIHM.add(messageMyst�re);//2
		infoForIHM.add(lancer);//3
		infoForIHM.add(question); //4
			
		
		
		this.notifyObservers(infoForIHM);
	}
	
	
	
	
	
	public void validerReponse(Question question, String reponse) {
		
		String message="";
		boolean rejoue=false;
		// si le joueur � r�pondu la bonne r�ponse
		if (question.isBonneReponse(reponse)) {
			rejoue=true;
			// si le joueur est sur une case super camembert
			if (this.jeu.getJoueurCourant().getCaseCourant().isSuperCamembert()) {
				// si la part a bien �t� ajouter
				if (this.jeu.getJoueurCourant().getCamembert().AjoutPartCamembert(question.getCouleur())) { 
					message = "Bravo vous avez gagner une part de camembert";
				}else { // si on a d�j� la part de camembert
					message = "Vous avez r�pondu juste mais vous poss�dez d�j� une part de camembert "+question.getCouleur();
				}
			//si c'est pas une super camembert
			}else {
				message = "Bravo vous avez r�pondu juste";
			}
			//si on a pas r�pondu juste
		}else {
			message = "Mauvaise r�ponse";
		}
		
		
		
		if (!rejoue) { // si le joueur a pas r�pondu juste il ne rejoue pas
			this.jeu.ChangementJoueur();
			message= message+", C'est au tour de "+this.jeu.getJoueurCourant().getNom();
		}else{
			message= message+", vous pouvez rejouer";
		}
		/*
		 * 0 : Position joueur 1
		 * 1 : Position joueur 2
		 * 2 : Message action myst�re 
		 * 3 : Nombre lancerDes
		 * 4 : Object Question
		 * 
		 * 
		 * 
		 */
		int positionj1 = this.jeu.getListeJoueur().get(0).getCaseCourant().getNumero();
		int positionj2 = this.jeu.getListeJoueur().get(1).getCaseCourant().getNumero();
		String lancer = "";
		ArrayList<Object> infoForIHM = new ArrayList<Object>();
		
		infoForIHM.add(positionj1);//0
		infoForIHM.add(positionj2);//1
		infoForIHM.add(message);//2
		infoForIHM.add(lancer);//3
		infoForIHM.add(null); //4	
		
		
		this.notifyObservers(infoForIHM);
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
