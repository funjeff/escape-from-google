package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class Frog extends GameObject implements Scannable{

	int timer = 0;
	
	double vy;
	
	ScanReigon r = new ScanReigon (this);
	
	public Frog () {
		this.setSprite(new Sprite ("resources/sprites/config/frogIdle.txt"));
		this.getAnimationHandler().setFrameTime(100);
		this.setHitbox(0, 0, 16, 16);
		//this.adjustHitboxBorders();
	}
	
	@Override
	public void frameEvent () {
		if (vy == 0) {
			timer = timer + 1;
		}
		if (timer % 25 == 0) {
			this.setSprite(new Sprite ("resources/sprites/config/frogJump.txt"));
			vy = -20;
			timer = timer + 1;
		}
		
		boolean falling = false;
		
		if (vy > 0) {
			falling = true;
		}
		
		if (vy < 18) {
			vy = vy + 2;
		}
		
		
		if (!this.goY(this.getY() + vy)) {
			if (falling) {
				while (this.goY(this.getY() + 1));
			}
			this.setSprite(new Sprite ("resources/sprites/config/frogIdle.txt"));
			vy = 0;
		}
		
		
	}
	
	@Override
	public void onDeclare () {
		
		r.setRadius (30);
		r.setTitleText ("Frog");
		r.setDescText (new String[] {"Hops around", "with the spacebar"});
		r.declare (this.getX(), this.getY());
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockJump();
		
	}

}
