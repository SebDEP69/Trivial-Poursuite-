package Model;

public class TestMain {

	public static void main(String[] args) {
		Camembert camembert = new Camembert();
		
		System.out.println(camembert.Vide());
		camembert.AjoutPartCamembert(Couleur.BLEU);
		System.out.println(camembert.Vide());
		camembert.AjoutPartCamembert(Couleur.ROUGE);
		camembert.AjoutPartCamembert(Couleur.BLEU);
		camembert.AjoutPartCamembert(Couleur.ORANGE);
		camembert.AjoutPartCamembert(Couleur.VERT);
		System.out.println(camembert.Plein());
	}

}
