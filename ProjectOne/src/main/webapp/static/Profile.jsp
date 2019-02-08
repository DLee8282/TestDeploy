<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles.css">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
	
<title>My profile</title>
</head>
<body>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	if(session.getAttribute("id")== null)
	    response.sendRedirect("/ProjectOne/");
%>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<h3>
		Your Profile:  		<%
						String username = (String) session.getAttribute("username");
						String firstname = (String) session.getAttribute("firstname");
						String lastname = (String) session.getAttribute("lastname");
						String email = (String) session.getAttribute("email");
					    %> 
 		</h3>
		<form action = "home" method = "get">
		<button type="submit" class="btn btn-light">Home Page</button>
		</form>
		<form action = "profile" method = "get">
		<button type="submit" class="btn btn-light">View/Change Profile</button>
		</form>
		<form action = "logout" method = "get">
		<button type="submit" class="btn btn-light">Logout</button>
		</form>
	</nav>

	<div class = "container">
	<form action = "update" method = "post">
	  <div class="form-group">
	    <label>Email address</label>
	    <input name = "updEmail" type="email" class="form-control" aria-describedby="emailHelp" placeholder="Enter email"
	    value = <% if (email != null) {out.println(email);}; %>
	    >
	  </div>
	  <div class="form-group">
	    <label> First Name</label>
	    <input name = "first" type="text" class="form-control" placeholder="First Name"
	    value = <% out.println(firstname); %>
	    >
	  </div>
	  
	  <div class="form-group">
	    <label>Last Name</label>
	    <input name = "last" type="text" class="form-control" placeholder="Last Name"
	    value = <% out.println(lastname); %>
	    >
	  </div>
	  
	  <div class="form-group">
	    <label>Password</label>
	    <input name="pass" type="password" class="form-control" placeholder="Password" required>
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
	</div>
	




</body>
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
</html>