package interfaz;

import java.awt.Color;
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
import javax.swing.SwingConstants;

public class EspacioRecibo {

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

	private JButton btnRegresar;
	
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
		body.setBounds(10, 90, 558, 407);
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
}
