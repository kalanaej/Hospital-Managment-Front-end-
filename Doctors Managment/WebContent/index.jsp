<%@ page import="com.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctors Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>

</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-6">
			<h1>Doctors Management</h1>
				
			<form id="formItem" name="formItem" method="post" action="index.jsp">
				
				Hospital Name:	
				<input id="hospitalName" name="hospitalName" type="text" class="form-control form-control-sm" >
				<br>
					
				Doctor Name: 
				<input id="docName" name="docName" type="text" class="form-control form-control-sm" >
				<br>
					
				Age: 
				<input id="age" name="age" type="text" class="form-control form-control-sm" >
				<br>
					
				Specialization: 
				<input id="spec" name="spec" type="text" class="form-control form-control-sm" >
				<br>
					
				Arrive Time: 
				<input id="arrive" name="arrive" type="text" class="form-control form-control-sm" >
				<br>
					
				Leave Time: 
				<input id="leave" name="leave" type="text" class="form-control form-control-sm" >
				<br>
					
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
			</form>
				
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
				
			<br>
			<div id="divItemsGrid">
				<%
					Doctor doc = new Doctor(); 
					out.print(doc.readDoctors());
				%>
			</div>
		</div>
	</div>
</div>
</body>
</html>