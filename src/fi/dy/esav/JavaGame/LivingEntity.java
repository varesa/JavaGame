package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;

public class LivingEntity extends Entity {

	protected int width = 64;
	protected int height = 64;
	
	protected final double xacc = 1;
	protected final double xdec = 2;

	protected final double jumpvel = 11;
	protected final double ydec = 0.3;
	
	protected final double maxxvel = 7;
	protected final double maxyvel = 12;
	
	protected double xvel = 0;
	protected double yvel = 0;
	
	protected double xtarget = 0;
	protected boolean jump = false;
	
	public LivingEntity(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, 64, 64);
	}
	
	@Override
	public void act() {
		if(xtarget < 0) {
			xvel -= xacc;
			if (xvel < -maxxvel) {
				xvel = -maxxvel;
			}
		} else if (xtarget > 0){
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
		
		if(jump) {
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
