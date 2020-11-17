package web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import web.exception.EmployeeExistsException;


@ControllerAdvice
public class EmployeeExistsAdvice {
	@ResponseBody
	@ExceptionHandler(EmployeeExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String employeeExistsHandler(EmployeeExistsException ex) {
		return ex.getMessage();
	}
}
