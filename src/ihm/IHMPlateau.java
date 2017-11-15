package ihm;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import Controler.TrivialControler;
import Model.ButtonJolie;
import Model.Couleur;
import Model.CouleurPion;
import Model.Joueur;
import Model.Question;
import observable.TrivialPursuite;


@SuppressWarnings("serial")
public class IHMPlateau extends JFrame implements MouseListener, MouseMotionListener, Observer {
	JLayeredPane layeredPane;
	JPanel trivialBoard, plateauPanel, desPanel, camembertPanel, titlePanel, questionPanel;
	ArrayList<int[]> numcaseToindicePanel;
	Dimension boardSize;
	private  TrivialControler trivialControler;
	//private int x; 
	//Timer tm = new Timer(5, this);
	
	
	public IHMPlateau( String nom_jeu, TrivialControler trivialControler,  Dimension dim){

		numcaseToindicePanel = new ArrayList<int[]>();
		this.trivialControler = trivialControler;
		int width = (int) (this.getToolkit().getScreenSize().getWidth() );
		int height = (int) (this.getToolkit().getScreenSize().getHeight());
		boardSize = new Dimension(width, height) ;
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
		titlePanel.add(titleLabel);
		titlePanel.setBackground(Color.white);
		trivialBoard.add(titlePanel, BorderLayout.NORTH);

		//GAUCHE
		desPanel = new JPanel();
		desPanel.setLayout(new GridLayout(5,0));
		desPanel.setPreferredSize( new Dimension(250,0));
		trivialBoard.add(desPanel,BorderLayout.WEST);

		//DROITE
		camembertPanel = new JPanel();
		camembertPanel.setLayout(new GridLayout(3,0));
		camembertPanel.setPreferredSize( new Dimension(200,0));
		trivialBoard.add(camembertPanel, BorderLayout.EAST);


		// CENTRE
		questionPanel = new JPanel();
		questionPanel.setLayout(new GridLayout(2,0));		

		//affiche le formulaire de creation de joueur
		formulaireCreationJoueur();
	
	}

