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

import interfaz.EspacioLectura;
import sqlt2.SQLITE;

public class UserLogin {

	private SQLITE db = new SQLITE();
	private EspacioLectura espacioLectura;

	private Boolean logged = false;

//	private File file;

	private JFrame frame;
	private JTabbedPane tabs;
	private JPanel panel;
	private JPasswordField passwordField;
	private JTextField usernameField;

//	private String[][] listaUsers = { { "Usuario", "Contrase" + ((char) 241) + "a" } };
//	private String[][] listaUsers = db.getDatosUsers();
	private static final String[] ADMIN = { "Admin", "Admin" };

	private UserLogin(JFrame frame, JTabbedPane tabs, boolean logged) {
		this.logged = logged;
		this.frame = frame;
		this.tabs = tabs;

//		loginProtocol();
	}

	public UserLogin(JFrame frame, JTabbedPane tabs, JPanel panel) {
		this.frame = frame;
		this.tabs = tabs;
		this.panel = panel;
		this.espacioLectura = new EspacioLectura(this.frame, this.panel, this);

	}

//	private void start(File file) {
//		this.panel.setVisible(false);
//
//		this.file = file;
//		if (!logged) {
//			loginStarter();
//		}
//		if (logged) {
////			this.espacioLectura.setQrFile(file);
//			this.panel.setVisible(true);
//
//		}
//	}

	public void start() {
		this.panel.setVisible(false);

		if (!logged) {
			loginStarter();
		}
		if (logged) {
			this.panel.setVisible(true);

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
			System.err.println("EL LECTOR QR SOLO ES PARA PERSONAL AUTORIZADO");
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
//				JOptionPane.showMessageDialog(frame, "BIENVENIDO", "", JOptionPane.PLAIN_MESSAGE);

//				new Lector(frame, tabs);

			} else {
				Toolkit.getDefaultToolkit().beep();
				System.err.println("NO SE RECONOCE AL USUARIO");
				JOptionPane.showMessageDialog(frame, "NO SE RECONOCE AL USUARIO", "", JOptionPane.ERROR_MESSAGE);

				tabs.setSelectedIndex(0);

			}
		} else if (a == 1 || a == JOptionPane.CLOSED_OPTION) {
			Toolkit.getDefaultToolkit().beep();
			System.err.println("EL LECTOR QR SOLO ES PARA PERSONAL AUTORIZADO");
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
		boolean bbb = false;
		System.out.println("autenticarAdmin");

		String pass = new String(password);
		String user = new String(username);
		password = null;
		username = null;
//		usernameField.setText("");
//		passwordField.setText("");

//		if (ADMIN[0].equals(user) && ADMIN[1].equals(pass)) {
		if (db.getAdmin()[0].equals(user) && db.getAdmin()[1].equals(pass)) {
			System.err.println("Admin autenticado".toUpperCase());
			boolean bool = true;
			// Agregar usuario
			do {
				int a = JOptionPane.showOptionDialog(frame, loginPanel(), "AGREGAR USUARIO",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						new String[] { "OK", "CANCEL" }, 0);

				if (a == JOptionPane.CANCEL_OPTION || a == JOptionPane.CLOSED_OPTION || a == 1
						|| a == JOptionPane.DEFAULT_OPTION) {
					bool = false;
					Toolkit.getDefaultToolkit().beep();
					tabs.setSelectedIndex(0);
				} else if (a == JOptionPane.OK_OPTION) {
					System.err.println(db.getClass().toString() + " SAYS:");

					String s1 = usernameField.getText();
					String s2 = new String(passwordField.getPassword());

					int i1 = 5;
					int i2 = 8;
					// Verificacion de que el usuario y la contraseņa cumplan con las condiciones
//					if (s1.length() >= 5 && s2.length() >= 8) {
					if (s1.length() >= i1 && s2.length() >= i2) {
						// invertimos para manejar el ciclo
//					bool = !db.insertar(usernameField.getText(), new String(passwordField.getPassword()));
						bool = !db.insertar(s1, s2);
						bbb = true;

					}
					System.out.println(usernameField.getText() + "::::" + new String(passwordField.getPassword()));

					if (bool) {
						System.err.println("NO SE ACEPTA EL USUARIO, INTENTE OTRO");
						int eee = JOptionPane.showConfirmDialog(frame,
								"NO SE ACEPTARON LOS CAMPOS. TENGA EN CUENTA LO SIGUIENTE" + "\n-EL USUARIO DEBE TENER "
										+ i1 + " CARACTERES O M" + (char) 193 + "S" + "\n-LA CONTRASE" + (char) 209
										+ "A DEBE TENER " + i2 + " O M" + (char) 193
										+ "S CARACTERES\n-ESCOGER UN USUARIO NO EXISTENTE EN EL SISTEMA",
								"AGREGAR USUARIO", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

						if (eee == JOptionPane.CANCEL_OPTION || eee == JOptionPane.CLOSED_OPTION) {
							bool = false;
							tabs.setSelectedIndex(0);
						}
					}
				}

			} while (bool);
			// se agrego usuario
			if (bbb) {
				loginScreen();
			}

		} else {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frame, "ERROR, NO SE RECONOCE AL ADMINISTRADOR", "ADMIN LOGIN",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("ERROR, NO SE RECONOCE AL ADMINISTRADOR");
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

	public Boolean getLogged() {
		return logged;
	}

	public void setLogged(Boolean logged) {
		this.logged = logged;
	}

	public JTabbedPane getTabs() {
		return tabs;
	}

}
