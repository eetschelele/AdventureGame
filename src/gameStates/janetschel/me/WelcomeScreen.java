package gameStates.janetschel.me;

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

/*
 * WelcomeScreen Klasse
 * Diese Klasse zeichnet alle Sprites (Bilder) die für das Menü nötig sind
 * Das alles in einer seperaten Klasse zu schreiben verhindert dass die Main Klasse mit zu viel Code vollgestopft ist
 */
public class WelcomeScreen extends GameObject{
	private Pictures pic;
	private float xLeftCloud , xRightCloud;
	private int lifeSpan, lifeSpanMenuPoints, speedX;
	
	public BufferedImage currentStart, currentOptions, currentQuit;

	public WelcomeScreen(float x, float y, GameID id, Pictures pic) {
		super(x, y, id);
		this.pic = pic;
		this.x = x;
		
		lifeSpan = 0;
		lifeSpanMenuPoints = 0;
		xRightCloud = Main.WIDTH - pic.imgCloud.getWidth();
		speedX = 3;
		
		currentStart = pic.imgStartSelected;
		currentOptions = pic.imgOptionsNotSelected;
		currentQuit = pic.imgQuitNotSelected;
	}

	public void tick() {
		if(lifeSpan < (100 / 1.3) - 1) lifeSpan++;
		if(xLeftCloud < (int)Main.WIDTH/2 - 350) xLeftCloud+=speedX;
		if(xRightCloud > (int)Main.WIDTH/2 - 30) xRightCloud-=speedX;
	}

	/*
	 * Render Methode
	 * Hier wird als erstes die Methode aufgerufen die dafür zuständig ist, den Titel und die Wolken hereinfliegen zu lassen
	 * Danach werden die Menüpunkte animiert
	 * 
	 * (non-Javadoc)
	 * @see mainGameCode.janetschel.me.GameObject#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		boolean moveOn = animateTitleAndClouds(g);
		if(moveOn) animateMenu(g);
	}

	/*
	 * In dieser Methode wird erst das Rechteck gezeichnet, da dieses nicht hereinfaden soll (1)
	 * Danach wird einen neue Variable erstellt, mit der es mir möglich ist, jedes Objekt langsam erscheinen zu lassen (2)
	 * Am Ende der Methode wird das AlphaComposite wieder auf 1 gesetzt, also 100 Prozent, um nicht mit anderen Render Methoden in Konflikt zu geraten (3)
	 */
	private boolean animateTitleAndClouds(Graphics g){
		// 1
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, (int)Main.WIDTH, (int)Main.HEIGHT);
				
		// 2
		float lifeSpanOpacity = BufferedImage.TYPE_INT_ARGB;
		lifeSpanOpacity = (float) ((float)lifeSpan * 1.3 / 100);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, lifeSpanOpacity));
			
			g2d.drawImage(pic.imgCloud, (int)xLeftCloud, (int)Main.HEIGHT/2 - (pic.imgTitle.getHeight() * 2) - 10, null);
			g2d.drawImage(pic.imgCloud, (int)xRightCloud, (int)Main.HEIGHT/2 - pic.imgTitle.getHeight()*3, null);
			g2d.drawImage(pic.imgTitle, (int)Main.WIDTH/2 - 225, (int)Main.HEIGHT/2 - pic.imgTitle.getHeight()*2, 225*2, 90*2, null);
				
		// 3
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
		return (lifeSpan >= (100 / 1.3) -1);
	}
	
	/*
	 * Diese Methode animiert die Unterpunkte im Menü
	 * Wie auch in der animateTitleAndCloud Methode mithilfe von AlphaComposite
	 */
	private void animateMenu(Graphics g){
		// Diesmal der "Countdown" in der Methode der Einfachkeit halber
		if(lifeSpanMenuPoints < 300) lifeSpanMenuPoints++;
		
		// 2
		float lifeSpanOpacity = BufferedImage.TYPE_INT_ARGB;
		lifeSpanOpacity = (float) ((float)lifeSpanMenuPoints / 300);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, lifeSpanOpacity));
		
			g2d.drawImage(currentStart, (int)Main.WIDTH/2 - 112/2, (int)Main.HEIGHT/2 + 75, 112, 18, null);
			g2d.drawImage(currentOptions, (int)Main.WIDTH/2 - 132/2, (int)Main.HEIGHT/2 + 110, 132, 18, null);
			g2d.drawImage(currentQuit, (int)Main.WIDTH/2 - 90/2, (int)Main.HEIGHT/2 + 145, 90, 18, null);
			
		// 3
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
	
	/*
	 * Diese Methode erlaubt im Zusammenhang mit dem KeyListener das Auswählen der Menüpunkte
	 */
	public void setCurrentSelected(String newSelected){
		switch(newSelected){
		case "start":
			currentStart = pic.imgStartSelected;
			currentOptions = pic.imgOptionsNotSelected;
			currentQuit = pic.imgQuitNotSelected;
			break;
		case "options":
			currentStart = pic.imgStartNotSelected;
			currentOptions = pic.imgOptionsSelected;
			currentQuit = pic.imgQuitNotSelected;
			break;
		case "quit":
			currentStart = pic.imgStartNotSelected;
			currentOptions = pic.imgOptionsNotSelected;
			currentQuit = pic.imgQuitSelected;
			break;
		}
	}
	
	// Returnt null, weil es hier nicht gebraucht wird
	public Rectangle getBounds() {
		return null;
	}
}
