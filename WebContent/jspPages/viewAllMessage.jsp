<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc"%>

<mvc:invoke action="fetchAllMessage"/>

		<div class="span4 offset1">
				<c:choose>
			<c:when test="${! empty msgs}">
			<c:forEach items="${msgs}" var="msg">
				<div class="span6">	          
            		<div class="well well-small" style="border-radius: 10px;">               
                		<div class="row">              		
               				<div class="span4">               		 
               		 			<p>Group Name: <b class="text-info">${msg.groupName}</b></p>                    
                			</div>
                 			<div class="text-right">               		 
               		 			<p>Created On: <b class="text-warning">${msg.createdOn}</b></p>                     
                			</div>               
                		</div>
                		<hr> 
                		<div class="row">
                			<div class="well well-small span5" style="background-color: white;border-radius: 5px;"> 
                    			${msg.message} 
                     		</div>
                		</div>
                		<hr>                  		
                   		<div class="row">
                 			<div class="span4">
           						<p>Posted By: <b class="text-success">${msg.userName}</b></p>
                  			</div>
                  			<div class="text-right"> 
                  				<c:if test="${user.name==msg.userName}">                 
                    				<a href="#" id="deleteNote" rel="${msg.id}" onclick="deleteMsg(this)" class="text-error"><i class="icon-remove"></i>Delete</a>
                    			</c:if>              
               				</div>
               			</div>
	         		</div>           
	     		</div>
			</c:forEach>
			
			 </c:when>
			 <c:otherwise>
			 No Message found.
			 </c:otherwise>
			 </c:choose>
		</div>
		
<script type="text/javascript">

	function deleteMsg(alink){
		var id=parseInt($(alink).attr('rel'));
		 if(confirm("Are you sure to remove this message?"))
			{
			 makeAjaxCall("removeMessage.process",{'mId':id},viewAllMessage);
			} 
		}
function viewAllMessage(data)
{
	alert(data);
	loadHomeBody("jspPages/viewAllMessage.jsp");
}
</script>