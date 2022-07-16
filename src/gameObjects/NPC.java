package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Textbox;

public class NPC extends GameObject {
	
	Textbox text = new Textbox ("");

	public NPC () {
		text.setFont("Bomb");
		text.setBox("Bomb");
		
		text.changeWidth(50);
		text.changeHeight(7);
		this.initText();
	}
	
	public void onTalk() {
		GameCode.getBombMaster().freeze();
		text.declare(100, 300);
	}

	@Override
	public void frameEvent () {
		if (text.isEmpty()) {
			onClose();
		}
	}

	public void onClose () {
		text.forget();
		this.initText();
		GameCode.getBombMaster().unfreeze();
	}
	
	public void initText() {
		
	}
}
