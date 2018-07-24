/**
 * Thrown when hexagon neighbour range is exceeded (must be 0-5 inclusive)
 * @author hasanahmed
 *
 */
public class InvalidNeighbourIndexException extends ArrayIndexOutOfBoundsException {
	public InvalidNeighbourIndexException(int i) {
		super ("Invalid index: " +i +", must be 0-5 inclusive");
	}
}
