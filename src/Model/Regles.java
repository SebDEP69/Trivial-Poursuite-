package Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Regles extends JFrame {
	
	
	
	public Regles(Dimension dim){
		//regles_Label.setText("Au d�but du jeu, un �cran d'accueil appara�t. Vous aurez les possibilit�s suivantes : D�but de la partie, Rejouer une partie, R�gles du jeu, Score de la partie, Personnalisation et Historique des scores. Pour commencer, cliquez sur � D�but de la Partie �");
		JLayeredPane layeredPane = new JLayeredPane();
		JLabel regles_Label = new JLabel("blabla");
		JPanel regle = new JPanel(new BorderLayout());
		getContentPane().add(layeredPane);
		layeredPane.add(regle,layeredPane.DEFAULT_LAYER);
		layeredPane.setPreferredSize(dim);
		regle.add(regles_Label,BorderLayout.CENTER);
		
		
	}

}
