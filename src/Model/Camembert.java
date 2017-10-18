package Model;
import java.util.ArrayList;

public class Camembert {

	private ArrayList<PartCamembert> listePart = new ArrayList<>();
	
	//Constructeur
	public Camembert(){
		
		this.listePart = new ArrayList<PartCamembert>();
	}
	
		
	
	//M�thode classe Camembert
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
			if(listePart.get(i).getCouleur().equals(couleur)){
				valeurDeRetour = true;
			}
			else{
				valeurDeRetour = false;
			}
			i++;
		}
		return valeurDeRetour;
	}
	
	public void AjoutPartCamembert(Couleur couleur){
		if(Plein() || ContientPart(couleur)){
			System.out.println("Erreur");
		}
		else{
			System.out.println("ajout de la part de camembert " + couleur);
			listePart.add(new PartCamembert(couleur));
		}
		
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

	
	
	
}
