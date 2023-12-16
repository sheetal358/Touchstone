<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html style="height: 100%;" lang="en-US">
<head>
<meta http-equiv="content-type" content="text/html; charset=windows-1252">

<title>TouchStone: an assessment platform.</title>
 
<c:import url="htmlPages/css_js.html"></c:import>
</head>
<body style="background-color: #3D5998;"> 
<br/>
<div class="container" style="background-color: #ffffff;">
     
     
	<div class="row">
	<br/>
		<div class="span2 offset1">
			  <a id="top" href="#">
			  <img src="img/touchstone.png" alt="TouchStone" style="border:0;margin-top:5px;" height="67" width="201"></a>
		</div>
		<div class="span4 offset5">
			  <form class="form-inline" action="login.process">
				<input type="text" name="mailId" class="input-small" placeholder="Email">
				<input type="password" name="password" class="input-small" placeholder="Password">
				<button type="submit" class="btn btn-small btn-primary">Sign in</button>
				
			</form>	
			<a href="#" id="forgotPassword">Forgot your password?</a>
		</div>
	</div>
<hr/>
<div id="page" class="row">

<div class="span4 offset1 hero-unit">

<p class="text-justify"><strong>TouchStone</strong> is an assessment platform for academic institutions & organizations.<br/> 
Using this platform assessment quizzes can be created having different type of questions and
can be assigned to students, trainees and employees. An assignee can attempt the quizzes, view their result and compare it with peers.
</p>
<p><a class="btn btn-primary btn-large">Learn more</a></p>
</div>							
			
	<c:import url="htmlPages/signup.html" />	
	
</div>
<hr/>
<!-- footer placeholder -->
<div id="footer" class="row">
			
<c:import url="jspPages/footer.jsp" />		
			
</div>
<br/>
<!-- modal div which is displayed to show a message. -->
<div class="modal hide" id="alert_div">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
			<i class="icon-remove"></i></button>
			<h3>Password change status...</h3>
			</div>
			<div class="modal-body">
				<div class="alert">
					<p id="alertText"></p>
				</div>
			</div>
</div>
<!-- modal form which is displayed to change password -->
	<div id="password_div" class="modal hide">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
			<i class="icon-remove"></i></button>
			<h3>Change Your Password</h3>
			</div>
			<div class="modal-body">
		 		<form  method="post" id="changePasswordFrm">
				<fieldset>
				<span id="mailFields">
				<label for="mailId">MailId:</label> 
				<input type="text"  name="mailId" />&nbsp;&nbsp;
				<span class="text-error" id="errMail"></span>
				</span>
				<span class="hide" id="ansFields">
				<label for="question">Security Question:</label> 
				<input type="text"  name="question" />
				<label for="answer">Answer:</label> 
				<input type="text"  name="answer" />&nbsp;&nbsp;<span class="text-error" id="errAnswer"></span>
				</span>
				<span class="hide" id="passFields">
				<label for="password">Password:</label> 
				<input type="password"  name="password" />
				<label for="cpassword">ConfirmPassword:</label> 
				<input type="password"  name="cpassword" />
				&nbsp;&nbsp;<span class="text-error" id="errPass"></span>
				</span>
				<input type="hidden"  name="userInfo" />
				<p><input type="button" id="btnReset" value="update" class="btn btn-primary"/></p>
				</fieldset>
				</form>
			 </div> 
			 <div class="modal-footer">
			<p id="passFooter">Step 1 of 3</p>
			</div>
			 
			</div>
			
		

