package fi.dy.esav.JavaGame;

import fi.dy.esav.GameEngine.GameEngine;

public class JavaGame {
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		GameEngine engine = new GameEngine();
		engine.getStage().setVisible(true);
	}

}
