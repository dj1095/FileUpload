package com.mindtree.sabre.generatereport.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
	/**
	 * timeStamp.
	 */
	private Date timeStamp;
	/**
	 * message.
	 */
	private String message;
	/**
	 * details.
	 */
	private String details;
}
