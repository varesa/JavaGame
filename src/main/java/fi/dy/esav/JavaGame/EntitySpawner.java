package fi.dy.esav.JavaGame;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;

import fi.dy.esav.GameEngine.Entity;
import fi.dy.esav.GameEngine.GameEngine;

public class EntitySpawner extends Entity {

	private long interval = 9000;
	private long intervalDelta = 50;
	private long initialDelay = 500;

	private String spawnable;

	public EntitySpawner(GameEngine engine) {
		super(engine);
		init();
	}

	public EntitySpawner(Point position, GameEngine engine) {
		super(position, engine);
		init();
	}

	public EntitySpawner(double x, double y, GameEngine engine) {
		super(x, y, engine);
		init();
	}

	public EntitySpawner(Point position, long interval, GameEngine engine) {
		super(position, engine);
		this.interval = interval;
		init();
	}

	public EntitySpawner(double x, double y, long interval, GameEngine engine) {
		super(x, y, engine);
		this.interval = interval;
		init();
	}
	
	public EntitySpawner(Point position, long interval, long initialDelay, GameEngine engine) {
		super(position, engine);
		this.interval = interval;
		this.initialDelay = initialDelay;
		init();
	}

	public EntitySpawner(double x, double y, long interval, long initialDelay, GameEngine engine) {
		super(x, y, engine);
		this.interval = interval;
		this.initialDelay = initialDelay;
		init();
	}

	private Thread spawnerThread = new Thread(new SpawnerThread(this, engine));

	private void init() {
		spawnerThread.start();
	}

	/**
	 * @return the interval
	 */
	public long getInterval() {
		return interval;
	}

	/**
	 * @param interval
	 *            the interval to set
	 */
	public void setInterval(long interval) {
		this.interval = interval;
	}

	/**
	 * @return the intervalDelta
	 */
	public long getIntervalDelta() {
		return intervalDelta;
	}

	/**
	 * @param intervalDelta
	 *            the intervalDelta to set
	 */
	public void setIntervalDelta(long intervalDelta) {
		this.intervalDelta = intervalDelta;
	}

	/**
	 * @return the initialDelay
	 */
	public long getInitialDelay() {
		return initialDelay;
	}

	/**
	 * @param initialDelay the initialDelay to set
	 */
	public void setInitialDelay(long initialDelay) {
		this.initialDelay = initialDelay;
	}

	/**
	 * @return the spawnable
	 */
	public String getSpawnable() {
		return spawnable;
	}

	/**
	 * @param spawnable the spawnable to set as FQN!
	 */
	public void setSpawnable(String spawnable) {
		this.spawnable = spawnable;
	}
}

class SpawnerThread implements Runnable {

	private boolean run = true;

	private EntitySpawner main = null;
	private GameEngine engine = null;

	public SpawnerThread(EntitySpawner main, GameEngine game) {
		this.main = main;
		this.engine = game;
	}

	public void run() {
		try {
			Thread.sleep(main.getInitialDelay());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while (run) {
			spawn();
			try {
				Thread.sleep(main.getInterval());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//main.setInterval(main.getInterval() - main.getIntervalDelta());

		}

	}

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

	public void stop() {
		run = false;
	}
}
