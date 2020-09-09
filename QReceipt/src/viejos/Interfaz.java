package viejos;

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
	// JPanels
	private JPanel panelNombre;
	private JPanel panelApellido;
	private JPanel panelFecha;
	private JPanel panelValor;
	private JPanel panelObjetos;

	// JLabels
	private JLabel lblHeaderNombre;
	private JLabel lblHeaderApellido;
	private JLabel lblHeaderFecha;
	private JLabel lblHeaderProductos;
	private JLabel lblHeaderCantidadProductos;
	private JLabel lblHeaderValor;
	private JLabel lblHeaderValorUnidad;
	private JLabel lblValorConsola;
	private JLabel lblValorPalabras;
	private JLabel lblValorTotal;

	// JTextFields
	private static JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldValorTotal;
	private JTextField textFieldProductos;
	private JTextField textFieldValorUnidad;

	
	// JComboBox s Fechas
	private JComboBox<String> comboBoxMonth, comboBoxDay, comboBoxYear;
	// JTable
	private JTable listaProductos;
	
	// JSpinner
	private JSpinner cantProductos;
	
	//JScrollPane
	private JScrollPane scrollProductos;
	
	//JCheckBox
	private JCheckBox modificarValor;

	private JPasswordField passwordField;

	// FIXME: Delete Commented Lines

	static final String[] MONTHS = { "MES", "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO",
			"SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" };
//	static final int[] DAY31 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
//	static final String[] DAY31 = {"DIA" ,"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23",
//							"24","25","26","27","28","29","30","31"};
	static final String[] DAYS31 = generarDias(31);

//	static final int[] DAY30 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
//	static final String[] DAY30 = {"DIA" ,"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23",
//			"24","25","26","27","28","29","30"};
	static final String[] DAYS30 = generarDias(30);

//	static final int[] DAY29 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
//	static final String[] DAY29 = {"DIA" ,"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23",
//			"24","25","26","27","28","29"};
	static final String[] DAYS29 = generarDias(29);

	private String[] dias = findActualMonthForDay();

	static final String[] AÑOS = generarAños();

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
					textFieldNombre.requestFocus();
					
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

		separador1 = new JSeparator();
		separador1.setBounds(0, 91, frame.getWidth(), 2);
		separador1.setOrientation(SwingConstants.HORIZONTAL);
//		frame.getContentPane().add(separador1);
		tab1.add(separador1);

		/*
		 * TAB 1
		 */
		
		/*
		 * PANEL NOMBRE
		 */
		panelNombre = new JPanel();
		panelNombre.setLayout(null);
//		panelNombre.setBounds(15, 103, 538, 30);
		panelNombre.setBounds(15, 103, 400, 30);
		panelNombre.setBackground(COLOR_PANEL);
//		frame.getContentPane().add(panel1);
		tab1.add(panelNombre);

		lblHeaderNombre = new JLabel("<html>Nombre:</html>".toUpperCase());// 6 Spaces
		lblHeaderNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderNombre.setBounds(5, 5, 100, panelNombre.getHeight() - 2 * 5);
		lblHeaderNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderNombre.setOpaque(true);
		lblHeaderNombre.setBorder(RAISED_BORDER);
		panelNombre.add(lblHeaderNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();

					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldApellido.requestFocus();
				}
			}
		});
//		textFieldNombre.setBounds((lblNombre.getWidth() + 5), 6, 350, 19);
		textFieldNombre.setBounds((lblHeaderNombre.getWidth() + 5), 6, 350 - 100, 19);
		panelNombre.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		/*
		 * PANEL APELLIDO
		 */
		panelApellido = new JPanel();
		panelApellido.setLayout(null);
		panelApellido.setBackground(COLOR_PANEL);
//		panelApellido.setBounds(panelNombre.getX(), 143, panelNombre.getWidth(), 30);
		panelApellido.setBounds(panelNombre.getX(), 143, panelNombre.getWidth(), panelNombre.getHeight());
		tab1.add(panelApellido);

		lblHeaderApellido = new JLabel("<html>APELLIDO:</html>");
		lblHeaderApellido.setOpaque(true);
		lblHeaderApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderApellido.setBorder(RAISED_BORDER);
		lblHeaderApellido.setBounds(5, 5, 100, panelApellido.getHeight() - 2 * 5);
		panelApellido.add(lblHeaderApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();

					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBoxMonth.requestFocus();
				}
			}
		});
		textFieldApellido.setColumns(10);
