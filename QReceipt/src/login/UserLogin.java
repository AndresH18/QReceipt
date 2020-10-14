package login;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sqlt2.SQLITE;

public class UserLogin {

	private SQLITE db = new SQLITE();
	
	private boolean logged = false;
	
	private JFrame frame;
	private JTabbedPane tabs;
	private JPasswordField passwordField;
	private JTextField usernameField;

//	private String[][] listaUsers = { { "Usuario", "Contrase" + ((char) 241) + "a" } };
//	private String[][] listaUsers = db.getDatosUsers();
	private static final String[] ADMIN = { "Admin", "Admin" };

	private UserLogin(JFrame frame, JTabbedPane tabs, SQLITE db) {
		this.db = db;
		this.frame = frame;
		this.tabs = tabs;

		loginStarter();
	}

	private UserLogin(JFrame frame, JTabbedPane tabs, SQLITE db, boolean logged) {
		this.logged = logged;
		this.db = db;
		this.frame = frame;
		this.tabs = tabs;

		loginStarter();
	}

	public UserLogin(JFrame frame, JTabbedPane tabs, boolean logged) {
		this.logged = logged;
		this.frame = frame;
		this.tabs = tabs;

//		loginProtocol();
	}

	public UserLogin(JFrame frame, JTabbedPane tabs) {
		this.frame = frame;
		this.tabs = tabs;

	}

