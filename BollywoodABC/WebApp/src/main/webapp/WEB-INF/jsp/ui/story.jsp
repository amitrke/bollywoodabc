<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">

                <TR>
                  <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;">&nbsp;</TD>
                </TR>
                <TR>
                  <TD HEIGHT="22" VALIGN="middle" CLASS="h1" STYLE="padding-left:12px; padding-right:12px;"><c:out value="${page.data.title}"/></TD>
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
                  <div ID="storyMeta"><p>
                  <c:if test="${!empty page.data.tagLinks}">
                  Keywords:
                  <c:forEach var="tag" items="${page.data.tagLinks}">
                  	<A HREF='/t/${tag.key}/${tag.urlEscapeValue}.htm'>${tag.value}</A> &nbsp;
                  </c:forEach>
                  <br>
                  </c:if>
                  <c:out value="${page.data.createDate}" /> 
                  </p></div>
                  </TD>
                </TR>
                <TR>

                  <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;"><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
                      <TR>
                        <TD VALIGN="TOP"> <IMG SRC="<c:out value="/dimage/${page.data.picture.id}.htm"/>" HSPACE="5" VSPACE="5" ALIGN="right"><!--<SPAN CLASS="quote"> <c:out value="${page.data.author}"/> </SPAN><BR><BR>-->
                          <SPAN CLASS="story"><c:out value="${page.data.bodyNl2br}" escapeXml="false" /></SPAN></TD>
                      </TR>
                  </TABLE></TD>
                </TR>
                <TR>
                  <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;">&nbsp;</TD>
                </TR>
				<!-- 
                <TR>
                  <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;"><A HREF="http://bollywoodabc.com/forum/posting.php?mode=newtopic&f=4" CLASS="style4">Comment on this story</A> <SPAN CLASS="style3">/</SPAN> <A HREF="http://bollywoodabc.com/forum/viewforum.php?f=4" CLASS="style4">Read comments</A> </TD>
                </TR>
                 -->
                <TR>
                  <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;"></TD>
                </TR>

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
                <c:if test="${!empty page.data.tags}">
                
                <TR>
                  <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;">
                  
                  <c:if test="${!empty page.data.relatedPhotogalleries}">
                  	<B>Related Photo galleries:</B><br>
                  	<c:forEach var="gallery" items="${page.data.relatedPhotogalleries}">
                  		<A HREF='<c:out value="${gallery.galleryUrl}" />'><c:out value="${gallery.name}" /></A>
                  	</c:forEach>
                  </c:if>
                  <BR>
                  <c:if test="${!empty page.data.relatedStories}">
                  	<B>Related Stories:</B><br>
                  	<c:forEach var="story" items="${page.data.relatedStories}">
                  		<A HREF='<c:out value="${story.storyUrl}" />'><c:out value="${story.title}" /></A><BR>
                  	</c:forEach>
                  </c:if>
                  </TD>
                </TR>
                <br>
                </c:if>
                
                <TR>
                  <TD VALIGN="top" CLASS="bot" STYLE="padding-left:12px; padding-right:12px;"> <SPAN CLASS="quote">If you have you a story idea, an article, a hi-res picture, be a citizen Journalist and send it to us at <A HREF="mailto:cj@bollywoodabc.com">cj@bollywoodabc.com</A></SPAN> </TD>
                </TR>
              </TABLE>
              