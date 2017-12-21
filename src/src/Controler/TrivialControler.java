package Controler;

import Model.CouleurPion;
import Model.Question;
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
	
	public void validerReponse(Question question,String reponse) {
		this.trivialPursuite.validerReponse(question,reponse);
	}
	public void actionCasePourCarteMystere() {
		this.trivialPursuite.actionCase(0);
	}
	
}
