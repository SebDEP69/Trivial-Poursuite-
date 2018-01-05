package vue;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controler.ControleurAccueil;
import Model.ButtonJolie;


@SuppressWarnings("serial")
public class Accueil extends JFrame  {

	//private JLayeredPane layeredPane;
	static Image im;

	public Accueil(String name) {

		super(name);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Toolkit leKit = this.getToolkit();
		Dimension boardSize = leKit.getScreenSize();
		this.setTitle(name);
		setSize(boardSize.width , boardSize.height);
		
		try {
			im = ImageIO.read(new File("images/wallpaper.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JPanel panel = this.setBackgroundImage();
		setContentPane(panel);
		panel.setPreferredSize( boardSize );
				
		ButtonJolie btn1 = new ButtonJolie("Lancer le jeu");
		btn1.setFont(new Font("Calibri", Font.BOLD, 40));
		Color c = Color.decode("#c932ac");
		btn1.setBackground(c);
		btn1.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		panel.setBorder(BorderFactory.createEmptyBorder(75,0, 0,0));
		panel.add(btn1);

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControleurAccueil controleurAccueil = new ControleurAccueil();
				controleurAccueil.creerMenu();
				dispose();
			}
		});
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
				g.drawImage(im,0,0,getWidth(),getHeight(),this);
				setSize( getWidth(), getHeight());
				System.out.println("acceuille !"+this.getWidth()+" "+this.getHeight());
			}
		};	
		return panel;
	}
}
