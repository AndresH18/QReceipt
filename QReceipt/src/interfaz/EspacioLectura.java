package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import static interfaz.Interfaz.*;

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

	public EspacioLectura(JFrame frame, JPanel panel) {
		this.frame = frame;
		this.panelTab = panel;

		initialize();

	}

	public void start() {

	}

	public void start(String data) {

	}

	private void initialize() {

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

		setExpLogo(btnSeleccionarArchivo);
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
		frame.setBackground(new Color(0, 0, 0));
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(COLOR_FRAME);

		panelTab = new JPanel();
		panelTab.setLayout(null);
		panelTab.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.add(panelTab);

		// DESDE AQUI
		btnCerrarSesion = new JButton("<html>Cerrar Sesi" + (char) 243 + "n");
		btnCerrarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnCerrarSesion: actionPerformed");

			}
		});
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
		btnSeleccionarArchivo.setBounds(panelTab.getWidth() - 50, panelTab.getHeight() - 75, 30, 30);
		panelTab.add(btnSeleccionarArchivo);

		panelInfo = new JPanel();
		panelInfo.setLayout(null);
		panelInfo.setBounds(30, 50, panelTab.getWidth() - 65, panelTab.getHeight() - 130);
		panelInfo.setBackground(new Color(192, 219, 92));
		panelTab.add(panelInfo);
		
		lblImgQR = new JLabel();
		lblImgQR.setBounds(panelInfo.getWidth()-35, 0, 35, 35);
		lblImgQR.setOpaque(true);
		panelInfo.add(lblImgQR);
		
		

	}

	private void setExpLogo(JButton btn) {
		BufferedImage buf1 = null;

		try {
			buf1 = ImageIO.read(new File(System.getProperty("user.dir") + "\\DOCS\\FileExp.png"));
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		Image thumbnail = buf1.getScaledInstance(btn.getWidth(), -1, Image.SCALE_SMOOTH);
		BufferedImage buf2 = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		buf2.getGraphics().drawImage(thumbnail, 0, 0, null);

		btn.setIcon(new ImageIcon(buf2));

	}
}
