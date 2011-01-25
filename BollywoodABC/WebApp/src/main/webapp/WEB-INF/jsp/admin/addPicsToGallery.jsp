<%@page isELIgnored="false" %><%@page 
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add pics to photogallery</title>
</head>
<body>
<form:form method="post" enctype="multipart/form-data" commandName="galleryModel" >
<table>
	<tr>
		<td>Category</td>
		<td>
			<form:select path="categoryId">
				<option value="0">--SELECT--</option>
				<form:options items="${categories}" itemLabel="value" itemValue="key" />
			</form:select>
			<form:errors path="categoryId" />
		</td>
	</tr>
	<tr>
		<td>Picture</td>
		<td><form:input path="picIds" /></td>
	</tr>
	<tr>
		<td>Picture</td>
		<td><form:input path="picIds" /></td>
	</tr>
	<tr>
		<td>Picture</td>
		<td><form:input path="picIds" /></td>
	</tr>
	<tr>
		<td>Picture</td>
		<td><form:input path="picIds" /></td>
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