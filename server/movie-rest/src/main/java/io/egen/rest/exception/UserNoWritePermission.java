package io.egen.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.FORBIDDEN)
public class UserNoWritePermission extends RuntimeException {

	private static final long serialVersionUID = 8231032937004562544L;
	
	public UserNoWritePermission(String message){
		super(message);
	}
	
	public UserNoWritePermission(String message, Throwable cause){
		super(message, cause);
	}

}
