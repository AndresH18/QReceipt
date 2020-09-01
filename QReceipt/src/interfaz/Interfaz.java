package interfaz;
import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;



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
	private JFrame frame;
//	private static JSeparator separador1, separador2;
	private static JSeparator separador3;
	private static JPanel panel1;
	
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
					System.out.println(window.frame.getWidth()-(panel1.getX()+panel1.getWidth()));
					System.out.println(window.frame.getWidth()-panel1.getX()-24);
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
		frame.setBounds(100, 100, 572, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
//		separador1 = new JSeparator();
//		separador1.setOrientation(SwingConstants.VERTICAL);
//		separador1.setBounds(57, 0, 1, frame.getHeight());
//		separador1.setVisible(false);
//		frame.getContentPane().add(separador1);
//
//		separador2 = new JSeparator();
//		separador2.setBounds((frame.getWidth()-separador1.getX()-15), 0, 1, frame.getHeight());
//		separador2.setOrientation(SwingConstants.VERTICAL);
//		separador2.setVisible(false);
//		frame.getContentPane().add(separador2);
		
		separador3 = new JSeparator();
		separador3.setBounds(0, 91, frame.getWidth(), 2);
		separador3.setOrientation(SwingConstants.HORIZONTAL);
		frame.getContentPane().add(separador3);
		
		panel1 = new JPanel();
//		panel1.setBounds(10, 10, 543, 71);
		panel1.setBounds(10, 10, frame.getWidth()-panel1.getX()-(SEPARACION_FRAME+10), 71);
		frame.getContentPane().add(panel1);
		
		
		
		
		
	}
}
