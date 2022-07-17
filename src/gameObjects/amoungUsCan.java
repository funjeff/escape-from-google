package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class amoungUsCan extends GameObject implements Scannable{


	public amoungUsCan () {
		this.setSprite(new Sprite ("resources/sprites/amoungUsCan.png"));
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent () {
		
		
	}
	
	@Override
	public void onDeclare () {
		
		ScanReigon r = new ScanReigon (this);
		
		r.setRadius (30);
		r.setTitleText ("Suspicious looking can");
		r.setDescText (new String[] {"for some reason","it can travel", "through vents"});
		r.declare (this.getX(), this.getY());
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockSUS();
		
	}

}
