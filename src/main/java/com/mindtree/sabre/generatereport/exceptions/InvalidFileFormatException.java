/**
 * 
 */
package com.mindtree.sabre.generatereport.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author M1044441
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFileFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidFileFormatException() {
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidFileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidFileFormatException(String invalidFileFormat) {
		super(invalidFileFormat);
	}


}
