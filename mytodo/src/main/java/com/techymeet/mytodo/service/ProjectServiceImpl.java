package com.techymeet.mytodo.service;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techymeet.mytodo.bo.ProjectBO;
import com.techymeet.mytodo.dao.ProjectDao;
import com.techymeet.mytodo.vo.Project;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	 private ProjectDao projectDao;
	@Override
	public ProjectBO createProject(ProjectBO project) {
		Project projectVO=new Project();
		try {
			projectVO.setProjectName(project.getProjectName());
			projectVO.setProjectManager(project.getProjectManager());
			projectVO.setStartDate(ZonedDateTime.parse(project.getStartDate()).toLocalDate());
			projectVO.setEndDate(ZonedDateTime.parse(project.getEndDate()).toLocalDate());
			projectVO=projectDao.createProject(projectVO);
			
			if(projectVO.getProjectId()>0) {
				project.setProjectId(projectVO.getProjectId());
				return project;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
