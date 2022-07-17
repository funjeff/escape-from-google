package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;
import engine.Vector2D;

public class Button4 extends GameObject {

	public Button4 () {
		this.setSprite (new Sprite ("resources/sprites/button.png"));
		this.useSpriteHitbox();
	}
	
	@Override
	public void onDeclare () {
		setX (getX () + 4);
		setY (getY () - 18);
	}
	
	public void pushButton() {
		GameCode.getSoundPlayer().playSoundEffect(6F, "resources/buttonChime.wav");
		Claw claw = (Claw)ObjectHandler.getObjectsByName ("Claw").get (0);
		claw.setX (getX () - 576);
		claw.setY (getY () - 300);
		claw.destination = new Vector2D (1632, 384);
		claw.start ();
	}
	
}
