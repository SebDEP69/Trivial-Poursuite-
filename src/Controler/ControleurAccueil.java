package Controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.BaseQuestionCSV;
import Model.BaseScroreCSV;
import Model.Couleur;
import Model.Partie;
import vue.Regles;
import vue.IHMPlateau;
import vue.Menu;

public class ControleurAccueil  extends Observable{

	public ControleurAccueil() {
		super();
	}


	public void afficherRegle() {

		Regles frame = new Regles();
		frame.setTitle("Regles");
		frame.setSize(1150, 680);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}

	/*public void afficherScorePartie() {
		ScorePartie frame = new ScorePartie();
		frame.setTitle("ScorePartie");
		frame.setSize(1150, 680);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}*/

	public void lancerPartie() {
		TrivialPursuiteObservable trivialPursuite = new TrivialPursuiteObservable();
		TrivialControler trivialControler = new TrivialControler(trivialPursuite);
		IHMPlateau frame = new IHMPlateau("Trivial Pursuit",trivialControler);
		trivialPursuite.addObserver((Observer) frame);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void creerMenu() {

		Menu menu = new Menu("Trivial Pursuit",this);

		menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menu.setVisible(true);
		//menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.addObserver((Observer) menu);
		this.afficherMenu();


	}
	public void afficherMenu() {
		ArrayList<Object> info = new ArrayList<Object>();
		info.add(0);
		this.notifyObservers(info);
	}

	public void afficherPersonalisation() {

		ArrayList<Object> info = new ArrayList<Object>();
		info.add(1);
		this.notifyObservers(info);
	}


	@SuppressWarnings("static-access")
	public void enregisterQuestion(String typeQuestion,String question,String[] reponses,String numReponseJuste,String descritption) {
		//Boîte du message d'information
		JOptionPane jop1 = new JOptionPane();
		String textPbl ="<html>";
		boolean error= false;
		if (typeQuestion.equals("")) {
			error= true;
			textPbl =textPbl+"<br>- Veuillez selectioner un type de question";
		}
		if (question.equals("")) {
			error= true;
			textPbl =textPbl+"<br>- Veuillez entrer une question";
		}
		if (reponses[0].equals("")) {
			error= true;
			textPbl =textPbl+"<br>- Veuillez entrez la réponse 1";
		}
		if (reponses[1].equals("")) {
			error= true;
			textPbl =textPbl+"<br>- Veuillez entrez la réponse 2";
		}
		if (reponses[2].equals("")) {
			error= true;
			textPbl =textPbl+"<br>- Veuillez entrez la réponse 3";
		}
		if (reponses[3].equals("")) {
			error= true;
			textPbl =textPbl+"<br>- Veuillez entrez la réponse 4";
		}
		if (descritption.equals("")) {
			error= true;
			textPbl =textPbl+"<br>- Veuillez entrer une description";
		}
		textPbl =textPbl+"</html>";

		if (error) {
			jop1.showMessageDialog(null, textPbl, "Erreur champ manquant", JOptionPane.ERROR_MESSAGE);

		}else{
			/*System.out.println(typeQuestion);
			System.out.println(question);
			System.out.println(reponses[0]);
			System.out.println(reponses[1]);
			System.out.println(reponses[2]);
			System.out.println(reponses[3]);
			System.out.println(numReponseJuste);
			System.out.println(descritption);*/
			BaseQuestionCSV BDD = new BaseQuestionCSV();

			Couleur color= Couleur.ORANGE;
			switch (typeQuestion) {
			case "CPE":
				color= Couleur.VERT;
				break;
			case "Blague":
				color= Couleur.ORANGE;
				break;
			case "Innovation":
				color= Couleur.ROUGE;
				break;
			case "Le saviez-vous?":
				color= Couleur.BLEU;
				break;
			default:
				color= Couleur.ORANGE;
				break;
			}

			try {
				BDD.ajouterQuestion(color, question, reponses, numReponseJuste, descritption);
			} catch (IOException e) {
				e.printStackTrace();
			}



			jop1.showMessageDialog(null, "<html>Votre question a bien été ajoutée <br> coucou</html>", "Ajout de la question", JOptionPane.INFORMATION_MESSAGE);
		}
	}


	public void afficherHistoriqueDesScores() {

		BaseScroreCSV BDD = new BaseScroreCSV();
		ArrayList<Partie> listePartie= new ArrayList<Partie>();
		ArrayList<Object> info = new ArrayList<Object>();
		info.add(2);
		try {
			listePartie = BDD.getInfoAllGame();
			System.out.println(listePartie.getClass());
			info.add(listePartie);
		} catch (IOException e) {
			e.printStackTrace();
		}


		this.notifyObservers(info);

	}


	public void AfficherdetailPartie(Partie partie){


		ArrayList<Object> info = new ArrayList<Object>();
		info.add(3);
		info.add(partie);
		this.notifyObservers(info);
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
