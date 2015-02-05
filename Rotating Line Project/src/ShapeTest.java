import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ShapeTest extends JFrame implements ActionListener {
	ScreenPanel panel = new ScreenPanel(2);
	JButton paint = new JButton("repaint");

	public ShapeTest() {
		this.setPreferredSize(new Dimension(600, 600));
		paint.setActionCommand("repaint");
		this.add(paint);
		this.add(panel);
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ShapeTest();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if (command.equals("repaint")) {
			paint.repaint();
		}

	}
}
