package io.egen.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.FORBIDDEN)
public class NoAuthHeaderFound extends RuntimeException {

	private static final long serialVersionUID = -6495933696035453718L;

		public NoAuthHeaderFound(String message) {
			super(message);
		}

		public NoAuthHeaderFound(String message, Throwable cause) {
			super(message, cause);
		}
}
