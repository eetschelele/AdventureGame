package exceptions.janetschel.me;


/*
 * mainGameThreadException
 * Wird gethrowt wenn aus irgendeinen Grund der Haupt-Thread nicht beendet werden kann
 */
public class MainGameThreadException extends Exception{
	private static final long serialVersionUID = -2976528865225784876L;

	public MainGameThreadException(){}
	
	public MainGameThreadException(String exceptionMessage){
		super(exceptionMessage);
	}
}
