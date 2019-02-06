<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="static/styles.css">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<style>
.table {
	max-width: none;
	table-layout: fixed;
	word-wrap: break-word;
}
</style>
<title>Manager Home page</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<h3>
			Welcome
			<%
			String string = (String) session.getAttribute("username");
		%>
			<%
				out.println(string + "!");
			%>
		</h3>


		<form action="home" method="get">
			<button type="submit" class="btn btn-light">Home Page</button>
		</form>
		<form action="profile" method="get">
			<button type="submit" class="btn btn-light">View/Change
				Profile</button>
		</form>
		<form action="logout" method="get">
			<button type="submit" class="btn btn-light">Logout</button>
		</form>

	</nav>


	<div class="table">
		<h5>Pending Requests:</h5>
		<table id="pendingTable" class="table">
			<thead>
				<tr>
					<th scope="col">Employee ID</th>
					<th scope="col"> Request ID </th>
					<th scope="col">Date</th>
					<th scope="col">Type</th>
					<th scope="col">Amount</th>
					<th scope="col">Description</th>
				</tr>
			</thead>

			<tbody>
			</tbody>
		</table>
	</div>



	<div class="container-fluid" id="Managercontainer">

		<div class="row">
			<div class="col-lg-3">
				<div class="card" style="width: 20rem;">
					<div class="card-body">
						<h5 class="card-title">Approve/Deny requests</h5>
						<p class="card-text">Approve/Deny requests.</p>
						<button type="button" class="btn btn-primary" data-toggle="modal"
							id="AddEmpBtn" data-target="#approveDeny"
							data-whatever="@getbootstrap">Approve/ Deny Requests</button>
					</div>
				</div>
			</div>

			<div class="col-lg-3">
				<div class="card" style="width: 20rem;">
					<div class="card-body">
						<h5 class="card-title">Add an employee</h5>
						<p class="card-text">Add a new employee to the system.</p>

						<button type="button" class="btn btn-primary" data-toggle="modal"
							id="AddEmpBtn" data-target="#addEmployeeModal"
							data-whatever="@getbootstrap">Add an Employee</button>
					</div>
				</div>
			</div>


			<div class="col-md-3">
				<div class="card" style="width: 20rem;">
					<div class="card-body">
						<h5 class="card-title">View all employees</h5>
						<p class="card-text">View all employees.</p>
						<button type="button" class="btn btn-primary" data-toggle="modal"
							id="AllEmpBtn" data-target="#empModal"
							data-whatever="@getbootstrap">All Employees</button>
					</div>
				</div>
			</div>


			<div class="col-md-3">
				<div class="card" style="width: 20rem;">
					<div class="card-body">
						<h5 class="card-title">View a Employee Request</h5>
						<p class="card-text">View a request from a particular
							employee.</p>
						<button type="button" class="btn btn-primary" data-toggle="modal"
							id="AllEmpBtn" data-target="#empRequest"
							data-whatever="@getbootstrap">Employee Request</button>
					</div>
				</div>
			</div>


		</div>

	</div>

	<div class="modal fade" id="empRequest" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">See a Employee
						Request</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label class="col-form-label">Employee ID</label> <select
							name="reqEmp" id="empID">
						</select>
					</div>
					<table id="personalTable" class="table">
						<thead>
							<tr>
								<th scope="col">Date</th>
								<th scope="col">Type</th>
								<th scope="col">Amount</th>
								<th scope="col">Status</th>
								<th scope="col">Description</th>
							</tr>
						</thead>
					</table>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button id="getRequests" type="button" class="btn btn-primary">Get
						Requests</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="approveDeny" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Approve/Deny a
						Request</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label class="col-form-label"> Request ID</label> <select
							name="idAppDeny" id="reqID">
							<option value = "null"> Choose one... </option>
						</select>
					</div>
					<table id="pendingReq" class="table">
						<thead>
							<tr>
								<th scope="col">Date</th>
								<th scope="col">Type</th>
								<th scope="col">Amount</th>
								<th scope="col">Status</th>
								<th scope="col">Description</th>
							</tr>
						</thead>
					</table>
				<select name="appDenyselect" id="approveDeny">
			    	<option value= "approve"> Approve </option>
			    	<option value= "deny"> Deny </option>
			    </select>

				</div>
				<div class="modal-footer">
					<button id="getRequests" type="button" class="btn btn-secondary"
							data-dismiss="modal">See Request</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button id="submitStatus" type="submit" class="btn btn-primary"> Submit </button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="empModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">All Employees</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table id="allEmployeeTable" class="table">
						<thead>
							<tr>
								<th scope="col">#ID</th>
								<th scope="col">First Name</th>
								<th scope="col">Last Name</th>
								<th scope="col">User name</th>
								<th scope="col">Email</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="addEmployeeModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel2" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Add a Employee</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<form action="employee" method="post">
					<div class="form-group">
						<label>Email address</label> <input name="email" type="email"
							class="form-control" aria-describedby="emailHelp"
							placeholder="Enter email">
					</div>
					<div class="form-group">
						<label> First Name</label> <input name="firstN" type="text"
							class="form-control" placeholder="First Name" required>
					</div>

					<div class="form-group">
						<label>Last Name</label> <input name="lastN" type="text"
							class="form-control" placeholder="Last Name" required>
					</div>
					<div class="form-group">
						<label>User name</label> <input name="user" type="text"
							class="form-control" placeholder="User Name" required>
					</div>

					<div class="form-group">
						<label>Password</label> <input name="passw" type="password"
							class="form-control" placeholder="Password" required>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Submit</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div class="table">
		<h5>Past Requests:</h5>
		<table id="allPastRequests" class="table">
			<thead>
				<tr>
					<th scope="col">Employee ID</th>
					<th scope="col"> Request ID </th>
					<th scope="col">Date</th>
					<th scope="col">Type</th>
					<th scope="col">Amount</th>
					<th scope="col">Description</th>
					<th scope="col">Status</th>
					<th scope="col">Manager ID</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
	<script src="static/manager.js">
		
	</script>
</body>
</html>