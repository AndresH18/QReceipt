package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import numeros.Num5;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;

/*
 * **https://htmlcolors.com/google-color-picker
 * **https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
 * **https://stackoverflow.com/questions/37396939/jcombobox-customize-vertical-scrollbar
 */
public class Interfaz {
	// TODO: make Javadoc
	// TODO: Implementar shift + tab para regresar en componentes

	//
	/*
	 * private void variables() { // infoEmpresaCLiente --> Date date;// dia mes año
	 * hora minuto /// precion y articulos String nombreCliente, telefonoCliente,
	 * idCliente, dirrecionCliente; int numFactura;// ++ // Object articulos;//
	 * Valor, total, forma de pago
	 * 
	 * // infoNuestraEmpresa --> Enumeracion(DIRECCION, NOMBRE, TELEFONO, NIT....) }
	 */

	static final String ROOT_PATH = System.getProperty("user.dir");
	static final String LOGO_PATH = ROOT_PATH + "\\DOCS\\QReceipt_logo.jpeg";
	static final String WINDOW_LOGO = ROOT_PATH + "\\DOCS\\QReceipt_WindowLogo.jpg";

	static final int SEPARACION_FRAME = 24;
	static final Border RAISED_BORDER = BorderFactory.createRaisedBevelBorder();
	static final Color COLOR_FRAME = new Color(217, 222, 222);
//	static final Color COLOR_FRAME = new Color(250, 252, 201);

	static final Color COLOR_PANEL = new Color(202, 202, 202);

	static final Font FUENTE_PLAIN_12 = new Font("Tahoma", Font.PLAIN, 12);
	static final Font FUENTE_BOLD_12 = new Font("Tahoma", Font.BOLD, 12);

	private boolean isVisibleFactura = false;

	private EspacioRecibo espacioRecibo;
	private FormatoRecibo formatoRecibo;
	private JFrame frame;
	private JPanel panelFormato;
	private JPanel panelRecibo;
	// TABS
	private JPanel tab1;
	private JPanel tab2;

	private JPasswordField passwordField;

	// FIXME: Delete Commented Lines

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
//					Pestaña1.textFieldNombre.requestFocus();

//					System.out.println(window.frame.getWidth()-(panel1.getX()+panel1.getWidth()));
//					System.out.println(window.frame.getWidth()-panel1.getX()-24);
//					System.out.println(lblNombre.getHeight());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		try {
			frame.setIconImage(ImageIO.read(setWindowIcon()));
		} catch (IOException e) {
			System.out.println("CATCH");
		}
		frame.setBounds(100, 100, 587, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(this.getClass().getCanonicalName());
		frame.setBackground(new Color(0, 0, 0));
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setBackground(COLOR_FRAME);// 90, 218, 250
//		frame.getContentPane().setLayout(null);

		JTabbedPane tabs = new JTabbedPane();
		tabs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
//				if(e.getKeyCode()==e.)
//				System.out.println("ExtendedkeyCode = " + k.getExtendedKeyCode());//ctrl = 17
//				System.out.println("KeyCode = " + k.getKeyCode());//ctrl = 17
				// ctrl + w ==> close window
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					//
					System.exit(0);
				}

			}
		});
		frame.getContentPane().setLayout(null);
		tabs.setBounds(0, 0, 583, 604);
		frame.getContentPane().add(tabs);

		tab1 = new JPanel();
		tab1.setLayout(null);
		tab1.setBackground(new Color(255, 255, 255));
		tabs.addTab("1", tab1);
		tabs.setBackgroundAt(0, Color.WHITE);

		tab2 = new JPanel();
		tab2.setBackground(COLOR_FRAME);
		tab2.setLayout(null);
		tabs.addTab("2", tab2);

		panelFormato = new JPanel();
		panelFormato.setLayout(null);
		panelFormato.setVisible(true);
		panelFormato.setBackground(new Color(255, 255, 255));
		panelFormato.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		tab1.add(panelFormato);

		formatoRecibo = new FormatoRecibo(frame, panelFormato);
		formatoRecibo.getBtnGenerarRecibo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("GENERAR");

//				igualarDatosProductos(formatoRecibo, espacioRecibo);
				igualarDatosProductos();

//				entregarDatosCliente(formatoRecibo, espacioRecibo);
				entregarDatosCliente();

				controlVisibilidadFormato_Recibo();

//				imprimirNuevo();
			}
		});
		panelRecibo = new JPanel();
		panelRecibo.setLayout(null);
		panelRecibo.setVisible(false);
		panelRecibo.setBackground(new Color(255, 255, 255));
		panelRecibo.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		tab1.add(panelRecibo);

		espacioRecibo = new EspacioRecibo(frame, panelRecibo, formatoRecibo.getDatosProductos());
		espacioRecibo.getLblLogo().setIcon(new ImageIcon(setLogo(espacioRecibo.getLblLogo())));
		espacioRecibo.getBtnRegresar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("DESGENERAR");
				controlVisibilidadFormato_Recibo();
			}
		});
		espacioRecibo.getBtnTerminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("IMPRIMIR RECIBO");
				reset();
			}
		});

		/*
		 * TAB 1
		 */
