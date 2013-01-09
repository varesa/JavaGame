package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;

import fi.dy.esav.GameEngine.GameEngine;

public class EnemyEntity extends LivingEntity {
	
	private Color color = Color.RED;
	
	protected final double xacc = 0.1;
	protected final double xdec = 0.1;
	protected final double maxxvel = 6;

	public EnemyEntity(GameEngine engine) {
		super(engine);
		
		super.xacc = this.xacc;
		super.xdec = this.xdec;
		super.maxxvel = this.maxxvel;
	}
	
	@Override
	public void act() {
		xtarget = JavaGame.world.player.getX() - this.getX();
		System.out.println(JavaGame.world.getStory(JavaGame.world.player)); // TODO: Implement JavaGame.getWorld(), and .getPlayer()
		super.act();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect((int)x, (int)y, 64, 64);
	}
}
