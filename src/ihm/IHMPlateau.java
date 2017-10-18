package ihm;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import javax.swing.*;

import Fonctionnel.Case;
import Fonctionnel.Couleur;
 

public class IHMPlateau extends JFrame implements MouseListener, MouseMotionListener, Observer {
  JLayeredPane layeredPane;
  JPanel trivialBoard, startDragCase;
  JLabel chessPiece;
  int xAdjustment;
  int yAdjustment;
  /** colonne (métier) */
  int xInit;
  /** ligne (métier) */
  int yInit;
 
  public IHMPlateau( String nom_jeu, Dimension dim){
	  Dimension boardSize = dim; 
	  
	  
	  this.setSize(dim);
	  this.setTitle(nom_jeu);
	  //  Use a Layered Pane for this this application
	  layeredPane = new JLayeredPane();
	  getContentPane().add(layeredPane);
	  layeredPane.setPreferredSize(boardSize);
	  layeredPane.addMouseListener(this);
	  layeredPane.addMouseMotionListener(this);
	  
	  
	  JPanel panelGeneral = new JPanel();
	 // panelGeneral.getPreferredSize();
	  panelGeneral.setLayout( new BorderLayout() );
	  panelGeneral.setPreferredSize( boardSize );
	  panelGeneral.setBounds(0, 0, boardSize.width, boardSize.height);
	  
	 
	  
	  layeredPane.add(panelGeneral, JLayeredPane.DEFAULT_LAYER);
	  
	  
	  
	  
	
	  
	  
	  JLabel nord = new JLabel(new ImageIcon("images/TitreTrivial.jpg"));
	  nord.setBackground(Color.white);
	  panelGeneral.add(nord, BorderLayout.NORTH);
	  
	  JPanel sud = new JPanel();
	 // sud.setBackground(Color.RED);
	  panelGeneral.add(sud, BorderLayout.SOUTH);
	   
	  JPanel WEST = new JPanel();
	 // WEST.setBackground(Color.PINK);
	  panelGeneral.add(WEST, BorderLayout.WEST);
	   
	  JPanel east = new JPanel();
	  east.setLayout(new GridLayout(2,0));
	  east.setBackground(Color.white);
	  
	  
	  JLabel camemebertJoueurUn = new JLabel(new ImageIcon("images/Cam_joueur1.png"));
	  JLabel camemebertJoueurdeux = new JLabel(new ImageIcon("images/Cam_joueur2.png"));
	  	  
	  east.add(camemebertJoueurUn);
	  east.add(camemebertJoueurdeux);
	  
	
	  
	  panelGeneral.add(east, BorderLayout.EAST);
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  JPanel panelPlateau = new JPanel(){
		  
		private static final long serialVersionUID = 1L;
		private Image image = 	 Toolkit.getDefaultToolkit().getImage("images/logo.jpg");
		public Dimension getPreferredSize() { 
			return new Dimension(image.getWidth(null), image.getHeight(null));}
		
		@Override
		  public void paintComponent(Graphics g)
		   {
		     super.paintComponent(g);
		     
		     g.drawImage (image, 65, 55, null);
		     repaint();
		   }
		  
	  };
	  
	  panelPlateau.setLayout( new GridLayout(8, 8) );
	  //panelPlateau.setBackground(new ImageIcon("image/logo.jpg"));
	  panelGeneral.add(panelPlateau, BorderLayout.CENTER);
	  
	  creationplateau(panelPlateau);
	  
	  
	  /*layeredPane.addMouseListener(this);
	  layeredPane.addMouseMotionListener(this);*/
	
	  //Add a chess board to the Layered Pane 
	  
	 /* trivialBoard = new JPanel();
	  layeredPane.add(trivialBoard, JLayeredPane.DEFAULT_LAYER);
	  trivialBoard.setLayout( new GridLayout(8, 8) );
	  trivialBoard.setPreferredSize( boardSize );
	  trivialBoard.setBounds(0, 0, boardSize.width, boardSize.height);
	  
	 */
	 
	  
	  
  }
  private void creationplateau(JPanel panelPlateau) {
	  
	  
	  for (int i = 0; i < 49; i++) {
		  JPanel square = new JPanel( new BorderLayout() );
		  panelPlateau.add( square );
		 
		  int row = (i / 7) % 2;
		  
		  square.setOpaque(false);
		  square.setBackground(Color.WHITE);
		 /* if (row == 0)
			  
			  square.setBackground( i % 2 == 0 ? Color.black : Color.white );
		  else
			  square.setBackground( i % 2 == 0 ? Color.white : Color.black );*/
	  }  
	  
	
	  ((JPanel) panelPlateau.getComponent(18)).add(new JLabel(new ImageIcon("image/logo.jpg")));
	  ((JPanel) panelPlateau.getComponent(18)).setSize(100,10);
	  
	  
	  Couleur serie[] = {Couleur.VERT, Couleur.ORANGE,Couleur.BLEU, Couleur.ROUGE,Couleur.NOIR};
	  int indexSerie=0;
	  int indicesuperCam=1;
	  int numeroCote =1;
	  int indiceelement=1;
	  for (int numCase = 0; numCase < 24; numCase++) {
		  
		((JPanel)  panelPlateau.getComponent(indiceelement)).setOpaque(true);
		  switch (serie[indexSerie]) {
	      case ROUGE : panelPlateau.getComponent(indiceelement).setBackground(Color.RED);
	               break;
	      case BLEU:   panelPlateau.getComponent(indiceelement).setBackground(Color.BLUE);
	               break;
	      case VERT: panelPlateau.getComponent(indiceelement).setBackground(Color.GREEN);
	               break;
	      case ORANGE:  panelPlateau.getComponent(indiceelement).setBackground(Color.ORANGE);
	               break;
	      case NOIR:  panelPlateau.getComponent(indiceelement).setBackground(Color.BLACK);
	               break;
		  }	
		  
		  
		if (indicesuperCam %6 ==0) {
			  ( (JPanel)panelPlateau.getComponent(indiceelement)).setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
			  
			  numeroCote++;
		} 
		 
		
		  if (indexSerie==4) {indexSerie=0;}
		  else {indexSerie++;}
		  
		  switch (numeroCote) {
	      case 1 : indiceelement++;
	               break;
	      case 2:  indiceelement= indiceelement+7;
	               break;
	      case 3: indiceelement--;
	               break;
	      case 4:  indiceelement= indiceelement-7;
	               break;
		  }	
		  
		  indicesuperCam++;
	  }
	  
	  
  }
  
  public static void main(String[] args) {


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
  
  
    
  public void mouseClicked(MouseEvent e) {
  
  }
  public void mouseMoved(MouseEvent e) {
 }
  public void mouseEntered(MouseEvent e){
  
  }
  public void mouseExited(MouseEvent e) {
  
  }



@Override
public void update(Observable arg0, Object arg1) {
	// TODO Auto-generated method stub
	
}



@Override
public void mouseDragged(MouseEvent arg0) {
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
}