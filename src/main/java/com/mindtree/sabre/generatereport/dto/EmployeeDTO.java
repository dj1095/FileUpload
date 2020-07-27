package com.mindtree.sabre.generatereport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {	
	/**
	 * id.
	 */
	private Integer id;
	/**
	 * name.
	 */
	private String name;
	/**
	 * department.
	 */
	private String department;

}
