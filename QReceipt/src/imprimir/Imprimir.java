package imprimir;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Imprimir {

	private JPanel panel;
	private JLabel label;

	public Imprimir(JPanel panel, JLabel label) {
		this.panel = panel;
		this.label = label;
	}

	public boolean ImprimirPanel(double x, double y) {
		PrinterJob job = PrinterJob.getPrinterJob();

		job.setPrintable(new MyPanel(panel, x, y).get());

		boolean b = job.printDialog();		
//		if (job.printDialog()) {
		if (b) {
			try {
				job.print();
				return true;
			} catch (PrinterException e) {
				System.err.println("Mistake Printing");
				System.out.println(e.getLocalizedMessage());
			}
		}
		return false;
	}

	public boolean ImprimirLabel(double x, double y) {
		PrinterJob job = PrinterJob.getPrinterJob();

		job.setPrintable(new MyLabel(label, x, y).get());
		boolean b = job.printDialog();
//		if (job.printDialog()) {
		if (b) {
			try {
				job.print();
				return true;
			} catch (PrinterException e) {
				System.err.println("Mistake Printing");
				System.out.println(e.getLocalizedMessage());
			}
		}
		return false;
	}

}

class MyLabel extends JLabel implements Printable {
	private JLabel label;
	private double sx;
	private double sy;

	public MyLabel(JLabel label, double sx, double sy) {
		this.label = label;
		this.sx = sx;
		this.sy = sy;
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex > 0) {
			return NO_SUCH_PAGE;
		}
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//		g2d.scale(0.75, 0.75);
		g2d.scale(sx, sy);

		label.print(g2d);

		return PAGE_EXISTS;
	}

	public MyLabel get() {
		return this;
	}
}

class MyPanel extends JPanel implements Printable {

	private JPanel panel;
	private double sx;
	private double sy;

	public MyPanel(JPanel panel, double sx, double sy) {
		this.panel = panel;
		this.sx = sx;
		this.sy = sy;
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex > 0) {
			return NO_SUCH_PAGE;
		}
		Graphics2D g2d = (Graphics2D) graphics;
//		g2d.translate(pageFormat.getImageableX() + 30, pageFormat.getImageableY() + 30);
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//		g2d.scale(0.75, 0.75);
		g2d.scale(sx, sy);

		panel.print(g2d);

		return PAGE_EXISTS;
	}

	public MyPanel get() {
		return this;
	}
}