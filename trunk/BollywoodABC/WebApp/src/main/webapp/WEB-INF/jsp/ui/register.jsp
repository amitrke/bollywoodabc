<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
	<TR>
		<TD VALIGN="TOP">
			<p>
				<strong>Register |</a>
				</strong><br>
			</p> 
			<form:form method="post" enctype="multipart/form-data"
				commandName="userModel">
				Name: <form:input path="name"/><br>
				<input type="submit" value="Submit" />
			</form:form>
		</TD>
	</TR>
</TABLE>