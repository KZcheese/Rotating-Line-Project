import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScreenPanel extends JPanel {
	private int radius = 0;
	private double angle = 0;
	private int sides;
	private int stroke;
	private Polygon p;

	public ScreenPanel() {
		this.setVisible(true);
	}

	public void rotate(double angle) {
		this.angle += angle;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = this.getSize();
		double x = size.getWidth();
		double y = size.getHeight();
		radius = (int) Math.round(x + y);
		int centerX = (int) Math.round(x / 2);
		int centerY = (int) Math.round(y / 2);
		int xCoord = (int) (radius * Math.cos(angle * Math.PI / 180));
		int yCoord = (int) (radius * Math.sin(angle * Math.PI / 180));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawLine(centerX - xCoord, centerY - yCoord, centerX + xCoord,
				centerY + yCoord);
		g2d.setStroke(new BasicStroke(0));
	}

	public int getStroke() {
		return stroke;
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
	}

	public int getRadius() {
		return radius;
	}

	public double getAngle() {
		return angle;
	}

	public int getSides() {
		return sides;
	}

	public Polygon getP() {
		return p;
	}
}
