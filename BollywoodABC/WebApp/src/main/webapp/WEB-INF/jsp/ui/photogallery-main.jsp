<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
              <TABLE WIDTH="100%"  BORDER="0" CELLSPACING="0" CELLPADDING="2">
                <TR>
                  <TD HEIGHT="32" width="20%"><h1>Photo Gallery</td>
                  <td align="right" width="80%"><g:plusone></g:plusone></h1></TD>
                </TR>
                <TR>
                  <TD HEIGHT="2" COLSPAN="2" BACKGROUND="/images/div2.gif"><IMG SRC="/images/spacer.gif" WIDTH="1" HEIGHT="1"></TD>
                </TR>
                <tr>
                <td colspan="2">
                <table WIDTH="100%"  BORDER="0" CELLSPACING="0" CELLPADDING="2">	
        <c:forEach var="galleryRow" items="${page.data.galleries}">
		<tr>
			<c:forEach var="gallery" items="${galleryRow}">
			<td width="1">
			<A HREF='/photogallery/${gallery.id}/${gallery.titleUrl}.htm'><img src="/dimage/thumb/${gallery.picture.id}.jpeg" alt="gallery.picture.caption" /></A>
			<br>
			<strong><A HREF='/photogallery/${gallery.id}/${gallery.titleUrl}.htm' CLASS='yellowtxt'>${gallery.name}</A></strong>
			</td>
			</c:forEach>
		</tr>
		</c:forEach>
		</table>
		</td>
		</tr>
  <TR >
    <TD HEIGHT="1" COLSPAN="2" BACKGROUND="/images/dotHorizontal.gif"></TD>
  </TR>
  <TR>
    <TD COLSPAN="2">&nbsp;</TD>
  </TR>

  <TR>
    <TD COLSPAN="2"></TD>
  </TR>
  <TR>
    <TD COLSPAN="2"></TD>
  </TR>
  <TR>
    <TD COLSPAN="2"></TD>
  </TR>

  <TR>
    <TD COLSPAN="2"></TD>
  </TR>
  <TR>
    <TD COLSPAN="2"></TD>
  </TR>
  <TR>
    <TD COLSPAN="2"></TD>
  </TR>

  <TR>
    <TD COLSPAN="2"></TD>
  </TR>
              </TABLE>