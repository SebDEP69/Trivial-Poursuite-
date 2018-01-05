package vue;



import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import Controler.ControleurAccueil;
import Model.ButtonJolie;
import Model.Couleur;
import Model.Partie; 



@SuppressWarnings("serial")
public class Menu extends JFrame implements  Observer {

	private JPanel panelMenu,panelGeneral,panelPersonalisation,panelHistorique,panelDetailPartie;
	private ControleurAccueil controleurAccueil;
	private JLayeredPane layeredPane;
	private static String policeEcriture = "Calibri";
	private int numeroDePage;
	private static Color bleuColor = new Color(29, 174, 255);
	private static Color rougeColor = new Color(206, 43, 46);
	private static Color orangecolor = new Color(254, 169, 38);
	private static Color vertColor = new Color(115, 201, 114);
	public Menu(String name, ControleurAccueil controleur) {
		super(name);

		this.controleurAccueil = controleur;
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Toolkit leKit = this.getToolkit();
		Dimension boardSize = leKit.getScreenSize();
		setSize(boardSize.width , boardSize.height);
		//this.revalidate();

		layeredPane = new JLayeredPane();
		this.setContentPane(layeredPane);
		layeredPane.setPreferredSize(boardSize);


		//layeredPane.setSize(boardSize.width , boardSize.height-150);

		panelGeneral = new JPanel(new BorderLayout());
		panelGeneral.setBounds(0, 0, getWidth(), getHeight());

		//panelGeneral.setBackground(Color.red);

		/*Toolkit tk = Toolkit.getDefaultToolkit();
		 Insets insets = tk.getScreenInsets(getGraphicsConfiguration());

		System.out.println(insets.left +" "+insets.right +" "+insets.top +" "+insets.bottom);*/
		panelMenu = new JPanel(new BorderLayout());
		panelMenu.setBounds(0, 0, getWidth(), getHeight());

		int decalage = 60;
		panelPersonalisation = new JPanel(new BorderLayout());
		panelPersonalisation.setBounds(0, decalage, getWidth(), getHeight()-decalage);
		panelPersonalisation.setVisible(false);

		panelHistorique = new JPanel(new BorderLayout());
		panelHistorique.setBounds(0, decalage, getWidth(), getHeight()-decalage-60);
		panelHistorique.setVisible(false);

		panelDetailPartie = new JPanel(new BorderLayout());
		panelDetailPartie.setBounds(0, decalage, getWidth(), getHeight()-decalage-60);
		panelDetailPartie.setVisible(false);

		layeredPane.add(panelGeneral, new Integer(0));
		layeredPane.add(panelMenu, new Integer(1));
		layeredPane.add(panelPersonalisation,new Integer(2));
		layeredPane.add(panelHistorique,new Integer(3));
		layeredPane.add(panelDetailPartie,new Integer(4));

	}



