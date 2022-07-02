package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

import extern.ScriptRunner;
import fachklasse.DBManager;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;

public class AnmeldungGui extends JFrame {

	private JPanel contentPane;
	private JTextField txtBenutzername;
	private JPasswordField passwordField;
	private JTextField txtHost;
	
	private DBManager dbm = null;
	
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

	  public void keyPressed(KeyEvent e) {
		    if (e.getKeyCode()==KeyEvent.VK_ENTER){
		      JOptionPane.showMessageDialog(null , "Your form has been sent");
		    }
		  }
	
	/**
	 * Create the frame.
	 */
	public AnmeldungGui() {
		
		//init Gui \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
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
		txtBenutzername.setText("root");
		txtBenutzername.setColumns(10);
		
		JLabel lblBenutzername = new JLabel("Benutzername");
		
		JLabel lblPassw = new JLabel("Passwort");
		
		JButton btnAnmelden = new JButton("Anmelden");
		btnAnmelden.addActionListener(new ActionListener() {
			//-------------------------------------------------------------------------------------------------
			public void actionPerformed(ActionEvent e) {
						loginAktion();
				}
		});
		
		//Beenden Button
		JButton btnBeenden = new JButton("Beenden");
		btnBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			
			}
		});
		
		
		//Rest Gui -------------------------------------------------------------------------------------------------
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
				      loginAktion();
				    }
			}
		});
		
		JLabel lblHost = new JLabel("Host");
		
		txtHost = new JTextField();
		txtHost.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				loginAktion();
			}
		});
		txtHost.setText("localhost");
		txtHost.setColumns(10);
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
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(btnAnmelden, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnBeenden, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
								.addComponent(lblPassw, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(lblHost, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtHost, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPassw)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblHost)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtHost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBeenden)
						.addComponent(btnAnmelden))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	
	private void loginAktion() {
		//DB datenübermittlung
		dbm = new DBManager(txtBenutzername.getText(), String.valueOf(passwordField.getPassword()),txtHost.getText());
		//Verbindungsaufbau
		if (dbm.startConnect("")) {
			//Überprüfung ob DB und Tabellen exisitieren
			if (!dbm.tableExist(dbm.getConnection(), "kunden") || !dbm.databaseExist(dbm.getConnection())) {
				//Dialog Popup
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Keine CRM Datenbank gefunden! Soll eine erstellt werden?","Warnung",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
						try {
							//DB Erstellung mit sql script
							ScriptRunner runner = new ScriptRunner(dbm.getConnection(), false, true);
							runner.runScript(new BufferedReader(new FileReader("src/init.sql")));
							System.out.println("DB eingefügt");
							//Schließt fenster und öffnet MainView falls DB nichht besteht
							dispose();
							new MainView(dbm).setVisible(true);
							//Fehler handler
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					dbm.closeConnection();
				}
			}else {
		//Schließt fenster und öffnet MainView falls DB schon besteht
		dispose();
		new MainView(dbm).setVisible(true);
		}
		dbm.closeConnection();
		}	
	}
}
