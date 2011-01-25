<%@page isELIgnored="false" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>List Photogalleries</title>
</head>
<body>
<table border="1">
	 <c:forEach var="category" items="${data}">
 	<tr>
 		<td>${category.key}</td>
 		<td><A HREF='/admin/picture/album/details/${category.key}.htm'>${category.value}</A></td>
 	</tr>
	 </c:forEach>
</table>
</body>
</html>