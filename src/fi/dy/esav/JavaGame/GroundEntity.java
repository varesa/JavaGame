package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;

public class GroundEntity extends Entity {
	
	private int width = 10, height = 10;
	private Color color = Color.BLACK;

	public GroundEntity(GameEngine engine) {
		super(engine);
	}
	
	public GroundEntity(GameEngine engine, Rectangle rect) {
		super(engine);
		x = rect.x;
		y = rect.y;
		width = rect.width;
		height = rect.height;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
	}

	/**
	 * @return the width
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
