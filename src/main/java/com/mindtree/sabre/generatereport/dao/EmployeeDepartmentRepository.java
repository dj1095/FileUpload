package com.mindtree.sabre.generatereport.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.sabre.generatereport.entity.EmployeeDepartment;

@Repository
public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment, Integer> {

	Optional<EmployeeDepartment> findByEmpId(int id);

}
