package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class Bouncer extends GameObject {

	public static Sprite bouncerSpr = new Sprite ("resources/sprites/bouncer.png");

	double vx = -35;
	double vy = -25;
	boolean isFlying = false;
	int flyTime = 0;
	
	public Bouncer () {
		setSprite (bouncerSpr);
		setHitboxAttributes (0, 0, 26, 51);
	}
	
	@Override
	public void frameEvent () {
		if (this.isColliding ("Firedball")) {
			isFlying = true;
		}
		if (isFlying) {
			setX (getX () + vx);
			setY (getY () + vy);
			flyTime++;
			if (flyTime > 30) {
				forget ();
			}
		}
	}
	
	@Override
	public void onDeclare () {
		setY (getY () - 20);
	}
	
}
