package gameObjects;

import map.MapObject;

public class Door extends MapObject {
	
	public Door () {
		
	}
	
	public void open() {
		this.forget();
	}

}
