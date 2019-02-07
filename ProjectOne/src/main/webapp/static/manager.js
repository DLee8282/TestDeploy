$(document).ready ( function () {
	var x = new XMLHttpRequest();
	x.onreadystatechange = function() {
		if ((x.readyState == 4) && (x.status == 200)) {
			let parsed = JSON.parse(x.responseText);
			for (var i in parsed) {
				console.log(parsed);
				$("#allEmployeeTable").find('tbody').append( 
				"<tr><td>" + parsed[i].userID 
				+ "</td><td>" + parsed[i].firstName
				+ "</td><td>" + parsed[i].lastName
				+ "</td><td> " +parsed[i].username
				+ "</td> <td> " + parsed[i].email
				+ "</td></tr>" );
			}
			
			for (var j in parsed) {
				if(parsed[j].isMgr == false) {
				 $('#empID')
		         .append($("<option></option>")
		                    .attr("value",parsed[j].userID)
		                    .text(parsed[j].userID)); 
				}
			}
			
		}
	}
	x.open("GET","http://localhost:8080/ProjectOne/employee");	
	x.send();
	
	
	var y  = new XMLHttpRequest();	
	y.onreadystatechange = function() {
		if ((y.readyState == 4) && (y.status == 200)) {
			let parsed2 = JSON.parse(y.responseText);
			console.log(parsed2);
			for (var j in parsed2) {
				if (parsed2[j].reqStatus == 0) {
				$("#pendingTable").find('tbody').append( 
				"<tr><td>" + parsed2[j].empID
				+"</td><td>" +parsed2[j].requestID
				+ "</td><td>" + parsed2[j].requestDate.monthValue +"/" 
				+ parsed2[j].requestDate.dayOfMonth + "/" +parsed2[j].requestDate.year
				+ "</td><td>" + parsed2[j].requestType
				+ "</td><td> $" +parsed2[j].requestAmount
				+ "</td> <td> " + parsed2[j].requestDesc
				+ "</td></tr>" );
				}
			}
			
			for (var req in parsed2) {
				console.log(parsed2[req].requestID);
				if (parsed2[req].reqStatus == 0) {
					 $('#reqID')
			         .append($("<option></option>")
			                    .attr("value",parsed2[req].requestID)
			                    .text(parsed2[req].requestID)); 
				} 
			}
		}
	}
	y.open("GET","http://localhost:8080/ProjectOne/request");	
	y.send();
	
	var z  = new XMLHttpRequest();	
	z.onreadystatechange = function() {
		if ((z.readyState == 4) && (z.status == 200)) {
			let parsed3 = JSON.parse(z.responseText);
			for (var k in parsed3) {
				if (parsed3[k].reqStatus != 0) {
					console.log("works");
					$("#allPastRequests").find('tbody').append( 
					"<tr><td>" + parsed3[k].empID
					+ "</td><td>" +parsed3[k].requestID
					+ "</td><td>" + parsed3[k].requestDate.monthValue +"/" 
					+ parsed3[k].requestDate.dayOfMonth + "/" +parsed3[k].requestDate.year
					+ "</td><td>" + parsed3[k].requestType
					+ "</td><td> $" +parsed3[k].requestAmount
					+ "</td> <td> " + parsed3[k].requestDesc
					+ "</td> <td> " + convertStatus(parsed3[k].reqStatus)
					+ "</td> <td> " + parsed3[k].mgrID
					+ "</td></tr>" );
				}
			}	
		}
	}
	z.open("GET","http://localhost:8080/ProjectOne/request");	
	z.send();
	
	
});

let btn = document.getElementById("getRequests");
btn.addEventListener("click",
		function getRequests() {
	var x = new XMLHttpRequest();
	x.onreadystatechange = function() {
		if ((x.readyState == 4) && (x.status == 200)) {
			let parseReq = JSON.parse(x.responseText);
			console.log(x.responseText);
			removeRows("personalTable");
				for (b in parseReq) {
					if (parseReq[b].empID == $("#empID").val()) {
					let row = document.createElement('tr');
					let requestDate = document.createElement("td");
					let requestType = document.createElement("td");
					let requestAmount = document.createElement("td");
					let requestStatus = document.createElement("td");
					let requestDesc = document.createElement("td");
					requestDate.textContent = parseReq[b].requestDate.monthValue + "/" +
					parseReq[b].requestDate.dayOfMonth + "/" +parseReq[b].requestDate.year;
					requestType.textContent = parseReq[b].requestType;
					requestAmount.textContent = "$" +parseReq[b].requestAmount;
					requestStatus.textContent = convertStatus(parseReq[b].reqStatus);
					requestDesc.textContent = parseReq[b].requestDesc;
					row.append(requestDate);
					row.append(requestType);
					row.append(requestAmount);
					row.append(requestStatus);
					row.append(requestDesc);
					document.getElementById("personalTable").append(row);
					}
				}
		}	
		}
	x.open("GET","http://localhost:8080/ProjectOne/request");	
	x.send();
});

function removeRows(tableId) {
	var table = document.getElementById(tableId);
	var rowCount = table.rows.length-1;
	while (rowCount > 0) {
		    table.deleteRow(rowCount);
		    rowCount--;
	}
}



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