	private void afficherMenuPrincipal() {
		panelMenu.setLayout( new BorderLayout() );
		JPanel sud = new JPanel();
		sud.setPreferredSize(new Dimension(0, 100));
		panelMenu.add(sud, BorderLayout.SOUTH);

		JPanel nord = new JPanel();
		panelMenu.add(nord, BorderLayout.NORTH);


		JPanel centre = new JPanel();
		centre.setLayout(new GridBagLayout());
		panelMenu.add(centre, BorderLayout.CENTER);
		//centre.setBackground(Color.ORANGE);

		// CENTRE HAUT // 
		JPanel texte1 = new JPanel();
		texte1.setPreferredSize(new Dimension(10,90));
		centre.add((JPanel) texte1);
		JLabel txt1 = new JLabel("Bienvenue sur le jeu TRIVIAL PURSUIT, amusez-vous bien !");
		txt1.setHorizontalTextPosition(JLabel.CENTER); 
		txt1.setFont(new Font("Calibri",Font.PLAIN,27));
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
		text.setFont(new Font("Calibri",Font.ITALIC,15));
		square3.add(text);
		square3.setVisible(true);

		//CENTRE2
		JPanel bouton1 = new JPanel();
		centre.add((JPanel) bouton1);
		ButtonJolie btn1 = new ButtonJolie("<Html><center>Nouvelle partie<Html>");
		//JButton btn1 = new JButton("<Html><center>Debut de la partie<Html>");

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controleurAccueil.lancerPartie();
				setVisible(false);
				dispose();
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

		JPanel bouton3 = new JPanel();
		centre.add((JPanel) bouton3);
		ButtonJolie btn3 = new ButtonJolie("<Html><center>Regles du jeu<Html>");


		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controleurAccueil.afficherRegle();
			}
		});

		btn3.setPreferredSize(new Dimension(180, 100));
		bouton3.add(btn3);
		bouton3.setVisible(true);
		GridBagConstraints f = new GridBagConstraints();   
		f.gridx = 0;                                       
		f.gridy = 3;         
		f.insets = new Insets(-20,100,-30,100);
		f.fill = GridBagConstraints.HORIZONTAL;
		centre.add(btn3, f);


		/*JPanel bouton4 = new JPanel();
		centre.add((JPanel) bouton4);
		ButtonJolie btn4 = new ButtonJolie("<Html><center>Score de la partie<Html>");
		btn4.setPreferredSize(new Dimension(180, 100));

		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controleurAccueil.afficherScorePartie();

			}
		});


		bouton4.add(btn4);
		bouton4.setVisible(true);
		GridBagConstraints g = new GridBagConstraints();   
		g.gridx = 2;                                       
		g.gridy = 1;      
		g.insets = new Insets(-20,100,-30,100);
		g.fill = GridBagConstraints.HORIZONTAL;
		centre.add(btn4, g);*/

		JPanel bouton5 = new JPanel();
		centre.add((JPanel) bouton5);
		ButtonJolie btn5 = new ButtonJolie("<Html><center>Ajouter des questions <Html>");
		btn5.setPreferredSize(new Dimension(180, 100));
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controleurAccueil.afficherPersonalisation();
			}
		});
		bouton5.add(btn5);
		bouton5.setVisible(true);
		GridBagConstraints h = new GridBagConstraints();   
		h.gridx = 2;                                       
		h.gridy = 2;
		h.insets = new Insets(-20,100,-30,100);     
		h.fill = GridBagConstraints.HORIZONTAL;
		centre.add(btn5, h);

		JPanel bouton6 = new JPanel();
		centre.add((JPanel) bouton6);
		ButtonJolie btn6 = new ButtonJolie("<Html><center>Historique des parties<Html>");
		btn6.setPreferredSize(new Dimension(200, 250));
		btn6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				controleurAccueil.afficherHistoriqueDesScores();

			}
		});
		bouton6.add(btn6);
		bouton6.setVisible(true);
		GridBagConstraints i = new GridBagConstraints();   
		i.gridx = 3;                                       
		i.gridy = 2;          
		i.insets = new Insets(-20,100,-30,100);
		i.fill = GridBagConstraints.HORIZONTAL;
		centre.add(btn6, i);


		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}

	private void afficheBoutonRetour(String choix){

		panelGeneral.removeAll();
		ButtonJolie btnRetourMenu = new ButtonJolie("Retour");
		btnRetourMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (choix) {
				case "Menu":
					controleurAccueil.afficherMenu();
					panelMenu.setVisible(true);
					panelHistorique.setVisible(false);
					panelPersonalisation.setVisible(false);
					break;
				case "Historique":
					controleurAccueil.afficherHistoriqueDesScores();
					panelHistorique.setVisible(true);
					panelDetailPartie.setVisible(false);
					break;
				default:
					break;
				}


			}
		});
		btnRetourMenu.setPreferredSize(new Dimension(150, 50));
		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelBtn.add(btnRetourMenu);
		panelGeneral.add(panelBtn,BorderLayout.NORTH);
	}
	private void personalisation() {



		panelPersonalisation.setLayout(new BorderLayout());

		this.afficheBoutonRetour("Menu");

		JPanel all = new JPanel(new GridLayout(4,0));
		panelPersonalisation.add(all,BorderLayout.CENTER);

		// Type question
		//JPanel panelTypeQuestion = new JPanel(new GridLayout(3, 3));
		JPanel panelTypeQuestion = new JPanel(new GridBagLayout());
		JPanel panelQuestion = new JPanel(new GridBagLayout());
		JPanel panelreponse = new JPanel(new GridBagLayout());
		JPanel panelDescription = new JPanel(new GridLayout(0, 2));
		JPanel panelValider = new JPanel(new FlowLayout());
		ButtonJolie btnReset= new ButtonJolie("Remise à zéro");
		ButtonJolie btnvalider = new ButtonJolie("Valider");
		btnvalider.setFont(new Font(policeEcriture,Font.BOLD,27));

		String[] elements = new String[]{"Blague", "CPE", "Innovation", "Le saviez-vous?"};
		JComboBox<String> typeQuestion = new JComboBox<String>(elements);
		JLabel selectype = new JLabel("Veuillez selectionner une catégorie de question");
		selectype.setFont(new Font(policeEcriture,Font.PLAIN,27));


		typeQuestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				panelQuestion.setVisible(true);
				btnReset.setVisible(true);
			}
		});
		GridBagConstraints c = new GridBagConstraints();
		//c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;

		panelTypeQuestion.add(selectype,c);
		c.gridy = 1;
		panelTypeQuestion.add(typeQuestion,c);

		// Panel question

		//panelQuestion.setBackground(Color.blue);

		JTextField question = new JTextField();
		JLabel labelQuestion = new JLabel("Entrez une question et terminer par un \"?\"");
		labelQuestion.setFont(new Font(policeEcriture,Font.PLAIN,27));
		question.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				String text = textField.getText();
				if (text.length()>0) {
					String lastCaract =text.substring((text.length() - 1));
					if (lastCaract.equals("?")) {
						panelreponse.setVisible(true);
					}else{
						panelDescription.setVisible(false);
						panelreponse.setVisible(false);
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		//L'objet servant à positionner les composants

		c.gridx = 0;
		c.gridy = 0;
		panelQuestion.add(labelQuestion,c);
		question.setPreferredSize(new Dimension(300, 50));
		c.gridy = 1;
		panelQuestion.add(question,c);

		// Panel reponse


		JLabel indicationReponse = new JLabel("Entrez les réponses et cochez la bonne réponse");
		indicationReponse.setFont(new Font(policeEcriture,Font.PLAIN,27));
		c.gridx = 0;
		c.gridy = 0;
		panelreponse.add(indicationReponse,c);
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();

		GridBagConstraints gbc = new GridBagConstraints();
		JTextField reponse1 = new JTextField();
		JTextField reponse2 = new JTextField();
		JTextField reponse3 = new JTextField();
		JTextField reponse4 = new JTextField();
		ArrayList<JTextField> listerep= new ArrayList<JTextField>();
		listerep.add(reponse1);
		listerep.add(reponse2);
		listerep.add(reponse3);
		listerep.add(reponse4);





		KeyListener keyListenRep = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				//JTextField textField = (JTextField) e.getSource();
				//String text = textField.getText();
				String text1 = reponse1.getText();
				String text2 = reponse2.getText();
				String text3 = reponse3.getText();
				String text4 = reponse4.getText();
				if (!text1.equals("") &&!text2.equals("") &&!text3.equals("") &&!text4.equals("")) {
					panelDescription.setVisible(true);
				}else{
					panelDescription.setVisible(false);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

		};
		reponse1.addKeyListener(keyListenRep);
		reponse2.addKeyListener(keyListenRep);
		reponse3.addKeyListener(keyListenRep);
		reponse4.addKeyListener(keyListenRep);
		int i=1;
		JPanel panelBtnReponse = new JPanel(new GridBagLayout());
		for (JTextField reponse : listerep) {

			reponse.setPreferredSize(new Dimension(300, 40));

			JRadioButton btnreponse = new JRadioButton("reponse"+i); 
			btnreponse.setFont(new Font(policeEcriture,Font.PLAIN,27));
			btnreponse.setActionCommand(""+i);
			if (i==1) {
				//btnreponse.setSelected(true);
			}
			group.add(btnreponse);
			gbc.gridy = i+1;
			gbc.gridx = 0;
			panelBtnReponse.add(btnreponse,gbc);
			gbc.gridx = 1;
			panelBtnReponse.add(reponse,gbc);
			i++;
		}

		c.gridy = 1;
		panelreponse.add(panelBtnReponse,c);

		// panel descritpion

		JLabel labelDescript = new JLabel("Description");
		labelDescript.setFont(new Font(policeEcriture,Font.PLAIN,27));
		JTextArea descritption = new JTextArea();

		descritption.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				btnvalider.setVisible(true);
			}

			@Override
			public void keyPressed(KeyEvent e) {}
		});
		panelDescription.add(labelDescript);
		panelDescription.add(descritption);

		panelQuestion.setVisible(false);
		panelreponse.setVisible(false);
		panelDescription.setVisible(false);

		all.add(panelTypeQuestion);
		all.add(panelQuestion);
		all.add(panelreponse);
		all.add(panelDescription);




		btnvalider.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				String Rtype= typeQuestion.getSelectedItem().toString();
				String Rquestion = question.getText();
				String[] listeReponses = {reponse1.getText(),reponse2.getText(),reponse3.getText(),reponse4.getText()};
				String numReponseJuste ="";
				String descript = descritption.getText();
				if (group.getSelection() !=null) {
					numReponseJuste = group.getSelection().getActionCommand();
				}
				controleurAccueil.enregisterQuestion(Rtype,Rquestion,listeReponses,numReponseJuste,descript);

			}
		});
		btnvalider.setVisible(false);


		btnReset.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				question.setText("");
				reponse1.setText("");
				reponse2.setText("");
				reponse3.setText("");
				reponse4.setText("");
				descritption.setText("");
				typeQuestion.setSelectedIndex(0);
				group.clearSelection();
				panelDescription.setVisible(false);
				panelQuestion.setVisible(false);
				panelreponse.setVisible(false);
				btnvalider.setVisible(false);
				btnReset.setVisible(false);
				//String numReponseJuste = group.getSelection().getActionCommand();

			}
		});
		btnReset.setFont(new Font(policeEcriture,Font.BOLD,27));
		btnReset.setVisible(false);

		panelValider.setPreferredSize(new Dimension(0, 100));
		panelValider.add(btnvalider, CENTER_ALIGNMENT);
		panelValider.add(btnReset, CENTER_ALIGNMENT);
		panelPersonalisation.add(panelValider,BorderLayout.SOUTH);
		panelValider.setVisible(true);
	}


	private String[] rechercheAllDate(ArrayList<Partie> listePartie){

		ArrayList<String> listeDateTmp = new ArrayList<String>();
		for (Partie partie : listePartie) {

			String date = partie.getDate();
			String tmpdate = date.substring(0, 8);		
			if (!listeDateTmp.contains(tmpdate)) {
				listeDateTmp.add(tmpdate);
			}
		}

		String[] elements = new String[listeDateTmp.size()+1];
		int i=1;
		elements[0]="TOUT";
		for (String date : listeDateTmp) {
			elements[i]=date;
			i++;
		}
		return elements;
	}


	private void afficherHistorique(ArrayList<Partie> listePartie,ArrayList<Partie> listePartieRecherche) {


		Color bleuColor = new Color(29, 174, 255);
		Color rougeColor = new Color(206, 43, 46);
		Color orangecolor = new Color(254, 169, 38);
		Color vertColor = new Color(115, 201, 114);
		numeroDePage =1;


		afficheBoutonRetour("Menu");
		panelHistorique.setLayout(new BorderLayout()); 
		CardLayout cardlayout = new CardLayout();
		JPanel layeredPaneHisto= new JPanel(cardlayout);

		//###################################
		// affichage recherche
		//###################################

		// recherche de toute les date

		JPanel panelRecherche = new JPanel();

		String[] alldate = rechercheAllDate(listePartie);

		JLabel labelDate = new JLabel("Date:");
		JComboBox<String> choseDate = new JComboBox<String>(alldate);

		JTextField pseudoAChercher = new JTextField();
		JLabel labelPseudo = new JLabel("Nom Joueur:");
		pseudoAChercher.setPreferredSize(new Dimension(300, 50));



		ButtonJolie btnRecherche = new ButtonJolie("Recherche");
		btnRecherche.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String date= choseDate.getSelectedItem().toString();
				String pseudo = pseudoAChercher.getText();
				controleurAccueil.rechercheHistorique(listePartie,date,pseudo);


			}
		});


		panelRecherche.add(labelDate);
		panelRecherche.add(choseDate);
		panelRecherche.add(labelPseudo);
		panelRecherche.add(pseudoAChercher);
		panelRecherche.add(btnRecherche);
		panelHistorique.add(panelRecherche, BorderLayout.NORTH);

		//###################################
		// partie affichage des Partie
		//###################################
		System.out.println(listePartieRecherche.size());
		Double nbPartie = (double) listePartieRecherche.size();

		KeyListener actionEntrer = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if (panelHistorique.isVisible() && e.getKeyCode()==KeyEvent.VK_ENTER) {
					System.out.println("test");
					btnRecherche.doClick();
				}
			}
		};
		pseudoAChercher.addKeyListener(actionEntrer);
		choseDate.addKeyListener(actionEntrer);

		if (nbPartie == 0) {
			//System.out.println("test");


			JLabel labelResultatRecherche = new JLabel("Aucun résultat trouver");
			labelResultatRecherche.setHorizontalAlignment(SwingConstants.CENTER);
			labelResultatRecherche.setVerticalAlignment(SwingConstants.CENTER);
			labelResultatRecherche.setFont(new Font(policeEcriture,Font.BOLD,50));
			labelResultatRecherche.setForeground(Color.RED);
			panelHistorique.add(labelResultatRecherche, BorderLayout.CENTER);
			panelHistorique.revalidate();
		}else{


			panelHistorique.add(layeredPaneHisto, BorderLayout.CENTER);

			int nbVue = (int) Math.ceil(nbPartie/15.0);
			int indicePartie = 0;
			for (int i = 0; i < nbVue; i++) {

				JPanel vue = new JPanel(new GridLayout(16,0));//  15 partie +1 pour l'intituler des colonnes			
				vue.setBounds(0, 0, getWidth(), getHeight()-120-60);
				layeredPaneHisto.add(vue);			

				// En tete
				JPanel panelPartie = new JPanel(new GridLayout(0, 6));
				JLabel date = new JLabel("DATE");
				JLabel joueur1 = new JLabel("Nom Joueur 1");
				JLabel scroreJ1 = new JLabel("Score Joueur 1");
				JLabel joueur2 = new JLabel("Nom Joueur 2");
				JLabel scroreJ2 = new JLabel("Score Joueur 2");
				Border borderligne= BorderFactory.createMatteBorder(0,2,2,2, Color.DARK_GRAY);
				Border bordercolonne= BorderFactory.createMatteBorder(0,0,0,2, Color.DARK_GRAY);
				String policeEcriture = "Calibri";
				int fontText = Font.BOLD;
				int tailleText = 27;
				panelPartie.add(date);
				panelPartie.add(joueur1);
				panelPartie.add(scroreJ1);
				panelPartie.add(scroreJ2);
				panelPartie.add(joueur2);
				panelPartie.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.DARK_GRAY));
				vue.add(panelPartie);

				// Bordure colonne
				date.setBorder(bordercolonne);
				joueur1.setBorder(bordercolonne);
				scroreJ1.setBorder(bordercolonne);
				joueur2.setBorder(bordercolonne);
				scroreJ2.setBorder(bordercolonne);

				//Style du texte
				date.setFont(new Font(policeEcriture,fontText,tailleText));
				joueur1.setFont(new Font(policeEcriture,fontText,tailleText));
				scroreJ1.setFont(new Font(policeEcriture,fontText,tailleText));
				joueur2.setFont(new Font(policeEcriture,fontText,tailleText));
				scroreJ2.setFont(new Font(policeEcriture,fontText,tailleText));

				int indiceCouleurLigne=0;
				// Element du tableau
				int j = 0;
				while (j<15 && ( (indicePartie+j)< listePartieRecherche.size() )) {

					Partie partie = listePartieRecherche.get(indicePartie+j);
					panelPartie = new JPanel(new GridLayout(0, 6));
					panelPartie.setBorder(borderligne);
					date = new JLabel(partie.getDate());
					joueur1 = new JLabel(partie.getNomJ1());
					scroreJ1 = new JLabel(partie.getNBcamJ1());
					joueur2 = new JLabel(partie.getNomJ2());
					scroreJ2 = new JLabel(partie.getNBcamJ2());

					// Bordure colonne
					date.setBorder(bordercolonne);
					joueur1.setBorder(bordercolonne);
					scroreJ1.setBorder(bordercolonne);
					joueur2.setBorder(bordercolonne);
					scroreJ2.setBorder(bordercolonne);

					//Style du texte
					date.setFont(new Font(policeEcriture,fontText,tailleText));
					joueur1.setFont(new Font(policeEcriture,fontText,tailleText));
					scroreJ1.setFont(new Font(policeEcriture,fontText,tailleText));
					joueur2.setFont(new Font(policeEcriture,fontText,tailleText));
					scroreJ2.setFont(new Font(policeEcriture,fontText,tailleText));

					// changement de couleur a chaque ligne
					if (indiceCouleurLigne==0) {
						panelPartie.setBackground(bleuColor);
						indiceCouleurLigne=1;
					}else if (indiceCouleurLigne==1) {
						panelPartie.setBackground(rougeColor);
						indiceCouleurLigne=2;
					}else if (indiceCouleurLigne==2) {
						panelPartie.setBackground(orangecolor);
						indiceCouleurLigne=3;
					}else if (indiceCouleurLigne==3) {
						panelPartie.setBackground(vertColor);
						indiceCouleurLigne=0;
					}
					ButtonJolie btnVoir = new ButtonJolie("Détails");
					btnVoir.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							controleurAccueil.AfficherdetailPartie(partie);
						}
					});
					panelPartie.add(date);
					panelPartie.add(joueur1);
					panelPartie.add(scroreJ1);
					panelPartie.add(scroreJ2);
					panelPartie.add(joueur2);
					panelPartie.add(btnVoir);
					vue.add(panelPartie);
					j++;
				}
				indicePartie=(indicePartie+j);

			}


			//###################################
			// affichage bouton bottom
			//###################################
			JLabel numPage = new JLabel("Page "+numeroDePage);
			numPage.setFont(new Font(policeEcriture,Font.BOLD,27));
			ButtonJolie next = new ButtonJolie("Page suivante");
			next.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					cardlayout.next(layeredPaneHisto);
					numeroDePage = numeroDePage+1;
					if (numeroDePage >nbVue) {
						numeroDePage=1;
					}
					numPage.setText("Page "+numeroDePage);
					numPage.revalidate();
				}
			});
			ButtonJolie preview = new ButtonJolie("Page précédente");
			preview.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					cardlayout.previous(layeredPaneHisto);
					numeroDePage = numeroDePage-1;
					if (numeroDePage <1) {
						numeroDePage=nbVue;
					}
					numPage.setText("Page "+numeroDePage);
					numPage.revalidate();
				}
			});

			JPanel panelChangePage = new JPanel();
			panelChangePage.add(preview);
			panelChangePage.add(next);
			JPanel bot = new JPanel(new GridLayout(2, 0));
			JPanel paneNumPage = new JPanel(new FlowLayout(FlowLayout.CENTER));

			paneNumPage.add(numPage);
			bot.add(paneNumPage);
			if (nbVue>1) {
				bot.add(panelChangePage);
			}
			panelHistorique.add(bot, BorderLayout.SOUTH);
		}
	}

	private void afficheDetailPartie(Partie partie){



		afficheBoutonRetour("Historique");

		panelDetailPartie.setLayout(new BoxLayout(panelDetailPartie, BoxLayout.X_AXIS));
	

		Couleur couleurs[] = {Couleur.BLEU,Couleur.ROUGE,Couleur.VERT,Couleur.ORANGE};

		for (int indiceJoueur = 1; indiceJoueur< 3; indiceJoueur++) {
			JPanel joueurs=new JPanel();
			joueurs.setLayout(new BoxLayout(joueurs, BoxLayout.Y_AXIS));
			//joueurs.setBorder(BorderFactory.createLineBorder(Color.black));

			//JLabel winOrLose = new JLabel(""); 
			if (partie.isGagnant(indiceJoueur)) {
				System.out.println("joueur "+indiceJoueur +" a gagner");
				//winOrLose.setText("WINNER");
				//joueurs.setBackground(Color.green);
			}else{
				//joueurs.setBackground(Color.red);
			//	winOrLose.setText("LOSER");
			}


			JPanel panelInfoJoueur =new JPanel(new GridBagLayout());
			//panelInfoJoueur.setLayout(new BoxLayout(panelInfoJoueur, BoxLayout.Y_AXIS));

			String nomJoueur = partie.getNomJoueur(indiceJoueur);
			JLabel labelNomJoueur = new JLabel(nomJoueur);
			String Score = partie.getNBCamJoueur(indiceJoueur);
			JLabel labelscore = new JLabel(Score); 

			//winOrLose.setFont(new Font(policeEcriture,Font.BOLD,60));
			labelNomJoueur.setFont(new Font(policeEcriture,Font.BOLD,50));
			labelscore.setFont(new Font(policeEcriture,Font.PLAIN,40));
			GridBagConstraints contrainte = new GridBagConstraints();    
			contrainte.gridx = 0;                                        
			contrainte.gridy = 0; 
			//panelInfoJoueur.add(winOrLose,contrainte);                                       
			//contrainte.gridy = 1; 
			panelInfoJoueur.add(labelNomJoueur,contrainte);
			contrainte.gridy = 1; 
			panelInfoJoueur.add(labelscore,contrainte);
			panelInfoJoueur.setOpaque(false);

			joueurs.add(panelInfoJoueur);
			//JPanel panelGraph = new JPanel(new GridBagLayout());
			JPanel panelGraph = new JPanel(new GridLayout(2,2));
			panelGraph.setBorder(BorderFactory.createEmptyBorder( 50,  0,  0,  0));
			panelGraph.setOpaque(false);

			contrainte.gridx = 0;                                        
			contrainte.gridy = 0; 
			contrainte.weightx = 1; 
			contrainte.weighty = 1; 
			for (Couleur couleur : couleurs) {
				String categorie = convertCouleurTocategorie(couleur);
				//System.out.println("Couleur :"+couleur+" indice "+indiceJoueur+" total :"+partie.getTotalAll(couleur, indiceJoueur));
				//System.out.println("Couleur :"+couleur+" indice "+indiceJoueur+" rep :"+partie.getReponseAll(couleur,indiceJoueur));
				int total = Integer.parseInt(partie.getTotalAll(couleur, indiceJoueur));
				int repJuste = Integer.parseInt(partie.getReponseAll(couleur,indiceJoueur));
				DefaultPieDataset dataset = new DefaultPieDataset();
				if (total>0) {					
					ArrayList<Double> values = new ArrayList<Double>();
					int repfausse = total -repJuste;
					values.add(new Double(repfausse));
					values.add(new Double(repJuste));
					ArrayList<String> legendes = new ArrayList<String>();
					legendes.add("Reponse Fausses");
					legendes.add("Reponse Juste");
					for (int i = 0; i < values.size(); i++) {
						dataset.setValue(legendes.get(i), values.get(i));
					}

				}
				JFreeChart  chart =createChart(dataset,"Catégorie "+categorie,couleur);
				ChartPanel graph = new ChartPanel(chart);
				switch (couleur) {
				case BLEU:
					chart.setBackgroundPaint(bleuColor);
					graph.setBackground(bleuColor);
					break;
				case ROUGE:	chart.setBackgroundPaint(rougeColor);
				break;
				case VERT:chart.setBackgroundPaint(vertColor);
				break;
				case ORANGE:chart.setBackgroundPaint(orangecolor);
				break;
				default:
					break;
				}
				panelGraph.add(graph);
			}
			joueurs.add(panelGraph);
			panelDetailPartie.add(joueurs);
			if (indiceJoueur ==1) {
				JPanel paneVS = new JPanel();
				JLabel labelVS = new JLabel("VS");
				labelVS.setFont(new Font(policeEcriture,Font.BOLD,50));
				paneVS.add(labelVS);
				panelDetailPartie.add(paneVS);
			}
			//centre.add(joueurs);
		}

	}
	private String convertCouleurTocategorie(Couleur couleur){
		String categorie = "";

		switch (couleur) {
		case BLEU:categorie="Le Saviez-Vous?";
		break;
		case ROUGE:	categorie= "Innovation";
		break;
		case VERT:categorie = "CPE";
		break;
		case ORANGE:categorie="Blagues";
		break;
		default:
			break;
		}
		return categorie;
	}




	@SuppressWarnings("deprecation")
	private static JFreeChart createChart(PieDataset dataset, String titre, Couleur couleur) {


		JFreeChart chart = ChartFactory.createPieChart3D(
				titre,  // chart title
				dataset,             // data
				true,               // include legend
				true,
				false
				);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		
		plot.setLabelFont(new Font(policeEcriture, Font.PLAIN, 12));
		plot.setNoDataMessage("Ce joueur n'as pas rencontré cette catégorie de question");
		plot.setNoDataMessageFont(new Font(policeEcriture,Font.PLAIN,30));
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		plot.setForegroundAlpha(0.7f);
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
		plot.setSectionPaint(1, Color.GREEN);
		plot.setSectionPaint(2, Color.RED);

		switch (couleur) {
		case BLEU:plot.setBackgroundPaint(bleuColor);
		break;
		case ROUGE:	plot.setBackgroundPaint(rougeColor);
		break;
		case VERT:plot.setBackgroundPaint(vertColor);
		break;
		case ORANGE:plot.setBackgroundPaint(orangecolor);
		break;
		default:
			break;
		}


		return chart;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object info) {

		/*
		 * info : 0 => afficherMenuPrincipal
		 * info : 1 => afficherPersonalisation
		 * info : 2 => afficherHistorique
		 * info : 3 => afficheDetailPartie
		 */


		panelPersonalisation.removeAll();
		panelHistorique.removeAll();
		panelDetailPartie.removeAll();
		panelMenu.removeAll();

		int choix = (int)((ArrayList<Object>)info).get(0);
		switch (choix) {
		case 0:

			this.afficherMenuPrincipal();
			panelMenu.setVisible(true);
			break;
		case 1:
			this.personalisation();
			panelPersonalisation.setVisible(true);
			panelMenu.setVisible(false);
			break;
		case 2:
			System.out.println(((ArrayList<Object>)info).get(1).getClass());
			ArrayList<Partie> listePartie = (ArrayList<Partie>) ((ArrayList<Object>)info).get(1);
			ArrayList<Partie> listePartieRecherche = (ArrayList<Partie>) ((ArrayList<Object>)info).get(2);
			this.afficherHistorique( listePartie,listePartieRecherche);
			panelHistorique.setVisible(true);
			panelMenu.setVisible(false);
			break;
		case 3:
			System.out.println(((ArrayList<Object>)info).get(1).getClass());
			Partie partie = (Partie) ((ArrayList<Object>)info).get(1);
			this.afficheDetailPartie( partie);
			panelDetailPartie.setVisible(true);
			panelMenu.setVisible(false);
			break;
		default:
			break;
		}

		//this.pack();
		//this.repaint();

	}

}
