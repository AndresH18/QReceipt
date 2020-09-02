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



/*
 * **https://htmlcolors.com/google-color-picker
 * **https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
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
	static final int SEPARACION_FRAME = 24;
	static final Border RAISED_BORDER = BorderFactory.createRaisedBevelBorder();
	static final Color COLOR_FRAME = new Color(250, 252, 201);
	static final Color COLOR_PANEL = new Color(202, 202, 202);
//	static final Color COLOR_PANEL = new Color(217, 243, 255);//azulezco
	
	private JFrame frame;
//	private static JSeparator separador3, separador2;
	private static JSeparator separador1;
	private static JPanel panel1;
	private static JTextField textFieldNombre;
	private static JLabel lblNombre;
	private static JTextField textFieldApellido;
	private static JLabel labelApellido;
	
//	private static Border raisedBorder = BorderFactory.
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
		frame.addKeyListener(new KeyAdapter() {
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
		frame.setBounds(100, 100, 572, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setBackground(COLOR_FRAME);//90, 218, 250
		frame.getContentPane().setLayout(null);

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
		frame.getContentPane().add(separador1);
		
		panel1 = new JPanel();
		panel1.setBounds(10, 103, 538, 30);
		panel1.setBackground(COLOR_PANEL);
		frame.getContentPane().add(panel1);
		panel1.setLayout(null);
		
		lblNombre = new JLabel("Nombre:      ".toUpperCase());//6 Spaces
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(5, 5, 100, panel1.getHeight() - 2*5);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setOpaque(true);
		lblNombre.setBorder(RAISED_BORDER);
		panel1.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(lblNombre.getWidth() + 5, 6, 350, 19);
		panel1.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		
		
	}
}