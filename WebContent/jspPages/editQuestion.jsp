<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc"%>

<mvc:invoke action="editQuestion" />


<div class="span4 offset1">
	<form id="editQuestForm">
		<fieldset>
			<c:choose>
				<c:when test="${question !=null}">
					<div class="row">
						<input type="hidden" id="qType" name="qType" value="${question.type}" />
						<input type="hidden" id="id" name="id" value="${question.id}" />
						<input type="hidden" id="qId" value="${question.quizId}" />
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td><label>Difficulty Level:</label> <select id="dLevel"
										name="dLevel">
											<option value="1" ${question.difficultyLevel=="1"?'selected':''}>Easy</option>
											<option value="2" ${question.difficultyLevel=="2"?'selected':''}>Medium</option>
											<option value="3" ${question.difficultyLevel=="3"?'selected':''}>Hard</option>
									</select></td>
								</tr>
								<tr>
									<td><c:if test="${question.type ==1}">
											<div id="tf">
												<label for="question">Question:</label>
												<textarea rows="4" cols="200" name="questionTF">${question.description}</textarea>
												<label for="option">Correct Answer:</label> <select
													id="answer1" name="answer1">
													<option value="1" ${question.ans=="1"?'selected':''}>True</option>
													<option value="2" ${question.ans=="2"?'selected':''}>False</option>
												</select>
											</div>
										</c:if> <c:if test="${question.type ==2}">
											<div id="mc">
												<label for="question">Question:</label>
												<textarea rows="4" cols="200" name="questionMC">${question.description}</textarea>
												<input type="hidden" name="optCount" value="2" />
												<%
													String[] lChar = {"A", "B", "C", "D", "E", "F", "G","H"};
													int loop = 0;
												%>
												<c:forEach items="${question.options}" var="opt">
													<c:if test="${opt != null}">
														<%
															loop += 1;
														%>
														<c:set var="loopCount" value="<%=loop%>" />
														<c:choose>
															<c:when test="${ loopCount<=2}">
																<label for="option">Option <%=lChar[loop - 1]%>:</label>
																<input type="text" name="option<%=loop%>" value="${opt }" />
															</c:when>
															<c:otherwise>
																<div>
																	<label for="option">Option <%=lChar[loop - 1]%>:</label> 
																	<input type="text" name="option<%=loop%>" value="${opt }" />
																</div>
															</c:otherwise>
														</c:choose>
													</c:if>
												</c:forEach>
												<label for="btnAddOption"></label> 
													<input type="button" id="btnAddOption" value="More Option" class="btn btn-info" />
												<label for="choice">Correct Answer:</label> 
												<select	id="answer2" name="answer2">
													<%
														loop = 0;
													%>
													<c:forEach items="${question.options}" var="opts">
														<c:if test="${opts != null}">
															<%
																loop += 1;
															%>
															<c:set var="loopCount2" value="<%=loop%>" />
															<option value="${loopCount2}" ${question.ans==loopCount2?'selected':''}><%=lChar[loop - 1]%></option>
														</c:if>
													</c:forEach>
												</select>

											</div>
										</c:if></td>
								</tr>
							</tbody>
						</table>
					</div>
					<p>
						<input type="button" id="btnUpdate" value="Update"	class="btn btn-primary" />
						<input type="button" id="btnBack" value="Back" class="btn btn-primary"/>
					</p>
				</c:when>
				<c:otherwise>
			 No Question found.
			 </c:otherwise>
			</c:choose>
		</fieldset>
	</form>

</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#btnUpdate").click(function() {
			var frm_data = $("#editQuestForm").serialize();
			$.post("updateQuestion.process", frm_data, function(data) {
				alert(data);
			});
		});
	
		$("#btnBack").click(function(){
			var id=parseInt($("#qId").val());
			loadHomeBody("jspPages/editQuiz.jsp",{'id':id});
		});
		
		$("#btnAddOption").click(function(){
			var v=$("#mc label[for='option']:last").html();
			var i=parseInt($("#mc select option:last").val())+1;
			if(i>5)
				{
					alert("One question can not have more than 5 options.");
				}
			else
				{
				$("#removeOption").remove();
			$( '<div><label for="option">Option '+nextChar(v)+':</label><input type="text" name="option'+i+'"/><i class="icon-remove" id="removeOption"></i><br></div>').insertBefore("#mc input:last");
			$('<option value="'+i+'">'+nextChar(v)+'</option>').insertAfter("#mc select option:last");
			$("#mc input[name='optCount']").val(i);
			$("#removeOption").click(function(){
				removeOption($(this));		
		});	
				}
		});
		function removeOption(obj)
		{
			$("#mc select option:last").remove();
			$(obj).closest('div').remove();
			if(parseInt($("#mc div").length)>0)
				{
					$('<i class="icon-remove" id="removeOption"></i>').insertAfter("#mc div:last input");
					$("#removeOption").click(function(){
						removeOption($(this));		
				});	
				}		
		}
		
		function nextChar(c) {
		    return String.fromCharCode(c.charCodeAt(c.length-2) + 1);
		}
	});
</script>
