package gameObjects;


import engine.ObjectHandler;
import map.Room;

public class EnterableObject extends BreakableObject {
	protected boolean inside = false;
	protected boolean isBroken = false;
	public EnterableObject () {
		
	}
	@Override 
	public void frameEvent () {
		if (this.isColliding("Robot") && !inside && !this.isBroken ) {
			this.onEntry();
			
		}
		if (inside) {
			double x = this.getX ();
			double y = this.getY ();
			int viewX = Room.getViewX ();
			int viewY = Room.getViewY ();
			if (y - viewY >= 320 && y - 320 < Room.getHeight () * 16 - 480) {
				viewY = (int) y - 320;
				Room.setView (Room.getViewX (), viewY);
			}
			if (y - viewY <= 160 && y - 160 > 0) {
				viewY = (int) y - 160;
				Room.setView (Room.getViewX (), viewY);
			}
			if (x - viewX >= 427 && x - 427 < Room.getWidth () * 16 - 640) {
				viewX = (int) x - 427;
				Room.setView (viewX, Room.getViewY ());
			}
			if (x - viewX <= 213 && x - 213 > 0) {
				viewX = (int) x - 213;
				Room.setView (viewX, Room.getViewY ());
			}
			ObjectHandler.getObjectsByName("Robot").get(0).setX(this.getX());
			ObjectHandler.getObjectsByName("Robot").get(0).setY(this.getY());
			try {
				if (this.isCollidingChildren("Enemy") || this.isCollidingChildren("Projectile")) {
					boolean notShard = false;
					for (int i = 0; i < this.getCollisionInfo().getCollidingObjects().size(); i++) {
						
						if (!(this.getCollisionInfo().getCollidingObjects().get(i) instanceof Shard)) {
							notShard = true;
							break;
						}
						
					}
					
					if (notShard) {
						this.onBreak();
					}
				}
			} catch (NullPointerException e) {
			
			}
		}
		
	}
	public void onEntry () {
		ObjectHandler.getObjectsByName("Robot").get(0).getAnimationHandler().hide();
		ObjectHandler.getObjectsByName("Robot").get(0).blackList();
		inside = true;
	}
	public void onBreak () {
		isBroken = true;
	}
	public void exit () {
		inside = false;
		ObjectHandler.getObjectsByName("Robot").get(0).getAnimationHandler().show();
		ObjectHandler.getObjectsByName("Robot").get(0).whiteList();
		}
	}
