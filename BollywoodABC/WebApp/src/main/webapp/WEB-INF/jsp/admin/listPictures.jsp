<%@page isELIgnored="false" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Admin > Pictures list</title>
</head>
<body>

<table border="1">
	 <c:forEach var="picture" items="${pictureModel}">
 	<tr>
 		<td>${picture.id}</td>
 		<td>${picture.caption}</td>
 		<td><img src="/dimage/thumb/${picture.id}.jpeg" width="50" height="50"></td>
 		<td>Groups</td>
 		<td>[ <a href="/admin/picture/update/${picture.id}.htm">Update</a>] [<a href="/admin/picture/delete/${picture.id}.htm">Delete</a>] </td>
 	</tr>
	 </c:forEach>
</table>
</body>
</html>