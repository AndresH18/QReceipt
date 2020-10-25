package qr;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JLabel;

public interface QR_Interface {

	int QR_IMAGE_SIZE = 150;
	String IMAGE_FORMAT = "png";

	BufferedImage write(JLabel label, String info);

	void read();

	void save(BufferedImage bufferedImage);

	void exists();

//	void changeState();

}
