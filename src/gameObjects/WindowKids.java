package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class WindowKids extends GameObject implements Scannable{


	
	public WindowKids () {
		this.setSprite(new Sprite ("resources/sprites/config/windowKids.txt"));
		this.getAnimationHandler().setFrameTime(500);
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent () {
		
		
	}
	
	@Override
	public void onDeclare () {
		
		ScanReigon r = new ScanReigon (this);
		
		r.setRadius (55);
		r.setTitleText ("Monkey kids");
		r.setDescText (new String[] {"plays on the monkey", " bars by pressing w", "while touching the", "bars"});
		r.declare (this.getX(), this.getY());
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockGrab();
		
	}

}
