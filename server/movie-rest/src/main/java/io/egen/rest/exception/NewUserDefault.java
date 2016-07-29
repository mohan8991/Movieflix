package io.egen.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.FORBIDDEN)
public class NewUserDefault extends RuntimeException {
	
		private static final long serialVersionUID = 2946097818531922448L;

	public NewUserDefault(String message) {
		super(message);
	}

	public NewUserDefault(String message, Throwable cause) {
		super(message, cause);
	}

}
