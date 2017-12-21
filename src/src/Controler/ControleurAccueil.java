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
import observable.TrivialPursuite;
import vue.Regles;
import vue.ScorePartie;
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
	
	public void afficherScorePartie() {
		ScorePartie frame = new ScorePartie();
			frame.setTitle("ScorePartie");
			frame.setSize(1150, 680);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
	}
	
	public void lancerPartie() {
		TrivialPursuite trivialPursuite = new TrivialPursuite();
		TrivialControler trivialControler = new TrivialControler(trivialPursuite);
		IHMPlateau frame = new IHMPlateau("Trivial Pursuit",trivialControler);
		trivialPursuite.addObserver((Observer) frame);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	public void creerMenu() {
		Menu menu = new Menu("Trivial Pursuit",this);
		menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menu.pack();
		menu.setVisible(true);
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
		
		System.out.println(typeQuestion);
		System.out.println(question);
		System.out.println(reponses[0]);
		System.out.println(reponses[1]);
		System.out.println(reponses[2]);
		System.out.println(reponses[3]);
		System.out.println(numReponseJuste);
		System.out.println(descritption);
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
		
		JOptionPane jop1;
		//Boîte du message d'information
		jop1 = new JOptionPane();
		jop1.showMessageDialog(null, "Votre question a bien été ajoutée", "Ajout de la question", JOptionPane.INFORMATION_MESSAGE);
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
