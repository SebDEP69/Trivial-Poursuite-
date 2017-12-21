package Model;

public class Case {

	private Couleur couleur;
	private int numero;
	private boolean superCamembert;
	
	//contructeur
	public Case(Couleur couleur, int numero, boolean superCamembert) {
		this.couleur = couleur;
		this.numero = numero;
		this.superCamembert = superCamembert;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public int getNumero() {
		return numero;
	}
	

	public boolean isSuperCamembert() {
		return superCamembert;
	}

	public  Boolean isQuestion() {
		
		Boolean valeurretour = false;
		if (this.getCouleur() == Couleur.NOIR) {
			valeurretour = false;
		} else {
			valeurretour = true;
		}
		
		
		return valeurretour;
	}
	
	
	
}
