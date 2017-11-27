package launcher;
import javax.swing.JFrame;
import vue.TrivialPursuitGUI;

public class LauncherGUI {

	public LauncherGUI(){		
		JFrame frame = new TrivialPursuitGUI("Trivial Pursuit");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		LauncherGUI launcher = new LauncherGUI();
		
	}
}
