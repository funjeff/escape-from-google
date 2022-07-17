package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class Desk extends GameObject {
	
	private Sprite deskSprite = new Sprite ("resources/sprites/desk_maybe.png");
	
	double accel = 2;
	double fallVel = 2;
	
	public Desk () {
		setSprite (deskSprite);
		setHitboxAttributes (0, 16, 32, 15);
	}
	
	@Override
	public void frameEvent () {
		if (goY (getY () + fallVel)) {
			fallVel += accel;
		}
		if (this.isColliding ("WaterCooler")) {
			WaterCooler cooler = (WaterCooler)this.getCollisionInfo ().getCollidingObjects ().get (0);
			cooler.burn ();
			forget ();
		}
	}

}
