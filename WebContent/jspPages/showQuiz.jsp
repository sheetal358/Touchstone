<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc" %>

<mvc:invoke action="showQuiz" />
			<c:choose>
			<c:when test="${! empty qList}">
			<div class="span5 offset1" >
					<table class="table table-striped table-bordered">
					<thead><tr style="background-color: #0088cc;color: white;">
					<th>Name</th><th>Duration</th><th>CreatedOn</th><td>Status</td></tr></thead>
					<tbody>					
					<c:forEach items="${qList}" var="quiz">
					<tr>
					<td>${quiz.name}</td>
					<td>${quiz.duration}</td>
					<td>${quiz.createdOn}</td>
					<td>
					<c:choose>
					<c:when test="${quiz.attempted ==0}">
						<a href="#" class="attemptQuiz" rel="${quiz.id}"><i class="icon-question-sign"></i>&nbsp;Attempt</a>
					</c:when>
					<c:otherwise>
						<i class="icon-ok-sign"></i>&nbsp;Attempted
					</c:otherwise>
					</c:choose>
					</td>
					</tr>
					</c:forEach>
					</tbody>
					</table>				
			 </div> 
			 </c:when>
			 <c:otherwise>
			 No quiz found.
			 </c:otherwise>
			 </c:choose>
			 
	<script type="text/javascript">
		$(document).ready(function(){
			$(".attemptQuiz").click(function(){
				if(confirm('Are you sure to attempt this quiz?'))
					{
						var qId=$(this).attr('rel');
						loadHomeBody("jspPages/attemptQuiz.jsp",{'qId':qId});
					}
			});
			
		});

</script>	
		
		
	