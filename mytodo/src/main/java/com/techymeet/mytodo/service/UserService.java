package com.techymeet.mytodo.service;

import java.util.List;
import java.util.Optional;

import com.techymeet.mytodo.bo.EmployeeBO;
import com.techymeet.mytodo.vo.Login;

public interface UserService {

	

	Optional<Login> findByUsername(String username);

	EmployeeBO registerEmployee(EmployeeBO employee);

	boolean findByEmail(String email);

	List<EmployeeBO> getemployeeList();

	EmployeeBO getEmployeeById(int id);

	EmployeeBO updateEmployee(EmployeeBO employee);

	boolean deleteEmployeeById(int id);

}
