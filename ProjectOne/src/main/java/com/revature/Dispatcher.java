package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Employee;
import com.revature.services.WebService;
import com.revature.services.WebServiceImp;

public class Dispatcher {
	
	private static final WebService web = new WebServiceImp();

	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ContextPath() is the path after localhost8080:/
		String switchString = request.getRequestURI().substring(request.getContextPath().length()+1);
		while (switchString.indexOf("/") > 0) {
			//If there the URL contains /, get the substring of the URL
			switchString = switchString.substring(0,switchString.indexOf("/"));
		}
		System.out.println(switchString);
		switch(switchString) {
		//User wants to go back to the home page
		case "home": web.goToHome(request, response);
		break;
		//User wants to view/change his profile
		case "profile": web.goToProfile(request, response);
		break;	
		//User is logging in
		case "login": web.processLogin(request, response);
		break;
		//User wants to logout
		case "logout": web.processLogout(request,response);
		break;
		case "info": web.processRequest(request,response);
		break;
		case "update": web.updateProfile(request, response);
		break;
		case "employee": web.HandleEmployees(request, response);
		break;
		case "request": web.HandleRequests(request, response);
		break;
		default: 
			System.out.println("Sorry not yet implemented.");
			break;
		}
	}
}
