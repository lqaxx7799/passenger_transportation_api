package web.exception;

public class EmployeeExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EmployeeExistsException(String identificationNumber) {
		super("Employee with identification number: "+ identificationNumber + " already exists");
	}
}
