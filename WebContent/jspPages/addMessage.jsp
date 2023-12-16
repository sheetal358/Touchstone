<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc"%>

<mvc:invoke action="fetchGroups"/>

<div class="span7">
	<form id="messageFrm">
	<table class="table table-striped table-bordered">
		<tr><td>
				<table class="table table-striped table-bordered">
					<thead><tr style="background-color: #0088cc;color: white;">
					<th><input id="selectall" type="checkbox" name="select">All</th><th>Group Name</th></tr></thead>
					<tbody>	
						<c:forEach items="${groups}" var="group">
							<tr>
								<td>
									<input class="case"  type="checkbox" name="check" value="${group.groupId}"/>
								</td>
								<td>${group.name}</td>
							</tr>
						</c:forEach>			
					</tbody>
				</table>
			</td><td><div>			
				<label>Message:</label>
				<textarea rows="5" cols="500" id="msg" name="msg"></textarea><br>
				<input type="button" id="btnSend" value="Send" class="btn-primary btn">
				</div>
				</td>
			</tr>
		</table>
	</form>
	<p><a href="#" id="viewMessage" >View All Message</a></p>
</div>



<script type="text/javascript">
$(document).ready(function(){
	
	$("#viewMessage").click(function(){
		 loadHomeBody("jspPages/viewAllMessage.jsp");
	});
	
	$('#selectall').click(function (e) {
	    $(this).closest('table').find('td input:checkbox').prop('checked', this.checked);
	});
	
$("#msg").click(function(){$("#msg").css("background-color","white");});
	
	$("#btnSend").click(function(){
		var msg=$("#msg").val();
		if($('input[type=checkbox]:checked').length == 0)
	    {
	        alert('Please select atleast one checkbox');
	    }		 
		else if(msg=="")
			{
				$("#msg").css("background-color","#FF9A9A");
			}
		else
			{
				var frmData=$("#messageFrm").serialize();
				makeAjaxCall("addMessage.process",frmData,resetForm);			
			} 
	});
});

function resetForm(data)
{
	alert(data);
	$("#messageFrm")[0].reset();
}
</script>