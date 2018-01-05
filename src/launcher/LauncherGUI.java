package launcher;

import javax.swing.JFrame;

import vue.Accueil;


public class LauncherGUI {

	public LauncherGUI(){		
		Accueil acc = new Accueil("Accueil");
		acc.setVisible(true);
		acc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		LauncherGUI launcher = new LauncherGUI();
		
	}
}
