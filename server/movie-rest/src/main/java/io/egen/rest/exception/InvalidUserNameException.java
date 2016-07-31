package io.egen.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.FORBIDDEN)
public class InvalidUserNameException extends RuntimeException {

	private static final long serialVersionUID = 8732777197574910756L;

	public InvalidUserNameException(String message) {
		super(message);
	}

	public InvalidUserNameException(String message, Throwable cause) {
		super(message, cause);
	}

}
