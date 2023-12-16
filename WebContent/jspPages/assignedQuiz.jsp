<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc"%>

<mvc:invoke action="assignedQuiz"/>


	<div class="span4 offset1">
	<c:choose>
		<c:when test="${! empty quizs}">
		
		<table class="table table-bordered">
			<thead>
				<tr style="background-color: #0088cc;color: white;"><td>Id</td><td>Name</td><td>Duration</td><td>Created On</td><td>Action</td></tr>
			</thead>
			<tbody>	
				<c:forEach items="${quizs}" var="quiz">
					<tr>
						<td>${quiz.id}</td><td>${quiz.name}</td><td>${quiz.duration}</td><td>${quiz.createdOn}</td>	
						<td><a href="#" id="deleteQuiz" rel="${quiz.id}" onclick="deleteQuiz(this)" class="text-error"><i class="icon-remove"></i>&nbsp;Unassign</a></td>
					</tr>
					</c:forEach>
			</tbody>
		</table>
		</c:when>
		<c:otherwise>
			<p>No quiz available.</p>
		</c:otherwise>
	</c:choose>	
	</div>
	
	
	<script type="text/javascript">
	function deleteQuiz(alink){
		var id=parseInt($(alink).attr('rel'));
		 if(confirm("Are you sure to unassign this quiz?"))
			{
			 makeAjaxCall("unassignQuiz.process",{'qId':id},unassignQuiz);
		} 
	}
	
	function unassignQuiz(data)
	{
		alert(data);
		loadHomeBody("jspPages/assignedQuiz.jsp");
	}
	</script>