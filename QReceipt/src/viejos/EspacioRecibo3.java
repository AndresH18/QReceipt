package viejos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import qr.QR_Implementation;
import qr.QR_Interface;

public class EspacioRecibo3 {

	private static final String COMPANY = "<html>Direccion: Cra 38# 6D Z sur 30<br>NIT: 112.358.132-1B<br>Telefono: (-57) 300336 9209<br>E-mail: qreceipt@receipt.qr</html>";
	private static final String ROOT_PATH = System.getProperty("user.dir");
	private static final String LOGO_PATH = ROOT_PATH + "\\DOCS\\QReceipt_logo.jpeg";

//	private String[][] datosProductos = {{"TV" , "10002" , "8888"} , {"computador" , "333" , "888"} , {"holla" , "2" , "100"}};
	private String[][] datosProductos;

	public void setDatosProductos(String[][] datosProductos) {
		this.datosProductos = datosProductos.clone();
		refrescarTabla(this.datosProductos.clone());

	}

	private static final Font FUENTE_PLAIN_12 = new Font("Tahoma", Font.PLAIN, 12);
	private static final Font FUENTE_BOLD_12 = new Font("Tahoma", Font.BOLD, 12);

	private String fechaString;
	private String nombreString;
	private String idString;
	private String direccionString;

	private JFrame frame;
	private JPanel panelRecibo;// panelGenerarRecibo

	private JPanel header;
	private JLabel lblEmpresa;
	private JLabel lblEmpresaInfo;
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
	private JLabel lblValor;
	private JLabel valorTotal;

	private JLabel lblQR;

	public JLabel getLblQR() {
		return this.lblQR;
	}

	private JSeparator separator2;

	private JTable tabla;
	private JScrollPane scrollPane;
	private JLabel lblLogo;

	public JLabel getLblLogo() {
		return lblLogo;
	}

	private JButton btnTerminar;

	public JButton getBtnTerminar() {
		return this.btnTerminar;
	}

	private JButton btnRegresar;

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	private EspacioRecibo3(JFrame frame, JPanel panelRecibo) {
		this.frame = frame;
		this.panelRecibo = panelRecibo;

		initialize();

		startActionListeners();

	}

	public EspacioRecibo3(JFrame frame, JPanel panelRecibo, String[][] datosProductos) {
		this.frame = frame;
		this.panelRecibo = panelRecibo;
		this.datosProductos = datosProductos.clone();

		initialize();

		startActionListeners();
	}

