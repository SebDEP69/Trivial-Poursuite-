package Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

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
