<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>Login</title>
    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">
 
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">
 
    
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <style>
	    .button10 {
	  display: inline-block;
	  color: black;
	  font-size: 120%;
	  font-weight: 700;
	  text-decoration: none;
	  user-select: none;
	  padding: .25em .5em;
	  outline: none;
	  border: 1px solid black;
	  border-radius: 7px;
	  background: #a0b0e0;
	  box-shadow: inset 0 -2px 1px rgba(0,0,0,0), inset 0 1px 2px rgba(0,0,0,0), inset 0 0 0 60px rgba(255,255,0,0);
	  transition: box-shadow .2s, border-color .2s;
	} 
    </style>
</head>
<body bgcolor="#c0c0d9">
	<div style="position:fixed;margin-left:36%;margin-top:15%;width:300px;height:200px;
		outline: 2px solid #000;border: 3px solid #fff;border-radius: 10px;background:#c0a0d9">
	    <form action="${pageContext.request.contextPath}/j_spring_security_check"  method="POST">
	        <table style="width:300px;height:100px;margin-left:15px;margin-top:20px;">
	        	<tr>
	        		<td><i style="font-size:20px;color:black;"><u>Login</u></i></td>
		        	<td><input type="text" style="height:20px;" class="form-control" name="j_username"><td>
		        </tr>
		        <tr>
		        	<td><i style="font-size:20px;color:black;"><u>Пароль</u></i></td>
		        	<td><input type="password" style="height:20px;" class="form-control" name="j_password"></td>
		        </tr>
		        <tr>
		        	<td><i style="font-size:15px;color:black;"><u>Запомнить меня</u></i></td>
		        	<td><input type="checkbox" style="width:17px;height:17px;" name="_spring_security_remember_me" /></td>
		        </tr>
		        <tr>
		        	<td><i style="font-size:15px;color:black;"><u><a href="${pageContext.request.contextPath}/login/auth">Зарегистрироваться</a></u></i></td>
		        </tr>
	        </table>
	        <input style="margin-top:38px;height:40px;width:300px;" class="button10" type="submit" value="Войти"/>
	    </form>
    </div>
 
</body>
</html>