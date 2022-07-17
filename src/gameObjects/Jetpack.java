package gameObjects;


import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class Jetpack extends GameObject implements Scannable{


	
	public Jetpack () {
		this.setSprite(new Sprite ("resources/sprites/jetpackJones.png"));
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent () {
		
		
	}
	
	@Override
	public void onDeclare () {
		
		ScanReigon r = new ScanReigon (this);
		
		r.setRadius (38);
		r.setTitleText ("Jetpack Jones");
		r.setDescText (new String[] {"flys using his jetpack"});
		r.declare (this.getX(), this.getY());
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).flyOff();
		
	}

}

