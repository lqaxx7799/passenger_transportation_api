package web.exception;

public class RouteNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 2L;

	public RouteNotFoundException(int id) {
		super("Could not find route " + id);
	}
}
