package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class WaterCooler extends GameObject implements Scannable {

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
	}
	
	public void burn () {
		setHitboxAttributes (13, 10, 63, 38);
		setSprite (coolestSprite);
		setY (getY () + 16);
		setX (getX () - 64);
		getAnimationHandler ().setFrameTime (150);
		isBurning = true;
		ScanReigon r = new ScanReigon (this);
		r.declare (getX (), getY ());
		r.setRadius (80);
		r.setTitleText ("Flaming water cooler");
		r.setDescText (new String[] {
				"What? I mean how?",
				"I mean... push F to",
				"shoot fire, I guess..."
		});
	}
	
	public void extinguish () {
		setHitboxAttributes (-1024, -1024, 32, 32);
		isBurning = false;
		setSprite (uncoolSprite);
		getAnimationHandler ().setFrameTime (0);
	}

	@Override
	public void scanCompleteAction () {
		((Robot)ObjectHandler.getObjectsByName ("Robot").get (0)).learnedFire = true;
	}
	
}
