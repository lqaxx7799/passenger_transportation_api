package web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import web.exception.RouteExistException;

@ControllerAdvice
public class RouteExistAdvice {
	@ResponseBody
	@ExceptionHandler(RouteExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String routeExistHandler(RouteExistException ex) {
		return ex.getMessage();
	}
}
