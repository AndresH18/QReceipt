package interfaz;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;



/*
 * **https://htmlcolors.com/google-color-picker
 * **https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
 * **https://stackoverflow.com/questions/37396939/jcombobox-customize-vertical-scrollbar
 */
public class Interfaz {
	//FIXME
	//TODO
	public static void variables() {
		//infoEmpresaCLiente --> 
		Date date;//dia mes año hora minuto
		///precion y articulos
		String nombreCliente, telefonoCliente, idCliente, dirrecionCliente;
		int numFactura;//++
		//
		Object articulos;//Valor, total, forma de pago
		
		//infoNuestraEmpresa --> Enumeracion(DIRECCION, NOMBRE, TELEFONO, NIT....)

	}
	//FIXME:
	static final String[] MONTHS = {"MES" , "ENERO" , "FEBRERO" , "MARZO" , "ABRIL" , "MAYO" , "JUNIO" , "JULIO" ,
						"AGOSTO" ,"SEPTIEMBRE" , "OCTUBRE" , "NOVIEMBRE" , "DICIEMBRE" };
//	static final int[] DAY31 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
	static final String[] DAY31 = {"DIA" ,"1","2","3","4","5","6","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23",
							"24","25","26","27","28","29","30","31"};
//	static final int[] DAY30 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
	static final String[] DAY30 = {"DIA" ,"1","2","3","4","5","6","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23",
			"24","25","26","27","28","29","30"};
//	static final int[] DAY29 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
	
	static final int SEPARACION_FRAME = 24;
	static final Border RAISED_BORDER = BorderFactory.createRaisedBevelBorder();
	static final Color COLOR_FRAME = new Color(250, 252, 201);
	static final Color COLOR_PANEL = new Color(202, 202, 202);
//	static final Color COLOR_PANEL = new Color(217, 243, 255);//azulezco
	
	private JFrame frame;
	private JSeparator separador1;
//	private static JSeparator separador3, separador2;

	private JPanel tab1, tab2;
	
	private JPanel panelNombre;
	private JPanel panelApellido;
	private JPanel panelFecha;
	
	private JLabel lblNombre;
	private JLabel lblApellido;	
	private JLabel lblFecha;
	
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	
	private JComboBox comboBoxMonth, comboBoxDay, comboBoxYear;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
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
		frame.setBounds(100, 100, 572, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setBackground(COLOR_FRAME);//90, 218, 250
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
//				if(e.getKeyCode()==e.)
//				System.out.println("ExtendedkeyCode = " + k.getExtendedKeyCode());//ctrl = 17
//				System.out.println("KeyCode = " + k.getKeyCode());//ctrl = 17
				//ctrl + w ==> close window
				if(k.isControlDown() && k.getKeyCode()==KeyEvent.VK_W) {
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
		
		separador1 = new JSeparator();
		separador1.setBounds(0, 91, frame.getWidth(), 2);
		separador1.setOrientation(SwingConstants.HORIZONTAL);
//		frame.getContentPane().add(separador1);
		tab1.add(separador1);
		
		panelNombre = new JPanel();
		panelNombre.setLayout(null);
//		panelNombre.setBounds(15, 103, 538, 30);
		panelNombre.setBounds(15, 103, 400, 30);
		panelNombre.setBackground(COLOR_PANEL);
//		frame.getContentPane().add(panel1);
		tab1.add(panelNombre);
		
		
		lblNombre = new JLabel("Nombre:      ".toUpperCase());//6 Spaces
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(5, 5, 100, panelNombre.getHeight() - 2*5);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setOpaque(true);
		lblNombre.setBorder(RAISED_BORDER);
		panelNombre.add(lblNombre);
		
		textFieldNombre = new JTextField();
//		textFieldNombre.setBounds((lblNombre.getWidth() + 5), 6, 350, 19);
		textFieldNombre.setBounds((lblNombre.getWidth() + 5), 6, 350-100, 19);
		panelNombre.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		panelApellido = new JPanel();
		panelApellido.setLayout(null);
		panelApellido.setBackground(COLOR_PANEL);
//		panelApellido.setBounds(panelNombre.getX(), 143, panelNombre.getWidth(), 30);
		panelApellido.setBounds(panelNombre.getX(), 143, panelNombre.getWidth(), panelNombre.getHeight());
//		frame.getContentPane().add(panelApellido);
		tab1.add(panelApellido);
		
		lblApellido = new JLabel("APELLIDO:      ");
		lblApellido.setOpaque(true);
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellido.setBorder(RAISED_BORDER);
		lblApellido.setBounds(5, 5, 100, panelApellido.getHeight()-2*5);
		panelApellido.add(lblApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
//		textFielApellido.setBounds(105, 6, 350, 19);
		textFieldApellido.setBounds(105, 6, 350-100, 19);
		panelApellido.add(textFieldApellido);	

		panelFecha = new JPanel();
		panelFecha.setLayout(null);
		panelFecha.setBounds(panelNombre.getX(), 183, panelNombre.getWidth(), panelNombre.getHeight());
		panelFecha.setBackground(COLOR_PANEL);
		tab1.add(panelFecha);
		
		lblFecha = new JLabel("FECHA:      ");
		lblFecha.setOpaque(true);
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setBorder(RAISED_BORDER);
		lblFecha.setBounds(5, 5, 100, panelFecha.getHeight()-2*5);
		panelFecha.add(lblFecha);
		
		comboBoxMonth = new JComboBox(MONTHS);
		comboBoxMonth.setBounds(115, 6, 74, 21);
		panelFecha.add(comboBoxMonth);
		
		comboBoxDay = new JComboBox();
		comboBoxDay.setBounds(199, 6, 29, 21);
		panelFecha.add(comboBoxDay);
		
		comboBoxYear = new JComboBox();
		comboBoxYear.setBounds(268, 6, 29, 21);
		panelFecha.add(comboBoxYear);
		
		
		
		
	}
}