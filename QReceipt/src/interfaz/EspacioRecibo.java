package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import accesoDatos.accesoRegistro.IManejoDatos;
import accesoDatos.accesoRegistro.ManejoDatos;
import imprimir.Imprimir;
import qr.QR;
import qr.IQR;

public class EspacioRecibo {

	private IManejoDatos datos;

	private Interfaz faz;

	private static final String COMPANY = "<html>Direccion: Cra 38# 6D Z sur 30<br>NIT: 112.358.132-1B<br>Telefono: (-57) 300336 9209<br>E-mail: qreceipt@receipt.qr</html>";
	private static final String ROOT_PATH = System.getProperty("user.dir");
	private static final String LOGO_PATH = ROOT_PATH + "\\.docs\\QReceipt_logo.jpeg";

//	private String[][] datosProductos = {{"TV" , "10002" , "8888"} , {"computador" , "333" , "888"} , {"holla" , "2" , "100"}};
	private String[][] datosProductos;

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

	private JPanel panel1;
	private JPanel body;
	private JLabel lblHeadFactura;
	private JPanel clienteInfo;
	private JLabel numFactura;
	private JLabel lblFecha;
	private JLabel lblCliente;
	private JLabel lbl_ID;
	private JLabel lblDireccion;
	private JLabel fecha;
	private JLabel nombre;
	private JLabel id;
	private JLabel direccion;
	private JLabel numFact;
	private JLabel lblValor;
	private JLabel valorTotal;
	private JLabel lblQR;
	private JLabel lblLogo;

	private JSeparator separator2;

	private JTable tabla;
	private JScrollPane scrollPane;

	private JButton btnImprimir;
	private JButton btnRegresar;
	private JButton btnGuardar;

	public EspacioRecibo(JFrame frame, JPanel panelRecibo, String[][] datosProductos) {
		datos = new ManejoDatos();
		datos.crearArchivo();

		this.frame = frame;
		this.panelRecibo = panelRecibo;
		this.datosProductos = datosProductos.clone();

		initialize();

		startActionListeners();
	}

	public EspacioRecibo(JFrame frame, JPanel panelRecibo, String[][] datosProductos, Interfaz faz) {
		datos = new ManejoDatos();
		datos.crearArchivo();

		this.faz = faz;
		this.frame = frame;
		this.panelRecibo = panelRecibo;
		this.datosProductos = datosProductos.clone();

		initialize();

		startActionListeners();
	}

	private void initialize() {
		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(0, 0, 578, 520);
		panel1.setLayout(null);
		panelRecibo.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(0, 520, 578, 577 - 520);
		panel2.setLayout(null);
		panelRecibo.add(panel2);

		header = new JPanel();
		header.setBounds(10, 0, 557, 90);
		header.setLayout(null);
		header.setBackground(Color.WHITE);
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
		body.setBackground(Color.WHITE);
		body.setLayout(null);
		panel1.add(body);

		numFactura = new JLabel("NUMERO: ");
		numFactura.setVerticalAlignment(SwingConstants.TOP);
		numFactura.setHorizontalAlignment(SwingConstants.LEFT);
		numFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFactura.setBounds(390, 0, 124, 15);
		body.add(numFactura);

		numFact = new JLabel("LSLLSLSS");
		numFact.setVerticalAlignment(SwingConstants.TOP);
		numFact.setHorizontalAlignment(SwingConstants.LEFT);
		numFact.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFact.setBounds(448, 0, 100, 15);
		body.add(numFact);

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

		lblDireccion = new JLabel("DIRECCION");
		lblDireccion.setFont(FUENTE_PLAIN_12);
		lblDireccion.setBounds(76, 79, 100, 13);
		clienteInfo.add(lblDireccion);

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

		valorTotal = new JLabel("VALOR TOTAL");
		valorTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		valorTotal.setBounds(186, 105, 230, 13);
		clienteInfo.add(valorTotal);

		lblQR = new JLabel("");
		lblQR.setHorizontalAlignment(SwingConstants.CENTER);
		lblQR.setVerticalAlignment(SwingConstants.CENTER);
		lblQR.setBounds(418, 2, clienteInfo.getHeight() - 3, clienteInfo.getHeight() - 3);
		clienteInfo.add(lblQR);

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
		btnRegresar.setBounds(30, 10, 86, 25);
		btnRegresar.setFocusable(false);
		btnRegresar.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegresar.setVerticalAlignment(SwingConstants.CENTER);
		btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel2.add(btnRegresar);

		btnImprimir = new JButton("<html>IMPRIMIR</html>");
		btnImprimir.setBounds(481 - 130, 10, 86, 25);
		btnImprimir.setVerticalAlignment(SwingConstants.CENTER);
		btnImprimir.setHorizontalAlignment(SwingConstants.CENTER);
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnImprimir.setFocusable(false);
		panel2.add(btnImprimir);

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		btnGuardar.setVerticalAlignment(SwingConstants.CENTER);
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGuardar.setBounds(481 - 20, 10, 90, 25);
		btnGuardar.setFocusable(false);
		btnGuardar.setFocusable(false);
		panel2.add(btnGuardar);

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

//		btnGuardar.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String num = numFact.getText();
//				datos.agregarEntrada(num, false);
//				
//				faz.reset();
//				
//				btnRegresar.doClick();
//
//			}
//		});

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

		new QR().directoryExists();
		File directory = new File(IQR.directoryName);
		this.numFact.setText(String.valueOf(directory.listFiles().length + 1));
	}

