package Controler;

import Model.CouleurPion;
import observable.TrivialPursuite;

public class TrivialControler {

	
	private TrivialPursuite trivialPursuite;

	
	public TrivialControler(TrivialPursuite trivialPursuite) {
		this.trivialPursuite = trivialPursuite;
	}
	
	public void lancerLesDes() {
		this.trivialPursuite.lancerLesDes();
	}
	
	public void creationJoueur(String nomjoueurun, String nomJoueurdeux, CouleurPion couleurJoueurun, CouleurPion couleurJoueurdeux) {
		
		this.trivialPursuite.creationJoueur(nomjoueurun,nomJoueurdeux,couleurJoueurun,couleurJoueurdeux);
	}
	public boolean isEnd() {
		
		return this.trivialPursuite.isEnd();
	}
	
}
