package qr;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JLabel;

public interface IQR {

	int QR_IMAGE_SIZE = 150;
	String IMAGE_FORMAT = "png";
	String directoryName = System.getProperty("user.dir") + "\\.recibos";

	BufferedImage writeQR(JLabel label, String info);

	String readQR(File file);

	String readQR(String num);

	BufferedImage read(File file);

	BufferedImage read(String num);

	BufferedImage readAndPlace(File file, JLabel label);

	BufferedImage readAndPlace(String num, JLabel label);

	void save(BufferedImage bufferedImage);

	void directoryExists();

//	void changeState();

}
