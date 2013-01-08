package fi.dy.esav.JavaGame;

import fi.dy.esav.GameEngine.GameEngine;

public class JavaGame {
	
	private static GameEngine engine;
	
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		engine = new GameEngine();
		engine.start();
		//engine.getStage().setVisible(true);

		while(!engine.getStage().isValid()) continue;
		
		engine.addEntity(new TestEntity(engine));
		
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
	public GameEngine getEngine() {
		return engine;
	}

	/**
	 * @param engine the engine to set
	 */
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}

}
