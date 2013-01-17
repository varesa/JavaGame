package fi.dy.esav.JavaGame;

import java.awt.Color;
import java.awt.Graphics;

import fi.dy.esav.GameEngine.GameEngine;
import fi.dy.esav.GameEngine.Utils;
import fi.dy.esav.JavaGame.enums.AINODE;

public class EnemyEntity extends LivingEntity {

	private Color color = Color.RED;

	protected final double xacc = 0.5;
	protected final double xdec = 0.5;
	protected final double maxxvel = 6;

	private int hitpoints = 30;
	private long fullOpacity = -1;
	
	private int fadetime = 400;
	
	private boolean dead = false;

	private boolean ai = true;

	public EnemyEntity(GameEngine engine) {
		super(engine);

		height = 48;
		width = 48;

		super.xacc = this.xacc;
		super.xdec = this.xdec;
		super.maxxvel = this.maxxvel;
	}

	@Override
	public void act() {
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
					xtarget = node.getX() - this.getX();
				}
			} else if (target < 0) {
				node = findNode(AINODE.DIR_DOWN);
				if (node == null) {
					xtarget = player.getX() - this.getX();
				} else {
					xtarget = node.getX() - this.getX();
				}

			} else {
				xtarget = player.getX() - this.getX();
			}

			if (node != null) {
				if (Utils.getXDistanceCenters(this, node) < 10) {
					jump = true;
				}
			}
			
			if(Utils.simpleHitTest(this, player)) {
				
				JavaGame.getWorld().gameOver();
			}
		}

		if(dead && System.currentTimeMillis() > fullOpacity) {
			engine.removeEntity(this);
		}
		
		super.act();
	}

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

	public void damage(double damage) {	
		if (damage > hitpoints) {
			dead = true;
			ai = false;
			JavaGame.getWorld().getScore().increase();
		} else {
			hitpoints -= damage;
		}
		fullOpacity = System.currentTimeMillis() + fadetime;
	}

	@Override
	public void draw(Graphics g) {
		if(System.currentTimeMillis() < fullOpacity) {
			int opacity = (int) ( (double)( fullOpacity - System.currentTimeMillis() ) / (double) fadetime * (double)255);
			Color opaque = new Color(255,0,0, opacity);
			g.setColor(opaque);
			g.fillRect((int)x, (int)y, width, height);
		}
		
		g.setColor(color);
		g.drawRect((int) x, (int) y, width, height);
	}
}
