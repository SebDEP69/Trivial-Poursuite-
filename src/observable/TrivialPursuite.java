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
	}
	
	
	public boolean isEnd() {
		
		return this.jeu.FinDuJeu();
	}
	
	public void creationJoueur(String nomjoueurun, String nomJoueurdeux, CouleurPion couleurJoueurun, CouleurPion couleurJoueurdeux) {
		
		System.out.println("je creer les joueurs ");
		
		
		this.jeu.CreationJoueur(nomjoueurun,nomJoueurdeux,couleurJoueurun,couleurJoueurdeux);
		String joueurCommence = this.jeu.OrdreJoueurDebut();
		

		
		this.envoiInfo(joueurCommence,"",null);
	}
	
	// s'occupe de faire toute les étapes d'un tour
	public void lancerLesDes() {
		
		int lancerDes = this.jeu.LanceDeDes();
		System.out.println("###################");
		System.out.println("JOUEUR : "+this.jeu.getJoueurCourant().getNom());
		System.out.println("lancer : "+ lancerDes);
		this.jeu.AvancerJoueur(lancerDes);
		System.out.println("Case COURANTE numero :"+this.jeu.getJoueurCourant().getCaseCourant().getNumero() + " couleur : "
						+this.jeu.getJoueurCourant().getCaseCourant().getCouleur());
		
		Question question = null;
		String messageMystere= "";
		if (this.jeu.getJoueurCourant().getCaseCourant().isQuestion()) { // si c'est une question on pose la question
			
			question = this.jeu.ActionCaseQuestion();
		}else {// si c'est une case mystère
			
			
			messageMystere = this.jeu.ActionCaseMystere();
			if (!messageMystere.equals("Vous pouvez rejouer"))
			{
				this.jeu.ChangementJoueur();
			}
			
			
			messageMystere = "Vous etes tombe sur une case mystere \n" +messageMystere;
		}
		
	
		String lancer = ((Integer) lancerDes).toString();
		this.envoiInfo(messageMystere,lancer,question);
	}
	
	public void validerReponse(Question question, String reponse) {
		
		String message="";
		boolean rejoue=false;
		// si le joueur à répondu la bonne réponse
		if (question.isBonneReponse(reponse)) {
			rejoue=true;
			// si le joueur est sur une case super camembert
			if (this.jeu.getJoueurCourant().getCaseCourant().isSuperCamembert()) {
				// si la part a bien été ajouter
				if (this.jeu.getJoueurCourant().getCamembert().AjoutPartCamembert(question.getCouleur())) { 
					message = "Bravo vous avez gagne une part de camembert";
				}else { // si on a déjà la part de camembert
					message = "Vous avez repondu juste, mais vous possedez deje une part de camembert "+question.getCouleur();
				}
			//si c'est pas une super camembert
			}else {
				message = "Bravo vous avez repondu juste";
			}
			//si on a pas répondu juste
		}else {
			message = "Mauvaise reponse";
		}
		
		
		if (isEnd()) {
			message = message+ "\n Bravo "+this.jeu.getJoueurCourant().getNom()+" a gagne la partie";
		}else {
			if (!rejoue) { // si le joueur a pas répondu juste il ne rejoue pas
				this.jeu.ChangementJoueur();
				message= message+", C'est au tour de "+this.jeu.getJoueurCourant().getNom();
			}else{
				message= message+", vous pouvez rejouer";
			}
		}
		this.envoiInfo(message,"",null);
	}
	
	
	private void envoiInfo(String message, String lancerDes,Question question) {
		
		ArrayList<Object> infoForIHM = new ArrayList<Object>();
		
		/*
		 * 0 : is end game
		 * 1 : listejoueur
		 * 2 : Message action mystère 
		 * 3 : Nombre lancerDes
		 * 4 : Object Question
		 */
		infoForIHM.add(isEnd());//0
		infoForIHM.add(this.jeu.getListeJoueur());//1
		infoForIHM.add(message);//2
		infoForIHM.add(lancerDes);//3
		infoForIHM.add(question); //4	
		
		
		
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
		
	}
	
	
	
	
}
