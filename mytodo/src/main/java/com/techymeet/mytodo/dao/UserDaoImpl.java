package com.techymeet.mytodo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techymeet.mytodo.vo.Employee;
import com.techymeet.mytodo.vo.Login;
import com.techymeet.mytodo.vo.Role;

import jakarta.persistence.EntityManager;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private EntityManager entityManager;
	@Override
	public Optional<Login> findByUsername(String username) {
		Optional<Login> user =null;
		try {
            CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
			CriteriaQuery<Login> criteriaQuery=criteriaBuilder.createQuery(Login.class);
			Root<Login> root = criteriaQuery.from(Login.class);
			Predicate[] predicate=new Predicate[2];
			predicate[0]=criteriaBuilder.equal(root.get("userName"),username);
			predicate[1]=criteriaBuilder.equal(root.get("isDelete"),false);
			
			criteriaQuery.select(root).where(predicate);
			Query<Login> query = (Query<Login>) entityManager.createQuery(criteriaQuery);	
			user=query.uniqueResultOptional();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
	}
	@Override
	public Role getRoleidByName(String roleName) {
	     Role role=null;
	     try {
	    	 CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
				CriteriaQuery<Role> criteriaQuery=criteriaBuilder.createQuery(Role.class);
				Root<Role> root = criteriaQuery.from(Role.class);
				criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("roleName"),roleName));
				Query<Role> query = (Query<Role>) entityManager.createQuery(criteriaQuery);
	    	      role=query.getSingleResult();
	    	      
	    	      if(null!=role) {
	    	    	  return role;
	    	      }
	     }catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
	@Override
	public Employee registerEmployee(Employee employee) {
		try {
			entityManager.persist(employee);
			if(employee.getEmployeeId()>0) {
				return employee;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	@Override
	public boolean findByEmail(String email) {
		Login login=null;
		 try {
	    	 CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
				CriteriaQuery<Login> criteriaQuery=criteriaBuilder.createQuery(Login.class);
				Root<Login> root = criteriaQuery.from(Login.class);
				Predicate[] predicate=new Predicate[2];
				predicate[0]=criteriaBuilder.equal(root.get("userName"),email);
				predicate[1]=criteriaBuilder.equal(root.get("isDelete"),false);
				criteriaQuery.select(root).where(predicate);
				Query<Login> query = (Query<Login>) entityManager.createQuery(criteriaQuery);
				login=query.getSingleResult();
	    	      
	    	      if(null!=login) {
	    	    	  return true;
	    	      }
	     }catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<Employee> getemployeeList() {
		List<Employee> employeeList =new ArrayList<>();
		try {
			CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery=criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("isDelete"),false));
			Query<Employee> query = (Query<Employee>) entityManager.createQuery(criteriaQuery);
			employeeList=query.getResultList();
    	      
    	      if(null!=employeeList&& employeeList.size()>0&&!employeeList.isEmpty()) {
    	    	  return employeeList;
    	      }
     }catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
	@Override
	public Employee getEmployeeById(int id) throws Exception {
	
		return entityManager.find(Employee.class, id);
	}
	@Override
	public Employee updateEmployee(Employee employee) {
		try {
			if(employee.getEmployeeId()>0) {
				entityManager.merge(employee);
				return employee;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean deleteEmployeeById(int id) {
		try {
			Employee employee=getEmployeeById(id);
			employee.setDelete(true);
			entityManager.merge(employee);
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
