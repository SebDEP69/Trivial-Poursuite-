package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BaseScroreCSV {

	private String nomFichier;
	
	
	
	
	
	public BaseScroreCSV(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public void EnregistrementQuestion() throws IOException {

		FileReader ficher = new FileReader(this.nomFichier);

		BufferedReader reader = new BufferedReader (ficher);
		String ligne="";
		reader.readLine(); // ne lit pas la premiere ligne du fichier
		while ( (ligne = reader.readLine())!=null ) {

			
			

		}

		reader.close();

	}




	public static void main(String[] args) {

		
	

	}

}