//		textFielApellido.setBounds(105, 6, 350, 19);
		textFieldApellido.setBounds(105, 6, 350 - 100, 19);
		panelApellido.add(textFieldApellido);

		/*
		 * PANEL FECHA
		 * 
		 * FIXME: use JDatePicker
		 * https://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-
		 * calendar-component
		 */
		panelFecha = new JPanel();
		panelFecha.setLayout(null);
		panelFecha.setBounds(panelNombre.getX(), 183, panelNombre.getWidth(), panelNombre.getHeight());
		panelFecha.setBackground(COLOR_PANEL);
		tab1.add(panelFecha);

		lblHeaderFecha = new JLabel("<html>FECHA:</html>");
		lblHeaderFecha.setOpaque(true);
		lblHeaderFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderFecha.setBorder(RAISED_BORDER);
		lblHeaderFecha.setBounds(5, 5, 100, panelFecha.getHeight() - 2 * 5);
		panelFecha.add(lblHeaderFecha);

		comboBoxMonth = new JComboBox(MONTHS);
		comboBoxMonth.setSelectedIndex(LocalDateTime.now().getMonthValue());
		((JLabel) comboBoxMonth.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMonthDays(comboBoxMonth, comboBoxDay);
			}
		});
		comboBoxMonth.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBoxDay.requestFocus();
				}
			}
		});
		comboBoxMonth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxMonth.setBounds(115, 5, 102, 21);
		panelFecha.add(comboBoxMonth);

		comboBoxDay = new JComboBox(dias);
		comboBoxDay.setSelectedIndex(LocalDateTime.now().getDayOfMonth());
		((JLabel) comboBoxDay.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxDay.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();

					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getExtendedKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBoxYear.requestFocus();
				}
			}
		});
		comboBoxDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxDay.setBounds(243, 5, 50, 21);
		panelFecha.add(comboBoxDay);

		comboBoxYear = new JComboBox(AÑOS);
		comboBoxYear.setSelectedItem(String.valueOf(LocalDateTime.now().getYear()));
//		comboBoxYear.setSelectedItem(String.valueOf(LocalDateTime.now().getYear()));
		((JLabel) comboBoxYear.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxYear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldProductos.requestFocus();
				}
			}
		});
		comboBoxYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxYear.setBounds(314, 5, 76, 21);
		panelFecha.add(comboBoxYear);

		

		/*
		 * PANEL OBJETOS
		 */
		panelObjetos = new JPanel();
		panelObjetos.setBounds(15, 223, 542, 157);
		panelObjetos.setBackground(COLOR_PANEL);
		tab1.add(panelObjetos);
		panelObjetos.setLayout(null);

		lblHeaderProductos = new JLabel("<html>PRODUCTO:</html>");
		lblHeaderProductos.setOpaque(true);
		lblHeaderProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderProductos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderProductos.setBorder(RAISED_BORDER);
		lblHeaderProductos.setBounds(5, 5, 100, 20);
		panelObjetos.add(lblHeaderProductos);

		textFieldProductos = new JTextField();
		textFieldProductos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					System.exit(0);
				}
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode()==KeyEvent.VK_ENTER) {
					//cantProductos.getEditor().requestFocus();
					cantProductos.setValue((int) 1);
					textFieldValorUnidad.requestFocus();

				}
			}
		});
		textFieldProductos.setBounds(5, 25, 140, 20);
		panelObjetos.add(textFieldProductos);

		lblHeaderCantidadProductos = new JLabel("<html>CANTIDAD:</html>");
		lblHeaderCantidadProductos.setOpaque(true);
		lblHeaderCantidadProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderCantidadProductos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderCantidadProductos.setBorder(RAISED_BORDER);
		lblHeaderCantidadProductos.setBounds(5, 51, 100, 20);
		panelObjetos.add(lblHeaderCantidadProductos);

		cantProductos = new JSpinner();
		cantProductos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					System.exit(0);
				}
				if (k.getKeyCode() == KeyEvent.VK_TAB) {
					textFieldValorUnidad.requestFocus();
				}
			}
		});
		cantProductos.setBounds((lblHeaderCantidadProductos.getX() + lblHeaderCantidadProductos.getWidth() + 3) ,
				lblHeaderCantidadProductos.getY(), 40, lblHeaderCantidadProductos.getHeight());
		cantProductos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cantProductos.setValue((int) 1);
		//cantProductos.setFocusable(true);
		panelObjetos.add(cantProductos);
		
		listaProductos = new JTable();
		listaProductos.setLayout(new FlowLayout());
		listaProductos.setBounds(173, 9, 359, 62);
		listaProductos.setModel(new DefaultTableModel(datosProductos, new String[] {"NOMBRE" , "CANTIDAD" , "VALOR UNITARIO"}));
		
