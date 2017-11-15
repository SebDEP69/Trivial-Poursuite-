package ihm;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import Controler.TrivialControler;
import Model.Couleur;
import Model.CouleurPion;
import Model.Joueur;
import Model.Question;
import observable.TrivialPursuite;
import vue.TrivialPursuitGUI;


@SuppressWarnings("serial")
public class IHMPlateau extends JFrame implements MouseListener, MouseMotionListener, Observer {
	JLayeredPane layeredPane;
	JPanel trivialBoard, plateauPanel, desPanel, camembertPanel, titlePanel, questionPanel;
	ArrayList<int[]> numcaseToindicePanel;
	Dimension boardSize;
	private  TrivialControler trivialControler;

	public IHMPlateau( String nom_jeu, TrivialControler trivialControler,  Dimension dim){

		numcaseToindicePanel = new ArrayList<int[]>();
		this.trivialControler = trivialControler;
		int width = (int) (this.getToolkit().getScreenSize().getWidth() );
		int height = (int) (this.getToolkit().getScreenSize().getHeight());
		boardSize = new Dimension(width-100, height-100) ;
		this.setTitle(nom_jeu);
		this.setSize(boardSize);
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);


		trivialBoard = new JPanel();
		trivialBoard.setLayout( new BorderLayout() );
		trivialBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		layeredPane.add(trivialBoard, JLayeredPane.DEFAULT_LAYER);

		//HAUT
		this.titlePanel = new JPanel();
		ImageIcon imageTitle = new ImageIcon("images/title.png");
		JLabel titleLabel = new JLabel(imageTitle);
		titlePanel.setPreferredSize(new Dimension(0, imageTitle.getIconHeight()));
		System.out.println(imageTitle.getIconHeight());
		titlePanel.add(titleLabel);
		titlePanel.setBackground(Color.white);
		trivialBoard.add(titlePanel, BorderLayout.NORTH);


		//GAUCHE
		desPanel = new JPanel();
		desPanel.setLayout(new GridLayout(5,0));
		desPanel.setPreferredSize( new Dimension(200,0));
		trivialBoard.add(desPanel,BorderLayout.WEST);
		JButton retour = new JButton("Retour");
		desPanel.add(retour);
		retour.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame frame;	
					Dimension dim;
					
					dim = new Dimension(800, 800);
					
