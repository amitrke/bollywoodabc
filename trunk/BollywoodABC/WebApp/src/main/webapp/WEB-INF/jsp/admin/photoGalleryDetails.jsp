<%@page isELIgnored="false" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Admin > Photogallery Details</title>
</head>
<body>

<table border="1">
	 <c:forEach var="picture" items="${galleryModel}">
 	<tr>
 		<td>${picture}</td>
 		<td><img src="/dimage/thumb/${picture.picId}.htm" width="50" height="50"></td>
 		<td>[ <a href="/admin/picture/update/${picture.picId}.htm">Update</a>] [<a href="#">Remove From Gallery</a>] </td>
 	</tr>
	 </c:forEach>
</table>
</body>
</html>