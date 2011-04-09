<%@page isELIgnored="false" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1">
	 <c:forEach var="tag" items="${data}">
 	<tr>
 		<td>${tag.id}</td>
 		<td>${tag.tag}</td>
 		<td>${tag.description}</td>
 		<td>${tag.type}</td>
 		<td>[<A HREF="/admin/tag/update/${tag.id}.htm">Edit</A>][<A HREF="/admin/tag/delete/${tag.id}.htm">Delete</A>]</td>
 	</tr>
	 </c:forEach>
</table>