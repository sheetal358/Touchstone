	<style>
	#qNumber td
{
	border: 0px;
}

	</style>
			<div class="span6 offset1">
				<form  id="quizEditorForm">
				<span id="num" class="hide">0</span>
				<fieldset>
				<div class="row">
				<table class="table table-bordered table-condensed"><tbody>
				<tr><td><label for="qName">Quiz Name:</label>
				<input type="hidden" name="qId" value="${quiz.id}"/>
				<input type="text" name="name" readonly="readonly" value="${quiz.name}"/></td>
				<td><label id="qTime">Duration:</label>
				<input type="text" id="Time" name="duration" readonly="readonly" value="${quiz.duration}"/><br>
				</td></tr>
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
				<table class="table table-bordered table-condensed"><tbody>
				<tr><td width="20%">
				<table id="qNumber">
				</table>		
				</td>
				<td width="80%">
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
				</tbody></table>
				</div>					
				</fieldset>
				</form>
			 
			 <div>			
				<p><input type="button" id="addQuset" value="Add Question" class="btn btn-primary"/>
				</p>
				
		</div>
		</div>	
		
<script type="text/javascript">
var valQuest=0;
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
		var level=$("#dLevel").find(':selected').val();		
		
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
				var frm_data=$("#quizEditorForm").serialize();
				console.log(frm_data);
				 $.post("addQuestion.process",frm_data,function(data){
									
						addQuestion(data);
						$(".edit").unbind('click').click(function(){
							editQuest(this);
						});
				}); 
				 
				function addQuestion(data)
				{
				 
				var htmlSpan='';
				var count=$("#num").html();
				htmlSpan=$("#qNumber").html();
				count++;
				htmlSpan+='<tr><td><button disabled="disabled" type="button" id="view" class="btn-info">'+count+'</button></td></td><td><button type="button" id="edit" class="btn-info edit" rel="'+data+'"><i class="icon-eye-open"></i></button></td><td><button type="button" id="remove" class="btn-info remove" rel="'+data+'"><i class="icon-remove"></i></button></td></tr>';
				$("#num").html(count);			
				$("#qNumber").html(htmlSpan);
				$("#quizEditorForm").trigger('reset');
				$("#qType").val(val);
				$("#dLevel").val(level);
				$(".remove").unbind('click').click(function(){
					removeQuest(this);
				});
				}
				
				function removeQuest(obj)
				{
					var val=parseInt($(obj).attr('rel'));
					if(confirm('Are you sure to delete this question?'))
					{
					$.post("removeQuestion.process",{'qId':val},function(data){
						alert(data);
					});
					$(obj).parent().parent().remove(); 
					}
				}
	});
	
	function editQuest(obj)
	{
		valQuest=parseInt($(obj).attr('rel'));
		makeAjaxCall("editQuestion.process",{'qId':valQuest},getQuestionEditor);
	}
		
	
});	

function getQuestionEditor(){
	makeAjaxCall("jspPages/questionEditor.jsp",{'qId':valQuest},makeQuestionEditor);
}
function makeQuestionEditor(data)
{
	$("#mc").hide();
	$("#tf").hide();
	$("#editorDiv").html(data);	
	$("#editorDiv").show();
	$("#removeOption").remove();
	if(parseInt($("#editorDiv #mc div").length)>0)
	{
		$('<i class="icon-remove" id="removeOption"></i>').insertAfter("#editorDiv #mc div:last input");
		$("#removeOption").unbind('click').click(function(){
			removeOption($(this));		
	});	
	}	
}
	</script>			
		