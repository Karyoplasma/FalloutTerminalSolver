package core;

public class WordLengthException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3425252279502668416L;
	
	public WordLengthException() {
		super("The length of the entered word does not match with the words existing in the wordlist!");
	}

}
