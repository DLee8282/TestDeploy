package com.revature.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Requests;

public class WebServiceImp implements WebService {

	private final ObjectMapper mapper = new ObjectMapper();
	
	
	
	//Processes go home requests
	public void goToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("manager") == null) {
			response.sendRedirect("/ProjectOne/");
		}
		else {
			boolean isMgr = (boolean) session.getAttribute("manager");
			if (isMgr) {
				RequestDispatcher disp = request.getRequestDispatcher("/static/managerHome.jsp");
				disp.include(request, response);
			}
			else {
				RequestDispatcher disp = request.getRequestDispatcher("/static/empHome.jsp");
				disp.include(request, response);
			}
		}
	}
	
	//Processes profile requests
	public void goToProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/ProjectOne/");
		}
		
		RequestDispatcher disp = request.getRequestDispatcher("/static/Profile.jsp");
		disp.include(request, response);	
	}
	
	//Updates profile
	public void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("POST".contentEquals(request.getMethod())) {
			HttpSession session = request.getSession();
			int id = (int) session.getAttribute("id");
			String username = (String) session.getAttribute("username");
			System.out.println(username);
			String email = (String) request.getParameter("updEmail");
			System.out.println(email);
			String firstname = (String) request.getParameter("first");
			System.out.println(firstname);
			String lastname = (String) request.getParameter("last");
			System.out.println(lastname);
			String password = (String) request.getParameter("pass");
			System.out.println(password);
			System.out.println(password.length());
			Employee update = new Employee();
			update.setUserID(id);
			update.setUsername(username);
			update.setMgr((boolean) session.getAttribute("manager"));
			if (!email.isEmpty()) {
				update.setEmail(email);
				session.setAttribute("email", email);
			}
			if (!firstname.isEmpty()) {
				update.setFirstName(firstname);
				session.setAttribute("firstname", firstname);
			}
			if (!lastname.isEmpty()) {
				update.setLastName(lastname);
				session.setAttribute("lastname", lastname);
			}
			if (!password.isEmpty()){
				update.setPassword(password);
			}
			
			EmployeeService.getEmployeeService().setEmployeeInformation(update);
		}
		response.sendRedirect("home");
	}	
	// Processes all requests
	
	//Processes requests
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		System.out.println(session);
		Employee cur_emp = new Employee();
		cur_emp.setUsername((String) session.getAttribute("username"));
		cur_emp = EmployeeService.getEmployeeService().getEmployeeInformation(cur_emp);
		
		System.out.println("Getting all requests");
		// Making a new request - Employee only
		if ("POST".contentEquals(request.getMethod())) {
			Requests new_req = new Requests();
			new_req.setEmpID((int) session.getAttribute("id"));
			new_req.setRequestType(request.getParameter("reqType"));
			new_req.setRequestAmount(Double.parseDouble(request.getParameter("reqAmount")));
			new_req.setRequestDesc(request.getParameter("reqDesc"));
			new_req.setRequestDate(LocalDateTime.now());
			EmployeeService.getEmployeeService().createRequest(new_req);
			System.out.println("Request created successfully!");
			response.sendRedirect("home");
		}
		// Getting a new request - Employees and Managers
		else if ("GET".contentEquals(request.getMethod())) {
			if (request.getSession() != null) {
				System.out.println(cur_emp.getUserID());
				ArrayList<Requests> req = (ArrayList<Requests>) EmployeeService.getEmployeeService().returnSpecificRequests(cur_emp);
				System.out.println(req.size());
				response.setContentType("application/json");
				response.getWriter().append(mapper.writeValueAsString(req));
			}
			else {
				response.sendRedirect("home");
			}
		}
	}

	//Processes the login methods
	@Override
	public void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession newSession = request.getSession();
		// If the method is POST: (User is logging in)
		if ("POST".contentEquals(request.getMethod())) {
			Employee login = new Employee();
			// Get the user name and password of the request
			login.setUsername(request.getParameter("username"));
			login.setPassword(request.getParameter("password"));
			// If the method is not null, return the user object
			System.out.println(request.getParameter("username"));
			System.out.println("Its in processLogin");
			// Checks if the user name and password is valid
			if (EmployeeService.getEmployeeService().LoginEmployee(login) != null) {
				System.out.println("Returning emp info");
				login = EmployeeService.getEmployeeService().getEmployeeInformation(login);
				newSession.setAttribute("id", login.getUserID());
				newSession.setAttribute("username", login.getUsername());
				newSession.setAttribute("firstname", login.getFirstName());
				newSession.setAttribute("lastname", login.getLastName());
				newSession.setAttribute("email", login.getEmail());
				System.out.println(login.getUserID());
				newSession.setAttribute("manager", login.getisMgr());
				if (login.getisMgr()) {
					System.out.println("Going to managerHome.jsp");
					RequestDispatcher disp = request.getRequestDispatcher("/static/managerHome.jsp");
					disp.include(request, response);
				} else {
					System.out.println("Going to empHome.jsp");
					RequestDispatcher disp = request.getRequestDispatcher("/static/empHome.jsp");
					disp.include(request, response);
				}
			} else {
				// Go back to the login page
				newSession.setAttribute("login", "invalid");
				System.out.println("redirecting");
				response.sendRedirect("/ProjectOne/");
			}
		}
	}

	//Process logout
	public void processLogout(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession oldSession = request.getSession();
		System.out.println("Im in processLogout");
		oldSession.invalidate();
		response.sendRedirect("home");
	}

	//Handles employees
	public void HandleEmployees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Creating an Employee
		System.out.println("In here");
		if ("POST".contentEquals(request.getMethod())) {
			Employee new_emp = new Employee();
			System.out.println("POST!!!");
			new_emp.setFirstName(request.getParameter("firstN"));
			new_emp.setLastName(request.getParameter("lastN"));
			new_emp.setEmail(request.getParameter("email"));
			new_emp.setUsername(request.getParameter("user"));
			new_emp.setPassword(request.getParameter("passw"));
			if(request.getParameter("isManager").toLowerCase().equals("yes")) {
				new_emp.setMgr(true);
			}
			System.out.println("creating employee");
			EmployeeService.getEmployeeService().addEmployee(new_emp);
			response.sendRedirect("home");			
		}	
		else if ("GET".contentEquals(request.getMethod())) {
			ArrayList<Employee> allEmployees = (ArrayList<Employee>) EmployeeService.getEmployeeService().getAllEmployees();
			response.setContentType("application/json");
			response.getWriter().append(mapper.writeValueAsString(allEmployees));
		}
	}
	
	//Handles pending/approve deny

	public void HandleRequests(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		//Approving/Denying requests
		if ("POST".contentEquals(request.getMethod())) {
			HttpSession session = request.getSession();
			if ((boolean) session.getAttribute("manager")) {
				Requests updatereq = new Requests();
				int mgrID = (int) session.getAttribute("id");
				if (request.getParameter("requestID").equals("null")) {
					response.sendRedirect("home");
				}
				else {
				int requestID = Integer.parseInt(request.getParameter("requestID"));
				updatereq.setRequestID(requestID);
			
				String approval = request.getParameter("appDenyselect");
				if (approval.contains("approve")) {
					updatereq.setReqStatus(2);
				}
				else if (approval.contains("deny")) {
					updatereq.setReqStatus(1);
				}
				updatereq.setMgrID(mgrID);
				EmployeeService.getEmployeeService().changeRequest(updatereq);
				response.sendRedirect("home");
				}
			}
		}
		//Get all requests
		if ("GET".contentEquals(request.getMethod())) {
			ArrayList<Requests> pending_req = (ArrayList<Requests>) EmployeeService.getEmployeeService().getAllRequests();
			response.setContentType("application/json");
			response.getWriter().append(mapper.writeValueAsString(pending_req));
		}
	}
}
