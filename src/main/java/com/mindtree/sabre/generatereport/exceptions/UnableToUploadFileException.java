package com.mindtree.sabre.generatereport.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableToUploadFileException extends Exception  {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public UnableToUploadFileException() {
		super();
	}

	public UnableToUploadFileException(String message) {
		super(message);
	}

}
