package fi.dy.esav.JavaGame;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;

public class World {
	
	static final double gravity = 10;
	
	private GameEngine engine;
	
	//Player, Enemies, Ai nodes, etc...
	
	private Player player;

	public void initialize() {
		engine = JavaGame.getEngine();
		
		initGround();
		
		Point playerSpawn = new Point(20, 80);
		player = new Player(engine);
		player.setX(playerSpawn.x); // TODO: Implement setPos(Point p) in Entity [inmylyn]
		player.setY(playerSpawn.y);
		
		engine.addEntity(player);
		
		ArrayList<Point> enemySpawns = new ArrayList<Point>();
		enemySpawns.add(new Point(500,50));
		
		for(Point p : enemySpawns) {
			EnemyEntity e = new EnemyEntity(engine);
			e.setX(p.getX());
			e.setY(p.getY());
			engine.addEntity(e);
		}
		
	}
	
	int storyHeight;
	int widthUnit;
	
	private void initGround() {
		int stageWidth = engine.getStage().getContentPane().getWidth();
		int stageHeight = engine.getStage().getContentPane().getHeight();
		
		storyHeight = stageHeight/4;
		widthUnit = stageWidth/16;
		
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
			engine.addEntity(new GroundEntity(engine, rect));
		}
	}
	
	public int getStory(Entity ent) {
		int entCenter = (int) (ent.getY() + ent.getHeight()/2);
		return (int) (3 - Math.floor(entCenter/storyHeight));
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}
