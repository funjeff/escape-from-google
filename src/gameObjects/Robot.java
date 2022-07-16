package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;
import map.Room;
import java.awt.event.KeyEvent;

public class Robot extends GameObject {

	double vy = 0;
	
	boolean learnedMovement = true;
	boolean learnedJump = true;
	boolean learnedDuck = true;
	boolean learnedButtons = true;
	boolean learnedPlant = true;
	boolean learnedLadder = true;
	
	
	boolean crouching = false;
	boolean onLadder = false;
	
	public Robot () {
		this.setSprite(new Sprite ("resources/sprites/robot.png"));
		this.setHitbox(0,0,this.getSprite().getWidth(),this.getSprite().getHeight());
		this.adjustHitboxBorders();
	}
	
	@Override
	public void frameEvent () {
		
		boolean falling = false;
		
		if (vy > 0) {
			falling = true;
		}
		
		if (vy < 18) {
			vy = vy + 2;
		}
		
		if (this.isColliding("Ladder") && GameCode.keyCheck('W', this)) {
			this.goY(this.getY() -3);
			vy = 0;
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
		
		if (GameCode.keyCheck('D', this) && learnedMovement) {
			this.goX(this.getX() + 5);
		}
		
		if (GameCode.keyCheck('A', this) && learnedMovement) {
			this.goX(this.getX() - 5);
		}
		if (GameCode.keyCheck('S',this) && learnedDuck){
			this.setHitbox(0,19,this.getSprite().getWidth(),16);
			crouching = true;
		} else {
			if (crouching) {
				this.setHitbox(0,0,this.getSprite().getWidth(),this.getSprite().getHeight());
				if (Room.isColliding(this)) {
					this.setHitbox(0,19,this.getSprite().getWidth(),16);
				} else {
					crouching = false;
				}
			}
		}
		
		if (GameCode.keyPressed(KeyEvent.VK_SPACE, this) && learnedJump && vy == 0 && !crouching) {
			vy = -20;
		}
	
	}
}
