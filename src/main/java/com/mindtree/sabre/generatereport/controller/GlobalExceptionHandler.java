package com.mindtree.sabre.generatereport.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.sabre.generatereport.dto.ExceptionResponse;
import com.mindtree.sabre.generatereport.exceptions.InvalidFileFormatException;
import com.mindtree.sabre.generatereport.exceptions.UnableToUploadFileException;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * LOGGER.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	/**
	 * @param ex ,never null.
	 * @param request , never null.
	 * @return response
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		LOGGER.error(ex.getMessage(), ex);
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidFileFormatException.class)
	public final ResponseEntity<Object> handlefileFormatException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		LOGGER.warn(ex.getMessage(), ex);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnableToUploadFileException.class)
	public final ResponseEntity<Object> handleUploadFailedException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		LOGGER.error(ex.getMessage(), ex);
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public final ResponseEntity<Object> handleUploadLimitExceededException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		LOGGER.error(ex.getMessage(), ex);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
}
