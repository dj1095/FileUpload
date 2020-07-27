package com.mindtree.sabre.generatereport.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.mindtree.sabre.generatereport.entity.Department;
import com.mindtree.sabre.generatereport.entity.EmployeeDepartment;
import com.mindtree.sabre.generatereport.entity.Name;

import lombok.NonNull;

public class ExcelHelper {

	/**
	 * LOGGER.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(ExcelHelper.class);
	/**
	 * path.
	 */
	@Value("${reports.path}")
	private static String path;

	/**
	 * converts excel sheet to list of entities
	 * 
	 * @param sheet , never null.
	 * @return [] , if value not found.
	 */
	public static List<Name> excleToNames(@NonNull Sheet sheet) {
		LOGGER.info("Entered into excleToNames method with param {0} in class ExcelHelper", new Object[] { sheet });
		List<Name> names = new ArrayList<Name>();
		for (Row row : sheet) {
			Name name = new Name();
			if (row.getRowNum() > 0) {
				for (Cell cell : row) {
					switch (cell.getColumnIndex()) {
					case 0:
						if (!Objects.isNull(cell.getNumericCellValue())) {
							name.setId((int) cell.getNumericCellValue());
						}
						break;
					case 1:
						if (!Objects.isNull(cell.getRichStringCellValue())) {
							name.setName(cell.getRichStringCellValue().getString());
						}
						break;
					default:
						LOGGER.warn("Excel Cell Index Out of Bounds");
						break;
					}
					names.add(name);
				}
			}

		}
		LOGGER.info("Exited from excleToNames method in class ExcelHelper");
		return names;
	}

	/**
	 * converts emp_dept sheet to List of entities
	 * 
	 * @param sheet , never null.
	 * @return [] ,if no value found
	 */
	public static List<EmployeeDepartment> excelToEmployeeDepartment(@NonNull Sheet sheet) {
		LOGGER.info("Entered into excelToEmployeeDepartment method with param {0} in class ExcelHelper",
				new Object[] { sheet });
		List<EmployeeDepartment> empDeptlist = new ArrayList<EmployeeDepartment>();
		for (Row row : sheet) {
			EmployeeDepartment empDept = new EmployeeDepartment();
			if (row.getRowNum() > 0) {
				for (Cell cell : row) {
					switch (cell.getColumnIndex()) {
					case 0:
						if (!Objects.isNull(cell.getNumericCellValue())) {
							empDept.setId((int) cell.getNumericCellValue());
						}
						break;
					case 1:
						if (!Objects.isNull(cell.getNumericCellValue())) {
							empDept.setEmpId((int) cell.getNumericCellValue());
						}
						break;
					case 2:
						if (!Objects.isNull(cell.getNumericCellValue())) {
							empDept.setDepId((int) cell.getNumericCellValue());
						}
						break;
					default:
						LOGGER.warn("Excel Cell Index Out of Bounds");
						break;
					}
					empDeptlist.add(empDept);
				}
			}

		}
		LOGGER.info("Exited from excelToEmployeeDepartment method in class ExcelHelper");
		return empDeptlist;
	}
	
	/**
	 * converts excel sheet to list of department entities
	 * @param sheet , never null
	 * @return [] , if value not found
	 */
	public static List<Department> excleToDepartments(@NonNull Sheet sheet) {
		LOGGER.info("Entered into excleToDepartments method with param {0} in class ExcelHelper",
				new Object[] { sheet });
		List<Department> departments = new ArrayList<Department>();
		for (Row row : sheet) {
			Department department = new Department();
			if (row.getRowNum() > 0) {
				for (Cell cell : row) {
					switch (cell.getColumnIndex()) {
					case 0:
						if (!Objects.isNull(cell.getNumericCellValue())) {
							department.setId((int) cell.getNumericCellValue());
						}
						break;
					case 1:
						if (!Objects.isNull(cell.getRichStringCellValue())) {
							department.setDeptName(cell.getRichStringCellValue().getString());
						}
						break;
					default:
						LOGGER.warn("Excel Cell Index Out of Bounds");
						break;
					}
					departments.add(department);
				}
			}
		}
		LOGGER.info("Exited from excleToDepartments method in class ExcelHelper");
		return departments;
	}
	
}
