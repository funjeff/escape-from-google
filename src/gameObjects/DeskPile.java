package gameObjects;

import java.util.ArrayList;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class DeskPile extends GameObject {

	public Sprite deskSpr = new Sprite ("resources/sprites/pile_o_desks.png");
	public Sprite fireSpr = new Sprite ("resources/sprites/config/desks_burning.txt");
	
	boolean isBurning = false;
	
	int burnTimer = 0;
	int smokeTimer = 30;
	
	public DeskPile () {
		setSprite (deskSpr);
	}
	
	public void burn () {
		setSprite (fireSpr);
		getAnimationHandler ().setFrameTime (150);
	}
	
	@Override
	public void onDeclare () {
		setY (getY () - 13);
	}
	
	@Override
	public void frameEvent () {
		if (this.keyDown ('F') && !isBurning) {
			burn ();
			this.setY (this.getY () - 15);
			this.setX (this.getX () - 4);
			isBurning = true;
		}
		if (isBurning) {
			if (smokeTimer == 0) {
				for (int i = 0; i < 2; i++) {
					new SmokePlume ().declare (getX () + Math.random () * 48, getY () + Math.random () * 5);
				}
				smokeTimer = (int)(Math.random () * 10) + 10;
			} else {
				smokeTimer--;
			}
			if (burnTimer == 100) {
				ArrayList<GameObject> fireSprinklers = ObjectHandler.getObjectsByName ("FireSprinkler");
				ArrayList<GameObject> fireAlarms = ObjectHandler.getObjectsByName ("FireAlarm");
				for (int i = 0; i < fireSprinklers.size (); i++) {
					FireSprinkler curr = (FireSprinkler)fireSprinklers.get (i);
					curr.activate ();
				}
				for (int i = 0; i < fireAlarms.size (); i++) {
					FireAlarm curr = (FireAlarm)fireAlarms.get (i);
					curr.activate ();
				}
			}
			burnTimer++;
		}
	}
	
}
