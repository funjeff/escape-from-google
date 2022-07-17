package gameObjects;

import engine.GameObject;
import engine.Sprite;
import map.Room;

public class Fireball extends GameObject {
	
	public static Sprite fireball = new Sprite ("resources/sprites/config/fireball.txt");
	
	double velocity = 0;
	boolean dir = false;
	
	int time = 0;
	
	public Fireball () {
		setSprite (fireball);
		getAnimationHandler ().setFrameTime (100);
		velocity = 0;
		setHitboxAttributes (0, 0, 32, 32);
	}
	
	public void setDir (boolean dir) {
		this.dir = dir;
	}
	
	@Override
	public void frameEvent () {
		setX (getX () + (dir ? -8 : 8));
		if (Room.isColliding (this) || time > 200) {
			forget ();
		}
		time++;
	}
	
}
