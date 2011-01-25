<%@page isELIgnored="false" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title><c:out value="${page.title}"/></title>
</head>
<body>
<h2>Hi <c:out value="${page.username}"/>, Welcome to <c:out value="${page.appname}"/> admin console.</h2>
<p align="center">
  The time on the server is <c:out value="${page.dateTime}"/>
</p>
<table border="1">
	 <c:forEach var="story" items="${data}">
 	<tr>
 		<td>${story.id}</td>
 		<td>${story.title}</td>
 		<td>${story.intro}</td>
 		<td><img src="/dimage/thumb/${story.pictureId}.htm" width="50" height="50"></td>
 	</tr>
	 </c:forEach>
</table>
</body>
</html>