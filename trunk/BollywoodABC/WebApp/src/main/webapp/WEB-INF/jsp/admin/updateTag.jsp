<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form:form method="post" enctype="multipart/form-data" commandName="tagModel" >
<table>
	<tr>
		<td>Type</td>
		<td>
			<form:select path="type">
				<form:option value="1">Actor</form:option>
				<form:option value="2">Event</form:option>
				<form:option value="3">Alias</form:option>
			</form:select>
			<form:errors path="type" />
		</td>
	</tr>
	<tr>
		<td>Tag</td>
		<td><form:input path="tag" size="70" /> <form:errors path="tag" /></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><form:input path="description"/><form:errors path="description" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><input type="submit" value="submit" /></td>
	</tr>
</table>
</form:form>