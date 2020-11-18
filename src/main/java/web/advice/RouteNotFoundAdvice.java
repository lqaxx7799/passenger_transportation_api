package web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import web.exception.RouteNotFoundException;

@ControllerAdvice
public class RouteNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(RouteNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String routeNotFoundHandler(RouteNotFoundException ex) {
		return ex.getMessage();
	}
}
