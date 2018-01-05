package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BaseQuestionCSV {


	private String nomFichier;
	private String separateur;
	private ArrayList<Carte> questionROUGE;
	private ArrayList<Carte> questionBLEU;
	private ArrayList<Carte> questionVERT;
	private ArrayList<Carte> questionORANGE;


	public BaseQuestionCSV() {

		this.nomFichier = "BDDQuestion.csv";
		this.separateur=";";
		this.questionROUGE = new ArrayList<Carte>();
		this.questionBLEU = new ArrayList<Carte>();
		this.questionVERT = new ArrayList<Carte>();
		this.questionORANGE = new ArrayList<Carte>();


	}


	public void EnregistrementQuestion() throws IOException {

		FileReader ficher = new FileReader(this.nomFichier);

		BufferedReader reader = new BufferedReader (ficher);
		String ligne="";
		reader.readLine(); // ne lit pas la premiere ligne du fichier
		while ( (ligne = reader.readLine())!=null ) {

			Question question = CreerQuestion(ligne,1);
			if (question !=null) {
				Couleur couleur = question.getCouleur();
				switch (couleur) {
				case ROUGE:
					this.questionROUGE.add(question);
					//System.out.println("ajoute de "+question.getCouleur()+ " "+question.getDescription());
					break;
				case BLEU:
					this.questionBLEU.add(question);
					//System.out.println("ajoute de "+question.getCouleur()+ " "+question.getDescription());
					break;
				case ORANGE:
					this.questionORANGE.add(question);
					//System.out.println("ajoute de "+question.getCouleur()+ " "+question.getDescription());
					break;
				case VERT:
					this.questionVERT.add(question);
					//System.out.println("ajoute de "+question.getCouleur()+ " "+question.getDescription());
					break;
				default:
					break;
				}	
			}

		}

		reader.close();

	}

	private Question CreerQuestion (String ligne, int indice ) {

		String[] temp = ligne.split(this.separateur);
		Question question = null;

		if (temp.length >=7) {
			String rep[] = {temp[2],temp[3],temp[4],temp[5]};
			Couleur couleur = null;
			switch (temp[0]) {
			case "ROUGE":
				couleur = Couleur.ROUGE;
				break;
			case "BLEU":
				couleur = Couleur.BLEU;
				break;
			case "ORANGE":
				couleur = Couleur.ORANGE;
				break;
			case "VERT":
				couleur = Couleur.VERT;

				break;
			default:
				break;
			}
			int indicerep =0;
			try {
				indicerep = Integer.parseInt(temp[6]);
			}catch (Exception e) {
				System.out.println("erreur: \""+ temp[6] + "\" n est pas un nombre");
			}
			if (couleur !=null &&  indicerep!=0 ) {
				if (temp.length >=8) {
					question = new Question(temp[1],indicerep,rep, couleur,temp[7]);
				}else if (temp.length >=7) {
					question = new Question(temp[1],indicerep,rep, couleur,"");
				} 				
			}
		}
		return question;
	}


	
	public void ajouterQuestion(Couleur typeQuestion,String question,String[] reponses,String numReponseJuste,String descritption) throws IOException {
		
		FileWriter ficher = new FileWriter(this.nomFichier,true);
		BufferedWriter writer = new BufferedWriter(ficher);
		
		
		String ligne = typeQuestion+separateur+question+separateur+reponses[0]+separateur+reponses[1]+
				separateur+reponses[2]+separateur+reponses[3]+separateur+numReponseJuste+separateur+descritption;
		writer.newLine();
		writer.write(ligne);
		
		writer.close();
	}
	
	
	
	
	
	
	
	
	public ArrayList<Carte> getQuestionROUGE() {
		return questionROUGE;
	}


	public ArrayList<Carte> getQuestionBLEU() {
		return questionBLEU;
	}


	public ArrayList<Carte> getQuestionVERT() {
		return questionVERT;
	}


	public ArrayList<Carte> getQuestionORANGE() {
		return questionORANGE;
	}



	public static void main(String[] args) throws IOException {

		BaseQuestionCSV BDD = new BaseQuestionCSV();
		BDD.EnregistrementQuestion();
	}



}
