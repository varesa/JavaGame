package fi.dy.esav.JavaGame;

import java.awt.Graphics;
import java.awt.Point;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.JavaGame.enums.BULLET;

public class Bullet extends Entity {
	
	private double speed = 10;
	private BULLET direction = null;

	public Bullet(GameEngine engine) {
		super(engine);
	}
	
	public Bullet(Point pos, BULLET direction, GameEngine engine) {
		super(pos, engine);
		this.direction = direction;
	}
	
	public Bullet(double x, double y, BULLET direction, GameEngine engine) {
		super(x, y, engine);
		this.direction = direction;
	}
	
	@Override
	public void act() {
		if(this.direction == BULLET.DIRECTION_LEFT) {
			this.x -= this.speed;
		} else if (this.direction == BULLET.DIRECTION_RIGHT) {
			this.x += this.speed;
		} else {
			try {
				throw new Exception("Direction for bullet not specified");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawRect((int)x, (int)y, 10, 5);
	}
	
	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * @return the direction
	 */
	public BULLET getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(BULLET direction) {
		this.direction = direction;
	}

}
