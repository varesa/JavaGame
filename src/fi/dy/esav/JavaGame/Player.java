package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;

public class Player extends Entity {
	
	private int width = 64;
	private int height = 64;
	
	private final double xacc = 1;
	private final double xdec = 2;

	private final double jumpvel = 10;
	private final double ydec = 0.3;
	
	private final double maxxvel = 7;
	private final double maxyvel = 10;
	
	private double xvel = 0;
	private double yvel = 0;

	public Player(GameEngine engine) {
		super(engine);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, 64, 64);
	}
	
	@Override
	public void act() {
		if(this.engine.getInputState().isKeyDown(KeyEvent.VK_LEFT)) {
			xvel -= xacc;
			if (xvel < -maxxvel) {
				xvel = -maxxvel;
			}
		} else if (this.engine.getInputState().isKeyDown(KeyEvent.VK_RIGHT)){
			xvel += xacc;
			if (xvel > maxxvel) {
				xvel =  maxxvel;
			}
		} else {
			double sign1 = Math.signum(xvel);
			xvel -= xdec * Math.signum(xvel);
			if(sign1 != Math.signum(xvel)) {
				xvel = 0;
			}
		}
		
		x += xvel;
		
		for(Entity ent : JavaGame.getEngine().getEntities()) {
			if(this.getY()+this.getHeight() > ent.getY() && this.getY() < ent.getY()+ent.getHeight()) {
				if( this.getX()+this.getWidth() > ent.getX() && this.getX()+this.getWidth() < ent.getX()+ent.getWidth()) {
					this.xvel = 0;
					this.x = ent.getX()-this.getWidth();
					System.out.println("Hit right");
				}
				if( this.getX()-0.1 > ent.getX() && this.getX()-0.1 < ent.getX()+ent.getWidth()) {
					this.xvel = 0;
					this.x = ent.getX() + ent.getWidth();
					System.out.println("Hit left");
				}
			}
		}
		
		boolean collided = false;
		boolean hitHead = false;
		
		y -= yvel;
		
		for(Entity ent : JavaGame.getEngine().getEntities()) {

			if(this.getX()+this.getWidth() > ent.getX() && this.getX() < ent.getX()+ent.getWidth()) {
				if( this.getY()+this.getHeight() > ent.getY() && this.getY()+this.getHeight() < ent.getY()+ent.getHeight()) {
					yvel = 0;
					y = ent.getY()-this.getHeight();
					collided = true;
					System.out.println("Hit floor");
				}
				if( this.getY()-0.1 > ent.getY() && this.getY()-0.1 < ent.getY()+ent.getHeight()) {
					yvel = 0;
					y = ent.getY() + ent.getHeight()+0.1;
					hitHead = true;
					System.out.println("Hit head");
				}
			}

			
		}
		if(!collided) {
			yvel -= ydec;
			if(Math.abs(yvel) > maxyvel) {
				yvel = Math.signum(yvel) * maxyvel;
			}
		}
		
		if(this.engine.getInputState().isKeyDown(KeyEvent.VK_UP)) {
			if(collided) {
				yvel = jumpvel;
			}
		}
		

	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}

}
