<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
<title>${page.title}</title>
</head>
<body>
<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td><tiles:insertAttribute name="header"/></td>
	</tr>
</table>
<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td><tiles:insertAttribute name="left"/></td>
		<td><tiles:insertAttribute name="body"/></td>
	</tr>
</table>
</body>
</html>