package qr;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QR_Writer3 {
	static final int QR_IMAGE_SIZE = 148;
	static final String IMAGE_FORMAT = ".png";
	
	private String info;
	
	public QR_Writer3(JLabel label, String info) {
		this.info = info;
		label.setIcon(new ImageIcon(genQR()));;
	}
	public QR_Writer3(String info) {
		this.info = info;
	}
	
	private BufferedImage genQR() {
		BitMatrix matrix;
		Writer wr = new QRCodeWriter();
		
		ciclo:
		do {
			try {
				matrix = wr.encode(info, BarcodeFormat.QR_CODE,	QR_IMAGE_SIZE, QR_IMAGE_SIZE);
			} catch (WriterException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				continue ciclo;
			}
			break ciclo;
		}while (true);
		
		BufferedImage image = new BufferedImage(QR_IMAGE_SIZE, QR_IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < QR_IMAGE_SIZE; y++) {
			for (int x = 0; x < QR_IMAGE_SIZE; x++) {
				int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
				image.setRGB(x, y, ((grayValue == 0) ? 0 : 0xFFFFFF));
			}
		}
		return image;
	}
	
	public void makeQR(JLabel label) {
		BitMatrix matrix;
		Writer wr = new QRCodeWriter();
		
		ciclo:
		do {
			try {
				matrix = wr.encode(info, BarcodeFormat.QR_CODE,	QR_IMAGE_SIZE, QR_IMAGE_SIZE);
			} catch (WriterException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				continue ciclo;
			}
			break ciclo;
		}while (true);
		
		BufferedImage image = new BufferedImage(QR_IMAGE_SIZE, QR_IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < QR_IMAGE_SIZE; y++) {
			for (int x = 0; x < QR_IMAGE_SIZE; x++) {
				int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
				image.setRGB(x, y, ((grayValue == 0) ? 0 : 0xFFFFFF));
			}
		}
		label.setIcon(new ImageIcon(image));
	}
	
}
