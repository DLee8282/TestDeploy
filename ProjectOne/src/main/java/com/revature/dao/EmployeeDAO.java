package com.revature.dao;

import java.util.List;
import com.revature.models.*;

/**
 * @author David Lee
 *
 */
public interface EmployeeDAO {
	//EMPLOYEES -------------------------------------------------------
	/**
	 * @return Returns a list of all employees, including managers
	 */
	public List<Employee> getAllEmployees();

	/**
	 * @param username - Employee user name
	 * @return Returns all information related to an employee
	 */
	public Employee getEmployeeInformation(String username);
	
	
	/**
	 * @param emp - Instance of employee
	 * @return Returns true/false if employee information was successfully updated
	 */
	public boolean setEmployeeInformation(Employee emp);
	
	/**
	 * @param emp Employee instance
	 * @return true if new employee is created
	 */
	public boolean addEmployee(Employee emp);
	
	/**
	 * @param emp Employee instance
	 * @return Returns null if login is successful, Object if it is
	 */
	public Object LoginEmployee(Employee emp);
	
	//REQUESTS-----------------------------------------------------------
	
	/**
	 * @param req Requests instance
	 * @return Returns true/false if a request is created
	 */
	public boolean createRequest(Requests req);
	/**
	 * @param req Requests instance
	 * @return Boolean if the request is approved/denied (changes are made)
	 */
	public boolean changeRequest(Requests req);	
	
	/**
	 * @return Returns all accounts for all employees, approved, pending, and denied
	 */
	public List<Requests> getAllRequests();
	
	/**
	 * @param emp: Specific employee
	 * @return All requests made by the employee
	 */
	public List<Requests> returnSpecificRequests(Employee emp);
	
	/**
	 * @param req Request instance
	 * @return All requests with a status of 0,1 or 2
	 */
	public List<Requests> returnStatusRequests(int status);
	
}
