<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc" %>

<mvc:invoke action="quizByGroup" />

<c:choose>
			<c:when test="${glist !=null}">
			<div class="span5 offset1"  >
				<form id="unassignQuizFrm">
					<table class="table table-striped table-bordered" >
					<thead><tr style="background-color: #0088cc;color: white;">
					<th>
					<label>Select Group</label>
					<select name="group" id="group">
						<option disabled="disabled" selected="selected">Select Group</option>
					<c:forEach items="${glist}" var="group">
						<option value="${group.groupId}">${group.name}</option>
					</c:forEach>
					</select>
					</th><th><label>Select Quiz to UnAssign</label>
					<input id="selectall" type="checkbox" name="select">&nbsp;All&nbsp;[<i class="icon-ok"></i>]</th></tr></thead>
					<tbody>					
					</tbody>
					</table>
					</form>
					<center><img class="hide" alt="loading..." id="imgSpinner" src="img/spinner.gif"></center>			
			 </div> 
			 </c:when>
			 <c:otherwise>
			 No Group found.
			 </c:otherwise>
			 </c:choose>
			 
			 
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#group").change(function(){
			var gval=$("#group").val();
			$("#imgSpinner").show();
			makeAjaxCall("quizByGroupId.process",{'gId':gval},showQuiz);
		});
		
		$('#selectall').click(function (e) {
		    $(this).closest('table').find('td input:checkbox').prop('checked', this.checked);
		});
		
		function showQuiz(objGroup)
		{
			var htmlDetails='';
			var jsonObject=$.parseJSON(objGroup);
			$(jsonObject).each(function(i,v){
				htmlDetails+='<tr><td>'+ v.name + '</td><td><input class="case"  type="checkbox" name="check" value="'+v.id+'"/></td></tr>';
			});
			if(htmlDetails!='')
			htmlDetails+='<tr><td></td><td><input type="button" id="btnUnassign" value="UnAssign" class="btn-primary btn"></td></tr>';
			else
				htmlDetails+="<center>No Quiz Assigned.<center>";
			$("#imgSpinner").hide();
			$("#unassignQuizFrm table tbody").html(htmlDetails);
			$("#btnUnassign").unbind('click').click(function(){
				submitAction(this);
			});
		}
		
		function submitAction()
		{
			if($('input[type=checkbox]:checked').length == 0)
		    {
		        alert('Please select atleast one checkbox');
		    }		 
			else
				{
					var frmData=$("#unassignQuizFrm").serialize();
					makeAjaxCall("unassignQuizByGroup.process",frmData,unassignQuizGrup);
				} 
		}
	});
	
	function unassignQuizGrup(data)
	{
		loadHomeBody("jspPages/unassignQuizByGroup.jsp");
	}
	
</script>