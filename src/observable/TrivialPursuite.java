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
		
		
		this.jeu.CreationJoueur(nomjoueurun,nomJoueurdeux,couleurJoueurun,couleurJoueurdeux);
		String joueurCommence = this.jeu.OrdreJoueurDebut();
		

		
		this.envoiInfo(joueurCommence,"",null);
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
		if (this.jeu.getJoueurCourant().getCaseCourant().isQuestion()) { // si c'est une question on pose la question
			
			question = this.jeu.ActionCaseQuestion();
		}else {// si c'est une case myst�re
			
			
			messageMyst�re = this.jeu.ActionCaseMystere();
			if (!messageMyst�re.equals("Vous pouvez rejouer"))
			{
				this.jeu.ChangementJoueur();
			}
			
			
			messageMyst�re = "Vous etes tombe sur une case mystere \n" +messageMyst�re;
		}
		
	
		String lancer = ((Integer) lancerDes).toString();
		this.envoiInfo(messageMyst�re,lancer,question);
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
		
		
		if (isEnd()) {
			message = message+ "\n Bravo "+this.jeu.getJoueurCourant().getNom()+" a gagn� la partie";
		}else {
			if (!rejoue) { // si le joueur a pas r�pondu juste il ne rejoue pas
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
		 * 2 : Message action myst�re 
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
	//	this.notifyObservers(jeu.getListeJoueur());
		
	}
	
	
	
	
}
