package sqlt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Interfaz__SQLITE extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz__SQLITE frame = new Interfaz__SQLITE();
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
	public Interfaz__SQLITE() {

		Login__SQLITE logi = new Login__SQLITE();
		logi.crear(); // Se abre la base de datos
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Login", null, panel, null);
		panel.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(190, 20, 48, 39);
		panel.add(lblLogin);

		textField = new JTextField();
		textField.setBounds(104, 70, 233, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(72, 73, 61, 14);
		panel.add(lblUser);

		JLabel lblPass = new JLabel("Pass");
		lblPass.setBounds(72, 125, 61, 14);
		panel.add(lblPass);

		JButton btnAceptar_1 = new JButton("Aceptar");
		btnAceptar_1.setBounds(166, 170, 89, 23);
		panel.add(btnAceptar_1);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(104, 122, 233, 20);
		panel.add(passwordField_1);

		// Registro
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Registro", null, panel_1, null);
		panel_1.setLayout(null);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(110, 71, 233, 20);
		panel_1.add(textField_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(110, 123, 233, 20);
		panel_1.add(passwordField);

		JLabel lblUser_1 = new JLabel("User");
		lblUser_1.setBounds(78, 74, 47, 14);
		panel_1.add(lblUser_1);

		JLabel lblPass_1 = new JLabel("Pass");
		lblPass_1.setBounds(78, 126, 34, 14);
		panel_1.add(lblPass_1);

		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setBounds(181, 26, 80, 14);
		panel_1.add(lblRegistro);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String USER = textField_2.getText();
				String PASS = new String(passwordField.getPassword());

				logi.insertar(USER, PASS);
				String tokens[] = logi.seleccionar();
				for (String t : tokens) {
					System.out.println(t);

				}
			}
		});
		btnAceptar.setBounds(172, 173, 89, 23);
		panel_1.add(btnAceptar);

	}
}
