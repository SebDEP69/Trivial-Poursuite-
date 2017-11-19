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
import javax.swing.border.Border;

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
	JPanel trivialBoard, desPanel, plateauPanel,camembertPanel, titlePanel, questionPanel,selectPersoUn,selectPersoDeux;
	
	ArrayList<int[]> numcaseToindicePanel;
	Dimension boardSize;
	private  TrivialControler trivialControler;
	JLabel imgPersoUn,imgPersoDeux;
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
		trivialBoard.add(desPanel,BorderLayout.WEST);

		//DROITE
		camembertPanel = new JPanel();
		camembertPanel.setLayout(new GridLayout(3,0));
		
		trivialBoard.add(camembertPanel, BorderLayout.EAST);


		// CENTRE
		questionPanel = new JPanel();
		questionPanel.setLayout(new GridLayout(2,0));		

		//affiche le formulaire de creation de joueur
		imgPersoUn= new JLabel(new ImageIcon("images/macron.png"));
		imgPersoUn.setName("macron");
		imgPersoDeux= new JLabel(new ImageIcon("images/macron.png"));
		imgPersoDeux.setName("macron");
		formulaireCreationJoueur();
	
	}
	
	private void changePerso(JPanel panelJoueur, JLabel imgPerso) {
		
		JPanel panelName = (JPanel) panelJoueur.getComponent(0);
		JPanel panelBouton  = (JPanel) panelJoueur.getComponent(2);
		panelJoueur.removeAll();
		panelJoueur.add(panelName,BorderLayout.NORTH);
		panelJoueur.add(imgPerso, BorderLayout.CENTER);
		panelJoueur.add(panelBouton, BorderLayout.SOUTH);
		panelJoueur.repaint();
		pack();
	}
	
	private void formulaireCreationJoueur() {
		///////////////////////////////////////////
		//   	 FORMULAIRE CREATION JOUEUR	      //
		//////////////////////////////////////////
		// CENTRE
		plateauPanel = new JPanel(new BorderLayout());
		
		JPanel tekkenVue = new JPanel(new GridLayout(0, 2));
			
		
		//###### JOUEUR 1
		//JPanel panelJoueurun = new JPanel(new GridLayout(2, 0));
		JPanel panelJoueurun = new JPanel(new BorderLayout());
		
		//nom joueur
		JPanel panelNomJoueurUn = new JPanel(new GridLayout(0,2));		
		JLabel labelNomJUn = new JLabel("Nom de joueur");
		JTextField nomJoueurUn = new JTextField();
		panelNomJoueurUn.add(labelNomJUn);
		panelNomJoueurUn.add(nomJoueurUn);
		panelJoueurun.add(panelNomJoueurUn, BorderLayout.NORTH);
		
		// image principale
		panelJoueurun.add(imgPersoUn, BorderLayout.CENTER);
		
		// partie select perso
		selectPersoUn = new JPanel(new GridLayout(0, 4));
		JButton btnMacron = new ButtonJolie(new ImageIcon("images/macron.png"));
		JButton btnMerkel= new JButton(new ImageIcon("images/merkel.png"));
		JButton btnPoutine = new JButton(new ImageIcon("images/poutine.png"));
		JButton btnTrump = new JButton(new ImageIcon("images/trump.png"));
		
		
		btnMacron.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgPersoUn = new JLabel(new ImageIcon("images/macron.png"));
				imgPersoUn.setName("macron");
				changePerso(panelJoueurun,imgPersoUn);				
			}
		});
		btnMerkel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgPersoUn = new JLabel(new ImageIcon("images/merkel.png"));
				imgPersoUn.setName("merkel");
				changePerso(panelJoueurun,imgPersoUn);					
			}
		});
		btnPoutine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgPersoUn = new JLabel(new ImageIcon("images/poutine.png"));
				imgPersoUn.setName("poutine");
				changePerso(panelJoueurun,imgPersoUn);					
			}
		});
		btnTrump.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgPersoUn = new JLabel(new ImageIcon("images/trump.png"));
				imgPersoUn.setName("trump");
				changePerso(panelJoueurun,imgPersoUn);					
			}
		});
		selectPersoUn.add(btnMacron);
		selectPersoUn.add(btnMerkel);
		selectPersoUn.add(btnPoutine);
		selectPersoUn.add(btnTrump);
		selectPersoUn.setPreferredSize(new Dimension(0, 100));
		panelJoueurun.add(selectPersoUn, BorderLayout.SOUTH);
		
			
		
		//###### JOUEUR 2
		JPanel panelJoueurdeux = new JPanel(new BorderLayout());
		//nom joueur
		JPanel panelNomJoueurDeux = new JPanel(new GridLayout(0,2));		
		JLabel labelNomJDeux = new JLabel("Nom de joueur");
		JTextField nomJoueurDeux = new JTextField();
		panelNomJoueurDeux.add(labelNomJDeux);
		panelNomJoueurDeux.add(nomJoueurDeux);
		panelJoueurdeux.add(panelNomJoueurDeux, BorderLayout.NORTH);
		
		// image principale
		panelJoueurdeux.add(imgPersoDeux, BorderLayout.CENTER);		
		// partie select perso
		selectPersoDeux = new JPanel(new GridLayout(0, 4));

		JButton btnMacrondeux = new JButton(new ImageIcon("images/macron.png"));
		JButton btnMerkeldeux= new JButton(new ImageIcon("images/merkel.png"));
		JButton btnPoutinedeux = new JButton(new ImageIcon("images/poutine.png"));
		JButton btnTrumpdeux = new JButton(new ImageIcon("images/trump.png"));

		btnMacrondeux.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgPersoDeux = new JLabel(new ImageIcon("images/macron.png"));
				imgPersoDeux.setName("macron");
				changePerso(panelJoueurdeux,imgPersoDeux);				
			}
		});
		btnMerkeldeux.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgPersoDeux = new JLabel(new ImageIcon("images/merkel.png"));
				imgPersoDeux.setName("merkel");
				changePerso(panelJoueurdeux,imgPersoDeux);					
			}
		});
		btnPoutinedeux.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgPersoDeux = new JLabel(new ImageIcon("images/poutine.png"));
				imgPersoDeux.setName("poutine");
				changePerso(panelJoueurdeux,imgPersoDeux);					
			}
		});
		btnTrumpdeux.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgPersoDeux = new JLabel(new ImageIcon("images/trump.png"));
				imgPersoDeux.setName("trump");
				changePerso(panelJoueurdeux,imgPersoDeux);					
			}
		});
		selectPersoDeux.add(btnMacrondeux);
		selectPersoDeux.add(btnMerkeldeux);
		selectPersoDeux.add(btnPoutinedeux);
		selectPersoDeux.add(btnTrumpdeux);
		selectPersoDeux.setPreferredSize(new Dimension(0, 100));
		panelJoueurdeux.add(selectPersoDeux, BorderLayout.SOUTH);	
		
		
		tekkenVue.add(panelJoueurun);
		tekkenVue.add(panelJoueurdeux);		
		/*Dimension dimensionJTF = new Dimension(100,30);
		CouleurPion CouleurduPion[] = {CouleurPion.MACRON, CouleurPion.MERKEL,CouleurPion.POUTINE, CouleurPion.TRUMP};
		//###### JOUEUR 1
		JLabel labelnomjoueurun = new JLabel("Nom joueur 1 :");
		//JLabel labelcouleurjoueurun = new JLabel("Couleur joueur 1 :");
		JTextField textNomJoueurun = new JTextField();
		textNomJoueurun.setPreferredSize(dimensionJTF);
		JComboBox<CouleurPion> couleurPionJoueurun = new JComboBox<CouleurPion>(CouleurduPion);

		//###### JOUEUR 2
		JLabel labelnomjoueurdeux = new JLabel("Nom joueur 2 :");
		//JLabel labelcouleurjoueurdeux = new JLabel("Couleur joueur 2 :");
		JTextField textNomJoueurdeux = new JTextField();
		textNomJoueurdeux.setPreferredSize(dimensionJTF);
		JComboBox<CouleurPion> couleurPionJoueurdeux = new JComboBox<CouleurPion>(CouleurduPion);

		
		
		// nom des joueurs
		plateauPanel.add(labelnomjoueurun);
		//plateauPanel.add(textNomJoueurun);
		plateauPanel.add(labelnomjoueurdeux);
		//plateauPanel.add(textNomJoueurdeux);
		 */
		// couleur des joueurs
		/*plateauPanel.add(labelcouleurjoueurun);
		plateauPanel.add(couleurPionJoueurun);
		plateauPanel.add(labelcouleurjoueurdeux);
		plateauPanel.add(couleurPionJoueurdeux);
		 */
		
		// Bouton création des joueurs
		JPanel panelButonLancer = new JPanel();
		panelButonLancer.setPreferredSize(new Dimension(0, 100));
		ButtonJolie btnlancer = new ButtonJolie("Lancer");
		
		btnlancer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nomjoueurun = nomJoueurUn.getText();
				String nomjoueurdeux = nomJoueurDeux.getText();
				CouleurPion couleurjoueur[]= {CouleurPion.MACRON,CouleurPion.MACRON};
				String liString[] = {""+imgPersoUn.getText(),""+imgPersoDeux.getText()};
				for (int indice = 0; indice < liString.length; indice++) {
					switch (liString[indice]) {
					case "macron":
						couleurjoueur[indice] = CouleurPion.MACRON;
						break;
					case "merkel":
						couleurjoueur[indice] = CouleurPion.MERKEL;
						break;
					case "poutine":
						couleurjoueur[indice] = CouleurPion.POUTINE;
						break;
					case "trump":
						couleurjoueur[indice] = CouleurPion.TRUMP;
						break;
					default:
						break;
					}
				}
				trivialControler.creationJoueur(nomjoueurun,nomjoueurdeux,couleurjoueur[0],couleurjoueur[1]);
				//trivialControler.creationJoueur("toto","titi",CouleurPion.MACRON,CouleurPion.MACRON);
			}
		});
		panelButonLancer.add(btnlancer);		
		plateauPanel.add(tekkenVue,BorderLayout.CENTER);
		plateauPanel.add(panelButonLancer,BorderLayout.SOUTH);
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
		
		camembertPanel.setPreferredSize( new Dimension(200,0));
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
		
		// Réponse 1
		JRadioButton rep1 = new JRadioButton(reponses[0]);
		//rep1.setBackground(questionPanel.getBackground());
		rep1.setActionCommand(rep1.getText());
		groupButton.add(rep1);
		reponsePanel.add(rep1);
		
		//Réponse 2
		JRadioButton rep2 = new JRadioButton(reponses[1]);
		rep2.setBackground(questionPanel.getBackground());
		rep2.setActionCommand(rep2.getText());
		groupButton.add(rep2);
		reponsePanel.add(rep2);
		
		//Réponse 3
		JRadioButton rep3 = new JRadioButton(reponses[2]);
		rep3.setBackground(questionPanel.getBackground());
		rep3.setActionCommand(rep3.getText());
		groupButton.add(rep3);
		reponsePanel.add(rep3);
		
		//Réponse 4
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
		
		desPanel.setPreferredSize( new Dimension(250,0));
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
			//  affiche le lancer de dé + QUESTION   //
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
			}else{ // si on est sur une case mystère 
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