package fi.dy.esav.JavaGame;

public class Score {
	private int kills = 0;
	
	private long startTime;
	private boolean running = true;
	private long stopTime;
	
	public Score() {
		startTime = System.currentTimeMillis();
	}
	
	public void increase() {
		kills++;
	}
	
	public int getKills() {
		return kills;
	}
	
	public void stopCounting() {
		stopTime = System.currentTimeMillis();
		running = false;
	}
	
	public long getTime() {
		if(running) {
			return System.currentTimeMillis() - startTime;
		} else {
			return stopTime - startTime;
		}
	}
}