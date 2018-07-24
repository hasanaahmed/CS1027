/**
 * Thrown when Labyrinth file has a character which the program has not
 * identified as an appropriate parameter to utilize 
 * @author sahme339
 *
 */
public class UnknownLabyrinthCharacterException extends RuntimeException {
	public UnknownLabyrinthCharacterException (char token) {
		super("Unknown labyrinth character: " + token);
	}
}
