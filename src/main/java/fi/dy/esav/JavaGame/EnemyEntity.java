package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.GameEngine.Utils;
import fi.dy.esav.JavaGame.enums.AINODE;

/**
 * Class for the basic enemy
 */

public class EnemyEntity extends LivingEntity {

	private Color color = Color.RED;

	protected final double xacc = 0.3;
	protected final double xdec = 1;
	protected final double maxxvel = 6;

	private int hitpoints = 30;
	private long fullOpacity = -1;

	private long startTime = -1;
	private long spawnTime = 1000;

	private int fadetime = 400;

	private boolean dead = false;

	private boolean ai = true;

	/**
	 * Disabled parameterless constructor
	 */
	private EnemyEntity() {super(null); }
	
	/**
	 * Default constructor
	 * @param engine reference to the engine
	 */
	public EnemyEntity(GameEngine engine) {
		super(engine);

		height = 48;
		width = 48;

		super.xacc = this.xacc;
		super.xdec = this.xdec;
		super.maxxvel = this.maxxvel;

		startTime = System.currentTimeMillis() + spawnTime;
	}

	/**
	 * Method to do the logic in
	 * overrides parent class template method
	 */
	@Override
	public void act() {
		if (System.currentTimeMillis() < startTime) {
			// Do nothing
		} else {
			if (ai) {
				World world = JavaGame.getWorld();
				PlayerEntity player = world.getPlayer();

				int myStory = world.getStory(this);
				int playerStory = world.getStory(player);
				int target = playerStory - myStory;

				JumpAiNode node = null;
				if (target > 0) {
					node = findNode(AINODE.DIR_UP);
					if (node == null) {
						xtarget = player.getX() - this.getX();
					} else {
						xtarget = node.getX() - (this.getX() + this.getWidth()/2);
					}
				} else if (target < 0) {
					node = findNode(AINODE.DIR_DOWN);
					if (node == null) {
						xtarget = player.getX() - this.getX();
					} else {
						xtarget = node.getX() - (this.getX() + this.getWidth()/2);
					}

				} else {
					xtarget = player.getX() - this.getX();
				}

				try {
				} catch (NullPointerException e) {
					System.out.println("no node");
				}
				
				if (node != null) {
					if (Utils.getXDistanceCenters(this, node) < 10) {
						jump = true;
					}
				}

				if (Utils.simpleHitTest(this, player)) {

					JavaGame.getWorld().gameOver();
				}
			}

			if (dead && System.currentTimeMillis() > fullOpacity) {
				engine.removeEntity(this);
			}

			super.act();

		}

	}

	/**
	 * private function to find the closest node for navigating
	 * @param direction we want to travel
	 * @return closest node
	 */
	private JumpAiNode findNode(AINODE direction) {

		int dist = -1;
		JumpAiNode closestNode = null;
		for (AiNode node : AiNode.getNodes()) {
			if (node instanceof JumpAiNode) {
				if ((dist == -1 || Utils.getDistanceCenters(node, this) < dist)
						&& (JavaGame.getWorld().getStory(node) == JavaGame
								.getWorld().getStory(this))
						&& ((JumpAiNode) node).getDirection() == direction) {
					closestNode = (JumpAiNode) node;
					dist = (int) Utils.getDistanceCenters(node, this);
				}

			}
		}

		return closestNode;
	}

	/**
	 * Damage the Entity
	 * @param damage amount of damage to be applied
	 */
	public void damage(double damage) {
		if (System.currentTimeMillis() > startTime) {
			if (damage > hitpoints) {
				dead = true;
				ai = false;
				JavaGame.getWorld().getScore().increase();
			} else {
				hitpoints -= damage;
			}
			fullOpacity = System.currentTimeMillis() + fadetime;
		}
	}

	/**
	 * Method to do the drawing in
	 * overrides parent class template method 
	 * @param g Graphics to draw on
	 */
	@Override
	public void draw(Graphics g) {
		if (System.currentTimeMillis() < startTime) {
			double time = (double) (startTime - System.currentTimeMillis())
					/ (double) spawnTime;
			
			double rotation = time * 2 * Math.PI;
			
			double originx = x + width / 2;
			double originy = y + height / 2;

			double sizex = width + 2 * time * width;
			double sizey = height + 2 * time * height;
			
			Color newcolor = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 - 255*time));
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(newcolor);
			g2d.rotate(rotation, originx, originy);
			g2d.drawRect((int) (originx-sizex/2), (int) (originy-sizey/2), (int)sizex, (int)sizey);
			g2d.rotate(-rotation, originx, originy);
			
			
		} else {
			if (System.currentTimeMillis() < fullOpacity) {
				int opacity = (int) ((double) (fullOpacity - System
						.currentTimeMillis()) / (double) fadetime * (double) 255);
				Color opaque = new Color(255, 0, 0, opacity);
				g.setColor(opaque);
				g.fillRect((int) x, (int) y, width, height);
			}

			g.setColor(color);
			g.drawRect((int) x, (int) y, width, height);
		}
	}
}
