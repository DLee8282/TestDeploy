$(document).ready ( function () {
	var x = new XMLHttpRequest();
	console.log("This works");
	x.onreadystatechange = function() {
		if ((x.readyState == 4) && (x.status == 200)) {
			console.log(x.responseText);
			let parsed = JSON.parse(x.responseText);
			console.log(parsed);
			console.log("fuck you");
			for (var i in parsed) {
				console.log("yo");
				if (parsed[i].reqStatus == 0) {
					$("#employeeTablePending").find('tbody').append( 
					"<tr><td>" + parsed[i].requestID 
					+ "</td><td>" + parsed[i].requestDate.monthValue +"/" 
					+ parsed[i].requestDate.dayOfMonth + "/" +parsed[i].requestDate.year
					+ "</td><td>" + parsed[i].requestType
					+ "</td><td> " +"$" +parsed[i].requestAmount
					+ "</td> <td> " + parsed[i].requestDesc 
					+ "</td><td>" + convertStatus(parsed[i].reqStatus)
					+ "</td><td>" +checkManager(parsed[i].mgrID)
					+ "</td></tr>" );
				}
				else {
					$("#employeeTablePast").find('tbody').append( 
							"<tr><td>" + parsed[i].requestID 
							+ "</td><td>" + parsed[i].requestDate.monthValue +"/" 
							+ parsed[i].requestDate.dayOfMonth + "/" +parsed[i].requestDate.year
							+ "</td><td>" + parsed[i].requestType
							+ "</td><td> " +"$" +parsed[i].requestAmount
							+ "</td> <td> " + parsed[i].requestDesc 
							+ "</td><td>" + convertStatus(parsed[i].reqStatus)
							+ "</td><td>" +checkManager(parsed[i].mgrID)
							+ "</td></tr>" );	
				}
			}	
		}
	}
	x.open("GET","http://localhost:8080/ProjectOne/info");	
	x.send();
});



function convertStatus(status) {
	if (status === 1) {
		return "Denied";
	}
	else if (status === 0) {
		return "Pending";
	}
	else {
		return "Approved";
	}
}

function checkManager(manager) {
	if (manager == 0) {
		return "Not approved yet"
	}
	else {
		return manager;
	}
}

