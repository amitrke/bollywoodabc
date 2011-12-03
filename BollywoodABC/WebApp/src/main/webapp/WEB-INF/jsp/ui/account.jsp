<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
	<TR>
		<TD VALIGN="TOP">
			<p>
				<strong>My Account |</a></strong><br>
			</p> 
			<c:choose>
				<c:when test="${!empty data.error }">
					<c:out value="${data.error}" />
				</c:when>
				<c:otherwise>
					Welcome <c:out value="${data.userEntity.name}" /> !
				</c:otherwise>
			</c:choose>
			<!-- 
			<p>Subscriptions</p>
			<form method="post">
				<table border="0" width="100%" cellspacing="1" cellpadding="1">
					<tr>
						<td width="10%"></td>
					</tr>
				</table>
			</form>
			 -->
		</TD>
	</TR>
</TABLE>