package launcher;

import java.awt.Dimension;
import javax.swing.JFrame;


import vue.TrivialPursuitGUI;

public class LauncherGUI {

	public LauncherGUI(){
		
		JFrame frame;
		Dimension dim;

		dim = new Dimension(1000, 1000);

		frame = new TrivialPursuitGUI("Trivial Pursuit", dim);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		LauncherGUI launcher = new LauncherGUI();
		
	}
}
