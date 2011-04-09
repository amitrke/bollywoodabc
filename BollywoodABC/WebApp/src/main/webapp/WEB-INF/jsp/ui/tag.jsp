<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">

    <TR>
      <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;">&nbsp;</TD>
    </TR>
    <TR>
      <TD HEIGHT="22" VALIGN="middle" CLASS="h1" STYLE="padding-left:12px; padding-right:12px;"><c:out value="${page.data.tag}"/></TD>
    </TR>
    <TR>
      <TD STYLE="padding-left:12px; padding-right:12px;"><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">

          <TR>
            <TD HEIGHT="1" BACKGROUND="/images/div3.gif"></TD>
          </TR>
      </TABLE></TD>
    </TR>
    <TR>
      <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;">
      <p><c:out value="${page.data.description}" /></p>
              </TD>
    </TR>
	<TR>
              <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;">
      <TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
      	<tr valign="top">
      		<td valign="top">
        	<c:if test="${!empty page.data.relatedPhotogalleries}">
        	<B>Photo Gallery:</B>
        	<c:forEach var="gallery" items="${page.data.relatedPhotogalleries}">
        		<A HREF='<c:out value="${gallery.galleryUrl}" />'><c:out value="${gallery.name}" /></A>&nbsp;
        	</c:forEach>
        	<BR>
        </c:if>
        <c:if test="${!empty page.data.relatedStories}">
        	<br><B>Recent Stories:</B><br>
        	<c:forEach var="story" items="${page.data.relatedStories}">
        		<p><A HREF='<c:out value="${story.storyUrl}" />'><c:out value="${story.title}" /></A><br>
        		<c:out value="${story.intro}" /></p>
        	</c:forEach>
        </c:if>
      		</td>
      		<td>
      			<c:if test="${!empty page.data.facePic}">
      				<IMG SRC="<c:out value="/dimage/${page.data.facePic}.htm"/>" HSPACE="5" VSPACE="5" ALIGN="right">
      			</c:if>
      		</td>
      	</tr>
      </TABLE>
      
      </TD>
    </TR>
    <c:if test="${!empty page.data.relatedPictures}">
    <tr>
    	<td VALIGN="top" STYLE="padding-left:12px; padding-right:12px;">
    		<B>Recent pics:</B><br>
    		<c:forEach var="picture" items="${page.data.relatedPictures}">
    			<A HREF="/photogallery/wallpaper/${picture.urlCaption}/${picture.id}.htm" style="border-style: none"/>
    			<IMG SRC="<c:out value="/dimage/thumb/${picture.id}.htm"/>" HSPACE="5" VSPACE="5" ALIGN="left">
    			</A>
    		</c:forEach>
    	</td>
    </tr>
    </c:if>
    <TR>
      <TD STYLE="padding-left:12px; padding-right:12px;"><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
          <TR>
            <TD HEIGHT="1" BACKGROUND="/images/div3.gif"></TD>
          </TR>
      </TABLE></TD>
    </TR>
    <TR>
      <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;">&nbsp;</TD>
    </TR>                
    
    
    <TR>
      <TD VALIGN="top" CLASS="bot" STYLE="padding-left:12px; padding-right:12px;"> <SPAN CLASS="quote">If you have you a story idea, an article, a hi-res picture, be a citizen Journalist and send it to us at <A HREF="mailto:cj@bollywoodabc.com">cj@bollywoodabc.com</A></SPAN> </TD>
    </TR>
  </TABLE>
              