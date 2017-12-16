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
				String ligne = IDgame+separateur+dateDuJour+separateur+nomJ+separateur+Nbcam+separateur+vainceur;
				
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
	}

	private  String decoupe(String ligne,int indice) {

		
		String[] temp = ligne.split(this.separateur);
		if (temp.length!=0) {
			return temp[indice-1];
		}else {
			return "";
		}


	}
	
	public ArrayList<String> getInfoLastGame() throws IOException{
		
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
		System.out.println("ligne"+tempLigne[0]);
		System.out.println("ligne"+tempLigne[1]);
		
		/*
		 * [0] : date
		 * [1] : joueur 1
		 * 			[0] : nom
		 * 			[1] : nb part
		 * 			[3] : 
		 * [2] : joueur 2
		 */
		
		reader.close();
		
		return null;
	}
	
	
	
	


	public static void main(String[] args) throws IOException {

		BaseScroreCSV BDD = new BaseScroreCSV();

		BDD.getInfoLastGame();


	}

}
