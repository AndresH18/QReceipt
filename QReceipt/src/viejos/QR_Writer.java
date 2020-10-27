package viejos;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QR_Writer {

	private static final int QR_IMAGE_SIZE = 150;
	private static final String IMAGE_FORMAT2 = "png";

	private String info;
	private JLabel label;

	public QR_Writer(JLabel label, String info) {
		this.info = info;
		this.label = label;
		label.setIcon(new ImageIcon(genQR()));
	}

	public QR_Writer() {

	}

	public void genQR(JLabel label, String info) {
		this.info = info;
		this.label = label;
		label.setIcon(new ImageIcon(genQR()));
	}

	private BufferedImage genQR() {
		BitMatrix matrix;
		Writer wr = new QRCodeWriter();

		ciclo: do {
			try {
				matrix = wr.encode(info, BarcodeFormat.QR_CODE, QR_IMAGE_SIZE, QR_IMAGE_SIZE);
			} catch (WriterException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				continue ciclo;
			}
			break ciclo;
		} while (true);

		BufferedImage image = new BufferedImage(QR_IMAGE_SIZE, QR_IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < QR_IMAGE_SIZE; y++) {
			for (int x = 0; x < QR_IMAGE_SIZE; x++) {
				int greyValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
				image.setRGB(x, y, ((greyValue == 0) ? 0 : 0xFFFFFF));
			}
		}

		Image thumbnail = image.getScaledInstance(label.getWidth() - 5, -1, Image.SCALE_SMOOTH);
		BufferedImage buf2 = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		buf2.getGraphics().drawImage(thumbnail, 0, 0, null);

		try {
			save(0, image);
		} catch (IOException e) {

		}
		return image;
	}

	private void save(int a, BufferedImage b) throws IOException {
		fileArch();

		BufferedImage bufIm = b;

		File file = new File(System.getProperty("user.dir") + "\\recibos\\Prueba de crear" + a + ".png");
		System.out.println("SAVING......");
		System.out.println("FILE:\t" + System.getProperty("user.dir") + "\\DOCS\\");
		FileOutputStream outputStream = new FileOutputStream(file);

		ImageIO.write(bufIm, IMAGE_FORMAT2, outputStream);

		outputStream.close();
	}

	private static void fileArch() {
		File f = new File(System.getProperty("user.dir") + "\\recibos");
		if (f.isDirectory() && f != null) {
//			Path path = Paths.get(f.getPath());
			Path path = f.toPath();
//			Files.setAttribute(f.getPath(), "dos:hidden",true);
			try {
				System.out.println(f.isHidden());
				if (!f.isHidden()) {
					System.out.println("hide");
					//hide
					Files.setAttribute(path, "dos:hidden", true);
				} else {
					System.out.println("unhide");
//					//unhide 
					Files.setAttribute(path, "dos:hidden", true);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage() + " ");
			}
		}
	}

	public static void main(String[] args) {
		fileArch();
	}

}
