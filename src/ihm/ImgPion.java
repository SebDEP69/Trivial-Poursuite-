package ihm;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;

public class ImgPion extends JLabel {
    private static final long serialVersionUID = -9129537057351390955L;
    private int Xinit,Yinit,Xdest,Ydest;
    private Image img;
      
    public ImgPion(Image image, int x, int y) {
    	super();
    	this.img = 	 image;
    	Xinit= x;
    	Yinit = y;
    	Xdest =0;
    	Ydest =0;
       // setBounds(coordX, coordY, 55, 55);
    }
    
    
    
    public void paintComponent(Graphics g) {
        //Changer la taille ici
        g.drawImage(img, Xinit, Yinit, 50, 50, this);
    }
}