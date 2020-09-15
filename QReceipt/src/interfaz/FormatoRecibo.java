package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import numeros.Num5;

public class FormatoRecibo {
	String fecha;
	String nombre;
	String id;
	String direccion;

	private boolean isReciboShowing = false;

	static final String[] MONTHS = { "MES", "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO",
			"SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" };
	static final String[] DAYS31 = generarDias(31);

	static final String[] DAYS30 = generarDias(30);

	static final String[] DAYS29 = generarDias(29);

	private static String[] dias = findActualMonthForDay();

	static final String[] AÑOS = generarAños();

	private String[][] datosProductos = new String[0][3];

	public String[][] getDatosProductos() {
		return datosProductos.clone();
	}

//	private String[][] datosProductos = { { "TV", "10002", "8888" }, { "computador", "333", "888" },
//			{ "holla", "2", "100" } };

	static final Color COLOR_FRAME = new Color(217, 222, 222);
	static final Color COLOR_PANEL = new Color(202, 202, 202);
	static final int SEPARACION_FRAME = 24;
	static final Border RAISED_BORDER = BorderFactory.createRaisedBevelBorder();

	private JFrame frame;
	private JPanel panelFormato;

	// JPanels
	private JPanel panelFecha;
	private JPanel panelNombre;
	private JPanel panelID_NIT;
	private JPanel panelDireccion;
	private JPanel panelValor;
	private JPanel panelObjetos;

	// JLabels
	private JLabel lblHeaderNombre;
	private JLabel lblHeaderFecha;
	private JLabel lblHeaderID_NIT;
	private JLabel lblHeaderProductos;
	private JLabel lblHeaderCantidadProductos;
	private JLabel lblHeaderValor;
	private JLabel lblHeaderValorUnidad;
	private JLabel lblValorConsola;
	private JLabel lblValorPalabras;
	private JLabel lblDireccion;

	// JTextFields
	private JTextField textFieldNombre;
	private JTextField textFieldValorTotal;
	private JTextField textFieldProductos;
	private JTextField textFieldValorUnidad;
	private JTextField textFieldDireccion;
	private JTextField textFieldID_NIT;

	// JComboBox s Fechas
	private JComboBox<String> comboBoxMonth, comboBoxDay, comboBoxYear;

	public JComboBox<String> getComboBoxMonth() {
		return comboBoxMonth;
	}

	public JComboBox<String> getComboBoxDay() {
		return comboBoxDay;
	}

	public JComboBox<String> getComboBoxYear() {
		return comboBoxYear;
	}

	// JTable
	private JTable listaProductos;
	// JSpinner
	private JSpinner cantProductos;

	// JScrollPane
	private JScrollPane scrollProductos;

	// JCheckBox
	private JCheckBox modificarValor;

	// JButton
	private JButton btnGenerarRecibo;

	public JButton getBtnGenerarRecibo() {
		return btnGenerarRecibo;
	}

	public FormatoRecibo(JFrame frame, JPanel panelFormato) {

		this.frame = frame;
		this.panelFormato = panelFormato;

		initialize();
		textFieldNombre.requestFocus();
	}

	private void initialize() {

		/*
		 * PANEL FECHA
		 * 
		 * FIXME: use JDatePicker
		 * https://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-
		 * calendar-component
		 */
		panelFecha = new JPanel();
		panelFecha.setLayout(null);
		panelFecha.setVisible(true);
		panelFecha.setBounds(15, 32, 400, 30);
		panelFecha.setBackground(COLOR_PANEL);
		panelFormato.add(panelFecha);

		lblHeaderFecha = new JLabel("<html>FECHA:</html>");
		lblHeaderFecha.setOpaque(true);
		lblHeaderFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderFecha.setBorder(RAISED_BORDER);
		lblHeaderFecha.setBounds(5, 5, 100, panelFecha.getHeight() - 2 * 5);
		panelFecha.add(lblHeaderFecha);

		comboBoxMonth = new JComboBox<String>(MONTHS);
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
		comboBoxMonth.setFocusable(true);
		comboBoxMonth.setEnabled(true);
		comboBoxMonth.setVisible(true);
		comboBoxMonth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxMonth.setBounds(115, 5, 102, 21);
		panelFecha.add(comboBoxMonth);

		comboBoxDay = new JComboBox<String>(dias);
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
		comboBoxDay.setFocusable(true);
		comboBoxDay.setEnabled(true);
		comboBoxDay.setVisible(true);
		comboBoxDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxDay.setBounds(243, 5, 50, 21);
		panelFecha.add(comboBoxDay);

		comboBoxYear = new JComboBox<String>(AÑOS);
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
					textFieldNombre.requestFocus();
				}
			}
		});
		comboBoxYear.setFocusable(true);
		comboBoxYear.setEnabled(true);
		comboBoxYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxYear.setBounds(314, 5, 76, 21);
		panelFecha.add(comboBoxYear);

		/*
		 * PANEL NOMBRE
		 */
		panelNombre = new JPanel();
		panelNombre.setLayout(null);
