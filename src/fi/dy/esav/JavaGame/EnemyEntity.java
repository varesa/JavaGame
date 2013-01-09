package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;

import fi.dy.esav.GameEngine.GameEngine;

public class EnemyEntity extends LivingEntity {
	
	private Color color = Color.RED;

	public EnemyEntity(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void act() {
		xtarget = JavaGame.world.player.getX() - this.getX();
		super.act();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect((int)x, (int)y, 64, 64);
	}
}
