package vue;



import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Regles extends JFrame {
		
	public Regles(){
		
		JPanel regle = new JPanel(new FlowLayout());
		JLabel parchemin_Regle = new JLabel();
		ImageIcon parchemin = new ImageIcon("images/Parchemin_Regle_Remplie.png");
		parchemin_Regle.setIcon(parchemin);
		
		//regle.setBackground(Color.red);
		parchemin_Regle.setBounds(0, 0, getWidth(), getHeight());
		regle.add(parchemin_Regle);		
		this.add(regle);
		
	}

	
}
