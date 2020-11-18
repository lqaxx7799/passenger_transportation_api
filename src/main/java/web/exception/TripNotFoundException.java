package web.exception;

public class TripNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TripNotFoundException(int id) {
		super("Could not find trip " + id);
	}
}
