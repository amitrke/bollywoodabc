<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
         <TR>
           <TD  VALIGN="top" BACKGROUND="/images/mainBG.gif" STYLE="padding-left:12px; padding-right:12px;">
           
           <div id="storyListDiv">
      <div>
           <div style="width: 300px;float: left;clear: none;">
	  			<h1>Archives : ${page.range}</h1>
	  		</div>
	  		<div align="right">
	  			<g:plusone></g:plusone>
	  		</div>
	  	</div>
	  <hr>
	  <c:forEach var="story" items="${page.storyList}">
	  	 <p>${story.createDateAsString} : <a href="/news/${story.id}/${story.titleUrl}.htm">${story.title}</a>
	  	 </p>
	  </c:forEach>
  </div>
           </TD>
         </TR>
     </TABLE>