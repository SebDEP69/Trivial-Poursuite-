package Model;

public class Question extends Carte{
	
	private String question;
	private String reponse;
	
	//Constructeur
	public Question(String question, String reponse,TypeCarte typeCarte, Couleur couleur) {
		super(typeCarte, couleur);
		this.question = question;
		this.reponse = reponse;
	}

	//Methode classe Question
	public String getQuestion() {
		return question;
	}


	public String getReponse() {
		return reponse;
	}
	
	
	

}
