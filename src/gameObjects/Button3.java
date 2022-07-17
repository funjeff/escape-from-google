package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class Button3 extends GameObject {

	public Button3 () {
		this.setSprite (new Sprite ("resources/sprites/button.png"));
		this.useSpriteHitbox();
	}
	
	@Override
	public void onDeclare () {
		setX (getX () + 4);
		setY (getY () - 18);
	}
	
	public void pushButton() {
		Claw claw = (Claw)ObjectHandler.getObjectsByName ("Claw").get (0);
		claw.start ();
	}
	
}
