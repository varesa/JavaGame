package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.JavaGame.enums.DIRECTION;

/**
 * A base class for every moving/living "creature"
 */

public class LivingEntity extends Entity {

	protected int width = 64;
	protected int height = 64;
	
	protected Color color = Color.GRAY;
	
	protected double xacc = 1.5;
	protected double xdec = 0.75;

	protected double jumpvel = 12;
	protected double ydec = 0.33;
	
	protected double maxxvel = 7;
	protected double maxyvel = 12;
	
	protected double xvel = 0;
	protected double yvel = 0;
	
	protected double xtarget = 0;
	protected boolean jump = false;
	
	protected DIRECTION facing = DIRECTION.RIGHT;
	
	/**
	 * Disabled parameterless constructor
	 */
	private LivingEntity() { super(null);	}
	
	/**
	 * Default constructor
	 * @param engine reference to the GameEngine
	 */
	public LivingEntity(GameEngine engine) {
		super(engine);
	}
	
	/**
	 * Method to do the drawing in
	 * overrides parent class template method
	 * @param g Graphics instance to draw on
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect((int)x, (int)y, 64, 64);
	}
	
	
	/**
	 * Method to do the logic
	 * overrides parent class template method
	 */
	@Override
	public void act() {
		@SuppressWarnings("unchecked")
		ArrayList<Entity> entities = (ArrayList<Entity>) engine.getEntities().clone();
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
		if(xtarget < 0) {
			facing = DIRECTION.LEFT;
		} else if(xtarget > 0){
			facing = DIRECTION.RIGHT;
		}
		x += xvel;
		
		for(Entity ent : entities) {
			if(!(ent instanceof GroundEntity)) break;
			if(this.getY()+this.getHeight() > ent.getY() && this.getY() < ent.getY()+ent.getHeight()) {
				if( this.getX()+this.getWidth() > ent.getX() && this.getX()+this.getWidth() < ent.getX()+ent.getWidth()) {
					this.xvel = 0;
					this.x = ent.getX()-this.getWidth();
				}
				if( this.getX()-0.1 > ent.getX() && this.getX()-0.1 < ent.getX()+ent.getWidth()) {
					this.xvel = 0;
					this.x = ent.getX() + ent.getWidth();
				}
			}
		}
		
		boolean collided = false;
		
		y -= yvel;
		
		for(Entity ent : entities) {
			if(!(ent instanceof GroundEntity)) break;
			if(this.getX()+this.getWidth() > ent.getX() && this.getX() < ent.getX()+ent.getWidth()) {
				if( this.getY()+this.getHeight() > ent.getY() && this.getY()+this.getHeight() < ent.getY()+ent.getHeight()) {
					yvel = 0;
					y = ent.getY()-this.getHeight();
					collided = true;
				}
				if( this.getY()-0.1 > ent.getY() && this.getY()-0.1 < ent.getY()+ent.getHeight()) {
					yvel = 0;
					y = ent.getY() + ent.getHeight()+0.1;
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
			jump = false;
		}
		

	}
	
	/**
	 * Method to return the width
	 * overrides parent class template method
	 * @return the width of the entity
	 */
	@Override
	public int getWidth() {
		return width;
	}
	
	/**
	 * Method to return the height
	 * overrides parent class template method
	 * @return the height of the entity
	 */
	@Override
	public int getHeight() {
		return height;
	}

}