//		panelNombre.setBounds(15, 103, 538, 30);
		panelNombre.setVisible(true);
		panelNombre.setBounds(15, 72, panelFecha.getWidth(), panelFecha.getHeight());
		panelNombre.setBackground(COLOR_PANEL);
		panelFormato.add(panelNombre);

		lblHeaderNombre = new JLabel("<html>Nombre:</html>".toUpperCase());// 6 Spaces
		lblHeaderNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderNombre.setBounds(5, 5, 100, panelNombre.getHeight() - 2 * 5);
		lblHeaderNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderNombre.setOpaque(true);
		lblHeaderNombre.setBorder(RAISED_BORDER);
		panelNombre.add(lblHeaderNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();

					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldID_NIT.requestFocus();
				}
			}
		});
//		textFieldNombre.setBounds((lblNombre.getWidth() + 5), 6, 350, 19);
		textFieldNombre.setBounds((lblHeaderNombre.getWidth() + 5), 6, 350 - 100, 19);
		textFieldNombre.setFocusable(true);
		textFieldNombre.setEnabled(true);
		panelNombre.add(textFieldNombre);

		/*
		 * PANEL ID_NIT
		 */
		panelID_NIT = new JPanel();
		panelID_NIT.setLayout(null);
		panelID_NIT.setVisible(true);
		panelID_NIT.setBackground(COLOR_PANEL);
//		panelID_NIT.setBounds(panelNombre.getX(), 143, panelNombre.getWidth(), 30);
		panelID_NIT.setBounds(15, 112, panelFecha.getWidth(), panelFecha.getHeight());
		panelFormato.add(panelID_NIT);

		lblHeaderID_NIT = new JLabel("<html>ID / NIT:</html>");
		lblHeaderID_NIT.setOpaque(true);
		lblHeaderID_NIT.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderID_NIT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderID_NIT.setBorder(RAISED_BORDER);
		lblHeaderID_NIT.setBounds(5, 5, 100, panelID_NIT.getHeight() - 2 * 5);
		panelID_NIT.add(lblHeaderID_NIT);

		textFieldID_NIT = new JTextField();
		textFieldID_NIT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();

					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldDireccion.requestFocus();
				}
			}
		});
		textFieldID_NIT.setColumns(10);
