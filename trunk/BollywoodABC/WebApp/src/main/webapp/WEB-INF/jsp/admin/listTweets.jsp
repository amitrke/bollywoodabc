<%@page isELIgnored="false" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1">
	 <c:forEach var="tweetList" items="${data}">
 	<tr>
 		<td>${tweetList.id}</td>
 		<td>${tweetList.userId}</td>
 		<td>${tweetList.text}</td>
 	</tr>
	 </c:forEach>
</table>