package mainTexture.janetschel.me;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pictures {
	public BufferedImage imgTitle, imgIcon, imgCloud;
	public BufferedImage imgStartSelected, imgStartNotSelected, imgOptionsSelected, imgOptionsNotSelected, imgQuitSelected, imgQuitNotSelected;
	public BufferedImage imgQuitWarning, imgQuitWarningYesSelected, imgQuitWarningYesNotSelected, imgQuitWarningNoSelected, imgQuitWarningNoNotSelected;
	
	/*
	 * Konstruktor
	 * Lädt die Bilder aus dem res-Ordner in das Programm damit sie später von allen anderen Klassen benutzt werden können
	 */
	public Pictures(){
		imgTitle = imgIcon = imgCloud = null;
		imgStartSelected = imgStartNotSelected = imgOptionsSelected = imgOptionsNotSelected = imgQuitSelected = imgQuitNotSelected = null;
		imgQuitWarning = imgQuitWarningYesSelected = imgQuitWarningYesNotSelected = imgQuitWarningNoSelected = imgQuitWarningNoNotSelected = null;
		try{
			// Icon
			imgIcon = ImageIO.read(new File("res/title.png"));
			
			// Sprites für den WelcomeScreen
			imgTitle = ImageIO.read(new File("res/title.png"));
			imgCloud = ImageIO.read(new File("res/cloud.png"));
			imgStartSelected = ImageIO.read(new File("res/startSelected.png"));
			imgStartNotSelected = ImageIO.read(new File("res/startNotSelected.png"));
			imgOptionsSelected = ImageIO.read(new File("res/optionsSelected.png"));
			imgOptionsNotSelected = ImageIO.read(new File("res/optionsNotSelected.png"));
			imgQuitSelected = ImageIO.read(new File("res/quitSelected.png"));
			imgQuitNotSelected = ImageIO.read(new File("res/quitNotSelected.png"));
			
			// Sprites für die Quit Warning
			imgQuitWarning = ImageIO.read(new File("res/quitWarning.png"));
			imgQuitWarningYesSelected = ImageIO.read(new File("res/quitWarningYesSelected.png"));
			imgQuitWarningYesNotSelected = ImageIO.read(new File("res/quitWarningYesNotSelected.png"));
			imgQuitWarningNoSelected = ImageIO.read(new File("res/quitWarningNoSelected.png"));
			imgQuitWarningNoNotSelected = ImageIO.read(new File("res/quitWarningNoNotSelected.png"));
			
			
		}catch(IOException e){
			System.out.println("Some Picture wasn't loaded correctly - Try again!");
			// Exception muss weiter nicht behandelt werden
		}
	}
}
