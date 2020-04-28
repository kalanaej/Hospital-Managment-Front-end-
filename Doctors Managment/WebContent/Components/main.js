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
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	$("#formItem").submit();
});


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	$("#hospitalName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#docName").val($(this).closest("tr").find('td:eq(2)').text());
	$("#age").val($(this).closest("tr").find('td:eq(3)').text());
	$("#spec").val($(this).closest("tr").find('td:eq(4)').text());
	$("#arrive").val($(this).closest("tr").find('td:eq(5)').text());
	$("#leave").val($(this).closest("tr").find('td:eq(6)').text());
});


// CLIENTMODEL=========================================================================
function validateItemForm()
{
	if ($("#hospitalName").val().trim() == "")
	{
		return "Insert Item Hospital Name.";
	}
	
	if ($("#age").val().trim() == "")
	{
		return "Insert Age.";
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