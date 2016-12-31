<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>
	<div id="auth">
		<form:form id="newUserForm" action="${pageContext.request.contextPath}/login/auth" commandName="user" method="GET">  
			<h3>Sorry, but your username/password is incorrect. Check it on the void and invalid characters.</h3>
			<input type="submit" value="Back">
		</form:form>  
	</div>
</body>
</html>