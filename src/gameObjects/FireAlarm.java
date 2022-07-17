package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class FireAlarm extends GameObject {
	
	Sprite fireAlarmSpr = new Sprite ("resources/sprites/config/fire_alarm.txt");
	
	boolean isActive = false;
	
	int time = 0;
	
	public FireAlarm () {
		setSprite (fireAlarmSpr);
		getAnimationHandler ().setFrameTime (0);
	}
	
	public void activate () {
		isActive = true;
	}
	
	@Override
	public void frameEvent () {
		if (isActive) {
			if (time < 90) {
				if (time / 15 % 2 == 0) {
					getAnimationHandler ().setAnimationFrame (0);
				} else {
					getAnimationHandler ().setAnimationFrame (1);
				}
			} else {
				getAnimationHandler ().setAnimationFrame (0);
				if (time == 120) {
					time = 0;
				}
			}
			time++;
			if (time == 120) {
				WaterCooler cooler = (WaterCooler)ObjectHandler.getObjectsByName ("WaterCooler").get (0);
				cooler.extinguish ();
			}
		}
	}

}
