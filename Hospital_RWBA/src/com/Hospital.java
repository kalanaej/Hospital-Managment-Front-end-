package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {

	
	
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/hospital","root", ""); 
			
			//for testing purpose
			System.out.print("Successfully connected");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			
		}
		
		return con;
		
		
	}
	
	
	
	public String insertHoapitalDet(String MOHcode, String Hos_name, String Hos_email, String Hos_managername,  String Hos_address, String Hos_phoneNumber )  
	{   
		String output = ""; 
	
	 
	  try   
	  {    
		  Connection con = connect(); 
	  
	 
		  if (con == null)    
	   {
		   return "Error while connecting to the database for inserting.";
		   
	   } 
		  
		  
		
		  
	 
	   // create a prepared statement    
	   String query = " insert into hospital "
	   		+ "(`hospitalID`,`mohCode`,`hospitalName`,`emailAddress`,`managerName`,`address`,`telephoneNo`)"     
			   + " values (?, ?, ?, ?, ?, ?, ?)"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	   
	 
	   // binding values   
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, MOHcode);    
			preparedStmt.setString(3, Hos_name);    
			preparedStmt.setString(4, Hos_email);
			preparedStmt.setString(5, Hos_managername); 
			preparedStmt.setString(6, Hos_address);
			preparedStmt.setString(7, Hos_phoneNumber);
			
				
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			
			//output = "Inserted successfully"; 
			
			String newHospitals = readHospitalDetails();
			 output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}"; 
			
		  
			
	  }
	  catch (Exception e)   
	  {    
		 // output = "Error while inserting the hospital details.";   
		  output = "{\"status\":\"error\", \"data\":\"Error while inserting the hospital.\"}"; 
		  System.err.println(e.getMessage());   
		  
	  }
	return output; 
	  }
	

	public String readHospitalDetails()  {
		
		String output = ""; 
	
	 
	  try   
	  {   
		  Connection con = connect(); 
	 
	 
	   if (con == null)   
	   {
		   return "Error while connecting to the database for reading.";
		   
	   } 
	   
	 
	   // Prepare the html table to be displayed    
	output = "<table border='1'><tr>"
			+ "<th>MOH Code</th>"
			+ "<th>Hospital Name</th>"
			+ "<th>Email</th>"
			+ "<th>Manager name</th>"
			+ "<th>Address</th>"
			+ "<th>TelNo</th>"
			+ "<th>UPDATE</th>"
			+ "<th>REMOVE</th>"
			+ "</tr>"; 
	 
	   String query = "select * from hospital";    
	   Statement stmt = con.createStatement();    
	   ResultSet rs = stmt.executeQuery(query); 
	
	
	// iterate through the rows in the result set   
	   while (rs.next())    
	   {    
		   String hospitalID = Integer.toString(rs.getInt("hospitalID"));     
		   String MohCode = rs.getString("mohCode");     
		   String hosName = rs.getString("hospitalName");     
		   String emailAdd = rs.getString("emailAddress");  
		   String managerName = rs.getString("managerName"); 
		   String address = rs.getString("address"); 
		   String telNo = rs.getString("telephoneNo"); 
	   
	    // Add into the html table     
			output += "<tr><td><input id='hidHospitalIDUpdate' name='hidHospitalIDUpdate' type='hidden' "
					+ "value='" + hospitalID + "'>" + MohCode + "</td>";     
		   output += "<td>" + hosName + "</td>";     
		   output += "<td>" + emailAdd + "</td>";     
		   output += "<td>" + managerName + "</td>"; 
		   output += "<td>" + address + "</td>"; 
		   output += "<td>" + telNo + "</td>"; 
	 
	    // buttons     
		  // output += "<td><input name=\"btnUpdate\" type=\"button\"        "
		   //		+ "value=\"Update\" class=\"btn btn-secondary\"></td>"      
			//	   + "<td><form method=\"post\" action=\"hospital.jsp\">"      
		   	//	+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"      class=\"btn btn-danger\">"     
			//	   + "<input name=\"hospitalID\" type=\"hidden\" value=\"" + hospitalID      + "\">" + "</form></td></tr>";    } 
	 
			output += "<td><input name='btnUpdate' type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
					 + "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger'data-hospitalid='"
					 + hospitalID + "'>" + "</td></tr>"; 
		   
	   }   
	   con.close(); 
	 
	   // Complete the html table    
	   output += "</table>";  
	   }   

	catch (Exception e)   
	{    
		output = "Error while reading the hospital details.";    
		System.err.println(e.getMessage());   
		
	} 
	 
	  return output;  
	  
	}

	
	public String updateHospitalDet(String ID, String MOHcode, String Hos_name, String Hos_email, String Hos_managername,  String Hos_address, String Hos_phoneNumber)  
	
	{   String output = ""; 
	 
	  try   {    
		  Connection con = connect(); 
	 
	   if (con == null)    
	   {
		   return "Error while connecting to the database for updating."; 
	   
	   } 
	 
	   // create a prepared statement    
	   String query = "UPDATE hospital SET mohCode=?,hospitalName=?,emailAddress=?,managerName=?,address=?,telephoneNo=?      WHERE hospitalID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setString(1, MOHcode);    
	   preparedStmt.setString(2, Hos_name);    
	   preparedStmt.setString(3, Hos_email);   
	   preparedStmt.setString(4, Hos_managername);
	   preparedStmt.setString(5, Hos_address); 
	   preparedStmt.setString(6, Hos_phoneNumber); 
	   preparedStmt.setInt(7, Integer.parseInt(ID)); 
	 
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	  // output = "Updated successfully";  
	   String newHospitals = readHospitalDetails();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newHospitals + "\"}"; 
	   
	  }   
	  
	  catch (Exception e)  
	  {    
		  //output = "Error while updating the hospital details.";    
		  output = "{\"status\":\"error\", \"data\":\"Error while updating the hospital.\"}"; 
		  System.err.println(e.getMessage());   
		  
	  } 
	 
	  return output;  
	  
	} 
	 
	 public String deleteHospitalDet(String hospitalID) 
	 {   
		 String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)    
	   
	   {
		   return "Error while connecting to the database for deleting.";
	   
	   } 
	 
	   // create a prepared statement    
	   String query = "delete from hospital where hospitalID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setInt(1, Integer.parseInt(hospitalID)); 
	 
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	   //output = "Deleted successfully";   
	   
		String newHospitals = readHospitalDetails();
		 output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}"; 

	   
	  }   catch (Exception e)   
	  
	  {    
		 // output = "Error while deleting the hospital details.";   
		  output = "{\"status\":\"error\", \"data\":\"Error while deleting the hospital.\"}"; 
		  System.err.println(e.getMessage());   
		  
	  } 
	 
	  return output;  
	  
	 } 
	 
	
}
