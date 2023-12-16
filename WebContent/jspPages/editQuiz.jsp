<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc" %>

<mvc:invoke action="quizLoader" />

	<style>
	#qNumber td
{
	border: 0px;
}
	</style>
	
	
			<div class="span6 offset1">
				<form  id="editQuizForm">
				<span id="num" class="hide">0</span>
				<fieldset>
				<div class="row">
				<table class="table table-bordered table-condensed"><tbody>
				<tr><td><label for="qName">Quiz Name:</label>
				<input type="hidden" id="quizId" name="quizId" value="${quiz.id}"/>
				<input type="text" name="name"  value="${quiz.name}"/></td>
				<td><label id="qTime">Duration:</label>
				<input type="text" id="Time" name="duration" value="${quiz.duration}"/></td></tr>				
				</tbody>
				</table>				
				</div>
				
		<c:choose>
			<c:when test="${! empty qset}">
				<div class="row">
					<table class="table table-striped table-bordered">
					<thead><tr style="background-color: #0088cc;color: white;">
					<th>Description</th><th>Type</th><th>Difficulty Level</th><th>Action</th><th>Action</th></tr></thead>
					<tbody>					
					<c:forEach items="${qset}" var="quest">
					<tr>
					<td>${quest.description}</td>
					<td>
					<c:if test="${quest.type==1}">True/False</c:if>
					<c:if test="${quest.type==2}">Multi-Choice</c:if>
					</td>
					<td>
					<c:if test="${quest.difficultyLevel==1}">Easy</c:if>
					<c:if test="${quest.difficultyLevel==2}">Medium</c:if>
					<c:if test="${quest.difficultyLevel==3}">Hard</c:if>
					</td>					
					<td><a href="#" id="editQuest" rel="${quest.id}" onclick="editQuest(this)"><i class="icon-edit"></i></a></td>					
					<td><a href="#" id="deleteQuest" rel="${quest.id}" onclick="deleteQuest(this)"><i class="icon-remove"></i></a></td>					
					</tr>
					</c:forEach>
					</tbody>
					</table>
					<p><input type="button" id="btnUpdate" value="Update" class="btn btn-primary"/>	
						<input type="button" id="btnBack" value="Back" class="btn btn-primary"/>
						<input type="button" id="btnAdd" value="Add More Question" class="btn btn-primary"/>			
					</p>				
					</div>
			 </c:when>
			 <c:otherwise>
			 No Question found.
			 </c:otherwise>
		</c:choose>			
				</fieldset>
				</form>
		</div>	
		
<script type="text/javascript">

$(document).ready(function(){
	$("#btnUpdate").click(function(){
		var frm_data=$("#editQuizForm").serialize();
		$.post("updateQuiz.process",frm_data,function(data){
				alert(data);
		});
	});
	
	$("#btnBack").click(function(){
		loadHomeBody("jspPages/viewQuiz.jsp");
	});
	
	$("#btnAdd").click(function(){
		loadHomeBody("jspPages/addQuestionToQuiz.jsp");
	});
	
});



function editQuest(alink){
	var id=$(alink).attr('rel');
	loadHomeBody("jspPages/editQuestion.jsp",{"qId":id});
}

function deleteQuest(alink){
	var id=parseInt($(alink).attr('rel'));
	
	if(confirm("Are you sure to remove this question ?"))
		{
		  makeAjaxCall("removeQuestion.process",{'qId':id},removeQuestion);
		}
}			

function removeQuestion(data)
{
	alert(data);
	 var id=$("#quizId").val();
	 loadHomeBody("jspPages/editQuiz.jsp",{"id":id});
}
	</script>			
		