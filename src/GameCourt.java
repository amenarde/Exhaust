/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
	private SpaceShip ship;
	private Celestial planet;

	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text (i.e. Running...)

	// Game constants
	public static final int COURT_WIDTH = 1000;
	public static final int COURT_HEIGHT = 1000;
	public static final int SQUARE_VELOCITY = 4;
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;

	public GameCourt(JLabel status) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start(); 
		setFocusable(true);

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				    ship.force(-10, 0); }
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				    ship.force(10, 0); }
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				    ship.force(0, 10); }
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
				    ship.force(0, -10); }
			}

			public void keyReleased(KeyEvent e) {
				//
			}
		});

		this.status = status;
	}

	public void reset() {

		ship = new SpaceShip(100, 100, Color.RED);
		planet = new Celestial(200, 200, Color.BLUE, 20);
		

		playing = true;
		status.setText("Running...");

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {
		if (playing) {
			gravity(ship, planet);
			ship.move();
			
			repaint();
			
			if (ship.intersects(planet)) {
			    playing = false;
                status.setText("You lose!");
			}
		}
	}
	
	private void gravity (SpaceShip body, Celestial planet) {
	    
	    int distance = (int)(Math.sqrt(Math.pow(body.getCenterX() - planet.getCenterX(), 2) +
	                                   Math.pow(body.getCenterY() - planet.getCenterY(), 2)));
	    
	    int xForce = (int)((double)planet.getGravity() / Math.pow(distance, 0.75));
	    int yForce = (int)((double)planet.getGravity() / Math.pow(distance, 0.75));
	    if (body.getCenterX() - planet.getCenterX() >= 0) {
	        xForce = -xForce;
	    }
	    if (body.getCenterY() - planet.getCenterY() >= 0) {
            yForce = -yForce;
        }
	    
	    body.force(xForce, yForce);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ship.draw(g);
		planet.draw(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
