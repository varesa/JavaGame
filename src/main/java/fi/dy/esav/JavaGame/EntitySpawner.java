package fi.dy.esav.JavaGame;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;

/**
 * A Entity to spawn other entities
 */

public class EntitySpawner extends Entity {

	private long interval = 9000;
	private long intervalDelta = 50;
	private long initialDelay = 500;

	private String spawnable;

	/**
	 * Disabled parameterless constructor
	 */
	private EntitySpawner() { super(null);	}
	
	/**
	 * Default constructor
	 * @param engine reference to the GameEngine
	 */
	public EntitySpawner(GameEngine engine) {
		super(engine);
		init();
	}

	/**
	 * Constructor with the position set
	 * @param position location for the spawner
	 * @param engine reference to the GameEngine
	 */
	public EntitySpawner(Point position, GameEngine engine) {
		super(position, engine);
		init();
	}

	/**
	 * Constructor with the position set
	 * @param x X-coordinate of the spawner
	 * @param y Y-coordinate of the spawner
	 * @param engine reference to the GameEngine
	 */
	public EntitySpawner(double x, double y, GameEngine engine) {
		super(x, y, engine);
		init();
	}

	/**
	 * Constructor with the position set
	 * @param position location for the spawner
	 * @param interval spawning interval
	 * @param engine reference to the GameEngine
	 */
	public EntitySpawner(Point position, long interval, GameEngine engine) {
		super(position, engine);
		this.interval = interval;
		init();
	}

	/**
	 * Constructor with the position set
	 * @param x X-coordinate of the spawner
	 * @param y Y-coordinate of the spawner
	 * @param interval spawning interval
	 * @param engine reference to the GameEngine
	 */
	public EntitySpawner(double x, double y, long interval, GameEngine engine) {
		super(x, y, engine);
		this.interval = interval;
		init();
	}

	/**
	 * Constructor with the position set
	 * @param position location for the spawner
	 * @param interval spawning interval
	 * @param the initial delay before a spawn
	 * @param engine reference to the GameEngine
	 */
	public EntitySpawner(Point position, long interval, long initialDelay,
			GameEngine engine) {
		super(position, engine);
		this.interval = interval;
		this.initialDelay = initialDelay;
		init();
	}

	/**
	 * Constructor with the position set
	 * @param x X-coordinate of the spawner
	 * @param y Y-coordinate of the spawner
	 * @param interval spawning interval
	 * @param intial delay before a spawn
	 * @param engine reference to the GameEngine
	 */	
	public EntitySpawner(double x, double y, long interval, long initialDelay,
			GameEngine engine) {
		super(x, y, engine);
		this.interval = interval;
		this.initialDelay = initialDelay;
		init();
	}

	private SpawnerThread spawner = new SpawnerThread(this, engine);
	private Thread spawnerThread = new Thread(spawner);

	/**
	 * Start spawning
	 */
	private void init() {
		spawnerThread.start();
	}

	/**
	 * Stop spawning
	 */
	public void shutdown() {
		spawner.shutdown();
		spawnerThread.interrupt();
	}

	/**
	 * get the spawning interval
	 * @return the interval
	 */
	public long getInterval() {
		return interval;
	}

	/**
	 * set new interval
	 * @param interval the interval to set
	 */
	public void setInterval(long interval) {
		this.interval = interval;
	}

	/**
	 * get the interval delta
	 * @return the intervalDelta
	 */
	public long getIntervalDelta() {
		return intervalDelta;
	}

	/**
	 * set new interval delta
	 * @param intervalDelta the intervalDelta to set
	 */
	public void setIntervalDelta(long intervalDelta) {
		this.intervalDelta = intervalDelta;
	}

	/**
	 * get the initial delay
	 * @return initial Delay
	 */
	public long getInitialDelay() {
		return initialDelay;
	}

	/**
	 * set new initial delay
	 * @param initialDelay the initialDelay to set
	 */
	public void setInitialDelay(long initialDelay) {
		this.initialDelay = initialDelay;
	}

	/**
	 * get the entity to be spawned
	 * @return the spawnable
	 */
	public String getSpawnable() {
		return spawnable;
	}

	/**
	 * set new entity to be spawned
	 * @param spawnable the spawnable to set as FQN!
	 */
	public void setSpawnable(String spawnable) {
		this.spawnable = spawnable;
	}
}


/**
 * private thread to do the spawning
 */
class SpawnerThread implements Runnable {

	private boolean run = true;

	private EntitySpawner main = null;
	private GameEngine engine = null;

	/**
	 * Disabled parameterless constructor
	 */
	@SuppressWarnings("unused")
	private SpawnerThread() { }
	
	/**
	 * Default constructor
	 * @param main reference to the spawner instance controlling this thread
	 * @param game instance to the GameEngine
	 */
	public SpawnerThread(EntitySpawner main, GameEngine game) {
		this.main = main;
		this.engine = game;
	}

	/**
	 * Thread run method
	 */
	public void run() {
		try {
			Thread.sleep(main.getInitialDelay());
		} catch (InterruptedException e1) {
			// Interrupted the thread, allow it to end
		}

		while (run) {
			spawn();
			try {
				Thread.sleep(main.getInterval());
			} catch (InterruptedException e) {
				// Interrupted the thread, allow it to end
			}
			main.setInterval(main.getInterval() - main.getIntervalDelta());
			if(main.getInterval() < 500) {
				main.setInterval(500);
				main.setIntervalDelta(0);
			}
		}
	}

	/**
	 * method to spawn an entity
	 */
	private void spawn() {
		try {
			Entity ent = (Entity) Class.forName(main.getSpawnable())
					.getConstructor(GameEngine.class)
					.newInstance(new Object[] { engine });
			ent.setPos(main.getPos());
			engine.addEntity(ent);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to shutdown the thread
	 */
	public void shutdown() {
		run = false;
	}
}
