import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Class that displays 3 Jpanels on one frame, with 2 Jpanels for the toolbar
 * and one for the rotating polygon, incorporated from the ScreenPanel class.
 * This class can start and stop a polygon from rotating, change its direction,
 * increase or decrease the speed of rotation, change the width of the polygon,
 * change the radius size for polygons with more than 2 sides, choose the color
 * of the polygon, and change the amount of sides on the polygon.
 * 
 * @author Kevin Zhan
 * @author Umanga Balasuriya
 * @version Last updated 2/28/15
 */
@SuppressWarnings("serial")
public class Frame extends JFrame implements ActionListener, ChangeListener {
	// creating the panels
	private JPanel toolbar = new JPanel();
	private JPanel toolbar2 = new JPanel();
	private ScreenPanel screen = new ScreenPanel();
	// creating a boolean for when the entire program is running
	private boolean isPlaying = false;
	// creating a timer variable for starting and stopping the rotations
	private Timer timer = new Timer(8, this);
	// creating a button that both starts and stops the rotations based on
	// which is currently going
	private JButton startstop = new JButton("Start");
	// creating a button to change the direction of the rotation
	private JButton changeDirec = new JButton("Change Direction");
	// creating a default layout bar
	private FlowLayout bar = new FlowLayout();
	// creating integers to place in for minimum speed, max speed, and initial
	// speed
	private final int speed_min = 0;
	private final int speed_max = 5;
	private int speed = 2;
	// creating a slider with a label for speed
	private JSlider speeds = new JSlider(JSlider.HORIZONTAL, speed_min,
			speed_max, speed);
	private JLabel speedLabel = new JLabel("Speed", JLabel.CENTER);
	// creating integers for max, min, and initial widths
	private final int width_min = 0;
	private int stroke = 2;
	private final int width_max = 10;
	// creating a slider with a label for width
	private JSlider width = new JSlider(JSlider.HORIZONTAL, width_min,
			width_max, stroke);
	private JLabel widthLabel = new JLabel("Width", JLabel.CENTER);
	// creating a boolean variable for the program to understand which way the
	// line is rotating
	private boolean clockwise = true;
	// creating a default set of sides, two being a line
	int sides = 2;
	// creating a slider with label for number of sides, going from 2 sides to
	// 10
	private JLabel sideLabel = new JLabel("Sides", JLabel.CENTER);
	private JSlider sideSlider = new JSlider(JSlider.HORIZONTAL, 2, 10, sides);
	// creating a button for choosing color
	private JButton chooseColor = new JButton("Choose Color");
	// creating a default radius, from 0 to 500 for the slider
	private int radius = 100;
	private JSlider radiusSlider = new JSlider(JSlider.HORIZONTAL, 0, 500,
			radius);
	private JLabel radiusLabel = new JLabel("Radius", JLabel.CENTER);

