package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class RaveRat extends GameObject implements Scannable{

	int standTimer = 0;
	
	boolean standUp = false;
	
	public RaveRat () {
		this.getAnimationHandler().setFrameTime(100);
		this.setSprite(new Sprite ("resources/sprites/config/rat_sit.txt"));
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent () {
		
			
			if (this.getAnimationHandler().getFrame() == 3 && standTimer != 25) {
				standTimer = standTimer + 1;
				this.getAnimationHandler().setFrameTime(0);
				this.getAnimationHandler().setAnimationFrame(3);
			}
			
			
			if (standTimer == 25) {
				this.getAnimationHandler().setFrameTime(100);
				this.getAnimationHandler().setAnimationFrame(4);
				standTimer = 0;
			}
		}
	
	@Override
	public void onDeclare () {
		
		ScanReigon r = new ScanReigon (this);
		
		r.setRadius (30);
		r.setTitleText ("RAVE RAT");
		r.setDescText (new String[] {"Press's d", "to have an", "epic rave"});
		r.declare (this.getX(), this.getY());
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockDuck();
		
	}

}
