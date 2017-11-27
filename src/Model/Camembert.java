package Model;
import java.util.ArrayList;

public class Camembert {

	private ArrayList<PartCamembert> listePart = new ArrayList<>();
	
	//Constructeur
	public Camembert(){
		
		this.listePart = new ArrayList<PartCamembert>();
	}
	
		
	
	//Methode classe Camembert
	public boolean Vide(){
		if(listePart.size() == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean Plein(){
		if(listePart.size() == 4){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public boolean ContientPart(Couleur couleur){
		boolean valeurDeRetour = false;
		int i =0;
		while(!valeurDeRetour && i<listePart.size()) {
			if(listePart.get(i).getCouleur() == couleur){
				valeurDeRetour = true;
			}
			else{
				valeurDeRetour = false;
			}
			i++;
		}
		return valeurDeRetour;
	}
	
	public boolean AjoutPartCamembert(Couleur couleur){
		boolean ok=false;
		if(Plein() || ContientPart(couleur)){
			System.out.println("Erreur dans l'ajout d'une part");
			ok=false;
		}
		else{
			System.out.println("ajout de la part de camembert " + couleur);
			listePart.add(new PartCamembert(couleur));
			ok=true;
		}
		return ok;
	}
	
	
	
	public boolean RetirerPartCamemebert() {
		
		boolean valeurDeRetour= false;
		if (!this.Vide()) {
			listePart.remove(0);
			System.out.println("part de camembert retirer");
			valeurDeRetour = true;
		} else {
			valeurDeRetour = false;
		}
		return valeurDeRetour;
		
	}



	@Override
	public String toString() {
		
		String val ="";
		for (PartCamembert partCamembert : listePart) {
			val = val + partCamembert.toString()+"\n";
		}
		return val;
	}

	
	
	
}
