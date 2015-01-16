<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>

	</head>
	<body>
	
		<form method="post"  action="doAction">
		
	<input type="hidden" name="action" value="login"/>
		
		<fieldset>
		<legend>Login</legend>
		<label>Client</label>
		<select name="client">
		
			<c:forEach items="${clients}" var="client">
		<option value="${client.title}">${client.desc}</option>
		<br />
			</c:forEach>
		
		
		</select><br>
		
		<label>nickname</label>
		<input type="text" name="nickname" value=""><br>

		<label>password</label>
		<input type="password" name="password" value=""><br>
		<input type="submit" value="Enter" />

		</fieldset>
		
	
	</form>	
	</body>
</html>