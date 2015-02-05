import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ScreenPanel extends JPanel {
	private double radius = 0;
	private double angle = 0;
	private int sides;

	public ScreenPanel(int sides) {
		this.sides = sides;
		this.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = this.getSize();
		double x = size.getWidth();
		double y = size.getHeight();
		int sizeMultiply = (int) Math.round(x + y);
		int centerX = (int) Math.round(x / 2);
		int centerY = (int) Math.round(y / 2);
		int xCoord = sizeMultiply
				* (int) Math.round(Math.cos(radius * Math.PI / 180));
		int yCoord = sizeMultiply
				* (int) Math.round(Math.sin(radius * Math.PI / 180));
		g2d.drawLine(centerX - xCoord, centerY - yCoord, centerX + xCoord,
				centerY + yCoord);
	}
}
