package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.GameEngine.InputState;

/**
 * A entity to represent the gameover screen
 */

public class GameoverEntity extends Entity {

	/**
	 * Disabled parameterless constructor
	 */
	private GameoverEntity() { super(null);	}
	
	/**
	 * Default constructor
	 * @param engine reference to the GameEngine
	 */
	public GameoverEntity(GameEngine engine) {
		super(engine);
	}

	String maintext = "Your game is over...";
	String othertext = "Press ENTER to continue, or ESC to quit";

	/**
	 * Method to do the drawing in
	 * overrides parent class template method
	 * @param g Graphics instance to do the drawing on
	 */
	@Override
	public void draw(Graphics g) {
		Font font = new Font("Arial", Font.ITALIC, 50);
		g.setFont(font);
		g.setColor(Color.RED);
		FontMetrics fm = g.getFontMetrics(font);
		g.drawString(maintext, engine.getStage().getContentPane().getWidth()
				/ 2 - fm.stringWidth(maintext) / 2, 200);
		
		SimpleDateFormat format = new SimpleDateFormat("mm:ss");
		String time = format.format(new Date(JavaGame.getWorld().getScore().getTime()));
		
		String score = "Your score was: " + JavaGame.getWorld().getScore().getKills() + " in " + time; 
		Font font3 = new Font("Arial", Font.ITALIC, 35);
		g.setFont(font3);
		g.setColor(Color.RED);
		FontMetrics fm3 = g.getFontMetrics(font3);
		g.drawString(score, engine.getStage().getContentPane().getWidth()
				/ 2 - fm3.stringWidth(score) / 2, 300);
		
		Font font2 = new Font("Arial", Font.ITALIC, 35);
		g.setFont(font2);
		g.setColor(Color.RED);
		FontMetrics fm2 = g.getFontMetrics(font2);
		g.drawString(othertext, engine.getStage().getContentPane().getWidth()
				/ 2 - fm2.stringWidth(othertext) / 2, 400);
	}

	/**
	 * Method to do the logic in
	 * overrides parent class template method
	 */
	@Override
	public void act() {
		InputState input = engine.getInputState();
		if(input.isKeyDown(KeyEvent.VK_ENTER)) {
			JavaGame.getWorld().reinitialize();
		} else if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			JavaGame.close();
		}
	}

}
