package web.exception;

public class AccountNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AccountNotFoundException(int id) {
		super("Could not find account " + id);
	}
}
