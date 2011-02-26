<%@page isELIgnored="false" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1">
	 <c:forEach var="crossRef" items="${data}">
 	<tr>
 		<td>${crossRef.id}</td>
 		<td>${crossRef.tagId}</td>
 		<td>${crossRef.entityId}</td>
 		<td>${crossRef.entityType}</td>
 		<td>[Edit][Delete]</td>
 	</tr>
	 </c:forEach>
</table>