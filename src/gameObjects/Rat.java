package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class Rat extends GameObject implements Scannable{

	int timer = 0;
	int standTimer = 0;
	
	boolean standUp = false;
	
	boolean moveLeft = false;
	
	ScanReigon r = new ScanReigon (this);
	public Rat () {
		this.getAnimationHandler().setFrameTime(100);
		this.setSprite(new Sprite ("resources/sprites/config/rat.txt"));
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent () {
		
		if (!standUp) {
			if (moveLeft) {
				timer = timer + 1;
				this.setX(this.getX() - 2);
			} else {
				timer = timer -1;
				this.setX(this.getX() + 2);
			}
		} else {
			
			if (this.getAnimationHandler().getFrame() == 3 && standTimer != 25) {
				standTimer = standTimer + 1;
				this.getAnimationHandler().setFrameTime(0);
				this.getAnimationHandler().setAnimationFrame(3);
			}
			
			if (this.getAnimationHandler().getFrame() == 6) {
				standUp = false;
				this.setSprite(new Sprite ("resources/sprites/config/rat.txt"));
			}
			
			
			if (standTimer == 25) {
				this.getAnimationHandler().setFrameTime(100);
				this.getAnimationHandler().setAnimationFrame(4);
				standTimer = 0;
			}
		}
		
		if (timer > 50) {
			moveLeft = false;
			this.getAnimationHandler().setFlipHorizontal(false);
		}
		
		if (timer == 25) {
			standUp = true;
			this.setSprite(new Sprite ("resources/sprites/config/rat_sit.txt"));
			
			if (moveLeft) {
				timer = timer + 1;
			} else {
				timer = timer - 1;
			}
		}
		
		
		if (timer < 0) {
			moveLeft = true;
			this.getAnimationHandler().setFlipHorizontal(true);
		}
		r.setX(this.getX());
	}
	
	@Override
	public void onDeclare () {
		
		r.setRadius (30);
		r.setTitleText ("RAT");
		r.setDescText (new String[] {"Likes to walk", "using the", "A and D keys"});
		r.declare (this.getX(), this.getY());
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockMovement();
		
	}

}
