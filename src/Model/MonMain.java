package Model;
import java.util.ArrayList;

public class MonMain {

	public static void main(String[] args) {
		
		
		
		ArrayList<Carte> listeCarteRouge = new ArrayList<Carte>();
		ArrayList<Carte> listeCarteOrange = new ArrayList<Carte>();
		ArrayList<Carte> listeCarteBleu = new ArrayList<Carte>();
		ArrayList<Carte> listeCarteVert = new ArrayList<Carte>();
		ArrayList<Carte> listeCarteMystere = new ArrayList<Carte>();
		
		String[] choixRougeun = {"repRed1","repRed2","repRed3","repRed4"};
		String[] choixRougedeux = {"repRed5","repRed6","repRed7","repRed8"};
		listeCarteRouge.add(new Question("question rouge1", 1,choixRougeun ,Couleur.ROUGE,"description"));
		listeCarteRouge.add(new Question("question rouge2", 1, choixRougedeux ,Couleur.ROUGE,"description"));
		
		
		String[] choixOrangeun = {"repOrange1","repOrange2","repOrange3","repOrange4"};
		String[] choixOrangedeux = {"repOrange5","repOrange6","repOrange7","repOrange8"};
		listeCarteOrange.add(new Question("question orange1", 1, choixOrangeun ,Couleur.ORANGE,"description"));
		listeCarteOrange.add(new Question("question orange2", 1, choixOrangedeux ,Couleur.ORANGE,"description"));
		
		
		String[] choixBleueun = {"repBleu1","repBleu2","repBleu3","repBleu4"};
		String[] choixBleudeux = {"repBleu5","repBleu6","repBleu7","repBleu8"};
		listeCarteBleu.add(new Question("question bleu1", 1, choixBleueun ,Couleur.BLEU,"description"));
		listeCarteBleu.add(new Question("question bleu2", 1, choixBleudeux ,Couleur.BLEU,"description"));
		
		
		String[] choixVertun = {"repert1","reperte2","repert3","repert4"};
		String[] choixVertdeux = {"repert5","repert6","repert7","repert8"};
		listeCarteVert.add(new Question("question vert1", 1, choixVertun ,Couleur.VERT,"description"));
		listeCarteVert.add(new Question("question vert2", 1, choixVertdeux ,Couleur.VERT,"description"));
		
		

		listeCarteMystere.add(new MystereProchainCamembert());
		
		int nombreCasePlateau = 24;
		
		Plateau monPlateau = new Plateau(listeCarteRouge, listeCarteOrange, listeCarteBleu, listeCarteVert, listeCarteMystere, nombreCasePlateau);
		
		
		// Test la fonction Tirer une carte
		/*Question maCarte = null;
		maCarte =  (Question) monPlateau.TirerCarte(Couleur.BLEU);
		
		System.out.println(maCarte.getCouleur()+ "   "+ maCarte.getTypeCarte()+ "  "+ maCarte.getQuestion());
		*/
		// test la fonction liste de Case
		
		ArrayList<Case> listeCase= monPlateau.getListeCase();
		for (Case case1 : listeCase) {
		System.out.println(case1.getCouleur() +"  " +case1.getNumero() + " " +case1.isSuperCamembert());
		}
		
		
		
		
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
		
		
		// Test fonction Retour case depart
		
		/*MystereRetourCaseDepart retourDepart = new MystereRetourCaseDepart();
		Joueur joueur1 = new Joueur(CouleurPion.BLEU, "Anthony", new Case(Couleur.BLEU, 5, false));
		System.out.println(joueur1.getCaseCourant().getNumero());
		
		retourDepart.Action(joueur1, null, null);
		
		System.out.println(joueur1.getCaseCourant().getNumero());*/

		// Test fonction Prochain camembert
		/*
		MystereProchainCamembert prochainCamembert = new MystereProchainCamembert();
		Joueur joueur1 = new Joueur(CouleurPion.BLEU, "Anthony", new Case(Couleur.BLEU, 7, false));
		System.out.println(joueur1.getCaseCourant().getNumero());
		
		prochainCamembert.Action(joueur1, monPlateau.getListeCase(), null);
		
		System.out.println(joueur1.getCaseCourant().getNumero());*/
		
		
		
		
		
		
		
		
		
	}

}
