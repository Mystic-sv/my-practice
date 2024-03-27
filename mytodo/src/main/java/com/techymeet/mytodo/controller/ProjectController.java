package com.techymeet.mytodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techymeet.mytodo.bo.EmployeeBO;
import com.techymeet.mytodo.bo.ProjectBO;
import com.techymeet.mytodo.service.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/project/")
public class ProjectController {
    @Autowired
	private ProjectService projectService;
	
	@PostMapping("v1/create")
	public ResponseEntity<ProjectBO> createProject(@RequestBody ProjectBO project){
		
		try {
			project=projectService.createProject(project);
			if(null==project) {
				return new ResponseEntity<ProjectBO>(project, HttpStatus.EXPECTATION_FAILED);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 return new ResponseEntity<ProjectBO>(project, HttpStatus.CREATED);	
	}
}
