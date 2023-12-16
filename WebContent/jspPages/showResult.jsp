<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc"%>
<mvc:invoke action="showResult"/>

	

<div class="span6 offset1">		
	<c:choose>
		<c:when test="${!empty rList}">
		<table class="table table-bordered" id="table">
			<thead>
				<tr style="background-color: #0088cc;color: white;">
					<th>Quiz Id</th><th>Attempted Date</th><th>Time Taken(In Second)</th><th>Result(In Percentage)</th><td>Result on graph</td>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${rList}" var="result">
				<tr>
					<td>
						${result.quizId}
					</td>
					<td>
						${result.date}
					</td>
					<td>
						${result.timeTaken} 'sec.
					</td>
					<td>
						${result.result} %
					</td>
					<td>
					<a href="#" class="graphView" rel="${result.resultId}">Graph view</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
			<center class="hide" id="spinner"><img alt="loading..." id="imgSpinner" src="img/spinner.gif"></center>
			<center><a href="#" class="graphViewForAll" >Graph view For All Quiz</a></center>
	</c:when>
	<c:otherwise>
		<h5>No result available....</h5>
	</c:otherwise>
</c:choose>
</div>
	
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		$(".graphView").click(function(){
			var rId=$(this).attr('rel');
			$("#table").hide();
			$("#spinner").show();
			 makeAjaxCall("resultinGraph.process",{'rId':rId},showResultGraph);			
		});
		
		function showResultGraph()
		{
			loadHomeBody("jspPages/resultGraph.jsp");
		}
		$(".graphViewForAll").click(function(){
			$("#table").hide();
			$("#spinner").show();
			 makeAjaxCall("resultinGraphForAllQuiz.process",null,showResultGraph);
		});
		
	});
	
	</script>
