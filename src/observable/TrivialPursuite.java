package observable;

import java.util.Observable;
import java.util.Observer;

import Model.Jeu;

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
	
	public int lancerLesDes() {
		this.jeu.TourJoueur();
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
