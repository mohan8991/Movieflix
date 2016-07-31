package io.egen.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class starsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2390119777778095369L;	
	
	public starsNotFoundException(String message) {
		super(message);
	}

	public starsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}


}
