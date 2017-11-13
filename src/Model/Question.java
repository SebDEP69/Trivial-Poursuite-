package Model;

public class Question extends Carte{
	
	private String question;
	private int reponse;
	private String[] choix;
	private String theme;
	
	//Constructeur
	public Question(String question, int reponse ,String[] choix, Couleur couleur) {
		super(TypeCarte.Question, couleur);
		this.question = question;
		this.choix = choix;
		this.reponse = reponse;
		
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

	public int getReponse() {
		return reponse;
	}

	public boolean isBonneReponse(String reponse){
		
		return (this.choix[this.reponse-1] == reponse);
	}

	public String getTheme() {
		return theme;
	}
	
	
	

}