	/**
	 * This constructor adds all the buttons and sliders to the 2 panels, adds
	 * in the screen panel, and puts them all in one frame.
	 */
	public Frame() {
		// creating an initial size and minimum size for the frame
		this.setPreferredSize(new Dimension(800, 600));
		this.setMinimumSize(new Dimension(800, 300));
		toolbar.setLayout(bar);
		// creating an action command for the timer
		timer.setActionCommand("Play");
		// creating an action command for the start stop button
		startstop.setActionCommand("startstop");
		startstop.addActionListener(this);
		// adding the button to the panel
		this.toolbar.add(startstop);

		changeDirec.setActionCommand("changeDirec");
		changeDirec.addActionListener(this);
		// adding the button to the panel
		this.toolbar.add(changeDirec);
		// modifying the speed slider
		speedLabel.setAlignmentX(Component.TOP_ALIGNMENT);
		this.toolbar.add(speedLabel);
		speeds.addChangeListener(this);
		speeds.setMajorTickSpacing(1);
		speeds.setPaintTicks(true);
		speeds.setPaintLabels(true);
		// adding the button to the panel
		this.toolbar.add(speeds);
		toolbar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		// modifying the width slider
		this.toolbar.add(widthLabel);
		width.addChangeListener(this);
		width.setMinorTickSpacing(1);
		width.setMajorTickSpacing(2);
		width.setPaintTicks(true);
		width.setPaintLabels(true);
		// adding the button to the panel
		this.toolbar.add(width);
		// modifying the radius slider
		this.toolbar2.add(radiusLabel);
		radiusSlider.addChangeListener(this);
		radiusSlider.setMinorTickSpacing(25);
		radiusSlider.setMajorTickSpacing(100);
		radiusSlider.setPaintTicks(true);
		radiusSlider.setPaintLabels(true);
		// adding the button to the second panel
		this.toolbar2.add(radiusSlider);
		toolbar2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		// modifying the color button
		chooseColor.setActionCommand("chooseColor");
		chooseColor.addActionListener(this);
		// adding the button to the second panel
		this.toolbar2.add(chooseColor);
		// modifying the side slider
		this.toolbar2.add(sideLabel);
		sideSlider.addChangeListener(this);
		sideSlider.setMinorTickSpacing(1);
		sideSlider.setMajorTickSpacing(2);
		sideSlider.setPaintTicks(true);
		sideSlider.setPaintLabels(true);
		// adding the button to the second panel
		this.toolbar2.add(sideSlider);
		// creating a split pane to put the panels over each other
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, toolbar,
				toolbar2);
		JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, split,
				screen);
		split.setDividerSize(0);
		split2.setDividerSize(0);
		// adding the split pane to the frame
		this.add(split2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}

	/**
	 * This method displays the frame constructor
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Frame();
	}

	/**
	 * This method overrides the default actions performed and allows the
	 * buttons to do different actions
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();
		// this action occurs if the start stop button is pressed
		if (action.equals("startstop")) {
			// if the boolean is not true, making the false false a true, the
			// timer starts and the button changes to stop
			// if the boolean is true, making the false true a false, the timer
			// stops and the button changes to start
			if (!isPlaying) {
				timer.start();
				startstop.setText("Stop ");
				isPlaying = true;
			} else {
				timer.stop();
				startstop.setText("Start");
				isPlaying = false;
			}
		} // this action occurs if the change direction button is pressed
		else if (action.equals("Play")) {
			// if the boolean expression is true, the screen rotates at the
			// current speed
			// if the boolean is not true, the screen changes its rotation and
			// rotates at the negative speed, or counter-clockwise
			// the polygon repaints
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
		} // this action occurs if the choose color button is pressed
		else if (action.equals("chooseColor")) {
			// this creates a color chooser window that allows the user to
			// change the color
			Color c = JColorChooser.showDialog(null, "Choose a Color",
					screen.getColor());
			// if the color variable is not void, when a color is chosen the
			// polygon will set its color to the given color
			if (c != null)
				screen.setColor(c);
		}
	}

	/**
	 * This method overrides the default states and allows the sliders to change
	 * the state of the polygon
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		JSlider slid = (JSlider) arg0.getSource();
		// this state changes if the speed slider is used
		if (slid.equals(speeds)) {
			// if the speed is 0, the timer stops
			// if the timer is not running and the boolean is Playing is false,
			// the timer starts
			speed = slid.getValue();
			if (speed == 0)
				timer.stop();
			else if (!timer.isRunning() && isPlaying)
				timer.start();
		} // this state changes if the width slider is used
		else if (slid.equals(width)) {
			// the stroke(width) is set to the value given by the user on the
			// slider and repaints at that width
			stroke = slid.getValue();
			screen.setStroke(stroke);
			screen.repaint();
		} // this state changes if the radius slider is used
		else if (slid.equals(radiusSlider)) {
			// the radius is set to the value given by the user on the slider
			// and repaints at that radius
			// radius only works for sides greater than 2
			radius = slid.getValue();
			screen.setRadius(radius);
			screen.repaint();
		} // this state changes if the side slider is used
		else if (slid.equals(sideSlider)) {
			// the side is set to the value given by the user on the slider and
			// repaints at that side value
			sides = slid.getValue();
			screen.setSides(sides);
			screen.repaint();
		}
	}
}
