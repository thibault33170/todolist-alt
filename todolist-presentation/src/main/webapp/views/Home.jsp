<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todolist</title>
</head>
<body>
 	<c:forEach items="${tasks}" var="task">
 	<span>${task.name} - ${task.status.name}</span>
    	<form action="/todolist-presentation/finish" method="POST">
			<input type="hidden" name="taskId" value="${task.id}"/>
			<input type="submit" value="Terminer la tâche" />
		</form> 
	</c:forEach>
</body>
</html>