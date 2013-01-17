package fi.dy.esav.JavaGame;

import fi.dy.esav.GameEngine.GameEngine;

public class JavaGame {
	
	private static GameEngine engine;
	static World world;
	
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		engine = new GameEngine();
		engine.start();

		while(!engine.getStage().isValid()) continue;
		
		world = new World();
		world.initialize();
		
		//engine.addEntity(new MouseTrackerEntity(engine));
		
		while(engine.getStage().isVisible()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		engine.stop();
		engine.getStage().dispose();
	}

	/**
	 * @return the engine
	 */
	public static GameEngine getEngine() {
		return JavaGame.engine;
	}

	/**
	 * @param engine the engine to set
	 */
	public static void setEngine(GameEngine engine) {
		JavaGame.engine = engine;
	}

	/**
	 * @return the world
	 */
	public static World getWorld() {
		return world;
	}

	/**
	 * @param world the world to set
	 */
	public static void setWorld(World world) {
		JavaGame.world = world;
	}
	
	

}
