package Model;

public class Partie {

	private String date;	
	private String [] joueur1,joueur2;
	
	
	
	public Partie(String date, String[] joueur1, String[] joueur2) {
		this.date = date;
		/*
		 * joueur1 et joueur2:
		 * 0 : nom
		 * 1 : nb camembert 
		 * 2 : Gagnant OUI/NON 
		 */
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
	}

	public String getNomJ1() {
		return joueur1[0];
	}
	
	public String getNBcamJ1() {
		return joueur1[1];
	}
	
	public String getGagnantJ1() {
		return joueur1[2];
	}
	
	public String getNomJ2() {
		return joueur2[0];
	}
	
	public String getNBcamJ2() {
		return joueur2[1];
	}
	
	public String getGagnantJ2() {
		return joueur2[2];
	}
	
	public String getDate() {
		return date;
	}


	public String[] getJoueur1() {
		return joueur1;
	}


	public String[] getJoueur2() {
		return joueur2;
	}
	
	
}
