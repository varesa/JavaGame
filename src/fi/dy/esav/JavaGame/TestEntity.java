package fi.dy.esav.JavaGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;

public class TestEntity extends Entity {
	
	Image sprite;
	
	private double x = 0;
	private double y = 500;
	
	public TestEntity(GameEngine engine) {
		super(engine);
		
		sprite = Toolkit.getDefaultToolkit().getImage("res/test.png");
		System.out.println(sprite.getHeight(engine.getStage()));
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(sprite, (int) x, (int) y, sprite.getWidth(engine.getStage()), sprite.getHeight(engine.getStage()), engine.getStage());
	}
	
	@Override
	public void act() {
		this.x += 0.5;
		this.y -= 0.9;
	}
}
