package Model;

public class Score {

	
	private int nbPartCamembert;
	private String gagnant; // oui / non
	public Score() {
		this.nbPartCamembert = 0;
		this.gagnant = null;
	}
	
	public int getNbPartCamembert() {
		return nbPartCamembert;
	}
	
	public String getGagnant() {
		return gagnant;
	}
	
	public void ajoutPart() {
		this.nbPartCamembert++;
	}
	
	public void supprimePart() {
		this.nbPartCamembert--;
	}
	
	public void estGagnant() {
		this.gagnant = "OUI";
	}
	
	public void estPerdant() {
		this.gagnant = "NON";
	}
	
	
	
}
