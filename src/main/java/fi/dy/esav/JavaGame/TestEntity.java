package fi.dy.esav.JavaGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;

/**
 * This is a tool for debugging / engine testing 
 */

public class TestEntity extends Entity {
	
	Image sprite;
	
	private double x = 0;
	private double y = 500;
	
	/**
	 * Disabled parameterless constructor
	 */
	private TestEntity() { super(null);	}
	
	/**
	 * Default constructor
	 * @param engine Reference to the engine instance
	 */
	public TestEntity(GameEngine engine) {
		super(engine);
		
		sprite = Toolkit.getDefaultToolkit().getImage("res/test.png");
		System.out.println(sprite.getHeight(engine.getStage()));
	}
	
	/**
	 * Method to do the drawing in
	 * Overrides the parent class template method
	 * @param g Graphics instance to draw on
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(sprite, (int) x, (int) y, sprite.getWidth(engine.getStage()), sprite.getHeight(engine.getStage()), engine.getStage());
	}
	
	/**
	 * Method to do logic in
	 * Overrides the parent class template method
	 */
	@Override
	public void act() {
		if(this.engine.getInputState().isKeyDown(KeyEvent.VK_LEFT)) {
			this.x -= 1;
		} else if (this.engine.getInputState().isKeyDown(KeyEvent.VK_RIGHT)){
			this.x += 1;
		}
		
		if(this.engine.getInputState().isKeyDown(KeyEvent.VK_UP)) {
			this.y -= 1;
		} else if(this.engine.getInputState().isKeyDown(KeyEvent.VK_DOWN)) {
			this.y += 1;
		}
	}
}
