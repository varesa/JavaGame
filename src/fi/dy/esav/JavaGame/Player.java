package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import fi.dy.esav.GameEngine.GameEngine;

public class Player extends LivingEntity {
	
	private Color color = Color.BLUE;

	public Player(GameEngine engine) {
		super(engine);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect((int)x, (int)y, 64, 64);
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
		
		super.act();

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
