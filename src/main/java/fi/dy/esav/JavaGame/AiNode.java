package fi.dy.esav.JavaGame;

import java.awt.Graphics;
import java.util.ArrayList;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.GameEngine.enums.ENTITY;

/**
 * Baseclass for building the node based AI
 */

public class AiNode extends Entity {
	
	/**
	 * Disabled parameterless constructor
	 */
	private AiNode() {super(null); }
	
	/**
	 * Default constructor
	 * @param engine reference to the engine
	 */
	public AiNode(GameEngine engine) {
		super(engine);
		init();
	}

	/**
	 * Constructor with the location set
	 * @param engine reference to the GameEngine
	 * @param x X-coordinate for the new location
	 * @param y Y-coordinate for the new location
	 */
	public AiNode(GameEngine engine, int x, int y) {
		super(engine);
		
		this.x = x;
		this.y = y;
		
		init();
	}
	
	/**
	 * Common initializations for all the constructors
	 */
	private void init() {
		this.setProperty(ENTITY.NO_ACT);
		this.setProperty(ENTITY.NO_DRAW);
	}
	
	/**
	 * Method to do the drawing
	 * overrides parent class template method
	 * @param g Graphics instance to do the drawing on
	 */
	@Override
	public void draw(Graphics g) {
		g.drawOval((int)x-5, (int)y-5, 10, 10);
	}
	
	/**
	 * get All the ainodes registered to the engine
	 * @return ArrayList of the nodes
	 */
	public static ArrayList<AiNode> getNodes() {
		ArrayList<AiNode> nodes = new ArrayList<AiNode>();
		for(Entity ent: JavaGame.getEngine().getEntities()) {
			if (ent instanceof AiNode) {
				nodes.add((AiNode) ent);
			}
		}
		return nodes;
	}
	
	/**
	 * Get the width of the entity
	 * override parent class template entity
	 * @return entity width
	 */
	@Override
	public int getWidth() {
		return 1;
	}
	
	/**
	 * Get the height of the entity
	 * override parent class template entity
	 * @return entity height
	 */
	@Override
	public int getHeight() {
		return 1;
	}

}
