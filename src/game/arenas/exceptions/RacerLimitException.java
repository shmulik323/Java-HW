package game.arenas.exceptions;

public class RacerLimitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RacerLimitException() {
		
	}

	public RacerLimitException(String arg0) {
		super(arg0);
		
	}

	public RacerLimitException(Throwable cause) {
		super(cause);
		
	}

	public RacerLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public RacerLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
