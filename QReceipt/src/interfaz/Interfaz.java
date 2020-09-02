package interfaz;
import java.awt.Color;
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
import javax.swing.border.Border;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;


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
	static final int SEPARACION_DEL_FRAME = 24;
	static final Color PANEL_COLOR = new Color(215,215,215);
	static final Border RAISED_BORDER = BorderFactory.createRaisedSoftBevelBorder();
	
	
	private JFrame frame;
//	private static JSeparator separador1, separador2;
	private static JSeparator separador3;
	private static JPanel panel1, panel2;
	
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
		frame.getContentPane().setBackground(new Color(217, 243, 255));//90, 218, 250
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
		panel1.setBounds(10, 10, frame.getWidth()-panel1.getX()-(SEPARACION_DEL_FRAME+10), 71);
//		panel1.setBackground(new Color(215,215,215));
		panel1.setBackground(PANEL_COLOR);
//		panel1.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		panel1.setBorder(RAISED_BORDER);
		
		frame.getContentPane().add(panel1);
		
		panel2 = new JPanel();
		panel2.setBounds(10, 105, frame.getWidth()-panel2.getX()-(SEPARACION_DEL_FRAME+10), 71);
		panel2.setBackground(PANEL_COLOR);
		panel2.setBorder(RAISED_BORDER);
		frame.getContentPane().add(panel2);
		
		
		
		
		
	}
}
