import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

/**
 * A class that displays a regular polygon that can rotate with modifiable line
 * width, side number, radius, and color.
 * 
 * @author Kevin Zhan
 * @author Umanga Balasuriya
 * @version Last updated 2/28/15
 */
@SuppressWarnings("serial")
public class ScreenPanel extends JPanel {
	private int radius;
	private double angle;
	private int stroke;
	private int sides;
	private Color color;

	/**
	 * Angle is stored in degrees. Radius and stroke are both stored as number
	 * of pixels.
	 * 
	 * @param sides
	 * @param radius
	 * @param stroke
	 * @param angle
	 * @param color
	 */
	public ScreenPanel(int sides, int radius, int stroke, double angle,
			Color color) {
		this.radius = radius;
		this.stroke = stroke;
		this.angle = angle;
		this.sides = sides;
		this.color = color;
	}

	/**
	 * Creates a ScreenPanel with default attributes of 2 sides, 100px radius,
	 * 0px stroke, 0°, and a color of (0, 0, 0) black.
	 */
	public ScreenPanel() {
		this(2, 100, 0, 0, new Color(0, 0, 0));
	}

	/**
	 * Rotates the polygon by the given number degrees. If the angle is greater
	 * than 360° the equivalent value between 0° and 360° will be used instead.
	 * 
	 * @param angle
	 */
	public void rotate(double angle) {
		this.angle += angle;
		if (angle > 360)
			angle -= 360;
	}

	/**
	 * Paints the line as a polygon object using polar coordinates. If the shape
	 * has 2 sides, the radius value will be ignored and a line will be drawn
	 * across the entire window instead.
	 */
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

	/*
	 * All setters automatically repaint the shape.
	 */
	public int getStroke() {
		return stroke;
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
		repaint();
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
