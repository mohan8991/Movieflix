package io.egen.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.FORBIDDEN)
public class PasswordNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 1821508901004575647L;

	public PasswordNotMatchException(String message) {
		super(message);
	}

	public PasswordNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

}
