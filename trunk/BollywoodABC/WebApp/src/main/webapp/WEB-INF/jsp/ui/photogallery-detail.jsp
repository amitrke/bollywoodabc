<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><TABLE WIDTH="100%"  BORDER="0" CELLSPACING="0" CELLPADDING="2">
        <TR>
            <TD HEIGHT="32" VALIGN="bottom" COLSPAN="2"><h1>${page.data.category.name}</h1></TD>
        </TR>
        <TR>
            <TD HEIGHT="2" COLSPAN="2" BACKGROUND="/images/div2.gif"><IMG SRC="/images/spacer.gif" WIDTH="1" HEIGHT="1"></TD>
        </TR>		         		      
		<c:forEach var="galleryRow" items="${page.data.gallery}">
		<tr>
			<c:forEach var="galleryElement" items="${galleryRow}">
			<td width="1"><A href="/photogallery/wallpaper/${galleryElement.urlCaption}/${galleryElement.id}.htm" style="border-style: none"><img src="/dimage/thumb/${galleryElement.id}.htm" alt="${galleryElement.caption}" /></A></td>
			</c:forEach>
		</tr>
		</c:forEach>
	</TABLE>