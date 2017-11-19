package Model;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ButtonJolie extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonJolie(String txt) {
	super(txt);
	miseEnForme();
	}

	public ButtonJolie() {
		super();
		miseEnForme();
	}

	public ButtonJolie(Action arg0) {
		super(arg0);
		miseEnForme();
	}

	public ButtonJolie(String arg0, Icon arg1) {
		super(arg0, arg1);
		miseEnForme();
	}

	public ButtonJolie(Icon arg0) {
		super(arg0);
		miseEnForme();
		
	}
	
	private void miseEnForme() {
		setOpaque(false);
		setFocusPainted(false); // On n'affiche pas l'effet de focus.
		setFont(new Font("Calibri", Font.BOLD, 20));
		setHorizontalTextPosition(JLabel.CENTER); 
		setFocusable(false);
		setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
		Color c = Color.decode("#3290ab");
		setBackground(c);
		setForeground(Color.WHITE);
		setUI(new StyledButtonUI());
	}
	
}


