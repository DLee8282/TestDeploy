package com.revature.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface WebService {
	
	//Go to home page
	void goToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	//Go to user profile page
	public void goToProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	//Updates the profile
	public void updateProfile(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException;
	
	//Handles viewing/creating Employees
	public void HandleEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	//Handles requests (approve/deny) and pending
	public void HandleRequests(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException;
	
	//Authenticates the login information
	void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	
	//Handler to process HttpRequests from Dispatcher
	void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	//Handler to process logouts
	void processLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
