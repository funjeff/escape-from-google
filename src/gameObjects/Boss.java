package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;
import map.Room;

public class Boss extends GameObject implements Scannable {
	
	public static Sprite bossSpr = new Sprite ("resources/sprites/boss.png");
	public static Sprite firedSpr = new Sprite ("resources/sprites/youre_fired.png");
	
	public Boss () {
		setHitboxAttributes (0, 0, 32, 32);
		setSprite (bossSpr);
	}
	
	@Override
	public void onDeclare () {
		ScanReigon r = new ScanReigon (this);
		r.declare (getX (), getY ());
		r.setRadius (50);
		r.setTitleText ("The Boss");
		r.setDescText (new String[] {
				"He's the big boss.",
				"He likes to FIRE people",
				"with the F key"
		});
	}
	
	@Override
	public void draw () {
		super.draw ();
		int drawX = (int)(getX () - Room.getViewX ());
		int drawY = (int)(getY () - Room.getViewY ());
		firedSpr.draw (drawX + (int)(Math.random () * 2), drawY + (int)(Math.random () * 2) - 64);
	}

	@Override
	public void scanCompleteAction () {
		((Robot)ObjectHandler.getObjectsByName ("Robot").get (0)).learnedFire2 = true;
	}

}
