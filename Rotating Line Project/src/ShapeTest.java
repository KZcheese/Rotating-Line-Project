import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class ShapeTest extends JFrame implements ActionListener {
	ScreenPanel panel = new ScreenPanel();
	JPanel Toolbar = new JPanel();
	JButton play = new JButton("play");
	JButton stop = new JButton("stop");
	Timer timer = new Timer(1000 / 120, this);

	public ShapeTest() {
		this.setPreferredSize(new Dimension(600, 600));
		timer.setActionCommand("playing");
		play.addActionListener(this);
		play.setActionCommand("play");
		stop.addActionListener(this);
		stop.setActionCommand("stop");
		panel.add(play);
		panel.add(stop);
		panel.setStroke(10);
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
		System.out.println("painting");
		String command = arg0.getActionCommand();
		if (command.equals("play")) {
			timer.start();
		} else if (command.equals("playing")) {
			panel.rotate(0.5);
			panel.repaint();
		} else if (command.equals("stop")) {
			timer.stop();
		}

	}
}
