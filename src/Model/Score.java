package Model;

public class Score {

	
	private int nbPartCamembert;
	private String gagnant; // oui / non
	private int totalBleu,totalRouge,totalVert,totalOrange,repJusteRouge,repJusteBleu,repJusteVert,repJusteOrange;
	public Score() {
		this.nbPartCamembert = 0;
		this.gagnant = null;
		this.totalBleu = 0;
		this.totalRouge = 0;
		this.totalVert = 0;
		this.totalOrange = 0;
		this.repJusteRouge = 0;
		this.repJusteBleu = 0;
		this.repJusteVert = 0;
		this.repJusteOrange = 0;
	}
	
	

	public int getNbPartCamembert() {
		return nbPartCamembert;
	}
	
	public String getGagnant() {
		return gagnant;
	}
	
	public Boolean isGagnant() {
		return this.gagnant.equals("OUI");
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



	
	
	
	
	public int getTotalBleu() {
		return totalBleu;
	}
	public void augmenteTotalBleu() {
		this.totalBleu++;
	}
	
	

	public int getTotalRouge() {
		return totalRouge;
	}
	public void augmenteTotalRouge() {
		this.totalRouge++;
	}
	
	



	public int getTotalVert() {
		return totalVert;
	}
	public void augmenteTotalVert() {
		this.totalVert++;
	}
	
	



	public int getTotalOrange() {
		return totalOrange;
	}
	public void augmenteTotalOrange() {
		this.totalOrange++;
	}
	
	



	public int getRepJusteRouge() {
		return repJusteRouge;
	}
	public void augmenteRepJusteRouge() {
		this.repJusteRouge++;
	}


	public int getRepJusteBleu() {
		return repJusteBleu;
	}
	public void augmenteRepJusteBleu() {
		this.repJusteBleu++;
	}
	
	
	public int getRepJusteVert() {
		return repJusteVert;
	}
	public void augmenteRepJusteVert() {
		this.repJusteVert++;
	}
	
	
	public int getRepJusteOrange() {
		return repJusteOrange;
	}
	public void augmenteRepJusteOrange() {
		this.repJusteOrange++;
	}



	@Override
	public String toString() {
		String val = "totalBleu= "+this.totalBleu+"\n"+ 
				"totalRouge = "+this.totalRouge+"\n"+ 
				"totalVert = "+this.totalVert+"\n"+ 
				"totalOrange = "+this.totalOrange+"\n"+ 
				"repJusteRouge = "+this.repJusteRouge+"\n"+
				"repJusteBleu = "+this.repJusteBleu+"\n"+
				"repJusteVert = "+this.repJusteVert+"\n"+
				"repJusteOrange = "+this.repJusteOrange+"\n";
				
		return val;
	}
	
	
	
}
