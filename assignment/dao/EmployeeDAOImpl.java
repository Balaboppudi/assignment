package com.assignment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.assignment.exception.EmployeeNotFoundException;
import com.assignment.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public void addEmployee(Employee p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Employee saved successfully, Employee Details="+p);
	}

	public void updateEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(employee);
		logger.info("Employee updated successfully, Employee Details="+employee);
	}

	public List<Employee> listEmployees() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Employee> employeeList = session.createQuery("from Employee").list();
		for(Employee p : employeeList){
			logger.info("EmployeeList List::"+p);
		}
		return employeeList;
	}

	public Employee getEmployeeById(int id)throws EmployeeNotFoundException {
		Session session = this.sessionFactory.getCurrentSession();		
		Employee employee = (Employee) session.load(Employee.class, new Integer(id));
		if(employee == null){
			logger.info("employee not found");
			throw new EmployeeNotFoundException("notFound");
		}
		logger.info("employee loaded successfully, Employee details="+employee);
		return employee;
	}

	public void removeEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee employee = (Employee) session.load(Employee.class, new Integer(id));
		if(employee !=null ){
			session.delete(employee);
		}
		logger.info("Employee deleted successfully, Employee details="+employee);
	}



}