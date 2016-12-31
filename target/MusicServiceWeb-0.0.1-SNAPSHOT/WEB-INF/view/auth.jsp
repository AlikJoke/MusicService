<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authentication</title>
</head>
<body>
	<div id="auth">
		<form:form id="newUserForm" action="${pageContext.request.contextPath}/login/auth" commandName="user" method="POST">  
			<table>  
				<tbody>   
					<tr>  
						<td>Your username: </td>  
						<td><form:input path="userName"></form:input></td>  
					</tr>  
					<tr>  
						<td>Your password:</td>  
						<td><form:input path="password"></form:input></td>  
					</tr>  
					<tr>  
						<td>Your email:</td>  
						<td><form:input path="email"></form:input></td>  
					</tr>  
					
					<tr>  
						<td><input type="submit" value="Sign up"></td>  
						<td></td>  
					</tr>  
				</tbody>  
			</table>  
		</form:form>  
	</div>
</body>
</html>