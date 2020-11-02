package qr;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

public class QR implements IQR {

//	String directoryName = System.getProperty("user.dir") + "\\.recibos";
	private final String fileName = "\\Recibo_num_";

	@Override
	public BufferedImage writeQR(JLabel label, String info) {
		BitMatrix matrix;
		Writer wr = new QRCodeWriter();

		ciclo: while (true) {
			try {
				matrix = wr.encode(info, BarcodeFormat.QR_CODE, QR_IMAGE_SIZE, QR_IMAGE_SIZE);
			} catch (WriterException e) {
				e.printStackTrace();
				continue ciclo;
			}
			break ciclo;
		}
		BufferedImage image = new BufferedImage(QR_IMAGE_SIZE, QR_IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < QR_IMAGE_SIZE; y++) {
			for (int x = 0; x < QR_IMAGE_SIZE; x++) {
				int greyValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
				image.setRGB(x, y, ((greyValue == 0) ? 0 : 0xFFFFFF));
			}
		}
		// CAUTION
		// FIXME: is this part necessary??
		Image thumbnail = image.getScaledInstance(label.getWidth() - 5, -1, Image.SCALE_SMOOTH);
		BufferedImage buf2 = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		buf2.getGraphics().drawImage(thumbnail, 0, 0, null);

		return image;
	}

	@Override
	public String readQR(File file) {
		BufferedImage image = this.read(file);
		if (image != null) {
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			
			try {
				Result result = new MultiFormatReader().decode(bitmap);
				return result.toString();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String readQR(String num) {
		BufferedImage image = this.read(num);
		if (image != null) {
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			
			try {
				Result result = new MultiFormatReader().decode(bitmap);
				return result.toString();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		return null;
	}

	@Override
	public BufferedImage read(File file) {
		if (file != null) {
			try {
				BufferedImage bufferedImage = ImageIO.read(file);
				return bufferedImage;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public BufferedImage read(String num) {
		final String s = directoryName + "\\" + num;
		File file = new File(s);
		return this.read(file);
	}

	@Override
	public BufferedImage readAndPlace(File file, JLabel label) {

		BufferedImage buf1 = null;
		buf1 = this.read(file);

		Image thumbnail = buf1.getScaledInstance(label.getWidth() - 5, -1, Image.SCALE_SMOOTH);
		BufferedImage buf2 = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		buf2.getGraphics().drawImage(thumbnail, 0, 0, null);

		return buf2;
	}

	@Override
	public BufferedImage readAndPlace(String num, JLabel label) {
		BufferedImage buf1 = null;
		buf1 = this.read(num);

		Image thumbnail = buf1.getScaledInstance(label.getWidth() - 5, -1, Image.SCALE_SMOOTH);
		BufferedImage buf2 = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		buf2.getGraphics().drawImage(thumbnail, 0, 0, null);

		return buf2;
	}

	@Override
	public void save(BufferedImage bufferedImage) {
		// File directory = new File(System.getProperty("user.dir") + "\\recibos");
		File directory = new File(directoryName);

		// if (!directory.exists()) {
		// directory.mkdir();
		// }
		directoryExists();

		// int a = directory.listFiles().length;

		// File file = new File(directoryName + (a + 1));
//		File file = new File(directoryName + fileName + (directory.listFiles().length + 1) + ".png");
		File file = new File(directoryName + "\\" + (directory.listFiles().length + 1) + ".png");

		System.out.println("SAVING......");
		// System.out.println("FILE:\t" + directoryName);
		System.out.println("FILE:\t" + file.getPath());
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			ImageIO.write(bufferedImage, IMAGE_FORMAT, outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void directoryExists() {
		File f = new File(directoryName);
		if (!f.exists()) {
			f.mkdir();
		}
		if (f.isDirectory()) {
			Path path = f.toPath();
			try {
				Files.setAttribute(path, "dos:hidden", true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// @Override
	// public void changeState() {
	// File f = new File(directoryName);
	// if (!f.exists()) {
	// f.mkdir();
	// }
	// if (f.isDirectory()) {
	// Path path = f.toPath();
	// try {
	// Files.setAttribute(path, "dos:hidden", true);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }

	// @Override
	// public void changeState() {
	// File f = new File(System.getProperty("user.dir") + "\\recibos");
	// if (f.isDirectory() && f != null) {
	//// Path path = Paths.get(f.getPath());
	// Path path = f.toPath();
	//// Files.setAttribute(f.getPath(), "dos:hidden",true);
	// try {
	// System.out.println(f.isHidden());
	// if (!f.isHidden()) {
	// System.out.println("hide");
	// // hide
	// Files.setAttribute(path, "dos:hidden", true);
	// } else {
	// System.out.println("unhide");
	//// //unhide
	// Files.setAttribute(path, "dos:hidden", false);
	//
	// }
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// System.err.println(e.getMessage() + " ");
	// }
	// }
	// }

}
