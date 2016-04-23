/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Game implements Runnable {
    
    private static boolean gameLost;
    
    private final JFrame frame;
    
    //game resources
    private final JPanel control_panel;
    private final JLabel status;
    private final JLabel fuel;
    private final JLabel level;
    //instance of game
    private final GameCourt court;
    
    //intro and outro screens
    private final JLabel introScreen;
    private final JLabel outroScreen;
    private final JLabel winScreen;
    
    public Game() {
        gameLost = false;
        frame = new JFrame("Exhaust");
        control_panel = new JPanel();
        status = new JLabel("");
        fuel = new JLabel("");
        level = new JLabel("");
        
        court = new GameCourt(status, level, fuel, this);
        
        BufferedImage intro = null;
        BufferedImage outro = null;
        BufferedImage win = null;
        try {
            intro = ImageIO.read(new File("intro.png"));
            outro = ImageIO.read(new File("end.png"));
            win = ImageIO.read(new File("win.png"));
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        
        if (intro == null && outro == null) {
            introScreen = new JLabel();
            outroScreen = new JLabel();
            winScreen = new JLabel();
        }
        else {
            introScreen = new JLabel(new ImageIcon(intro));
            outroScreen = new JLabel(new ImageIcon(outro));
            winScreen = new JLabel(new ImageIcon(win));
        }
    }
    
    
    public void run() {
        
		
		frame.setLocation(100, 100);

		//Control Panel
		frame.add(control_panel, BorderLayout.NORTH);
	    
	    //Reset Button
	    final JButton reset = new JButton("PLAY");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gameLost) {
                    gameLost = false;
                    
                    System.out.println("Here1");
                    frame.remove(outroScreen);
                    frame.revalidate();
                    frame.add(court, BorderLayout.CENTER);
                    frame.repaint();
                    //frame.setBackground(Color.BLACK);
                    court.reset();
                    frame.setVisible(true);
                }
                else if (court.getLevelNumber() != 1) {
                    System.out.println("Here2");
                    court.playing = true;
                    court.requestFocusInWindow();
                    status.setText("");
                }
                else {
                    System.out.println("Here3");
                    frame.remove(introScreen);
                    frame.setVisible(true);
                    court.reset();
                    status.setText("");
                }
            }
        });
        
        //add to Control Panel
	    control_panel.add(fuel);
	    control_panel.add(reset);
	    control_panel.add(status);
	    control_panel.add(level);
		
		frame.add(introScreen, BorderLayout.CENTER);
		// Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
		// Main playing area
        frame.add(court, BorderLayout.CENTER);
        frame.setBackground(Color.BLACK);

		// Start game
		court.reset();
		court.playing = false;
		status.setText("");
		level.setText("");
		fuel.setText("");
	}
    
    public void lose() {
        court.reset();
        court.playing = false;
        frame.remove(court);
        frame.revalidate();
        frame.add(outroScreen, BorderLayout.CENTER);
        frame.repaint();
        gameLost = true;
        frame.setVisible(true);
    }
    
    public void win() {
        court.reset();
        court.playing = false;
        frame.remove(court);
        frame.revalidate();
        frame.add(winScreen, BorderLayout.CENTER);
        frame.repaint();
        gameLost = true;
        frame.setVisible(true);
    }

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
	 * this in the final submission of your game.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