//		textFielID_NIT.setBounds(105, 6, 350, 19);
		textFieldID_NIT.setBounds(105, 6, 350 - 100, 19);
		textFieldID_NIT.setFocusable(true);
		textFieldID_NIT.setEnabled(true);
		panelID_NIT.add(textFieldID_NIT);

		/*
		 * PANEL DIRECCION
		 */
		panelDireccion = new JPanel();
		panelDireccion.setLayout(null);
		panelDireccion.setVisible(true);
		panelDireccion.setBackground(new Color(202, 202, 202));
		panelDireccion.setBounds(15, 152, 400, 30);
		panelFormato.add(panelDireccion);

		lblDireccion = new JLabel("<html>DIRECCION:</html>");
		lblDireccion.setOpaque(true);
		lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDireccion.setBorder(RAISED_BORDER);
		lblDireccion.setBounds(5, 5, 100, 20);
		panelDireccion.add(lblDireccion);

		textFieldDireccion = new JTextField();
		textFieldDireccion.addKeyListener(new KeyAdapter() {
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
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBounds(105, 6, 250, 19);
		textFieldDireccion.setFocusable(true);
		textFieldDireccion.setEnabled(true);
		panelDireccion.add(textFieldDireccion);

		/*
		 * PANEL OBJETOS
		 */
		panelObjetos = new JPanel();
		panelObjetos.setBounds(panelFecha.getX(), 223, 542, 157);
		panelObjetos.setBackground(COLOR_PANEL);
		panelObjetos.setVisible(true);
		panelFormato.add(panelObjetos);
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
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {
					// cantProductos.getEditor().requestFocus();
					cantProductos.setValue((int) 1);
					textFieldValorUnidad.requestFocus();

				}
			}
		});
		textFieldProductos.setBounds(5, 25, 140, 20);
		textFieldProductos.setFocusable(true);
		textFieldProductos.setEnabled(true);
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
		cantProductos.setBounds((lblHeaderCantidadProductos.getX() + lblHeaderCantidadProductos.getWidth() + 3),
				lblHeaderCantidadProductos.getY(), 40, lblHeaderCantidadProductos.getHeight());
		cantProductos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cantProductos.setValue((int) 1);
		cantProductos.setFocusable(true);
		cantProductos.setEnabled(true);
		panelObjetos.add(cantProductos);

		listaProductos = new JTable();
		listaProductos.setEnabled(false);
		listaProductos.setLayout(new FlowLayout());
		listaProductos.setBounds(173, 9, 359, 62);
		listaProductos.setModel(new DefaultTableModel(null, new String[] { "NOMBRE", "CANTIDAD", "VALOR UNITARIO" }));
