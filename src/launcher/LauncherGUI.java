package launcher;

import java.awt.Dimension;
import java.util.Observer;

import javax.swing.JFrame;


import vue.ChessGameGUI;

/**
 * @author Floriane SANCHIS - Tanguy DUMAS
 *  Lance l'ex�cution d'un jeu d'�chec en mode
 *  graphique. La vue (ChessGameGUI) observe le mod�le (ChessGame) les
 *  �changes passent par le contr�leur (ChessGameControlers)
 * 
 */
public class LauncherGUI {

	public LauncherGUI(){
		
		JFrame frame;
		Dimension dim;

		dim = new Dimension(1000, 1000);

		frame = new ChessGameGUI("Trivial Pursuit", dim);
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(500, 30);
		frame.pack();
		frame.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LauncherGUI launcher = new LauncherGUI();
		
	}
}
