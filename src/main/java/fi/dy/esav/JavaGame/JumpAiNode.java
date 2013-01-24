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
	
	public JumpAiNode(GameEngine engine) {
		super(engine);
	}

	public JumpAiNode(GameEngine engine, int x, int y, AINODE direction) {
		super(engine, x, y);
		
		if(!(direction == AINODE.DIR_DOWN || direction == AINODE.DIR_UP)) {
			throw new IllegalArgumentException("Direction parameter is invalid");
		}
		this.direction = direction;
	}
	
	public AINODE getDirection() {
		return direction;
	}
	
	public void setDirection(AINODE direction) {
		this.direction = direction;
	}

}