	public void start() {
		if (!logged) {
			loginStarter();
		}
	}

//	public UserLogin(JFrame frame, JTabbedPane tabs, boolean sign) {
//		if(!sign) {
//			Interfaz.setSign(true);
//			this.frame = frame;
//			this.tabs = tabs;
//	
//			loginProtocol();
//		}else {
//			new Lector(frame, tabs);
//		}
//	}
	private void loginStarter() {
		System.out.println("loginProtocol");
		int op = JOptionPane.showConfirmDialog(frame, "PARA CONTINUAR, PORFAVOR INICIE SESION", "",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

		if (op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION) {
			JOptionPane.showMessageDialog(frame, "EL LECTOR QR SOLO ES PARA PERSONAL AUTORIZADO");

			tabs.setSelectedIndex(0);
		} else {
			// LoginScreen
			loginScreen();
//			int loginOpt = JOptionPane.showOptionDialog(frame, loginPanel(), "Login", JOptionPane.YES_NO_CANCEL_OPTION,
//					JOptionPane.WARNING_MESSAGE, null, new String[] { "Continuar", "Cancelar", "Crear Usuario" }, 0);
//			usersLoginVerify(loginOpt);
		}
	}
	
	private void loginScreen() {
		int loginOpt = JOptionPane.showOptionDialog(frame, loginPanel(), "Login", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE, null, new String[] { "Continuar", "Cancelar", "Crear Usuario" }, 0);
		usersLoginVerify(loginOpt);
	}

	private void usersLoginVerify(int a) {
		System.out.println("loginerOptioner");
		if (a == 0) {
			// Continuar
			if (autenticarUsuario(usernameField.getText(), passwordField.getPassword())) {
				JOptionPane.showMessageDialog(frame, "BIENVENIDO", "", JOptionPane.PLAIN_MESSAGE);

//				new Lector(frame, tabs);
			} else {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(frame, "NO SE RECONOCE AL USUARIO", "", JOptionPane.ERROR_MESSAGE);

				tabs.setSelectedIndex(0);

			}
		} else if (a == 1 || a == JOptionPane.CLOSED_OPTION) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frame, "EL LECTOR QR SOLO ES PARA PERSONAL AUTORIZADO");

			tabs.setSelectedIndex(0);
		} else if (a == 2) {

//			int adminOpt = JOptionPane.showOptionDialog(frame, loginPanel(), "ADMIN LOGIN", JOptionPane.WARNING_MESSAGE,
//					JOptionPane.OK_CANCEL_OPTION, null, null, 0);
//			
//			if (adminOpt == JOptionPane.CANCEL_OPTION || adminOpt == JOptionPane.CLOSED_OPTION) {
//				tabs.setSelectedIndex(1);
//			} else if (adminOpt == JOptionPane.OK_OPTION) {
//				autenticarAdmin(usernameField.getText(), passwordField.getPassword());
//			}
			adminLoginVerify();

		}
	}

	private void adminLoginVerify() {
		System.out.println("adminLogin");

		int adminOpt = JOptionPane.showOptionDialog(frame, loginPanel(), "ADMIN LOGIN", JOptionPane.WARNING_MESSAGE,
				JOptionPane.OK_CANCEL_OPTION, null, new String[] { "OK", "CANCEL" }, 0);

		if (adminOpt == JOptionPane.CANCEL_OPTION || adminOpt == JOptionPane.CLOSED_OPTION || adminOpt == 1) {
			Toolkit.getDefaultToolkit().beep();
			tabs.setSelectedIndex(0);
		} else if (adminOpt == JOptionPane.OK_OPTION) {
			autenticarAdmin(usernameField.getText(), passwordField.getPassword());
		}

	}

	private boolean autenticarUsuario(String username, char[] password) {
		System.out.println("autenticarUsuario");

		String pass = new String(password);
		String user = new String(username);
		password = null;
		username = null;
		usernameField.setText("");
		passwordField.setText("");

//		String[][] listaUsers = db.getDatosUsers();
		String[][] listaUsers = db.getDatos();
		for (int i = 0; i < listaUsers.length; i++) {
			if (listaUsers[i][0].equals(user) && listaUsers[i][1].equals(pass)) {
				logged = true;
				return true;
			}
		}

		return false;
	}

	private void autenticarAdmin(String username, char[] password) {
		System.out.println("autenticarAdmin");

		String pass = new String(password);
		String user = new String(username);
		password = null;
		username = null;
//		usernameField.setText("");
//		passwordField.setText("");

//		if (ADMIN[0].equals(user) && ADMIN[1].equals(pass)) {
		if (db.getAdmin()[0].equals(user) && db.getAdmin()[1].equals(pass)) {
			System.out.println("Admin autenticado".toUpperCase());
			boolean bool = true;
			//Agregar usuario
			do {
				int a = JOptionPane.showOptionDialog(frame, loginPanel(), "AGREGAR USUARIO",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						new String[] { "OK", "CANCEL" }, 0);

				if (a == JOptionPane.CANCEL_OPTION || a == JOptionPane.CLOSED_OPTION || a == 1) {
					bool = false;
					Toolkit.getDefaultToolkit().beep();
					tabs.setSelectedIndex(0);
				} else if (a == JOptionPane.OK_OPTION) {
					System.err.println(db.getClass().toString() + " SAYS:");
					// invertimos para manejar el ciclo
					bool = !db.insertar(usernameField.getText(), new String(passwordField.getPassword()));
					System.out.println(usernameField.getText() + "::::" + new String(passwordField.getPassword()));

					if (bool) {
						int eee = JOptionPane.showConfirmDialog(frame, "NO SE ACEPTA EL USUARIO, INTENTE OTRO",
								"AGREGAR USUARIO", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
						
						if(eee == JOptionPane.CANCEL_OPTION || eee == JOptionPane.CLOSED_OPTION) {
							bool = false;
							tabs.setSelectedIndex(0);
						}
					}
				}
			} while (bool);
			// se agrego usuario
			loginScreen();
			// TODO CONTINUAR:

		} else {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frame, "ERROR, NO SE RECONOCE AL ADMINISTRADOR", "ADMIN LOGIN",
					JOptionPane.ERROR_MESSAGE);
			tabs.setSelectedIndex(0);
		}
	}

	private JPanel loginPanel() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("User", SwingConstants.RIGHT));
		label.add(new JLabel("Password", SwingConstants.RIGHT));
		panel.add(label, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		usernameField = new JTextField();
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				try {
					if (k.getKeyCode() == KeyEvent.VK_ENTER) {
						passwordField.requestFocus();
					}
				} catch (Exception e) {

				}
			}
		});
		controls.add(usernameField);
		passwordField = new JPasswordField();
		controls.add(passwordField);
		panel.add(controls, BorderLayout.CENTER);
		return panel;
	}

}
