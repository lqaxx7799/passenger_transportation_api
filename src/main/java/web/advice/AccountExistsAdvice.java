package web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import web.exception.AccountExistsException;


@ControllerAdvice
public class AccountExistsAdvice {
	@ResponseBody
	@ExceptionHandler(AccountExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String accountExistsHandler(AccountExistsException ex) {
		return ex.getMessage();
	}
}
