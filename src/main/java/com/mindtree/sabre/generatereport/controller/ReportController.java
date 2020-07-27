package com.mindtree.sabre.generatereport.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.sabre.generatereport.constants.ExcelReportConstants;
import com.mindtree.sabre.generatereport.controller.service.ReportsService;
import com.mindtree.sabre.generatereport.dto.EmployeesList;
import com.mindtree.sabre.generatereport.dto.ReportUploadResponse;
import com.mindtree.sabre.generatereport.entity.Document;
import com.mindtree.sabre.generatereport.exceptions.InvalidFileFormatException;
import com.mindtree.sabre.generatereport.exceptions.UnableToUploadFileException;

/**
 * @author M1044441
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class ReportController {

	/**
	 * LOGGER.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
	/**
	 * reportsService.
	 */
	@Autowired
	private ReportsService reportsService;

	/**
	 * @param file , never null
	 * @throws UnableToUploadFileException
	 * @throws InvalidFileFormatException
	 */
	@PostMapping("/upload/report")
	public ResponseEntity<ReportUploadResponse> upLoadReport(@RequestParam("file") @NonNull MultipartFile file)
			throws UnableToUploadFileException, InvalidFileFormatException {
		ReportUploadResponse response = null;
		try {
			if (ExcelReportConstants.FILE_TYPE.equalsIgnoreCase(file.getContentType())) {
				response = reportsService.upLoad(file);
				Document document = reportsService.storeFile(file);
				response.setFileId(document.getId());
				reportsService.save(file);
			} else {
				throw new InvalidFileFormatException(ExcelReportConstants.INVALID_FILE_FORMAT);
			}
		} catch (IOException ex) {
			LOGGER.error(ExcelReportConstants.FILE_UPLOAD_FAILED, ex);
			throw new UnableToUploadFileException();
		}
		return ResponseEntity.ok().body(response);
	}

	/**
	 * @return EmployeesList , [] if value not found
	 */
	@GetMapping("/employees/all")
	public EmployeesList retrieveAllEmployees() {
		EmployeesList employees = reportsService.retrieve();
		return employees;
	}
	
	/**
	 * @param fileId
	 * @return ByteArrayResource
	 * @throws FileNotFoundException
	 */
	@GetMapping("/download/employees/data/{fileId}")
	public ResponseEntity<ByteArrayResource> downLoadFile(@PathVariable("fileId")final Integer fileId) throws FileNotFoundException{
		Optional<Document> doc = reportsService.retrieveFile(fileId);
		if(!doc.isPresent()) {
			throw new FileNotFoundException("File Not Found!");
		}
		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(doc.get().getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+doc.get().getDocName()+"\"")
				.body(new ByteArrayResource(doc.get().getData()));
	}

}
