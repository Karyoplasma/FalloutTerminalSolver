package core;

public class WordLengthException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3425252279502668416L;
	
	public WordLengthException(String word) {
		super("The length of the the following token does not match the other candidates: " + word);
	}

}
