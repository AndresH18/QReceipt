package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import metodos.Num3;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;

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
	private JFrame frame;
	private JSeparator separador1;
//	private static JSeparator separador3, separador2;
	// TABS
	private JPanel tab1, tab2;
	
	private JPasswordField passwordField;

	// FIXME: Delete Commented Lines


	static final int SEPARACION_FRAME = 24;
	static final Border RAISED_BORDER = BorderFactory.createRaisedBevelBorder();
	static final Color COLOR_FRAME = new Color(217, 222, 222);
//	static final Color COLOR_FRAME = new Color(250, 252, 201);

	static final Color COLOR_PANEL = new Color(202, 202, 202);
	
	private static String[][] datosProductos = new String[1][3];
	
//	static final Color COLOR_PANEL = new Color(217, 243, 255);//azulezco

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
					Pestaña1.textFieldNombre.requestFocus();
					
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
//		frame.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent k) {
////				if(e.getKeyCode()==e.)
////				System.out.println("ExtendedkeyCode = " + k.getExtendedKeyCode());//ctrl = 17
////				System.out.println("KeyCode = " + k.getKeyCode());//ctrl = 17
//				//ctrl + w ==> close window
//				if(k.isControlDown() && k.getKeyCode()==KeyEvent.VK_W) {
//					frame.dispose();
//					//
//					System.exit(0);
//				}
//
//			}
//		});
		frame.setBounds(100, 100, 587, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(this.getClass().getCanonicalName());
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
		tabs.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.getContentPane().add(tabs);

		tab1 = new JPanel();
		tab1.setLayout(null);
		tab1.setBackground(COLOR_FRAME);
		tabs.addTab("1", tab1);

		tab2 = new JPanel();
		tab2.setBackground(COLOR_FRAME);
		tab2.setLayout(null);
		tabs.addTab("2", tab2);

//		separador3 = new JSeparator();
//		separador3.setOrientation(SwingConstants.VERTICAL);
//		separador3.setBounds(57, 0, 1, frame.getHeight());
//		separador3.setVisible(false);
//		frame.getContentPane().add(separador3);
//
//		separador2 = new JSeparator();
//		separador2.setBounds((frame.getWidth()-separador1.getX()-15), 0, 1, frame.getHeight());
//		separador2.setOrientation(SwingConstants.VERTICAL);
//		separador2.setVisible(false);
//		frame.getContentPane().add(separador2);

		

		/*
		 * TAB 1
		 */
		new Pestaña1(frame, tab1, datosProductos);
		
		
		/*
		 * TAB 2
		 * 
		 * TODO: aplicar un usuario y contraseña para poder leer el QR
		 */
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 421, 271, 19);
		tab2.add(passwordField);

	}


}