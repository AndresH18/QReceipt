package viejos;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QR_Writer3 {
//	static final int QR_IMAGE_SIZE = 148;
	static final int QR_IMAGE_SIZE = 150;
	static final String IMAGE_FORMAT = ".png";
	static final String IMAGE_FORMAT2 = "png";

	private String info;
	private JLabel label;

	public QR_Writer3(JLabel label, String info) {
		this.info = info;
		this.label = label;
		label.setIcon(new ImageIcon(genQR()));
		
	}

	public QR_Writer3(String info) {
		this.info = info;
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
				int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
				image.setRGB(x, y, ((grayValue == 0) ? 0 : 0xFFFFFF));
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

	private void makeQR(JLabel label) {
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
				int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
				image.setRGB(x, y, ((grayValue == 0) ? 0 : 0xFFFFFF));
			}
		}
		label.setIcon(new ImageIcon(image));
		
		try {
			save(0, image);
		} catch (IOException e) {

		}
	}

	public QR_Writer3() {

	}

	private void savepruba() throws IOException {
		File outputFile = new File(System.getProperty("user.dir") + "\\DOCS\\Prueba de crear archivo.txt");

		String str = "Hello lol";
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		writer.write(str);

		writer.close();
	}

	private void save(int a, BufferedImage b) throws IOException {
		BufferedImage bufIm = b;
		
		File file = new File(System.getProperty("user.dir") + "\\DOCS\\Prueba de crear.png");
		System.out.println("SAVING......");
		System.out.println("FILE:\t" + System.getProperty("user.dir") + "\\DOCS\\");
		FileOutputStream outputStream = new FileOutputStream(file);
		
		ImageIO.write(bufIm, IMAGE_FORMAT2, outputStream);
		
		outputStream.close();
		
	}

	public static void main(String[] args) throws Exception {
		QR_Writer3 a = new QR_Writer3();
		
	}

}
