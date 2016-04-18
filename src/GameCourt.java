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
	private Square square; // the Black Square, keyboard control
	private Circle snitch; // the Golden Snitch, bounces
	private Poison poison; // the Poison Mushroom, doesn't move
	private SpaceShip ship;
	private Celestial planet;

	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text (i.e. Running...)

	// Game constants
	public static final int COURT_WIDTH = 300;
	public static final int COURT_HEIGHT = 300;
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
				    ship.force(-1, 0); }
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				    ship.force(1, 0); }
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				    ship.force(0, 1); }
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
				    ship.force(0, -1); }
			}

			public void keyReleased(KeyEvent e) {
				//
			}
		});

		this.status = status;
	}

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {

		square = new Square(COURT_WIDTH, COURT_HEIGHT);
		poison = new Poison(COURT_WIDTH, COURT_HEIGHT);
		snitch = new Circle(COURT_WIDTH, COURT_HEIGHT);
		ship = new SpaceShip(100, 100, Color.RED);
		planet = new Celestial(200, 200, Color.BLUE, 30);
		

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
			// advance the square and snitch in their
			// current direction.
			square.move();
			snitch.move();
			gravity(ship, planet);
			ship.move();

			// make the snitch bounce off walls...
			snitch.bounce(snitch.hitWall());
			// ...and the mushroom
			snitch.bounce(snitch.hitObj(poison));

			// check for the game end conditions
			if (square.intersects(poison)) {
				playing = false;
				status.setText("You lose!");

			} else if (square.intersects(snitch)) {
				playing = false;
				status.setText("You win!");
			}

			// update the display
			repaint();
		}
	}
	
	private void gravity (SpaceShip body, Celestial planet) {
	    int Xdistance = body.getCenterX() - planet.getCenterX();
	    int Ydistance = body.getCenterY() - planet.getCenterY();
	    
	    
	    
	    System.out.println(Xdistance);
	    
	    body.force((int)((double)planet.getGravity() / Math.pow(Xdistance, 2)),
	               (int)((double)planet.getGravity() / Math.pow(Ydistance, 2)));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		square.draw(g);
		poison.draw(g);
		snitch.draw(g);
		ship.draw(g);
		planet.draw(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
