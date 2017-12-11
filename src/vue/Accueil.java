package vue;

import ihm.IHMPlateau;
import observable.TrivialPursuite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Controler.TrivialControler;
import Model.BackgroundPanel;
import Model.ButtonJolie;
import Model.Regles; 



@SuppressWarnings("serial")
public class Accueil extends javax.swing.JFrame implements MouseListener,
		MouseMotionListener, Observer {

	private JLayeredPane layeredPane;
	private Image img;
	private JPanel centre;
	static Image im;

	public Accueil(String name) {

		super(name);

		Toolkit leKit = this.getToolkit();
		Dimension boardSize = leKit.getScreenSize();
		this.setTitle(name);
		
		



		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		
		
		JFrame fram = new JFrame();
		try {
			JPanel panel = setBackgroundImage(fram, new File("images/wallpaper.png"));
			 im = ImageIO.read(new File("images/wallpaper.png")).getScaledInstance(boardSize.width, boardSize.height, Image.SCALE_DEFAULT);
			
			panel.setPreferredSize( boardSize );
			panel.setBounds(0, 0, boardSize.width, boardSize.height);

		
	        ButtonJolie btn1 = new ButtonJolie("Lancer le jeu");
	        panel.add(btn1);
	        
	        btn1.addActionListener(new ActionListener() {
	 			
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 				JFrame frame = new TrivialPursuitGUI("Trivial Pursuit");
	 				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 				frame.pack();
	 				frame.setVisible(true);
	 			}
	 		});
					
			fram.pack();
			fram.setVisible(true);
			fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         
      
      
	}


	
	public static JPanel setBackgroundImage(JFrame frame, final File img) throws IOException
	{
		JPanel panel = new JPanel()
		{
			private static final long serialVersionUID = 1;
			
			private BufferedImage buf = ImageIO.read(img);
			
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(im,0,0,this);
			}
		};
		
		frame.setContentPane(panel);
		
		return panel;
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
