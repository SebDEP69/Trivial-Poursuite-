package Controler;

import observable.TrivialPursuite;

public class TrivialControler {

	
	private TrivialPursuite trivialPursuite;

	
	public TrivialControler(TrivialPursuite trivialPursuite) {
		this.trivialPursuite = trivialPursuite;
	}
	
	public void lancerLesDes() {
		this.trivialPursuite.lancerLesDes();
	}
	
	public boolean isEnd() {
		
		return this.trivialPursuite.isEnd();
	}
	
}
