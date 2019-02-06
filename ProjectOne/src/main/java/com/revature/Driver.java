package com.revature;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.models.*;
import com.revature.services.EmployeeService;

public class Driver {
	
	public static void main(String[] args) {
		System.out.println(EmployeeService.getEmployeeService().getAllEmployees());
	}
//		Employee emp = new Employee();
//		emp.setFirstName("David");
//		emp.setLastName("Lee");
//		emp.setUsername("Username1");
//		emp.setPassword("password");
//		emp.setEmail("David@mail.com");
//		emp.setMgr(false);
		//EmployeeService.getEmployeeService().addEmployee(emp);
		//List<Employee> all_emp = EmployeeService.getEmployeeService().getAllEmployees();
//		for (Employee x : all_emp) {
//			System.out.println(x.getUserID());
//			System.out.println(x.getFirstName());
//			System.out.println(x.getLastName());
//			System.out.println(x.getEmail()); 
//			System.out.println("------------------------------------");
//		}
//		emp = EmployeeService.getEmployeeService().getEmployeeInformation(emp);
//		
//		Requests req = new Requests();
//		req.setEmpID(emp.getUserID());
//		req.setRequestType("Another type");
//		req.setRequestDesc("Different Description");
//		req.setRequestAmount(10.005);
//		req.setRequestDate(LocalDateTime.now());
		//EmployeeService.getEmployeeService().createRequest(req);
		
//		List<Requests> all_req = EmployeeService.getEmployeeService().getAllRequests();
//		
//		for (Requests r : all_req) {
//			System.out.println(r.getEmpID());
//			System.out.println(r.getRequestDesc());
//			System.out.println(r.getMgrID());
//		}
//		
//		List<Requests> my_req = EmployeeService.getEmployeeService().returnSpecificRequests(emp);
//		
//		for (Requests r1 : my_req) {
//			System.out.println(r1.getRequestID());
//			System.out.println(r1.getEmpID());
//		}
//		Requests change = new Requests();
//		change.setMgrID(12);
//		change.setRequestID(23);
//		change.setRequestAmount(33.32);
//		change.setRequestDesc("I changed this request again");
//		EmployeeService.getEmployeeService().changeRequest(change);
			
//		List<Requests> req3 = EmployeeService.getEmployeeService().returnStatusRequests(1);
//		
//		for (Requests h : req3) {
//			System.out.println(h.getRequestID());
//		}
//	}
}
