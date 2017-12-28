package vue;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Controler.ControleurAccueil;
import Model.ButtonJolie;


@SuppressWarnings("serial")
public class Accueil extends JFrame  {

	private JLayeredPane layeredPane;
	static Image im;

	public Accueil(String name) {

		super(name);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Toolkit leKit = this.getToolkit();
		Dimension boardSize = leKit.getScreenSize();
		this.setTitle(name);

		
		
		
		

		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);

		try {
			im = ImageIO.read(new File("images/wallpaper.png")).getScaledInstance(boardSize.width, boardSize.height, Image.SCALE_DEFAULT);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		JPanel panel = this.setBackgroundImage();
		
		panel.setPreferredSize( boardSize );
		
		System.out.println("beforeeeee : "+this.getWidth()+" "+this.getHeight());
		panel.setBounds(0, 0, boardSize.width, boardSize.height);
		System.out.println("test");
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
		//get local graphics environment
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();

		
		System.out.println(graphicsEnvironment.getMaximumWindowBounds());
	}

	private  JPanel setBackgroundImage()
	{
		JPanel panel = new JPanel()
		{
			
			private static final long serialVersionUID = 1;
			@Override
			protected void paintComponent(Graphics g)
			{
				System.out.println("teste");
				super.paintComponent(g);
				
				g.drawImage(im,0,0,getWidth(),getHeight(),this);
				System.out.println("after !"+this.getWidth()+" "+this.getHeight());
				
				
			}
		};
		//System.out.println("before : "+getWidth()+" "+getHeight());
		setContentPane(panel);
		return panel;
	}


}
