package vue;



import java.awt.BorderLayout;
import java.awt.Dimension;
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

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controler.ControleurAccueil;
import Model.ButtonJolie;
import Model.Partie; 



@SuppressWarnings("serial")
public class Menu extends JFrame implements  Observer {

	private JLayeredPane layeredPane;

	private JPanel panelGeneral;
	private ControleurAccueil controleurAccueil;
	private Dimension boardSize;

	public Menu(String name, ControleurAccueil controleur) {

		super(name);
		this.controleurAccueil = controleur;

		Toolkit leKit = this.getToolkit();
		boardSize = leKit.getScreenSize();
		this.setTitle(name);
		//  Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		panelGeneral = new JPanel();
		panelGeneral.setLayout( new BorderLayout() );
		panelGeneral.setPreferredSize( boardSize );
		panelGeneral.setBounds(0, 0, boardSize.width, boardSize.height);
		layeredPane.add(panelGeneral, JLayeredPane.DEFAULT_LAYER);

	}



	private void afficherMenuPrincipal() {

		panelGeneral.setLayout( new BorderLayout() );
		JPanel sud = new JPanel();
		panelGeneral.add(sud, BorderLayout.SOUTH);

		JPanel est = new JPanel();
		panelGeneral.add(est, BorderLayout.EAST);
		//est.setBackground(Color.ORANGE);

		JPanel ouest = new JPanel();
		panelGeneral.add(ouest, BorderLayout.WEST);
		//ouest.setBackground(Color.BLUE);

		JPanel nord = new JPanel();
		panelGeneral.add(nord, BorderLayout.NORTH);


		//sud.setBackground(Color.ORANGE);

		JPanel centre = new JPanel();
		centre.setLayout(new GridBagLayout());
		panelGeneral.add(centre, BorderLayout.CENTER);
		//centre.setBackground(Color.ORANGE);

		// CENTRE HAUT // 
		JPanel texte1 = new JPanel();
		texte1.setPreferredSize(new Dimension(10,90));
		centre.add((JPanel) texte1);
		JLabel txt1 = new JLabel("Bienvenue sur l'application TRIVIAL PURSUIT, pour lancer une partie cliquez sur : Debut de la partie");
		txt1.setHorizontalTextPosition(JLabel.CENTER); 
		txt1.setFont(new Font("Calibri",Font.PLAIN,32));
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
		ButtonJolie btn1 = new ButtonJolie("<Html><center>Debut de la partie<Html>");
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


		JPanel bouton4 = new JPanel();
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
		centre.add(btn4, g);


		JPanel bouton5 = new JPanel();
		centre.add((JPanel) bouton5);
		ButtonJolie btn5 = new ButtonJolie("<Html><center>Personnalisation<Html>");
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
		h.gridy = 3;
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
	}


	private void personalisation() {

		panelGeneral.setLayout(new BorderLayout());
		ButtonJolie btnRetourMenu = new ButtonJolie("Retour");
		btnRetourMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controleurAccueil.afficherMenu();

			}
		});
		panelGeneral.add(btnRetourMenu,BorderLayout.NORTH);


		JPanel all = new JPanel(new GridLayout(3,0));
		panelGeneral.add(all,BorderLayout.CENTER);


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

			reponse.setPreferredSize(new Dimension(300, 50));

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
		btnvalider.setPreferredSize(new Dimension(100, 100));
		panelGeneral.add(btnvalider,BorderLayout.SOUTH);
	}




	private void affichierHistorique(ArrayList<Partie> listePartie) {


		int nbPartie = listePartie.size();

		panelGeneral.setLayout(new GridLayout((nbPartie+2),0)); // +1 pour le bouton retour et +1 pour l'intituler des colonnes

		ButtonJolie btnRetourMenu = new ButtonJolie("Retour");
		btnRetourMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controleurAccueil.afficherMenu();

			}
		});
		panelGeneral.add(btnRetourMenu);
		
		
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
		panelGeneral.add(panelPartie);





		for (Partie partie : listePartie) {
			panelPartie = new JPanel(new GridLayout(0, 6));
			date = new JLabel(partie.getDate());
			joueur1 = new JLabel(partie.getNomJ1());
			scroreJ1 = new JLabel(partie.getNBcamJ1());
			joueur2 = new JLabel(partie.getNomJ2());
			scroreJ2 = new JLabel(partie.getNBcamJ2());

			ButtonJolie btnVoir = new ButtonJolie("Voir plus");
			panelPartie.add(date);
			panelPartie.add(joueur1);
			panelPartie.add(scroreJ1);
			panelPartie.add(scroreJ2);
			panelPartie.add(joueur2);
			panelPartie.add(btnVoir);
			panelGeneral.add(panelPartie);
		}


	}



	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object info) {

		/*
		 * info : 0 => afficherMenuPrincipal
		 * info : 1 => afficherPersonalisation
		 * 
		 */
		panelGeneral.removeAll();
		int choix = (int)((ArrayList<Object>)info).get(0);
		switch (choix) {
		case 0:

			this.afficherMenuPrincipal();
			break;
		case 1:

			this.personalisation();
			break;
		case 2:
			System.out.println(((ArrayList<Object>)info).get(1).getClass());
			ArrayList<Partie> listePartie = (ArrayList<Partie>) ((ArrayList<Object>)info).get(1);
			this.affichierHistorique( listePartie);
			break;

		default:
			break;
		}

		this.pack();
		this.repaint();

	}

}
