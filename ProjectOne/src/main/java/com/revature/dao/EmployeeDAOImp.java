package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.Requests;
import com.revature.services.EmployeeService;
import com.revature.utils.ConnectionUtils;

public class EmployeeDAOImp implements EmployeeDAO {

	private static EmployeeDAOImp employeeDAO;
	final static Logger log = Logger.getLogger(EmployeeDAOImp.class);
	
	
	public static EmployeeDAO getEmployeeDAO() {
		if (employeeDAO == null) {
			employeeDAO = new EmployeeDAOImp();
		}
		return employeeDAO;
	}
	
	
//EMPLOYEES --------------------------------------
	
	public List<Employee> getAllEmployees() {
		try (Connection conn = ConnectionUtils.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Employees";
			ResultSet results = stmt.executeQuery(sql);
			List<Employee> allEmployees = new ArrayList<Employee>();
			while (results.next()) {
				Employee tmp = new Employee();
				tmp.setUserID(results.getInt(1));
				tmp.setFirstName(results.getString(2));
				tmp.setLastName(results.getString(3));
				tmp.setUsername(results.getString(4));
				tmp.setPassword(results.getString(5));
				if (results.getInt(6) == 1) {
					tmp.setMgr(true);
				}
				else {
					tmp.setMgr(false);
				}
				tmp.setEmail(results.getString(7));
				allEmployees.add(tmp);
			}
			stmt.close();
			return allEmployees;
		}catch (Exception e) {
			log.error("Error: Cannot retrieve employee details!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}

		return new ArrayList<Employee>();
	}
	
	public Employee getEmployeeInformation(String username) {
		try (Connection conn = ConnectionUtils.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Employees WHERE USERNAME= '"+username+"'";
			ResultSet results = stmt.executeQuery(sql);
			Employee details = new Employee();
			if (results.next()) {
				details.setUserID(results.getInt(1));
				details.setFirstName(results.getString(2));
				details.setLastName(results.getString(3));
				details.setUsername(results.getString(4));
				details.setPassword(results.getString(5));
				if (results.getInt(6) == 1) {
					details.setMgr(true);
				}
				else {
					details.setMgr(false);
				}
				details.setEmail(results.getString(7));
			}
			stmt.close();
			return details;
		}catch (Exception e) {
			log.error("Error: Cannot retrieve employee details!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return new Employee();
	}
	
	public boolean setEmployeeInformation(Employee emp) {
		//TODO? Error checking user_ID doesn't exist and stuff
		try (Connection conn = ConnectionUtils.getConnection()) {
			CallableStatement stmt = conn.prepareCall(
					"CALL UPDATE_EMPLOYEE(?,?,?,?,?,?,?)");
			stmt.setInt(1, emp.getUserID());
			stmt.setString(2, emp.getFirstName());
			stmt.setString(3, emp.getLastName());
			stmt.setString(4, emp.getUsername());
			stmt.setString(5, emp.getPassword());
			if (emp.getisMgr()) {
				stmt.setInt(6, 1);	
			}
			else {
				stmt.setInt(6, 0);
			}
			stmt.setString(7, emp.getEmail());
			
			if (stmt.executeUpdate() == 0) {
				return true;
			}
		} catch (Exception e) {
			log.error("Error: Cannot update employee!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;
		}
		return false;
	}

	public boolean addEmployee(Employee emp) {
		try (Connection conn = ConnectionUtils.getConnection()) {
			CallableStatement stmt = conn.prepareCall(
					"CALL CREATE_EMPLOYEE(?,?,?,?,?,?)");
			stmt.setString(1, emp.getFirstName());
			stmt.setString(2, emp.getLastName());
			stmt.setString(3, emp.getUsername());
			stmt.setString(4, emp.getPassword());
			if (emp.getisMgr()) {
				stmt.setInt(5, 1);	
			}
			else {
				stmt.setInt(5, 0);
			}
			stmt.setString(6, emp.getEmail());
	
			if (stmt.executeUpdate() == 0) {
				return true;
			}
		} catch (Exception e) {
			log.error("Error: Cannot add new employee!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;
		}
		return false;
	}

	public Object LoginEmployee(Employee emp) {
		
		Employee check_emp = EmployeeService.getEmployeeService().getEmployeeInformation(emp);
		//Check if the user ID exists, 0 if the person does not exist
		if (check_emp.getUserID() > 0) {	
			try (Connection conn = ConnectionUtils.getConnection()){
				CallableStatement stmt = conn.prepareCall("{? = call GET_USER_HASH(?,?)}");
				stmt.registerOutParameter(1, Types.NVARCHAR);
				stmt.setString(2, emp.getUsername());
				stmt.setString(3,emp.getPassword());
				stmt.execute();
				
				if (stmt.getString(1).equals(check_emp.getPassword())) {
					return check_emp;
				}
			}
			catch (Exception e) {
				log.error("Internal error occurred in LoginEmployee");
				log.error(e.getMessage());
				log.error(e.getStackTrace());
				return null;
			}
		}
		return null;
	}
//REQUESTS-------------------------------------------
	//0 IS PENDING
	//1 IS DENIED
	//2 IS APPROVED
	public boolean createRequest(Requests req) {
		try (Connection conn = ConnectionUtils.getConnection()) {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
			
			CallableStatement stmt = conn.prepareCall(
					"CALL CREATE_REQUEST(?,?,?,?,?)");
			stmt.setInt(1, req.getEmpID());
			stmt.setString(2, req.getRequestType());
			stmt.setDouble(3, req.getRequestAmount());
			stmt.setString(4, formatter.format(req.getRequestDate()));
			stmt.setString(5, req.getRequestDesc());
			if (stmt.executeUpdate() == 0) {
				return true;
			}
		} catch (Exception e) {
			log.error("Error: Cannot create request!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;
		}
		return false;
	}

	public boolean changeRequest(Requests req) {
		try (Connection conn = ConnectionUtils.getConnection()) {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
			
			CallableStatement stmt = conn.prepareCall(
					"CALL UPDATE_REQUESTS(?,?,?,?,?,?,?)");
			stmt.setInt(1, req.getRequestID());
			stmt.setInt(2, req.getMgrID());
			stmt.setString(3, req.getRequestType());
			if (req.getRequestAmount() > 0) {
				stmt.setDouble(4, req.getRequestAmount());
			}
			else {
				stmt.setDouble(4, java.sql.Types.NULL);
			}
			if (req.getRequestDate() != null) {
				stmt.setString(5, formatter.format(req.getRequestDate()));
			}
			else {
				stmt.setString(5, null);
			}
			stmt.setString(6, req.getRequestDesc());
			stmt.setInt(7, req.getReqStatus());
			
			if (stmt.executeUpdate() == 0) {
				return true;
			}
		} catch (Exception e) {
			log.error("Error: Cannot change request!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;
		}
		return false;
	}
	
	public List<Requests> getAllRequests() {
		try (Connection conn = ConnectionUtils.getConnection()) {
			List<Requests> allRequests = new ArrayList<Requests>();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Request";
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				//T_ID, EMP_ID, T_TYPE, MGR_ID, T_AMT, T_DATE, T_DESC, T_STATUS
				Requests tmp = new Requests();
				tmp.setRequestID(results.getInt(1));
				tmp.setEmpID(results.getInt(2));
				tmp.setMgrID(results.getInt(3));
				tmp.setRequestType(results.getString(4));
				tmp.setRequestAmount(results.getDouble(5));
				tmp.setRequestDate(LocalDateTime.parse(results.getString(6)));
				tmp.setRequestDesc(results.getString(7));
				tmp.setReqStatus(results.getInt(8));
				allRequests.add(tmp);
			}
			stmt.close();
			return allRequests;
		}catch (Exception e) { 
			log.error("Error: Cannot retrieve request details!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}

		return new ArrayList<Requests>();
	}

	public List<Requests> returnSpecificRequests(Employee emp) {
		try (Connection conn = ConnectionUtils.getConnection()) {
			List<Requests> specreq = new ArrayList<Requests>();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Request WHERE EMP_ID="+emp.getUserID();
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				Requests tmp = new Requests();
				tmp.setRequestID(results.getInt(1));
				tmp.setEmpID(results.getInt(2));
				tmp.setMgrID(results.getInt(3));
				tmp.setRequestType(results.getString(4));
				tmp.setRequestAmount(results.getDouble(5));
				tmp.setRequestDate(LocalDateTime.parse(results.getString(6)));
				tmp.setRequestDesc(results.getString(7));
				tmp.setReqStatus(results.getInt(8));
				specreq.add(tmp);
			}
			stmt.close();
			return specreq;
		}catch (Exception e) { 
			log.error("Error: Cannot retrieve request details!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}

		return new ArrayList<Requests>();
	}

	public List<Requests> returnStatusRequests(int status) {
		try (Connection conn = ConnectionUtils.getConnection()) {
			List<Requests> statusreq = new ArrayList<Requests>();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Request WHERE T_STATUS="+status;
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				Requests tmp = new Requests();
				tmp.setRequestID(results.getInt(1));
				tmp.setEmpID(results.getInt(2));
				tmp.setMgrID(results.getInt(3));
				tmp.setRequestType(results.getString(4));
				tmp.setRequestAmount(results.getDouble(5));
				tmp.setRequestDate(LocalDateTime.parse(results.getString(6)));
				tmp.setRequestDesc(results.getString(7));
				tmp.setReqStatus(results.getInt(8));
				statusreq.add(tmp);
			}
			stmt.close();
			return statusreq;
		}catch (Exception e) { 
			log.error("Error: Cannot retrieve status request details!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}

		return new ArrayList<Requests>();
	}
	

//VALIDATION----------------------------------------------
	
	public boolean checkLogin(Employee emp) {
		return false;
	}
	
}
