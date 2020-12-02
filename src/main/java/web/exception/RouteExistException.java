package web.exception;

public class RouteExistException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public RouteExistException(String route) {
		super("this route: "+route+" already exist");
	}
}
