<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
         <TR>
           <TD  VALIGN="top" BACKGROUND="/images/mainBG.gif" STYLE="padding-left:12px; padding-right:12px;">
           
           <div id="storyListDiv">
      <div>
           <div style="width: 100px;float: left;clear: none;">
	  			<h1>Top Stories</h1>
	  		</div>
	  		<div align="right">
	  			<g:plusone></g:plusone>
	  		</div>
	  	</div>
	  
	  <c:forEach var="story" items="${page.storyList}">
	  	<hr>
  		<div ID="storyListElement" onClick="window.location='/news/${story.id}/${story.titleUrl}.htm';">

             	<img src="data:image/jpeg;base64,${story.picture.thumbBase64}" alt="${story.picture.caption}" style="border-style: none;" height="70" width="70">
              	<h2><a href="/news/${story.id}/${story.titleUrl}.htm">${story.title}</a></h2>
             	${story.createDateAsString} : ${story.intro}
             	</div> 
	  </c:forEach>
	  
	  <c:if test="${page.paging.previousPageNo > 0}">
   		<A href='<c:out value="${page.paging.previousPageNo}" />.htm' class="whitetxt"> Previous </A>
   	</c:if>
   	&nbsp; Showing <c:out value="${page.paging.startRecCount}" /> to <c:out value="${page.paging.endRecCount}" /> of total <c:out value="${page.paging.totalRecCount}" /> Articles 
   	
   	<c:if test="${page.paging.nextPageNo > 0}">
   		<A href='<c:out value="${page.paging.nextPageNo}" />.htm' class="whitetxt"> Next </A>
   	</c:if>
  </div>
           </TD>
         </TR>
     </TABLE>