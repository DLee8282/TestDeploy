package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.revature.dao.EmployeeDAOImp;
import com.revature.services.EmployeeService;
import com.revature.models.*;
public class ProjectOneTest {

		private final EmployeeDAOImp test = new EmployeeDAOImp();
		
		@Test
		public void isThisTheRealLife() {
			assertTrue(true);
		}
		//Bad userID input
		@Test
		public void isThisJustFantasy() {
			Employee emp = new Employee();
			emp.setUserID(99);
			assertEquals(test.returnSpecificRequests(emp),new ArrayList<Employee>());
		}
		//Test changeRequest if it works
		@Test
		public void caughtInaLandSlide() {
			Requests req = new Requests();
			assertTrue(test.changeRequest(req));
		}
		//No value passed in requests
		@Test
		public void NoEscapeFromReality() {
			Requests req = new Requests();
			assertFalse(test.createRequest(req));
		}
		//Check if adding a employee with no values
		@Test
		public void OpenYourEyes() {
			Employee emp = new Employee();
			assertFalse(test.addEmployee(emp));
		}
		@Test
		public void LookUpToTheSky() {
			assertFalse(false);
		}
		//Check if bad login employee returns null
		@Test
		public void AndSEEEEE() {
			Employee emp = new Employee();
			assertEquals(test.LoginEmployee(emp), null);
		}
		
		@Test
		public void GiveMeTheBeatBoys() {
			assertEquals(1,1);
		}
		//Check for bad request query
		@Test
		public void IWannaGetLostInYourRockandRoll() {
			assertEquals(test.returnStatusRequests(-1),new ArrayList<Requests>());
		}
		//Check for fake employee
		@Test
		public void AndDriftAway(){
			assertEquals(test.getEmployeeInformation("FakeUsername"),new Employee());
		}
		
	}

