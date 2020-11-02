package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import buscador.Buscador;
import login.UserLogin;

public class EspacioLectura {

	private static final String COMPANY = "<html>Direccion: Cra 38# 6D Z sur 30<br>NIT: 112.358.132-1B<br>Telefono: (-57) 300336 9209<br>E-mail: qreceipt@receipt.qr</html>";
	private static final Font FUENTE_PLAIN_12 = new Font("Tahoma", Font.PLAIN, 12);
	private static final Font FUENTE_BOLD_12 = new Font("Tahoma", Font.BOLD, 13);

	private JFrame frame;
	private JPanel panelTab;
	private JPanel panelInfo;
	private JButton btnCerrarSesion;
	private JButton btnSeleccionarArchivo;
	private JButton btnPagar;
	private JLabel lblInfo;
	private JSeparator separador;
	private JScrollPane scrollPane;
	private JTable table;
	private JFileChooser fileChooser;

//	private File qrFile = null;

	private UserLogin userLogin;
	private Buscador buscador = new Buscador();

	private JPanel header;
	private JLabel lblEmpresa;
	private JLabel lblEmpresaInfo;
	private JLabel lblLogo;
	private JSeparator separator1;
	private JPanel body;
	private JLabel numFactura;
	private JLabel numFact;
	private JLabel lblHeadFactura;
	private JPanel clienteInfo;
	private JLabel lblFecha;
	private JLabel lblCliente;
	private JLabel lbl_ID;
	private JLabel lblDireccion;
	private JSeparator separator2;
	private JLabel fecha;
	private JLabel nombre;
	private JLabel id;
	private JLabel direccion;
	private JLabel lblValor;
	private JLabel valorTotal;
	private JLabel lblQR;
	private JLabel estadoPago;

//	public void setQrFile(File qrFile) {
//		this.qrFile = qrFile;
//	}

	private EspacioLectura(JFrame frame, JPanel panel) {
		this.frame = frame;
		this.panelTab = panel;
	}

	public EspacioLectura(JFrame frame, JPanel panel, UserLogin userLogin) {
		this.frame = frame;
		this.panelTab = panel;
		this.userLogin = userLogin;

		initialize();

		fecha.setText("");
		nombre.setText("");
		id.setText("");
		direccion.setText("");
		estadoPago.setText("");
		valorTotal.setText("");
		lblQR.setText("");
		numFact.setText("");

		setExpLogo(btnSeleccionarArchivo);
		setEmpLogo(lblLogo);

		startActions();

	}

//	private EspacioLectura(JFrame frame, JPanel panel, File file) {
//		this.frame = frame;
//		this.panelTab = panel;
//		this.qrFile = file;
//
//		initialize();
//
//		setExpLogo(btnSeleccionarArchivo);
//		setEmpLogo(lblLogo);
//
//		startActions();
//
//	}
//
//	private EspacioLectura(JFrame frame, JPanel panel, File file, UserLogin userLogin) {
//		this.frame = frame;
//		this.panelTab = panel;
//		this.qrFile = file;
//		this.userLogin = userLogin;
//
//		initialize();
//
//		setExpLogo(btnSeleccionarArchivo);
//		setEmpLogo(lblLogo);
//
//		startActions();
//
//	}

