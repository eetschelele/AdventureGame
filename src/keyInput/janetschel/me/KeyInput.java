package keyInput.janetschel.me;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import enums.janetschel.me.GameID;
import gameStates.janetschel.me.WelcomeScreen;
import handlingCode.janetschel.me.Handler;
import mainGameCode.janetschel.me.Main;
import mainTexture.janetschel.me.Pictures;
import warnings.janetschel.me.QuitWarning;

public class KeyInput implements KeyListener{
	private boolean[] keyPress = new boolean[2];
	private boolean[] currentlySelected = new boolean[3];
	private boolean isInQuitWarning; 
	private boolean isOnYes;
	
	private WelcomeScreen welcomeScreen;
	public Pictures pic;
	private Handler gameHandler;
	
	private QuitWarning tempWarning;
	
	public KeyInput(Handler handler, Main game, Pictures pic, WelcomeScreen welcomeScreen, Handler gameHandler){
		this.welcomeScreen = welcomeScreen;
		this.pic = pic;
		this.gameHandler = gameHandler;
		
		currentlySelected[0] = true; // Da am Anfang "Start" ausgewählt ist
		currentlySelected[1] = false;
		currentlySelected[2] = false;
		
		/* Benutzt werden hier anstatt der reine KeyInput ein Array um Ghosting zu verhindern
		 * Die Array-Einträge werden auf true gesetzt wenn die Taste gedrückt ist und erst auf false wenn man wieder loslässt
		 */
		keyPress[0] = false; 
		keyPress[1] = false; 
		
		isInQuitWarning = false;
		isOnYes = false;
		
		tempWarning = new QuitWarning(0, 0, GameID.quitWarning, pic);
	}
	
	public void keyPressed(KeyEvent e) {
		int tempKey = e.getKeyCode();
		
		/*
		 * Dieses if-Statement schaut welche Position ausfegwählt ist uns wechselt die dementsprechend
		 * Wenn man bei Quit ganz unten ist, fängt er wieder bei Start ganz oben an
		 */
		if(tempKey == KeyEvent.VK_DOWN){
			if(isInQuitWarning) return;
			if(currentlySelected[0]){
				welcomeScreen.setCurrentSelected("options");
				currentlySelected[0] = false;
				currentlySelected[1] = true;
			}else if(currentlySelected[1]){
				welcomeScreen.setCurrentSelected("quit");
				currentlySelected[1] = false;
				currentlySelected[2] = true;
			}else if(currentlySelected[2]){
				welcomeScreen.setCurrentSelected("start");
				currentlySelected[0] = true;
				currentlySelected[2] = false;
			}
		}
		
		/*
		 * Dieses if-Statement schaut welche Position ausfegwählt ist uns wechselt die dementsprechend
		 * Wenn man bei Start ganz oben ist, fängt er wieder bei Quit ganz unten an
		 */
		if(tempKey == KeyEvent.VK_UP){
			if(isInQuitWarning) return;
			if(currentlySelected[0]){
				welcomeScreen.setCurrentSelected("quit");
				currentlySelected[0] = false;
				currentlySelected[2] = true;
			}else if(currentlySelected[1]){
				welcomeScreen.setCurrentSelected("start");
				currentlySelected[0] = true;
				currentlySelected[1] = false;
			}else if(currentlySelected[2]){
				welcomeScreen.setCurrentSelected("options");
				currentlySelected[1] = true;
				currentlySelected[2] = false;
			}
		}
		
		if(tempKey == KeyEvent.VK_LEFT){
			if(!isInQuitWarning) return;
			tempWarning.setCurrentSelected("yes");
			isOnYes = true;
		}
		if(tempKey == KeyEvent.VK_RIGHT){
			if(!isInQuitWarning) return;
			tempWarning.setCurrentSelected("no");
			isOnYes = false;
		}
		
		 //Diese if-Statement registriert die Enter-Tasten und leitet die dementsprechend weiter
		if(tempKey == KeyEvent.VK_ENTER){
			if(currentlySelected[0]);
			if(currentlySelected[1]);
			if(currentlySelected[2]){
				if(isInQuitWarning){
					if(!isOnYes){
						tempWarning.lifeSpan = 0;
						gameHandler.removeObject(tempWarning);
						isInQuitWarning = false;
						return;
					}else if(isOnYes) System.exit(0);
				}
				isInQuitWarning = true;
				gameHandler.addObject(tempWarning);
			}
		}
		 
		
	}
	
	public void keyReleased(KeyEvent e) {
		int tempKey = e.getKeyCode();
		
	}
	
	// Methode wird nicht gebraucht
	public void keyTyped(KeyEvent e) {}
}
