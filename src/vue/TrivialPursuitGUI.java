package vue;

import ihm.IHMPlateau;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.UIManager; 



public class TrivialPursuitGUI extends javax.swing.JFrame implements MouseListener,
		MouseMotionListener, Observer {

	private JLayeredPane layeredPane;
	private JPanel nord;
	private JPanel sud;
	private JPanel centre;
	
	public TrivialPursuitGUI(String name, Dimension boardSize) {

		super(name);
		this.setSize(boardSize);

		// Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		
		/*// Add a chess board to the Layered Pane
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new BorderLayout());
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		chessBoard.setBorder(BorderFactory.createLineBorder(Color.black));
		chessBoard.setBackground(Color.blue);*/
	
	
		nord = new JPanel();
		nord.setLayout(new GridLayout(1,3));

		sud = new JPanel();
		sud.setPreferredSize(new Dimension(100,60));
		
		centre = new JPanel();
		centre.setLayout(new GridBagLayout());
		
		// CENTRE HAUT // 
	     JPanel texte1 = new JPanel();
	     texte1.setPreferredSize(new Dimension(10,90));
	     centre.add((JPanel) texte1);
	     JLabel txt1 = new JLabel("Bienvenue sur le jeu Trivial Pursuit, pour lancer une partie cliquez sur : Debut de la partie");
	     txt1.setHorizontalTextPosition(JLabel.CENTER); 
	     txt1.setFont(new Font("Freestyle Script",Font.PLAIN,50));
	   // texte1.setBackground(Color.cyan);
	     texte1.add(txt1);
	     texte1.setVisible(true);
	     GridBagConstraints a = new GridBagConstraints();    
		 a.gridx = 0;                                        
		 a.gridy = 0;    
		 a.gridwidth = 5;
		 a.fill = GridBagConstraints.HORIZONTAL;
		 a.insets = new Insets(-100,-150,50,-150);
		 centre.add(texte1, a);
	     
	
		
		// NORD GAUCHE
		 JPanel square = new JPanel();
		 nord.add((JPanel) square);
	     //square.setBackground(Color.blue);
	     Icon joueur1 = new ImageIcon("images/j1.png");
	     JLabel player1 = new JLabel();
	     player1.setIcon(joueur1);
	     square.add(player1);
	     square.setVisible(true);
	     
	     // NORD MILIEU
	     JPanel square1 = new JPanel();
	     nord.add((JPanel) square1);
        // square1.setBackground(Color.red);
         Icon titre = new ImageIcon("images/title.png");
         JLabel title = new JLabel();
         title.setIcon(titre);
         square1.add(title);
         square1.setVisible(true); 
	      
	     // NORD DROIT
         JPanel square2 = new JPanel();
         nord.add((JPanel) square2);
         //square2.setBackground(Color.orange);
         Icon joueur2 = new ImageIcon("images/j2.png");
         JLabel player2 = new JLabel();
         player2.setIcon(joueur2);
         square2.add(player2);
         square2.setVisible(true); 
         
         
         //SUD
         JPanel square3 = new JPanel();
         sud.add((JPanel) square3);
         JLabel text = new JLabel();
         //square3.setBackground(Color.gray);
         text.setText(text.getText()+"Realise par Depasse, De Paoli, Begni, Dumas");
         text.setFont(new Font("Apple Chancery",Font.ITALIC,15));
         square3.add(text);
         square3.setVisible(true);
         
         //CENTRE2
         JPanel bouton1 = new JPanel();
         centre.add((JPanel) bouton1);
         JButton btn1 = new JButton("<Html><center>Debut de la partie<Html>");
         btn1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
         btn1.setFocusable(false);
         btn1.setFont(new Font("Calibri", Font.BOLD, 20));
         //bouton1.setBackground(Color.green);
         btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame;	
				Dimension dim;
			
				dim = new Dimension(800, 800);
				
				
				frame = new IHMPlateau("TrivialPursuite",  dim);
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
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
         JButton btn2 = new JButton("<Html><center>Rejouer une partie<Html>");
         //bouton2.setBackground(Color.pink);
         btn2.setPreferredSize(new Dimension(180, 100));
         btn2.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
         btn2.setFocusable(false);
         btn2.setFont(new Font("Calibri", Font.BOLD, 20));
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
         JButton btn3 = new JButton("<Html><center>Regles du jeu<Html>");
         //bouton3.setBackground(Color.yellow);
         btn3.setPreferredSize(new Dimension(180, 100));
         btn3.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
         btn3.setFocusable(false);
         btn3.setFont(new Font("Calibri", Font.BOLD, 20));
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
         JButton btn4 = new JButton("<Html><center>Score de la partie<Html>");
         //bouton4.setBackground(Color.cyan);
         btn4.setPreferredSize(new Dimension(180, 100));
         btn4.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
         btn4.setFocusable(false);
         btn4.setFont(new Font("Calibri", Font.BOLD, 20));
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
         JButton btn5 = new JButton("<Html><center>Personnalisation<Html>");
         //bouton5.setBackground(Color.MAGENTA);
         btn5.setPreferredSize(new Dimension(180, 100));
         btn5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
         btn5.setFocusable(false);
         btn5.setFont(new Font("Calibri", Font.BOLD, 20));
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
         JButton btn6 = new JButton("<Html><center>Historique des parties<Html>");
         btn6.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
         btn6.setFocusable(false);
         //bouton6.setBackground(Color.LIGHT_GRAY);
         btn6.setPreferredSize(new Dimension(200, 250));
         btn6.setFont(new Font("Calibri", Font.BOLD, 20));
         bouton6.add(btn6);
         bouton6.setVisible(true);
         GridBagConstraints i = new GridBagConstraints();   
		 i.gridx = 3;                                       
		 i.gridy = 2;          
		 i.insets = new Insets(-20,100,-30,100);
		 i.fill = GridBagConstraints.HORIZONTAL;
		 centre.add(btn6, i);
         
	        
		this.getContentPane().add(centre, BorderLayout.CENTER);
		this.getContentPane().add(nord, BorderLayout.NORTH);
		this.getContentPane().add(sud, BorderLayout.SOUTH);
		//this.getContentPane().add(new JButton (), BorderLayout.WEST);
		//this.getContentPane().add(new JButton (), BorderLayout.EAST);
	    this.setVisible(true);
        
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
