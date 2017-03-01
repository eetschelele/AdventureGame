package handlingCode.janetschel.me;

import java.awt.Graphics;
import java.util.ArrayList;

import mainGameCode.janetschel.me.GameObject;
import mainTexture.janetschel.me.Pictures;

/*
 * Handler Klasse
 * Anstatt alle einzelnen Objekte in der Main Klasse zu rendern, werden alle hier mithilfe einer for-Schleife gerendert.
 * Acuh wird die tick-Methode für jedes Objekt ausgeführt
 */
public class Handler {
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public Pictures pic;
	
	public Handler(Pictures pic){
		this.pic = pic;
	}
	
	public void tick(){
		for(int i = 0; i < objects.size(); i++){
			GameObject currentObject = objects.get(i);
			currentObject.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < objects.size(); i++){
			GameObject currentObject = objects.get(i);
			currentObject.render(g);
		}
	}
	
	public void addObject(GameObject object){
		objects.add(object);
	}
	
	public void removeObject(GameObject object){
		objects.remove(object);
	}
}
