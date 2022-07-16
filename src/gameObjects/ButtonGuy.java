package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;

public class ButtonGuy extends GameObject implements Scannable{


	
	public ButtonGuy () {
		
	}
	
	@Override
	public void frameEvent () {
		
		
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockButtons();
		
	}

}
