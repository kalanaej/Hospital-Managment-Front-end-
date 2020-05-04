$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts--------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
			
	// Form validation-----------------
	var status = validateItemForm();
			
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
			
	// If valid------------------------
	var type = ($("#hidDoctorIDSave").val() == "") ? "POST" : "PUT";
			
	$.ajax(
	{
		url : "DoctorsAPI",
		type : type,
		data : $("#formDoctor").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onItemSaveComplete(response.responseText, status);
		}
	});
});

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidDoctorIDSave").val($(this).closest("tr").find('#hidDoctorIDUpdate').val());
	$("#doctorID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#hospitalName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#docName").val($(this).closest("tr").find('td:eq(2)').text());
	$("#age").val($(this).closest("tr").find('td:eq(3)').text());
	$("#spec").val($(this).closest("tr").find('td:eq(4)').text());
	$("#arrive").val($(this).closest("tr").find('td:eq(5)').text());
	$("#leave").val($(this).closest("tr").find('td:eq(6)').text());
});


function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divDoctorsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidDoctorIDSave").val("");
	$("#formDoctor")[0].reset();
}

$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "DoctorsAPI",
		type : "DELETE",
		data : "docID=" + $(this).data("docid"),
		dataType : "text",
		complete : function(response, status)
		{
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divDoctorsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}
//CLIENTMODEL=========================================================================
function validateItemForm()
{
	if ($("#hospitalName").val().trim() == "")
	{
		return "Insert Hospital Name.";
	}
	
	if ($("#doctorID").val().trim() == "")
	{
		return "Insert Doctor ID.";
	}
	
	var doctorID = $("#doctorID").val().trim();
	
	if (doctorID == "DOC")
	{
		return "You have to enter number After DOC";
	}
	
	if ($("#docName").val().trim() == "")
	{
		return "Insert Doctor Name.";
	}
	
	if ($("#age").val().trim() == "")
	{
		return "Insert Age.";
	}
	
	var age = $("#age").val().trim();
	
	// check age is numerical value
	if (!$.isNumeric(age))
	{
		return "Insert a numerical value for Age.";
	}
	
	// validate age range
	if (age > 75 || age < 25)
	{
		return "Age should be between 25 and 75";
	}
	
	if ($("#spec").val().trim() == "")
	{
		return "Insert Item Specialization";
	}
	
	if ($("#arrive").val().trim() == "")
	{
		return "Insert Arrive Time";
	}
	
	if ($("#leave").val().trim() == "")
	{
		return "Insert Leave Time";
	}
	
	return true;
}