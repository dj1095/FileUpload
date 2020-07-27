package com.mindtree.sabre.generatereport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author M1044441
 *
 */
@Entity
@Table(name="Name")
@Data
public class Name {
	/**
	 * id.
	 */
	@Id
	@Column(name="Emp_ID")
	private int id;
	/**
	 * name.
	 */
	@Column(name = "Name")
	private String name;

}
