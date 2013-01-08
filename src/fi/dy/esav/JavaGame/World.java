package fi.dy.esav.JavaGame;

import java.awt.Rectangle;

public class World {
	//Player, Enemies, Ai nodes, etc...

	public void initialize() {
		int stageWidth = JavaGame.getEngine().getStage().getContentPane().getWidth();
		int stageHeight = JavaGame.getEngine().getStage().getContentPane().getHeight();
		
		Rectangle[] ground = {  new Rectangle(0, 0, 10, stageHeight), 
								new Rectangle(0, 0, stageWidth, 10),
								new Rectangle(0, stageHeight-10, stageWidth, 10),
								new Rectangle(stageWidth-10, 0, 10, stageHeight),
								
								
								};
		
		for (Rectangle rect : ground) {
			JavaGame.getEngine().addEntity(new GroundEntity(JavaGame.getEngine(), rect));
		}
	}
}
