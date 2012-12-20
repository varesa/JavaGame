package fi.dy.esav.JavaGame;

import fi.dy.esav.GameEngine.GameEngine;

public class JavaGame {
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		GameEngine engine = new GameEngine();
		engine.getStage().setVisible(true);

		while(!engine.getStage().isValid()) continue;
		
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

		System.out.println("Closing");
		engine.getStage().dispose();
	}

}