	private void formulaireCreationJoueur() {
		///////////////////////////////////////////
		//   	 FORMULAIRE CREATION JOUEUR	      //
		//////////////////////////////////////////

		// CENTRE
		plateauPanel = new JPanel();
		GridBagLayout gridbagcentre = new GridBagLayout();
		plateauPanel.setLayout(gridbagcentre);

		Dimension dimensionJTF = new Dimension(150,40);
		CouleurPion CouleurduPion[] = {CouleurPion.VERT, CouleurPion.ORANGE,CouleurPion.BLEU, CouleurPion.ROUGE};
		//###### JOUEUR 1
		JLabel labelnomjoueurun = new JLabel("Nom joueur 1 : ");
		labelnomjoueurun.setFont(new Font("Calibri", Font.BOLD, 23));
		//JLabel labelcouleurjoueurun = new JLabel("Couleur joueur 1 :");
		JTextField textNomJoueurun = new JTextField();
		textNomJoueurun.setPreferredSize(dimensionJTF);
		JComboBox<CouleurPion> couleurPionJoueurun = new JComboBox<CouleurPion>(CouleurduPion);
		
		//###### JOUEUR 2
		JLabel labelnomjoueurdeux = new JLabel("Nom joueur 2 : ");
		labelnomjoueurdeux.setFont(new Font("Calibri", Font.BOLD, 23));
		//JLabel labelcouleurjoueurdeux = new JLabel("Couleur joueur 2 :");
		JTextField textNomJoueurdeux = new JTextField();
		textNomJoueurdeux.setPreferredSize(dimensionJTF);
		JComboBox<CouleurPion> couleurPionJoueurdeux = new JComboBox<CouleurPion>(CouleurduPion);

		
		
		// nom des joueurs
		plateauPanel.add(labelnomjoueurun);
		plateauPanel.add(textNomJoueurun);
		plateauPanel.add(labelnomjoueurdeux);
		plateauPanel.add(textNomJoueurdeux);

		// couleur des joueurs
		/*plateauPanel.add(labelcouleurjoueurun);
		plateauPanel.add(couleurPionJoueurun);
		plateauPanel.add(labelcouleurjoueurdeux);
		plateauPanel.add(couleurPionJoueurdeux);
		 */
		
		// Bouton cr�ation des joueurs
		ButtonJolie btnlancer = new ButtonJolie("Lancer");
		//JButton btnlancer = new JButton("Lancer");
		
		btnlancer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nomjoueurun = textNomJoueurun.getText();
				String nomjoueurdeux = textNomJoueurdeux.getText();
				CouleurPion couleurjoueurun = (CouleurPion) couleurPionJoueurun.getSelectedItem();
				CouleurPion couleurjoueurdeux = (CouleurPion) couleurPionJoueurdeux.getSelectedItem();
				trivialControler.creationJoueur(nomjoueurun,nomjoueurdeux,couleurjoueurun,couleurjoueurdeux);
			}
		});
		plateauPanel.add(btnlancer);
		trivialBoard.add(plateauPanel,BorderLayout.CENTER);
		this.pack();
		
	}
	private void creationPanelCamembert(ArrayList<Joueur> listeDesJoueur)
	{

		///////////////////////////////////////////
		//   			 CAMEMBERT 	            //
		//////////////////////////////////////////
		
		/*
		 * ROLE : PERMET DE CREE LE PANEL POUR AFFICHER LES CAMEMBERT DES JOUEURS
		 * 
		 * PARAM : LA LISTE DES JOUEURS
		 * 
		 */
		Image cam;
		try {
			int indicejoueur = 1;
			// ON PARTCOURT LA LISTE DES JOUEURS
			for (Joueur joueur : listeDesJoueur) {
				// RECUPERE LA LISTE DES IMAGE DES PART QUE CONTIENT LE JOUEUR
				String[] listeImage = listeImageCamembert(joueur,indicejoueur);	
				// ON MET LES PART DE CAMEMBERT SUR L IAMGE DE CAMEMBERT
				cam = imageCamembertWithPart("images/j"+indicejoueur+".png",listeImage[0],listeImage[1],listeImage[2],listeImage[3]);
				// ON CREE LE CAMEMBERT POUR L AFFICHER
				JLabel camemebertJoueur = new JLabel(new ImageIcon(cam));
				camemebertJoueur.setBorder(BorderFactory.createLineBorder(Color.black));
				camembertPanel.add(camemebertJoueur);
				indicejoueur++;
			}
			
		} catch (IOException e) {e.printStackTrace();}	
		
	}
	
	private String[] listeImageCamembert(Joueur joueur, int numJoueur) {
		
		/*
		 * ROLE : PERMET DE FAIRE LA LISTE DES CHEMINS DES IMAGES DE PART DE CAMEMBERT QUE POSSEDE UN JOUEUR
		 * 
		 * PARAM : UN OBJET JOUEUR
		 * 		   LE NUMERO DU JOUEUR (ORDRE DANS LE QUELLE IL JOUE)
		 * 
		 * RETURN : RETOURNE UN TABLEAU DE STRING CONTENANT LES CHEMINS DES IMAGES DE PART DE CAMEMBERT QUE POSSEDE
		 * 			LE JOUEUR
		 * 
		 */
		String[] listeImage = {"","","",""};
		Couleur[] listeCouleur = {Couleur.BLEU,Couleur.ROUGE,Couleur.ORANGE,Couleur.VERT};
		int indice = 0;
		// CHECK POUR CHAQUE COULEUR DE PART SI LE JOUEUR CONTIENT LA PART OU PAS
		// SI IL POSSEDE LA PART ALORS ON AJOUTE LE CHEMIN DE L IMAGE DE LA PART
		for (Couleur couleur : listeCouleur) {
			if (joueur.getCamembert().ContientPart(couleur)) {
				listeImage[indice] = "images/joueur"+numJoueur+"-"+couleur+".png";
			}else {
				listeImage[indice] = null;
			}
			indice++;
		}
		return listeImage;
	}
	
	public static BufferedImage imageCamembertWithPart(String pathcam, String partBleu, String partRouge, String partOrange
													,String partvert) throws IOException{ 


		/*
		 *  ROLE :CETTE FONCTION PERMET DE DESSINER LES PART DE CAMEMBERT SUR LE CAMEMBERT
		 * 		  CE QUI PERMET D AVOIR UNE IMAGE AVEC LES PART SUR LES CAMEMEBERT
		 * 
		 * PARAM : CHEMIN DE L IMAGE DU CAMEMBERT
		 * 		   CHEMIN DE L IMAGE DE LA PART BLEU
		 *    	   CHEMIN DE L IMAGE DE LA PART ROUGE
		 *     	   CHEMIN DE L IMAGE DE LA PART PRANGE
		 *         CHEMIN DE L IMAGE DE LA PART VERT
		 *         
		 * RETURN : RETOURNE UNE IMAGE AVEC LE CAMEMBERT ET CES PART
		 * 
		 */
		Image imagetemp1 = ImageIO.read(new File(pathcam));
		BufferedImage image1 = (BufferedImage) imagetemp1;
		Graphics2D g2d = image1.createGraphics(); 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY); 

		// AJOUTE LA PART BLEU SUR LE CAMEMEBERT
		if (partBleu !=null) {
			Image imagetemp = ImageIO.read(new File(partBleu));
			BufferedImage image = (BufferedImage) imagetemp;
			g2d.drawImage(image, 7, 2, 78,78, null); 
		}
		// AJOUTE LA PART ROUGE SUR LE CAMEMEBERT
		if (partRouge !=null) {
			Image imagetemp = ImageIO.read(new File(partRouge));
			BufferedImage image = (BufferedImage) imagetemp;
			g2d.drawImage(image, 85, 2, 78,78, null); 
		}
		// AJOUTE LA PART VERTE SUR LE CAMEMEBERT
		if (partvert !=null) {
			Image imagetemp = ImageIO.read(new File(partvert));
			BufferedImage image = (BufferedImage) imagetemp;
			g2d.drawImage(image, 7, 80, 78,78, null); 
		}
		// AJOUTE LA PART ORANGE SUR LE CAMEMEBERT
		if (partOrange !=null) {
			Image imagetemp = ImageIO.read(new File(partOrange));
			BufferedImage image = (BufferedImage) imagetemp;
			g2d.drawImage(image, 85, 80, 78,78, null); 
		}

		g2d.dispose(); 
		return image1 ; 
	}

	private void creationplateau() {

		
		/*
		 * LE PLATEAU EST UN BORDER LAYOUT (NORD,SUD,EST,OUEST,CENTRE)
		 * 
		 * LES CASES SE TROUVE DANS LES PARTIES NORD,SUD,EST,OUEST
		 * 
		 * LES CASES SUPER CAMEMBERT DES ANGLES SE TROUVE DANS LES PARTIE NORD ET SUD
		 * 
		 * LA PARTIE CENTRE SERA RESERVER AU MESSAGE ET QUESTION POUR LES JOUEURS
		 * 
		 * LA CREATION DES CASES SE FAIT DANS LE SENS DES AIGUILLES D UNE MONTRE
		 * 
		 * HAUT => DROITE => BAS => GAUCHE
		 */
		
		///////////////////////////////////////////
		//   			 HAUT   	            //
		//////////////////////////////////////////
		
		// PARAMETRE DE LA PARTIE HAUT
		JPanel haut = new JPanel( new GridLayout(1, 7));
		haut.setPreferredSize( new Dimension(0,(plateauPanel.getHeight()/7)));
		haut.setName("en haut");
		
		//PREMIER CASE SUPER CAMEMBERT DU HAUT
		JPanel caseun = new JPanel( );
		caseun.setBackground(Color.RED);
		caseun.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
		haut.add(caseun);
		
		// CREATION DES CASES "NORMALE"
		Color serieCouleurHaut[] = {Color.GREEN, Color.ORANGE,Color.BLUE, Color.RED,Color.BLACK};
		for (Color couleur : serieCouleurHaut) {
			JPanel square = new JPanel();
			square.setBackground(couleur);
			
			haut.add(square);
		}
		
		// DERNIERE CASE SUPER CAMEMBERT DU HAUT
		JPanel lastCase = new JPanel( );
		lastCase.setBackground(Color.GREEN);
		lastCase.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
		haut.add(lastCase);
		plateauPanel.add(haut, BorderLayout.NORTH);
		
		// AJOUT DES COORDONER DES CASES DU HAUT POUR FAIRE LA TRANSISTION ENTRE LE NUMERO DE CASE DU JOUEUR ET
		// LES COORDONEES DANS L IHM
		for (int i = 1; i < 7; i++) {
			int[] coordonnee = {0,i};
			numcaseToindicePanel.add(coordonnee);
		}
		
		///////////////////////////////////////////
		//   			 DROITE   		        //
		//////////////////////////////////////////
		
		// PARAMETRE DE LA PARTIE DROITE
		JPanel droite = new JPanel( new GridLayout(5, 1));
		droite.setPreferredSize( new Dimension((plateauPanel.getWidth()/7),0));
		droite.setName("Droite");
		
		
		// CREATION DES CASES "NORMALE"
		Color serieCouleurdroite[] = { Color.ORANGE,Color.BLUE, Color.RED,Color.BLACK,Color.GREEN};
		for (Color couleur : serieCouleurdroite) {
			JPanel square = new JPanel( );
			square.setBackground(couleur);
			droite.add(square);
		}
		plateauPanel.add(droite, BorderLayout.EAST);
		
		// AJOUT DES COORDONER DES CASES DU HAUT POUR FAIRE LA TRANSISTION ENTRE LE NUMERO DE CASE DU JOUEUR ET
		// LES COORDONEES DANS L IHM
		for (int i = 0; i < 5; i++) {
			int[] coordonnee = {1,i};
			numcaseToindicePanel.add( coordonnee);
		}
		
		
		///////////////////////////////////////////
		//   			 BAS   		            //
		//////////////////////////////////////////
		
		// PARAMETRE DE LA PARTIE BAS
		JPanel bas = new JPanel( new GridLayout(1, 7));
		bas.setPreferredSize( new Dimension(0,(plateauPanel.getHeight()/7)));
		bas.setName("BAS");
		
		//PREMIER CASE SUPER CAMEMBERT DU BAS
		JPanel basun = new JPanel(  );
		basun.setBackground(Color.BLUE);
		basun.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
		bas.add(basun);
		
		// CREATION DES CASES "NORMALE"
		Color serieCouleurBas[] = { Color.ORANGE,Color.GREEN,Color.BLACK,Color.RED,Color.BLUE };
		for (Color couleur : serieCouleurBas) {
			JPanel square = new JPanel(  );
			square.setBackground(couleur);
			bas.add(square);
		}
		
		//DERNIERE CASE SUPER CAMEMBERT DU BAS
		JPanel lastBas = new JPanel( );
		lastBas.setBackground(Color.ORANGE);
		lastBas.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
		bas.add(lastBas);
		plateauPanel.add(bas, BorderLayout.SOUTH);
		
		
		// AJOUT DES COORDONER DES CASES DU HAUT POUR FAIRE LA TRANSISTION ENTRE LE NUMERO DE CASE DU JOUEUR ET
		// LES COORDONEES DANS L IHM
		for (int i = 6; i >= 0; i--) {
			int[] coordonnee = {2,i};
			numcaseToindicePanel.add( coordonnee);
		}
		
		
		///////////////////////////////////////////
		//   			 GAUCHE   		        //
		//////////////////////////////////////////
		
		// PARAMETRE DE LA PARTIE GAUCHE
		JPanel gauche = new JPanel( new GridLayout(5, 1));
		gauche.setPreferredSize( new Dimension((plateauPanel.getWidth()/7),0));
		gauche.setName("gauche");
		
		// CREATION DES CASES "NORMALE"
		Color serieCouleurGauche[] = { Color.BLUE,Color.ORANGE, Color.GREEN,Color.BLACK,Color.RED};
		for (Color couleur : serieCouleurGauche) {
			JPanel square = new JPanel( );
			square.setBackground(couleur);
			gauche.add(square);
		}
		plateauPanel.add(gauche, BorderLayout.WEST);
		
		// AJOUT DES COORDONER DES CASES DU HAUT POUR FAIRE LA TRANSISTION ENTRE LE NUMERO DE CASE DU JOUEUR ET
		// LES COORDONEES DANS L IHM
		for (int i = 4; i >= 0 ; i--) {
			int[] coordonnee = {3,i};
			numcaseToindicePanel.add( coordonnee);
		}
		
		// AJOUT DES COORDONNEES DE LA DERNIERE CASE DU PLATEAU QUI SE TROUVE DANS LA PARTIE HAUT
		int[] coordonnee = {0,0};
		numcaseToindicePanel.add( coordonnee);
	}

	
	private void creationPanelQuestion(Question question){
		
		///////////////////////////////////////////
		//   			 QUESTION 	            //
		//////////////////////////////////////////
		
		// AFFICHAGE DE L IMAGE DU TYPE DE LA QUESTION ET CHANGEMENT DE LA COULEUR DE FOND
		questionPanel = new JPanel(new GridLayout(3,0));
		Couleur couleur = question.getCouleur();
		JLabel imageTypeQuestion = new JLabel();
		switch (couleur) {
		case ROUGE:
			//questionPanel.setBackground(Color.red);
			imageTypeQuestion = new JLabel( new ImageIcon("images/innovation.png"));
			break;
		case BLEU:
			//questionPanel.setBackground(Color.blue);
			imageTypeQuestion = new JLabel(new ImageIcon("images/lesaviezvous.png"));
			break;
		case VERT:
			//questionPanel.setBackground(Color.green);
			imageTypeQuestion = new JLabel( new ImageIcon("images/cpe.png"));
			break;
		case ORANGE:
			//questionPanel.setBackground(Color.orange);
			imageTypeQuestion = new JLabel(new ImageIcon("images/blague.png") );
			break;
		default:
			break;
		}		
		this.questionPanel.add(imageTypeQuestion);
		
		//QUESTION 
		JLabel questionLabel = new JLabel( question.getQuestion() );
		this.questionPanel.add(questionLabel);
		
		// REPONSES
		String[] reponses = question.getChoix();
		ButtonGroup groupButton = new ButtonGroup();
		JPanel reponsePanel = new JPanel(new GridLayout(5,0));
		
		// R�ponse 1
		JRadioButton rep1 = new JRadioButton(reponses[0]);
		//rep1.setBackground(questionPanel.getBackground());
		rep1.setActionCommand(rep1.getText());
		groupButton.add(rep1);
		reponsePanel.add(rep1);
		
		//R�ponse 2
		JRadioButton rep2 = new JRadioButton(reponses[1]);
		rep2.setBackground(questionPanel.getBackground());
		rep2.setActionCommand(rep2.getText());
		groupButton.add(rep2);
		reponsePanel.add(rep2);
		
		//R�ponse 3
		JRadioButton rep3 = new JRadioButton(reponses[2]);
		rep3.setBackground(questionPanel.getBackground());
		rep3.setActionCommand(rep3.getText());
		groupButton.add(rep3);
		reponsePanel.add(rep3);
		
		//R�ponse 4
		JRadioButton rep4 = new JRadioButton(reponses[3]);
		rep4.setBackground(questionPanel.getBackground());
		rep4.setActionCommand(rep4.getText());
		groupButton.add(rep4);
		reponsePanel.add(rep4);
		
		// bouton valider reponse
		JButton valider= new JButton("Valider la reponse");
		valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (groupButton.getSelection() !=null) {
					String reponse =groupButton.getSelection().getActionCommand();
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
		//   			 LANCER LES DES	         //
		//////////////////////////////////////////
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
		
		desPanel.add(btnLancerLesDes);
		desPanel.add(categorie);
	}

	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object info) {
		
		//FIN DU GAME
		if ( (Boolean) ((ArrayList<Object>)info).get(0))  { 
			
			this.plateauPanel.removeAll();
			this.questionPanel.removeAll();
			this.camembertPanel.removeAll();
			ArrayList<Joueur> listeDesJoueur = ((ArrayList<Joueur>) ((ArrayList<Object>) info).get(1));
			this.creationPanelCamembert(listeDesJoueur);
			
			this.creationplateau();
			JLabel message = new JLabel( (String) ( (ArrayList<Object>) info).get(2) );
			this.plateauPanel.add(message, BorderLayout.CENTER);
			
		}else { // SI CEST PAS LA FIN DU GAME
			this.camembertPanel.removeAll();
			this.desPanel.removeAll();
			this.plateauPanel.removeAll();
			this.questionPanel.removeAll();

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

			plateauPanel.setLayout(new BorderLayout());
			this.creationplateau();
		

			///////////////////////////////////////////
			//   			 MESSAGE     		      //
			//////////////////////////////////////////
			if ( ((ArrayList<Object>) info).size() >4 && ((ArrayList<Object>) info).get(4) != null) { // si on a une question
				creationPanelQuestion( (Question) ((ArrayList<Object>) info).get(4));
			}else{ // si on est sur une case myst�re 
				JLabel message = new JLabel( (String) ( (ArrayList<Object>) info).get(2) );
				this.plateauPanel.add(message, BorderLayout.CENTER);
			}

			///////////////////////////////////////////
			//   			 PLACEMENT PION	        //
			//////////////////////////////////////////


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
			
			
			
		
			
			System.out.println(border.getX()+" "+border.getY());
			System.out.println(componentBorder.getX()+" "+componentBorder.getY());
			
			
			
			
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
		}
			this.revalidate();
	}


	/*public void paintCompomnent(Graphics g) {
		
		super.paintComponents(g);
		
		g.setColor(Color.RED);
		g.fillRect(x, 500, 500,500);
		tm.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		x = x+2;
		System.out.println("test");
		plateauPanel.repaint();
		
	}*/
	
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