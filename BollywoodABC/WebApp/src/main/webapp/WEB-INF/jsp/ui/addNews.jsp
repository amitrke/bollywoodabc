<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"
%>
<h1>Submit News</h1>
<form:form method="post" enctype="multipart/form-data" commandName="storyModel" >
<table>
	<tr>
		<td>Category</td>
		<td>
			<form:select path="category">
				<option value="0">--SELECT--</option>
				<form:options items="${categories}" itemLabel="value" itemValue="key" />
			</form:select>
			<form:errors path="category" />
		</td>
	</tr>
	<tr>
		<td>Title</td>
		<td><form:input path="title" size="70" /> <form:errors path="title" /></td>
	</tr>
	<tr>
		<td>Author</td>
		<td><form:input path="author"/><form:errors path="author" /></td>
	</tr>
	<tr>
		<td>Intro</td>
		<td><form:textarea path="intro" rows="3" cols="70"/><form:errors path="intro" /></td>
	</tr>
	<tr>
		<td>Priority</td>
		<td><form:input path="priority"/><form:errors path="priority" /></td>
	</tr>
	<tr>
		<td>Body</td>
		<td><form:textarea path="body" rows="20" cols="70"/><form:errors path="body" /></td>
	</tr>
	<tr>
		<td>Image</td>
		<td><input type="file" name="imageData" /></td>
	</tr>
	<tr>
		<td>Image Caption</td>
		<td><form:input path="imageCaption"/></td>
	</tr>
	<tr>
		<td>Existing Image Id</td>
		<td><form:input path="imageId"/></td>
	</tr>
	<tr>
		<td>video</td>
		<td><form:input path="video"/></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><input type="submit" value="submit" /></td>
	</tr>
</table>
</form:form>