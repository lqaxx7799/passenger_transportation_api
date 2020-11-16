package web.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import web.exception.AccountNotFoundException;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class AccountNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(AccountNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String accountNotFoundHandler(AccountNotFoundException ex) {
		return ex.getMessage();
	}
}
