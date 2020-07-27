package com.mindtree.sabre.generatereport.controller.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.mindtree.sabre.generatereport.dto.EmployeesList;
import com.mindtree.sabre.generatereport.dto.ReportUploadResponse;
import com.mindtree.sabre.generatereport.entity.Document;

import lombok.NonNull;

public interface ReportsService {

	/**
	 * @param file , never null
	 * @return null, if value not found
	 * @throws IOException 
	 */
	public ReportUploadResponse upLoad(MultipartFile file) throws IOException;
	/**
	 * @param file ,never null
	 * @throws IOException 
	 */
	public void save(MultipartFile file) throws IOException;
	/**
	 * @return list of Employees, [] if not found.
	 */
	public EmployeesList retrieve();
	/**
	 * @param file , never null
	 * @return , document
	 * @throws IOException 
	 */
	public Document storeFile(@NonNull MultipartFile file) throws IOException;
	/**
	 * @param fileId ,never null
	 * @return document
	 */
	public Optional<Document> retrieveFile(@NonNull Integer fileId);
}
