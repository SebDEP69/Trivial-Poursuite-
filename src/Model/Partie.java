package Model;

public class Partie {

	private String date;	
	private String [] joueur1,joueur2;
	
	
	
	public Partie(String date, String[] joueur1, String[] joueur2) {
		this.date = date;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
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
