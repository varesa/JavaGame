package fi.dy.esav.JavaGame;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;

/**
 * Main class for JavaGame
 */

public class JavaGame {
	
	private static GameEngine engine;
	private static World world;
	
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		engine = new GameEngine();
		engine.start();

		engine.getStage().setTitle("The Hard-O-Game");
		
		while(!engine.getStage().isValid()) continue;
		
		world = new World();
		world.initialize();
		
		//engine.addEntity(new MouseTrackerEntity(engine));
		
		while(engine.getStage().isVisible()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (Entity ent : engine.getEntities()) {
			if (ent instanceof EntitySpawner) {
				((EntitySpawner) ent).shutdown();
			}
		}
		
		engine.stop();
		engine.getStage().dispose();
	}

	/**
	 * A method to shutdown and close the game
	 */
	public static void close() {
		world.clear();
		engine.stop();
		engine.getStage().dispose();
	}
	
	/**
	 * get reference to the engine
	 * @return the engine
	 */
	public static GameEngine getEngine() {
		return JavaGame.engine;
	}

	/**
	 * set new engine
	 * @param engine the engine to set
	 */
	public static void setEngine(GameEngine engine) {
		JavaGame.engine = engine;
	}

	/**
	 * get the world
	 * @return the world
	 */
	public static World getWorld() {
		return world;
	}

	/**
	 * set new world
	 * @param world the world to set
	 */
	public static void setWorld(World world) {
		JavaGame.world = world;
	}
}
