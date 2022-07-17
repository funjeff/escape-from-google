package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Vector2D;

public class FinalLevelLanding extends GameObject {

	boolean first = true;
	boolean was = false;
	
	Claw claw;
	
	public FinalLevelLanding () {
		setHitboxAttributes (0, 0, 32, 32);
	}
	
	@Override
	public void frameEvent () {
		if (this.isColliding ("Robot")) {
			boolean enter = !was;
			was = true;
			if (first) {
				claw = new Claw ();
				claw.declare ();
				claw.setX (getX ());
				claw.setY (getY () - 600);
				claw.destination = new Vector2D (1744, 1216);
				first = false;
				Robot r = (Robot)getCollisionInfo ().getCollidingObjects ().get (0);
				r.breakBot ();
			} else if (enter) {
				claw.setX (getX ());
				claw.setY (getY () - 600);
				claw.destination = new Vector2D (1744, 1216);
			}
		} else {
			was = false;
		}
	}
	
}
