package ihm;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controler.TrivialControler;
import Model.Case;
import Model.Couleur;
import observable.TrivialPursuite;
 

public class IHMPlateau extends JFrame implements MouseListener, MouseMotionListener, Observer {
  JLayeredPane layeredPane;
  JPanel trivialBoard, startDragCase;
  int xAdjustment;
  int yAdjustment;
  /** colonne (metier) */
  int xInit;
  /** ligne (metier) */
  int yInit;
  private  TrivialControler trivialControler;
  
  public IHMPlateau( String nom_jeu, TrivialControler trivialControler,  Dimension dim){
	  
	  //this.setExtendedState(this.MAXIMIZED_BOTH);
	  this.trivialControler = trivialControler;
	  Toolkit leKit = this.getToolkit();
	  Dimension tailleFenetre = leKit.getScreenSize();
	  Dimension boardSize = tailleFenetre;
	  //this.setSize(dim);
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
 
	  JLabel nord = new JLabel(new ImageIcon("images/title.png"));
	  nord.setBackground(Color.white);
	  panelGeneral.add(nord, BorderLayout.NORTH);
	  
	  JPanel sud = new JPanel();
	 // sud.setBackground(Color.RED);
	  panelGeneral.add(sud, BorderLayout.SOUTH);
	   
	  JPanel west = new JPanel();
	  west.setLayout(new GridLayout(4,0));
	 // WEST.setBackground(Color.PINK);
	  //panelGeneral.add(west, BorderLayout.WEST);
	  JLabel de = new JLabel(new ImageIcon("images/de.png"));
	 
	  
	  JLabel numero = new JLabel();
	  numero.setLayout(new GridLayout(3,2));
	  
	  
	  JButton btnLancerLesDes = new JButton("Lancer les des");
	  
	  
	  btnLancerLesDes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				trivialControler.lancerLesDes();

			}
		});
	  numero.add(btnLancerLesDes);
	  numero.add(new Button("1"));
	  numero.add(new Button("2"));
	  numero.add(new Button("3"));
	  numero.add(new Button("4"));
	  numero.add(new Button("5"));
	  numero.add(new Button("6"));
	 
	  JLabel categorie = new JLabel();
	  categorie.setLayout(new GridLayout(6,6));
	  //JPanel categorie = new JPanel();
      //categorie.setLayout(new GridLayout(6,6));
     
	  categorie.add(new JLabel(new ImageIcon("images/noir.png")));
	  categorie.add(new JLabel("Cat√©gorie Myst√®re"));
	  
	  categorie.add(new JLabel(new ImageIcon("images/bleu.png")));
	  categorie.add(new JLabel("Cat√©gorie Le-saviez-vous ?"));

	  categorie.add(new JLabel(new ImageIcon("images/rouge.png")));
	  categorie.add(new JLabel("Cat√©gorie Innovations"));
	  
	  categorie.add(new JLabel(new ImageIcon("images/orange.png")));
	  categorie.add(new JLabel("Cat√©gorie Blague"));
	  
	  categorie.add(new JLabel(new ImageIcon("images/vert.png")));
	  categorie.add(new JLabel("Cat√©gorie CPE"));
	  
	  categorie.add(new JLabel(new ImageIcon("images/super.png")));
	  categorie.add(new JLabel("Super Camembert"));
   
	  west.add(de);
	  west.add(numero);
	  west.add(categorie);
	  panelGeneral.add(west,BorderLayout.WEST);
	   
	  JPanel east = new JPanel();
	  east.setLayout(new GridLayout(3,0));
	  
	  JLabel camemebertJoueurUn = new JLabel(new ImageIcon("images/j1.png"));
	  camemebertJoueurUn.setBorder(BorderFactory.createLineBorder(Color.black));
	  JLabel camemebertJoueurdeux = new JLabel(new ImageIcon("images/j2.png"));
	  camemebertJoueurdeux.setBorder(BorderFactory.createLineBorder(Color.black));
	  
	  east.add(camemebertJoueurUn);
	  east.add(camemebertJoueurdeux);
	  panelGeneral.add(east, BorderLayout.EAST);
	  ///////////////////////////////////////////
	  //   			 PLATEAU   			      //
	  //////////////////////////////////////////
	  
	  JPanel panelPlat = new JPanel();
		 // panelGeneral.getPreferredSize();
	  		panelPlat.setLayout( new BorderLayout() );
	  		panelPlat.setPreferredSize( boardSize );
	  		panelPlat.setBounds(0, 0, boardSize.width, boardSize.height);
	  		panelGeneral.add(panelPlat, BorderLayout.CENTER);
	  		
	  		//panelPlat.setBackground(Color.ORANGE);
	  		
	  		
	    /*JPanel panelPlat = new JPanel(){
		  
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
		  
	  };*/
	  
	  panelPlat.setLayout( new GridLayout(8, 8,1,1) );
	  creationplateau(panelPlat);
	  
	  
	  /*JPanel panelInterieurPlat = new JPanel();
		 // panelGeneral.getPreferredSize();
	        panelInterieurPlat.setLayout( new BorderLayout() );
	        panelInterieurPlat.setPreferredSize( boardSize );
	        panelInterieurPlat.setBounds(0, 0, boardSize.width, boardSize.height);
	  		panelPlat.add(panelInterieurPlat);
	  		
	  		panelInterieurPlat.setBackground(Color.YELLOW);
	  
	 */
	  
	  
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
  private void creationplateau(JPanel panelPlat) {
	  
	  
	  for (int i = 0; i < 49; i++) {
		  JPanel square = new JPanel( new BorderLayout() );
		  panelPlat.add( square );
		 
		  int row = (i / 7) % 2;
		  
		  square.setOpaque(false);
		  square.setBackground(Color.WHITE);
		 /* if (row == 0)
			  
			  square.setBackground( i % 2 == 0 ? Color.black : Color.white );
		  else
			  square.setBackground( i % 2 == 0 ? Color.white : Color.black );*/
	  }  
	  
	
	  ((JPanel) panelPlat.getComponent(18)).add(new JLabel(new ImageIcon("image/logo.jpg")));
	  ((JPanel) panelPlat.getComponent(18)).setSize(100,10);
	  
	  
	  Couleur serie[] = {Couleur.VERT, Couleur.ORANGE,Couleur.BLEU, Couleur.ROUGE,Couleur.NOIR};
	  int indexSerie=0;
	  int indicesuperCam=1;
	  int numeroCote =1;
	  int indiceelement=1;
	  for (int numCase = 0; numCase < 24; numCase++) {
		  
		((JPanel)  panelPlat.getComponent(indiceelement)).setOpaque(true);
		  switch (serie[indexSerie]) {
	      case ROUGE : panelPlat.getComponent(indiceelement).setBackground(Color.RED);
	               break;
	      case BLEU:   panelPlat.getComponent(indiceelement).setBackground(Color.BLUE);
	               break;
	      case VERT: panelPlat.getComponent(indiceelement).setBackground(Color.GREEN);
	               break;
	      case ORANGE:  panelPlat.getComponent(indiceelement).setBackground(Color.ORANGE);
	               break;
	      case NOIR:  panelPlat.getComponent(indiceelement).setBackground(Color.BLACK);
	               break;
		  }	
		  
		  
		if (indicesuperCam %6 ==0) {
			  ( (JPanel)panelPlat.getComponent(indiceelement)).setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
			  
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
		
		TrivialPursuite trivialPursuite = new TrivialPursuite();
		TrivialControler trivialControler = new TrivialControler(trivialPursuite);
		
		
		frame = new IHMPlateau("TrivialPursuite",trivialControler,  dim);
		
		trivialPursuite.addObserver((Observer) frame);
	//	frame.pack();
		//frame.setDefaultLookAndFeelDecorated(true);
		//frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLocation(600, 10);
		//frame.setSize(1000,1000);
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
  	
  	// rÈactualiser l'interface, il faut aussi passer en paramËtre les question si besoin pour pouvoir les afficher
  	System.out.println("j'ai bien tout reÁu");
  	
  	
  	/* TO DO
  	 * 
  	 * ajouter un atribut message dans JEU pour pouvoir passez les messages ‡ afficher
  	 * 
  	 * removeAll du panel EST, CENTRE et OUEST
  	 * 
  	 * Refaire le plateau CENTRE avec les cases et les pion qui ont changer de place
  	 * 
  	 * OUEST refaire les camembert avec les part
  	 * 
  	 * EST affichier la question s'il y en a une
  	 * 
  	 * 
  	 * 
  	 */
  	
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