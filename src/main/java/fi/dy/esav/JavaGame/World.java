package fi.dy.esav.JavaGame;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.JavaGame.enums.AINODE;

/**
 * An instance to store the all the world objects in and manage it
 */
public class World {
	
	private GameEngine engine;
	
	private PlayerEntity player;
	
	private Score score;

	int storyHeight;
	int widthUnit;

	/**
	 * Disabled parameterless constructor
	 */
	@SuppressWarnings("unused")
	private World() { }
	
	/**
	 * Default constructor
	 * 
	 */
	public World(GameEngine engine) {
		this.engine = engine;
	}
	
	/**
	 * First time initialization of the game world
	 */
	public void initialize() {
		initGround();
		
		Point playerSpawn = new Point(20, 100);
		player = new PlayerEntity(engine);
		player.setPos(playerSpawn);
		
		engine.addEntity(player);
		
		ArrayList<Point> enemySpawns = new ArrayList<Point>();
		enemySpawns.add(new Point(500,50));
		enemySpawns.add(new Point(50,50));
		enemySpawns.add(new Point(100,500));
		enemySpawns.add(new Point(700,300));
		
		
		long initialDelay = 0;
		for(Point p : enemySpawns) {
			EntitySpawner e = new EntitySpawner(p, 20000, initialDelay, engine);
			initialDelay += 5000;
			e.setSpawnable(EnemyEntity.class.getCanonicalName());
			engine.addEntity(e);
		}
		
		Point[] jumpsUp = {new Point(75,  500),
				           new Point(390, 500),
						   new Point(705, 500),
						   
						   new Point(235, 380),
						   new Point(545, 380),
						   
						   new Point(75,  250),
				           new Point(390, 250),
						   new Point(705, 250),};
		for (Point point : jumpsUp) {
			engine.addEntity(new JumpAiNode(engine, point.x, point.y, AINODE.DIR_UP));
		}
		
		score = new Score();
		HudEntity hud = new HudEntity(engine);
		engine.addEntity(hud);
		
	}
	
	/**
	 * Initialise the stage ground and borders
	 */
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
	
	
	/**
	 * Remove everything in game
	 */
	public void clear() {
		for (Entity ent : engine.getEntities()) {
			if (ent instanceof EntitySpawner) {
				((EntitySpawner) ent).shutdown();
			}
			engine.removeEntity(ent);
		}
	}
	
	/**
	 * Make "game over"
	 */
	public void gameOver() {
		
		score.stopCounting();
		
		for (Entity ent : engine.getEntities()) {
			if (ent instanceof EntitySpawner) {
				((EntitySpawner) ent).shutdown();
			}
			engine.removeEntity(ent);
		}
		engine.addEntity(new GameoverEntity(engine));
		
		/*JavaGame.setEngine(new GameEngine());
		JavaGame.getEngine().start();

		while(!JavaGame.getEngine().getStage().isValid()) continue;
		
		JavaGame.setWorld(new World());
		JavaGame.getWorld().initialize();
		
		//engine.getStage().setVisible(false);
		engine.getStage().dispose();
		engine.stop();*/
	}
	
	/**
	 * Reinitialize the game ("restart")
	 */
	public void reinitialize() {		
		clear();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		initialize();
	}
	
	/**
	 * Get the "building story" a entity is in 
	 * @param ent The entity to get the story for
	 * @return the story the entity is in
	 */
	public int getStory(Entity ent) {
		int entCenter = (int) (ent.getY());
		return (int) (3 - Math.floor(entCenter/storyHeight));
	}

	/**
	 * Get the player
	 * @return the player
	 */
	public PlayerEntity getPlayer() {
		return player;
	}

	/**
	 * Set the player
	 * @param player the player to set
	 */
	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	/**
	 * Get the score
	 * @return the score
	 */
	public Score getScore() {
		return score;
	}

	/**
	 * Set the score
	 * @param score the score to set
	 */
	public void setScore(Score score) {
		this.score = score;
	}
}
