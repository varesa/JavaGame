package fi.dy.esav.JavaGame;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;

/**
 * This is a tool for debugging / map creation
 * It prints out the mouse position to the console every ten frames
 */

public class MouseTrackerEntity extends Entity {

	/**
	 * Default constructor
	 * @param engine reference to the GameEngine
	 */
	public MouseTrackerEntity(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	
	int a = 0;
	
	/**
	 * Method to do the logic
	 * Overrides parent class template method
	 */
	@Override
	public void act() {
		if(a == 10) { 
			System.out.println("X: " + engine.getInputState().getMouseX() +
					           ", Y: " + engine.getInputState().getMouseY());
			a = 0;
		} else {
			a++;
		}
	}

}
