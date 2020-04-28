<%@ page import="com.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%

if (request.getParameter("hospitalName") != null)
{
	Doctor doc = new Doctor();
	String stsMsg = "";
	
	//Insert Doctor
	if(request.getParameter("hidItemIDSave") == "")
	{
	
		stsMsg = doc.insertDoctor(request.getParameter("hospitalName"),
								  request.getParameter("docName"),
								  Integer.parseInt(request.getParameter("age")),
								  request.getParameter("spec"),
								  request.getParameter("arrive"),
								  request.getParameter("leave"));
	
		session.setAttribute("statusMsg", stsMsg);
	}
	else //Update Doctor
	{
		stsMsg = doc.updateDoctor(request.getParameter("hidItemIDSave"),
								  request.getParameter("hospitalName"),
				  				  request.getParameter("docName"),
								  Integer.parseInt(request.getParameter("age")),
								  request.getParameter("spec"),
								  request.getParameter("arrive"),
								  request.getParameter("leave"));
		
		session.setAttribute("statusMsg", stsMsg);
	}
}

//Delete Doctor
if (request.getParameter("hidItemIDDelete") != null)
{
	Doctor doc = new Doctor();
	
	String stsMsg = doc.deleteDoctor(Integer.parseInt(request.getParameter("hidItemIDDelete")));
	session.setAttribute("statusMsg", stsMsg);
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>

</head>
<body>

<div class="container">
		<div class="row">
			<div class="col">
				<h1>Doctors Management</h1>
				
				<form id="formItem" name="formItem" method="post" action="index.jsp">
				
					Hospital Name:	<input id="hospitalName" name="hospitalName" type="text" class="form-control" >
					<br>
					
					Doctor Name: <input id="docName" name="docName" type="text" class="form-control" >
					<br>
					
					Age: <input id="age" name="age" type="text" class="form-control" >
					<br>
					
					Specialization: <input id="spec" name="spec" type="text" class="form-control" >
					<br>
					
					Arrive Time: <input id="arrive" name="arrive" type="text" class="form-control" >
					<br>
					
					Leave Time: <input id="leave" name="leave" type="text" class="form-control" >
					<br>
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				
				<%	
					out.print(session.getAttribute("statusMsg"));
				%>
				
				<br>
				
				<%
					Doctor doc = new Doctor(); 
					out.print(doc.readDoctors());
				%>
					
				<div class="alert alert-success">
					<% out.print(session.getAttribute("statusMsg"));%>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>