<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc"%>

<mvc:invoke action="fetchAllNotification"/>

		<div class="span4 offset1">
				<c:choose>
			<c:when test="${! empty notes}">
			<c:forEach items="${notes}" var="note">
				<div class="span6">	          
            		<div class="well well-small" style="border-radius: 10px;">               
                		<div class="row">              		
               				<div class="span4">               		 
               		 			<p>Posted By: <b class="text-success">${note.userName}</b></p>                    
                			</div>
                 			<div class="text-right">               		 
               		 			<p>Created On: <b class="text-warning">${note.createdOn}</b></p>                     
                			</div>               
                		</div>
                		<hr> 
                		<div class="row">
                			<div class="well well-small span5" style="background-color: white;border-radius: 5px;"> 
                    			${note.notification} 
                     		</div>
                		</div>
                		<hr>                  		
                   		<div class="row">
                 			<div class="span4">
           
                  			</div>
                  			<div class="text-right"> 
                  				<c:if test="${user.name==note.userName}">                 
                    				<a href="#" id="deleteNote" rel="${note.id}" onclick="deleteNote(this)" class="text-error"><i class="icon-remove"></i>Delete</a>
                    			</c:if>              
               				</div>
               			</div>
	         		</div>           
	     		</div>
			</c:forEach>
			
			 </c:when>
			 <c:otherwise>
			 No Notification found.
			 </c:otherwise>
			 </c:choose>
		</div>
		
<script type="text/javascript">

	function deleteNote(alink){
		var id=parseInt($(alink).attr('rel'));
		 if(confirm("Are you sure to remove this notification?"))
		{
			 makeAjaxCall("removeNotification.process",{'nId':id},viewAllMessage);
		} 
	}
	function viewAllMessage(data)
	{
		alert(data);
		loadHomeBody("jspPages/viewAllNotification.jsp");
	}
</script>