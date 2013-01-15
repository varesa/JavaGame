package fi.dy.esav.JavaGame;

import java.awt.Graphics;
import java.util.ArrayList;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.GameEngine.enums.ENTITY;

public class AiNode extends Entity {
	
	//Disabled default constructor
	private AiNode() { super(null); }

	public AiNode(GameEngine engine) {
		super(engine);
		init();
	}

	public AiNode(GameEngine engine, int x, int y) {
		super(engine);
		
		this.x = x;
		this.y = y;
		
		init();
	}
	
	private void init() {
		this.setProperty(ENTITY.NO_ACT);
		this.setProperty(ENTITY.NO_DRAW);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawOval((int)x-5, (int)y-5, 10, 10);
	}
	
	public static ArrayList<AiNode> getNodes() {
		ArrayList<AiNode> nodes = new ArrayList<AiNode>();
		for(Entity ent: JavaGame.getEngine().getEntities()) {
			if (ent instanceof AiNode) {
				nodes.add((AiNode) ent);
			}
		}
		return nodes;
	}
	
	@Override
	public int getWidth() {
		return 1;
	}
	
	@Override
	public int getHeight() {
		return 1;
	}

}
