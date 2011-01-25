<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
              <TABLE WIDTH="100%"  BORDER="0" CELLSPACING="0" CELLPADDING="2">
                <TR>
                  <TD HEIGHT="32" VALIGN="bottom" COLSPAN="2"><h1>Photo Gallery</h1></TD>
                </TR>
                <TR>
                  <TD HEIGHT="2" COLSPAN="2" BACKGROUND="/images/div2.gif"><IMG SRC="/images/spacer.gif" WIDTH="1" HEIGHT="1"></TD>
                </TR>		      
		<c:forEach var="gallery" items="${page.data.galleries}">
  		<tr>
			<td height="31" colspan="2" class="yellowtxt"><strong><A HREF='/photogallery/${gallery.id}/${gallery.titleUrl}.htm' CLASS='yellowtxt'>${gallery.name}</A></strong></td>
		</tr>
		<tr>
			<td width="1"><A HREF='/photogallery/${gallery.id}/${gallery.titleUrl}.htm'><img src="/dimage/thumb/${gallery.picture.id}.htm" alt="gallery.picture.caption" /></A></td>
			<td valign="top" class="whitetxt"><A HREF='/photogallery/${gallery.id}/${gallery.titleUrl}.htm' class="whitetxt">${gallery.description}</A></td>
		</tr>
		</c:forEach>
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