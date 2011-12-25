<%@page isELIgnored="false" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Admin</title>
</head>
<body>
<p align="center">
	Entity Cache Hits/Calls:<c:out value="${data.entity.hits}"/>/<c:out value="${data.entity.calls}"/>
	Query Cache Hits/Calls:<c:out value="${data.query.hits}"/>/<c:out value="${data.query.calls}"/>
</p>
</body>
</html>