	private EspacioRecibo3(JFrame frame, JPanel panel, String... datos) {
		// orden datos: fecha, nombre, id, direccion;
		this.frame = frame;
		this.panelRecibo = panel;
		this.fechaString = datos[0];
		this.nombreString = datos[1];
		this.idString = datos[2];
		this.direccionString = datos[3];

		initialize();

		startActionListeners();
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
	private EspacioRecibo3(JFrame frame, JPanel panel, String fecha, String nombre, String id, String direccion) {
		// orden datos: fecha, nombre, id, direccion;
		this.frame = frame;
		this.panelRecibo = panel;
		this.fechaString = fecha;
		this.nombreString = nombre;
		this.idString = id;
		this.direccionString = direccion;

		initialize();

		startActionListeners();

	}

	private void initialize() {

		header = new JPanel();
		header.setBounds(10, 0, 557, 90);
		header.setLayout(null);
		header.setBackground(new Color(255, 255, 255));
		panelRecibo.add(header);

		lblEmpresa = new JLabel("QReceipt");
		lblEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpresa.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresa.setFont(FUENTE_BOLD_12);
		lblEmpresa.setBounds(128, 5, 429, 20);
		header.add(lblEmpresa);

		lblEmpresaInfo = new JLabel(COMPANY);
		lblEmpresaInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpresaInfo.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresaInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmpresaInfo.setBounds(128, 23, 429, 69);
		header.add(lblEmpresaInfo);

		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(0, 0, 90, 90);
		header.add(lblLogo);

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
		numFactura.setBounds(390, 0, 124, 15);
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
		fecha.setBounds(186, 11, 230, 13);
		clienteInfo.add(fecha);

		nombre = new JLabel("NOMBRE");
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nombre.setBounds(186, 34, 230, 13);
		clienteInfo.add(nombre);

		id = new JLabel("id");
		id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		id.setBounds(186, 57, 230, 13);
		clienteInfo.add(id);

		direccion = new JLabel("DIRECCION");
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		direccion.setBounds(186, 80, 230, 13);
		clienteInfo.add(direccion);

		lblValor = new JLabel("VALOR");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValor.setBounds(76, 105, 50, 13);
		clienteInfo.add(lblValor);

		valorTotal = new JLabel();
		valorTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		valorTotal.setBounds(186, 105, 230, 13);
		clienteInfo.add(valorTotal);

		lblQR = new JLabel("");
		lblQR.setHorizontalAlignment(SwingConstants.CENTER);
		lblQR.setVerticalAlignment(SwingConstants.CENTER);
		lblQR.setBounds(418, 2, clienteInfo.getHeight() - 3, clienteInfo.getHeight() - 3);
		clienteInfo.add(lblQR);

		numFact = new JLabel("LSLLSLSS");
		numFact.setVerticalAlignment(SwingConstants.TOP);
		numFact.setHorizontalAlignment(SwingConstants.LEFT);
		numFact.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFact.setBounds(448, 0, 100, 15);
		body.add(numFact);

		separator2 = new JSeparator();
		separator2.setBounds(0, 153, frame.getWidth() - 40, 2);
		body.add(separator2);

		tabla = new JTable();
		tabla.setEnabled(false);
		tabla.setLayout(new FlowLayout());
		tabla.setBounds(173, 9, 359, 62);
		tabla.setModel(new DefaultTableModel(datosProductos, new String[] { "CANTIDAD", "NOMBRE", "VALOR" }));

		scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(20, 183, 517, 214);
		body.add(scrollPane);

		btnRegresar = new JButton("<html>REGRESAR</html>");
		btnRegresar.setBounds(378, 506, 86, 40);
		btnRegresar.setFocusable(false);
		btnRegresar.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegresar.setVerticalAlignment(SwingConstants.CENTER);
		btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelRecibo.add(btnRegresar);

		btnTerminar = new JButton("<html>IMPRIMIR</html>");
		btnTerminar.setVerticalAlignment(SwingConstants.CENTER);
		btnTerminar.setHorizontalAlignment(SwingConstants.CENTER);
		btnTerminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTerminar.setFocusable(false);
		btnTerminar.setBounds(481, 506, 86, 40);
		panelRecibo.add(btnTerminar);

	}

	private void startActionListeners() {
		btnRegresar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					System.exit(0);
				}
			}
		});
		btnRegresar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_W) {
					frame.dispose();
					System.exit(0);
				}
			}
		});

	}

	public void refrescarTabla(String[][] datosProductosOrig) {
		String[][] datosProductos = datosProductosOrig.clone();

		DefaultTableCellRenderer textoTablaDerecha = new DefaultTableCellRenderer();
		textoTablaDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
//		tabla.getColumnModel().getColumn(2).setCellRenderer(textoTablaDerecha);

		DefaultTableCellRenderer textoTablaCentro = new DefaultTableCellRenderer();
		textoTablaCentro.setHorizontalAlignment(SwingConstants.CENTER);
//		tabla.getColumnModel().getColumn(0).setCellRenderer(textoTablaCentro);
//		tabla.getColumnModel().getColumn(1).setCellRenderer(textoTablaCentro);
		tabla.setModel(new DefaultTableModel(datosProductos.clone(), new String[] { "CANTIDAD", "NOMBRE", "VALOR" }));

		tabla.getColumnModel().getColumn(0).setCellRenderer(textoTablaCentro);
		tabla.getColumnModel().getColumn(1).setCellRenderer(textoTablaCentro);
		tabla.getColumnModel().getColumn(2).setCellRenderer(textoTablaDerecha);
	}

	private void actualizarDatosCliente(String valor) {
		this.fecha.setText(this.fechaString);
		this.nombre.setText(this.nombreString);
		this.id.setText(this.idString);
		this.direccion.setText(this.direccionString);
		this.valorTotal.setText(valor);

		new QR_Implementation().directoryExists();
//		File directory = new File(QR_Interface.directoryName);
//		this.numFact.setText(String.valueOf(directory.listFiles().length) + 1);
	}

	public void setDatosCliente(String[] infoCliente, String valor) {
		// infoCliente => {fecha, nombre, id, direccion}
		this.fechaString = infoCliente[0];
		this.nombreString = infoCliente[1];
		this.idString = infoCliente[2];
		this.direccionString = infoCliente[3];
		actualizarDatosCliente(valor);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EspacioRecibo3 window = new EspacioRecibo3();
					window.frame.setVisible(true);
				} catch (Exception e) {

				}
			}
		});
	}

	private EspacioRecibo3() {
		pruebasInternas();
	}

	private void pruebasInternas() {
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

		/**
		 * 
		 * DESDE AQUI SE COPIA
		 * 
		 */

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.ORANGE);
		panel1.setBounds(0, 0, 578, 520);
		panel1.setLayout(null);
		panelRecibo.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.CYAN);
		panel2.setBounds(0, 520, 578, 577 - 520);
		panel2.setLayout(null);
		panelRecibo.add(panel2);

		header = new JPanel();
		header.setBounds(10, 0, 557, 90);
		header.setLayout(null);
		header.setBackground(new Color(255, 255, 255));
		panel1.add(header);

		lblEmpresa = new JLabel("QReceipt");
		lblEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpresa.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresa.setFont(FUENTE_BOLD_12);
		lblEmpresa.setBounds(128, 5, 429, 20);
		header.add(lblEmpresa);

		lblEmpresaInfo = new JLabel(COMPANY);
		lblEmpresaInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpresaInfo.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresaInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmpresaInfo.setBounds(128, 23, 429, 69);
		header.add(lblEmpresaInfo);

		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(0, 0, 90, 90);
		header.add(lblLogo);

		separator1 = new JSeparator();
		separator1.setBounds(10, 90, frame.getWidth() - 40, 2);
		panel1.add(separator1);

		body = new JPanel();
		body.setBounds(10, 95, 558, 407);
		body.setBackground(new Color(255, 255, 255));
		body.setLayout(null);
		panel1.add(body);

		numFactura = new JLabel("NUMERO: ");
		numFactura.setVerticalAlignment(SwingConstants.TOP);
		numFactura.setHorizontalAlignment(SwingConstants.LEFT);
		numFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFactura.setBounds(390, 0, 124, 15);
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
		fecha.setBounds(186, 11, 230, 13);
		clienteInfo.add(fecha);

		nombre = new JLabel("NOMBRE");
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nombre.setBounds(186, 34, 230, 13);
		clienteInfo.add(nombre);

		id = new JLabel("id");
		id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		id.setBounds(186, 57, 230, 13);
		clienteInfo.add(id);

		direccion = new JLabel("DIRECCION");
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		direccion.setBounds(186, 80, 230, 13);
		clienteInfo.add(direccion);

		lblValor = new JLabel("VALOR");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValor.setBounds(76, 105, 50, 13);
		clienteInfo.add(lblValor);

		valorTotal = new JLabel();
		valorTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		valorTotal.setBounds(186, 105, 230, 13);
		clienteInfo.add(valorTotal);

		lblQR = new JLabel("");
		lblQR.setHorizontalAlignment(SwingConstants.CENTER);
		lblQR.setVerticalAlignment(SwingConstants.CENTER);
		lblQR.setBounds(418, 2, clienteInfo.getHeight() - 3, clienteInfo.getHeight() - 3);
		clienteInfo.add(lblQR);

		numFact = new JLabel("LSLLSLSS");
		numFact.setVerticalAlignment(SwingConstants.TOP);
		numFact.setHorizontalAlignment(SwingConstants.LEFT);
		numFact.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFact.setBounds(448, 0, 100, 15);
		body.add(numFact);

		separator2 = new JSeparator();
		separator2.setBounds(0, 153, frame.getWidth() - 40, 2);
		body.add(separator2);

		tabla = new JTable();
		tabla.setEnabled(false);
		tabla.setLayout(new FlowLayout());
		tabla.setBounds(173, 9, 359, 62);
		tabla.setModel(new DefaultTableModel(datosProductos, new String[] { "CANTIDAD", "NOMBRE", "VALOR" }));

		scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(20, 183, 517, 214);
		body.add(scrollPane);

		btnRegresar = new JButton("<html>REGRESAR</html>");
		btnRegresar.setBounds(378, 10, 86, 40);
		btnRegresar.setFocusable(false);
		btnRegresar.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegresar.setVerticalAlignment(SwingConstants.CENTER);
		btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel2.add(btnRegresar);

		btnTerminar = new JButton("<html>IMPRIMIR</html>");
		btnTerminar.setBounds(481, 10, 86, 40);
		btnTerminar.setVerticalAlignment(SwingConstants.CENTER);
		btnTerminar.setHorizontalAlignment(SwingConstants.CENTER);
		btnTerminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTerminar.setFocusable(false);
		panel2.add(btnTerminar);
	}

}
