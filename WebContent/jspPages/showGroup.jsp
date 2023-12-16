<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc" %>

<mvc:invoke action="showGroup" />
			<c:choose>
			<c:when test="${gList !=null}">
			<div class="span5 offset1" >
					<table class="table table-striped table-bordered">
					<thead><tr style="background-color: #0088cc;color: white;">
					<th>Name</th><th>Description</th><th>Code</th></tr></thead>
					<tbody>					
					<c:forEach items="${gList}" var="group">
					<tr>
					<td>${group.name}</td>
					<td>${group.description}</td>
					<td>${group.code}</td>
					</tr>
					</c:forEach>
					</tbody>
					</table>				
			 </div> 
			 </c:when>
			 <c:otherwise>
			 No Group found.
			 </c:otherwise>
			 </c:choose>
			 
		
		
		
	