					frame = new TrivialPursuitGUI("Trivial Pursuit", dim);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.pack();
					frame.setVisible(true);

					
				}
			});

		//DROITE
		camembertPanel = new JPanel();
		camembertPanel.setLayout(new GridLayout(3,0));
		camembertPanel.setPreferredSize( new Dimension(200,0));
		trivialBoard.add(camembertPanel, BorderLayout.EAST);


		// CENTRE
		questionPanel = new JPanel();
		questionPanel.setLayout(new GridLayout(2,0));		


		///////////////////////////////////////////
		//   	 FORMULAIRE CREATION JOUEUR	      //
		//////////////////////////////////////////

		// CENTRE
		plateauPanel = new JPanel();
		GridBagLayout gridbagcentre = new GridBagLayout();
		plateauPanel.setLayout(gridbagcentre);
	

		Dimension dimensionJTF = new Dimension(100,100);
		CouleurPion CouleurduPion[] = {CouleurPion.VERT, CouleurPion.ORANGE,CouleurPion.BLEU, CouleurPion.ROUGE};
		//###### JOUEUR 1
		JLabel labelnomjoueurun = new JLabel("Joueur 1 ");
		JLabel labelcouleurjoueurun = new JLabel("Couleur joueur 1 :");
		JTextField textNomJoueurun = new JTextField();
		textNomJoueurun.setPreferredSize(dimensionJTF);
		JComboBox<CouleurPion> couleurPionJoueurun = new JComboBox<CouleurPion>(CouleurduPion);

		//###### JOUEUR 2
		JLabel labelnomjoueurdeux = new JLabel("Joueur 2 ");
		JLabel labelcouleurjoueurdeux = new JLabel("Couleur joueur 2 :");
		JTextField textNomJoueurdeux = new JTextField();
		textNomJoueurdeux.setPreferredSize(dimensionJTF);
		JComboBox<CouleurPion> couleurPionJoueurdeux = new JComboBox<CouleurPion>(CouleurduPion); 


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
		


		JButton btnlancer = new JButton("Lancer");
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

				trivialControler.creationJoueur(nomjoueurun,nomjoueurdeux, couleurjoueurun, couleurjoueurdeux);

			}
		});

		plateauPanel.add(btnlancer);
		trivialBoard.add(plateauPanel,BorderLayout.CENTER);



		this.pack();

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

	private void creationPanelCamembert(ArrayList<Joueur> listeDesJoueur)
	{

		///////////////////////////////////////////
		//   			 CAMEMBERT 	            //
		//////////////////////////////////////////
		
		Image cam;
	//	Image camJdeux;
		try {
			int indicejoueur = 1;
			for (Joueur joueur : listeDesJoueur) {
				String[] listeImage = listeImageCamembert(joueur,indicejoueur);	
				cam = imageCamembertWithPart("images/j"+indicejoueur+".png",listeImage[0],listeImage[1],listeImage[2],listeImage[3]);
				JLabel camemebertJoueur = new JLabel(new ImageIcon(cam));
				camemebertJoueur.setBorder(BorderFactory.createLineBorder(Color.black));
				camembertPanel.add(camemebertJoueur);
				indicejoueur++;
			}
			
			
		} catch (IOException e) {e.printStackTrace();}
			
		
		/*// JOUEUR 1
		JLabel camemebertJoueurUn = new JLabel(new ImageIcon(camJun));
		camemebertJoueurUn.setBorder(BorderFactory.createLineBorder(Color.black));
		try {
			camJdeux = imageCamembertWithPart("images/j1.png","images/joueur2-1.png","images/joueur2-2.png","images/joueur2-3.png","images/joueur2-4.png");
		} catch (IOException e) {e.printStackTrace();}
		
		
		
		// JOUEUR 2
		JLabel camemebertJoueurdeux = new JLabel(new ImageIcon(camJdeux));
		camemebertJoueurdeux.setBorder(BorderFactory.createLineBorder(Color.black));

		camembertPanel.add(camemebertJoueurUn);
		camembertPanel.add(camemebertJoueurdeux);
		
		*/
	}
	
	private String[] listeImageCamembert(Joueur joueur, int numJoueur) {
		
		
		String[] listeImage = {"","","",""};
		if (joueur.getCamembert().ContientPart(Couleur.BLEU)) {
			listeImage[0] = "images/joueur"+numJoueur+"-1.png";
		}else {
			listeImage[0] = null;
		}
		
		if (joueur.getCamembert().ContientPart(Couleur.ROUGE)) {
			listeImage[1] = "images/joueur"+numJoueur+"-2.png";
		}else {
			listeImage[1] = null;
		}
		
		if (joueur.getCamembert().ContientPart(Couleur.ORANGE)) {
			listeImage[2] = "images/joueur"+numJoueur+"-3.png";
		}else {
			listeImage[2] = null;
		}
		
		if (joueur.getCamembert().ContientPart(Couleur.VERT)) {
			listeImage[3] = "images/joueur"+numJoueur+"-4.png";
		}else {
			listeImage[3] = null;
		}
		return listeImage;
	}
	
	public static BufferedImage imageCamembertWithPart(String pathcam, String partBleu, String partRouge, String partOrange
													,String partvert) throws IOException{ 
		
		Image imagetemp1 = ImageIO.read(new File(pathcam));
		BufferedImage image1 = (BufferedImage) imagetemp1;
		
		

		Graphics2D g2d = image1.createGraphics(); 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
		                RenderingHints.VALUE_ANTIALIAS_ON); 
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, 
		                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY); 
	    if (partBleu !=null) {
	    	Image imagetemp = ImageIO.read(new File(partBleu));
			BufferedImage image = (BufferedImage) imagetemp;
			g2d.drawImage(image, 7, 2, 78,78, null); 
		}
	    if (partRouge !=null) {
	    	Image imagetemp = ImageIO.read(new File(partRouge));
			BufferedImage image = (BufferedImage) imagetemp;
			g2d.drawImage(image, 85, 2, 78,78, null); 
		}
	    if (partvert !=null) {
	    	Image imagetemp = ImageIO.read(new File(partvert));
			BufferedImage image = (BufferedImage) imagetemp;
			g2d.drawImage(image, 7, 80, 78,78, null); 
		}
	    if (partOrange !=null) {
	    	Image imagetemp = ImageIO.read(new File(partOrange));
			BufferedImage image = (BufferedImage) imagetemp;
			g2d.drawImage(image, 85, 80, 78,78, null); 
		}
	    
		g2d.dispose(); 
	  
		return image1 ; 
	}

	private void creationplateau() {

		///////////////////////////////////////////
		//   			 HAUT   	            //
		//////////////////////////////////////////
		Color serieCouleurHaut[] = {Color.GREEN, Color.ORANGE,Color.BLUE, Color.RED,Color.BLACK};
		JPanel haut = new JPanel( new GridLayout(1, 7));
		
		System.out.println("plateau" + plateauPanel.getHeight());
		haut.setPreferredSize( new Dimension(0,(plateauPanel.getHeight()/7)));
		haut.setName("en haut");
		
		
		JPanel caseun = new JPanel( );
		caseun.setName("case depart");
		caseun.setBackground(Color.RED);
		caseun.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
		haut.add(caseun);
		
		for (Color couleur : serieCouleurHaut) {
			JPanel square = new JPanel(  );
			square.setBackground(couleur);
			haut.add(square);
		}
		JPanel lastCase = new JPanel( );
		lastCase.setBackground(Color.GREEN);
		lastCase.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
		haut.add(lastCase);
		plateauPanel.add(haut, BorderLayout.NORTH);
		
		
		
		
		for (int i = 1; i < 7; i++) {
			int[] coordonnee = {0,i};
			numcaseToindicePanel.add( coordonnee);
		}
		
		///////////////////////////////////////////
		//   			 DROITE   		        //
		//////////////////////////////////////////
		Color serieCouleurdroite[] = { Color.ORANGE,Color.BLUE, Color.RED,Color.BLACK,Color.GREEN};
		JPanel droite = new JPanel( new GridLayout(5, 1));
		droite.setPreferredSize( new Dimension((plateauPanel.getWidth()/7),0));
		droite.setName("Droite");
		for (Color couleur : serieCouleurdroite) {
			JPanel square = new JPanel( );
			square.setBackground(couleur);
			droite.add(square);
		}
		plateauPanel.add(droite, BorderLayout.EAST);
		
		for (int i = 0; i < 5; i++) {
			int[] coordonnee = {1,i};
			numcaseToindicePanel.add( coordonnee);
		}
		///////////////////////////////////////////
		//   			 BAS   		            //
		//////////////////////////////////////////
		Color serieCouleurBas[] = { Color.ORANGE,Color.GREEN,Color.BLACK,Color.RED,Color.BLUE };
		JPanel bas = new JPanel( new GridLayout(1, 7));
		bas.setPreferredSize( new Dimension(0,(plateauPanel.getHeight()/7)));
		bas.setName("BAS");
		JPanel basun = new JPanel(  );
		basun.setBackground(Color.BLUE);
		basun.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
		bas.add(basun);
		
		for (Color couleur : serieCouleurBas) {
			JPanel square = new JPanel(  );
			square.setBackground(couleur);
			bas.add(square);
		}
		JPanel lastBas = new JPanel( );
		lastBas.setBackground(Color.ORANGE);
		lastBas.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
		bas.add(lastBas);
		plateauPanel.add(bas, BorderLayout.SOUTH);
		
		for (int i = 6; i >= 0; i--) {
			int[] coordonnee = {2,i};
			numcaseToindicePanel.add( coordonnee);
		}
		///////////////////////////////////////////
		//   			 GAUCHE   		        //
		//////////////////////////////////////////
		Color serieCouleurGauche[] = { Color.BLUE,Color.ORANGE, Color.GREEN,Color.BLACK,Color.RED};
		JPanel gauche = new JPanel( new GridLayout(5, 1));
		gauche.setPreferredSize( new Dimension((plateauPanel.getWidth()/7),0));
		gauche.setName("gauche");
		
		for (Color couleur : serieCouleurGauche) {
			JPanel square = new JPanel( );
			square.setBackground(couleur);
			gauche.add(square);
		}
		for (int i = 4; i >= 0 ; i--) {
			int[] coordonnee = {3,i};
			numcaseToindicePanel.add( coordonnee);
		}
		
		int[] coordonnee = {0,0};
		numcaseToindicePanel.add( coordonnee);
		
		plateauPanel.add(gauche, BorderLayout.WEST);
		
	}

	
	private void creationPanelQuestion(Question question){
		
		
		
		///////////////////////////////////////////
		//   			 QUESTION 	            //
		//////////////////////////////////////////
				
		//QUESTION 
		questionPanel = new JPanel(new GridLayout(2,0));
		JLabel questionLabel = new JLabel( question.getQuestion() );
		this.questionPanel.add(questionLabel);
		
		
		// REPONSES
		String[] reponses = question.getChoix();
		ButtonGroup groupButton = new ButtonGroup();
		JPanel reponsePanel = new JPanel(new GridLayout(5,0));
		// R�ponse 1
		JRadioButton rep1 = new JRadioButton(reponses[0]);
		rep1.setActionCommand(rep1.getText());
		groupButton.add(rep1);
		reponsePanel.add(rep1);
		
		//R�ponse 2
		JRadioButton rep2 = new JRadioButton(reponses[1]);
		rep2.setActionCommand(rep2.getText());
		groupButton.add(rep2);
		reponsePanel.add(rep2);
		
		//R�ponse 3
		JRadioButton rep3 = new JRadioButton(reponses[2]);
		rep3.setActionCommand(rep3.getText());
		groupButton.add(rep3);
		reponsePanel.add(rep3);
		
		//R�ponse 4
		JRadioButton rep4 = new JRadioButton(reponses[3]);
		rep4.setActionCommand(rep4.getText());
		groupButton.add(rep4);
		reponsePanel.add(rep4);
		
		JButton valider= new JButton("Valider la reponse");
		valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (groupButton.getSelection() !=null) {
					String reponse =groupButton.getSelection().getActionCommand();
					System.out.println("bouton selected "+groupButton.getSelection().getActionCommand());
					trivialControler.validerReponse(question, reponse);
				}else{
					System.out.println("selectioner une reponse");
				}
				

			}
		});
		reponsePanel.add(valider);
		
		this.questionPanel.add(reponsePanel);
		this.plateauPanel.add(questionPanel, BorderLayout.CENTER);		
	}
	
	private void creationPanelDes(String lancede, boolean isQuestion) 
	{

		///////////////////////////////////////////
		//   			 LANCER LES DES 		         //
		//////////////////////////////////////////



		//JLabel imagede = new JLabel(new ImageIcon("images/de.png"));
		//JLabel de = new JLabel(new ImageIcon("images/de"+lancede+".png"));


		//JLabel numero = new JLabel();
		//numero.setLayout(new GridLayout(3,2));
		
		JButton btnLancerLesDes = new JButton(new ImageIcon("images/de"+lancede+".png"));  
		
		if (!isQuestion) {
			btnLancerLesDes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					trivialControler.lancerLesDes();

				}
			});
		}
		


		JLabel categorie = new JLabel();
		categorie.setLayout(new GridLayout(6,2));

		categorie.add(new JLabel(new ImageIcon("images/noir.png")));
		categorie.add(new JLabel("Mystere"));

		categorie.add(new JLabel(new ImageIcon("images/bleu.png")));
		categorie.add(new JLabel("Le-saviez-vous ?"));

		categorie.add(new JLabel(new ImageIcon("images/rouge.png")));
		categorie.add(new JLabel("Innovations"));

		categorie.add(new JLabel(new ImageIcon("images/orange.png")));
		categorie.add(new JLabel("Blague"));

		categorie.add(new JLabel(new ImageIcon("images/vert.png")));
		categorie.add(new JLabel("CPE"));

		categorie.add(new JLabel(new ImageIcon("images/super.png")));
		categorie.add(new JLabel("Super Camembert"));
		
		//questionPanel.add(imagede);
		
		JLabel titreLancerDes = new JLabel();
		titreLancerDes.add(new JLabel("Bouton de lancement des des"));  
		desPanel.add(titreLancerDes); 
		desPanel.add(btnLancerLesDes);
		desPanel.add(categorie);


	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object info) {

		// r�actualiser l'interface, il faut aussi passer en param�tre les question si besoin pour pouvoir les afficher


		/*for (int i = 0; i < trivialBoard.getComponentCount(); i++) {
  		((JPanel)trivialBoard.getComponent(i)).removeAll();
	}*/

		if ( (Boolean) ((ArrayList<Object>)info).get(0))  { // si c'est la fin du game
			
			this.plateauPanel.removeAll();
			this.questionPanel.removeAll();
			this.camembertPanel.removeAll();
			ArrayList<Joueur> listeDesJoueur = ((ArrayList<Joueur>) ((ArrayList<Object>) info).get(1));
			this.creationPanelCamembert(listeDesJoueur);
			
			this.creationplateau();
			JLabel message = new JLabel( (String) ( (ArrayList<Object>) info).get(2) );
			this.plateauPanel.add(message, BorderLayout.CENTER);
			
		}else {
			

			this.camembertPanel.removeAll();
			this.desPanel.removeAll();
			this.plateauPanel.removeAll();
			this.questionPanel.removeAll();

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
			//  affiche le lancer de d� + QUESTION   //
			////////////////////////////////////////// 
			if (((ArrayList<String>) info).size() >3) {
				if ( ((ArrayList<Object>) info).size() >4 && ((ArrayList<Object>) info).get(4) != null) {// si ya une question
					this.creationPanelDes(( (ArrayList<String>) info).get(3), true) ;
				}else{
					this.creationPanelDes(( (ArrayList<String>) info).get(3), false) ;
				}

			}





			///////////////////////////////////////////
			//   			 CAMEMBERT 			      //
			//////////////////////////////////////////

			ArrayList<Joueur> listeDesJoueur = ((ArrayList<Joueur>) ((ArrayList<Object>) info).get(1));
			this.creationPanelCamembert(listeDesJoueur);

			///////////////////////////////////////////
			//   			 PLATEAU   			      //
			//////////////////////////////////////////

			//plateauPanel = new JPanel();
			//plateauPanel.setLayout( new GridLayout(7, 7,1,1) );
			plateauPanel.setLayout(new BorderLayout());
			//plateauPanel.setPreferredSize( boardSize );
			//plateauPanel.setBounds(0, 0, boardSize.width, boardSize.height);
			//plateauPanel.setBackground(Color.RED);
			this.creationplateau();
			/*int casee = 0;
		for (int[] coord : numcaseToindicePanel) {
			System.out.println("Case :"+casee+" border :"+coord[0]+ " component :"+coord[1]);
			casee++;
		}*/

			///////////////////////////////////////////
			//   			 MESSAGE     		      //
			//////////////////////////////////////////
			//		if (((ArrayList<String>) info).size() >2) {
			//			JLabel message = new JLabel( ( (ArrayList<String>) info).get(2) );
			//			this.plateauPanel.add(message, BorderLayout.CENTER);
			//		}
			if ( ((ArrayList<Object>) info).size() >4 && ((ArrayList<Object>) info).get(4) != null) { // si on a une question
				creationPanelQuestion( (Question) ((ArrayList<Object>) info).get(4));
			}else{ // si on est sur une case myst�re 
				JLabel message = new JLabel( (String) ( (ArrayList<Object>) info).get(2) );
				this.plateauPanel.add(message, BorderLayout.CENTER);
			}



			//this.pack();
			///////////////////////////////////////////
			//   			 PLACEMENT PION	        //
			//////////////////////////////////////////

			//System.out.println( " joueur 1 case"+((ArrayList<Object>) info).get(0));
			//System.out.println( " joueur 2 case"+((ArrayList<Object>) info).get(1));


			this.pack();
			/*
			 * JOUEUR 1
			 */
			int casejUn = listeDesJoueur.get(0).getCaseCourant().getNumero();



			int[] coord = numcaseToindicePanel.get(casejUn);
			JPanel border = ((JPanel) plateauPanel.getComponent(coord[0]));
			JPanel componentBorder = ((JPanel) border.getComponent(coord[1]));
			
			String[] listeImage = listeImageCamembert(listeDesJoueur.get(0),1);				
			try {
			Image cam = imageCamembertWithPart("images/j1.png",listeImage[0],listeImage[1],listeImage[2],listeImage[3]);
			
			JLabel camemebertJoueur1 = new JLabel((new ImageIcon(cam.getScaledInstance(75, 75, Image.SCALE_DEFAULT))));
			componentBorder.add(camemebertJoueur1);
			} catch (IOException e) {
				e.printStackTrace();
			}

			/*
			 * JOUEUR 2
			 */
			int casejdeux = listeDesJoueur.get(1).getCaseCourant().getNumero();

			int[] coordj2 = numcaseToindicePanel.get(casejdeux);
			border = ((JPanel) plateauPanel.getComponent(coordj2[0]));
			componentBorder = ((JPanel) border.getComponent(coordj2[1]));
			
			listeImage = listeImageCamembert(listeDesJoueur.get(1),2);	
			try {
				Image cam = imageCamembertWithPart("images/j2.png",listeImage[0],listeImage[1],listeImage[2],listeImage[3]);
			
				JLabel camemebertJoueur2 = new JLabel((new ImageIcon(cam.getScaledInstance(75, 75, Image.SCALE_DEFAULT))));
				componentBorder.add(camemebertJoueur2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			
			
			//ImageIcon iconj2 = new ImageIcon(new ImageIcon("images/j1.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
			//JLabel pionj2 = new JLabel(iconj2);
			//componentBorder.add(pionj2);
			//System.out.println(componentBorder.getName());
			//int xj2 = componentBorder.getX();
			//int yj2 = componentBorder.getY();
			//System.out.println(xj2+"  "+ yj2);
			//ImgPion pionjun = new ImgPion("images/j1.png", xj1,yj1);
			//componentBorder.add( new JLabel(new ImageIcon("images/j2.png")));

			/*
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

			 */


		}
			this.revalidate();
		
	
	}


	public static void main(String[] args) {


		JFrame frame;	
		Dimension dim;

		dim = new Dimension(800, 800);

		TrivialPursuite trivialPursuite = new TrivialPursuite();
		TrivialControler trivialControler = new TrivialControler(trivialPursuite);


		frame = new IHMPlateau("TrivialPursuite",trivialControler,  dim);

		trivialPursuite.addObserver((Observer) frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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