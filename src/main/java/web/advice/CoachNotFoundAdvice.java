package web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import web.exception.CoachNotFoundException;

@ControllerAdvice
public class CoachNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(CoachNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String coachNotFoundHandler(CoachNotFoundException ex) {
		return ex.getMessage();
	}
}
