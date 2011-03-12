<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form:form method="post" enctype="multipart/form-data" commandName="crossRefModel" >

<table>
	<tr>
		<td>Entity Type</td>
		<td>
			<form:select path="entityType">
				<form:option value="1">Story</form:option>
				<form:option value="2">Picture</form:option>
				<form:option value="3">Photo Gallery</form:option>
			</form:select>
			<form:errors path="entityType" />
		</td>
	</tr>
	<tr>
		<td>Tag Id</td>
		<td>
		<form:select path="tagId">
			<form:options items="${taglist}" itemLabel="tagLabel" itemValue="id" />
		</form:select>
		<form:errors path="tagId" /></td>
	</tr>
	<tr>
		<td>Entity Id</td>
		<td><form:input path="entityId" size="8"/><form:errors path="entityId" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><input type="submit" value="submit" /></td>
	</tr>
</table>
</form:form>