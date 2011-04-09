<%@page isELIgnored="false" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1">
	<tr>
 		<td>TAG</td>
 		<td>Entity</td>
 		<td>[Edit][Delete]</td>
 	</tr>
	 <c:forEach var="crossRef" items="${data}">
 	<tr>
 		<td>${crossRef.tagTypeAsString}-${crossRef.tag}(${crossRef.entityId})</td>
 		<td>${crossRef.entityTypeAsString}-${crossRef.tagId}</td>
 		<td>[Edit][Delete]</td>
 	</tr>
	 </c:forEach>
</table>