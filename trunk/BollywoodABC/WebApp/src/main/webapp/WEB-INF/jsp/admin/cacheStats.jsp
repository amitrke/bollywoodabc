<%@page isELIgnored="false" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Cache statistics</title>
</head>
<body>
<table border="1">
	<tr>
		<td>Entity Calls</td>
		<td><c:out value="${data.entity.calls}"/></td>
	</tr>
	<tr>
		<td>Entity Hits</td>
		<td><c:out value="${data.entity.hits}"/></td>
	</tr>
	<tr>
		<td>Query Calls</td>
		<td><c:out value="${data.query.calls}"/></td>
	</tr>
	<tr>
		<td>Query Hits</td>
		<td><c:out value="${data.query.hits}"/></td>
	</tr>
</table>
</body>
</html>