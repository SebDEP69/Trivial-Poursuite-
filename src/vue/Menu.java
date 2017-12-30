package vue;



import java.awt.BorderLayout;
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
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
	public Menu(String name, ControleurAccueil controleur) {
		super(name);

		this.controleurAccueil = controleur;
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Toolkit leKit = this.getToolkit();
		Dimension boardSize = leKit.getScreenSize();
		setSize(boardSize.width , boardSize.height-50);
	
		layeredPane = this.getLayeredPane();
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
		panelHistorique.setBounds(0, decalage, getWidth(), getHeight()-decalage);
		panelHistorique.setVisible(false);

		panelDetailPartie = new JPanel(new BorderLayout());
		panelDetailPartie.setBounds(0, decalage, getWidth(), getHeight()-decalage);
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
		JLabel txt1 = new JLabel("Bienvenue sur le jeu TRIVIAL PURSUIT, amsuez-vous bien !");
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
		System.out.println("after !"+this.getWidth()+" "+this.getHeight());
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

		JPanel all = new JPanel(new GridLayout(3,0));
		panelPersonalisation.add(all,BorderLayout.CENTER);


		// Panel question
		JPanel panelQuestion = new JPanel(new GridBagLayout());

		String[] elements = new String[]{"Blague", "CPE", "Innovation", "Le saviez-vous?"};
		JComboBox<String> typeQuestion = new JComboBox<String>(elements);
		JTextField question = new JTextField();
		JLabel labelQuestion = new JLabel("Question");
		//L'objet servant à positionner les composants

		panelQuestion.add(typeQuestion);
		panelQuestion.add(labelQuestion);
		question.setPreferredSize(new Dimension(300, 50));
		panelQuestion.add(question);

		// Panel reponse
		JPanel panelreponse = new JPanel(new GridBagLayout());

		JLabel indicationReponse = new JLabel("Selectionnez la bonne reponse");
		panelreponse.add(indicationReponse);
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
		int i=1;
		for (JTextField reponse : listerep) {

			reponse.setPreferredSize(new Dimension(300, 40));

			JRadioButton btnreponse = new JRadioButton("reponse"+i); 

			btnreponse.setActionCommand(""+i);
			if (i==1) {
				btnreponse.setSelected(true);
			}
			group.add(btnreponse);
			gbc.gridy = i+1;
			gbc.gridx = 0;
			panelreponse.add(btnreponse,gbc);
			gbc.gridx = 1;
			panelreponse.add(reponse,gbc);
			i++;
		}



		// panel descritpion
		JPanel panelDescription = new JPanel(new GridLayout(0, 2));
		JLabel labelDescript = new JLabel("Description");
		JTextArea descritption = new JTextArea();
		panelDescription.add(labelDescript);
		panelDescription.add(descritption);

		all.add(panelQuestion);
		all.add(panelreponse);
		all.add(panelDescription);

		JPanel bot = new JPanel(new FlowLayout());
		ButtonJolie btnvalider = new ButtonJolie("Valider");
		btnvalider.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				String Rtype= typeQuestion.getSelectedItem().toString();
				String Rquestion = question.getText();
				String[] listeReponses = {reponse1.getText(),reponse2.getText(),reponse3.getText(),reponse4.getText()};
				String numReponseJuste = group.getSelection().getActionCommand();
				String descript = descritption.getText();

				controleurAccueil.enregisterQuestion(Rtype,Rquestion,listeReponses,numReponseJuste,descript);

			}
		});
		bot.setPreferredSize(new Dimension(0, 100));
		bot.add(btnvalider, CENTER_ALIGNMENT);
		panelPersonalisation.add(bot,BorderLayout.SOUTH);
	}




	private void afficherHistorique(ArrayList<Partie> listePartie) {


		int nbPartie = listePartie.size();
		panelHistorique.setLayout(new BorderLayout()); 
		JPanel vue = new JPanel(new GridLayout((nbPartie+2),0));// +1 pour le bouton retour et +1 pour l'intituler des colonnes
		panelHistorique.add(vue,BorderLayout.CENTER);

		afficheBoutonRetour("Menu");


		JPanel panelPartie = new JPanel(new GridLayout(0, 6));
		JLabel date = new JLabel("DATE");
		JLabel joueur1 = new JLabel("Nom Joueur1");
		JLabel scroreJ1 = new JLabel("Score Joueur1");
		JLabel joueur2 = new JLabel("Nom Joueur 2");
		JLabel scroreJ2 = new JLabel("Scrore Joueur 2");
		panelPartie.add(date);
		panelPartie.add(joueur1);
		panelPartie.add(scroreJ1);
		panelPartie.add(scroreJ2);
		panelPartie.add(joueur2);
		vue.add(panelPartie);




		for (Partie partie : listePartie) {
			panelPartie = new JPanel(new GridLayout(0, 6));
			date = new JLabel(partie.getDate());
			joueur1 = new JLabel(partie.getNomJ1());
			scroreJ1 = new JLabel(partie.getNBcamJ1());
			joueur2 = new JLabel(partie.getNomJ2());
			scroreJ2 = new JLabel(partie.getNBcamJ2());

			ButtonJolie btnVoir = new ButtonJolie("Voir plus");
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
		}


	}

	private void afficheDetailPartie(Partie partie){



		panelDetailPartie.setLayout(new BorderLayout());
		afficheBoutonRetour("Historique");


		JPanel centre = new JPanel(new GridLayout(0, 2));

		panelDetailPartie.add(centre,BorderLayout.CENTER);

		Couleur couleurs[] = {Couleur.BLEU,Couleur.ROUGE,Couleur.VERT,Couleur.ORANGE};

		for (int indiceJoueur = 1; indiceJoueur< 3; indiceJoueur++) {
			JPanel joueurs= new JPanel(new GridLayout(2, 0));
			joueurs.setBorder(BorderFactory.createLineBorder(Color.black));
			JPanel panelInfoJoueur = new JPanel(new GridLayout(2, 0));
			String nomJoueur = partie.getNomJoueur(indiceJoueur);
			JLabel labelNomJoueur = new JLabel(" NOM JOUEUR : "+nomJoueur);
			String Score = partie.getNBCamJoueur(indiceJoueur);
			JLabel labelscore = new JLabel("SCORE : "+Score); 
			panelInfoJoueur.add(labelNomJoueur);
			panelInfoJoueur.add(labelscore);

			joueurs.add(panelInfoJoueur);
			JPanel panelGraph = new JPanel(new GridLayout(2, 2));


			for (Couleur couleur : couleurs) {
				String categorie = convertCouleurTocategorie(couleur);
				System.out.println("Couleur :"+couleur+" indice "+indiceJoueur+" total :"+partie.getTotalAll(couleur, indiceJoueur));
				System.out.println("Couleur :"+couleur+" indice "+indiceJoueur+" rep :"+partie.getReponseAll(couleur,indiceJoueur));
				int total = Integer.parseInt(partie.getTotalAll(couleur, indiceJoueur));
				int repJuste = Integer.parseInt(partie.getReponseAll(couleur,indiceJoueur));
				if (total==0) {
					JLabel noQuestion = new JLabel("CE JOUEUR N'A PAS AFFRONTE DE QUESTION "+categorie);
					noQuestion.setBorder(BorderFactory.createLineBorder(Color.black));
					panelGraph.add(noQuestion);
				}else{

					DefaultPieDataset dataset = new DefaultPieDataset();
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
					JFreeChart  chart =createChart(dataset,"Question "+categorie);
					ChartPanel graph = new ChartPanel(chart);
					panelGraph.add(graph);
				}
			}

			joueurs.add(panelGraph);

			centre.add(joueurs);
		}

	}
	private String convertCouleurTocategorie(Couleur couleur){
		String categorie = "";

		switch (couleur) {
		case BLEU:
			categorie="Le Saviez-Vous?";
			break;
		case ROUGE:
			categorie= "Innovation";

			break;
		case VERT:
			categorie = "CPE";

			break;
		case ORANGE:
			categorie="Blagues";
			break;
		default:
			break;
		}

		return categorie;
	}




	@SuppressWarnings("deprecation")
	private static JFreeChart createChart(PieDataset dataset, String titre) {

		JFreeChart chart = ChartFactory.createPieChart3D(
				titre,  // chart title
				dataset,             // data
				true,               // include legend
				true,
				false
				);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		plot.setSectionPaint(1, Color.GREEN);
		plot.setSectionPaint(2, Color.RED);
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

		


		int choix = (int)((ArrayList<Object>)info).get(0);
		switch (choix) {
		case 0:
			
			this.afficherMenuPrincipal();
			panelMenu.setVisible(true);
			break;
		case 1:
			
			panelPersonalisation.removeAll();
			this.personalisation();
			panelPersonalisation.setVisible(true);
			panelMenu.setVisible(false);
			break;
		case 2:
			
			System.out.println(((ArrayList<Object>)info).get(1).getClass());
			ArrayList<Partie> listePartie = (ArrayList<Partie>) ((ArrayList<Object>)info).get(1);
			panelHistorique.removeAll();
			this.afficherHistorique( listePartie);
			panelHistorique.setVisible(true);
			panelMenu.setVisible(false);
			break;
		case 3:
			
			
			System.out.println(((ArrayList<Object>)info).get(1).getClass());
			Partie partie = (Partie) ((ArrayList<Object>)info).get(1);
			panelDetailPartie.removeAll();
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
