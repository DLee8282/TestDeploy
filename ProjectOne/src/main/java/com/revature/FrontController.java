package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author David Lee
 * 
 * Front controller
 */

public class FrontController extends DefaultServlet{
	//Implements Serializable
	private static final long serialVersionUID = 1L;

	//Marshals POJOs into JSON files
	private final ObjectMapper mapper = new ObjectMapper();
	
	
	private Dispatcher dp = new Dispatcher();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			/*
			 * When there is nothing in the URL, it loads the /ProjectOne/ directory
			 */
			if (req.getRequestURI().contentEquals("/ProjectOne/")
				|| req.getRequestURI().contains("/static/")) {
					//super.doGet handles web pages 
					System.out.println("In if statement in doGet");
					super.doGet(req, resp);
				}
			
			else {
				//Processes all the string (i.e. Login, EmployeeHome etc)
				System.out.println("In the else statment in doGet");
				dp.process(req, resp);
			}
				
		}
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
	
	
}
