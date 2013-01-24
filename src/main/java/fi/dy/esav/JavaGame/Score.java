package fi.dy.esav.JavaGame;


/**
 * Class to keep score and spent time in
 */
public class Score {
	private int kills = 0;
	
	private long startTime;
	private boolean running = true;
	private long stopTime;
	
	/**
	 * Default constructor
	 */
	public Score() {
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * Increase the kills
	 */
	public void increase() {
		kills++;
	}
	
	/**
	 * Get the amount of kills
	 * @return amount of kills
	 */
	public int getKills() {
		return kills;
	}
	
	/**
	 * Stop counting time
	 */
	public void stopCounting() {
		stopTime = System.currentTimeMillis();
		running = false;
	}
	
	/**
	 * Get current time
	 * @return current time
	 */
	public long getTime() {
		if(running) {
			return System.currentTimeMillis() - startTime;
		} else {
			return stopTime - startTime;
		}
	}
}