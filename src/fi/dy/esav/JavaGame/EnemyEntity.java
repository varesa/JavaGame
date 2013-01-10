package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;

import fi.dy.esav.GameEngine.GameEngine;

public class EnemyEntity extends LivingEntity {
	
	private Color color = Color.RED;
	
	protected final double xacc = 0.5;
	protected final double xdec = 0.5;
	protected final double maxxvel = 6;

	public EnemyEntity(GameEngine engine) {
		super(engine);
		
		super.xacc = this.xacc;
		super.xdec = this.xdec;
		super.maxxvel = this.maxxvel;
	}
	
	@Override
	public void act() {
		xtarget = JavaGame.getWorld().getPlayer().getX() - this.getX();
		System.out.println(JavaGame.world.getStory(JavaGame.getWorld().getPlayer()));
		super.act();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect((int)x, (int)y, 64, 64);
	}
}
