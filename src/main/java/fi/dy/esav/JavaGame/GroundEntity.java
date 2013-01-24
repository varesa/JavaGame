package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.GameEngine.enums.ENTITY;

/**
 * A entity to build the stage ground from
 */

public class GroundEntity extends Entity {
	
	private int width = 10, height = 10;
	private Color color = Color.BLACK;

	/**
	 * Disabled parameterless constructor
	 */
	private GroundEntity() { super(null);	}
	
	/**
	 * Default constructor
	 * @param engine reference to the GameEngine
	 */
	public GroundEntity(GameEngine engine) {
		super(engine);
	}
	
	/**
	 * A constructor with the ground box defined
	 * @param engine reference to the GameEngine
	 * @param rect the rectangle to become the part of ground
	 */
	public GroundEntity(GameEngine engine, Rectangle rect) {
		super(engine);
		
		this.setProperty(ENTITY.NO_ACT);
		
		x = rect.x;
		y = rect.y;
		width = rect.width;
		height = rect.height;
	}
	
	/**
	 * Method to do the drawing in
	 * overrides parent class template method
	 * @param g Graphics to draw on
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
	}

	/**
	 * Get the width of the entity
	 * overrides parent class template method
	 * @return the width
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * Set the width of the entity
	 * overrides parent class template method
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * get the entity height
	 * overrides parent class template method
	 * @return the height
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * set the entity height
	 * overrides parent class template method
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
