package web.exception;

public class CoachNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CoachNotFoundException(int id) {
		super("Could not find coach " + id);
	}
}
