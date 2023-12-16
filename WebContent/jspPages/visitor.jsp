<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html style="height: 100%;" lang="en-US">
<head>
<meta http-equiv="content-type" content="text/html; charset=windows-1252">

<title>${title}</title>
 
<c:import url="../htmlPages/css_js.html" />
</head>
<body style="background-color: #3D5998;"> 
<br/>
<div class="container" style="background-color: #ffffff;">
     
     
	<div class="row">
	<br/>
		<div class="span2 offset1">
			  <a id="top" href="#"><img src="img/touchstone.png" alt="TouchStone" style="border:0;margin-top:5px;" height="67" width="201"></a>
		</div>
		
	</div>
<hr/>
<div id="page" class="row">
<c:import url="${body}" />

</div>
<hr/>
<!-- footer placeholder -->
<div id="footer" class="row">
			
<c:import url="footer.jsp" />		
			
</div>
<br/>
</div><!-- container div ends -->
<br/>
</body>   
</html>			
		
		
