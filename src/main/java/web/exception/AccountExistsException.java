package web.exception;

public class AccountExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AccountExistsException(String username) {
		super("Username:" +username +" already exists");
	}
}
