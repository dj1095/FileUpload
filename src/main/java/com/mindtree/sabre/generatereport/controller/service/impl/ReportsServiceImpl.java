package com.mindtree.sabre.generatereport.controller.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.sabre.generatereport.controller.service.ReportsService;
import com.mindtree.sabre.generatereport.dao.DepartmentsRepository;
import com.mindtree.sabre.generatereport.dao.DocumentRepository;
import com.mindtree.sabre.generatereport.dao.EmployeeDepartmentRepository;
import com.mindtree.sabre.generatereport.dao.NamesRepository;
import com.mindtree.sabre.generatereport.dto.EmployeeDTO;
import com.mindtree.sabre.generatereport.dto.EmployeesList;
import com.mindtree.sabre.generatereport.dto.ReportUploadResponse;
import com.mindtree.sabre.generatereport.entity.Department;
import com.mindtree.sabre.generatereport.entity.Document;
import com.mindtree.sabre.generatereport.entity.EmployeeDepartment;
import com.mindtree.sabre.generatereport.entity.Name;
import com.mindtree.sabre.generatereport.enums.FileUploadStatusEnum;
import com.mindtree.sabre.generatereport.helper.ExcelHelper;

import lombok.NonNull;

/**
 * @author M1044441
 *
 */
@Service
public class ReportsServiceImpl implements ReportsService {

	/**
	 * LOGGER.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(ExcelHelper.class);
	/**
	 * namesRepo.
	 */
	@Autowired
	private NamesRepository namesRepo;
	/**
	 * departmentsRepo.
	 */
	@Autowired
	private DepartmentsRepository departmentsRepo;
	/**
	 * empDeptRepo.
	 */
	@Autowired
	private EmployeeDepartmentRepository empDeptRepo;
	/**
	 * docRepo.
	 */
	@Autowired
	private DocumentRepository docRepo;
	/**
	 * path.
	 */
	@Value("${reports.path}")
	private String path;

	/**
	 * @param file , never null
	 * @throws IOException
	 */
	@Override
	public ReportUploadResponse upLoad(@NonNull MultipartFile file) throws IOException {
		LOGGER.info("Entered into upLoad method with param {0} in class ReportsServiceImpl", new Object[] { file });
		ReportUploadResponse response = null;
		File fileLocation = new File(path + file.getOriginalFilename());
		FileOutputStream out = new FileOutputStream(fileLocation);
		try {
			out.write(file.getBytes());
			response = ReportUploadResponse.builder().fileName(file.getOriginalFilename())
					.fileUploadStatus(FileUploadStatusEnum.SUCCESSFUL.toString()).build();
		} finally {
			out.flush();
			out.close();
		}
		LOGGER.info("Exited from upLoad method in class ReportsServiceImpl");
		return response;
	}

	@Override
	public void save(MultipartFile file) throws IOException {
		LOGGER.info("Entered into save method with param {0} in class ReportsServiceImpl", new Object[] { file });
		File fileLocation = new File(path + file.getOriginalFilename());
		FileInputStream in = new FileInputStream(fileLocation);
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(in);
			for (Sheet sheet : workbook) {
				switch (sheet.getSheetName()) {
				case "Name":
					List<Name> names = ExcelHelper.excleToNames(sheet);
					if (!names.isEmpty()) {
						namesRepo.saveAll(names);
					}
					break;
				case "EMP_DEP":
					List<EmployeeDepartment> empDepts = ExcelHelper.excelToEmployeeDepartment(sheet);
					if (!empDepts.isEmpty()) {
						empDeptRepo.saveAll(empDepts);
					}
					break;
				case "Department":
					List<Department> departments = ExcelHelper.excleToDepartments(sheet);
					if (!departments.isEmpty()) {
						departmentsRepo.saveAll(departments);
					}
					break;
				default:
					LOGGER.warn("Sheet Name Not Found:" + sheet.getSheetName());

				}
			}
		} finally {
			workbook.close();
			LOGGER.info("Exited from save method in class ReportsServiceImpl");
		}

	}

	@Override
	public EmployeesList retrieve() {
		LOGGER.info("Entered into retrieve method with no params in class ReportsServiceImpl");
		List<Name> employeeNames = namesRepo.findAll();
		List<EmployeeDTO> employeesData = new ArrayList<EmployeeDTO>();
		EmployeesList empList = new EmployeesList();
		employeeNames.forEach((employeeName) -> {
			EmployeeDTO employee = new EmployeeDTO();
			employee.setId(employeeName.getId());
			employee.setName(employeeName.getName());
			Optional<EmployeeDepartment> empDept = empDeptRepo.findByEmpId(employeeName.getId());
			if (empDept.isPresent()) {
				EmployeeDepartment employeeDepartment = empDept.get();
				Optional<Department> dept = departmentsRepo.findById(employeeDepartment.getDepId());
				if (dept.isPresent()) {
					Department department = dept.get();
					employee.setDepartment(department.getDeptName());
				}
			}
			employeesData.add(employee);
		});
		empList.setEmployees(employeesData);
		LOGGER.info("Exited from retrieve method in class ReportsServiceImpl");
		return empList;
	}

	@Override
	public Document storeFile(@NonNull MultipartFile file) throws IOException {
		LOGGER.info("Entered into storeFile method with param {0} in class ReportsServiceImpl", new Object[] { file });
		String docName = file.getOriginalFilename();
		Document doc = new Document(docName, file.getContentType(), file.getBytes());
		LOGGER.info("Exited from storeFile method in class ReportsServiceImpl");
		return docRepo.save(doc);
	}

	@Override
	public Optional<Document> retrieveFile(@NonNull Integer fileId) {
		LOGGER.info("Entered into retrieveFile method with param {0} in class ReportsServiceImpl",
				new Object[] { fileId });
		Optional<Document> doc = docRepo.findById(fileId);
		LOGGER.info("Exited from retrieveFile method in class ReportsServiceImpl");
		return doc;
	}
}
