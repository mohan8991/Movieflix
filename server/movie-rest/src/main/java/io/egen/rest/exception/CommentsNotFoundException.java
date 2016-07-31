package io.egen.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class CommentsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2519327283818807522L;

	public CommentsNotFoundException(String message) {
		super(message);
	}

	public CommentsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
