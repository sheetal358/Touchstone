<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${! empty question}">
	<c:choose>
		<c:when test="${question.type==1}">
				<div id="tf">
				<label for="question">Question:</label>
				<textarea rows="4" cols="200" name="questionTF" >${question.description}</textarea>
				<label for="option">Correct Answer:</label>
				<select id="answer1" name="answer1">
				<option value="1" ${question.ans=="1"?'selected':''}>True</option>
				<option value="2" ${question.ans=="2"?'selected':''}>False</option>
				</select>
				</div>
		</c:when>
		<c:otherwise>	
				<div id="mc">
				<label for="question">Question:</label>
				<textarea rows="4" cols="200" name="questionMC">${question.description}</textarea>
				<input type="hidden" name="optCount" value="2"/>
				<%String[] lChar={"A","B","C","D","E","F","G","H"};
				int loop=0;
				%>
				<c:forEach items="${question.options}" var="opt">
				<c:if test="${opt != null}">
				<%loop+=1; %>
				<c:set var="loopCount" value="<%=loop %>"/>
				<c:choose>
				<c:when test="${ loopCount<=2}">
				<label for="option">Option <%=lChar[loop-1] %>:</label>				
				<input type="text" name="option<%=loop%>" value="${opt }"/>
				</c:when>
				<c:otherwise>				
				<div>
				<label for="option">Option <%=lChar[loop-1] %>:</label>				
				<input type="text" name="option<%=loop%>" value="${opt }"/>
				</div>
				</c:otherwise>
				</c:choose>
				</c:if>
				</c:forEach>
				<label for="btnAddOption"></label>
				<input type="button" id="btnAddOption" value="More Option" class="btn btn-info"/>
				<label for="choice">Correct Answer:</label>
				<select id="answer2" name="answer2">
				<% loop=0;%>
				<c:forEach items="${question.options}" var="opts">
				<c:if test="${opts != null}">
				<%loop+=1; %>
				<c:set var="loopCount2" value="<%=loop %>"/>
				<option value="${loopCount2}" ${question.ans==loopCount2?'selected':''}><%=lChar[loop-1] %></option>
				</c:if>
				</c:forEach>
				</select>
				</div>
		</c:otherwise>
	</c:choose>
</c:if>

					