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
	private int radius = 2;
	private JSlider radiusSlider = new JSlider(JSlider.HORIZONTAL, width_min, width_max, radius);
	private JLabel radiusLabel = new JLabel("Radius", JLabel.CENTER);
	
	public Frame() {
		this.setPreferredSize(new Dimension(1000, 500));
		this.setMinimumSize(new Dimension(1000, 300));

		toolbar.setLayout(bar);
		timer.setActionCommand("Play");
		startstop.setActionCommand("startstop");
		startstop.addActionListener(this);
		this.toolbar.add(startstop);

		// change direction
		changeDirec.setActionCommand("changeDirec");
		changeDirec.addActionListener(this);
		this.toolbar.add(changeDirec);

		// mph
		speedLabel.setAlignmentX(Component.TOP_ALIGNMENT);
		// change speed double using speed variable
		this.toolbar.add(speedLabel);
		speeds.addChangeListener(this);
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
		
		this.toolbar.add(radiusLabel);
		radiusSlider.addChangeListener(this);
		radiusSlider.setMinorTickSpacing(1);
		radiusSlider.setMajorTickSpacing(0);
		radiusSlider.setMajorTickSpacing(10);
		radiusSlider.setMajorTickSpacing(2);
		radiusSlider.setPaintTicks(true);
		radiusSlider.setPaintLabels(true);
		this.toolbar.add(radiusSlider);
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
				timer.start();
				startstop.setText("Stop ");
				isPlaying = true;

			} else {
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
		JSlider slid = (JSlider) arg0.getSource();
		if (slid.equals(speeds)) {
			speed = slid.getValue();
			if (speed == 0)
				timer.stop();
			else if (!timer.isRunning() && isPlaying)
				timer.start();
		} else if (slid.equals(width)) {
			stroke = slid.getValue();
			screen.setStroke(stroke);
			screen.repaint();
		}
		
	}
}
