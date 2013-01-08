package fi.dy.esav.JavaGame;

import java.awt.Rectangle;

public class World {
	//Player, Enemies, Ai nodes, etc...

	public void initialize() {
		int stageWidth = JavaGame.getEngine().getStage().getContentPane().getWidth();
		int stageHeight = JavaGame.getEngine().getStage().getContentPane().getHeight();
		
		int storyHeight = stageHeight/4;
		int widthUnit = stageWidth/16;
		
		Rectangle[] ground = {  new Rectangle(0, 				0, 				10, 		stageHeight), 
								new Rectangle(0, 				0, 				stageWidth, 10),
								new Rectangle(0, 				stageHeight-10, stageWidth, 10),
								new Rectangle(stageWidth-10, 	0, 				10, 		stageHeight),
								
								new Rectangle(3*widthUnit,				storyHeight,	4*widthUnit,	10),
								new Rectangle(stageWidth-7*widthUnit,	storyHeight,	4*widthUnit,	10),
								
								new Rectangle(0, 						storyHeight*2, 4*widthUnit, 10),
								new Rectangle(6*widthUnit, 				storyHeight*2, 4*widthUnit, 10),
								new Rectangle(stageWidth-4*widthUnit, 	storyHeight*2, 4*widthUnit, 10),
								
								new Rectangle(3*widthUnit,				storyHeight*3,	4*widthUnit,	10),
								new Rectangle(stageWidth-7*widthUnit,	storyHeight*3,	4*widthUnit,	10),
								};
		
		for (Rectangle rect : ground) {
			JavaGame.getEngine().addEntity(new GroundEntity(JavaGame.getEngine(), rect));
		}
	}
}
