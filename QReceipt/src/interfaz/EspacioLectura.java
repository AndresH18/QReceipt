package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import crypto.Crypto;
import login.UserLogin;
import qr.QR_Implementation;
import qr.QR_Interface;

public class EspacioLectura {

	private JFrame frame;
	private JPanel panelTab;
	private JPanel panelInfo;
	private JButton btnCerrarSesion;
	private JButton btnSeleccionarArchivo;
	private JLabel lblInfo;
	private JLabel lblImgQR;
	private JSeparator separador;
	private JScrollPane scrollPane;
	private JTable table;
	private JFileChooser fileChooser;

	private File qrFile = null;

	private QR_Interface qr = new QR_Implementation();
	private UserLogin userLogin;

	public EspacioLectura(JFrame frame, JPanel panel) {
		this.frame = frame;
		this.panelTab = panel;
	}

	public EspacioLectura(JFrame frame, JPanel panel, UserLogin userLogin) {
		this.frame = frame;
		this.panelTab = panel;
		this.userLogin = userLogin;

		initialize();

		setExpLogo(btnSeleccionarArchivo);

		startActionListeners();

	}

	public EspacioLectura(JFrame frame, JPanel panel, File file) {
		this.frame = frame;
		this.panelTab = panel;
		this.qrFile = file;

		initialize();

		setExpLogo(btnSeleccionarArchivo);

		startActionListeners();

	}

	public void start() {

	}

	public void start(String data) {

	}

	private void initialize() {
		btnCerrarSesion = new JButton("<html>Cerrar Sesi" + (char) 243 + "n");
		btnCerrarSesion.setVerticalAlignment(SwingConstants.CENTER);
		btnCerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		btnCerrarSesion.setHorizontalTextPosition(SwingConstants.LEFT);
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCerrarSesion.setFocusable(false);
		btnCerrarSesion.setBounds(5, 5, 70, 35);
		panelTab.add(btnCerrarSesion);

//		btnSeleccionarArchivo = new JButton(new ImageIcon(setExpLogo()));
		btnSeleccionarArchivo = new JButton();
		btnSeleccionarArchivo.setHorizontalAlignment(SwingConstants.CENTER);
		btnSeleccionarArchivo.setVerticalAlignment(SwingConstants.CENTER);
		btnSeleccionarArchivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSeleccionarArchivo.setBounds(panelTab.getWidth() - 50, panelTab.getHeight() - (75 + 27), 30, 30);
		panelTab.add(btnSeleccionarArchivo);

		panelInfo = new JPanel();
		panelInfo.setLayout(null);
		panelInfo.setBounds(30, 50, panelTab.getWidth() - 65, panelTab.getHeight() - 160);
//		panelInfo.setBackground(new Color(192, 219, 92));
		panelInfo.setBackground(Color.GREEN);
		panelTab.add(panelInfo);

		lblImgQR = new JLabel();
		lblImgQR.setBounds(panelInfo.getWidth() - 50, 0, 50, 50);
		lblImgQR.setBackground(Color.BLACK);
		lblImgQR.setOpaque(true);
		panelInfo.add(lblImgQR);

	}

	private void startActionListeners() {
//		frame.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				System.out.println("CLOSING");
//				System.exit(0);
//			}
//		});
		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnCerrarSesion: actionPerformed");

			}
		});
		btnSeleccionarArchivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (qrFile != null) {
					fileChooser = new JFileChooser(qrFile.getPath());
				} else {
					fileChooser = new JFileChooser(System.getProperty("user.home"));
				}
				int val = fileChooser.showOpenDialog(frame);
				if (val == JFileChooser.APPROVE_OPTION) {
					qrFile = fileChooser.getSelectedFile();
					if (qr.read(qrFile) != null) {
						lblImgQR.setIcon(new ImageIcon(resize(qr.read(qrFile), lblImgQR)));

						System.out.println(traducir(qr.read(qrFile)));
						System.out.println(Crypto.hexToString(traducir(qr.read(qrFile))));
					} else {
						Toolkit.getDefaultToolkit().beep();
						System.err.println("El archivo no es valido");
					}
				}
			}
		});
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EspacioLectura window = new EspacioLectura();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EspacioLectura() {
		initialize2();

//		setExpLogo(btnSeleccionarArchivo);
	}

	private void initialize2() {
		frame = new JFrame();
		frame.setBounds(100, 100, 587, 632);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				System.out.println("CLOSING");
				System.exit(0);
			}

		});
		frame.setTitle(this.getClass().getCanonicalName());
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setLayout(null);
//		frame.getContentPane().setBackground(COLOR_FRAME);

		panelTab = new JPanel();
		panelTab.setLayout(null);
		panelTab.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.add(panelTab);

		// DESDE AQUI

	}

	private void setExpLogo(JButton btn) {
		BufferedImage buf1 = null;

		try {
			buf1 = ImageIO.read(new File(System.getProperty("user.dir") + "\\DOCS\\FileExp.png"));
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		if (buf1 != null) {
			Image thumbnail = buf1.getScaledInstance(btn.getWidth(), -1, Image.SCALE_SMOOTH);
			BufferedImage buf2 = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			buf2.getGraphics().drawImage(thumbnail, 0, 0, null);

			btn.setIcon(new ImageIcon(buf2));
		}

	}

	private BufferedImage resize(BufferedImage image, JLabel label) {
		if (image != null) {
			Image thumbnail = image.getScaledInstance(label.getWidth(), -1, Image.SCALE_SMOOTH);
			BufferedImage buf2 = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			buf2.getGraphics().drawImage(thumbnail, 0, 0, null);

			return buf2;

		} else {
			return image;
		}
	}

	private String traducir(BufferedImage image) {
		if (image != null) {
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			try {
				Result result = new MultiFormatReader().decode(bitmap);
				return result.getText();
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
			Toolkit.getDefaultToolkit().beep();
			return "THERE IS NO \"QR\" IN THE IMAGE ";
		} else {
			return null;
		}
	}
}
