package mainGameCode.janetschel.me;

import java.awt.Graphics;
import java.awt.Rectangle;
import enums.janetschel.me.GameID;

public abstract class GameObject {
	protected float x, y, speedX, speedY;
	protected GameID id;
	
	protected GameObject(float x, float y, GameID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	// Setter Methoden für alle Unterklassen
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	public void setSpeedX(float speedX){
		this.speedX = speedX;
	}
	public void setSpeedY(float speedY){
		this.speedY = speedY;
	}
	public void setID(GameID id){
		this.id = id;
	}
	
	// Getter Methoden für alle Unterklassen
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public float getSpeedX(){
		return speedX;
	}
	public float getSpeedY(){
		return speedY;
	}
	public GameID getID(){
		return id;
	}
}
