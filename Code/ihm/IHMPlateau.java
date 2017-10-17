package ihm;

import java.awt.*;
import java.awt.event.*;
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
	
	  //Add a chess board to the Layered Pane 
	  
	  trivialBoard = new JPanel();
	  layeredPane.add(trivialBoard, JLayeredPane.DEFAULT_LAYER);
	  trivialBoard.setLayout( new GridLayout(8, 8) );
	  trivialBoard.setPreferredSize( boardSize );
	  trivialBoard.setBounds(0, 0, boardSize.width, boardSize.height);
	  
	 
	 
	 
	  
  }
  private void creationplateau() {
	  
	  
	  for (int i = 0; i < 64; i++) {
		  JPanel square = new JPanel( new BorderLayout() );
		  trivialBoard.add( square );
		 
		  int row = (i / 8) % 2;
		  if (row == 0)
			  
			  square.setBackground( i % 2 == 0 ? Color.black : Color.white );
		  else
			  square.setBackground( i % 2 == 0 ? Color.white : Color.black );
	  }  
	  
	  ((JPanel) trivialBoard.getComponent(18)).add(new JLabel(new ImageIcon("image/logo.jpg")));
	  ((JPanel) trivialBoard.getComponent(18)).setSize(100,10);
	  
	  
	  Couleur serie[] = {Couleur.VERT, Couleur.ORANGE,Couleur.BLEU, Couleur.ROUGE,Couleur.NOIR};
	  int indexSerie=0;
	  int indicesuperCam=1;
	  int numeroCote =1;
	  int indiceelement=10;
	  for (int numCase = 0; numCase < 24; numCase++) {
			
		  switch (serie[indexSerie]) {
	      case ROUGE : trivialBoard.getComponent(indiceelement).setBackground(Color.RED);
	               break;
	      case BLEU:   trivialBoard.getComponent(indiceelement).setBackground(Color.BLUE);
	               break;
	      case VERT: trivialBoard.getComponent(indiceelement).setBackground(Color.GREEN);
	               break;
	      case ORANGE:  trivialBoard.getComponent(indiceelement).setBackground(Color.ORANGE);
	               break;
	      case NOIR:  trivialBoard.getComponent(indiceelement).setBackground(Color.BLACK);
	               break;
		  }	
		  
		  
		if (indicesuperCam %6 ==0) {
			  ( (JPanel)trivialBoard.getComponent(indiceelement)).setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));
			  
			  numeroCote++;
		} 
		 
		
		  if (indexSerie==4) {indexSerie=0;}
		  else {indexSerie++;}
		  
		  switch (numeroCote) {
	      case 1 : indiceelement++;
	               break;
	      case 2:  indiceelement= indiceelement+8;
	               break;
	      case 3: indiceelement--;
	               break;
	      case 4:  indiceelement= indiceelement-8;
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
  
  
  /*private void creation_damier(){
	  
	  
	  for (int i = 0; i < 64; i++) {
		  JPanel square = new JPanel( new BorderLayout() );
		  chessBoard.add( square );
		 
		  int row = (i / 8) % 2;
		  if (row == 0)
			  square.setBackground( i % 2 == 0 ? Color.black : Color.white );
		  else
			  square.setBackground( i % 2 == 0 ? Color.white : Color.black );
	  }  
  }*/
 
  /*private void affiche_move_possible(int xInit,int yInit){
	  
	  for (int x = 0; x <= 7; x++) {
		for (int y = 0; y <= 7; y++) {
			if (chessGameControler.isMoveOk(xInit, yInit, x, y)) {
				int place = x+y*8;
				JPanel panel = (JPanel)chessBoard.getComponent(place);
				panel.setBackground(Color.PINK);
			}
		}
	  }  
	  
  }*/
  
  
 /* public void mousePressed(MouseEvent e){
	  
	  
	  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
	  
	  Point parentLocation = c.getParent().getLocation();
	 
	  if (c instanceof JPanel) 
		  return;
		 
	  if (((JPanel)c.getParent()) instanceof JPanel){
		  startDragCase = (JPanel) c.getParent();
		 
	  }
	  // calcule des coordonnées.
	  int num_case =0;
	  JPanel macase= (JPanel) chessBoard.getComponentAt(e.getX(),e.getY());
	  for (num_case = 0;  num_case< 64; num_case++) {
		if (chessBoard.getComponent(num_case).equals(macase)) {
			break;			
		}
	  }
	 // System.out.println("i="+num_case);
	  xInit =  num_case%8;
	  yInit = (num_case-xInit)/8;
	 // System.out.println(xInit+"  "+yInit);
	  if (!chessGameControler.isPlayerOK(new Coord(xInit,yInit))) {
		  System.out.println("c'est pas a ton tour de jouer !!!");
		return;
	}
	  
	  
	  affiche_move_possible(xInit,yInit);
	  chessPiece = null;
	  
	  xAdjustment = parentLocation.x - e.getX();
	  yAdjustment = parentLocation.y - e.getY();
	  chessPiece = (JLabel)c;
	  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
	  chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
	  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	  
	  chessBoard.repaint();
	  //System.out.println(parentLocation.getX() +" "+ parentLocation.getY());
	 // System.out.println(xInit +"  "+yInit );
  }*/
 
  //Move the chess piece around
  
 /* public void mouseDragged(MouseEvent me) {
	  
	  if (chessPiece == null) return;
	 chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	 chessBoard.repaint();
 }*/
 
  //Drop the chess piece back onto the chess board
 
  /*public void mouseReleased(MouseEvent e) {
	  
	  if(chessPiece == null) return; 
	  
	  chessPiece.setVisible(false);
	//  System.out.println(e.getX()+ "  "+ e.getY()+ "  "+ this.chessBoard.getHeight());
	  
	  
	  if ((e.getX() <= this.chessBoard.getHeight() && e.getX() >= 0)  && 
			  (e.getY() <= this.chessBoard.getWidth() && e.getY()>=0 )) {
		  
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		
		 // int xfinal= (((int)(e.getX() )/8) / (layeredPane.getPreferredSize().height /64));
		 // int yfinal= (((int)(e.getY() )/8) / (layeredPane.getPreferredSize().width /64));
		  int num_case =0;
		  JPanel macase= (JPanel) chessBoard.getComponentAt(e.getX(),e.getY());
		  for (num_case = 0;  num_case< 64; num_case++) {
			if (chessBoard.getComponent(num_case).equals(macase)) {
				break;			
			}
		  }
		//  System.out.println("i="+num_case);
		  int xfinal =  num_case%8;
		  int yfinal = (num_case-xfinal)/8;
		  //System.out.println(xfinal+"  "+yfinal);
		  
		  Coord coordfinal = new Coord(xfinal, yfinal);
		  Coord coordInit = new Coord(xInit, yInit);
		  
		  Boolean move_ok = chessGameControler.move(coordInit, coordfinal);
		  
		  if (move_ok) {
			  //System.out.println("move ok");
		  }else if (startDragCase!=null) {
			  startDragCase.add( chessPiece );
			 // System.out.println("move non ok");
			  chessPiece.setVisible(true);
		  }
		  
  	}else if (startDragCase!=null){
  			re_fait_damier();
		  startDragCase.add( chessPiece );
		  chessPiece.setVisible(true);
	 }
	  
	  chessBoard.repaint();
		  startDragCase= null; 
		  chessPiece=null;
		  
  }*/
 
  /*private void re_fait_damier(){
	  for (int i = 0; i < 64; i++) {
			 JPanel panel = (JPanel)chessBoard.getComponent(i);
			  
			  int row = (i / 8) % 2;
			  if (row == 0)
				  panel.setBackground( i % 2 == 0 ? Color.black : Color.white );
			  else
				  panel.setBackground( i % 2 == 0 ? Color.white : Color.black );
		  }
  }*/
  
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
 
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	/*@Override
	public void update(Observable o, Object arg) {
		
		
		
		
		
		for (int i = 0; i < 64; i++) {
			JPanel panel = (JPanel)chessBoard.getComponent(i);
			panel.removeAll();
		}
		
		//chessBoard.removeAll();
		System.out.println(chessGameControler.getMessage() + "\n");	

		List<PieceIHM> piecesIHM = (List<PieceIHM>) arg;
		
		// création d'un tableau 2D avec les noms des pièces
		for(PieceIHM pieceIHM : piecesIHM) {

			Couleur color = pieceIHM.getCouleur();
			
			String type = (pieceIHM.getNamePiece());
			int place = pieceIHM.getX()+pieceIHM.getY()*8;
			JLabel piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile(type, color)) );
			
			JPanel panel = (JPanel)chessBoard.getComponent(place);
			//panel.removeAll();
			panel.add(piece);
			
		}
		re_fait_damier();
	
		
		 if (chessGameControler.isEnd()) {
				System.out.println("c'est la fin");
				JOptionPane.showMessageDialog(this,"Bravo!!! Vous avez gagné!!!");
			}
		
	}*/
}