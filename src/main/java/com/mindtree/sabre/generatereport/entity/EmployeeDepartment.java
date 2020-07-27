package com.mindtree.sabre.generatereport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="EMP_DEP")
@Data
public class EmployeeDepartment {

	/**
	 * empId.
	 */
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="EMP_ID")
	private int empId;
	/**
	 * depId.
	 */
	@Column(name="DEP_ID")
	private int depId;
}
