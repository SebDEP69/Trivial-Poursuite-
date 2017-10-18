package Model;
import java.util.ArrayList;

public class MonMain {

	public static void main(String[] args) {
		
		
		
		ArrayList<Carte> listeCarteRouge = new ArrayList<Carte>();
		ArrayList<Carte> listeCarteOrange = new ArrayList<Carte>();
		ArrayList<Carte> listeCarteBleu = new ArrayList<Carte>();
		ArrayList<Carte> listeCarteVert = new ArrayList<Carte>();
		ArrayList<Carte> listeCarteMystere = new ArrayList<Carte>();
		
		
		listeCarteRouge.add(new Question("question rouge1", "reponse rouge1", TypeCarte.Question ,Couleur.ROUGE));
		listeCarteRouge.add(new Question("question rouge2", "reponse rouge1", TypeCarte.Question ,Couleur.ROUGE));
		
		listeCarteOrange.add(new Question("question orange1", "reponse orange1", TypeCarte.Question ,Couleur.ORANGE));
		listeCarteOrange.add(new Question("question orange2", "reponse orange1", TypeCarte.Question ,Couleur.ORANGE));
		
		listeCarteBleu.add(new Question("question bleu1", "reponse bleu1", TypeCarte.Question ,Couleur.BLEU));
		listeCarteBleu.add(new Question("question bleu2", "reponse bleu1", TypeCarte.Question ,Couleur.BLEU));
		
		listeCarteVert.add(new Question("question vert1", "reponse vert1", TypeCarte.Question ,Couleur.VERT));
		listeCarteVert.add(new Question("question vert2", "reponse vert1", TypeCarte.Question ,Couleur.VERT));
		
		
		listeCarteMystere.add(new MystereEnleverCamembert());
		
		int nombreCasePlateau = 24;
		
		
		Plateau monPlateau = new Plateau(listeCarteRouge, listeCarteOrange, listeCarteBleu, listeCarteVert, listeCarteMystere, nombreCasePlateau);
		
		
		// Test la fonction Tirer une carte
		/*Question maCarte = null;
		maCarte =  (Question) monPlateau.TirerCarte(Couleur.BLEU);
		
		System.out.println(maCarte.getCouleur()+ "   "+ maCarte.getTypeCarte()+ "  "+ maCarte.getQuestion());
		*/
		// test la fonction liste de Case
	/*	ArrayList<Case> listeCase= monPlateau.getListeCase();
		for (Case case1 : listeCase) {
		//	System.out.println(case1.getCouleur() +"  " +case1.getNumero() + " " +case1.isSuperCamembert());
		}*/
		
		
		
		
		// test MystereGainCamembert
		/*MystereGainCamembert cartemystere = new MystereGainCamembert();
		Joueur joueur1 = new Joueur(new Pion(CouleurPion.BLEU), new Camembert(), "jean", new Case(Couleur.BLEU, 0, false));
		joueur1.getCamembert().AjoutPartCamembert(Couleur.ROUGE);
		
		
		cartemystere.Action(joueur1, null, null);*/
		
		/*for (PartCamembert part : joueur1.getCamembert().getListePart()) {
			System.out.println(part.getCouleur());
		}
		*/
	//	MysterePerteCamembert carteperte = new MysterePerteCamembert();
		//carteperte.Action(joueur1, null, null);
	//	
	//	System.out.println(joueur1.CheckPartCamembert(Couleur.ORANGE));
		/*for (PartCamembert part : joueur1.getCamembert().getListePart()) {
			System.out.println(part.getCouleur());
		}*/

		
		
		
		
		
		
		
		
		
	}

}
