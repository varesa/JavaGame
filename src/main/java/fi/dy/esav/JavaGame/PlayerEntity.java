package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.JavaGame.enums.DIRECTION;

public class PlayerEntity extends LivingEntity {
	
	private Color color = Color.BLUE;

	public PlayerEntity(GameEngine engine) {
		super(engine);
		
		height = 48;
		width = 48;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect((int)x, (int)y, width, height);
	}
	
	@Override
	public void act() {
		if(this.engine.getInputState().isKeyDown(KeyEvent.VK_LEFT)) {
			this.xtarget = -1;
		} else if (this.engine.getInputState().isKeyDown(KeyEvent.VK_RIGHT)){
			this.xtarget = +1;
		} else {
			this.xtarget = 0;
		}
		
		if(this.engine.getInputState().isKeyDown(KeyEvent.VK_UP)) {
			this.jump = true;
		} else {
			this.jump = false;
		}
		
		if(this.engine.getInputState().isKeyDown(KeyEvent.VK_SPACE)) {
			if(facing == DIRECTION.LEFT) {
				this.engine.addEntity(new Bullet(this.getPos(), DIRECTION.LEFT, this.engine));
			} else if(facing == DIRECTION.RIGHT) {

				this.engine.addEntity(new Bullet(this.getPos(), DIRECTION.RIGHT, this.engine));
			}
		}
		
		super.act();

	}
}
