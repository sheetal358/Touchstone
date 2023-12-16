<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc"%>

<mvc:invoke action="quizByGroup"/>


	<div class="span4 offset1">
		<table class="table table-bordered btn-primary">
			<tbody>	
					<tr>
						<td>	<label>Select Group:</label>
								<select id="groupList" name="groupList">
									<option disabled="disabled" selected="selected">Select Group</option>
									<c:forEach items="${glist}" var="group">
										<option value="${group.groupId}">${group.name}</option>
									</c:forEach>
								</select>
						</td>
						<td>
								<label>Select Quiz:</label>
								<select id="quizList" name="quizList">
									<option disabled="disabled" selected="selected">Select Quiz</option>
									<c:forEach items="${qlist}" var="quiz">
										<option value="${quiz.id}">${quiz.name}</option>
									</c:forEach>
								</select>
						</td>	
					</tr>
			</tbody>
		</table>
			<a><input type="button" id="btnAssign" value="Assign" class="btn btn-primary"/></a>
	</div>
	
	
	<script type="text/javascript">
	$(document).ready(function(){
		
		$("#btnAssign").click(function(){
			var gval=$("#groupList").val();
			var qval=$("#quizList").val();
			makeAjaxCall("quizToGroup.process",{'gId':gval,'qId':qval},showNotification);
		});
	});
	
	
	</script>