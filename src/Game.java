/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	public void run() {
		
		final JFrame frame = new JFrame("TOP LEVEL FRAME");
		frame.setLocation(0, 0);

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Running...");
		status_panel.add(status);
		
		//Side Panel
		final JPanel side_panel = new JPanel();
	    side_panel.setPreferredSize(new Dimension(200, 600));
	    frame.add(side_panel, BorderLayout.WEST);
		
	    //title
	    int index = 30;
	    final JLabel title = new JLabel(new String(UserText.TITLE_TEXT.getText()));
		title.setPreferredSize(new Dimension(200, 30));
		title.setFont(new Font("Sans Serif", Font.PLAIN, 15));
	    side_panel.add(title, BorderLayout.NORTH);
	    //
	    final JPanel p1 = new JPanel();
        p1.setPreferredSize(new Dimension(200, 600 - index));
        side_panel.add(p1, BorderLayout.SOUTH);
	    
	    //level number
	    final JLabel level = new JLabel("INSTRUCTIONS");
        level.setPreferredSize(new Dimension(200, 30));
        level.setFont(new Font("Serif", Font.PLAIN, 12));
        p1.add(level, BorderLayout.NORTH);
        index += 30;
        
        //description
        final JLabel description = new JLabel(new String(UserText.INSTRUCTIONS.getText()), SwingConstants.CENTER);
        description.setPreferredSize(new Dimension(200, 200));
        description.setFont(new Font("Sans Serif", Font.PLAIN, 12));
        p1.add(description, BorderLayout.SOUTH);
		
		// Main playing area
		final GameCourt court = new GameCourt(status);
		frame.add(court, BorderLayout.CENTER);
		frame.setBackground(Color.BLACK);

		// Reset button
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);

		// Note here that when we add an action listener to the reset
		// button, we define it as an anonymous inner class that is
		// an instance of ActionListener with its actionPerformed()
		// method overridden. When the button is pressed,
		// actionPerformed() will be called.
		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
			}
		});
		control_panel.add(reset);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		court.reset();
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
