package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import accesoDatos.accesoRegistro.IManejoDatos;
import accesoDatos.accesoRegistro.ManejoDatos;
import codificar.Codificar;
import crypto.Crypto;
import login.UserLogin;
import qr.IQR;
import qr.QR;

/*
 * **https://htmlcolors.com/google-color-picker
 * **https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
 * **https://stackoverflow.com/questions/37396939/jcombobox-customize-vertical-scrollbar
 */
public class Interfaz {

	private static final String ROOT_PATH = System.getProperty("user.dir");
	private static final String LOGO_PATH = ROOT_PATH + "\\.docs\\QReceipt_logo.jpeg";
	static final String WINDOW_LOGO = ROOT_PATH + "\\.docs\\QReceipt_WindowLogo.jpg";

	private static final int SEPARACION_FRAME = 24;
	private static final Border RAISED_BORDER = BorderFactory.createRaisedBevelBorder();
	static final Color COLOR_FRAME = new Color(217, 222, 222);

	private static final Color COLOR_PANEL = new Color(202, 202, 202);

	private static final Font FUENTE_PLAIN_12 = new Font("Tahoma", Font.PLAIN, 12);
	private static final Font FUENTE_BOLD_12 = new Font("Tahoma", Font.BOLD, 12);

	private static boolean logg = false;

//	private File lastFile = null;

	private boolean isVisibleFactura = false;

	private String hex1;

	private UserLogin userLogin;
	private IQR qr;

	private IManejoDatos datos;

	private EspacioRecibo espacioRecibo;
	private FormatoRecibo formatoRecibo;

	private JFileChooser fileChooser;

	private JFrame frame;
	private JPanel panelFormato;
	private JPanel panelRecibo;

	private JPanel panelLector;

	private JTabbedPane tabs;
	// TABS
	private JPanel tab1;
	private JPanel tab2;

	public static void main(String[] args) {

		System.out.println(ROOT_PATH);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interfaz() {

		initialize();

		startActionListeners();
		try {
			setLogos();
		} catch (Exception e) {
			e.printStackTrace();
		}

//		userLogin = new UserLogin(frame, tabs);
		userLogin = new UserLogin(frame, tabs, panelLector);

		qr = new QR();
		datos = new ManejoDatos();
		fileChooser = new JFileChooser(System.getProperty("user.home"));

	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 587, 632);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(this.getClass().getCanonicalName());
//		frame.setBackground(new Color(0, 0, 0));
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(COLOR_FRAME);// 90, 218, 250

		tabs = new JTabbedPane();
		tabs.setBounds(0, 0, 583, 604);
		frame.getContentPane().add(tabs);

		tab1 = new JPanel();
		tab1.setLayout(null);
		tab1.setBackground(new Color(255, 255, 255));
		tabs.addTab("GENERAR", tab1);
		tabs.setBackgroundAt(0, Color.WHITE);

		panelFormato = new JPanel();
		panelFormato.setLayout(null);
		panelFormato.setVisible(true);
		panelFormato.setBackground(new Color(255, 255, 255));
		panelFormato.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		tab1.add(panelFormato);

		formatoRecibo = new FormatoRecibo(frame, panelFormato);

		panelRecibo = new JPanel();
		panelRecibo.setLayout(null);
		panelRecibo.setVisible(false);
		panelRecibo.setBackground(new Color(255, 255, 255));
		panelRecibo.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		tab1.add(panelRecibo);

		espacioRecibo = new EspacioRecibo(frame, panelRecibo, formatoRecibo.getDatosProductos(), this);

		tab2 = new JPanel();
		tab2.setBackground(COLOR_FRAME);
		tab2.setLayout(null);
		tabs.addTab("LECTURA", tab2);

		panelLector = new JPanel();
		panelLector.setLayout(null);
		panelLector.setVisible(true);
		panelLector.setBackground(new Color(255, 255, 255));
		panelLector.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		tab2.add(panelLector);

//		espacioLectura = new EspacioLectura(frame, panelLector, userLogin);

	}

