<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>Employee Home Page</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<h2>
			Welcome
			<%
			String string = (String) session.getAttribute("username");
		%>
			<%
				out.println(string + "!");
			%>
		</h2>
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

	<div class="container" id="pendingRequests">
		<h5>Your Pending Requests:</h5>
		<table id="employeeTablePending" class="table">
			<thead>
				<tr>
					<th scope="col">#ID</th>
					<th scope="col">Date</th>
					<th scope="col">Type</th>
					<th scope="col">Amount</th>
					<th scope="col">Description</th>
					<th scope="col">Status</th>
					<th scope="col">Approved By:</th>
				</tr>
			</thead>

			<tbody>
			</tbody>
		</table>
	</div>


	<div class="container" id="employee-request">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Create a request</h5>
						<p class="card-text">Create a new request</p>
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#requestModal" data-whatever="@getbootstrap">
							Create Request</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container" id="pastRequests">
		<h5>Your Past Requests:</h5>
		<table id="employeeTablePast" class="table">
			<thead>
				<tr>
					<th scope="col">#ID</th>
					<th scope="col">Date</th>
					<th scope="col">Type</th>
					<th scope="col">Amount</th>
					<th scope="col">Description</th>
					<th scope="col">Status</th>
					<th scope="col">Approved By:</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>







	<div class="modal fade" id="requestModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">New Request</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="info" method="post">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-form-label">Request Type:</label> <select
								name="reqType">
								<option value="Training">Training</option>
								<option value="Travel">Travel</option>
								<option value="Supplies">Supplies</option>
								<option value="Other">Other</option>
							</select>
						</div>
						<div class="form-group">
							<label class="col-form-label">Request Amount:</label> <input
								name="reqAmount" type="number" step="0.01" />
						</div>

						<div class="form-group">
							<label class="col-form-label">Description:</label>
							<textarea name="reqDesc" class="form-control" id="message-text"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Submit
							Request</button>
					</div>
				</form>
			</div>
		</div>
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
	<script src="static/ajaxrequests.js"></script>
</body>
</html>