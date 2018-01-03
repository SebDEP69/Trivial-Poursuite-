package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BaseScroreCSV {

	private String nomFichier;
	private final String separateur;




	public BaseScroreCSV() {
		this.nomFichier = "BDDScore.csv";
		this.separateur= ";";
	}


	private static String getDate() {
		String format = "dd/MM/yy H:mm:ss"; 
		SimpleDateFormat formater = new SimpleDateFormat( format ); 
		Date date = new Date(); 
		String dateDuJour = formater.format( date );

		return dateDuJour;
	}
	public void EnregistrementDesScoreDeLaPartie(ArrayList<Joueur> listeJoueur)  {

		try {
			String dateDuJour = getDate();	
			String IDgame;
			IDgame = (( Integer )this.getIDgame()).toString();

			FileWriter ficher = new FileWriter(this.nomFichier,true);
			BufferedWriter writer = new BufferedWriter(ficher);

			for (Joueur joueur : listeJoueur) {
				String nomJ= joueur.getNom();
				String Nbcam = (( Integer )joueur.getNbPart()).toString();
				String vainceur = joueur.getGagnant();
				int total[] = joueur.getTottalAll();
				int repJuste[] = joueur.getReponseJusteAll();
			
				String ligne = IDgame+separateur+dateDuJour+separateur+nomJ+separateur+Nbcam+separateur+vainceur;
				
				for (int i = 0; i < repJuste.length; i++) {
					ligne = ligne +separateur+total[i]+separateur+repJuste[i];
				}
				
				
				
				
				writer.newLine();
				writer.write(ligne);

			}
			writer.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private int getIDgame() throws IOException {

		FileReader ficher = new FileReader(this.nomFichier);

		BufferedReader reader = new BufferedReader (ficher);
		String lignetmp="";
		String ligne = "";
		while ( (lignetmp = reader.readLine())!=null ) {
			ligne= lignetmp;
		}

		String IdTemp = decoupe(ligne,1);
		int ID;
		if (!IdTemp.equals("ID partie") && !IdTemp.equals("")) {
			ID =Integer.parseInt(IdTemp)+1;
		}else {
			ID =1;
		}
		reader.close();
		return ID;
	}



	/*@SuppressWarnings("unused")
	private String getLastIDgame() throws IOException {

		FileReader ficher = new FileReader(this.nomFichier);

		BufferedReader reader = new BufferedReader (ficher);
		String lignetmp="";
		String ligne = "";
		while ( (lignetmp = reader.readLine())!=null ) {
			ligne= lignetmp;
		}

		String IdTemp = decoupe(ligne,1);
		String ID;
		if (!IdTemp.equals("ID partie") && !IdTemp.equals("")) {
			ID =IdTemp;
		}else {
			ID ="0";
		}
		reader.close();
		return ID;
	}*/

	private  String decoupe(String ligne,int indice) {


		String[] temp = ligne.split(this.separateur);
		if (temp.length!=0) {
			return temp[indice-1];
		}else {
			return "";
		}


	}

	/*public Partie getInfoLastGame() throws IOException{

		FileReader ficher = new FileReader(this.nomFichier);

		BufferedReader reader = new BufferedReader (ficher);
		String ligne;
		String lastIDGame = this.getLastIDgame();

		String [] tempLigne = {"","",""};
		int i =0;

		while ( (ligne = reader.readLine())!=null ) {

			if ((decoupe(ligne, 1)).equals(lastIDGame)) {
				tempLigne[i]=ligne;
				i++;
			}
		}

		String [] joueur1= {decoupe(tempLigne[0], 3),decoupe(tempLigne[0], 4),decoupe(tempLigne[0], 5)};
		String [] joueur2= {decoupe(tempLigne[1], 3),decoupe(tempLigne[1], 4),decoupe(tempLigne[1], 5)};
		String date = decoupe(tempLigne[0], 2);



		Partie partie = new Partie(date, joueur1, joueur2);
		System.out.println("ligne"+tempLigne[0]);
		System.out.println("ligne"+tempLigne[1]);
		reader.close();

		return partie;
	}*/


	public  ArrayList<Partie> getInfoAllGame() throws IOException{

		FileReader ficher = new FileReader(this.nomFichier);

		BufferedReader reader = new BufferedReader (ficher);
		String ligne;
		String ID = "1";
		ArrayList<String[]> joueurs = new ArrayList<String[]>();
		ArrayList<String[]> reponseJoueurs = new ArrayList<String[]>();
		String date ="";

		ArrayList<Partie> listePartie = new ArrayList<Partie>();
		reader.readLine();
		while ( (ligne = reader.readLine())!=null ) {

			if (!ligne.equals("")) {
				
				if (!(decoupe(ligne, 1)).equals(ID)) {
					Partie partie = new Partie(date, joueurs.get(0), joueurs.get(1),reponseJoueurs.get(0),reponseJoueurs.get(1));
					listePartie.add(partie);
					joueurs.remove(0);
					joueurs.remove(0);
					reponseJoueurs.remove(0);
					reponseJoueurs.remove(0);
					ID = decoupe(ligne, 1);

				}
				String [] tmpjoueur= {decoupe(ligne, 3),decoupe(ligne, 4),decoupe(ligne, 5)};
				joueurs.add(tmpjoueur);
				String [] tmpRepjoueur= {decoupe(ligne, 6),decoupe(ligne, 7),decoupe(ligne, 8),
						decoupe(ligne, 9),decoupe(ligne, 10),decoupe(ligne, 11),
						decoupe(ligne,12),decoupe(ligne, 13)};
				reponseJoueurs.add(tmpRepjoueur);
				date = decoupe(ligne, 2);	
			}


		}
		// ajout de la derni�re partie
	
		Partie partie = new Partie(date, joueurs.get(0), joueurs.get(1),reponseJoueurs.get(0),reponseJoueurs.get(1));
		listePartie.add(partie);
		reader.close();
		return listePartie;
	}

	public static void main(String[] args) throws IOException {

		BaseScroreCSV BDD = new BaseScroreCSV();

		//Partie partie = BDD.getInfoLastGame();
		ArrayList<Partie> listePartie = new ArrayList<>();
		listePartie = BDD.getInfoAllGame();
		System.out.println(listePartie.size());
		for (Partie part : listePartie) {
			System.out.println("############");
			System.out.println("date ="+part.getDate());
			System.out.println("j1 ="+part.getJoueur1()[0]);
			System.out.println("j2 ="+part.getJoueur2()[0]);
		}

	}

}