	private void initialize() {
		btnCerrarSesion = new JButton("<html>Cerrar Sesi" + (char) 243 + "n");
		btnCerrarSesion.setVerticalAlignment(SwingConstants.CENTER);
		btnCerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		btnCerrarSesion.setHorizontalTextPosition(SwingConstants.LEFT);
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCerrarSesion.setFocusable(false);
		btnCerrarSesion.setBounds(5, 5, 70, 35);
		panelTab.add(btnCerrarSesion);

//		btnSeleccionarArchivo = new JButton(new ImageIcon(setExpLogo()));
		btnSeleccionarArchivo = new JButton();
		btnSeleccionarArchivo.setHorizontalAlignment(SwingConstants.CENTER);
		btnSeleccionarArchivo.setVerticalAlignment(SwingConstants.CENTER);
		btnSeleccionarArchivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSeleccionarArchivo.setBounds(panelTab.getWidth() - 50, panelTab.getHeight() - (75 + 27), 30, 30);
		panelTab.add(btnSeleccionarArchivo);

		btnPagar = new JButton("Pagar");
		btnPagar.setHorizontalAlignment(SwingConstants.CENTER);
		btnPagar.setVerticalAlignment(SwingConstants.CENTER);
		btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPagar.setBounds(panelTab.getWidth() - 150, panelTab.getHeight() - (75 + 27), 70, 28);
		btnPagar.setFocusable(false);
		panelTab.add(btnPagar);

		panelInfo = new JPanel();
		panelInfo.setLayout(null);
		panelInfo.setBounds(30, 50, panelTab.getWidth() - 65, panelTab.getHeight() - 160);
//		panelInfo.setBackground(new Color(192, 219, 92));
		panelInfo.setBackground(Color.WHITE);
		panelTab.add(panelInfo);

//		lblImgQR = new JLabel();
//		lblImgQR.setBounds(panelInfo.getWidth() - 50, 0, 50, 50);
//		lblImgQR.setBackground(Color.RED);
//		lblImgQR.setOpaque(true);
//		panelInfo.add(lblImgQR);

		header = new JPanel();
		header.setBounds(0, 0, 557, 120);
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panelInfo.add(header);

		lblEmpresa = new JLabel("QReceipt");
		lblEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpresa.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresa.setFont(FUENTE_BOLD_12);
		lblEmpresa.setBounds(128, 5 + 10, 429, 20);
		header.add(lblEmpresa);

		lblEmpresaInfo = new JLabel(COMPANY);
		lblEmpresaInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpresaInfo.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresaInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmpresaInfo.setBounds(128, 23 + 15, 429, 69);
		header.add(lblEmpresaInfo);

		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(0, 15, 90, 90);
		header.add(lblLogo);

		separator1 = new JSeparator();
		separator1.setBounds(10, 90 + 45, frame.getWidth() - 40, 2);
		panelInfo.add(separator1);

		body = new JPanel();
		body.setBounds(0, 150, 558 - 10, 407);
		body.setBackground(Color.WHITE);
		body.setLayout(null);
		panelInfo.add(body);

		numFactura = new JLabel("NUMERO: ");
		numFactura.setVerticalAlignment(SwingConstants.TOP);
		numFactura.setHorizontalAlignment(SwingConstants.LEFT);
		numFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFactura.setBounds(390 - 50, 0, 124, 15);
		body.add(numFactura);

		numFact = new JLabel("LSLLSLSS");
		numFact.setVerticalAlignment(SwingConstants.TOP);
		numFact.setHorizontalAlignment(SwingConstants.LEFT);
		numFact.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFact.setBounds(450 - 50, 0, 100, 15);
		body.add(numFact);

		lblHeadFactura = new JLabel("FACTURA DE VENTA");
		lblHeadFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadFactura.setVerticalAlignment(SwingConstants.TOP);
		lblHeadFactura.setFont(FUENTE_BOLD_12);
		lblHeadFactura.setBounds(0, 0, 400, 15);
		body.add(lblHeadFactura);

		clienteInfo = new JPanel();
		clienteInfo.setBounds(0, 16, 558, 150);
		clienteInfo.setBackground(frame.getBackground());
		body.add(clienteInfo);
		clienteInfo.setLayout(null);

		lblFecha = new JLabel("FECHA");
		lblFecha.setFont(FUENTE_PLAIN_12);
		lblFecha.setBounds(76, 10, 70, 13);
		clienteInfo.add(lblFecha);

		lblCliente = new JLabel("CLIENTE");
		lblCliente.setFont(FUENTE_PLAIN_12);
		lblCliente.setBounds(76, 33, 70, 13);
		clienteInfo.add(lblCliente);

		lbl_ID = new JLabel("C.C / NIT");
		lbl_ID.setFont(FUENTE_PLAIN_12);
		lbl_ID.setBounds(76, 56, 70, 13);
		clienteInfo.add(lbl_ID);

		lblDireccion = new JLabel("DIRECCION");
		lblDireccion.setFont(FUENTE_PLAIN_12);
		lblDireccion.setBounds(76, 79, 70, 13);
		clienteInfo.add(lblDireccion);

		separator2 = new JSeparator(SwingConstants.VERTICAL);
		separator2.setBounds(lbl_ID.getX() + lblDireccion.getWidth() + 20, 0, 1, 127);
		clienteInfo.add(separator2);

		fecha = new JLabel("FECHA");
		fecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fecha.setBounds(186, 11, 230, 13);
		clienteInfo.add(fecha);

		nombre = new JLabel("NOMBRE");
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nombre.setBounds(186, 34, 230, 13);
		clienteInfo.add(nombre);

		id = new JLabel("id");
		id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		id.setBounds(186, 57, 230, 13);
		clienteInfo.add(id);

		direccion = new JLabel("DIRECCION");
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		direccion.setBounds(186, 80, 230, 13);
		clienteInfo.add(direccion);

		lblValor = new JLabel("VALOR");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValor.setBounds(76, 105, 50, 13);
		clienteInfo.add(lblValor);

		valorTotal = new JLabel("VALORRRR");
		valorTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		valorTotal.setBounds(186, 105, 230, 13);
		clienteInfo.add(valorTotal);

		lblQR = new JLabel("QR");
		lblQR.setHorizontalAlignment(SwingConstants.CENTER);
		lblQR.setVerticalAlignment(SwingConstants.CENTER);
		lblQR.setBounds(418 - 20, 2, 127 - 3, 127 - 3);
		clienteInfo.add(lblQR);

		estadoPago = new JLabel("FALTA POR PAGAR");
		estadoPago.setBounds(lblQR.getX() - 20, lblQR.getHeight() + 10, 160, 13);
		estadoPago.setFont(new Font("Tahoma", Font.BOLD, 13));
		estadoPago.setForeground(Color.red);
		clienteInfo.add(estadoPago);


	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EspacioLectura window = new EspacioLectura();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EspacioLectura() {
		initialize2();

		startActions();

		setExpLogo(btnSeleccionarArchivo);

		setExpLogo(lblQR);

		setEmpLogo(lblLogo);
	}

	private void initialize2() {
		frame = new JFrame();
		frame.setBounds(100, 100, 587, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.addWindowListener(new WindowAdapter() {
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				System.out.println("CLOSING");
//				System.exit(0);
//			}
//
//		});
		frame.setTitle(this.getClass().getCanonicalName());
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setLayout(null);
//		frame.getContentPane().setBackground(COLOR_FRAME);

		panelTab = new JPanel();
		panelTab.setLayout(null);
		panelTab.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.add(panelTab);

		// DESDE AQUI

		btnCerrarSesion = new JButton("<html>Cerrar Sesi" + (char) 243 + "n");
		btnCerrarSesion.setVerticalAlignment(SwingConstants.CENTER);
		btnCerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		btnCerrarSesion.setHorizontalTextPosition(SwingConstants.LEFT);
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCerrarSesion.setFocusable(false);
		btnCerrarSesion.setBounds(5, 5, 70, 35);
		panelTab.add(btnCerrarSesion);

//		btnSeleccionarArchivo = new JButton(new ImageIcon(setExpLogo()));
		btnSeleccionarArchivo = new JButton();
		btnSeleccionarArchivo.setHorizontalAlignment(SwingConstants.CENTER);
		btnSeleccionarArchivo.setVerticalAlignment(SwingConstants.CENTER);
		btnSeleccionarArchivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSeleccionarArchivo.setBounds(panelTab.getWidth() - 50, panelTab.getHeight() - (75 + 27), 30, 30);
		panelTab.add(btnSeleccionarArchivo);

		btnPagar = new JButton("Pagar");
		btnPagar.setHorizontalAlignment(SwingConstants.CENTER);
		btnPagar.setVerticalAlignment(SwingConstants.CENTER);
		btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPagar.setBounds(panelTab.getWidth() - 150, panelTab.getHeight() - (75 + 27), 70, 28);
		btnPagar.setFocusable(false);
		panelTab.add(btnPagar);

		panelInfo = new JPanel();
		panelInfo.setLayout(null);
		panelInfo.setBounds(30, 50, panelTab.getWidth() - 65, panelTab.getHeight() - 160);
//		panelInfo.setBackground(new Color(192, 219, 92));
		panelInfo.setBackground(Color.WHITE);
		panelTab.add(panelInfo);

//		lblImgQR = new JLabel();
//		lblImgQR.setBounds(panelInfo.getWidth() - 50, 0, 50, 50);
//		lblImgQR.setBackground(Color.RED);
//		lblImgQR.setOpaque(true);
//		panelInfo.add(lblImgQR);

		header = new JPanel();
		header.setBounds(0, 0, 557, 120);
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panelInfo.add(header);

		lblEmpresa = new JLabel("QReceipt");
		lblEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpresa.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresa.setFont(FUENTE_BOLD_12);
		lblEmpresa.setBounds(128, 5 + 10, 429, 20);
		header.add(lblEmpresa);

		lblEmpresaInfo = new JLabel(COMPANY);
		lblEmpresaInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpresaInfo.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresaInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmpresaInfo.setBounds(128, 23 + 15, 429, 69);
		header.add(lblEmpresaInfo);

		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(0, 15, 90, 90);
		header.add(lblLogo);

		separator1 = new JSeparator();
		separator1.setBounds(10, 90 + 45, frame.getWidth() - 40, 2);
		panelInfo.add(separator1);

		body = new JPanel();
		body.setBounds(0, 150, 558 - 10, 407);
		body.setBackground(Color.WHITE);
		body.setLayout(null);
		panelInfo.add(body);

		numFactura = new JLabel("NUMERO: ");
		numFactura.setVerticalAlignment(SwingConstants.TOP);
		numFactura.setHorizontalAlignment(SwingConstants.LEFT);
		numFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFactura.setBounds(390 - 50, 0, 124, 15);
		body.add(numFactura);

		numFact = new JLabel("LSLLSLSS");
		numFact.setVerticalAlignment(SwingConstants.TOP);
		numFact.setHorizontalAlignment(SwingConstants.LEFT);
		numFact.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFact.setBounds(450 - 50, 0, 100, 15);
		body.add(numFact);

		lblHeadFactura = new JLabel("FACTURA DE VENTA");
		lblHeadFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadFactura.setVerticalAlignment(SwingConstants.TOP);
		lblHeadFactura.setFont(FUENTE_BOLD_12);
		lblHeadFactura.setBounds(0, 0, 400, 15);
		body.add(lblHeadFactura);

		clienteInfo = new JPanel();
		clienteInfo.setBounds(0, 16, 558, 150);
		clienteInfo.setBackground(frame.getBackground());
		body.add(clienteInfo);
		clienteInfo.setLayout(null);

		lblFecha = new JLabel("FECHA");
		lblFecha.setFont(FUENTE_PLAIN_12);
		lblFecha.setBounds(76, 10, 70, 13);
		clienteInfo.add(lblFecha);

		lblCliente = new JLabel("CLIENTE");
		lblCliente.setFont(FUENTE_PLAIN_12);
		lblCliente.setBounds(76, 33, 70, 13);
		clienteInfo.add(lblCliente);

		lbl_ID = new JLabel("C.C / NIT");
		lbl_ID.setFont(FUENTE_PLAIN_12);
		lbl_ID.setBounds(76, 56, 70, 13);
		clienteInfo.add(lbl_ID);

		lblDireccion = new JLabel("DIRECCION");
		lblDireccion.setFont(FUENTE_PLAIN_12);
		lblDireccion.setBounds(76, 79, 70, 13);
		clienteInfo.add(lblDireccion);

		separator2 = new JSeparator(SwingConstants.VERTICAL);
		separator2.setBounds(lbl_ID.getX() + lblDireccion.getWidth() + 20, 0, 1, 127);
		clienteInfo.add(separator2);

		fecha = new JLabel("FECHA");
		fecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fecha.setBounds(186, 11, 230, 13);
		clienteInfo.add(fecha);

		nombre = new JLabel("NOMBRE");
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nombre.setBounds(186, 34, 230, 13);
		clienteInfo.add(nombre);

		id = new JLabel("id");
		id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		id.setBounds(186, 57, 230, 13);
		clienteInfo.add(id);

		direccion = new JLabel("DIRECCION");
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		direccion.setBounds(186, 80, 230, 13);
		clienteInfo.add(direccion);

		lblValor = new JLabel("VALOR");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValor.setBounds(76, 105, 50, 13);
		clienteInfo.add(lblValor);

		valorTotal = new JLabel("VALORRRR");
		valorTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		valorTotal.setBounds(186, 105, 230, 13);
		clienteInfo.add(valorTotal);

		lblQR = new JLabel("QR");
		lblQR.setHorizontalAlignment(SwingConstants.CENTER);
		lblQR.setVerticalAlignment(SwingConstants.CENTER);
		lblQR.setBounds(418 - 20, 2, 127 - 3, 127 - 3);
		clienteInfo.add(lblQR);

		estadoPago = new JLabel("FALTA POR PAGAR");
		estadoPago.setBounds(lblQR.getX() - 20, lblQR.getHeight() + 10, 160, 13);
		estadoPago.setFont(new Font("Tahoma", Font.BOLD, 13));
		estadoPago.setForeground(Color.red);
		clienteInfo.add(estadoPago);

	}

	private void startActions() {
//		frame.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				System.out.println("CLOSING");
//				System.exit(0);
//			}
//		});

		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnCerrarSesion: actionPerformed");

				if (userLogin != null) {
					userLogin.setLogged(false);
					userLogin.getTabs().setSelectedIndex(0);
					JOptionPane.showMessageDialog(frame, "SE HA CERRADO LA SESION");
				}

			}
		});
		btnSeleccionarArchivo.setToolTipText("Buscar Factura");
		btnSeleccionarArchivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscador.buscar(frame, self());

//				if (qrFile != null) {
//					fileChooser = new JFileChooser(qrFile.getPath());
//				} else {
//					fileChooser = new JFileChooser(System.getProperty("user.home"));
//				}
//				int val = fileChooser.showOpenDialog(frame);
//				if (val == JFileChooser.APPROVE_OPTION) {
//					qrFile = fileChooser.getSelectedFile();
//					if (qr.read(qrFile) != null) {
//						lblImgQR.setIcon(new ImageIcon(resize(qr.read(qrFile), lblImgQR)));
//
//						System.out.println(traducir(qr.read(qrFile)));
//						System.out.println(Crypto.hexToString(traducir(qr.read(qrFile))));
//					} else {
//						Toolkit.getDefaultToolkit().beep();
//						System.err.println("El archivo no es valido");
//					}
//				}
			}
		});
		btnPagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				buscador.cambiar(frame);
				
				

			}
		});
	}

	private void setExpLogo(JButton btn) {
		BufferedImage buf1 = null;

		try {
			buf1 = ImageIO.read(new File(System.getProperty("user.dir") + "\\.DOCS\\FileExp.png"));
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		if (buf1 != null) {
			Image thumbnail = buf1.getScaledInstance(btn.getWidth(), -1, Image.SCALE_SMOOTH);
			BufferedImage buf2 = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			buf2.getGraphics().drawImage(thumbnail, 0, 0, null);

			btn.setIcon(new ImageIcon(buf2));
		}

	}

	private void setExpLogo(JLabel btn) {
		BufferedImage buf1 = null;

		try {
			buf1 = ImageIO.read(new File(System.getProperty("user.dir") + "\\.DOCS\\Prueba de crear0.png"));
			btn.setIcon(new ImageIcon(buf1));
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

	}

	private void setEmpLogo(JLabel label) {
		BufferedImage buf1 = null;
		try {
			buf1 = ImageIO.read(new File(Interfaz.WINDOW_LOGO));
			Image thumbnail = buf1.getScaledInstance(label.getWidth() - 5, -1, Image.SCALE_SMOOTH);
			BufferedImage buf2 = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			buf2.getGraphics().drawImage(thumbnail, 0, 0, null);

			label.setIcon(new ImageIcon(buf2));
		} catch (IOException e) {

		}
	}

	private BufferedImage resize(BufferedImage image, JLabel label) {
		if (image != null) {
			Image thumbnail = image.getScaledInstance(label.getWidth(), -1, Image.SCALE_SMOOTH);
			BufferedImage buf2 = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			buf2.getGraphics().drawImage(thumbnail, 0, 0, null);

			return buf2;

		} else {
			return image;
		}
	}

	private String traducir(BufferedImage image) {
		if (image != null) {

			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			try {
				Result result = new MultiFormatReader().decode(bitmap);
				return result.getText();
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
			Toolkit.getDefaultToolkit().beep();
			return "THERE IS NO \"QR\" IN THE IMAGE ";
		} else {
			return null;
		}
	}

	public synchronized JLabel getValorTotal() {
		return valorTotal;
	}

	public JLabel getNumFact() {
		return numFact;
	}

	public JLabel getFecha() {
		return fecha;
	}

	public JLabel getNombre() {
		return nombre;
	}

	public synchronized JLabel getDireccion() {
		return direccion;
	}

	public JLabel getId() {
		return id;
	}

	public JLabel getEstadoPago() {
		return estadoPago;
	}

	private EspacioLectura self() {
		return this;
	}

	public synchronized JLabel getLblQR() {
		return lblQR;
	}

}