	public void setDatosCliente(String[] infoCliente, String valor) {
		// infoCliente => {fecha, nombre, id, direccion}
		this.fechaString = infoCliente[0];
		this.nombreString = infoCliente[1];
		this.idString = infoCliente[2];
		this.direccionString = infoCliente[3];
		actualizarDatosCliente(valor);
	}

	public boolean imprimir() {
//		new Imprimir(panel1, lblQR).ImprimirPanel(0.75, 0.75);
//		new Imprimir(panel1, lblQR).ImprimirPanel(1, 1);
		return (new Imprimir(panel1, lblQR).ImprimirPanel(1, 1));

	}

	public JLabel getNumFact() {
		return numFact;
	}

	public JLabel getLblQR() {
		return this.lblQR;
	}

	public JButton getBtnImprimir() {
		return this.btnImprimir;
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public void setDatosProductos(String[][] datosProductos) {
		this.datosProductos = datosProductos.clone();
		refrescarTabla(this.datosProductos.clone());

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EspacioRecibo window = new EspacioRecibo();
					window.frame.setVisible(true);
				} catch (Exception e) {

				}
			}
		});
	}

	private EspacioRecibo() {
		pruebasInternas();
	}

	private void pruebasInternas() {
		final Color COLOR_FRAME = new Color(217, 222, 222);

		frame = new JFrame();
		frame.setBounds(100, 100, 587, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(this.getClass().getCanonicalName());
		frame.setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setBackground(COLOR_FRAME);

		JPanel panelRecibo = new JPanel();
		panelRecibo.setLayout(null);
		panelRecibo.setVisible(true);
		panelRecibo.setBackground(Color.WHITE);
		panelRecibo.setBounds(0, 0, 578, 577);
		frame.getContentPane().add(panelRecibo);

		/**
		 * 
		 * DESDE AQUI SE COPIA
		 * 
		 */
		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(0, 0, 578, 520);
		panel1.setLayout(null);
		panelRecibo.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(0, 520, 578, 577 - 520);
		panel2.setLayout(null);
		panelRecibo.add(panel2);

		header = new JPanel();
		header.setBounds(10, 0, 557, 90);
		header.setLayout(null);
		header.setBackground(Color.WHITE);
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
		body.setBackground(Color.WHITE);
		body.setLayout(null);
		panel1.add(body);

		numFactura = new JLabel("NUMERO: ");
		numFactura.setVerticalAlignment(SwingConstants.TOP);
		numFactura.setHorizontalAlignment(SwingConstants.LEFT);
		numFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFactura.setBounds(390, 0, 124, 15);
		body.add(numFactura);

		numFact = new JLabel("LSLLSLSS");
		numFact.setVerticalAlignment(SwingConstants.TOP);
		numFact.setHorizontalAlignment(SwingConstants.LEFT);
		numFact.setFont(new Font("Tahoma", Font.BOLD, 12));
		numFact.setBounds(448, 0, 100, 15);
		body.add(numFact);

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

		lblDireccion = new JLabel("DIRECCION");
		lblDireccion.setFont(FUENTE_PLAIN_12);
		lblDireccion.setBounds(76, 79, 100, 13);
		clienteInfo.add(lblDireccion);

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

		valorTotal = new JLabel("VALOR TOTAL");
		valorTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		valorTotal.setBounds(186, 105, 230, 13);
		clienteInfo.add(valorTotal);

		lblQR = new JLabel("");
		lblQR.setHorizontalAlignment(SwingConstants.CENTER);
		lblQR.setVerticalAlignment(SwingConstants.CENTER);
		lblQR.setBounds(418, 2, clienteInfo.getHeight() - 3, clienteInfo.getHeight() - 3);
		clienteInfo.add(lblQR);

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
		btnRegresar.setBounds(30, 10, 86, 25);
		btnRegresar.setFocusable(false);
		btnRegresar.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegresar.setVerticalAlignment(SwingConstants.CENTER);
		btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel2.add(btnRegresar);

		btnImprimir = new JButton("<html>IMPRIMIR</html>");
		btnImprimir.setBounds(481 - 130, 10, 86, 25);
		btnImprimir.setVerticalAlignment(SwingConstants.CENTER);
		btnImprimir.setHorizontalAlignment(SwingConstants.CENTER);
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnImprimir.setFocusable(false);
		panel2.add(btnImprimir);

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		btnGuardar.setVerticalAlignment(SwingConstants.CENTER);
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGuardar.setBounds(481 - 20, 10, 90, 25);
		btnGuardar.setFocusable(false);
		panel2.add(btnGuardar);

	}
}
