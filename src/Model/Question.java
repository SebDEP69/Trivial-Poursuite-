package Model;

public class Question extends Carte{
	
	private String question;
	private int indiceReponse;
	private String[] choix;
	private String theme;
	private String description;
	
	//Constructeur
	public Question(String question, int reponse ,String[] choix, Couleur couleur,String description) {
		super(TypeCarte.Question, couleur);
		this.question = question;
		this.choix = choix;
		this.indiceReponse = reponse;
		this.description = description;
		switch (couleur) {
		case ROUGE:
			theme = "Innovation";
			break;
		case BLEU:
			theme = "Le Saviez Vous?";
			break;
		case VERT:
			theme = "CPE";
			break;
		case ORANGE:
			theme = "Blague";
			break;
		default:
			break;
		}
		
		
	}

	//Methode classe Question
	public String getQuestion() {
		return question;
	}

	public String[] getChoix() {
		return choix;
	}

	public String getReponse() {
		return this.choix[indiceReponse-1];
	}

	public boolean isBonneReponse(String reponse){
		
		return (this.choix[this.indiceReponse-1] == reponse);
	}

	public String getTheme() {
		return theme;
	}

	public String getDescription() {
		return description;
	}
	
	
	

}
