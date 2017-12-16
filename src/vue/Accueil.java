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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import Model.ButtonJolie;


@SuppressWarnings("serial")
public class Accueil extends JFrame  {

	private JLayeredPane layeredPane;
	static Image im;

	public Accueil(String name) {

		super(name);

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
		panel.setBounds(0, 0, boardSize.width, boardSize.height);

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
				JFrame frame = new TrivialPursuitGUI("Trivial Pursuit");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
				dispose();
			}
		});
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				
			}
		};
		setContentPane(panel);
		return panel;
	}


}
