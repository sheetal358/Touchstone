<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc" %>

<%-- <mvc:invoke action="quizLoader" /> --%>

	<style>
	#qNumber td
{
	border: 0px;
}
	</style>
	
	
			<div class="span6 offset1">
				<form  id="addQuestionForm">
				<span id="num" class="hide">0</span>
				<fieldset>
				<div class="row">
				<table class="table table-bordered table-condensed"><tbody>
				<tr><td><label for="qName">Quiz Name:</label>
				<input type="hidden" id="qId" name="qId" value="${quiz.id}"/>
				<input type="text" name="name" readonly="readonly" value="${quiz.name}"/></td>
				<td><label id="qTime">Duration:</label>
				<input type="text" id="Time" readonly="readonly" name="duration" value="${quiz.duration}"/></td></tr>
				<tr><td>				
				<label for="qType">Question Type</label>
				<select id="qType" name="qType">
				<option value="1" selected="selected">True/False</option>
				<option value="2">MultiChoice</option>
				</select>				
				</td><td>
				<label for="difficulty">Difficulty Level</label>
				<select id="dLevel" name="dLevel">
				<option value="1" selected="selected">Easy</option>
				<option value="2">Medium</option>
				<option value="3">Hard</option>
				</select>
				</td></tr>				
				</tbody>
				</table>				
				</div>
		
				<div class="row">
					<table class="table table-striped table-bordered">
					<tbody>					
					<tr>
				<td>
				<div id="tf">
				<label for="question">Question:</label>
				<textarea rows="4" cols="200" name="questionTF"></textarea>
				<label for="option">Correct Answer:</label>
				<select id="answer1" name="answer1">
				<option value="1" selected="selected">True</option>
				<option value="2">False</option>
				</select>
				</div>
				<div id="mc" class="hide">
				<label for="question">Question:</label>
				<textarea rows="4" cols="200" name="questionMC"></textarea>
				<input type="hidden" name="optCount" value="2"/>
				<label for="option">Option A:</label><input type="text" name="option1"/>
				<label for="option">Option B:</label><input type="text" name="option2"/>
				<label for="btnAddOption"></label>
				<input type="button" id="btnAddOption" value="More Option" class="btn btn-info"/>
				<label for="choice">Correct Answer:</label>
				<select id="answer2" name="answer2">
				<option value="1" selected="selected">A</option>
				<option value="2">B</option>
				</select>
				</div>
				<div id="editorDiv" class="hide">
				</div>
				</td></tr>
					</tbody>
					</table>
					<p><input type="button" id="addQuset" value="Add" class="btn btn-primary"/>	&nbsp;
					<input type="button" id="btnBack" value="Back" class="btn btn-primary"/>		
					</p>				
					</div>
			
				</fieldset>
				</form>
		</div>	
		
<script type="text/javascript">

$(document).ready(function(){
	

	
	$("#qType option[value='1']").attr("selected", "selected");
	
	$("#qType").change(function(){
		var val = $(this).find(':selected').val();
		if(val=="1")
			{
			$("#editorDiv").hide();
			$("#tf").show();
			$("#mc").hide();
			}
		else
			{
			$("#editorDiv").hide();
			$("#mc").show();
			$("#tf").hide();
			}
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
	
	$("#btnBack").click(function(){
		var id=$("#qId").val();
		 loadHomeBody("jspPages/editQuiz.jsp",{"id":id});
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
	
	$("#tf textarea[name='questionTF'], #mc textarea[name='questionMC']").click(function(){
		$(this).css("background-color","white");
	});

	
	$("#addQuset").unbind('click').click(function(){
		var quest1=$("#tf textarea[name='questionTF']").val();
		var quest2=$("#mc textarea[name='questionMC']").val();
		var val=$("#qType").find(':selected').val();
		
	 if(val=="1")
			{			
				if(quest1=="")
					{
					$("#tf textarea[name='questionTF']").css("background-color","#FF9A9A");
					return false;
					}							
			}
		else if(val=="2")
			{

			if(quest2=="")
				{
				$("#mc textarea[name='questionMC']").css("background-color","#FF9A9A");
				return false;
				}
			
			}		
				var frm_data=$("#addQuestionForm").serialize();
				console.log(frm_data);
				makeAjaxCall("addQuestion.process",frm_data,getEditQuiz);
	});

	
});

function getEditQuiz()
{
	alert("Question added successfully.");	
	 var id=$("#qId").val();
	 loadHomeBody("jspPages/editQuiz.jsp",{"id":id});
	}

	</script>			
		