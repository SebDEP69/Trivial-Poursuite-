package vue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePartie extends JFrame {

	
	public ScorePartie() {
		JPanel regle = new JPanel();
		JLabel parchemin_Regle = new JLabel();
		Icon parchemin = new ImageIcon("images/blague.png");
		parchemin_Regle.setIcon(parchemin);
		
		setContentPane(regle);
		regle.add(parchemin_Regle);	
	}

	public static void main(String[] args) {
		
	}

}