//		listaProductos.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.RIGHT));
		// Manipular el alignment del texto de la tabla
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
		textFieldValorUnidad.setColumns(10);
		textFieldValorUnidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					System.exit(0);

					// requestFocus of next Component
				} else if (k.getKeyCode() == KeyEvent.VK_TAB) {
					btnGenerarRecibo.requestFocus();
					if (modificarValor.isSelected()) {
						textFieldValorTotal.setFocusable(true);
						textFieldValorTotal.requestFocus();
					} else {
						btnGenerarRecibo.requestFocus();
					}
				} else if (k.getKeyCode() == KeyEvent.VK_ENTER) {
					// Valor valido
					if (!textFieldValorUnidad.getText().matches(Num5.FORMATO_VALIDO)) {
						lblValorConsola.setForeground(Color.red);
						lblValorConsola.setText("<html>VALOR NO VALIDO</html>");
						lblValorConsola.setVisible(true);

					} else {
						lblValorConsola.setForeground(Color.BLACK);

						lblValorConsola.setVisible(false);
						agregarProductos((String) textFieldProductos.getText(), (Integer) cantProductos.getValue(),
								textFieldValorUnidad.getText());
						// scrollProductos.remove(listaProductos);
						listaProductos.setModel(new DefaultTableModel(datosProductos,
								new String[] { "CANTIDAD", "NOMBRE", "VALOR UNITARIO" }));

						// organiza la orientacion del texto de la Tabla
						listaProductos.getColumnModel().getColumn(0).setCellRenderer(textoTablaCentro);
						listaProductos.getColumnModel().getColumn(1).setCellRenderer(textoTablaCentro);
						listaProductos.getColumnModel().getColumn(2).setCellRenderer(textoTablaDerecha);
						// textFieldProductos.requestFocus();
						textFieldValorTotal.setText(sumarValorProductos());
						lblValorPalabras.setText(new Num5(sumarValorProductos()).getNumeroString());

						cantProductos.setValue((int) 1);
						textFieldValorUnidad.setText("");
						textFieldProductos.setText("");
						textFieldProductos.requestFocus();

					}

				}
			}
		});
		textFieldValorUnidad.setBounds(5, 98, 119, 19);
		textFieldValorUnidad.setFocusable(true);
		textFieldValorUnidad.setEnabled(true);
		panelObjetos.add(textFieldValorUnidad);

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
		panelValor.setBounds(15, 424, 542, 58);
		panelValor.setBackground(COLOR_PANEL);
		panelFormato.add(panelValor);

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
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {

					if (textFieldValorTotal.getText().matches(Num5.FORMATO_VALIDO)) {
						lblValorPalabras.setForeground(Color.BLACK);
						lblValorPalabras.setText(new Num5(textFieldValorTotal.getText()).getNumeroString());
						btnGenerarRecibo.requestFocus();
					} else {
						lblValorPalabras.setForeground(Color.red);
						lblValorPalabras.setText("INTRODUZCA UN VALOR VALIDO");
						textFieldValorTotal.setText("");
					}

				}
			}
		});
		textFieldValorTotal.setColumns(10);
		textFieldValorTotal.setFocusable(false);
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

				if (modificarValor.isSelected()) {
					textFieldValorTotal.setText("");
					textFieldValorTotal.setFocusable(true);
					textFieldValorTotal.setEditable(true);
					textFieldValorTotal.requestFocus();
				} else {
					textFieldValorTotal.setEditable(false);
					textFieldValorTotal.setFocusable(false);
					textFieldValorTotal.setText(sumarValorProductos());
				}

			}
		});
		modificarValor.setFocusable(false);
		modificarValor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		modificarValor.setBounds(272, 6, 135, 21);
		panelValor.add(modificarValor);

		btnGenerarRecibo = new JButton("<html>GENERAR RECIBO</html>");
		btnGenerarRecibo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();

					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB) {
					textFieldNombre.requestFocus();
				}
				if (k.getKeyCode() == KeyEvent.VK_ENTER) {

					if (comboBoxMonth.getSelectedIndex() != 0 && comboBoxDay.getSelectedIndex() != 0
							&& comboBoxYear.getSelectedIndex() != 0) {
//						generar();
					}

				}
			}
		});
		btnGenerarRecibo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (comboBoxMonth.getSelectedIndex() != 0 && comboBoxDay.getSelectedIndex() != 0
						&& comboBoxYear.getSelectedIndex() != 0) {

//					generar();

				}
			}
		});
		btnGenerarRecibo.setBounds(471, 513, 86, 40);
		btnGenerarRecibo.setFocusable(true);
		btnGenerarRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		btnGenerarRecibo.setVerticalAlignment(SwingConstants.CENTER);
		btnGenerarRecibo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelFormato.add(btnGenerarRecibo);

	}

	/*
	 * PARA PRUEBAS INTERNAS
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormatoRecibo window = new FormatoRecibo();
					window.frame.setVisible(true);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}

	private FormatoRecibo() {
		pruebasInternas();
	}

	private void pruebasInternas() {

		frame = new JFrame();
		frame.setBounds(100, 100, 587, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(this.getClass().getCanonicalName());
		frame.setBackground(new Color(0, 0, 0));
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setBackground(COLOR_FRAME);

		JPanel panelFormato = new JPanel();
		panelFormato.setLayout(null);
		panelFormato.setVisible(true);
		panelFormato.setBackground(new Color(255, 255, 255));
		panelFormato.setBounds(0, 0, 578, 577);
		frame.getContentPane().add(panelFormato);

		/**
		 * 
		 * DESDE AQUI SE COPIA
		 * 
		 */

		/*
		 * PANEL FECHA
		 * 
		 * FIXME: use JDatePicker
		 * https://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-
		 * calendar-component
		 */
		panelFecha = new JPanel();
		panelFecha.setLayout(null);
		panelFecha.setVisible(true);
		panelFecha.setBounds(15, 32, 400, 30);
		panelFecha.setBackground(COLOR_PANEL);
		panelFormato.add(panelFecha);

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
		comboBoxMonth.setFocusable(true);
		comboBoxMonth.setEnabled(true);
		comboBoxMonth.setVisible(true);
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
		comboBoxDay.setFocusable(true);
		comboBoxDay.setEnabled(true);
		comboBoxDay.setVisible(true);
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
					textFieldNombre.requestFocus();
				}
			}
		});
		comboBoxYear.setFocusable(true);
		comboBoxYear.setEnabled(true);
		comboBoxYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxYear.setBounds(314, 5, 76, 21);
		panelFecha.add(comboBoxYear);

		/*
		 * PANEL NOMBRE
		 */
		panelNombre = new JPanel();
		panelNombre.setLayout(null);
