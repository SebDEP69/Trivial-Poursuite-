package vue;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;



public class ChessGameGUI extends javax.swing.JFrame implements MouseListener,
		MouseMotionListener, Observer {

	private static final long serialVersionUID = 5131673871376781349L;

	//private ChessGameControlers chessGameControler;

	private JLayeredPane layeredPane;
	private JPanel chessBoard;
	private JPanel nord;
	private JPanel sud;
	private JPanel centre;
	private JPanel centre2;
	private JPanel[][] cases;
	//private Map<JLabel, PieceIHM> imagePiece;
	//private Map<JPanel, Coord> coordCase;
	private int xAdjustment;
	private int yAdjustment;

	//private Coord coordInit;
	//private Coord coordFinal;

	private List<JPanel> listePanelCoordFinales;

	JLabel chessPiece;
	
	public ChessGameGUI(String name, Dimension boardSize) {

		super(name);
		this.setSize(boardSize);

		// Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);


		// Add a chess board to the Layered Pane
		chessBoard = new JPanel();
		//chessBoard.setBackground(Color.blue);
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new BorderLayout());
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		chessBoard.setBorder(BorderFactory.createLineBorder(Color.black));
            
		nord = new JPanel();
		nord.setLayout(new GridLayout(1,3));
		
		sud = new JPanel();
		//sud.setLayout(new GridLayout(1,1));
		
		centre = new JPanel();
		centre.setLayout(new GridLayout(2,0));
		//centre.setBorder(BorderFactory.createLineBorder(Color.black));
		
	      JPanel texte1 = new JPanel();
	      centre.add((JPanel) texte1);
	      JLabel txt1 = new JLabel("Bienvenu sur le jeu Trivial Pursuit, pour lancer une partie cliquez sur : � D�but de la partie �");
	      txt1.setHorizontalTextPosition(JLabel.CENTER); 
	      txt1.setFont(new Font("Apple Chancery",Font.PLAIN,30));
	     // texte1.setBackground(Color.cyan);
	      texte1.add(txt1);
	      texte1.setVisible(true);
	         
		JPanel centre2 = new JPanel();
		centre2.setLayout(new GridLayout(0,3));
		centre.add(centre2);
		
		JPanel col1 = new JPanel();
		col1.setLayout(new GridLayout(3,0));
		centre2.add(col1);
		
		JPanel col2 = new JPanel();
		col2.setLayout(new GridLayout(2,0));
		centre2.add(col2);
		
		JPanel col3 = new JPanel();
		col3.setLayout(new GridLayout(1,0));
		centre2.add(col3);
		

		 JPanel square = new JPanel();
		 nord.add((JPanel) square);
	     //square.setBackground(Color.blue);
	     Icon joueur1 = new ImageIcon("images/j1.png");
	     JLabel player1 = new JLabel();
	     player1.setIcon(joueur1);
	     square.add(player1);
	     square.setVisible(true); 
	     
	     JPanel square1 = new JPanel();
	     nord.add((JPanel) square1);
         //square1.setBackground(Color.red);
         Icon titre = new ImageIcon("images/title.png");
         JLabel title = new JLabel();
         title.setIcon(titre);
         square1.add(title);
         square1.setVisible(true); 
	      
	        
         JPanel square2 = new JPanel();
         nord.add((JPanel) square2);
         //square2.setBackground(Color.orange);
         Icon joueur2 = new ImageIcon("images/j2.png");
         JLabel player2 = new JLabel();
         player2.setIcon(joueur2);
         square2.add(player2);
         square2.setVisible(true); 
         
         
         JPanel square3 = new JPanel();
         sud.add((JPanel) square3);
         JLabel text = new JLabel();
         //square3.setBackground(Color.gray);
         text.setText(text.getText()+"R�alis� par Depasse, De Paoli, Begni, Dumas");
         text.setFont(new Font("Apple Chancery",Font.ITALIC,15));
         square3.add(text);
         square3.setVisible(true);
         
         
         JPanel bouton1 = new JPanel();
         col1.add((JPanel) bouton1);
         JButton btn1 = new JButton("D�but de la partie");
         //bouton1.setBackground(Color.green);
         
         btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame;	
				Dimension dim;
			
				dim = new Dimension(800, 800);
				
				
				frame = new IHMPlateau("TrivialPursuite",  dim);
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocation(600, 10);
				frame.setSize(1000,1000);
				frame.pack();
				frame.setVisible(true);
				
			}
		});
         
         btn1.setPreferredSize(new Dimension(150, 100));
         btn1.setFont(new Font("Calibri",Font.PLAIN,15));
         bouton1.add(btn1);
         bouton1.setVisible(true);
         
         JPanel bouton2 = new JPanel();
         col1.add((JPanel) bouton2);
         JButton btn2 = new JButton("Rejouer une partie");
         //bouton2.setBackground(Color.pink);
         btn2.setPreferredSize(new Dimension(150, 100));
         btn2.setFont(new Font("Calibri",Font.PLAIN,15));
         bouton2.add(btn2);
         bouton2.setVisible(true);
         
         JPanel bouton3 = new JPanel();
         col1.add((JPanel) bouton3);
         JButton btn3 = new JButton("R�gles du jeu");
         //bouton3.setBackground(Color.yellow);
         btn3.setPreferredSize(new Dimension(150, 100));
         btn3.setFont(new Font("Calibri",Font.PLAIN,15));
         bouton3.add(btn3);
         bouton3.setVisible(true);
         
         JPanel bouton4 = new JPanel();
         col2.add((JPanel) bouton4);
         JButton btn4 = new JButton("Score de la partie");
         //bouton4.setBackground(Color.cyan);
         btn4.setPreferredSize(new Dimension(150, 100));
         btn4.setFont(new Font("Calibri",Font.PLAIN,15));
         bouton4.add(btn4);
         bouton4.setVisible(true);
         
         JPanel bouton5 = new JPanel();
         col2.add((JPanel) bouton5);
         JButton btn5 = new JButton("Personnalisation");
         //bouton5.setBackground(Color.MAGENTA);
         btn5.setPreferredSize(new Dimension(150, 100));
         btn5.setFont(new Font("Calibri",Font.PLAIN,15));
         bouton5.add(btn5);
         bouton5.setVisible(true);
         
         JPanel bouton6 = new JPanel();
         col3.add((JPanel) bouton6);
         JButton btn6 = new JButton("Historique des parties");
         //bouton6.setBackground(Color.LIGHT_GRAY);
         btn6.setPreferredSize(new Dimension(150, 200));
         btn6.setFont(new Font("Calibri",Font.PLAIN,15));
         bouton6.add(btn6);
         bouton6.setVisible(true);
	        
		this.getContentPane().add(centre, BorderLayout.CENTER);
		this.getContentPane().add(nord, BorderLayout.NORTH);
		this.getContentPane().add(sud, BorderLayout.SOUTH);
		this.getContentPane().add(new JPanel (), BorderLayout.WEST);
		this.getContentPane().add(new JPanel (), BorderLayout.EAST);
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