	private void startActionListeners() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				System.err.println("CLOSING");
				System.err.println("CLOSED");
				System.err.println("THE PROGRAM HAS BEEN TERMINATED CORRECTLY");
				System.exit(0);
			}
		});

		tabs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();

					System.exit(0);
				}
			}
		});

		tabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("tabs.getSelectedIndex() == " + tabs.getSelectedIndex());
				if (tabs.getSelectedIndex() == 1) {

//					userLogin.start(lastFile);
					userLogin.start();

				} else if (tabs.getSelectedIndex() == 0) {

				}
			}
		});

		formatoRecibo.getBtnGenerarRecibo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("GENERAR");

				procesoGenQR();

				controlVisibilidadFormatoRecibo();

			}
		});

		espacioRecibo.getBtnRegresar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("DESGENERAR");
				controlVisibilidadFormatoRecibo();
			}
		});

		espacioRecibo.getBtnImprimir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("IMPRIMIR RECIBO");
				frame.setAlwaysOnTop(false);
				System.err.println("ALWAYS ON TOP = FALSE");
				boolean b = espacioRecibo.imprimir();
				frame.setAlwaysOnTop(true);
				System.err.println("ALWAYS ON TOP = TRUE");
				if (b) {

					String num = espacioRecibo.getNumFact().getText();
					qr.save(qr.writeQR(espacioRecibo.getLblQR(), hex1));
					datos.agregarEntrada(num, false);

					reset();
					espacioRecibo.getBtnRegresar().doClick();
				}

			}
		});

		espacioRecibo.getBtnGuardar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = espacioRecibo.getNumFact().getText();

				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//				String lastPath = fileChooser.getSelectedFile().getPath();
