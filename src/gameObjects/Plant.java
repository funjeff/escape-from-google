package gameObjects;


import engine.GameCode;
import engine.ObjectHandler;
import engine.Sprite;
import map.Room;
import java.awt.event.KeyEvent;

public class Plant extends EnterableObject {
	
	private boolean inPot = false;
	
	private boolean inzialize = true;
	
	private int despawnTimer = 0;
	
	private int originX;
	private int originY;
	
	private static final Sprite J_IDLE = new Sprite ("resources/sprites/config/Plant/J_Idle.txt");
	
	private static final Sprite J_WALK = new Sprite ("resources/sprites/config/Plant/J_Walking.txt");

	private static final Sprite J_DOWN = new Sprite ("resources/sprites/config/Plant/J_Down.txt");	
	
	private static final Sprite J_UP = new Sprite ("resources/sprites/config/Plant/J_Up.txt");
	
	public Plant () {
		this.setSprite(J_IDLE);
		this.getAnimationHandler().setFrameTime(40);
		this.setHitboxAttributes(0,18,16,30);
	}
	public boolean isExposed() {
		return !this.isBroken && !(this.getSprite().equals(J_IDLE));
	}
	@Override 
	public void frameEvent () {
		super.frameEvent();
		if (!this.isBroken) {
			if (inzialize) {
				this.setY(this.getY() - 35);
				inzialize = false;
			}
			this.goY(this.getY() + 2);
			if (inPot) {
				
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
				
				
				if (keyDown('D') || keyDown ('A')) {
					if (!(this.getSprite().equals(J_UP) || this.getSprite().equals(J_WALK))) {
							this.setSprite(J_UP);
							this.setHitboxAttributes(0,0,16,48);
						
					} else {
						if (this.getAnimationHandler().getFrame() == 7) {
							if (this.getSprite().equals(J_UP)) {
								this.setSprite(J_WALK);
							}
						}
					}
					if (this.keyDown('A') && !this.keyDown('D')) {
						if (!this.getAnimationHandler().flipHorizontal()) {
							this.getAnimationHandler().setFlipHorizontal(true);
						}
						this.goX(this.getX() - 2);
					}
					if (this.keyDown('D') && !this.keyDown('A')) {
						if (this.getAnimationHandler().flipHorizontal()) {
							this.getAnimationHandler().setFlipHorizontal(false);
						}
						this.goX(this.getX() + 2);
					}
				} else {
					if (this.getSprite().equals(J_UP) || this.getSprite().equals(J_WALK)) {
						this.setSprite(J_DOWN);
					}
					if (this.getAnimationHandler().getFrame() == 3) {
						if (this.getSprite().equals(J_DOWN)) {
							this.setSprite(J_IDLE);
							this.setHitboxAttributes(0,18,16,30);
						}
					}
				}
				if (GameCode.keyPressed(KeyEvent.VK_SPACE, this)) {
					this.makeBroken();
				}
			}
		} else {
		this.despawnAllCoolLike(200);
		}
	}
	@Override
	public void onBreak() {
		this.makeBroken();
	}
	@Override
	public void onEntry() {
		if (!this.isBroken) {
			this.inside = true;
			this.setSprite(J_IDLE);
			ObjectHandler.getObjectsByName("Robot").get(0).getAnimationHandler().hide();
			ObjectHandler.getObjectsByName("Robot").get(0).blackList();
			inPot = true;
		}
	}
	public void makeBroken () {
		this.isBroken = true;
		ObjectHandler.getObjectsByName("Robot").get(0).whiteList();
		this.inside = false;
		ObjectHandler.getObjectsByName("Robot").get(0).show();
		this.Break(new Shard [] {new Shard (new Sprite ("resources/sprites/config/Plant/shards/shard1.txt")), new Shard (new Sprite ("resources/sprites/config/Plant/shards/shard2.txt")), new Shard (new Sprite ("resources/sprites/config/Plant/shards/shard3.txt")), new Shard (new Sprite ("resources/sprites/config/Plant/shards/shard4.txt")), new Shard (new Sprite ("resources/sprites/config/Plant/shards/shard5.txt")), new Shard (new Sprite ("resources/sprites/config/Plant/shards/shard6.txt")), new Shard (new Sprite ("resources/sprites/config/Plant/shards/shard7.txt")), new Shard (new Sprite ("resources/sprites/config/Plant/shards/shard8.txt")), new Shard (new Sprite ("resources/sprites/config/Plant/shards/shard9.txt"))},this.getX(),this.getY() + 18, 9, 2, 4, 0, 3.14);
		this.setSprite(new Sprite ("resources/sprites/Broken_Plant.png"));
	}
}
