package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class DeskGuy extends GameObject implements Scannable {

	public Sprite deskGuySpr = new Sprite ("resources/sprites/config/desk_guy.txt");
	
	public DeskGuy () {
		setSprite (deskGuySpr);
		this.getAnimationHandler ().setFrameTime (200);
		this.setHitboxAttributes (0, 0, 64, 32);
	}
	
	@Override
	public void onDeclare () {
		ScanReigon r = new ScanReigon (this);
		r.declare (getX (), getY ());
		r.setRadius (50);
		r.setTitleText ("Desk Pusher Technician");
		r.setDescText (new String[] {
				"This employee walks into",
				"desks to push them.",
				"(He's the new intern.)"
		});
	}

	@Override
	public void scanCompleteAction () {
		((Robot)ObjectHandler.getObjectsByName ("Robot").get (0)).learnedPush = true;
	}
	
}
