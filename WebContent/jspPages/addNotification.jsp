<div class="span4 offset1">
	<form id="noteFrm">
		<label>Notification:</label>
		<textarea rows="5" cols="500" id="note" name="note"></textarea>
		<input type="button" id="btnSend" value="Send" class="btn-primary btn">
	</form>
	<p><a href="#" id="viewNote" >View All Notifications</a></p>
</div>



<script type="text/javascript">
$(document).ready(function(){
	
	$("#note").click(function(){$("#note").css("background-color","white");});
	
	$("#btnSend").click(function(){
		var note=$("#note").val();
		if(note=="")
			{
				$("#note").css("background-color","#FF9A9A");
			}
		else
			{
				makeAjaxCall("addNotification.process",{'note':note},resetForm);			
			}
	});
	
	$("#viewNote").click(function(){
		 loadHomeBody("jspPages/viewAllNotification.jsp");
	});
	
});

function resetForm(data)
{
	alert(data);
	$("#noteFrm")[0].reset();
}
</script>