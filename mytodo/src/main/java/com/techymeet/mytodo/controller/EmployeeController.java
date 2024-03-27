package com.techymeet.mytodo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techymeet.mytodo.bo.EmployeeBO;
import com.techymeet.mytodo.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
	private UserService userService;
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/v1/employees")
	public ResponseEntity<List<EmployeeBO>> getemployeeList(){
		List<EmployeeBO> employeeList=new ArrayList<>();
		
		  
		try {
			employeeList=userService.getemployeeList();
			if(null==employeeList||employeeList.size()==0||employeeList.isEmpty()) {
				return new ResponseEntity<List<EmployeeBO>>(employeeList, HttpStatus.NOT_FOUND);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 return new ResponseEntity<List<EmployeeBO>>(employeeList, HttpStatus.OK);	
	}
	@GetMapping("/v1/getemployee/{id}")
	public ResponseEntity<EmployeeBO> getEmployeeById(@PathVariable int id){
		EmployeeBO employee=new EmployeeBO();
		try {
			employee=userService.getEmployeeById(id);
			if(null==employee) {
				return new ResponseEntity<EmployeeBO>(employee, HttpStatus.NOT_FOUND);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 return new ResponseEntity<EmployeeBO>(employee, HttpStatus.OK);	
	}
	@PutMapping("/v1/update")
	public ResponseEntity<EmployeeBO> updateEmployee(@RequestBody EmployeeBO employee){
		try {
		
			employee=userService.updateEmployee(employee);
			if(null==employee) {
				return new ResponseEntity<EmployeeBO>(employee, HttpStatus.NOT_MODIFIED);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 return new ResponseEntity<EmployeeBO>(employee, HttpStatus.OK);	
	}
	
	@DeleteMapping("/v1/delete/{id}")
	public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable int id){
		boolean status=false;
		try {
			status=userService.deleteEmployeeById(id);
			if(status) {
				return new ResponseEntity<Boolean>(status, HttpStatus.OK);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 return new ResponseEntity<Boolean>(status, HttpStatus.NOT_FOUND);	
	}
}
