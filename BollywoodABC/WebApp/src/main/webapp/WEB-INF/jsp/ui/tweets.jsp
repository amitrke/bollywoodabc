<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><TABLE id="tweetList" WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">

                <TR>
                  <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;">&nbsp;</TD>
                </TR>
                <TR>
                  <TD HEIGHT="22" VALIGN="middle" CLASS="h1" STYLE="padding-left:12px; padding-right:12px;">Bollywood Tweets</TD>
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
                  <table width="100%" border="0">
                  <c:forEach var="tweet" items="${page.data.tweets}">
                  <tr>
                  	<td width="20%">
                  	<c:if test="${!empty tweet.facePic}"><A HREF='/t/${tweet.tagId}/${tweet.titleUrl}.htm' style="text-decoration:none" title="${tweet.tweetDesc}">
                  	<IMG width="40" height="40" SRC="<c:out value="/dimage/${tweet.facePic}.htm"/>" HSPACE="1" VSPACE="1" ALIGN="left">
                  	</A>
                  	<br>
                  	</c:if>
                  	<c:out value="${tweet.name}"/>
                  	</td>
                  	<td><c:out value="${tweet.tweet}"/></td>
                  </tr>
                  </c:forEach>
                  </table>
                  </TD>
                </TR>
                
               
                <TR>
                  <TD VALIGN="top" STYLE="padding-left:12px; padding-right:12px;"></TD>
                </TR>
				<TR style="background-color: #FF4D4D;text-align: right; font-weight: bold;">
				    <TD COLSPAN="2">
				    	<c:if test="${page.data.paging.previousPageNo > 0}">
				    		<A href='<c:out value="${page.data.paging.previousPageNo}" />.htm'> Previous </A>
				    	</c:if>
				    	&nbsp; Showing <c:out value="${page.data.paging.startRecCount}" /> to <c:out value="${page.data.paging.endRecCount}" /> of total <c:out value="${page.data.paging.totalRecCount}" /> Tweets 
				    	
				    	<c:if test="${page.data.paging.nextPageNo > 0}">
				    		<A href='<c:out value="${page.data.paging.nextPageNo}" />.htm'> Next </A>
				    	</c:if>
				    	
				    	</TD>
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
                
                
                <TR>
                  <TD VALIGN="top" CLASS="bot" STYLE="padding-left:12px; padding-right:12px;"> <SPAN CLASS="quote">If you have you a story idea, an article, a hi-res picture, be a citizen Journalist and send it to us at <A HREF="mailto:cj@bollywoodabc.com">cj@bollywoodabc.com</A></SPAN> </TD>
                </TR>
              </TABLE>
              