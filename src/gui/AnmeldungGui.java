package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnmeldungGui extends JFrame {

	private JPanel contentPane;
	private JTextField txtBenutzername;
	private JTextField txtPasswort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnmeldungGui frame = new AnmeldungGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AnmeldungGui() {
		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/img/icon.png")));
		setTitle("CRM Programm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setMinimumSize(new Dimension(400, 380));
		setContentPane(contentPane);
		JLabel lblAnmelden = new JLabel("Anmelden");
		lblAnmelden.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnmelden.setFont(new Font("Tahoma", Font.BOLD, 35));
		
		txtBenutzername = new JTextField();
		txtBenutzername.setColumns(10);
		
		JLabel lblBenutzername = new JLabel("Benutzername");
		
		JLabel lblNewLabel = new JLabel("Passwort");
		
		txtPasswort = new JTextField();
		txtPasswort.setColumns(10);
		
		JButton btnAnmelden = new JButton("Anmelden");
		
		JButton btnBeenden = new JButton("Beenden");
		btnBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAnmelden, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(135)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblBenutzername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(txtBenutzername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(txtPasswort, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAnmelden, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnBeenden, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
							.addGap(141))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAnmelden)
					.addGap(38)
					.addComponent(lblBenutzername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtBenutzername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPasswort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBeenden)
						.addComponent(btnAnmelden))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
