package com.jrp.pma.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.EmployeeRepository;

@Service
public class EmployeeServiseOLD {
	
	//Field Injection
//	@Qualifier("staffRepositoryImpl1")
//	@Autowired
	IStaffRepository empRepo;
	
	

	// Constructor Injection
//	public EmployeeServise(IStaffRepository empRepo) {
//		super();
//		this.empRepo = empRepo;
//	}
	
	// Setter Injection
//	@Autowired
//	public void setEmpRepo(EmployeeRepository empRepo) {
//		this.empRepo = empRepo;
//	}
	
}
