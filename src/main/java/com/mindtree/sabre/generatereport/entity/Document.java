package com.mindtree.sabre.generatereport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="docs")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Document {

	/**
	 * id.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DOCUMENT_ID")
	private Integer id;
	/**
	 * docName.
	 */
	@NonNull
	@Column(name="DOCUMENT_NAME")
	private String docName;
	/**
	 * docType.
	 */
	@NonNull
	@Column (name="DOCUMENT_TYPE")
	private String docType;
	
	@Lob
	@Column(name="DATA")
	@NonNull
	private byte[] data;
}
