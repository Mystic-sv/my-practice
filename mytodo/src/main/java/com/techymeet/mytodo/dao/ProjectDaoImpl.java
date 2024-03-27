package com.techymeet.mytodo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techymeet.mytodo.vo.Project;

import jakarta.persistence.EntityManager;

@Repository
public class ProjectDaoImpl implements ProjectDao{

	  @Autowired
	  private EntityManager entityManager;
	@Override
	public Project createProject(Project projectVO) {
		try {
			entityManager.persist(projectVO);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return projectVO;
	}

}
