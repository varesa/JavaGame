package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.GameEngine.InputState;
import fi.dy.esav.GameEngine.enums.ENTITY;

public class GameoverEntity extends Entity {

	public GameoverEntity(GameEngine engine) {
		super(engine);
	}

	String maintext = "Your game is over...";
	String othertext = "Press ENTER to continue, or ESC to quit";

	@Override
	public void draw(Graphics g) {
		Font font = new Font("Arial", Font.ITALIC, 50);
		g.setFont(font);
		g.setColor(Color.RED);
		FontMetrics fm = g.getFontMetrics(font);
		g.drawString(maintext, engine.getStage().getContentPane().getWidth()
				/ 2 - fm.stringWidth(maintext) / 2, 200);
		
		Font font2 = new Font("Arial", Font.ITALIC, 35);
		g.setFont(font2);
		g.setColor(Color.RED);
		FontMetrics fm2 = g.getFontMetrics(font2);
		g.drawString(othertext, engine.getStage().getContentPane().getWidth()
				/ 2 - fm2.stringWidth(othertext) / 2, 400);
	}

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
