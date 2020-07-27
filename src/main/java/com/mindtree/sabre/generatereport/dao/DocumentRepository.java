package com.mindtree.sabre.generatereport.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.sabre.generatereport.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
