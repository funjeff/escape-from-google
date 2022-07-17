package engine;

import java.util.ArrayList;

import javax.sound.sampled.FloatControl;

import gameObjects.ScanReigon;
import gameObjects.TV;
import gameObjects.Table;
import gameObjects.TitleScreen;
import gameObjects.WaterCooler;
import gameObjects.Claw;
import gameObjects.Desk;
import gameObjects.DeskPile;
import gameObjects.FireAlarm;
import gameObjects.FireSprinkler;
import gameObjects.HatRack;
import gameObjects.FullCrate;
import gameObjects.GreenFilter;
import gameObjects.Robot;
import gameObjects.ScanReigon;
import map.Room;
import java.awt.event.KeyEvent;



public class GameCode {
	
	static int veiwX;
	static int veiwY;
	static boolean isScanMode = false;
	
	static GreenFilter green = new GreenFilter ();
	

	static ArrayList <Asker> askers = new ArrayList <Asker> ();
	
	static SoundPlayer s;


	public static void testBitch () {
		
		
	}
	
	public static void beforeGameLogic () {
		
	}

	public static void afterGameLogic () {
		
	}

	public static void init () {
		
		try {
			s.stop();
			} catch (NullPointerException e) {
				
			}
			s = new SoundPlayer();
		

		TitleScreen t = new TitleScreen();
		t.declare(0, 0);
		
		s.play("resources/escape from google.wav", 6F);
		//Test

		
		//openCrate();

//		ScanReigon r = new ScanReigon (null);
//		r.setRadius (40);
//		r.setTitleText ("HELLO");
//		r.setDescText (new String[] {"DESCRIPTION", "TEXT"});
//		r.declare (256, 256);
	
	//	Bat b = new Bat();
	//	b.declare(300,200);

//		CactusDude c = new CactusDude();
//		c.declare(300,100);

//		Bombhog h = new Bombhog();
//		h.declare(200,200);
//		
//		Heart h = new Heart ();
//		h.declare(350,250);
//		
		
//		MachineBomber mb = new MachineBomber();
//		mb.declare(400,200);
		
		//Test
		//Room2 room2 = new Room2 ();
		//room2.loadMap ("big_test.tmj");

	}
	
	public static void realInit () {
		
		//Test
        Setup.initAll();
		Room.loadRoom ("resources/mapdata/office_map.tmj");
		
		Room.setView(0, 79);
	//	openCrate();


	}
		
	public static void openCrate() {
			FullCrate f = (FullCrate)ObjectHandler.getObjectsByName("FullCrate").get(0);
	//
			f.setRenderPriority(20);
	//		
			f.open();
	}
	
	public static void resetSounds() {
		try {
			s.stop();
		} catch (NullPointerException e) {
		
		}
		s = new SoundPlayer();
	}
	public static SoundPlayer getSoundPlayer () {
		return s;
	}
	
	public static void resetGame () {
		
		
		ArrayList <ArrayList <GameObject>> objs = ObjectHandler.getChildrenByName("GameObject");
		
		for (int i = 0; i < objs.size(); i++) {
			while(!objs.get(i).isEmpty()) {
				objs.get(i).get(0).forget();
			}
		}
		
	
	
		
	}
	
	
	public static void gameLoopFunc () {
		
		ObjectHandler.callAll();
		
		 for (int i = 0; i < askers.size(); i++) {
		    	for (int j = 0; j < askers.get(i).getKeys().size(); j++) {
		    		if (!GameLoop.getInputImage().keyDown(askers.get(i).heldKeys.get(j))) {
		    			askers.get(i).getKeys().remove(j);
		    			j--;
		    		}
		    	}
		    }
		
	}
	
	  public static void removeAsker(GameObject asker) {
		  Asker toAsk = getAsker(asker);
		  askers.remove(toAsk);
	  }
	  
	  public static boolean keyCheck(int keyCode, GameObject whosAsking) {
			boolean returnValue = GameLoop.getInputImage().keyDown(keyCode);
		    
			Asker asking = getAsker(whosAsking);
			
			if (returnValue) {
				
				asking.getKeys().add(keyCode);
			}
			
			
			return returnValue;
		  }
		
		public static Asker getAsker (GameObject whosAsking) {
		
			Asker asking = null;
			
			boolean foundAsker = false;
			
			for (int i = 0; i < askers.size(); i++) {
				if (askers.get(i).isAsker(whosAsking)) {
					asking = askers.get(i);
					foundAsker = true;
					break;
				}
			}
			
			if (!foundAsker) {
				askers.add(new Asker(whosAsking));
				asking = askers.get(askers.size() -1);
			}
			
			return asking;
		}
		  
		  public static boolean keyPressed(int keyCode, GameObject whosAsking) {
			boolean returnValue = GameLoop.getInputImage().keyPressed(keyCode);
			
			Asker asking = getAsker(whosAsking);
			
			if (returnValue && !asking.getKeys().contains(keyCode)) {
				asking.getKeys().add(keyCode);
				return returnValue;
			} else {
				return false;
			}
			
			
		  }
		  
		  public static boolean keyReleased(int keyCode) {
		    return GameLoop.getInputImage().keyReleased(keyCode);
		  }
	
	
	public static void renderFunc () {
		Room.render();
		ObjectHandler.renderAll();
	}
	
	public static void beforeRender() {
		
	}
	
	public static void afterRender()
	{
		if (isScanMode()) {
			green.setX(Room.getViewX());
			green.setY(Room.getViewY());
			
		}
	}
		
	public static int getResolutionX() {
		return RenderLoop.wind.getResolution()[0];
	}
	public static int getResolutionY() {
		return RenderLoop.wind.getResolution()[1];
	}

	
	public static void setScanMode (boolean scanMode) {
		if (scanMode) {
			ObjectHandler.pause(true);
			green.declare();
		} else {
			ObjectHandler.pause(false);
			green.forget();
		}
		isScanMode = scanMode;
	}
	
	public static boolean isScanMode () {
		return isScanMode;
	}
	
}
