package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class ScannablePlant extends GameObject implements Scannable{


	
	public ScannablePlant () {
		this.setSprite(new Sprite ("resources/sprites/config/plant/J_Idle.txt"));
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent () {
		
		
	}
	
	@Override
	public void onDeclare () {
		
		ScanReigon r = new ScanReigon (this);
		
		r.setRadius (38);
		r.setTitleText ("Plant");
		r.setDescText (new String[] {"Blends in with the p", "key breaks with the", "space key"});
		r.declare (this.getX(), this.getY());
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockPlant();
		
	}

}
