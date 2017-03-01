/*
 * @author: Jan Etschel
 * Ein kleines Indie-Game namens AAO für den Informatik Unterricht
 * 
 * Note: Auch die Sprites (die Bilder) wurden alle selber mit PAINT.NET gepixelt
 */

package mainGameCode.janetschel.me;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import enums.janetschel.me.GameID;
import enums.janetschel.me.GameState;
import exceptions.janetschel.me.MainGameThreadException;
import gameStates.janetschel.me.WelcomeScreen;
import handlingCode.janetschel.me.Handler;
import keyInput.janetschel.me.KeyInput;
import mainTexture.janetschel.me.Pictures;

/*
 * Main Klasse
 * Von hier passieren alle Aufrufe der Unterklassen
 */
public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = 3953283979622766071L;
	public static final float WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;
	
	private Thread mainGameThread;
	private Handler gameHandler;
	private Pictures pic;
	
	public boolean running = false;
	public GameState states;
	public WelcomeScreen welcomeScreen;

	/*
	 * Main Methode
	 * Initialisiert einfach ein neues Objekt der Klasse
	 */
	public static void main(String[] args){
		new Main();
	}
	
	/*
	 * Konstruktor
	 * Setzt alle wichtigen Werte die zum Start gebraucht werden
	 */
	public Main(){
		// Als erstes werden versucht die Bilder zu laden -> ohne Bilder kein Spiel
		pic = new Pictures();
		try{
			Thread tempGameThread = new Thread((Runnable) pic);
			tempGameThread.wait();
		}catch(Exception e){
			// Exception wird in der Pictures Klasse genauer behandelt
		}
		
		// Initialisiern von wichtigen Komponenten
		gameHandler = new Handler(pic);
		welcomeScreen = new WelcomeScreen(0, 0, GameID.welcomeScreen, pic);
		
		// Fügt den KeyListener hinzu
		this.addKeyListener(new KeyInput(gameHandler, this, pic, welcomeScreen, gameHandler));
		
		// Erzeugt ein neues Window (JFrame) und versucht so lange Focus zu bekommen, bis es Focus hat
		new Window(WIDTH, HEIGHT, "An Awesome Office - The Game", this, pic);
		while(!this.hasFocus()) this.requestFocus();
		states = GameState.mainMenu;
		
		// Erzeugt als erstes eine Wilkommens-Nachricht
		gameHandler.addObject(welcomeScreen);
	}
	
	/*
	 * Start Methode
	 * Startet den Thread und somit das Spiel indem sie die Main Methode den Thread übergibt
	 * Nicht die beste Lösung aber solang man kein alsozu großen Multi-Threading betreibt führt dies zu keinem Problem
	 */
	public void start(){
		mainGameThread = new Thread(this);
		mainGameThread.start();
		running = true;
	}
	
	/*
	 * Stop Methode
	 * Dient dazu den Thread zu schließen und damit das Spiel ohne Meldungen oder sonstiges zu beenden
	 */
	public void stop() throws MainGameThreadException, InterruptedException{
		mainGameThread.join();
		running = false;
	}
	
	/*
	 * Tick Methode
	 * Dient dazu die Logik hinter dem Programm aufrecht zu erhalten, anders als die Render Methode
	 */
	private void tick(){
		gameHandler.tick();
	}
	
	/*
	 * Render Methode
	 * Dient zum rendern der Sprites (der Bilder) mithilfe der BufferStrategy
	 * Verwendet wird hier eine Anzahl von 3, welche durch viele Test als bestes Ergebnise bewiesen wurde
	 */
	private void render(){
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if (bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bufferStrategy.getDrawGraphics();
		gameHandler.render(g);
		
		g.dispose();
		bufferStrategy.show();
	}
	
	/*
	 * Game Loop Methode
	 * Irgendwelche Mathematik schafft hier, das Spiel flüssig laufen zu lassen
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
			
		while(running) {
			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}

			if(running) render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(frames +"fps, " + states);
				frames = 0;
			}
		}
		try {
			stop();
		}catch(MainGameThreadException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
