package vue;

import ihm.IHMPlateau;
import observable.TrivialPursuite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

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



@SuppressWarnings("serial")
public class TrivialPursuitGUI extends javax.swing.JFrame implements MouseListener,
		MouseMotionListener, Observer {

	private JLayeredPane layeredPane;
	private JPanel nord;
	private JPanel sud;
	private JPanel centre;
	private JPanel est;
	private JPanel ouest;
	
	public TrivialPursuitGUI(String name) {

		super(name);

		Toolkit leKit = this.getToolkit();
		Dimension boardSize = leKit.getScreenSize();
		this.setTitle(name);
		//  Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);



		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout( new BorderLayout() );
		panelGeneral.setPreferredSize( boardSize );
		panelGeneral.setBounds(0, 0, boardSize.width, boardSize.height);
		layeredPane.add(panelGeneral, JLayeredPane.DEFAULT_LAYER);
			  
			  
		est = new JPanel();
		panelGeneral.add(est, BorderLayout.EAST);
		//est.setBackground(Color.ORANGE);
				
		ouest = new JPanel();
		panelGeneral.add(ouest, BorderLayout.WEST);
		//ouest.setBackground(Color.BLUE);
		
		nord = new JPanel();
		panelGeneral.add(nord, BorderLayout.NORTH);

		sud = new JPanel();
		panelGeneral.add(sud, BorderLayout.SOUTH);
		
		centre = new JPanel();
		centre.setLayout(new GridBagLayout());
		panelGeneral.add(centre, BorderLayout.CENTER);
		
		// CENTRE HAUT // 
	     JPanel texte1 = new JPanel();
	     texte1.setPreferredSize(new Dimension(10,90));
	     centre.add((JPanel) texte1);
	     JLabel txt1 = new JLabel("Bienvenue sur le jeu Trivial Pursuit, pour lancer une partie cliquez sur : Debut de la partie");
	     txt1.setHorizontalTextPosition(JLabel.CENTER); 
	     txt1.setFont(new Font("Calibri",Font.PLAIN,25));
	     texte1.add(txt1);
	     texte1.setVisible(true);
	     GridBagConstraints a = new GridBagConstraints();    
		 a.gridx = 0;                                        
		 a.gridy = 0;    
		 a.gridwidth = 5;
		 a.fill = GridBagConstraints.HORIZONTAL;
		 a.insets = new Insets(-100,-150,50,-150);
         texte1.add(txt1);
		 centre.add(texte1, a);
		 
	     
	
		
		
	     // NORD MILIEU
	     JPanel square1 = new JPanel();
	     nord.add((JPanel) square1);
         Icon titre = new ImageIcon("images/title2.png");
         JLabel title = new JLabel();
         title.setIcon(titre);
         square1.add(title);
         square1.setVisible(true); 
	   
         
         
         //SUD
         JPanel square3 = new JPanel();
         sud.add((JPanel) square3);
         JLabel text = new JLabel();
         text.setText(text.getText()+"Realise par Depasse, De Paoli, Begni, Dumas");
         text.setFont(new Font("Apple Chancery",Font.ITALIC,15));
         square3.add(text);
         square3.setVisible(true);
         
         //CENTRE2
         JPanel bouton1 = new JPanel();
         centre.add((JPanel) bouton1);
         ButtonJolie btn1 = new ButtonJolie("<Html><center>Debut de la partie<Html>");
         //JButton btn1 = new JButton("<Html><center>Debut de la partie<Html>");
      
         btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TrivialPursuite trivialPursuite = new TrivialPursuite();
				TrivialControler trivialControler = new TrivialControler(trivialPursuite);
				JFrame frame = new IHMPlateau("Trivial Pursuit",trivialControler);
				trivialPursuite.addObserver((Observer) frame);
				setVisible(false);
				dispose();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
         
         btn1.setPreferredSize(new Dimension(180, 100));
         bouton1.add(btn1);
         bouton1.setVisible(true);
         
         GridBagConstraints d = new GridBagConstraints();    
		 d.gridx = 0;                                        
		 d.gridy = 1;                    
		 d.fill = GridBagConstraints.HORIZONTAL;
		 d.insets = new Insets(-20,100,-30,100);
		 centre.add(btn1, d);
         
         
         
         JPanel bouton2 = new JPanel();
         centre.add((JPanel) bouton2);
         ButtonJolie btn2 = new ButtonJolie("<Html><center>Rejouer une partie<Html>");
         btn2.setPreferredSize(new Dimension(180, 100));
         bouton2.add(btn2);
         bouton2.setVisible(true);
         GridBagConstraints e = new GridBagConstraints();    
		 e.gridx = 0;                                        
		 e.gridy = 2;     
		 e.insets = new Insets(-20,100,-30,100);
		 e.fill = GridBagConstraints.HORIZONTAL;
		 centre.add(btn2, e);
         
         
         
         JPanel bouton3 = new JPanel();
         centre.add((JPanel) bouton3);
         ButtonJolie btn3 = new ButtonJolie("<Html><center>Regles du jeu<Html>");
         btn3.setPreferredSize(new Dimension(180, 100));
         bouton3.add(btn3);
         bouton3.setVisible(true);
         GridBagConstraints f = new GridBagConstraints();   
		 f.gridx = 0;                                       
		 f.gridy = 3;         
		 f.insets = new Insets(-20,100,-30,100);
		 f.fill = GridBagConstraints.HORIZONTAL;
		 centre.add(btn3, f);
         
         
         JPanel bouton4 = new JPanel();
         centre.add((JPanel) bouton4);
         ButtonJolie btn4 = new ButtonJolie("<Html><center>Score de la partie<Html>");
         btn4.setPreferredSize(new Dimension(180, 100));
         bouton4.add(btn4);
         bouton4.setVisible(true);
         GridBagConstraints g = new GridBagConstraints();   
		 g.gridx = 2;                                       
		 g.gridy = 1;      
		 g.insets = new Insets(-20,100,-30,100);
		 g.fill = GridBagConstraints.HORIZONTAL;
		 centre.add(btn4, g);
         
         
         JPanel bouton5 = new JPanel();
         centre.add((JPanel) bouton5);
         ButtonJolie btn5 = new ButtonJolie("<Html><center>Personnalisation<Html>");
         btn5.setPreferredSize(new Dimension(180, 100));
         bouton5.add(btn5);
         bouton5.setVisible(true);
         GridBagConstraints h = new GridBagConstraints();   
		 h.gridx = 2;                                       
		 h.gridy = 3;
		 h.insets = new Insets(-20,100,-30,100);     
		 h.fill = GridBagConstraints.HORIZONTAL;
		 centre.add(btn5, h);
         
         JPanel bouton6 = new JPanel();
         centre.add((JPanel) bouton6);
         ButtonJolie btn6 = new ButtonJolie("<Html><center>Historique des parties<Html>");
         btn6.setPreferredSize(new Dimension(200, 250));
         bouton6.add(btn6);
         bouton6.setVisible(true);
         GridBagConstraints i = new GridBagConstraints();   
		 i.gridx = 3;                                       
		 i.gridy = 2;          
		 i.insets = new Insets(-20,100,-30,100);
		 i.fill = GridBagConstraints.HORIZONTAL;
		 centre.add(btn6, i);
         
	        
	
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
