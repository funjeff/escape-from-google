package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class WindowGuy extends GameObject implements Scannable{


	
	public WindowGuy () {
		this.setSprite(new Sprite ("resources/sprites/config/windowGuy.txt"));
		this.getAnimationHandler().setFrameTime(100);
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent () {
		
		
	}
	
	@Override
	public void onDeclare () {
		
		ScanReigon r = new ScanReigon (this);
		
		r.setRadius (60);
		r.setTitleText ("Button guy");
		r.setDescText (new String[] {"press's buttons for","a living using the", "enter button"});
		r.declare (this.getX()+ 40, this.getY() + 30);
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockButtons();
		
	}

}
