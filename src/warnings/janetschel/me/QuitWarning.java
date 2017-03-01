package warnings.janetschel.me;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import enums.janetschel.me.GameID;
import mainGameCode.janetschel.me.GameObject;
import mainGameCode.janetschel.me.Main;
import mainTexture.janetschel.me.Pictures;

public class QuitWarning extends GameObject{
	private Pictures pic;
	public int lifeSpan;
	
	public BufferedImage currentYes, currentNo;
	
	public QuitWarning(float x, float y, GameID id, Pictures pic) {
		super(x, y, id);
		this.pic = pic;
		
		lifeSpan = 0;
		
		currentYes = pic.imgQuitWarningYesNotSelected;
		currentNo = pic.imgQuitWarningNoSelected;
	}

	
	public void tick() {
		if(lifeSpan < 100 / 5) lifeSpan++;
	}

	/*
	 * In dieser Render Methode wird nur eine Wolke mit Optionen animiert
	 * Für genauere Erklärung siehe Klasse WelcomeScreen
	 * 
	 * (non-Javadoc)
	 * @see mainGameCode.janetschel.me.GameObject#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		float lifeSpanOpacity = BufferedImage.TYPE_INT_ARGB;
		lifeSpanOpacity = (float) lifeSpan * 5 / 100;
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, lifeSpanOpacity));
		
			g2d.setColor(new Color(0, 0, 0, 170));
			g2d.fillRect(0, 0, (int)Main.WIDTH, (int)Main.HEIGHT);
			g2d.drawImage(pic.imgQuitWarning, (int)Main.WIDTH/2 - 609/2, (int)Main.HEIGHT/2 - 50, 609, 27, null);
			
			g2d.drawImage(currentYes, (int)Main.WIDTH/2 - 84/2 - 50, (int)Main.HEIGHT/2, 84, 18, null);
			g2d.drawImage(currentNo, (int)Main.WIDTH/2 - 66/2 + 50, (int)Main.HEIGHT/2, 66, 18, null);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
	
	/*
	 * Diese Methode ändert basierend auf der Klasse KeyInput die richtigen Sprites (Bilder)
	 * Vergleiche: setCurrentSelected in der Klasse WelcomeScreen
	 */
	public void setCurrentSelected(String newSelected){
		switch(newSelected){
		case "yes":
			currentYes = pic.imgQuitWarningYesSelected;
			currentNo = pic.imgQuitWarningNoNotSelected;
			break;
		case "no":
			currentNo = pic.imgQuitWarningNoSelected;
			currentYes = pic.imgQuitWarningYesNotSelected;
			break;
		}
	}

	// Returnt null, weil es hier nicht gebraucht wird
	public Rectangle getBounds() {
		return null;
	}
}
