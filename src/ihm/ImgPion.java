package ihm;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

public class ImgPion extends JLabel {
    private static final long serialVersionUID = -9129537057351390955L;
    private int Xinit,Yinit;
    private Image img;
      
    public ImgPion(Image image, int x, int y) {
    	super();
    	this.img = 	 image;
    	Xinit= x;
    	Yinit = y;

       // setBounds(coordX, coordY, 55, 55);
    }
    
    
    
    public void paintComponent(Graphics g) {
        //Changer la taille ici
        g.drawImage(img, Xinit, Yinit, 50, 50, this);
    }
}