//		panelNombre.setBounds(15, 103, 538, 30);
		panelNombre.setVisible(true);
		panelNombre.setBounds(15, 72, panelFecha.getWidth(), panelFecha.getHeight());
		panelNombre.setBackground(COLOR_PANEL);
		panelFormato.add(panelNombre);

		lblHeaderNombre = new JLabel("<html>Nombre:</html>".toUpperCase());// 6 Spaces
		lblHeaderNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderNombre.setBounds(5, 5, 100, panelNombre.getHeight() - 2 * 5);
		lblHeaderNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderNombre.setOpaque(true);
		lblHeaderNombre.setBorder(RAISED_BORDER);
		panelNombre.add(lblHeaderNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();

					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldID_NIT.requestFocus();
				}
			}
		});
//		textFieldNombre.setBounds((lblNombre.getWidth() + 5), 6, 350, 19);
		textFieldNombre.setBounds((lblHeaderNombre.getWidth() + 5), 6, 350 - 100, 19);
		textFieldNombre.setFocusable(true);
		textFieldNombre.setEnabled(true);
		panelNombre.add(textFieldNombre);

		/*
		 * PANEL ID_NIT
		 */
		panelID_NIT = new JPanel();
		panelID_NIT.setLayout(null);
		panelID_NIT.setVisible(true);
		panelID_NIT.setBackground(COLOR_PANEL);
//		panelID_NIT.setBounds(panelNombre.getX(), 143, panelNombre.getWidth(), 30);
		panelID_NIT.setBounds(15, 112, panelFecha.getWidth(), panelFecha.getHeight());
		panelFormato.add(panelID_NIT);

		lblHeaderID_NIT = new JLabel("<html>ID / NIT:</html>");
		lblHeaderID_NIT.setOpaque(true);
		lblHeaderID_NIT.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderID_NIT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeaderID_NIT.setBorder(RAISED_BORDER);
		lblHeaderID_NIT.setBounds(5, 5, 100, panelID_NIT.getHeight() - 2 * 5);
		panelID_NIT.add(lblHeaderID_NIT);

		textFieldID_NIT = new JTextField();
		textFieldID_NIT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();

					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldDireccion.requestFocus();
				}
			}
		});
		textFieldID_NIT.setColumns(10);
//		textFielID_NIT.setBounds(105, 6, 350, 19);
		textFieldID_NIT.setBounds(105, 6, 350 - 100, 19);
		textFieldID_NIT.setFocusable(true);
		textFieldID_NIT.setEnabled(true);
		panelID_NIT.add(textFieldID_NIT);

		/*
		 * PANEL DIRECCION
		 */
		panelDireccion = new JPanel();
		panelDireccion.setLayout(null);
		panelDireccion.setVisible(true);
		panelDireccion.setBackground(new Color(202, 202, 202));
		panelDireccion.setBounds(15, 152, 400, 30);
		panelFormato.add(panelDireccion);

		lblDireccion = new JLabel("<html>DIRECCION:</html>");
		lblDireccion.setOpaque(true);
		lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDireccion.setBorder(RAISED_BORDER);
		lblDireccion.setBounds(5, 5, 100, 20);
		panelDireccion.add(lblDireccion);

		textFieldDireccion = new JTextField();
		textFieldDireccion.addKeyListener(new KeyAdapter() {
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
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBounds(105, 6, 250, 19);
		textFieldDireccion.setFocusable(true);
		textFieldDireccion.setEnabled(true);
		panelDireccion.add(textFieldDireccion);

		/*
		 * PANEL OBJETOS
		 */
		panelObjetos = new JPanel();
		panelObjetos.setBounds(panelFecha.getX(), 223, 542, 157);
		panelObjetos.setBackground(COLOR_PANEL);
		panelObjetos.setVisible(true);
		panelFormato.add(panelObjetos);
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
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {
					// cantProductos.getEditor().requestFocus();
					cantProductos.setValue((int) 1);
					textFieldValorUnidad.requestFocus();

				}
			}
		});
		textFieldProductos.setBounds(5, 25, 140, 20);
		textFieldProductos.setFocusable(true);
		textFieldProductos.setEnabled(true);
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
		cantProductos.setBounds((lblHeaderCantidadProductos.getX() + lblHeaderCantidadProductos.getWidth() + 3),
				lblHeaderCantidadProductos.getY(), 40, lblHeaderCantidadProductos.getHeight());
		cantProductos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cantProductos.setValue((int) 1);
		cantProductos.setFocusable(true);
		cantProductos.setEnabled(true);
		panelObjetos.add(cantProductos);

		listaProductos = new JTable();
		listaProductos.setEnabled(false);
		listaProductos.setLayout(new FlowLayout());
		listaProductos.setBounds(173, 9, 359, 62);
		listaProductos.setModel(new DefaultTableModel(null, new String[] { "CANTIDAD", "NOMBRE", "VALOR UNITARIO" }));
