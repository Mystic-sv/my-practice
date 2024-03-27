package com.techymeet.mytodo.dao;

import java.util.List;
import java.util.Optional;

import com.techymeet.mytodo.vo.Employee;
import com.techymeet.mytodo.vo.Login;
import com.techymeet.mytodo.vo.Role;

public interface UserDao {

	Optional<Login> findByUsername(String username);

	Role getRoleidByName(String string);

	Employee registerEmployee(Employee employee);

	boolean findByEmail(String email);

	List<Employee> getemployeeList();

	Employee getEmployeeById(int id) throws Exception;

	Employee updateEmployee(Employee employee);

	boolean deleteEmployeeById(int id);

}
