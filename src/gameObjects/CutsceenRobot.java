package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;
import map.Room;

public class CutsceenRobot extends GameObject{

	boolean falling = false;
	boolean broken = false;
	
	int timer = 0;
	
	boolean inzialized = false;
	
	public static final Sprite POWER_DOWN = new Sprite ("resources/sprites/config/robotPowerDown.txt");
	public static final Sprite DRAMATIC_FALL = new Sprite ("resources/sprites/config/robotFall.txt");
	
	int vx = 30;
	int vy = 0;
	
	public CutsceenRobot () {
		this.setSprite(new Sprite ("resources/sprites/config/jetpackRobot.txt"));
		this.getAnimationHandler().setFrameTime(100);
		try {
			GameCode.getSoundPlayer().stop();
			} catch (NullPointerException e) {
			}
		GameCode.getSoundPlayer().play("resources/moon.wav", 6F);
		
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent () {
		
		if (!inzialized) {
			Room.forceView((int)this.getX() - 480, (int)this.getY() - 280);
			inzialized = true;
		}
		if (!falling && !broken) {
			this.setX(this.getX() + 10);
			Room.setView(Room.getViewX() + 10, Room.getViewY());			
		}
		
		
		if (this.isColliding("DroneStrike")) {
			falling = true;
			this.setSprite(new Sprite ("resources/sprites/shitJetpack.png"));
			this.setSprite(DRAMATIC_FALL);
			this.getAnimationHandler().setFrameTime(500);
			this.getAnimationHandler().setRepeat(false);
		}
		
		if (this.getAnimationHandler().getFrame() == 8 && timer == 0) {
			GameCode.getSoundPlayer().stop();
			timer = 1;
		}
		
		if (timer >= 1) {
			timer = timer + 1;
		}
		
		
		if (timer == 90) {
			
			GameCode.getSoundPlayer().play("resources/plot twist theme.wav", 6F);
		}
		
		if (timer == 250) {
			LeadScientist villan = new LeadScientist();
			villan.declare(this.getX() + 500, this.getY() - 20);
		}
		
		if (falling) {
			
			vy = vy + 2;
			if (!this.goY(this.getY() + vy)) {		
				while (this.goY(this.getY() + 1));
				broken = true;
				falling = false;
			}
				
		}
		if (falling || broken) {
			if (vx > 0) {
				vx = vx - 1;
			}
			this.goX(this.getX() + vx);
			Room.setView(Room.getViewX() + vx, Room.getViewY());
		}
		
	}
	
	
}
