package com.mindtree.sabre.generatereport.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportUploadResponse {
	/**
	 * fileUploadStatus
	 */
	private String fileUploadStatus;
	/**
	 * fileName.
	 */
	private String fileName;
	/**
	 * fileId.
	 */
	private Integer fileId;
	
}
