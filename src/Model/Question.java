package Model;

public class Question extends Carte{
	
	private String question;
	private int reponse;
	private String[] choix;
	
	//Constructeur
	public Question(String question, int reponse ,String[] choix, Couleur couleur) {
		super(TypeCarte.Question, couleur);
		this.question = question;
		this.choix = choix;
		this.reponse = reponse;
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
		
		return (this.choix[this.reponse] == reponse);
	}
	
	
	

}
