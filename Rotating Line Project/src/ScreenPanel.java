import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScreenPanel extends JPanel {
	private int radius;
	private double angle = 0;
	private int stroke;
	private int sides;
	private int[] xPoints;
	private int[] yPoints;
	private boolean coversPanel = false;

	public ScreenPanel(int sides, int radius, int stroke) {
		this.stroke = stroke;
		this.sides = sides;
	}

	public ScreenPanel(int stroke) {
		this(2, 1, stroke);
		coversPanel = true;
	}

	public ScreenPanel() {
		this(0);
	}

	public void rotate(double angle) {
		this.angle += angle;
		if (angle > 360)
			angle -= 360;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = this.getSize();
		double x = size.getWidth();
		double y = size.getHeight();
		if (coversPanel)
			radius = (int) Math.round(x + y);
		g2d.setStroke(new BasicStroke(stroke));
		int centerX = (int) Math.round(x / 2);
		int centerY = (int) Math.round(y / 2);
		int[] xCoords = new int[sides];
		int[] yCoords = new int[sides];
		for (int i = 0; i < sides; i++) {
			xCoords[i] = (int) (radius * Math.cos((angle + 360 / sides * i)
					* Math.PI / 180))
					+ centerX;
			yCoords[i] = (int) (radius * Math.sin((angle + 360 / sides * i)
					* Math.PI / 180))
					+ centerY;
		}
		g2d.drawPolygon(new Polygon(xCoords, yCoords, sides));
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

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public double getAngle() {
		return angle;
	}

	public int getSides() {
		return sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}

}
