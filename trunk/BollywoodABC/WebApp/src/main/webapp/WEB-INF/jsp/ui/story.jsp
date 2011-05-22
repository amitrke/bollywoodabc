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
                  <c:if test="${!empty page.data.tags.tags}">
                  Keywords:
                  <c:forEach var="tag" items="${page.data.tags.tags}">
                  	<A HREF='/t/${tag.tagId}/${tag.tagUrlEscape}.htm' title='${tag.description}'>${tag.tag}</A> &nbsp;
                  </c:forEach>
<br>
                  </c:if>
                  <c:out value="${page.data.createDate}" /> 
                  <br>
                  <script type="text/javascript"><!--
google_ad_client = "ca-pub-1068930101706447";
/* babc-story-content */
google_ad_slot = "0292172899";
google_ad_width = 468;
google_ad_height = 15;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
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
                  
                  <c:if test="${!empty page.data.tags.relatedPhotogalleries}">
		        	<B>Photo Gallery:</B>
		        	<c:forEach var="gallery" items="${page.data.tags.relatedPhotogalleries}">
		        		<A HREF='<c:out value="${gallery.galleryUrl}" />'><c:out value="${gallery.name}" /></A>&nbsp;
		        	</c:forEach>
		        </c:if>
                  <BR>
                   <c:if test="${!empty page.data.tags.relatedStories}">
		        	<br><B>Related Stories:</B><br>
		        	<c:forEach var="story" items="${page.data.tags.relatedStories}">
		        		<p><A HREF='<c:out value="${story.storyUrl}" />'><c:out value="${story.title}" /></A><br>
		        		<c:out value="${story.intro}" /></p>
		        	</c:forEach>
		          </c:if>
        		<c:if test="${!empty page.data.tags.relatedPictures}">
			    <tr>
			    	<td VALIGN="top" STYLE="padding-left:12px; padding-right:12px;">
			    		<B>Recent pics:</B><br>
			    		<c:forEach var="picture" items="${page.data.tags.relatedPictures}">
			    			<A HREF="/photogallery/wallpaper/${picture.urlCaption}/${picture.id}.htm" style="border-style: none"/>
			    			<IMG SRC="<c:out value="/dimage/thumb/${picture.id}.htm"/>" HSPACE="5" VSPACE="5" ALIGN="left">
			    			</A>
			    		</c:forEach>
			    	</td>
			    </tr>
			    </c:if>
                  </TD>
                </TR>
                <br>
                </c:if>
                
                <TR>
                  <TD VALIGN="top" CLASS="bot" STYLE="padding-left:12px; padding-right:12px;"> <SPAN CLASS="quote">If you have you a story idea, an article, a hi-res picture, be a citizen Journalist and send it to us at <A HREF="mailto:cj@bollywoodabc.com">cj@bollywoodabc.com</A></SPAN> </TD>
                </TR>
              </TABLE>
              