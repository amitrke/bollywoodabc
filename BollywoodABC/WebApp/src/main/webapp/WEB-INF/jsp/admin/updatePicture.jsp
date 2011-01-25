<%@page isELIgnored="false" %><%@page 
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create/Update Category</title>
</head>
<body>
<form:form method="post" enctype="multipart/form-data" commandName="pictureModel" >
<table>
	<tr>
		<td>Caption</td>
		<td><form:input path="caption"/><form:errors path="caption" /></td>
	</tr>
	<c:if test="${!empty pictureModel.existingPicId}">
	<tr>
		<td>Existing pic</td>
		<td><img src="/dimage/${pictureModel.existingPicId}.htm"></td>
	</tr>
	</c:if>
	<tr>
		<td>Picture</td>
		<td><input type="file" name="file"> </td>
	</tr>
	<tr>
		<td>
			&nbsp;	
		</td>
		<td>
			<input type="submit" value="Submit" />
		</td>
	</tr>
</table>
</form:form>
</body>
</html>