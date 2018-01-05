package Controler;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import Model.Couleur;
import Model.CouleurPion;
import Model.Jeu;
import Model.Joueur;
import Model.Question;

public class TrivialPursuiteObservable extends Observable {

	private Jeu jeu;

	public TrivialPursuiteObservable() {
		super();
		this.jeu = new Jeu();
	}


	private boolean isEnd() { return this.jeu.FinDuJeu(); }

	@SuppressWarnings("static-access")
	public void creationJoueur(String nomjoueurun, String nomJoueurdeux, CouleurPion couleurJoueurun, CouleurPion couleurJoueurdeux) {

		JOptionPane jop1 = new JOptionPane();
		String textPbl ="<html>";
		boolean error= false;
		if (nomjoueurun.equals("")) {
			error= true;
			textPbl =textPbl+"- Veuillez entrer un nom pour le joueur 1";
		}
		if (nomJoueurdeux.equals("")) {
			error= true;
			textPbl =textPbl+"<br>- Veuillez entrer un nom pour le joueur 2";
		}
		textPbl =textPbl+"</html>";
		
		if (error) {
			jop1.showMessageDialog(null, textPbl, "Erreur champ manquant", JOptionPane.ERROR_MESSAGE);

		}else{
			//System.out.println("je creer les joueurs ");
			this.jeu.CreationJoueur(nomjoueurun,nomJoueurdeux,couleurJoueurun,couleurJoueurdeux);
			String joueurCommence = this.jeu.OrdreJoueurDebut();
			//this.jeu.getJoueurCourant().AjoutPartCamembert(Couleur.BLEU);
			//this.jeu.getJoueurCourant().AjoutPartCamembert(Couleur.ROUGE);
			//this.jeu.getJoueurCourant().AjoutPartCamembert(Couleur.VERT);
			this.envoiInfo(joueurCommence,"0",null,false,0,false,null,"");
		}
	}

	// s'occupe de faire toute les etapes d'un tour
	public void lancerLesDes() {

		int lancerDes = this.jeu.LanceDeDes();
		//int lancerDes = this.jeu.LanceDeDesDemo();
		System.out.println("###################");
		System.out.println("JOUEUR : "+this.jeu.getJoueurCourant().getNom());
		System.out.println("lancer : "+ lancerDes);
		this.jeu.AvancerJoueur(lancerDes);
		System.out.println("Case COURANTE numero :"+this.jeu.getJoueurCourant().getCaseCourant().getNumero() + " couleur : "
				+this.jeu.getJoueurCourant().getCaseCourant().getCouleur());

		actionCase(lancerDes);
	}


	public void actionCase(int lancerDes) {
		Question question = null;
		String messageMystere= "";
		Boolean actionMystere = false;
		Boolean rejoue = false;
		int isquestion;
		if (this.jeu.getJoueurCourant().getCaseCourant().isQuestion()) { // si c'est une question on pose la question
			question = this.jeu.ActionCaseQuestion();
			isquestion =1;
		}else {// si c'est une case mystere
			isquestion =2;
			messageMystere = this.jeu.ActionCaseMystere();
			System.out.println(messageMystere);
			if (messageMystere.equals("Vous vous dirigez vers la prochaine case Super camembert. Cliquez sur le bouton ci-dessous pour répondre à la question"))
			{
				actionMystere = true;
			}else if (!messageMystere.equals("Vous pouvez rejouer") && !isEnd())  {

				this.jeu.ChangementJoueur();
			}else if (messageMystere.equals("Vous pouvez rejouer") && !isEnd()) {
				rejoue = true;
			}
			//messageMystere = "Vous etes tombe sur une case mystere <br> " +messageMystere;

			if (isEnd()) {
				messageMystere = actionDeFinGame(messageMystere);
			}
		}
		String lancer = ((Integer) lancerDes).toString();
		this.envoiInfo(messageMystere,lancer,question,actionMystere,isquestion,rejoue,null,"");
	}

	private String actionDeFinGame(String message) {

		Joueur jcourant = this.jeu.getJoueurCourant();
		jcourant.estGagnant();
		for (Joueur joueur : this.jeu.getListeJoueur()) {
			if (!(joueur.equals(jcourant))) {
				joueur.estPerdant();
			}
		}
		String messageretour = message+" <br> Bravo "+jcourant.getNom()+" a gagne la partie ";
		this.jeu.enregistrementDesScore();
		return messageretour;
	}

	public void validerReponse(Question question, String reponse) {

		String message="";
		boolean rejoue=false;
		Boolean isbonneRep= false;
		Couleur couleurWin= null;
		String rep = question.getReponse();
		isbonneRep =question.isBonneReponse(reponse);
		// si le joueur a repondu la bonne reponse
		if (isbonneRep) {
			rejoue=true;

			this.jeu.getJoueurCourant().augmenteReponseJuste(question.getCouleur());

			// si le joueur est sur une case super camembert
			if (this.jeu.getJoueurCourant().getCaseCourant().isSuperCamembert()) {
				// si la part a bien ete ajouter
				if (this.jeu.getJoueurCourant().AjoutPartCamembert(question.getCouleur())) { 
					//message = "Bravo vous avez gagne une part de camembert";
					couleurWin = question.getCouleur();
				}else { // si on a deja la part de camembert
					message = "Vous avez repondu juste, mais vous possedez deja une part de camembert "+question.getCouleur();
				}
				//si c'est pas une super camembert
			}else {
				message = "Bravo vous avez repondu juste";
			}
			//si on a pas repondu juste
		}else {
			message = "Mauvaise reponse";
		}	
		//message =  message +"<br>La reponse est :"+question.getReponse()+"<br>" +question.getDescription();
		message = question.getDescription();
		if (isEnd()) {

			message = actionDeFinGame("");

		}else {
			if (!rejoue) { // si le joueur a pas repondu juste il ne rejoue pas
				this.jeu.ChangementJoueur();
				//message= message+", C'est au tour de "+this.jeu.getJoueurCourant().getNom();
			}else{
				//message= message+", vous pouvez rejouer";
			}
		}

		//System.out.println(this.jeu.getJoueurCourant().toString());
		this.envoiInfo(message,"0",null,false,3,isbonneRep,couleurWin,rep);
	}

	private void envoiInfo(String message, String lancerDes,Question question,Boolean prochainCam,int typeAffiche,
			boolean isrepJuste, Couleur couleurwin,String reponse) {

		ArrayList<Object> infoForIHM = new ArrayList<Object>();

		/*
		 * 0 : is end game
		 * 1 : liste des joueurs
		 * 2 : Message a afficher � l'utilisateur
		 * 3 : Nombre lancerDes / ou chaine vide si pas de lancer a renvoyer
		 * 4 : Object Question
		 * 5 : joueur courant
		 * 6 : 0 rien 1 question 2 mystère 3 description reponse
		 * 7 : faire action de la case (Myst�reProchainCamembert)
		 * 8 : reponse juste a la question (boolean)
		 * 9 : couleur de la part de camembet gagner
		 * 10 : reponse
		 */
		infoForIHM.add(isEnd());//0
		infoForIHM.add(this.jeu.getListeJoueur());//1
		infoForIHM.add(message);//2
		infoForIHM.add(lancerDes);//3
		infoForIHM.add(question); //4
		infoForIHM.add(this.jeu.getJoueurCourant()); //5
		infoForIHM.add(typeAffiche); //6
		infoForIHM.add(prochainCam); //7
		infoForIHM.add(isrepJuste); //8
		infoForIHM.add(couleurwin); //9
		infoForIHM.add(reponse);
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
