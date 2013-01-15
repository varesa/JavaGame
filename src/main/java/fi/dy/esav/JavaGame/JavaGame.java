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
		//engine.getStage().setVisible(true);

		while(!engine.getStage().isValid()) continue;
		
		//engine.addEntity(new TestEntity(engine));
		world = new World();
		world.initialize();
		
		//engine.addEntity(new MouseTrackerEntity(engine));
		
		while(engine.getStage().isVisible()) {
			//System.out.println(engine.getInputstate().getMouseX() + ", " + engine.getInputstate().getMouseY());
			//System.out.println(engine.getInputstate().getKeyboardState().toString());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		engine.stop();

		System.out.println("Closing");
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
	public void setEngine(GameEngine engine) {
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
