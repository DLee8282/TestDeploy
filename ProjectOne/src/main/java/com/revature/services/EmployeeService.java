package com.revature.services;

import java.util.List;

import com.revature.dao.EmployeeDAOImp;
import com.revature.models.Employee;
import com.revature.models.Requests;

public class EmployeeService {
	
	private static EmployeeService employeeService;
	
	private EmployeeService() {
		
	}
	
	public static EmployeeService getEmployeeService() {
		if (employeeService == null) {
			employeeService = new EmployeeService();
		}
		return employeeService;
	}
	/**
	 * @return Returns a list of all employees, including managers
	 */
	public List<Employee> getAllEmployees() {
		return EmployeeDAOImp.getEmployeeDAO().getAllEmployees();
	}
	
	/**
	 * @param emp Current employee instance
	 * @return Returns Updated Employee Information
	 */
	public Employee getEmployeeInformation(Employee emp) {
		return EmployeeDAOImp.getEmployeeDAO().getEmployeeInformation(emp.getUsername());
	}
	
	/**
	 * @param emp Current employee instance
	 * @return Returns true/false if employee information is updated
	 */
	public boolean setEmployeeInformation(Employee emp) {
		return EmployeeDAOImp.getEmployeeDAO().setEmployeeInformation(emp);
	}
	
	/**
	 * @param emp Current employee instance
	 * @return returns true if employee is added
	 */
	public boolean addEmployee(Employee emp) {
		return EmployeeDAOImp.getEmployeeDAO().addEmployee(emp);
	}
	
	public Object LoginEmployee(Employee emp) {
		return EmployeeDAOImp.getEmployeeDAO().LoginEmployee(emp);
	}
	
	/**
	 * @param emp Current employee instance
	 * @return true if a request is successfully created
	 */
	public boolean createRequest(Requests req) {
		return EmployeeDAOImp.getEmployeeDAO().createRequest(req);
	}
	
	/**
	 * @param emp Current employee instance
	 * @param req Current request instance
	 * @return true if the request approval goes through
	 */
	public boolean changeRequest(Requests req) {
		return EmployeeDAOImp.getEmployeeDAO().changeRequest(req);
	}
	/**
	 * @return Returns all accounts for all employees, approved, pending, and denied
	 */
	public List<Requests> getAllRequests() {
		return EmployeeDAOImp.getEmployeeDAO().getAllRequests();
	}
	/**
	 * @param emp: Specific employee
	 * @return All requests made by the employee
	 */
	public List<Requests> returnSpecificRequests(Employee emp) {
		return EmployeeDAOImp.getEmployeeDAO().returnSpecificRequests(emp);
	}
	
	/**
	 * @param status Status integer 0,1,2
	 * @return list of requests with that certain status
	 */
	public List<Requests> returnStatusRequests(int status) {
		return EmployeeDAOImp.getEmployeeDAO().returnStatusRequests(status);
	}
}
