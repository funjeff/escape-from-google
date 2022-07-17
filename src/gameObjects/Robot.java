package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;
import map.Room;
import java.awt.event.KeyEvent;

public class Robot extends GameObject {

	public static final Sprite IDLE_SPRITE = new Sprite ("resources/sprites/config/robotIdle.txt");
	public static final Sprite WALK_SPRITE = new Sprite ("resources/sprites/config/robotWalk.txt");
	
	public static final Sprite IDLE_CROUCH_SPRITE = new Sprite ("resources/sprites/config/robotCrouchIdle.txt");
	public static final Sprite WALK_CROUCH_SPRITE = new Sprite ("resources/sprites/config/robotCrouchWalk.txt");
	
	
	double vy = 0;
	
	/*
	boolean learnedMovement = false;
	boolean learnedJump = false;
	boolean learnedDuck = false;
	boolean learnedButtons = false;
	boolean learnedPlant = false;
	boolean learnedLadder = false;
	boolean learnedGrab = false;
	boolean learnedSUS= false;
	*/
	
	boolean learnedMovement = true;
	boolean learnedJump = true;
	boolean learnedDuck = true;
	boolean learnedButtons = true;
	boolean learnedPlant = true;
	boolean learnedLadder = true;
	boolean learnedGrab = true;
	boolean learnedSUS= true;
	
	boolean crouching = false;
	boolean onLadder = false;
	
	public Robot () {
		this.setSprite(IDLE_SPRITE);
		this.setHitbox(0,0,this.getSprite().getWidth(),this.getSprite().getHeight());
		this.adjustHitboxBorders();
		this.getAnimationHandler().setFrameTime(100);
	}
	
	@Override
	public void frameEvent () {
		
		
		if (GameCode.keyPressed('E', this)) {
			GameCode.setScanMode(true);
		}
		
		boolean falling = false;
		
		if (vy > 0) {
			falling = true;
		}
		
		if (vy < 18) {
			vy = vy + 2;
		}
		
		if (this.isColliding("Ladder") && GameCode.keyCheck('W', this) && learnedLadder) {
			this.goY(this.getY() -3);
			vy = 0;
		}
		
		if (this.isColliding("grabableCeiling") && GameCode.keyCheck('W', this) && learnedGrab) {
			vy = 0;
			this.getAnimationHandler().setFlipVertical(true);
		} else {
			this.getAnimationHandler().setFlipVertical(false);
		}
		
		
		if (!this.goY(this.getY() + vy)) {
			if (falling) {
				while (this.goY(this.getY() + 1));
			}
			vy = 0;
		}
		
		
		
		if (learnedPlant && GameCode.keyPressed('P', this)) {
			Plant p = new Plant ();
			p.declare(this.getX(), this.getY() + 16);
		}
		
		if ((this.isColliding("Button1") || this.isColliding("Button2")) && GameCode.keyPressed(KeyEvent.VK_ENTER, this) && learnedButtons) {
			try {
				((Button1)this.getCollisionInfo().getCollidingObjects().get(0)).pushButton();
			} catch (ClassCastException e) {
				((Button2)this.getCollisionInfo().getCollidingObjects().get(0)).pushButton();
			}
		}
		
		
		if (Room.getViewY() + 60 > this.getY()) {
			if ((int)(Room.getViewY() - (60 - (this.getY() - Room.getViewY()))) > 0) {
				Room.setView(Room.getViewX(),(int)(Room.getViewY() - (60 - (this.getY() - Room.getViewY()))) );
			}
		}
		
		if (Room.getViewX() + 150 > this.getX()) {
			if ((Room.getViewX() - (150 - (this.getX() - Room.getViewX())) > 0)){
				Room.setView((int)(Room.getViewX() - (150 - (this.getX() - Room.getViewX()))), Room.getViewY() );
			}
		}
		
		if (Room.getViewY() + 460 < this.getY()) {
			Room.setView(Room.getViewX(),(int)(this.getY() - 460));
		}
		
		if (Room.getViewX() + 725 < this.getX()) {
			Room.setView((int)(this.getX() - 725), Room.getViewY());
		}
		
		
		boolean walking = false;
		
		if (GameCode.keyCheck('D', this) && learnedMovement) {
			this.goX(this.getX() + 5);
			if (!crouching) {
				this.setSprite(WALK_SPRITE);
			} else {
				this.setSprite(WALK_CROUCH_SPRITE);
			}
			walking = true;
			this.getAnimationHandler().setFlipHorizontal(false);
		}
		
		if (GameCode.keyCheck('A', this) && learnedMovement) {
			this.goX(this.getX() - 5);
			if (!crouching) {
				this.setSprite(WALK_SPRITE);
			} else {
				this.setSprite(WALK_CROUCH_SPRITE);
			}
			walking = true;
			this.getAnimationHandler().setFlipHorizontal(true);
		}
		
		if (!walking) {
			if (!crouching) {
				this.setSprite(IDLE_SPRITE);
			} else {
				this.setSprite(IDLE_CROUCH_SPRITE);
			}
		}
		
		if (GameCode.keyCheck('S',this) && learnedDuck){
			this.setHitbox(0,0,this.getSprite().getWidth(),23);
			crouching = true;
			if (this.getSprite() != WALK_CROUCH_SPRITE) {
				this.setSprite(IDLE_CROUCH_SPRITE);
			}
		} else {
			if (crouching) {
				this.setSprite(IDLE_SPRITE);
				this.setY(this.getY() - 9);
				this.setHitbox(0,0,this.getSprite().getWidth(),this.getSprite().getHeight());
				if (Room.isColliding(this)) {
					this.setSprite(IDLE_CROUCH_SPRITE);
					this.setHitbox(0,0,this.getSprite().getWidth(),23);
					this.setY(this.getY()+ 9);
				} else {
					
					crouching = false;
				}
			}
		}
		
		if (GameCode.keyPressed(KeyEvent.VK_SPACE, this) && learnedJump && vy == 0 && !crouching) {
			vy = -20;
		}
	
	}
	
	@Override
	public void pausedEvent (){
		if (GameCode.keyPressed('E', this)) {
			GameCode.setScanMode(false);
		}
	}
	
	
	public void unlockMovement () {
		learnedMovement = true;
	}
	
	public void unlockJump () {
		learnedJump = true;
	}
	
	public void unlockDuck () {
		learnedDuck = true;
	}
	
	public void unlockButtons () {
		learnedButtons = true;
	}
	
	public void unlockPlant () {
		learnedPlant = true;
	}
	
	public void unlockLadder () {
		learnedLadder = true;
	}
	
	public void unlockGrab () {
		learnedGrab = true;
	}
	public void unlockSUS() {
		learnedSUS = true;
	}
	
}
