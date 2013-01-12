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
		
		if(target > 0) {
			JumpAiNode node = findNode(AINODE.DIR_UP);
			if(node == null) {
				xtarget = player.getX() - this.getX();
			} else {
				xtarget = node.getX();
			}
		} else if (target < 0) {
			JumpAiNode node = findNode(AINODE.DIR_DOWN);
			if(node == null) {
				xtarget = player.getX() - this.getX();
			} else {
				xtarget = node.getX();
			}
			
		} else {
			xtarget = player.getX() - this.getX();
		}
		
		//System.out.println(xtarget);
		super.act();
	}
	
	private JumpAiNode findNode(AINODE direction) {
		
		int dist = -1;
		JumpAiNode closestNode = null;
		for (AiNode node : AiNode.getNodes()) {
			if (node instanceof JumpAiNode) {
				if (
						(dist == -1 || Utils.getDistanceCenters(node, this) < dist) &&
						(JavaGame.getWorld().getStory(node) == JavaGame.getWorld().getStory(this)) &&
						((JumpAiNode) node).getDirection() == direction
				   ) {
					closestNode = (JumpAiNode) node; 
				}
				
			}
		}
		
		return closestNode;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect((int)x, (int)y, width, height);
	}
}
