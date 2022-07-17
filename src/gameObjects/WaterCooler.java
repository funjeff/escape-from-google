package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class WaterCooler extends GameObject {

	public static Sprite coolerSprite = new Sprite ("resources/sprites/water_cooler.png");
	public static Sprite coolestSprite = new Sprite ("resources/sprites/config/water_coolest.txt");
	public static Sprite uncoolSprite = new Sprite ("resources/sprites/water_uncool.png");
	
	private boolean isBurning;
	
	public WaterCooler () {
		setSprite (coolerSprite);
		setHitboxAttributes (0, 0, 32, 64);
	}
	
	public boolean isBurning () {
		return isBurning;
	}
	
	@Override
	public void frameEvent () {
		if (isColliding ("Robot")) {
			Robot r = (Robot)getCollisionInfo ().getCollidingObjects ().get (0);
			r.setY (getY () + getHitboxYOffset () - r.getSprite ().getHeight ());
		}
		if (keyDown ('T')) {
			extinguish ();
		}
	}
	
	public void burn () {
		setHitboxAttributes (13, 10, 63, 38);
		setSprite (coolestSprite);
		setY (getY () + 16);
		setX (getX () - 64);
		getAnimationHandler ().setFrameTime (150);
		isBurning = true;
	}
	
	public void extinguish () {
		setHitboxAttributes (0, 0, 0, 0);
		isBurning = false;
		setSprite (uncoolSprite);
		getAnimationHandler ().setFrameTime (0);
	}
	
}
