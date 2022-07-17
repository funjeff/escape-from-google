package gameObjects;

import engine.GameObject;
import engine.Sprite;
import map.Room;

public class Vent extends GameObject {
	
	public static Sprite vent = new Sprite ("resources/sprites/vent.png");
	
	String dest = "";
	
	public Vent () {
		setSprite (vent);
	}
	
	@Override
	public void frameEvent () {
		if (Room.getRoomName ().equals ("resources/mapdata/lab.tmj")) {
			dest = "resources/mapdata/office_map.tmj";
		}
	}

}
