/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Set;
import java.util.TreeSet;

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
	private Set<Celestial> planets;
	private Set<SpaceObject> stations;

	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text (i.e. Running...)

	// Game constants
	public static final int COURT_WIDTH = 900;
	public static final int COURT_HEIGHT = 600;
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;

	public GameCourt(JLabel status) {
	    super();
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
	    
	    setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start(); 
		setFocusable(true);

		int effect = 2;
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				    ship.force(-effect, 0); }
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				    ship.force(effect, 0); }
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				    ship.force(0, -effect); }
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
				    ship.force(0, effect); }
			}

			public void keyReleased(KeyEvent e) {
				//
			}
		});

		this.status = status;
	}

	public void reset() {

		ship = new SpaceShip(100, 100);
		planets = new TreeSet<>();
		//planets.add(new Celestial(200, 200, "earthlike.png", true));
		//planets.add(new Celestial(300, 300, "earthlike.png", true));
		//planets.add(new Celestial(800, 300, "earthlike.png", true));
		//planets.add(new Celestial(650, 250, "balloffire.png", false));
		//planets.add(new Celestial(650, 350, "rocky.png", true));
		planets.add(new Celestial(650, 350, "gasgiant.png", true));
		
		stations = new TreeSet<>();
		stations.add(new SpaceObject(800, 500, "station.png"));

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
			
		    for (Celestial c : planets) {
		        gravity(ship, c);
		    }
		    //wallGravity(ship);
		    
			ship.move();
			
			repaint();
			
			for (Celestial c : planets) {
			    if (ship.intersects(c)) {
	                playing = false;
	                status.setText("You lose!");
	            }
            }
			
			for (SpaceObject s : stations) {
                if (ship.intersects(s)) {
                    playing = false;
                    status.setText("You Win!");
                }
            }
		}
	}
	
	public static void gravity (SpaceShip body, Celestial planet) {
	    
	    int distance = (int)(Math.sqrt(Math.pow(body.getCenterX() - planet.getCenterX(), 2) +
	                                   Math.pow(body.getCenterY() - planet.getCenterY(), 2)));
	    
	    double xForce = ((double)planet.getGravity() / Math.pow(distance, 2));
	    double yForce = ((double)planet.getGravity() / Math.pow(distance, 2));
	    if (body.getCenterX() - planet.getCenterX() >= 0) {
	        xForce = -xForce;
	    }
	    if (body.getCenterY() - planet.getCenterY() >= 0) {
            yForce = -yForce;
        }
	    
	    body.force(xForce, yForce);
	}
	
	private void wallGravity (SpaceShip body) {
	    double GravityCoef = 1000;
	    
	    double xForce = 0; double yForce = 0;
	    int distance = body.getCenterX();
	    xForce += ((double)GravityCoef / Math.pow(distance, 3));
	    xForce -= ((double)GravityCoef / Math.pow(COURT_WIDTH - distance, 3));
	    
	    distance = body.getCenterY();
        yForce += ((double)GravityCoef / Math.pow(distance, 3));
        yForce -= ((double)GravityCoef / Math.pow(COURT_HEIGHT - distance, 3));
        
        body.force(xForce, yForce);
	}

	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
		ship.draw(g);
		for (Celestial c : planets) {
		    c.draw(g); 
		}
		for (SpaceObject s : stations) {
            s.draw(g); 
        }
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
