package fi.dy.esav.JavaGame;

import java.awt.Font;
import java.awt.Graphics;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.GameEngine.enums.ENTITY;

public class HudEntity extends Entity {

	public HudEntity(GameEngine engine) {
		super(engine);
		this.setProperty(ENTITY.NO_ACT);
	}
	
	@Override
	public void draw(Graphics g) {
		String score = String.valueOf(JavaGame.getWorld().getScore().getKills());
		Font font = new Font("Arial", Font.PLAIN, 20);
		g.setFont(font);
		g.drawString(score, 20, engine.getStage().getContentPane().getHeight()-20);
	}
	
	

}
