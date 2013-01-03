package fi.dy.esav.JavaGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;

public class TestEntity extends Entity {
	
	Image sprite;
	
	public TestEntity(GameEngine engine) {
		super(engine);
		
		sprite = Toolkit.getDefaultToolkit().getImage("../res/test.png");
	}
	
	@Override
	public void draw(Graphics g) {
		System.out.println("drawing");
		g.drawImage(sprite, 50, 50, engine.getStage());
	}
}
