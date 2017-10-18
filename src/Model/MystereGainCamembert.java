package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MystereGainCamembert extends Mystere{

	//Constructeur
	public MystereGainCamembert() {
		super();

	}

	//Methode classe MystereGainCamembert
	@Override
	void Action(Joueur joueurcourant, ArrayList<Case> listecase, ArrayList<Joueur> listejoueur) {

		Camembert camembertjoueur = joueurcourant.getCamembert();
		Couleur serie[] = {Couleur.VERT, Couleur.ORANGE,Couleur.BLEU, Couleur.ROUGE};
		
		int indice = 1;
		HashMap<Integer, Couleur> proposition = new HashMap<Integer, Couleur>();
		
		System.out.println("quelle couleur voulez vous avoir? \n ");
		for (Couleur couleur : serie) {
			if (!camembertjoueur.ContientPart(couleur)) {
				
				proposition.put(indice, couleur);
				System.out.println(indice+" : "+couleur+"\n");
				indice++;
			}
			
		}
					
		Scanner sc = new Scanner(System.in);
		int choix = sc.nextInt();
		
		switch (choix) {
		case 1:
			camembertjoueur.AjoutPartCamembert(proposition.get(1));
			break;
		case 2:
			camembertjoueur.AjoutPartCamembert(proposition.get(2));
			break;
		case 3:
			camembertjoueur.AjoutPartCamembert(proposition.get(3));
			break;
		case 4:
			camembertjoueur.AjoutPartCamembert(proposition.get(4));
			break;
		default:
			camembertjoueur.AjoutPartCamembert(Couleur.ROUGE);
			break;
		}

		sc.close();
	}

}
