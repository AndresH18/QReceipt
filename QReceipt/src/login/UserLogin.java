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

public class UserLogin {

	private JFrame frame;
	private JTabbedPane tabs;
	private JPasswordField passwordField;
	private JTextField usernameField;

	private String[][] listaUsers = { { "Usuario", "Contrase" + ((char) 241) + "a" } };
	private static final String[] ADMIN = { "AdminUser", "AdminPassword" };

	public UserLogin(JFrame frame, JTabbedPane tabs) {
		this.frame = frame;
		this.tabs = tabs;

		loginProtocol();
	}

	private void loginProtocol() {
		int op = JOptionPane.showConfirmDialog(frame, "PARA CONTINUAR, PORFAVOR INICIE SESION", "",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

		if (op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION) {
			JOptionPane.showMessageDialog(frame, "EL LECTOR QR SOLO ES PARA PERSONAL AUTORIZADO");

			tabs.setSelectedIndex(0);
		} else {
			// LoginScreen
			int loginOpt = JOptionPane.showOptionDialog(frame, loginPanel(), "Login", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, null, new String[] { "Continuar", "Cancelar", "Crear Usuario" }, 0);
			loginerOptioner(loginOpt);
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

	private void loginerOptioner(int a) {
		if (a == 0) {
			// COntinuar
			if (autenticarUsuario(usernameField.getText(), passwordField.getPassword())) {

			}
		} else if (a == 1 || a == JOptionPane.CLOSED_OPTION) {
			JOptionPane.showMessageDialog(frame, "EL LECTOR QR SOLO ES PARA PERSONAL AUTORIZADO");

			tabs.setSelectedIndex(0);
		} else if (a == 2) {
			int adminOpt = JOptionPane.showOptionDialog(frame, loginPanel(), "ADMIN LOGIN", JOptionPane.WARNING_MESSAGE,
					JOptionPane.OK_CANCEL_OPTION, null, null, 0);
			if (adminOpt == JOptionPane.CANCEL_OPTION || adminOpt == JOptionPane.CLOSED_OPTION) {
				tabs.setSelectedIndex(1);
			} else if (adminOpt == JOptionPane.OK_CANCEL_OPTION) {
				autenticarAdmin(usernameField.getText(), passwordField.getPassword());
			}

		}
	}

	private boolean autenticarUsuario(String username, char[] password) {
		String pass = new String(password);
		String user = new String(username);
		password = null;
		username = null;
		usernameField.setText("");
		passwordField.setText("");

		for (int i = 0; i < listaUsers.length; i++) {
			if (listaUsers[i][0].equals(user) && listaUsers[i][1].equals(pass)) {
				return true;
			}
		}

		return false;
	}

	private void autenticarAdmin(String username, char[] password) {
		String pass = new String(password);
		String user = new String(username);
		password = null;
		username = null;
		usernameField.setText("");
		passwordField.setText("");
		if (ADMIN[0].equals(user) && ADMIN[1].equals(pass)) {
			// TODO FUNCIONES DE ADMIN
		} else {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frame, "ERROR, NO SE RECONOCE AL ADMINISTRADOR", "ADMIN LOGIN",
					JOptionPane.ERROR_MESSAGE);
			tabs.setSelectedIndex(0);
		}
	}

}