//		listaProductos.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.RIGHT));
		// Manipular el alignment del texto de la tabla
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
		textFieldValorUnidad.setColumns(10);
		textFieldValorUnidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					System.exit(0);

					// requestFocus of next Component
				} else if (k.getKeyCode() == KeyEvent.VK_TAB) {
					btnGenerarRecibo.requestFocus();
					if (modificarValor.isSelected()) {
						textFieldValorTotal.setFocusable(true);
						textFieldValorTotal.requestFocus();
					} else {
						btnGenerarRecibo.requestFocus();
					}
				} else if (k.getKeyCode() == KeyEvent.VK_ENTER) {
					// Valor valido
					if (!textFieldValorUnidad.getText().matches(Num5.FORMATO_VALIDO)) {
						lblValorConsola.setForeground(Color.red);
						lblValorConsola.setText("<html>VALOR NO VALIDO</html>");
						lblValorConsola.setVisible(true);

					} else {
						lblValorConsola.setForeground(Color.BLACK);

						lblValorConsola.setVisible(false);
						agregarProductos((String) textFieldProductos.getText(), (Integer) cantProductos.getValue(),
								textFieldValorUnidad.getText());
						// scrollProductos.remove(listaProductos);
						listaProductos.setModel(new DefaultTableModel(datosProductos,
								new String[] { "CANTIDAD", "NOMBRE", "VALOR UNITARIO" }));

						// organiza la orientacion del texto de la Tabla
						listaProductos.getColumnModel().getColumn(0).setCellRenderer(textoTablaCentro);
						listaProductos.getColumnModel().getColumn(1).setCellRenderer(textoTablaCentro);
						listaProductos.getColumnModel().getColumn(2).setCellRenderer(textoTablaDerecha);
						// textFieldProductos.requestFocus();
						textFieldValorTotal.setText(sumarValorProductos());
						lblValorPalabras.setText(new Num5(sumarValorProductos()).getNumeroString());

						cantProductos.setValue((int) 1);
						textFieldValorUnidad.setText("");
						textFieldProductos.setText("");
						textFieldProductos.requestFocus();

					}

				}
			}
		});
		textFieldValorUnidad.setBounds(5, 98, 119, 19);
		textFieldValorUnidad.setFocusable(true);
		textFieldValorUnidad.setEnabled(true);
		panelObjetos.add(textFieldValorUnidad);

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
		panelValor.setBounds(15, 424, 542, 58);
		panelValor.setBackground(COLOR_PANEL);
		panelFormato.add(panelValor);

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
				if (k.getKeyCode() == KeyEvent.VK_TAB || k.getKeyCode() == KeyEvent.VK_ENTER) {

					if (textFieldValorTotal.getText().matches(Num5.FORMATO_VALIDO)) {
						lblValorPalabras.setForeground(Color.BLACK);
						lblValorPalabras.setText(new Num5(textFieldValorTotal.getText()).getNumeroString());
						btnGenerarRecibo.requestFocus();
					} else {
						lblValorPalabras.setForeground(Color.red);
						lblValorPalabras.setText("INTRODUZCA UN VALOR VALIDO");
						textFieldValorTotal.setText("");
					}

				}
			}
		});
		textFieldValorTotal.setColumns(10);
		textFieldValorTotal.setFocusable(false);
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

				if (modificarValor.isSelected()) {
					textFieldValorTotal.setText("");
					textFieldValorTotal.setFocusable(true);
					textFieldValorTotal.setEditable(true);
					textFieldValorTotal.requestFocus();
				} else {
					textFieldValorTotal.setEditable(false);
					textFieldValorTotal.setFocusable(false);
					textFieldValorTotal.setText(sumarValorProductos());
				}

			}
		});
		modificarValor.setFocusable(false);
		modificarValor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		modificarValor.setBounds(272, 6, 135, 21);
		panelValor.add(modificarValor);

		btnGenerarRecibo = new JButton("<html>GENERAR RECIBO</html>");
		btnGenerarRecibo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();

					System.exit(0);
				}
				// requestFocus of next Component
				if (k.getKeyCode() == KeyEvent.VK_TAB) {
					textFieldNombre.requestFocus();
				}
				if (k.getKeyCode() == KeyEvent.VK_ENTER) {

					if (comboBoxMonth.getSelectedIndex() != 0 && comboBoxDay.getSelectedIndex() != 0
							&& comboBoxYear.getSelectedIndex() != 0) {
//						generar();
					}

				}
			}
		});
		btnGenerarRecibo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (comboBoxMonth.getSelectedIndex() != 0 && comboBoxDay.getSelectedIndex() != 0
						&& comboBoxYear.getSelectedIndex() != 0) {

//					generar();

				}
			}
		});
		btnGenerarRecibo.setBounds(471, 513, 86, 40);
		btnGenerarRecibo.setFocusable(true);
		btnGenerarRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		btnGenerarRecibo.setVerticalAlignment(SwingConstants.CENTER);
		btnGenerarRecibo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelFormato.add(btnGenerarRecibo);
	}

	//

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
	private static String[] findActualMonthForDay() {

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

//	private void agregarProductos(String nombreProducto, int cantidadProducto, String valorProducto) {
//		
//		// fila zero
//		// Nombre | Cantidad | valorUnidad | valorSemiTotal
//		String[][] matrizTemporal = datosProductos.clone();
//
//		String nombre = nombreProducto.toUpperCase();
//		String cantidad = String.valueOf(cantidadProducto);
//		String valor = valorProducto;
//
//		int columnas = datosProductos[0].length;
//		int filas = datosProductos.length;
//
//		datosProductos = null;
//		datosProductos = new String[filas + 1][columnas];
//		// llenar columna 0
//		for (int i = 0; i < filas; i++) {
////			datosProductos[i][0] = new String(matrizTemporal[i][0]);
//			datosProductos[i][0] = matrizTemporal[i][0];
//		}
//		datosProductos[filas][0] = nombre;
//
//		// llenar columna 1
//		for (int i = 0; i < filas; i++) {
//			datosProductos[i][1] = matrizTemporal[i][1];
//		}
//		datosProductos[filas][1] = cantidad;
//
//		// llenar columna 2
//		for (int i = 0; i < filas; i++) {
//			datosProductos[i][2] = matrizTemporal[i][2];
//		}
//		datosProductos[filas][2] = valor;
//
//	}

//	private void agregarProduct(String nombreProducto, int cantidadProducto, String valorProductoUni) {
//		// CANTIDAD[0], NOMBRE[1], VALORUNITARIO[2]
//		String[][] matrizTemporal = datosProductos.clone();
//
//		String nombre = nombreProducto.toUpperCase();
//		String cantidad = String.valueOf(cantidadProducto);
//		String valoruUni = valorProductoUni;
//
//		int columnas = datosProductos[0].length;// 3
//		int filas = datosProductos.length;// 1
////					datosProductos.length+1;//1 + 1//2
//
//		datosProductos = null;
//		datosProductos = new String[filas + 1][columnas];// [2][3] -1/// [2][3]
//
//		for (int i = 0; i < filas; i++) {
////			datosProductos[i][0] = new String(matrizTemporal[i][0]);
//			datosProductos[i][0] = matrizTemporal[i][0];
//		}
//		datosProductos[filas][0] = cantidad;
//
//		// llenar columna 1
//		for (int i = 0; i < filas; i++) {
//			datosProductos[i][1] = matrizTemporal[i][1];
//		}
//		datosProductos[filas][1] = nombre;
//
//		// llenar columna 2
//		for (int i = 0; i < filas; i++) {
//			datosProductos[i][2] = matrizTemporal[i][2];
//		}
//		datosProductos[filas][2] = valoruUni;
//	}

	/**
	 * Agregar productos a la lista de productos
	 * 
	 * @param nombreProducto
	 * @param cantidadProducto
	 * @param valorProducto
	 */
	private void agregarProductos(String nombreProducto, int cantidadProducto, String valorProductoUni) {
		// CANTIDAD[0], NOMBRE[1], VALORUNITARIO[2]
		String[][] matTemporal = this.datosProductos.clone();

		String nombre = nombreProducto.toUpperCase();
		String cantidad = String.valueOf(cantidadProducto);
		String valoruUni = valorProductoUni;

		int columnas = 3;// matrizTemporal[0].lentgth
		int filas = (matTemporal.length + 1);

		this.datosProductos = null;
		this.datosProductos = new String[filas][columnas];

		// [i][0]
		for (int i = 0; i < matTemporal.length; i++) {
			this.datosProductos[i][0] = matTemporal[i][0];
		}
		this.datosProductos[matTemporal.length][0] = cantidad;

		// [i][1]
		for (int i = 0; i < matTemporal.length; i++) {
			this.datosProductos[i][1] = matTemporal[i][1];
		}
		this.datosProductos[matTemporal.length][1] = nombre;

		// [i][2]
		for (int i = 0; i < matTemporal.length; i++) {
			this.datosProductos[i][2] = matTemporal[i][2];
		}
		this.datosProductos[matTemporal.length][2] = valoruUni;

	}

//	private String sumarValorProductos() {
//		String[][] temporal = datosProductos.clone();
//		int total = 0;
//		//
//		// Tener en cuenta cantidad{columna 1} y valorUnidad{columna 2} [formtato desde
//		// 0 hasta n]
//		
//		for (int i = 1; i < temporal.length; i++) {
//			total += (Integer.valueOf(temporal[i][1]) * Integer.valueOf(temporal[i][2]));
//		}
//
//		return String.valueOf(total);
//	}

	private String sumarValorProductos() {
		String[][] temporal = this.datosProductos.clone();
		// CANTIDAD[0], NOMBRE[1], VALORUNITARIO[2]
		int total = 0;
		// int i = 0;
		for (int i = 0; i < temporal.length; i++) {
//			[i][0] cantidad , [i][2] valorUnidad
			total += (Integer.valueOf(temporal[i][0]) * Integer.valueOf(temporal[i][2]));
		}

		return String.valueOf(total);
	}

	public JComponent[] getComponents() {
		JComponent[] componentes = new JComponent[12];
		componentes[0] = panelFormato;
		componentes[1] = comboBoxMonth;
		componentes[2] = comboBoxDay;
		componentes[3] = comboBoxYear;
		componentes[4] = textFieldNombre;
		componentes[5] = textFieldID_NIT;
		componentes[6] = textFieldDireccion;
		componentes[7] = textFieldProductos;
		componentes[8] = cantProductos;
		componentes[9] = textFieldValorUnidad;
		componentes[10] = modificarValor;
		componentes[11] = btnGenerarRecibo;

		return componentes;
	}

	public String[] getInfoCliente() {
		StringBuilder sb = new StringBuilder();
		String fecha;
		String nombre;
		String id;
		String direccion;

		sb.append(comboBoxDay.getSelectedItem().toString()).append(" / ");
		sb.append(comboBoxMonth.getSelectedItem().toString()).append(" / ");
		sb.append(comboBoxMonth.getSelectedIndex()).append(" / ");
		sb.append(comboBoxYear.getSelectedItem().toString());
		fecha = sb.toString();
//		System.out.println(fecha);
		if (textFieldNombre.getText() == null) {
			nombre = "";
		} else {
			nombre = textFieldNombre.getText();
		}
		if (textFieldID_NIT.getText() == null) {
			id = "";
		} else {
			id = textFieldID_NIT.getText();
		}
		if (textFieldDireccion.getText() == null) {
			direccion = "";
		} else {
			direccion = textFieldDireccion.getText();
		}

		return new String[] { fecha, nombre, id, direccion };
	}
}