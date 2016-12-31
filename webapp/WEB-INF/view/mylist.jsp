<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My personal list</title>
<script src="http://kolber.github.com/audiojs/audiojs/audio.js" type="text/javascript"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function () {
	    $('input').on('click', function () {
	        var id = $(this).val().toString();
	        if (id != $('#search').val().toString()) {
				$.ajax({
					url: "/MusicServiceWeb/mylist/delete/" + id,
				  	type: "DELETE"
				});
	        }
	    });
	}); 
</script>
<script type="text/javascript">

</script>
</head>
<body>
	<p align="right">Login as Alik</p>
	<form:form name="searchForm" action="/MusicServiceWeb/audio.search" method="GET" >
		<input type="text" name = "query" id="searchText" width="1000px">
		<input type="submit" id="search" value="Search" width="100px">
	</form:form>
	<table>
	<b>Your personal music list</b>
	<c:forEach items="${allRecords}" var="record">
			<tr height="10px">
				<td align="center" bgcolor="gray">${record.musicName}</td>
				<td align="center" bgcolor="gray">
					<audio id="audio" controls preload="metadata">
					    <source src="${record.link}" type="audio/mpeg">
					</audio>
					<script type="text/javascript">
						var audio = document.getElementsByTagName('audio');
						audio.onended = function() {
						    alert("The audio has ended");
						};
					</script>
					
				</td>
				<td align="center" width="50px" bgcolor="gray">
					<input type="button" id="delete" name="delete" width="50px" value="-">
				</td>
			</tr>
	</c:forEach>
	</table>
</body>
</html>