//				fileChooser.setCurrentDirectory(new File(lastPath));
//				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
					String path = fileChooser.getSelectedFile().getPath();
					System.out.println(path);

					qr.save(qr.writeQR(espacioRecibo.getLblQR(), hex1), path);
					qr.save(qr.writeQR(espacioRecibo.getLblQR(), hex1));

					datos.agregarEntrada(num, false);

					reset();
					espacioRecibo.getBtnRegresar().doClick();

				}

			}
		});

	}

	private void procesoGenQR() {
//		igualarDatosProductos();
		System.out.println(this.getClass().getCanonicalName() + " igualarDatosProductos()");

		espacioRecibo.setDatosProductos(valorUni_TotalProducto());

//		entregarDatosCliente();
		System.out.println(this.getClass().getCanonicalName() + " entregarDatosCliente()");
		espacioRecibo.setDatosCliente(formatoRecibo.getInfoCliente(), formatoRecibo.getTextFieldValorTotal().getText());

//		datosQR();
		datosQR();
	}

	private void datosQR() {

		String numFile = String.valueOf(new File(IQR.directoryName).listFiles().length + 1);
		int numDataQR = formatoRecibo.getInfoQR().length;
		String[] info = new String[numDataQR + 1];
//		String[] info = new String[formatoRecibo.getInfoQR().length];
		for (int i = 0; i < info.length - 1; i++) {
			info[i] = formatoRecibo.getInfoQR()[i];
		}
		info[numDataQR] = numFile;
		String codi = (new Codificar(info).getCoded());
		System.out.println(this.getClass().getCanonicalName() + ".datosQR()");

		System.out.println("codi:\t" + codi);
		hex1 = Crypto.stringToHex(codi);

		System.out.println("hex1:\t" + hex1);
		System.out.println();

		espacioRecibo.getLblQR().setIcon(new ImageIcon(qr.writeQR(espacioRecibo.getLblQR(), hex1)));

	}

	private String[][] valorUni_TotalProducto() {
		System.out.println(this.getClass().getCanonicalName() + " valorUni_TotalProducto()");

		if (formatoRecibo.getDatosProductos().length == 0) {
			return new String[][] { { "", "", "" } };
		}
		// CANTIDAD[0], NOMBRE[1], VALORUNITARIO[2]
		// ====> CANTIDAD[0], NOMBRE[1], VALORPRODUCTO[2]{valorProducto*cantidad}
		String[][] importados = formatoRecibo.getDatosProductos();
		String[][] datos = new String[importados.length][importados[0].length];

		for (int i = 0; i < importados.length; i++) {
			for (int j = 0; j < importados[0].length; j++) {
				if (j != importados[0].length - 1) {
					datos[i][j] = new String(importados[i][j]);
				} else {
					double val = Integer.valueOf(importados[i][0]) * Integer.valueOf(importados[i][2]);
					datos[i][j] = new String(String.valueOf((int) val));
				}
			}
		}

		return datos;
	}

	public void reset() {
		System.out.println(this.getClass().getCanonicalName() + " reset()");
		System.out.println();

		JComponent[] componenteFormatos = formatoRecibo.getComponents();
		for (JComponent jComponent : componenteFormatos) {
			reseter(jComponent);
		}
		// CUIDADO CHECK IF NESESARY
		formatoRecibo.setDatosProductos(null);
		formatoRecibo.setDatosProductos(new String[0][3]);

	}

	private void reseter(JComponent j) {
		if (j instanceof JTextField) {
			((JTextField) j).setText("");
		} else if (j instanceof JSpinner) {
			((JSpinner) j).setValue(1);
		} else if (j instanceof JTable) {
			DefaultTableCellRenderer textoTablaCentro = new DefaultTableCellRenderer();
			textoTablaCentro.setHorizontalAlignment(SwingConstants.CENTER);

			DefaultTableCellRenderer textoTablaDerecha = new DefaultTableCellRenderer();
			textoTablaDerecha.setHorizontalAlignment(SwingConstants.RIGHT);

			((JTable) j).setModel(
					new DefaultTableModel(new String[][] {}, new String[] { "CANTIDAD", "NOMBRE", "VALOR UNITARIO" }));
			((JTable) j).getColumnModel().getColumn(0).setCellRenderer(textoTablaCentro);
			((JTable) j).getColumnModel().getColumn(1).setCellRenderer(textoTablaCentro);
			((JTable) j).getColumnModel().getColumn(2).setCellRenderer(textoTablaDerecha);
		}
	}

	private void controlVisibilidadFormatoRecibo() {
		JComponent[] componentesformato = formatoRecibo.getComponents();
		//
		if (!isVisibleFactura) {

			isVisibleFactura = true;

			// formato ->invisible, recibo -> visible
			for (JComponent componente : componentesformato) {
				if (!(componente instanceof JTable)) {
					componente.setVisible(false);
					componente.setFocusable(false);
					componente.setEnabled(false);
				}
			}
			espacioRecibo.getBtnRegresar().setVisible(true);
			espacioRecibo.getBtnRegresar().setEnabled(true);
			espacioRecibo.getBtnImprimir().setVisible(true);
			espacioRecibo.getBtnImprimir().setEnabled(true);
			espacioRecibo.getBtnGuardar().setVisible(true);
			espacioRecibo.getBtnGuardar().setEnabled(true);
			panelRecibo.setVisible(true);

		} else {

			isVisibleFactura = false;

			// recibo -> invisiblre, formato -> visible
			panelFormato.setVisible(true);

			espacioRecibo.getBtnRegresar().setVisible(false);
			espacioRecibo.getBtnRegresar().setEnabled(false);
			espacioRecibo.getBtnImprimir().setVisible(false);
			espacioRecibo.getBtnImprimir().setEnabled(false);
			espacioRecibo.getBtnGuardar().setVisible(false);
			espacioRecibo.getBtnGuardar().setVisible(false);
			for (JComponent componente : componentesformato) {
				// no hacerle nada al JTable
				if (!(componente instanceof JTable)) {
					componente.setVisible(true);
					componente.setFocusable(true);
					componente.setEnabled(true);
				}
			}
		}
	}

	private void setLogos() {
		System.out.println("Window Icon:\t" + WINDOW_LOGO);
		System.out.println();

		BufferedImage buf1 = null;
		try {
			buf1 = ImageIO.read(new File(WINDOW_LOGO));
		} catch (IOException e) {

		}
		frame.setIconImage(buf1);

		Image thumbnail = buf1.getScaledInstance(espacioRecibo.getLblLogo().getWidth() - 5, -1, Image.SCALE_SMOOTH);
		BufferedImage buf2 = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		buf2.getGraphics().drawImage(thumbnail, 0, 0, null);

		espacioRecibo.getLblLogo().setIcon(new ImageIcon(buf2));

	}
}
