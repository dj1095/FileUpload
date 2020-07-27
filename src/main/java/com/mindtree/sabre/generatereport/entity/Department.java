package com.mindtree.sabre.generatereport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Department")
@Data
public class Department {

	/**
	 * depId.
	 */
	@Id
	@Column(name="DEPT_ID")
	private int id;
	/**
	 * deptName.
	 */
	@Column(name = "DEPT_NAME")
	private String deptName;
}
