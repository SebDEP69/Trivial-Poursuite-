package Model;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Regles extends JFrame {
	
	
	
	public Regles(){
			
		JPanel regle = new JPanel();
		JLabel parchemin_Regle = new JLabel();
		Icon parchemin = new ImageIcon("images/Parchemin_Regle_Remplie.png");
		parchemin_Regle.setIcon(parchemin);
		
		setContentPane(regle);
		regle.add(parchemin_Regle);		
		
		
		
	}

}
