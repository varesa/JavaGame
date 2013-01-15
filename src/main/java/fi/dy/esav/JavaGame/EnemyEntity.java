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
		World world = JavaGame.getWorld();
		PlayerEntity player = world.getPlayer();
		
		
		
		int myStory = world.getStory(this);
		int playerStory = world.getStory(player);
		int target = playerStory - myStory;
		//System.out.println(target); // TODO: remove
		
		
		JumpAiNode node = null;
		if(target > 0) {
			 node = findNode(AINODE.DIR_UP);
			if(node == null) {
				xtarget = player.getX() - this.getX();
			} else {
				xtarget = node.getX() - this.getX();
			}
		} else if (target < 0) {
			node = findNode(AINODE.DIR_DOWN);
			if(node == null) {
				xtarget = player.getX() - this.getX();
			} else {
				xtarget = node.getX() - this.getX();
			}
			
		} else {
			xtarget = player.getX() - this.getX();
		}
		
		if(node != null) {
			if(Utils.getXDistanceCenters(this, node) < 10) {
				jump = true;
			}
		}
		
		//System.out.println(xtarget);
		super.act();
	}
	
	private JumpAiNode findNode(AINODE direction) {
		
		int dist = -1;
		JumpAiNode closestNode = null;
		//System.out.println("Looking for nodes");
		for (AiNode node : AiNode.getNodes()) {
			//System.out.print("Found node");
			if (node instanceof JumpAiNode) {
				//System.out.println("and it is a jump node");
				//System.out.println("bools: " + String.valueOf((dist == -1 || Utils.getDistanceCenters(node, this) < dist)) + ", " + String.valueOf((JavaGame.getWorld().getStory(node) == JavaGame.getWorld().getStory(this))) + ", " + String.valueOf(((JumpAiNode) node).getDirection() == direction));
				//System.out.println("A: " + JavaGame.getWorld().getStory(node) + ", B: " + JavaGame.getWorld().getStory(this));
				
				if (
						(dist == -1 || Utils.getDistanceCenters(node, this) < dist) &&
						(JavaGame.getWorld().getStory(node) == JavaGame.getWorld().getStory(this)) &&
						((JumpAiNode) node).getDirection() == direction
				   ) {
					//System.out.println("new closest node");
					closestNode = (JumpAiNode) node; 
				}
				
			}
			//System.out.println("");
		}
		
		return closestNode;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect((int)x, (int)y, width, height);
	}
}
