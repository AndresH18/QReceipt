package qr;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JLabel;

public interface QR_Interface {

	int QR_IMAGE_SIZE = 150;
	String IMAGE_FORMAT = "png";
	String directoryName = System.getProperty("user.dir") + "\\recibos";


	BufferedImage write(JLabel label, String info);

	BufferedImage read(File file);

	void save(BufferedImage bufferedImage);

	void directoryExists();

//	void changeState();

}
