<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc" %>

<mvc:invoke action="viewQuiz" />
			<c:choose>
			<c:when test="${! empty qlist}">
			<div class="span6 offset1" >
					<table class="table table-striped table-bordered">
					<thead><tr style="background-color: #0088cc;color: white;">
					<th>Name</th><th>Duration</th><th>Created On</th><th>Status</th><th>Action</th><th>Action</th></tr></thead>
					<tbody>					
					<c:forEach items="${qlist}" var="quiz">
					<tr>
					<td>${quiz.name}</td>
					<td>${quiz.duration}</td>
					<td>${quiz.createdOn}</td>
					<td>
					<c:if test="${quiz.status==1}">ASSIGNED</c:if>
					<c:if test="${quiz.status==2}">UNASSIGNED</c:if>
					</td>
					<td><a href="#" id="editQuiz" rel="${quiz.id}" onclick="editQuiz(this)"><i class="icon-edit"></i>&nbsp;Edit</a></td>					
					<td><a href="#" id="deleteQuiz" rel="${quiz.id}" onclick="deleteQuiz(this)"><i class="icon-remove"></i>&nbsp;Delete</a></td>
					
					</tr>
					</c:forEach>
					</tbody>
					</table>				
			 </div> 
			 </c:when>
			 <c:otherwise>
			 No Quiz found.
			 </c:otherwise>
			 </c:choose>
			 
		
		
		
	<script type="text/javascript">

	
		function editQuiz(alink){
			var id=$(alink).attr('rel');
			loadHomeBody("jspPages/editQuiz.jsp",{"id":id});
		}
		function deleteQuiz(alink){
			var id=parseInt($(alink).attr('rel'));
			if(confirm("Are you suer to remove this quiz ?"))
				{
				 makeAjaxCall("removeQuiz.process",{'qId':id},viewQuiz);
				}
		}
		
		function viewQuiz(data)
		{
			alert(data);
			loadHomeBody("jspPages/viewQuiz.jsp");
		}
	</script>			
		