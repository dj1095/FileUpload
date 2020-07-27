package com.mindtree.sabre.generatereport.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.sabre.generatereport.entity.Department;

@Repository
public interface DepartmentsRepository extends JpaRepository<Department, Integer> {

}
