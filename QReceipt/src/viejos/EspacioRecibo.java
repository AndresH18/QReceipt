package viejos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class EspacioRecibo {
	
	String[][] datosProductos = {{"TV" , "10002" , "8888"} , {"computador" , "333" , "888"} , {"holla" , "2" , "100"}};

	static final Font FUENTE_PLAIN_12 = new Font("Tahoma", Font.PLAIN, 12);
	static final Font FUENTE_BOLD_12 = new Font("Tahoma", Font.BOLD, 12);

	
	private String fechaString;
	private String nombreString;
	private String idString;
	private String direccionString;

	private JFrame frame;
	private JPanel panelRecibo;// panelGenerarRecibo
	

	private JPanel header;
	private JLabel lblEmpresaCliente;
	private JLabel lblEmpresaClienteInfo;
	private JSeparator separator1;

	private JPanel body;
	private JLabel lblHeadFactura;
	private JPanel clienteInfo;
	private JLabel numFactura;
	private JLabel lblFecha;
	private JLabel lblCliente;
	private JLabel lbl_ID;
	private JLabel lblDiereccion;
	private JLabel fecha;
	private JLabel nombre;
	private JLabel id;
	private JLabel direccion;
	private JLabel numFact;
	
	private JSeparator separator2;
	private JPanel panelObjetos;
	private JLabel lblObjetosHeader1 , lblObjetosHeader2 , lblObjetosHeader3;

	private JButton btnRegresar;
	private JTable tabla;
	private JScrollPane scrollPane;
	
	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public EspacioRecibo(JFrame frame, JPanel panelRecibo) {
		this.frame = frame;
		this.panelRecibo = panelRecibo;
		initialize();
	}

	/**
	 * 
	 * @param frame
	 * @param panel
	 * @param datos
	 */
	public EspacioRecibo(JFrame frame, JPanel panel, String... datos) {
		// orden datos: fecha, nombre, id, direccion;
		this.frame = frame;
		this.panelRecibo = panel;
		this.fechaString = datos[0];
		this.nombreString = datos[1];
		this.idString = datos[2];
		this.direccionString = datos[3];
		initialize();
//		start();
	}

	/**
	 * 
	 * @param frame
	 * @param panel
	 * @param fecha
	 * @param nombre
	 * @param id
	 * @param direccion
	 */
	public EspacioRecibo(JFrame frame, JPanel panel, String fecha, String nombre, String id, String direccion) {
		// orden datos: fecha, nombre, id, direccion;
		this.frame = frame;
		this.panelRecibo = panel;
		this.fechaString = fecha;
		this.nombreString = nombre;
		this.idString = id;
		this.direccionString = direccion;
		initialize();
//		start();

	}

	// TODO: Pasar lo que esta en interfaz para aca. Adicionar los las variables a
	// los JLabels
	private void initialize() {

		header = new JPanel();
		header.setBounds(10, 0, 557, 90);
		header.setLayout(null);
		header.setBackground(new Color(255, 255, 255));
		panelRecibo.add(header);

		lblEmpresaCliente = new JLabel("New label");
		lblEmpresaCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresaCliente.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresaCliente.setFont(FUENTE_BOLD_12);
		lblEmpresaCliente.setBounds(0, 5, 557, 20);
		header.add(lblEmpresaCliente);

		lblEmpresaClienteInfo = new JLabel("New label");
		lblEmpresaClienteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresaClienteInfo.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresaClienteInfo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpresaClienteInfo.setBounds(0, 23, header.getWidth(), 69);
		header.add(lblEmpresaClienteInfo);

		separator1 = new JSeparator();
		separator1.setBounds(10, 90, frame.getWidth() - 40, 2);
		panelRecibo.add(separator1);

		body = new JPanel();
		body.setBounds(10, 95, 558, 407);
		body.setBackground(new Color(255, 255, 255));
		body.setLayout(null);
		panelRecibo.add(body);

		numFactura = new JLabel("NUMERO: ");
		numFactura.setVerticalAlignment(SwingConstants.TOP);
		numFactura.setHorizontalAlignment(SwingConstants.LEFT);
		numFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFactura.setBounds(390, 0, 62, 116);
		body.add(numFactura);

		lblHeadFactura = new JLabel("FACTURA DE VENTA");
		lblHeadFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadFactura.setVerticalAlignment(SwingConstants.TOP);
		lblHeadFactura.setFont(FUENTE_BOLD_12);
		lblHeadFactura.setBounds(0, 0, 558, 15);
		body.add(lblHeadFactura);

		clienteInfo = new JPanel();
		clienteInfo.setBounds(0, 16, 558, 127);
		body.add(clienteInfo);
		clienteInfo.setLayout(null);

		lblFecha = new JLabel("FECHA");
		lblFecha.setFont(FUENTE_PLAIN_12);
		lblFecha.setBounds(76, 10, 100, 13);
		clienteInfo.add(lblFecha);

		lblCliente = new JLabel("CLIENTE");
		lblCliente.setFont(FUENTE_PLAIN_12);
		lblCliente.setBounds(76, 33, 100, 13);
		clienteInfo.add(lblCliente);

		lbl_ID = new JLabel("C.C / NIT");
		lbl_ID.setFont(FUENTE_PLAIN_12);
		lbl_ID.setBounds(76, 56, 104, 13);
		clienteInfo.add(lbl_ID);

		lblDiereccion = new JLabel("DIRECCION");
		lblDiereccion.setFont(FUENTE_PLAIN_12);
		lblDiereccion.setBounds(76, 79, 100, 13);
		clienteInfo.add(lblDiereccion);

		fecha = new JLabel("FECHA");
		fecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fecha.setBounds(186, 11, 200, 13);
		clienteInfo.add(fecha);

		nombre = new JLabel("NOMBRE");
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nombre.setBounds(186, 34, 200, 13);
		clienteInfo.add(nombre);

		id = new JLabel("id");
		id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		id.setBounds(186, 57, 200, 13);
		clienteInfo.add(id);

		direccion = new JLabel("DIRECCION");
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		direccion.setBounds(186, 80, 200, 13);
		clienteInfo.add(direccion);

		numFact = new JLabel("");
		numFact.setVerticalAlignment(SwingConstants.TOP);
		numFact.setHorizontalAlignment(SwingConstants.LEFT);
		numFact.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFact.setBounds(448, 2, 100, 15);
		body.add(numFact);
		
//		panelObjetos = new JPanel();
//		panelObjetos.setBounds(0, 174, 558, 233);
//		body.add(panelObjetos);
//		panelObjetos.setLayout(null);
		
		
		
		separator2 = new JSeparator();
		separator2.setBounds(0, 153,frame.getWidth() - 40, 2);
		body.add(separator2);
		
		lblObjetosHeader1 = new JLabel("CANTIDAD");//10 espacios    -    20 espacios
		lblObjetosHeader1.setBounds(10, 158, 180, 15);
		lblObjetosHeader1.setHorizontalAlignment(SwingConstants.LEFT);
		lblObjetosHeader1.setVerticalAlignment(SwingConstants.CENTER);
		lblObjetosHeader1.setFont(FUENTE_BOLD_12);
		body.add(lblObjetosHeader1);
		
		lblObjetosHeader2 = new JLabel("DESCRIPCION");//10 espacios    -    20 espacios
		lblObjetosHeader2.setBounds(200, 158, 238, 15);
		lblObjetosHeader2.setHorizontalAlignment(SwingConstants.LEFT);
		lblObjetosHeader2.setVerticalAlignment(SwingConstants.CENTER);
		lblObjetosHeader2.setFont(FUENTE_BOLD_12);
		body.add(lblObjetosHeader2);
		
		lblObjetosHeader3 = new JLabel("VALOR");//10 espacios    -    20 espacios
		lblObjetosHeader3.setBounds(448, 158, 110, 15);
		lblObjetosHeader3.setHorizontalAlignment(SwingConstants.CENTER);
		lblObjetosHeader3.setVerticalAlignment(SwingConstants.CENTER);
		lblObjetosHeader3.setFont(FUENTE_BOLD_12);
		body.add(lblObjetosHeader3);
		
		
		
//		tabla = new JTable();
//		tabla.setEnabled(false);
//		tabla.setLayout(new FlowLayout());
//		tabla.setBounds(173, 9, 359, 62);
//		tabla.setModel(
//				new DefaultTableModel(datosProductos, new String[] { "NOMBRE", "CANTIDAD", "VALOR UNITARIO" }));
////		
//
//		scrollPane = new JScrollPane(tabla);
//		scrollPane.setBounds(20, 183, 538, 214);
//		body.add(scrollPane);
//		
		tabla = new JTable();
		tabla.setEnabled(false);
		tabla.setLayout(new FlowLayout());
		tabla.setBounds(173, 9, 359, 62);
		tabla.setModel(
				new DefaultTableModel(datosProductos, new String[] { "NOMBRE", "CANTIDAD", "VALOR UNITARIO" }));
//		
		
		scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(20, 183, 517, 214);
		body.add(scrollPane);
		
		
		
		btnRegresar = new JButton("<html>REGRESAR</html>");
		btnRegresar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					System.exit(0);
				}
			}
		});
		btnRegresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				refrescarTabla(datosProductos);
				
			}
		});
		btnRegresar.setBounds(471, 513, 86, 40);
		btnRegresar.setFocusable(true);
		btnRegresar.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegresar.setVerticalAlignment(SwingConstants.CENTER);
		btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelRecibo.add(btnRegresar);
	}
