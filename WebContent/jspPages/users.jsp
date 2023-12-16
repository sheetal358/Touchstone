<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<c:if test="${! empty users}">
      <table>
      	<tr>
      	<th>   
      	User Name</th><th>MailId</th><th>Action</th><th>Action</th></tr>	
    
      <c:forEach items="${users}" var="user">
      <tr> 
      <td>${user.name}</td>
      <td>${user.mailId}</td>
     <td>${user.password}</td>
     <td><a href="editUser.process?id=${user.id}">Edit</a></td>
     <td><a href="deleteUser.process?id=${user.id}">Delete</a></td>
      </tr>
      </c:forEach>          
      </table>    
           
</c:if>	
	

	