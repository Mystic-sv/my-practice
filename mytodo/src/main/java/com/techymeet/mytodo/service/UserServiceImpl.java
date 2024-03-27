package com.techymeet.mytodo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techymeet.mytodo.bo.EmployeeBO;
import com.techymeet.mytodo.dao.UserDao;
import com.techymeet.mytodo.vo.Employee;
import com.techymeet.mytodo.vo.Login;
import com.techymeet.mytodo.vo.Role;
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public Optional<Login> findByUsername(String username) {
		
		return userDao.findByUsername(username);
	}
	@Override
	public EmployeeBO registerEmployee(EmployeeBO employeeBO) {
		  Employee employee=new Employee();
		  try {
		  BeanUtils.copyProperties(employeeBO, employee);
		  Login login=new Login();
		  login.setUserName(employeeBO.getEmailId());
		  login.setPassword(passwordEncoder.encode(employeeBO.getPassword()));
		  Role role=new Role();
		  role=userDao.getRoleidByName("ROLE_EMPLOYEE");
		  login.setRole(role);
		  login.setEmployee(employee);
		  employee.setLogin(login);
		  employee=userDao.registerEmployee(employee);
		  if(null!=employee&&employee.getEmployeeId()>0) {
			  employeeBO.setEmployeeId(employee.getEmployeeId());
			  return employeeBO;
		  }
		  
		  
		  }catch (Exception e) {
			e.printStackTrace();
		}
		  
		return employeeBO;
	}
	@Override
	public boolean findByEmail(String email) {
		
		return userDao.findByEmail(email);
	}
	@Override
	public List<EmployeeBO> getemployeeList() {
		List<Employee> employeeList =new ArrayList<>();
		List<EmployeeBO> employeeBOList =new ArrayList<>();
		try {
		employeeList=userDao.getemployeeList();
		if(null!=employeeList) {
			for(Employee employee:employeeList) {
				EmployeeBO employeeBO=new EmployeeBO();
				BeanUtils.copyProperties(employee, employeeBO);
				employeeBOList.add(employeeBO);
			}
			return employeeBOList;
			
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public EmployeeBO getEmployeeById(int id) {
		EmployeeBO employeeBO=new EmployeeBO();
		 try {
			 Employee employee=userDao.getEmployeeById(id);
			 if(null!=employee) {
				 BeanUtils.copyProperties(employee, employeeBO);
				 return employeeBO;
			 }
			 
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
				return employeeBO;
	}
	@Override
	public EmployeeBO updateEmployee(EmployeeBO employeeBO) {
		
		try {
			 Employee employee=userDao.getEmployeeById(employeeBO.getEmployeeId());
			 if(null!=employee) {
				 BeanUtils.copyProperties(employeeBO, employee);
				 employee.getLogin().setUserName(employeeBO.getEmailId());
				 employee.getLogin().setPassword(passwordEncoder.encode(employeeBO.getPassword()));
				 employee=userDao.updateEmployee(employee);
				 if(null!=employee&&employee.getEmployeeId()>0) {
				 return employeeBO;
				 }
			 }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean deleteEmployeeById(int id) {
		
		return userDao.deleteEmployeeById(id);
	}

}
