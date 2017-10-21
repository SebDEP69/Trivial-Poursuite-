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
import Model.CouleurPion;
import observable.TrivialPursuite;


public class IHMPlateau extends JFrame implements MouseListener, MouseMotionListener, Observer {
	JLayeredPane layeredPane;
	JPanel trivialBoard, plateauPanel, questionPanel,camembertPanel,titlePanel;
	HashMap<Integer, Integer> numcaseToindicePanel;
	Dimension boardSize;
	private  TrivialControler trivialControler;

	public IHMPlateau( String nom_jeu, TrivialControler trivialControler,  Dimension dim){

		numcaseToindicePanel = new HashMap<Integer,Integer>();
		//this.setExtendedState(this.MAXIMIZED_BOTH);
		this.trivialControler = trivialControler;
		boardSize = this.getToolkit().getScreenSize();
		this.setTitle(nom_jeu);
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);



		trivialBoard = new JPanel();
		trivialBoard.setLayout( new BorderLayout() );
		trivialBoard.setPreferredSize( boardSize );
		trivialBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		layeredPane.add(trivialBoard, JLayeredPane.DEFAULT_LAYER);

		//HAUT
		this.titlePanel = new JPanel();
		JLabel titleLabel = new JLabel(new ImageIcon("images/title.png"));
		titlePanel.add(titleLabel);
		titlePanel.setBackground(Color.white);
		trivialBoard.add(titlePanel, BorderLayout.NORTH);




		//GAUCHE
		questionPanel = new JPanel();
		questionPanel.setLayout(new GridLayout(5,0));
		trivialBoard.add(questionPanel,BorderLayout.WEST);

		//DROITE
		camembertPanel = new JPanel();
		camembertPanel.setLayout(new GridLayout(3,0));
		trivialBoard.add(camembertPanel, BorderLayout.EAST);




		///////////////////////////////////////////
		//   	 FORMULAIRE CREATION JOUEUR	      //
		//////////////////////////////////////////

		// CENTRE
		plateauPanel = new JPanel();
		GridBagLayout gridbagcentre = new GridBagLayout();
		plateauPanel.setLayout(gridbagcentre);

		Dimension dimensionJTF = new Dimension(100,30);
		CouleurPion CouleurduPion[] = {CouleurPion.VERT, CouleurPion.ORANGE,CouleurPion.BLEU, CouleurPion.ROUGE};
		//###### JOUEUR 1
		JLabel labelnomjoueurun = new JLabel("Nom joueur 1 :");
		JLabel labelcouleurjoueurun = new JLabel("Couleur joueur 1 :");
		JTextField textNomJoueurun = new JTextField();
		textNomJoueurun.setPreferredSize(dimensionJTF);
		JComboBox<CouleurPion> couleurPionJoueurun = new JComboBox(CouleurduPion);

		//###### JOUEUR 2
		JLabel labelnomjoueurdeux = new JLabel("Nom joueur 2 :");
		JLabel labelcouleurjoueurdeux = new JLabel("Couleur joueur 2 :");
		JTextField textNomJoueurdeux = new JTextField();
		textNomJoueurdeux.setPreferredSize(dimensionJTF);
		JComboBox<CouleurPion> couleurPionJoueurdeux = new JComboBox(CouleurduPion);



		// nom des joueurs
		plateauPanel.add(labelnomjoueurun);
		plateauPanel.add(textNomJoueurun);
		plateauPanel.add(labelnomjoueurdeux);
		plateauPanel.add(textNomJoueurdeux);

		// couleur des joueur
		plateauPanel.add(labelcouleurjoueurun);
		plateauPanel.add(couleurPionJoueurun);
		plateauPanel.add(labelcouleurjoueurdeux);
		plateauPanel.add(couleurPionJoueurdeux);


		JButton btnlancer = new JButton("Lance");
		btnlancer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				String nomjoueurun = textNomJoueurun.getText();
				String nomjoueurdeux = textNomJoueurdeux.getText();
				CouleurPion couleurjoueurun = (CouleurPion) couleurPionJoueurun.getSelectedItem();
				CouleurPion couleurjoueurdeux = (CouleurPion) couleurPionJoueurdeux.getSelectedItem();


				System.out.println(nomjoueurun);
				System.out.println(nomjoueurdeux);
				System.out.println(couleurjoueurun);
				System.out.println(couleurjoueurdeux);