//		listaProductos.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.RIGHT));
		//Manipular el alignment del texto de la tabla
		DefaultTableCellRenderer textoTablaDerecha = new DefaultTableCellRenderer();
		textoTablaDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
		listaProductos.getColumnModel().getColumn(2).setCellRenderer(textoTablaDerecha);
		
		DefaultTableCellRenderer textoTablaCentro = new DefaultTableCellRenderer();
		textoTablaCentro.setHorizontalAlignment(SwingConstants.CENTER);
		listaProductos.getColumnModel().getColumn(0).setCellRenderer(textoTablaCentro);
		listaProductos.getColumnModel().getColumn(1).setCellRenderer(textoTablaCentro);

		
		scrollProductos = new JScrollPane(listaProductos);
		scrollProductos.setBounds(190, 9, 342, 136);
		panelObjetos.add(scrollProductos);
		
		lblHeaderValorUnidad = new JLabel("<html>VALOR UNIDAD:</html>");
		lblHeaderValorUnidad.setOpaque(true);
		lblHeaderValorUnidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderValorUnidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderValorUnidad.setBorder(RAISED_BORDER);
		lblHeaderValorUnidad.setBounds(5, 79, 100, 20);
		panelObjetos.add(lblHeaderValorUnidad);
		
		textFieldValorUnidad = new JTextField();
		textFieldValorUnidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB) {
					// TODO: implement requestFocus due to next UI(User Interface)components
				}
				if (k.getKeyCode() == KeyEvent.VK_ENTER) {
					//Valor valido
					if (!textFieldValorUnidad.getText().matches(Num3.FORMATO_VALIDO)) {
						lblValorConsola.setForeground(Color.red);
						lblValorConsola.setText("<html>VALOR NO VALIDO</html>");
						lblValorConsola.setVisible(true);
						
					} else {
//						lblValorConsola.setForeground(Color.BLACK);
//						lblValorConsola.setText("");
						lblValorConsola.setVisible(false);
						agregarProductos((String)textFieldProductos.getText(), (Integer)cantProductos.getValue() , textFieldValorUnidad.getText());
//						scrollProductos.remove(listaProductos);
						listaProductos.setModel(new DefaultTableModel(datosProductos, new String[] {"NOMBRE" , "CANTIDAD" , "VALOR UNITARIO"}));
						
						//organiza la orientacion del texto de la Tabla
						listaProductos.getColumnModel().getColumn(0).setCellRenderer(textoTablaCentro);
						listaProductos.getColumnModel().getColumn(1).setCellRenderer(textoTablaCentro);
						listaProductos.getColumnModel().getColumn(2).setCellRenderer(textoTablaDerecha);
						//textFieldProductos.requestFocus();
						textFieldValorTotal.setText(sumarValorProductos());
						lblValorPalabras.setText(new Num3(sumarValorProductos()).getNumeroString());
						
						cantProductos.setValue((int) 1);
						textFieldValorUnidad.setText("");
						textFieldProductos.setText("");
						textFieldProductos.requestFocus();
						
					}

				}
			}
		});
		textFieldValorUnidad.setBounds(5, 98, 119, 19);
		panelObjetos.add(textFieldValorUnidad);
		textFieldValorUnidad.setColumns(10);
		
		lblValorConsola = new JLabel("");
		lblValorConsola.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorConsola.setOpaque(false);
		lblValorConsola.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValorConsola.setBounds(5, 125, 119, 20);
		panelObjetos.add(lblValorConsola);
		
		JSeparator separatorObjetos = new JSeparator();
		separatorObjetos.setOrientation(SwingConstants.VERTICAL);
		separatorObjetos.setBounds(173, 5, 1, 140);
		panelObjetos.add(separatorObjetos);
		
		
		/*
		 * PANEL VALOR
		 */
		panelValor = new JPanel();
		panelValor.setLayout(null);
		panelValor.setBounds(15, 465, 542, 58);
		panelValor.setBackground(COLOR_PANEL);
		tab1.add(panelValor);

		lblHeaderValor = new JLabel("<html>VALOR:      ");
		lblHeaderValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderValor.setBounds(5, 5, 100, lblHeaderFecha.getHeight());
		lblHeaderValor.setOpaque(true);
		lblHeaderValor.setBorder(RAISED_BORDER);
		panelValor.add(lblHeaderValor);

		
		textFieldValorTotal = new JTextField();
		textFieldValorTotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();

					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB) {
				}
				if (k.getKeyCode() == KeyEvent.VK_ENTER) {
					if (textFieldValorTotal.getText().matches(Num3.FORMATO_VALIDO)) {
						lblValorPalabras.setForeground(Color.BLACK);
						lblValorPalabras.setText(new Num3(textFieldValorTotal.getText()).getNumeroString());
					} else {
						lblValorPalabras.setForeground(Color.red);
						lblValorPalabras.setText("INTRODUZCA UN VALOR VALIDO");
					}

				}
			}
		});
		textFieldValorTotal.setColumns(10);
		textFieldValorTotal.setVisible(true);
		textFieldValorTotal.setEditable(false);
		textFieldValorTotal.setBounds(115, 7, 126, 20);
		panelValor.add(textFieldValorTotal);

		lblValorPalabras = new JLabel("");
		lblValorPalabras.setOpaque(false);
		lblValorPalabras.setBounds(5, 32, 527, 20);
		lblValorPalabras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelValor.add(lblValorPalabras);
		
		modificarValor = new JCheckBox("<html>MODIFICAR EL VALOR</html>");
		modificarValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(modificarValor.isSelected()) {
					textFieldValorTotal.setText("");
					textFieldValorTotal.setEditable(true);
				} else {
					textFieldValorTotal.setEditable(false);
					textFieldValorTotal.setText(sumarValorProductos());
				}
			
			}
		});
		modificarValor.setFont(new Font("Tahoma" , Font.PLAIN , 10));
		modificarValor.setBounds(272, 6, 135, 21);
		panelValor.add(modificarValor);

		
		
		/*
		 * TAB 2
		 * 
		 * TODO: aplicar un usuario y contraseña para poder leer el QR
		 */
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 421, 271, 19);
		tab2.add(passwordField);

	}

	/**
	 * Genera una lista de Dias(int) a partir de la cantidad de dias de dias por mes
	 * 
	 * @param cantDias
	 * @return
	 */
	private static String[] generarDias(int cantDias) {
		// FIXME: Condicional de año biziesto(no se como se escribe)
		String[] dias = new String[cantDias + 1];
		dias[0] = "DIA";
		for (int i = 1; i <= cantDias; i++) {
			if (i < 10) {
				dias[i] = "0" + String.valueOf(i);
			} else {
				dias[i] = String.valueOf(i);
			}
		}
//		for (String a : dias) {
//			System.out.println(a);
//		}
		return dias;

	}

	/**
	 * Genera una lista con años a partir del año actual
	 * 
	 * @return
	 */
	private static String[] generarAños() {
		LocalDateTime.now().getYear();
		int añoMinPredeterminado = -10000;
		int con = 0;
		String[] años = new String[LocalDateTime.now().getYear() - añoMinPredeterminado + 1];
		años[0] = "AÑO";
		for (int i = LocalDateTime.now().getYear(); i > añoMinPredeterminado; i--) {
			años[++con] = String.valueOf(i);
		}
//		System.out.println(años.length);
//		System.out.println("con:" + con);
//		StringBuilder sb = new StringBuilder();
//	       sb.append("Termino en prueba").append(LocalDateTime.now().getYear()-añoMinPredeterminado);
//	       sb.toString();
		/*
		 * for (String año : años) { sb.append(año).append(" "); }
		 * System.out.println(sb.toString());
		 */
		return años;
	}

	/**
	 * Establece el mes actual en la lista de Meses
	 * 
	 * @return
	 */
	private String[] findActualMonthForDay() {

		// Tienen 31 días: 1Enero, 3marzo, 5mayo, 7julio, 8agosto, 10octubre y
		// 12diciembre.
		// Tienen 30 días: 4Abril, 6junio, 9septiembre y 11noviembre
		switch (LocalDateTime.now().getMonthValue()) {
		// 31
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return DAYS31;
		// 30
		case 4:
		case 6:
		case 9:
		case 11:
			return DAYS30;

		// 29
		case 2:
			return DAYS29;

		default:
			return null;
		}
	}

	/**
	 * Actualiza la Lista de los dias, de acuerdo al mes seleccionado
	 * 
	 * @param boxMonth
	 * @param boxDay
	 */
	private void updateMonthDays(JComboBox<String> boxMonth, JComboBox<String> boxDay) {
		boxDay.removeAllItems();
		/*
		 * Tienen 31 días: 1Enero, 3marzo, 5mayo, 7julio, 8agosto, 10octubre y
		 * 12diciembre. Tienen 30 días: 4Abril, 6junio, 9septiembre y 11noviembre.
		 */
		switch (boxMonth.getSelectedIndex()) {
		// 31
//		case (1||3||5||7||8||10||12):
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
//			dias = DAY31;
//			System.out.println(31);
//			boxDay.removeAllItems();
//			for(String dia: dias) {
//				comboBoxDay.addItem(dia);
//			}
			for (String dia : DAYS31) {
				boxDay.addItem(dia);
			}
			break;
		// 30
//		case 4|6|9|11:
		case 4:
		case 6:
		case 9:
		case 11:
//			dias = DAY30;
//			System.out.println(30);
//			boxDay.removeAllItems();
//			for(String dia: dias) {
//				comboBoxDay.addItem(dia);
//			}
			for (String dia : DAYS30) {
				boxDay.addItem(dia);
			}
			break;
		case 2:
//			dias = DAY29;
//			boxDay.removeAllItems();
//			for(String dia: dias) {
//				comboBoxDay.addItem(dia);
//			}
			for (String dia : DAYS29) {
				boxDay.addItem(dia);
			}
			break;
		default:
//			throw new IllegalArgumentException("Unexpected value: " + comboBoxMonth.getSelectedIndex());
//			dias = null;
//			boxDay.removeAllItems();
			boxDay.addItem("DIA");
			break;
		}
	}
	/**
	 * Agregar productos a la lista de productos
	 * @param nombreProducto
	 * @param cantidadProducto
	 * @param valorProducto
	 */
	private static void agregarProductos(String nombreProducto, int cantidadProducto, String valorProducto) {
		// Nombre | Cantidad | valorUnidad | valorSemiTotal
		String[][] matrizTemporal = datosProductos.clone();
		
		String nombre = nombreProducto.toUpperCase();
		String cantidad = String.valueOf(cantidadProducto);
		String valor = valorProducto;
		
		int columnas = datosProductos[0].length;
		int filas = datosProductos.length;
		
		datosProductos = null;
		datosProductos = new String[filas+1][columnas];
		//llenar columna 0
		for (int i = 0; i < filas; i++) {
//			datosProductos[i][0] = new String(matrizTemporal[i][0]);
			datosProductos[i][0] = matrizTemporal[i][0];
		}
		datosProductos[filas][0] = nombre;
		
		//llenar columna 1
		for (int i = 0; i < filas; i++) {
			datosProductos[i][1] = matrizTemporal[i][1];
		}
		datosProductos[filas][1] = cantidad;
		
		//llenar columna 2
		for (int i = 0; i < filas; i++) {
			datosProductos[i][2] = matrizTemporal[i][2];
		}
		datosProductos[filas][2] = valor;
		
	}
	
	public static String sumarValorProductos() {
		String[][] temporal = datosProductos.clone();
		int total = 0;
		//Tener en cuenta cantidad{columna 1} y valorUnidad{columna 2}  [formtato desde 0 hasta n]
		for (int i = 1 ; i<temporal.length ; i++) {
			total += (Integer.valueOf(temporal[i][1]) * Integer.valueOf(temporal[i][2]));
		}
		
		return String.valueOf(total);
	}
}