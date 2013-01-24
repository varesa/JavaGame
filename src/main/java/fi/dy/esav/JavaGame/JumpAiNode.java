package fi.dy.esav.JavaGame;

import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.JavaGame.enums.AINODE;;

/**
 * Class to build the node based jump-navigation 
 */

public class JumpAiNode extends AiNode {

	private AINODE direction;
	
	/**
	 * Disabled parameterless constructor
	 */
	private JumpAiNode() { super(null);	}
	
	/**
	 * The default constructor
	 * @param engine reference to the GameEngine
	 */
	public JumpAiNode(GameEngine engine) {
		super(engine);
	}

	
	/**
	 * Constructor with the location and direction defined
	 * @param engine reference to the GameEngine
	 * @param x the x-coordinate of the node to be created
	 * @param y the y-coordinate of the node to be created
	 * @param direction direction of the node to be created
	 */
	public JumpAiNode(GameEngine engine, int x, int y, AINODE direction) {
		super(engine, x, y);
		
		if(!(direction == AINODE.DIR_DOWN || direction == AINODE.DIR_UP)) {
			throw new IllegalArgumentException("Direction parameter is invalid");
		}
		this.direction = direction;
	}
	
	/**
	 * Get the direction of the node
	 * @return the direction
	 */
	public AINODE getDirection() {
		return direction;
	}
	
	/**
	 * Set the direction of the node
	 * @param direction new direction
	 */
	public void setDirection(AINODE direction) {
		this.direction = direction;
	}

}
