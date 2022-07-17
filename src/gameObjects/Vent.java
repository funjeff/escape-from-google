package gameObjects;

import engine.GameObject;
import engine.Sprite;
import map.Room;

public class Vent extends GameObject {
	
	public static Sprite vent = new Sprite ("resources/sprites/vent.png");
	
	String dest = "";
	
	public Vent () {
		setSprite (vent);
		setHitboxAttributes (-8, -8, 48, 48);
	}
	
	public void doLoad () {
		System.out.println (dest);
		if (!dest.equals ("")) {
			Room.loadRoom (dest);
			Room.forceView (0, 0);
		}
	}
	
	@Override
	public void frameEvent () {
		if (Room.getRoomName ().equals ("resources/mapdata/lab.tmj")) {
			dest = "resources/mapdata/office_map.tmj";
		}
		if (Room.getRoomName ().equals ("resources/mapdata/final_level.tmj")) {
			setHitboxAttributes (-8, -8, 1000, 48);
		}
	}

}