//		new Pestaña1(frame, tab1, datosProductos);

		/*
		 * TAB 2
		 * 
		 * TODO: aplicar un usuario y contraseña para poder leer el QR
		 */
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 421, 271, 19);
		tab2.add(passwordField);

	}

	private void controlVisibilidadFormato_Recibo() {
//	private void controlVisibilidadFormato_Recibo(FormatoRecibo formatoRecibo, EspacioRecibo espacioRecibo) {
		JComponent[] componentesformato = formatoRecibo.getComponents();
		//
		if (!isVisibleFactura) {
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
			panelRecibo.setVisible(true);
			isVisibleFactura = true;
		} else {
			// recibo -> invisiblre, formato -> visible
			panelFormato.setVisible(true);
			isVisibleFactura = false;
			espacioRecibo.getBtnRegresar().setVisible(false);
			espacioRecibo.getBtnRegresar().setEnabled(false);
			for (JComponent componente : componentesformato) {
				//no hacerle nada al JTable
				if (!(componente instanceof JTable)) {
					componente.setVisible(true);
					componente.setFocusable(true);
					componente.setEnabled(true);
				}
			}
		}
	}
	/*
	 * TODO: hacer metodo que reciba la matriz y que luego podamos hacer lo de la
	 * suma del valor unitario*la cantidad
	 */

	/**
	 * Sends String[][] datosProductos From formatoRecibo to espacioRecibo
	 * 
	 * @param formatoRecibo
	 * @param espacioRecibo
	 */
//	private void igualarDatosProductos(FormatoRecibo formatoRecibo, EspacioRecibo espacioRecibo) {
	private void igualarDatosProductos() {
//		espacioRecibo.setDatosProductos(formatoRecibo.getDatosProductos().clone());

		espacioRecibo.setDatosProductos(valorUni_TotalProducto());
	}

//	private void entregarDatosCliente(FormatoRecibo formatoRecibo, EspacioRecibo espacioRecibo) {
	private void entregarDatosCliente() {
		espacioRecibo.setDatosClientes(formatoRecibo.getInfoCliente());
	}

//	private String[][] sendProductos(FormatoRecibo formatoRecibo, EspacioRecibo espacioRecibo) {
	private String[][] valorUni_TotalProducto() {
		// Evita problemas si no se ha puesto informacion
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

	/**
	 * Limpia los componentes del formato despues de imprimir
	 */
	private void reset() {
		System.out.println("RESET");
		JComponent[] componentesformato = formatoRecibo.getComponents();
		for (JComponent jComponent : componentesformato) {
			reseter(jComponent);
		}
		formatoRecibo.setDatosProductos(null);
		formatoRecibo.setDatosProductos(new String[0][3]);
	}

	private void reseter(JComponent j) {
		
		if (j instanceof JTextField) {
			((JTextField) j).setText("");
		} else if (j instanceof JSpinner) {
			((JSpinner) j).setValue(1);
		}else if(j instanceof JTable) {
			DefaultTableCellRenderer textoTablaCentro = new DefaultTableCellRenderer();
			textoTablaCentro.setHorizontalAlignment(SwingConstants.CENTER);
			
			DefaultTableCellRenderer textoTablaDerecha = new DefaultTableCellRenderer();
			textoTablaDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
			
			((JTable)j).setModel(new DefaultTableModel(new String[][] {{}, {},{}}, new String[] { "CANTIDAD", "NOMBRE", "VALOR UNITARIO" }));
			((JTable)j).getColumnModel().getColumn(0).setCellRenderer(textoTablaCentro);
			((JTable)j).getColumnModel().getColumn(1).setCellRenderer(textoTablaCentro);
			((JTable)j).getColumnModel().getColumn(2).setCellRenderer(textoTablaDerecha);
		}

	}

	/**
	 * Imprimir para pruebas
	 */
	private void imprimirPruebaDeMatrizNueva() {
//		private void imprimirNuevo(FormatoRecibo formatoRecibo, EspacioRecibo espacioRecibo) {
//		String[][] a = sendProductos(formatoRecibo, espacioRecibo);
		String[][] a = valorUni_TotalProducto();
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * Sets the icon of the Frame/window
	 * 
	 * @return
	 */
	private InputStream setWindowIcon() {
		System.out.println("Window Icon: " + WINDOW_LOGO);

		Mat m = Imgcodecs.imread(WINDOW_LOGO, Imgcodecs.IMREAD_UNCHANGED);
		MatOfByte mByte = new MatOfByte();
		Imgcodecs.imencode(".jpg", m, mByte);
//		Imgcodecs.imencode(".jpeg", m, mByte);
		byte[] byteArray = mByte.toArray();
		InputStream inC = new ByteArrayInputStream(byteArray);

		return inC;
	}

	private BufferedImage setLogo(JLabel label) {
//		System.out.println("Logo: " + LOGO_PATH);

//		Mat m = Imgcodecs.imread(LOGO_PATH, Imgcodecs.IMREAD_UNCHANGED);
		System.out.println("Logo: " + WINDOW_LOGO);
		
		Mat m = Imgcodecs.imread(WINDOW_LOGO, Imgcodecs.IMREAD_UNCHANGED);

		MatOfByte mByte = new MatOfByte();
		Imgcodecs.imencode(".jpg", scaleImage(m, label), mByte);
//		Imgcodecs.imencode(".jpeg", m, mByte);
		byte[] byteArray = mByte.toArray();
		InputStream inC = new ByteArrayInputStream(byteArray);
		BufferedImage bf = null;

		try {
			bf = ImageIO.read(inC);
		} catch (IOException e) {

		}
		return bf;
	}

	/**
	 * Scales image to fit JLabel
	 * 
	 * @param mat
	 * @param label
	 * @return
	 */
	private Mat scaleImage(Mat mat, JLabel label) {
		Mat scaled = new Mat();
		double z;

		if (mat.height() > mat.width()) {
			z = ((double) label.getHeight()) / ((double) mat.height());
		} else {
			z = ((double) label.getWidth()) / ((double) mat.width());

		}
		// INTER_AREA is better for reducing size
		Imgproc.resize(mat, scaled, new Size(), z, z, Imgproc.INTER_AREA);

		return scaled;
	}

}