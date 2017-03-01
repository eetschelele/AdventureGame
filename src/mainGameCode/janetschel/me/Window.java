package mainGameCode.janetschel.me;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

import mainTexture.janetschel.me.Pictures;

public class Window extends Canvas{
	private static final long serialVersionUID = -8344092821267101735L;
	public Pictures pic;
	
	/*
	 * Konstruktor
	 * Erstellt einen neuen JFrame mit den eingegebenen Eingangsparametern
	 */
	public Window(float width, float height, String title, Main game, Pictures pic){
		this.pic = pic;
		
		JFrame frame = new JFrame(title);
		Dimension dim = new Dimension((int)width, (int)height);
		
		frame.setPreferredSize(dim);
		frame.setMinimumSize(dim);
		frame.setMaximumSize(dim);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setIconImage(pic.imgIcon);
		
		game.start();
	}
}

