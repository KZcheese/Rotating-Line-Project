import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScreenPanel extends JPanel {
	private int radius = 0;
	private double angle = 0;
	private int sides;
	private int lineWidth;

	public ScreenPanel(int sides) {
		this.sides = sides;
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
		int xCoord = radius * (int) Math.round(Math.cos(angle * Math.PI / 180));
		int yCoord = radius * (int) Math.round(Math.sin(angle * Math.PI / 180));
		g2d.drawLine(centerX - xCoord, centerY - yCoord, centerX + xCoord,
				centerY + yCoord);
	}
}
