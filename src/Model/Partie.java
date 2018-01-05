package Model;

public class Partie {

	private String date;	
	private String [] joueur1,joueur2,reponseJ1,reponseJ2;



	public Partie(String date, String[] joueur1, String[] joueur2, String[] repJ1, String[] repJ2 ) {
		this.date = date;
		/*
		 * joueur1 et joueur2:
		 * 0 : nom
		 * 1 : nb camembert 
		 * 2 : Gagnant OUI/NON 
		 * repJ1 et repJ2
		 * 0 : TotalBleu
		 * 1 : Repbleu
		 * 2 : total rouge
		 * 3 : rep rouge
		 * 4 : total vert
		 * 5 : rep vert
		 * 6 : total orange
		 * 7 : rep orange
		 */
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.reponseJ1 = repJ1;
		this.reponseJ2 = repJ2;
		
		
	}

	
	// reponse
	public String getReponseAll(Couleur couleur,int indiceJoueur) {

		String val= "";
		switch (couleur) {
		case BLEU:
			if (indiceJoueur==1) {
				val = this.reponseJ1[1];
			}else if (indiceJoueur==2) {
				val = this.reponseJ2[1];
			} 
			break;
		case ROUGE:
			
			if (indiceJoueur==1) {
				val = this.reponseJ1[3];
			}else if (indiceJoueur==2) {
				val = this.reponseJ2[3];
			} 
			break;
		case VERT:
			
			if (indiceJoueur==1) {
				val = this.reponseJ1[5];
			}else if (indiceJoueur==2) {
				val = this.reponseJ2[5];
			} 
			break;
		case ORANGE:
			
			if (indiceJoueur==1) {
				val = this.reponseJ1[7];
			}else if (indiceJoueur==2) {
				val = this.reponseJ2[7];
			} 
			break;
		default:
			break;
		}
		return val;
	}
	
	
	
	public String getReponseBleu(int indiceJoueur) {

		String val = "";
		if (indiceJoueur==1) {
			val = this.reponseJ1[1];
		}else if (indiceJoueur==2) {
			val = this.reponseJ2[1];
		} 

		return val;
	}

	public String getReponseRouge(int indiceJoueur) {

		String val = "";
		if (indiceJoueur==1) {
			val = this.reponseJ1[3];
		}else if (indiceJoueur==2) {
			val = this.reponseJ2[3];
		} 

		return val;
	}

	
	public String getReponseVert(int indiceJoueur) {

		String val = "";
		if (indiceJoueur==1) {
			val = this.reponseJ1[5];
		}else if (indiceJoueur==2) {
			val = this.reponseJ2[5];
		} 

		return val;
	}
	
	
	public String getReponseOrange(int indiceJoueur) {

		String val = "";
		if (indiceJoueur==1) {
			val = this.reponseJ1[7];
		}else if (indiceJoueur==2) {
			val = this.reponseJ2[7];
		} 

		return val;
	}
	
	// total
	public String getTotalAll(Couleur couleur,int indiceJoueur) {

		String val= "";
		switch (couleur) {
		case BLEU:
			if (indiceJoueur==1) {
				val = this.reponseJ1[0];
			}else if (indiceJoueur==2) {
				val = this.reponseJ2[0];
			} 
			break;
		case ROUGE:
			
			if (indiceJoueur==1) {
				val = this.reponseJ1[2];
			}else if (indiceJoueur==2) {
				val = this.reponseJ2[2];
			} 
			break;
		case VERT:
			
			if (indiceJoueur==1) {
				val = this.reponseJ1[4];
			}else if (indiceJoueur==2) {
				val = this.reponseJ2[4];
			} 
			break;
		case ORANGE:
			
			if (indiceJoueur==1) {
				val = this.reponseJ1[6];
			}else if (indiceJoueur==2) {
				val = this.reponseJ2[6];
			} 
			break;
		default:
			break;
		}
		return val;
	}
	
	
	
	public String getTotalBleu(int indiceJoueur) {

		//System.out.println("taille ="+reponseJ1.length);
		String val = "";
		if (indiceJoueur==1) {
			
			val = this.reponseJ1[0];
		}else if (indiceJoueur==2) {
			val = this.reponseJ2[0];
		} 
		return val;
	}
	
	public String getTotalRouge(int indiceJoueur) {

		String val = "";
		if (indiceJoueur==1) {
			val = this.reponseJ1[2];
		}else if (indiceJoueur==2) {
			val = this.reponseJ2[2];
		} 
		return val;
	}
	public String getTotalVert(int indiceJoueur) {

		String val = "";
		if (indiceJoueur==1) {
			val = this.reponseJ1[4];
		}else if (indiceJoueur==2) {
			val = this.reponseJ2[4];
		} 
		return val;
	}
	public String getTotalOrange(int indiceJoueur) {

		String val = "";
		if (indiceJoueur==1) {
			val = this.reponseJ1[6];
		}else if (indiceJoueur==2) {
			val = this.reponseJ2[6];
		} 
		return val;
	}


	public String getNomJ1() {
		return joueur1[0];
	}

	public String getNBcamJ1() {
		return joueur1[1];
	}

	public String getGagnantJ1() {
		return joueur1[2];
	}

	public Boolean isGagnant(int numJoueur){
		Boolean val=false ;
		if (numJoueur==1 && joueur1[2].equals("OUI")) {
			val = true;
		}else if (numJoueur==2 && joueur2[2].equals("OUI")) {
			val = true;
		}
		
		return val;
		
	}
	public String getNomJ2() {
		return joueur2[0];
	}

	public String getNBcamJ2() {
		return joueur2[1];
	}

	public String getGagnantJ2() {
		return joueur2[2];
	}

	public String getDate() {
		return date;
	}

	public String getNomJoueur(int indiceJoueur) {
		String nom= "";
		if (indiceJoueur==1) {
			nom = joueur1[0];
		}else if (indiceJoueur==2) {
			nom = joueur2[0];
		}
		
		return nom;
	}

	public String getNBCamJoueur(int indiceJoueur) {
		String nom= "";
		if (indiceJoueur==1) {
			nom = joueur1[1];
		}else if (indiceJoueur==2) {
			nom = joueur2[1];
		}
		
		return nom;
	}

	public String[] getJoueur1() {
		return joueur1;
	}


	public String[] getJoueur2() {
		return joueur2;
	}


	
}
