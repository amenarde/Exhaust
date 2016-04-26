/**
 * Exhaust
 * (c) Antonio Menarde
 * @version 1.0, Apr 2016
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.*;


@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
	private SpaceShip ship;
	private SpaceObject egg;
	private Set<Celestial> planets;
	private Set<SpaceObject> stations;
	private int fuelLeft;
	private int levelNumber;
	private boolean easterEgg = false;

	public boolean playing = false; // whether the game is running
	private JLabel status;          // Current status text (i.e. Running...)
	private JLabel fuel;
	private JLabel level;
	private Game game;

	// Game constants
	public static final int COURT_WIDTH = 900;
	public static final int COURT_HEIGHT = 600;
	public static final int INTERVAL = 35; // Update interval for timer, in milliseconds

	public GameCourt(JLabel status, JLabel level, JLabel fuel, Game game) {
	    super();
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        this.game = game;
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
				if (e.getKeyCode() == KeyEvent.VK_LEFT && fuelLeft > 0) {
				    ship.force(-effect, 0); fuelLeft--; }
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT && fuelLeft > 0) {
				    ship.force(effect, 0); fuelLeft--; }
				else if (e.getKeyCode() == KeyEvent.VK_DOWN && fuelLeft > 0) {
				    ship.force(0, -effect); fuelLeft--; }
				else if (e.getKeyCode() == KeyEvent.VK_UP && fuelLeft > 0) {
				    ship.force(0, effect); fuelLeft--; }
				else if (e.getKeyCode() == KeyEvent.VK_E) {
				    easterEgg = !easterEgg;
				}
			}

			public void keyReleased(KeyEvent e) {
				//
			}
		});

		this.status = status;
		this.fuel = fuel;
		this.level = level;
	}

	private void buildLevel() {
	    
	    planets = new TreeSet<>();
	    stations = new TreeSet<>();
	    egg = new SpaceObject(100, 500, "egg.png");
	            
	    switch (levelNumber) {
	    case 1: ship = new SpaceShip(100, 100);
	    		planets.add(new Celestial(300, 500, "galaxy.png", false));
	    		planets.add(new Celestial(250, 550, "gasgiant.png", true));
	    		planets.add(new Celestial(100, 525, "gasgiant.png", true));
	    		planets.add(new Celestial(175, 475, "gasgiant.png", true));
	    		planets.add(new Celestial(400, 525, "earthlike.png", true));
	    		planets.add(new Celestial(750, 550, "rocky.png", true));
	    		planets.add(new Celestial(775, 510, "rocky.png", true));
	    		planets.add(new Celestial(830, 485, "rocky.png", true));
	    		planets.add(new Celestial(865, 450, "rocky.png", true));
	    		planets.add(new Celestial(450, 100, "balloffire.png", false));
	    		planets.add(new Celestial(850, 550, "gasgiant.png", false));
	    		planets.add(new Celestial(850, 150, "rocky.png", true));
	    		planets.add(new Celestial(750, 50, "rocky.png", true));
	    		
	    		stations.add(new SpaceObject(800, 75, "station.png"));
	    		
	    		fuelLeft = 500;
                fuel.setText("Fuel: " + fuelLeft + " | ");
                level.setText("Level 1");
	    		
	    break;
	    
	    case 2: ship = new SpaceShip(100, 100);
                planets.add(new Celestial(650, 350, "gasgiant.png", true));
                stations.add(new SpaceObject(725, 425, "station.png"));
                fuelLeft = 1;
                fuel.setText("Fuel: " + fuelLeft + " | ");
                level.setText("Level 2");
                
        break;
	    case 3: ship = new SpaceShip(800, 100);
                planets.add(new Celestial(200, 200, "earthlike.png", true));
                planets.add(new Celestial(300, 300, "earthlike.png", true));
                planets.add(new Celestial(800, 300, "earthlike.png", true));
                planets.add(new Celestial(650, 250, "balloffire.png", false));
                planets.add(new Celestial(650, 350, "rocky.png", true));
                planets.add(new Celestial(650, 350, "gasgiant.png", true));
                stations.add(new SpaceObject(800, 500, "station.png"));
                fuelLeft = 3;
                fuel.setText("Fuel: " + fuelLeft + " | ");
                level.setText("Level 3");
                playing = false;
                status.setText(" | Press to begin playing ");
        break;
        default: playing = false;
                 game.win();
	    }
	}
	
	
	public void reset() {

		levelNumber = 1;
		buildLevel();
	    
	    playing = true;
		status.setText("");

		requestFocusInWindow(); // Make sure that this component has the keyboard focus
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {
		if (playing) {
			
		    //gravity
		    for (Celestial c : planets) {
		        gravity(ship, c);
		    }
		    
		    //move ship and check for wall conflicts
		    if (ship.move()) {
			    repaint();
	            playing = false;
                status.setText("");
			    game.lose();
			}
			
			//repaint
			repaint();
			
			//check for intersections
			for (Celestial c : planets) {
			    if (ship.intersects(c)) {
	                playing = false;
	                repaint();
	                status.setText("");
	                game.lose();
	            }
            }
			
			for (SpaceObject s : stations) {
                if (ship.intersects(s)) {
                    levelNumber++;
                    buildLevel();
                    repaint();
                }
            }
			
			fuel.setText("Fuel: " + fuelLeft + " | ");
		}
	}
	
	public static void gravity (SpaceShip body, Celestial planet) {
	    
	    int xDistance = Math.abs(body.getCenterX() - planet.getCenterX());
	    int yDistance = Math.abs(body.getCenterY() - planet.getCenterY());
	    int distance = (int)(Math.sqrt(Math.pow(xDistance, 2) +
	                                   Math.pow(yDistance, 2)));
	    double force = ((double)planet.getGravity() / Math.pow(distance, 2));
	    double xForce = force * (xDistance) / (xDistance + yDistance);
	    double yForce = force * (yDistance) / (xDistance + yDistance);
	    
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
		for (Celestial c : planets) {
		    c.draw(g); 
		}
		for (SpaceObject s : stations) {
            s.draw(g); 
        }
		
		if (easterEgg) {
		    egg.draw(g);
		}
	}
	
	public int getLevelNumber() {
	    return levelNumber;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
