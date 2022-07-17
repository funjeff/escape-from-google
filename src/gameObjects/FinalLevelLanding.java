package gameObjects;

import engine.GameObject;
import engine.Vector2D;

public class FinalLevelLanding extends GameObject {

	boolean first = true;
	
	Claw claw;
	
	public FinalLevelLanding () {
		setHitboxAttributes (0, 0, 32, 32);
	}
	
	@Override
	public void frameEvent () {
		if (this.isColliding ("Robot") && first) {
			claw = new Claw ();
			claw.declare ();
			claw.setX (getX ());
			claw.setY (getY () - 600);
			claw.destination = new Vector2D (1792, 1216);
			first = false;
		}
	}
	
}
