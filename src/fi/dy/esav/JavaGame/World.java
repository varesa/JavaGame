package fi.dy.esav.JavaGame;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class World {
	
	static final double gravity = 10;
	//Player, Enemies, Ai nodes, etc...
	
	Player player;

	public void initialize() {
		initGround();
		
		Point playerSpawn = new Point(20, 80);
		player = new Player(JavaGame.getEngine());
		player.setX(playerSpawn.x); // TODO: Implement setPos(Point p) in Entity
		player.setY(playerSpawn.y);
		
		JavaGame.getEngine().addEntity(player);
		
		ArrayList<Point> enemySpawns = new ArrayList<Point>();
		enemySpawns.add(new Point(500,50));
		
		for(Point p : enemySpawns) {
			EnemyEntity e = new EnemyEntity(JavaGame.getEngine()); // TODO: Reduce amount of JavaGame.getEngine()'s with a variable
			e.setX(p.getX());
			e.setY(p.getY());
			JavaGame.getEngine().addEntity(e);
		}
		
	}
	
	private void initGround() {
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
