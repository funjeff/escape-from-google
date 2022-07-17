package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;

import java.awt.event.KeyEvent;

public class TitleScreen extends GameObject {
	
	public TitleScreen () {
		this.setSprite(new Sprite ("resources/sprites/titalScreen.png"));
	}
	
	@Override
	public void frameEvent () {
		if (GameCode.keyPressed(KeyEvent.VK_ENTER, this)) {
			this.forget();
			GameCode.realInit();
		}
	}
	
	

}
