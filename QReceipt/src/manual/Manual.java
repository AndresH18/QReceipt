package manual;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;

public class Manual {

	public static void openManual() {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop()
						.open(new File(System.getProperty("user.dir") + "\\.docs\\Manual de usuario QReceipt.pdf"));
			} else {
				Toolkit.getDefaultToolkit().beep();
			}
		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();
		}
	}
}
