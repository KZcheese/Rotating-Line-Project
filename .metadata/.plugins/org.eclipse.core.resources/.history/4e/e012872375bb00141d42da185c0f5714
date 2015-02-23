import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Frame extends JFrame implements ActionListener, ChangeListener {
	private JPanel toolbar = new JPanel();
	private ScreenPanel screen = new ScreenPanel();
	private boolean isPlaying = false;
	private Timer timer = new Timer(8, this);
	private JButton startstop = new JButton("Start");
	private JButton changeDirec = new JButton("Change Direction");
	private FlowLayout bar = new FlowLayout();
	private final int speed_min = 0;
	private final int speed_max = 5;
	private int speed = 2;
	private JSlider speeds = new JSlider(JSlider.HORIZONTAL, speed_min,
			speed_max, speed);
	private JLabel speedLabel = new JLabel("Speed", JLabel.CENTER);
	private final int width_min = 0;
	private int stroke = 2;
	private final int width_max = 10;
	private JSlider width = new JSlider(JSlider.HORIZONTAL, width_min,
			width_max, stroke);
	private JLabel widthLabel = new JLabel("Width", JLabel.CENTER);
	private boolean clockwise = true;

	public Frame() {
		this.setPreferredSize(new Dimension(800, 500));
		this.setMinimumSize(new Dimension(750, 300));

		toolbar.setLayout(bar);
		timer.setActionCommand("Play");
		startstop.setActionCommand("startstop");
		startstop.addActionListener(this);
		this.toolbar.add(startstop);

		// change direction
		timer.setActionCommand("Play");
		changeDirec.setActionCommand("changeDirec");
		changeDirec.addActionListener(this);
		this.toolbar.add(changeDirec);

		// mph
		speedLabel.setAlignmentX(Component.TOP_ALIGNMENT);
		// change speed double using speed variable
		this.toolbar.add(speedLabel);

		speeds.addChangeListener(this);
		// speeds.setMinorTickSpacing(1);
		speeds.setMajorTickSpacing(1);
		speeds.setPaintTicks(true);
		speeds.setPaintLabels(true);
		this.toolbar.add(speeds);
		toolbar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		// setStroke
		this.toolbar.add(widthLabel);
		width.addChangeListener(this);
		width.setMinorTickSpacing(1);
		width.setMajorTickSpacing(0);
		width.setMajorTickSpacing(10);
		width.setMajorTickSpacing(2);
		width.setPaintTicks(true);
		width.setPaintLabels(true);
		this.toolbar.add(width);
		toolbar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, toolbar,
				screen);
		split.setDividerSize(0);
		this.add(split);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}

	public static void main(String[] args) {
		new Frame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();
		if (action.equals("startstop")) {
			if (!isPlaying) {
				// start timer and stuff
				// if(action.equals("changeDirec")){
				// screen.rotate(-1);
				// }
				timer.start();
				startstop.setText("Stop ");
				isPlaying = true;

			} else {
				// stop timer and stuff
				timer.stop();
				startstop.setText("Start");
				isPlaying = false;
			}

		} else if (action.equals("Play")) {
			// do the thing that actually plays
			if (clockwise)
				screen.rotate(speed);
			else
				screen.rotate(-1 * speed);
			screen.repaint();
		} else if (action.equals("changeDirec")) {
			if (clockwise)
				clockwise = false;
			else
				clockwise = true;
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {

	}
}
