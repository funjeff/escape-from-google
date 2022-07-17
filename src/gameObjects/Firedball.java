package gameObjects;

import engine.GameObject;
import engine.Sprite;
import map.Room;

public class Firedball extends GameObject {
	
	public static Sprite fireball = new Sprite ("resources/sprites/fired.png");
	
	double velocity = 0;
	boolean dir = false;
	
	int time = 0;
	
	public Firedball () {
		velocity = 0;
		setHitboxAttributes (0, 0, 42, 27);
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
	
	@Override
	public void draw () {
		int drawX = (int)getX () - Room.getViewX () - 2 + (int)(Math.random () * 4);
		int drawY = (int)getY () - Room.getViewY () - 2 + (int)(Math.random () * 4);
		fireball.draw (drawX, drawY);
	}
	
}
