package gameObjects;

import engine.GameObject;
import engine.Sprite;
import map.Room;

public class TV extends GameObject {
	
	public static Sprite tvSpr = new Sprite ("resources/sprites/tv.png");
	public static Sprite marcusSpr = new Sprite ("resources/sprites/marcus.png");
	public static Sprite bongSpr = new Sprite ("resources/sprites/bong.png");
	
	int time = 0;
	
	@Override
	public void frameEvent () {
		time++;
	}
	
	@Override
	public void draw () {
		
		double offsX = 0, offsY = 0;
		double vscale = 16.0;
		double hscale = 8.0;
		if (time < 15) {
			offsX = time;
		} else if (time < 30) {
			offsX = time;
			double val = (time - 15) / hscale;
			offsY = -vscale * (val * (val - 2));
		} else if (time < 50) {
			offsX = 29;
			offsY = 8;
		} else if (time < 68) {
			offsX = 29;
			offsY = 8 - ((time - 50) / 2);
		} else if (time < 100) {
			offsX = 29;
			offsY = 0;
		} else {
			time = 0;
		}
		
		int drawX = (int)getX () - Room.getViewX ();
		int drawY = (int)getY () - Room.getViewY ();
		tvSpr.draw (drawX, drawY);
		marcusSpr.draw (drawX + 36 - (int)offsX, drawY + 22 - (int)offsY);
		bongSpr.draw (drawX + 6, drawY + 20);
	}

}