</div><!-- container div ends -->
<br/>
<script type="text/javascript">
			$(document).ready(function(){
				
				/* Handler of signupForm */
								
				$("#newUserForm :radio").click(function(){
					if($(this).val()==2)
						$("#gcode").removeClass("hide");
					else
						$("#gcode").addClass("hide");
				});
				
				$("#groupCode").change(function(){
					var val=$("#newUserForm input[type='radio']:checked").val();
					var gc=$("#groupCode").val();
					var codeRegex =/^[A-Z0-9]+$/;
					
					if(val==2)
						{ 	
							if(gc=="")
							{
								$("#errCode").html("GroupCode can't be empty.");
								$("#errCode").css("color","#AC5959");
							}
							else if(gc.match(codeRegex))
							{
								$.post("validateGroupCode.process",{'code':gc},function(data){
									$("#errCode").html(data);
		 							$("#errCode").css("color","green");
		 	  	             	if(data.match("Code matched"))
		 	  	            	 {
		 	  	            		$("#errCode").html(data);
		 	  	            	 }
		 	  	             	else
		 	  	            	 {
		 	  	            		$("#errCode").html(data);
		 	  	          			$("#errCode").css("color","#AC5959");
		 	  	            	 }
								});
							}
							else
							{
								$("#errCode").html("Only 5 charactor in uppercase.");
								$("#errCode").css("color","#AC5959");
							}
						}					
				});
				
				
				
				$("#name").change(function(){
					var n=$("#name").val();
					var nameRegex = /^[a-zA-Z ]*$/; 
					if(n=="")
					$("#errName").html("Name can't be empty.");
					else if(n.match(nameRegex))
					$("#errName").html("");
					else
						$("#errName").html("Name can contain only alphabets and space.");
				});
				
				$("#mailId").change(function(){
					var m=$("#mailId").val();
					var mailRegex=/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/; 
					if(m=="")
						{
					$("#errMail").html("MailId can't be empty.");
					$("#errMail").css("color","#AC5959");
						}
					else if(m.match(mailRegex))
					{
					$("#errMail").html("");
					
					var mail=$("#mailId").val();
                    
 					$.post("validateMailId.process",{'mailId':mail},function(data){
 						$("#errMail").html(data);
 						$("#errMail").css("color","#AC5959");
 	  	             if(data.match("Already"))
 	  	            	 {
 	  	            		$("#errMail").html(data);
 	  	            	 }
 	  	             else
 	  	            	 {
 	  	            	$("#errMail").html("Is Available.");
 	  	          		$("#errMail").css("color","green");
 	  	            	 }
 					});
					
					}
					else
						{
						$("#errMail").html("MailId is not valid.");
						$("#errMail").css("color","#AC5959");
						}
				});
				$("#pass").change(function(){
					var p=$("#pass").val();
					var passRegex=/^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$/;
					if(p=="")
					$("#errPass").html("Password can't be empty.");
					else if(p.match(passRegex))
					$("#errPass").html("");
					else
						$("#errPass").html("Password must contain alphabets and number.");
				});
				
				$("#newUserForm input[type='submit']").click(function(){
						var ercount=0;					
						
						var val=$("#newUserForm input[type='radio']:checked").val();
						var gc=$("#groupCode").val();
						var codeRegex =/^[A-Z0-9]+$/;
						if(val==2)
							{ 							
								if(gc=="")
								{
									$("#errCode").html("GroupCode can't be empty.");
									$("#errCode").css("color","#AC5959");
									ercount+=1;
								}
								else if(gc.match(codeRegex))
								{
									var cod=$("#errCode").html();
			 	  	             	if(cod.match("Code matched"))
			 	  	            	 {
			 	  	            		
			 	  	            	 }
			 	  	             	else
			 	  	            	 {
			 	  	             		ercount+=1;
			 	  	            	 }									
								}
								else
								{
									$("#errCode").html("Only 5 charactor in uppercase.");
									$("#errCode").css("color","#AC5959");
									ercount+=1;
								}
							}
						
						
						var n=$("#name").val();
						var nameRegex = /^[a-zA-Z ]*$/; 
						if(n=="")
							{
						$("#errName").html("Name can't be empty.");
						ercount+=1;
							}
						else if(n.match(nameRegex))							
						$("#errName").html("");
						else
							{
							$("#errName").html("Name can contain only alphabets and space.");
							ercount+=1;
							}
					
						var m=$("#mailId").val();
						var mailRegex=/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/; 
						if(m=="")
						{
						   $("#errMail").html("MailId can't be empty.");
						   $("#errMail").css("color","#AC5959");
						   ercount+=1;
						}
						else if(m.match(mailRegex))
						{ 
							var msg=$("#errMail").html();
							if(msg.match("Already"))
								{
								
								ercount+=1;
								}
						}
						else
						{
							$("#errMail").html("MailId is not valid.");
							$("#errMail").css("color","#AC5959");
							ercount+=1;
						}
					
					
						var p=$("#pass").val();
						var passRegex=/^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$/;
						if(p=="")
							{
						$("#errPass").html("Password can't be empty.");
						ercount+=1;
							}
						else if(p.match(passRegex))
							{
						$("#errPass").html("");
							}
						else
							{
							$("#errPass").html("Password must contain alphabets and number.");
							ercount+=1;
							}
				if(ercount>0)
					return false;
				else
					return true;
					
				});
				
				/*Handler to display Reset Password form*/
				
				$("#forgotPassword").click(function(){
					$("#password_div").modal("show");
				});
				
				/*Reset Password Handler*/
				
				$("#btnReset").click(
						function(){
					var v=$(this).val();
					console.log("Button caption: "+v);
					if(v=="update")
					{
						/* Fetch data from server using mailId and display fields
						   to receive security question.
						 */
					
					var mid=$("#changePasswordFrm input[name='mailId']").val();
						$("#mailFields").addClass("hide");
					
					$.post("fetchUserDetails.process",
							{'mailId' : mid}, function(data){
								
								$("#changePasswordFrm input[name='userInfo']").val(data);
								console.log(data);
								if(data=="Invalid MailId")
								{
								$("#changePasswordFrm #errMail").html(data);
								}
								else
								{
								var user=$.parseJSON(data);
								$("#changePasswordFrm input[name='question']").val(user.question);
								$("#ansFields").removeClass("hide");
								$("#mailFields").addClass("hide");
								$("#passFooter").html("Step 2 of 3");
								$("#btnReset").val("Match Answer");
								}
							});
					}
					else if(v=="Match Answer")
					{
						/* Value of ans is compared if valid password fields are shown. */
					 var ans=$("#changePasswordFrm input[name='answer']").val();
					 var user=$.parseJSON($("#changePasswordFrm input[name='userInfo']").val());
					 console.log("Answer given: "+ans);
					 console.log("Answer in db: "+user.ans);
					 if(ans==user.ans)
					   {
						 $("#ansFields").addClass("hide");
						 $("#passFields").removeClass("hide");
						 $(this).val("Reset Password");
						 $("#passFooter").html("Step 3 of 3");
						 $("#errAnswer").html("");
					   }
					 else
						 {
						 $("#changePasswordFrm #errAnswer").html("Answer not matched.");
						 }
					}
					else
						{
						/* Send request to the server to update password. */
						var p=$("#changePasswordFrm input[name='password']").val();
						var cp=$("#changePasswordFrm input[name='cpassword']").val();
						if(p==cp)
							{
							 var user=$.parseJSON($("#changePasswordFrm input[name='userInfo']").val());
							 $.post("changePassword.process",
									 {'id' :user.id, 'password' : p}, 
									 function(data){
								$("#password_div").modal("hide");
								$("#alertText").html(data);
								$("#alert_div").modal("show");
							 });		
							}
						else
							{
							$("#changePasswordFrm #errPass").html("Password & Confirm password not matched.");
							}
						
						
						}
					});
				
			});
			</script>
</body>   
</html>			
		
		
		
		