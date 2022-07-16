package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;

public class PlaygroundKids extends GameObject implements Scannable{


	
	public PlaygroundKids () {
		
	}
	
	@Override
	public void frameEvent () {
		
		
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockGrab();
		
	}

}