//	private void start() {
//		fecha.setText(fechaString);
//		nombre.setText(nombreString);
//		id.setText(idString);
//		direccion.setText(direccionString);
//	}
	/**
	 * PARA PREUBAS INTERNAS
	 */
	private EspacioRecibo() {
		initialize2();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EspacioRecibo window = new EspacioRecibo();
					window.frame.setVisible(true);
				}catch(Exception e){
					
				}
			}
		});
	}
	private void initialize2() {
		final Color COLOR_FRAME = new Color(217, 222, 222);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 587, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(this.getClass().getCanonicalName());
		frame.setBackground(new Color(0, 0, 0));
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setBackground(COLOR_FRAME);
		
		JPanel panelRecibo = new JPanel();
		panelRecibo.setLayout(null);
		panelRecibo.setVisible(true);
		panelRecibo.setBackground(new Color(255, 255, 255));
		panelRecibo.setBounds(0, 0, 578, 577);
		frame.getContentPane().add(panelRecibo);
		
		header = new JPanel();
		header.setBounds(10, 0, 557, 90);
		header.setLayout(null);
		header.setBackground(new Color(255, 255, 255));
		panelRecibo.add(header);

		lblEmpresaCliente = new JLabel("New label");
		lblEmpresaCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresaCliente.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresaCliente.setFont(FUENTE_BOLD_12);
		lblEmpresaCliente.setBounds(0, 5, 557, 20);
		header.add(lblEmpresaCliente);

		lblEmpresaClienteInfo = new JLabel("New label");
		lblEmpresaClienteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresaClienteInfo.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresaClienteInfo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpresaClienteInfo.setBounds(0, 23, header.getWidth(), 69);
		header.add(lblEmpresaClienteInfo);

		separator1 = new JSeparator();
		separator1.setBounds(10, 90, frame.getWidth() - 40, 2);
		panelRecibo.add(separator1);

		body = new JPanel();
		body.setBounds(10, 95, 558, 407);
		body.setBackground(new Color(255, 255, 255));
		body.setLayout(null);
		panelRecibo.add(body);

		numFactura = new JLabel("NUMERO: ");
		numFactura.setVerticalAlignment(SwingConstants.TOP);
		numFactura.setHorizontalAlignment(SwingConstants.LEFT);
		numFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFactura.setBounds(390, 0, 62, 116);
		body.add(numFactura);

		lblHeadFactura = new JLabel("FACTURA DE VENTA");
		lblHeadFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadFactura.setVerticalAlignment(SwingConstants.TOP);
		lblHeadFactura.setFont(FUENTE_BOLD_12);
		lblHeadFactura.setBounds(0, 0, 558, 15);
		body.add(lblHeadFactura);

		clienteInfo = new JPanel();
		clienteInfo.setBounds(0, 16, 558, 127);
		body.add(clienteInfo);
		clienteInfo.setLayout(null);

		lblFecha = new JLabel("FECHA");
		lblFecha.setFont(FUENTE_PLAIN_12);
		lblFecha.setBounds(76, 10, 100, 13);
		clienteInfo.add(lblFecha);

		lblCliente = new JLabel("CLIENTE");
		lblCliente.setFont(FUENTE_PLAIN_12);
		lblCliente.setBounds(76, 33, 100, 13);
		clienteInfo.add(lblCliente);

		lbl_ID = new JLabel("C.C / NIT");
		lbl_ID.setFont(FUENTE_PLAIN_12);
		lbl_ID.setBounds(76, 56, 104, 13);
		clienteInfo.add(lbl_ID);

		lblDiereccion = new JLabel("DIRECCION");
		lblDiereccion.setFont(FUENTE_PLAIN_12);
		lblDiereccion.setBounds(76, 79, 100, 13);
		clienteInfo.add(lblDiereccion);

		fecha = new JLabel("FECHA");
		fecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fecha.setBounds(186, 11, 200, 13);
		clienteInfo.add(fecha);

		nombre = new JLabel("NOMBRE");
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nombre.setBounds(186, 34, 200, 13);
		clienteInfo.add(nombre);

		id = new JLabel("id");
		id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		id.setBounds(186, 57, 200, 13);
		clienteInfo.add(id);

		direccion = new JLabel("DIRECCION");
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		direccion.setBounds(186, 80, 200, 13);
		clienteInfo.add(direccion);

		numFact = new JLabel("");
		numFact.setVerticalAlignment(SwingConstants.TOP);
		numFact.setHorizontalAlignment(SwingConstants.LEFT);
		numFact.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFact.setBounds(448, 2, 100, 15);
		body.add(numFact);
		
//		panelObjetos = new JPanel();
//		panelObjetos.setBounds(0, 174, 558, 233);
//		body.add(panelObjetos);
//		panelObjetos.setLayout(null);
		
		
		
		separator2 = new JSeparator();
		separator2.setBounds(0, 153,frame.getWidth() - 40, 2);
		body.add(separator2);
		
		lblObjetosHeader1 = new JLabel("CANTIDAD");//10 espacios    -    20 espacios
		lblObjetosHeader1.setBounds(10, 158, 180, 15);
		lblObjetosHeader1.setHorizontalAlignment(SwingConstants.LEFT);
		lblObjetosHeader1.setVerticalAlignment(SwingConstants.CENTER);
		lblObjetosHeader1.setFont(FUENTE_BOLD_12);
		body.add(lblObjetosHeader1);
		
		lblObjetosHeader2 = new JLabel("DESCRIPCION");//10 espacios    -    20 espacios
		lblObjetosHeader2.setBounds(200, 158, 238, 15);
		lblObjetosHeader2.setHorizontalAlignment(SwingConstants.LEFT);
		lblObjetosHeader2.setVerticalAlignment(SwingConstants.CENTER);
		lblObjetosHeader2.setFont(FUENTE_BOLD_12);
		body.add(lblObjetosHeader2);
		
		lblObjetosHeader3 = new JLabel("VALOR");//10 espacios    -    20 espacios
		lblObjetosHeader3.setBounds(448, 158, 110, 15);
		lblObjetosHeader3.setHorizontalAlignment(SwingConstants.CENTER);
		lblObjetosHeader3.setVerticalAlignment(SwingConstants.CENTER);
		lblObjetosHeader3.setFont(FUENTE_BOLD_12);
		body.add(lblObjetosHeader3);
		
		
		
//		tabla = new JTable();
//		tabla.setEnabled(false);
//		tabla.setLayout(new FlowLayout());
//		tabla.setBounds(173, 9, 359, 62);
//		tabla.setModel(
//				new DefaultTableModel(datosProductos, new String[] { "NOMBRE", "CANTIDAD", "VALOR UNITARIO" }));
////		
//
//		scrollPane = new JScrollPane(tabla);
//		scrollPane.setBounds(20, 183, 538, 214);
//		body.add(scrollPane);
//		
		tabla = new JTable();
		tabla.setEnabled(false);
		tabla.setLayout(new FlowLayout());
		tabla.setBounds(173, 9, 359, 62);
		tabla.setModel(
				new DefaultTableModel(datosProductos, new String[] { "NOMBRE", "CANTIDAD", "VALOR UNITARIO" }));
//		
		
		scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(20, 183, 517, 214);
		body.add(scrollPane);
		
		
		
		btnRegresar = new JButton("<html>REGRESAR</html>");
		btnRegresar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					System.exit(0);
				}
			}
		});
		btnRegresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				refrescarTabla(datosProductos);
				
			}
		});
		btnRegresar.setBounds(471, 513, 86, 40);
		btnRegresar.setFocusable(true);
		btnRegresar.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegresar.setVerticalAlignment(SwingConstants.CENTER);
		btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelRecibo.add(btnRegresar);
	}
	
	//FIXME: Replace JTable with a JLabel
	public void refrescarTabla(String[][] datosProductosOrig) {
		String[][] datosProductos = ordenarMatriz(datosProductosOrig);
		
		DefaultTableCellRenderer textoTablaDerecha = new DefaultTableCellRenderer();
		textoTablaDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
		tabla.getColumnModel().getColumn(2).setCellRenderer(textoTablaDerecha);

		DefaultTableCellRenderer textoTablaCentro = new DefaultTableCellRenderer();
		textoTablaCentro.setHorizontalAlignment(SwingConstants.CENTER);
		tabla.getColumnModel().getColumn(0).setCellRenderer(textoTablaCentro);
		tabla.getColumnModel().getColumn(1).setCellRenderer(textoTablaCentro);
		tabla.setModel(new DefaultTableModel(this.datosProductos, new String[] {"CANTIDAD" , "NOMBRE" , "VALOR"}));
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(textoTablaCentro);
		tabla.getColumnModel().getColumn(1).setCellRenderer(textoTablaCentro);
		tabla.getColumnModel().getColumn(2).setCellRenderer(textoTablaDerecha);
	}
	/**
	 * 
	 * @param datosProductos String[][] original {Nombre , Cantidad , ValorUnitario}
	 * @return mat   String[][] {Cantidad , Nombre , ValorTotalProducto}
	 */
	public String[][] ordenarMatriz(String[][] datosProductos) {
		String[][] mat = datosProductos.clone();
		
		//Valor cant*valorUnitario
		for (int i = 0; i < mat.length; i++) {
			int valUni = Integer.valueOf(mat[i][2]);
			mat[i][2] = String.valueOf(Integer.valueOf(mat[i][1])*valUni);
		}
		
		
		//Intercambio columnas 0 1
		for (int i = 0; i < mat.length; i++) {
			String temp = mat[i][0];
			mat[i][0] = mat[i][1];
			mat[i][1] = temp;
		}
		return mat;
	}
}
