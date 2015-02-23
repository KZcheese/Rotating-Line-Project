import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScreenPanel extends JPanel {
	private int radius;
	private double angle;
	private int stroke;
	private int sides;
	private Color color;

	public ScreenPanel(int sides, int radius, int stroke, double angle,
			Color color) {
		this.radius = radius;
		this.stroke = stroke;
		this.angle = angle;
		this.sides = sides;
		this.color = color;
	}

	public ScreenPanel() {
		this(2, 100, 0, 0, new Color(0, 0, 0));
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
		g2d.setStroke(new BasicStroke(stroke));
		g2d.setColor(color);
		int centerX = (int) Math.round(x / 2);
		int centerY = (int) Math.round(y / 2);
		int[] xCoords = new int[sides];
		int[] yCoords = new int[sides];
		int rTemp = radius;
		if (sides == 2)
			rTemp = (int) Math.round(Math.sqrt((centerX * centerX + centerY
					* centerY)));
		for (int i = 0; i < sides; i++) {
			xCoords[i] = (int) (rTemp * Math.cos((angle + (360 / sides) * i)
					* Math.PI / 180))
					+ centerX;
			yCoords[i] = (int) (rTemp * Math.sin((angle + (360 / sides) * i)
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
		repaint();
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
		repaint();
	}

	public int getSides() {
		return sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
		repaint();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color = c;
		repaint();
	}

}
