<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html style="height: 100%;" lang="en-US">
<head>
<meta http-equiv="content-type" content="text/html; charset=windows-1252">
<title>${title}</title> 
<c:import url="../htmlPages/css_js.html" />
</head>
<body style="background-color: #3D5998;"> 
<br/>
<div class="container" style="background-color: #ffffff;">     
     
	<div class="row">
	<br/>
		<div class="span2 offset1">
			  <a id="top" href="#"><img src="img/touchstone.png" alt="TouchStone" style="border:0;margin-top:5px;" height="67" width="201"></a>
		</div>
		<div class="span3 offset6">
			<div class="dropdown"><span id="welcomeMsg"> Welcome,${user.name}</span>
			<a href="#" id="drop" role="button" class="dropdown-toggle"
			data-toggle="dropdown"><i class="icon-user"></i><b class="caret"></b></a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="drop">
			<li><a tabindex="-1" href="#" id="profileLink">Profile</a></li>
			<li class="divider"></li>
			<li><a tabindex="-1" href="logout.process">Logout</a></li>
			</ul>
			</div>
			 
		</div>
	</div>
<hr/>
<div id="page" class="row">
<c:choose>
<c:when test="${user.role==1}">
<c:import url="../htmlPages/assessorMenu.html" />
</c:when>
<c:otherwise>
<c:import url="../htmlPages/userMenu.html" />
</c:otherwise>
</c:choose>

<span id="home_body">
<input type="hidden" id="formstate"> <br/>
<c:import url="${body}" />
</span>

</div>
<hr/>

<!-- footer placeholder -->
<div id="footer" class="row">			
<c:import url="footer.jsp" />			
</div>

<!-- modal div which is displayed to show a message. -->
<div class="modal hide" id="alert_div">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
			<i class="icon-remove"></i></button>
			<h3>Received Response...</h3>
			</div>
			<div class="modal-body">
				<div class="alert">
					<p id="alertText"></p>
				</div>
			</div>
</div>


<!-- modal form which is displayed on an event -->
	<div id="profile_div" class="modal hide">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
			<i class="icon-remove"></i></button>
			<h3>Update Profile</h3>
			</div>
			<div class="modal-body">
		 		<form  method="post" id="updateProfileFrm">
				<fieldset>
			 <label for="name">Name:</label> 
				<input type="text"  name="name" />&nbsp;&nbsp;<span class="text-error" id="errName"></span>
				<label for="mailId">MailId:</label> 
				<input type="text"  name="mailId" />&nbsp;&nbsp;<span class="text-error" id="errMail"></span>
				<label for="password">Password:</label> 
				<input type="text"  name="password" />&nbsp;&nbsp;<span class="text-error" id="errPass"></span>
				<p><input type="button" id="btnUpdate" value="update" class="btn btn-primary"/></p>
				</fieldset>
				</form>
			 </div> 
			</div>
			
			
			


<br/>
</div><!-- container div ends -->
<br/>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#profileLink").click(function(){
		
		// to fetch user details to be updated.
		$.get("userProfile.process",function(data){
			var user=$.parseJSON(data);
		$("#updateProfileFrm input[name='name']").val(user.name);
		$("#updateProfileFrm input[name='mailId']").val(user.mailId);
		$("#updateProfileFrm input[name='password']").val(user.password);		
		$("#profile_div").modal("show");		
		});		
	});
	
	// to update details
$("#btnUpdate").click(function(){
		var frm_data=$("#updateProfileFrm").serialize();
		console.log(frm_data);
		$.post("updateProfile.process",frm_data,function(data){
		var user=$.parseJSON(data);
		$("#welcomeMsg").html("Welcome, "+user.name);
		$("#profile_div").modal("hide");	
		$("#alertText").html("successfully updated.");
		$("#alert_div").modal("show");
		});		
	});	
});

function showNotification(msg)
{
	var msgDiv='<div class="span4 offset2 text-info">'+
		msg+'</div>';
	$("#home_body").html(msgDiv);
}
	
function loadHomeBody(urlSource,requestData)
{	
	if(requestData==null)
	{
		$.ajax({
			url: urlSource + "?s=" + Math.random().toString(), 
			success: function(data){
				$("#home_body").html(data);
			}, 
			cache: false
			});	
	}
	else
		{
		$.ajax({
			url: urlSource, 
			data:requestData,
			success: function(data){
				$("#home_body").html(data);
			}, 
			cache: false
			});	
		}
}

function makeAjaxCall(urlSource,requestData,callbackFunction)
{
	if(requestData==null)
		{
		$.ajax({
			url: urlSource, 
			success: callbackFunction, 
			cache: false
			});	
		}
	else
		{
		$.ajax({
			url: urlSource, 
			data:requestData,
			success: callbackFunction, 
			cache: false
			});			
		}
}
</script>

</body>   
</html>			
		
		
