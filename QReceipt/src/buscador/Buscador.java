package buscador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import accesoDatos.accesoRegistro.IManejoDatos;
import accesoDatos.accesoRegistro.ManejoDatos;
import accesoDatos.conexionDatos.AccesoRegistro;
import codificar.Decodificar;
import crypto.Crypto;
import interfaz.EspacioLectura;
import numeros.Num6;
import qr.QR;

public class Buscador {

	private IManejoDatos data;
	private EspacioLectura lectura;

	private boolean entrySelected;
	private boolean existe;

	private String entry;

	private String fecha;
	private String nombre;
	private String id;
	private String direccion;
	private String valor;
	private String estado;

	private JFrame frame;
	private JTextField textField;

	public Buscador() {
		data = new ManejoDatos();
		data.crearArchivo();
		entrySelected = false;
		existe = false;
	}

	private Buscador(JFrame frame, JPanel panel) {
		this.frame = frame;
		start(frame);

	}

	private Buscador(JFrame frame) {
		this.frame = frame;
		start(frame);
	}

	@Deprecated(since = "I didn't like how it")
	private void start(JFrame frame) {
//		this.frame = frame;
//
//		int a = JOptionPane.showConfirmDialog(frame, panel(), "", JOptionPane.OK_CANCEL_OPTION,
//				JOptionPane.PLAIN_MESSAGE);
//		if (a == JOptionPane.OK_OPTION) {
//			String dato = textField.getText();
//
//			existe = data.existe(dato);
//			if (existe) {
//				lectura.getLblQR().setIcon(new ImageIcon(new QR().read(dato)));
//				System.err.println("HEEERRRRREEEE");
//			}
//
//		}
//
	}

	public void buscar(JFrame frame, EspacioLectura espacioLectura) {
		this.frame = frame;
		this.lectura = espacioLectura;

		int a = JOptionPane.showOptionDialog(frame, panel(), "", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, new String[] { "OK", "CANCEL" }, 0);
		if (a == JOptionPane.OK_OPTION) {

			entry = textField.getText();
//			System.out.println(entry.replaceAll("[^[0-9]]", "@"));
//			System.out.println(entry.replaceAll("[^[[0-9]+]]", "@"));
			
//			// remove spaces " "
//			entry = entry.replace(" ", "");
////			// remove Letters, leave numbers
//			entry = entry.replaceAll("[a-zA-Z]", "");
			
			entry = entry.replaceAll("[^[0-9]]", "");
//			entry = entry.replaceAll("[^[[0-9]+]]", "");

			if (entry.matches(Num6.FORMATO_VALIDO)) {

				existe = data.existe(entry);
				if (existe) {
//				lectura.getLblQR().setIcon(new ImageIcon(new QR().read(dato + ".png")));
					lectura.getLblQR()
							.setIcon(new ImageIcon(new QR().readAndPlace((entry + ".png"), lectura.getLblQR())));

					String s1 = new QR().readQR(entry + ".png");
					System.out.println("hex: " + s1);
					String s2 = Crypto.hexToString(s1);
					System.out.println("lin: " + s2);
					String[] s = new Decodificar(s2).getDeCoded().clone();

					boolean b = data.getEstado(entry);
//					System.out.println("DATO_ESTADO: " + data.getEstado(dato) + "  EXISTE: " + data.existe(dato));
//					System.out.println("111:   " + b);
					if (b) {
						lectura.getEstadoPago().setText("PAGADA");
						lectura.getEstadoPago().setForeground(Color.BLACK);
					} else {
						lectura.getEstadoPago().setText("NO SE HA PAGADO");
						lectura.getEstadoPago().setForeground(Color.RED);
					}
					lectura.getFecha().setText(s[0]);
					lectura.getNombre().setText(s[1]);
					lectura.getId().setText(s[2]);
					lectura.getDireccion().setText(s[3]);
					lectura.getValorTotal().setText(s[4]);
					lectura.getNumFact().setText(entry);

				} else {
					Toolkit.getDefaultToolkit().beep();
//				JOptionPane.showConfirmDialog(frame,
//						"NO EXISTE.\nSI CREE QUE ES UN ERROR, \nPOR FAVOR CONTACTE A SOPORTE", "",
//						JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(frame,
							"NO EXISTE.\nSI CREE QUE ES UN ERROR, \nPOR FAVOR CONTACTE A SOPORTE", "",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}

	public void cambiar(JFrame frame) {
		
		if (existe) {

			int opt = JOptionPane.showConfirmDialog(frame, "CONFIRMAR PAGAR FACTURA", "", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (opt == JOptionPane.OK_OPTION) {
				data.cambiarEstado(entry, true);
				lectura.getEstadoPago().setText("PAGADA");
				lectura.getEstadoPago().setForeground(Color.BLACK);				
			}

		} else {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frame, "DEBE SELECCIONAR UN ARCHIVO PRIMERO", "",
					JOptionPane.WARNING_MESSAGE);

		}
	}

	private JPanel panel() {
		JPanel panel = new JPanel(new GridLayout(0, 1, 2, 2));

		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("Introducir Numero Factura", SwingConstants.CENTER));
		panel.add(label, BorderLayout.WEST);

		JPanel control = new JPanel(new GridLayout(0, 1, 2, 2));
		textField = new JTextField();
		textField.grabFocus();
		control.add(textField);

		panel.add(control, BorderLayout.WEST);

		panel.requestFocus();
		textField.requestFocus();

		return panel;
	}

	public boolean isEntrySelected() {
		return entrySelected;
	}

	public void setEntrySelected(boolean entrySelected) {
		this.entrySelected = entrySelected;
	}

	public synchronized boolean isExiste() {
		return existe;
	}
}