				trivialControler.creationJoueur(nomjoueurun,nomjoueurdeux,couleurjoueurun,couleurjoueurdeux);

			}
		});






		plateauPanel.add(btnlancer);
		trivialBoard.add(plateauPanel,BorderLayout.CENTER);





		/*
	  trivialBoard = new JPanel();
	 // panelGeneral.getPreferredSize();
	  trivialBoard.setLayout( new BorderLayout() );
	  trivialBoard.setPreferredSize( boardSize );
	  trivialBoard.setBounds(0, 0, boardSize.width, boardSize.height);
	  layeredPane.add(trivialBoard, JLayeredPane.DEFAULT_LAYER);

	  JLabel nord = new JLabel(new ImageIcon("images/title.png"));
	  nord.setBackground(Color.white);
	  trivialBoard.add(nord, BorderLayout.NORTH);

	  JPanel sud = new JPanel();
	 // sud.setBackground(Color.RED);
	  trivialBoard.add(sud, BorderLayout.SOUTH);

	  ///////////////////////////////////////////
	  //   			 QUESTION 			      //
	  //////////////////////////////////////////
	  questionPanel = new JPanel();
	  questionPanel.setLayout(new GridLayout(5,0));
	  this.creationPanelQuestion("");
	  trivialBoard.add(questionPanel,BorderLayout.WEST);



	  ///////////////////////////////////////////
	  //   			 CAMEMBERT 			      //
	  //////////////////////////////////////////

	  camembertPanel = new JPanel();
	  camembertPanel.setLayout(new GridLayout(3,0));
	  this.creationPanelCamembert();
	  trivialBoard.add(camembertPanel, BorderLayout.EAST);


	  ///////////////////////////////////////////
	  //   			 PLATEAU   			      //
	  //////////////////////////////////////////

	  plateauPanel = new JPanel();
	  // panelGeneral.getPreferredSize();
	  plateauPanel.setLayout( new BorderLayout() );
	  plateauPanel.setPreferredSize( boardSize );
	  plateauPanel.setBounds(0, 0, boardSize.width, boardSize.height);
	  trivialBoard.add(plateauPanel, BorderLayout.CENTER);
	  plateauPanel.setLayout( new GridLayout(7, 7,1,1) );
	  this.creationplateau();

		 */ 
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

	private void creationPanelCamembert()
	{


		JLabel camemebertJoueurUn = new JLabel(new ImageIcon("images/j1.png"));
		camemebertJoueurUn.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel camemebertJoueurdeux = new JLabel(new ImageIcon("images/j2.png"));
		camemebertJoueurdeux.setBorder(BorderFactory.createLineBorder(Color.black));

		camembertPanel.add(camemebertJoueurUn);
		camembertPanel.add(camemebertJoueurdeux);
	}

	private void creationplateau() {


		for (int i = 0; i < 49; i++) {
			JPanel square = new JPanel( new BorderLayout() );
			plateauPanel.add( square );

			int row = (i / 7) % 2;

			square.setOpaque(false);
			square.setBackground(Color.WHITE);
			/* if (row == 0)

			  square.setBackground( i % 2 == 0 ? Color.black : Color.white );
		  else
			  square.setBackground( i % 2 == 0 ? Color.white : Color.black );*/
		}  


		// ((JPanel) plateauPanel.getComponent(18)).add(new JLabel(new ImageIcon("image/logo.jpg")));
		// ((JPanel) plateauPanel.getComponent(18)).setSize(100,10);


		Couleur serie[] = {Couleur.VERT, Couleur.ORANGE,Couleur.BLEU, Couleur.ROUGE,Couleur.NOIR};
		int indexSerie=0;
		int indicesuperCam=1;
		int numeroCote =1;
		int indiceelement=1;
		for (int numCase = 0; numCase < 24; numCase++) {

			numcaseToindicePanel.put(numCase, indiceelement);

			((JPanel)  plateauPanel.getComponent(indiceelement)).setOpaque(true);
			switch (serie[indexSerie]) {
			case ROUGE : plateauPanel.getComponent(indiceelement).setBackground(Color.RED);
			break;
			case BLEU:   plateauPanel.getComponent(indiceelement).setBackground(Color.BLUE);
			break;
			case VERT: plateauPanel.getComponent(indiceelement).setBackground(Color.GREEN);
			break;
			case ORANGE:  plateauPanel.getComponent(indiceelement).setBackground(Color.ORANGE);
			break;
			case NOIR:  plateauPanel.getComponent(indiceelement).setBackground(Color.BLACK);
			break;
			}	



			if (indicesuperCam %6 ==0) {
				( (JPanel)plateauPanel.getComponent(indiceelement)).setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
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

	private void creationPanelQuestion(String lancede) 
	{

		///////////////////////////////////////////
		//   			 QUESTION 		         //
		//////////////////////////////////////////



		JLabel imagede = new JLabel(new ImageIcon("images/de.png"));
		JLabel de = new JLabel(new ImageIcon("images/de"+lancede+".png"));


		//JLabel numero = new JLabel();
		//numero.setLayout(new GridLayout(3,2));
		JButton btnLancerLesDes = new JButton("Lancer les des",new ImageIcon("images/de.png"));  
		btnLancerLesDes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				trivialControler.lancerLesDes();

			}
		});


		JLabel categorie = new JLabel();
		categorie.setLayout(new GridLayout(6,2));
		//JPanel categorie = new JPanel();
		//categorie.setLayout(new GridLayout(6,6));

		categorie.add(new JLabel(new ImageIcon("images/noir.png")));
		categorie.add(new JLabel("Categorie Mystere"));

		categorie.add(new JLabel(new ImageIcon("images/bleu.png")));
		categorie.add(new JLabel("Categorie Le-saviez-vous ?"));

		categorie.add(new JLabel(new ImageIcon("images/rouge.png")));
		categorie.add(new JLabel("Categorie Innovations"));

		categorie.add(new JLabel(new ImageIcon("images/orange.png")));
		categorie.add(new JLabel("Categorie Blague"));

		categorie.add(new JLabel(new ImageIcon("images/vert.png")));
		categorie.add(new JLabel("Categorie CPE"));

		categorie.add(new JLabel(new ImageIcon("images/super.png")));
		categorie.add(new JLabel("Super Camembert"));

		questionPanel.add(de);
		//questionPanel.add(imagede);
		questionPanel.add(btnLancerLesDes);

		questionPanel.add(categorie);


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



	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object info) {

		// réactualiser l'interface, il faut aussi passer en paramètre les question si besoin pour pouvoir les afficher
		System.out.println("j'ai bien tout reçu");


		/* TO DO
		 * 
		 * ajouter un atribut message dans JEU pour pouvoir passez les messages à afficher
		 * 
		 * removeAll du panel EST, CENTRE et OUEST
		 * 
		 * Refaire le plateau CENTRE avec les cases et les pion qui ont changer de place
		 * 
		 * Gerer la possition des deux pion sur la même case
		 * 
		 * OUEST refaire les camembert avec les part
		 * 
		 * EST affichier la question s'il y en a une
		 * 
		 * 
		 * 
		 */



		/*for (int i = 0; i < trivialBoard.getComponentCount(); i++) {
  		((JPanel)trivialBoard.getComponent(i)).removeAll();
	}*/


		this.camembertPanel.removeAll();
		this.questionPanel.removeAll();
		this.plateauPanel.removeAll();
		//this.titlePanel.removeAll();

		///////////////////////////////////////////
		//   		 TITRE  					 //
		////////////////////////////////////////// 

		/*JLabel titleLabel = new JLabel(new ImageIcon("images/title.png"));
 	titlePanel.add(titleLabel);
	trivialBoard.add(titlePanel, BorderLayout.NORTH);
		 */

		/* JPanel sud = new JPanel();
	 // sud.setBackground(Color.RED);
	  trivialBoard.add(sud, BorderLayout.SOUTH);
		 */

		///////////////////////////////////////////
		//   		 affiche le lancer + QUESTION   //
		////////////////////////////////////////// 
		if (((ArrayList<String>) info).size() >3) {
			this.creationPanelQuestion(( (ArrayList<String>) info).get(3) ) ;
		}

		///////////////////////////////////////////
		//   			 CAMEMBERT 			      //
		//////////////////////////////////////////

		this.creationPanelCamembert();

		///////////////////////////////////////////
		//   			 PLATEAU   			      //
		//////////////////////////////////////////

		//plateauPanel = new JPanel();
		plateauPanel.setLayout( new GridLayout(7, 7,1,1) );

		this.creationplateau();


		///////////////////////////////////////////
		//   			 MESSAGE     		      //
		//////////////////////////////////////////
		if (((ArrayList<String>) info).size() >2) {
			JLabel message = new JLabel( ( (ArrayList<String>) info).get(2) );
			this.questionPanel.add(message);
		}

		///////////////////////////////////////////
		//   			 PLACEMENT PION		      //
		//////////////////////////////////////////
		System.out.println( " joueur 1 case"+((ArrayList<String>) info).get(0));
		System.out.println( " joueur 2 case"+((ArrayList<String>) info).get(1));
		int positionj1 = numcaseToindicePanel.get(Integer.parseInt( ((ArrayList<String>) info).get(0)));
		int positionj2 = numcaseToindicePanel.get(Integer.parseInt( ((ArrayList<String>) info).get(1)));

		int xj1 = ((JPanel) plateauPanel.getComponent(positionj1)).getX();
		int yj1 = ((JPanel) plateauPanel.getComponent(positionj1)).getY();
		ImgPion pionjun = new ImgPion("images/j1.png", xj1,yj1);
		((JPanel) plateauPanel.getComponent(positionj1)).add(pionjun);

		//x = x + (pionjun.getWidth()*2);
		int xj2 = ((JPanel) plateauPanel.getComponent(positionj2)).getX();
		int yj2 = ((JPanel) plateauPanel.getComponent(positionj2)).getY();
		ImgPion pionjdeux = new ImgPion("images/j2.png", xj2,yj2);
		((JPanel) plateauPanel.getComponent(positionj2)).add(pionjdeux);


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