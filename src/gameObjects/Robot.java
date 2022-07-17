package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;
import map.Room;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Robot extends GameObject {

	public static final Sprite IDLE_SPRITE = new Sprite ("resources/sprites/config/robotIdle.txt");
	public static final Sprite WALK_SPRITE = new Sprite ("resources/sprites/config/robotWalk.txt");
	
	public static final Sprite IDLE_CROUCH_SPRITE = new Sprite ("resources/sprites/config/robotCrouchIdle.txt");
	public static final Sprite WALK_CROUCH_SPRITE = new Sprite ("resources/sprites/config/robotCrouchWalk.txt");
	
	public static final Sprite ROBOT_BREAK = new Sprite ("resources/sprites/config/robotBreak.txt");
	
	
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
	
	boolean learnedMovement = false;
	boolean learnedJump = false;
	boolean learnedDuck = false;
	boolean learnedButtons = false;
	boolean learnedPlant = false;
	boolean clawMode = false;
	boolean learnedLadder = false;
	boolean learnedGrab = false;
	boolean learnedSUS = false;
	boolean learnedPush = false;
	boolean learnedFire = false;
	boolean learnedFire2 = false;
	boolean learnedPipe = false;
	boolean crouching = false;
	boolean onLadder = false;
	
	boolean first = true;
	

	int breakTimer = 0;

	
	public Robot () {
		this.setSprite(IDLE_SPRITE);
		this.setHitbox(0,0,this.getSprite().getWidth(),this.getSprite().getHeight());
		this.getAnimationHandler().setFrameTime(100);
<<<<<<< HEAD
		this.setRenderPriority(1);
=======
		setRenderPriority (1);
>>>>>>> 8d0cd0a12a05299c14d24f9696c2b4cf0aa9d1f6
	}
	
	public void breakBot () {
		breakTimer = 100;
		setSprite (ROBOT_BREAK);
		setX (getX () - 16);
		setY (getY () + 6);
		clawMode ();
	}
	
	@Override
	public void onDeclare () {
		this.setY (getY () - 2);
	}
	
	public void flyOff () {
		this.hide();
		this.blackList();
		FlyPuppet p = new FlyPuppet ();
		p.declare(this.getX(),this.getY());
	}
	
	@Override
	public void frameEvent () {
		
		if (breakTimer > 0) {
			breakTimer--;
			if (breakTimer == 0) {
				setSprite (IDLE_SPRITE);
				setX (getX () + 16);
				setY (getY () - 6);
				unclawMode ();
			}
		}
		
		//Map-specific initialization
		if (first) {
			if (Room.getRoomName ().equals ("resources/mapdata/lab.tmj")) {
				//No starting skills
			}
			if (Room.getRoomName ().equals ("resources/mapdata/office_map.tmj")) {
				learnedMovement = true;
				learnedJump = true;
				learnedDuck = true;
				learnedButtons = true;
				learnedPlant = true;
				learnedLadder = true;
				learnedGrab = true;
			}
			if (Room.getRoomName ().equals ("resources/mapdata/final_level.tmj")) {
				//No starting skills
				Room.forceView (Room.getViewXAcurate () + 400, Room.getViewYAcurate ());
			}
			first = false;
		}
		
		//Spikes
		if (isColliding ("Spikes")) {
			//1216 1536
			this.setX (1216);
			this.setY (1534);
		}
		
		//Venting
		if (Room.getRoomName ().equals ("resources/mapdata/final_level.tmj")) {
			if (learnedSUS) {
				if (isColliding ("Vent")) {
					this.hide ();
				} else {
					this.show ();
				}
			} else {
				if (isColliding ("Vent")) {
					setX (getX () - 6);
				}
			}
		} else {
			if (isColliding ("Vent") && learnedSUS) {
				Vent vent = (Vent)getCollisionInfo ().getCollidingObjects ().get (0);
				vent.doLoad ();
				System.out.println (Room.getViewX () + ", " + Room.getViewY ());
				return;
			}
		}
		
		if (!clawMode) {
			
			if (isColliding ("Claw")) {
				Claw claw = (Claw)getCollisionInfo ().getCollidingObjects ().get (0);
				if (claw.mode == 2) {
					clawMode ();
				}
			}
		
			if (GameCode.keyPressed('E', this)) {
				GameCode.setScanMode(true);
			}
			boolean fPress = GameCode.keyPressed ('F', this);
			if (fPress && learnedFire) {
				boolean flip = this.getAnimationHandler ().flipHorizontal ();
				Fireball fb = new Fireball ();
				fb.setDir (flip);
				fb.declare (getX () + (flip ? -32 : 32), getY ());
			}
			if (fPress && learnedFire2) {
				System.out.println("HIA");
				boolean flip = this.getAnimationHandler ().flipHorizontal ();
				Firedball fb = new Firedball ();
				fb.setDir (flip);
				fb.declare (getX () + (flip ? -42 : 42), getY () - 12);
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
			
			if ((this.isColliding("Button1") || this.isColliding("Button2") || this.isColliding ("Button3") || this.isColliding ("Button4")) && GameCode.keyPressed(KeyEvent.VK_ENTER, this) && learnedButtons) {
				GameObject button = this.getCollisionInfo().getCollidingObjects().get(0);
				if (button instanceof Button1) {
					((Button1)button).pushButton();
				} else if (button instanceof Button2) {
					((Button2)button).pushButton();
				} else if (button instanceof Button3) {
					((Button3)button).pushButton();
				} else if (button instanceof Button4) {
					((Button4)button).pushButton();
				}
				
			}
				
				if (learnedPlant && GameCode.keyPressed('P', this)) {
					Plant p = new Plant ();
					p.declare(this.getX(), this.getY() + 16);
				}
				
				
				
				if (Room.getRoomName ().equals ("resources/mapdata/final_level.tmj")) {
					Room.setView ((int)getX () - 480, (int)getY () - 270);
				} else {
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
				}
				
				
				if (GameCode.keyPressed(KeyEvent.VK_SPACE, this) && learnedJump && vy == 0 && !crouching) {
					vy = -20;
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
		}
	
	}
	
	@Override
	public boolean goX (double x) {
		double startX = getX ();
		setX (x);
		if (this.isColliding ("Desk")) {
			if (learnedPush) {
				ArrayList<GameObject> desks = this.getCollisionInfo ().getCollidingObjects ();
				for (int i = 0; i < desks.size (); i++) {
					Desk curr = (Desk)desks.get (i);
					if ((getX () < curr.getX () && x < startX) || getX () > curr.getX () && x > startX) {
						
					} else {
						if (!(curr.goX (curr.getX () + (x - startX)))) {
							setX (startX);
							return false;
						}
					}
				}
			} else {
				setX (startX);
				return false;
			}
			setX (startX);
		}
		if (this.isColliding ("WaterCooler")) {
			setX (startX);
			return false;
		}
		if (this.isColliding ("Bouncer")) {
			setX (startX);
			return false;
		}
		setX (startX);
		return super.goX (x);
	}
	
	@Override
	public boolean goY (double y) {
		double startY = getY ();
		setY (y);
		if (this.isColliding ("Desk")) {
			setY (startY);
			return false;
		}
		if (this.isColliding ("Table")) {
			setY (startY);
			return false;
		}
		if (this.isColliding ("HatRack")) {
			setY (startY);
			return false;
		}
		if (this.isColliding ("WaterCooler")) {
			setY (startY);
			return false;
		}
		setY (startY);
		return super.goY (y);
	}
	
	void clawMode () {
		clawMode = true;
	}
	
	void unclawMode () {
		clawMode = false;
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
