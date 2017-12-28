package vue;


import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Regles extends JFrame {
	
	Image img;
	
	public Regles(){
			
		JPanel regle = this.setBackgroundImage();

		try {
			img = ImageIO.read(new File("images/Parchemin_Regle_Remplie.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.setContentPane(regle);
	}

	private  JPanel setBackgroundImage()
	{
		JPanel panel = new JPanel()
		{
			private static final long serialVersionUID = 1;

			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(img,0,0,getWidth(),getHeight(),this);
				
			}
		};
		
		return panel;
	}

	
}
