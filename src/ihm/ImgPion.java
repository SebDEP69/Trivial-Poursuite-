package ihm;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImgPion extends JLabel {
    private static final long serialVersionUID = -9129537057351390955L;
    private int coordX;
    private int coordY;
    private Image img;
      
    public ImgPion(String image, int x, int y) {
    	this.img = 	 Toolkit.getDefaultToolkit().getImage(image);
        coordX = x;
        coordY = y;
        setBounds(coordX, coordY, 55, 55);
    }
    public void paintComponent(Graphics g) {
        //tu peux changer la taille ici
        g.drawImage(img, coordX, coordY, 100, 100, this);
    }
}