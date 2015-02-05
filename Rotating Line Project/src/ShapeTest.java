import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class ShapeTest extends JFrame implements ActionListener {
	ScreenPanel panel = new ScreenPanel(2);
	JPanel Toolbar = new JPanel();
	JButton play = new JButton("play");
	Timer timer = new Timer(1000 / 60, this);

	public ShapeTest() {
		this.setPreferredSize(new Dimension(600, 600));
		timer.setActionCommand("playing");
		play.setActionCommand("play");
		panel.add(play);
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
		System.out.println("painting");
		if (command.equals("play")) {
			timer.start();
		} else if (command.equals("playing")) {
			panel.rotate(5);
			panel.repaint();
		}

	}
}
