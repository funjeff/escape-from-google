package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class Table extends GameObject {

	public static Sprite tableSprite = new Sprite ("resources/sprites/table.png");
	
	@Override
	public void onDeclare () {
		setY (getY () + 14);
		TV tv = new TV ();
		tv.declare (getX () + 17, getY () - 48);
	}
	
	public Table () {
		setSprite (tableSprite);
		setHitboxAttributes (16, 1, 64, 17);
	}
	
}
