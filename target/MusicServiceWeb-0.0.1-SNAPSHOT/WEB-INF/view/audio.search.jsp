<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
	    $('input').on('click', function () {
	        var id = $(this).val().toString();
	        if (id != $('#back').val().toString()) {
	        	$.post("/MusicServiceWeb/audio.search/add/" + id);
	        }
	    });
	});
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/mylist" method="GET">
		<input type="submit" id="back" value="My list">
	</form>
	<table>
	<c:forEach items="${musicList}" var="record">
			<tr>
				<td align="center" width="250px" bgcolor="gray">${record.musicName}</td>
				<td align="center" bgcolor="gray">
					<audio controls>
					    <source src="${record.link}" type="audio/mpeg">
					</audio>
				</td>	
				<td align="center" width="50px" bgcolor="gray">
					<input type="button" name="add" width="50px" value="${record.ownerId}_${record.id}">
				</td>
			</tr>
	</c:forEach>
	</table>
</body>
</html>