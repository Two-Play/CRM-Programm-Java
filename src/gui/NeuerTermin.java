package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JDialog;

public class NeuerTermin extends JDialog {

	public NeuerTermin() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/img/icon.png")));
	}

}
