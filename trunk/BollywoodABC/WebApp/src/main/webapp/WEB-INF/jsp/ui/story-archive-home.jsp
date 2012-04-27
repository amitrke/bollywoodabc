<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
         <TR>
           <TD  VALIGN="top" BACKGROUND="/images/mainBG.gif" STYLE="padding-left:12px; padding-right:12px;">
           
           <div id="storyListDiv">
      <div>
           <div style="width: 100px;float: left;clear: none;">
	  <h1>Archives</h1>
	  </div>
	  		<div align="right">
	  			<g:plusone></g:plusone>
	  		</div>
	  	</div>
	  	<hr>
	  <c:forEach var="year" items="${page.years}">
	  <p>
	  	<h2> ${year} :</h2> <a href="/news/archive-Q1-${year}.htm">Q1</a>&nbsp;
	  	<a href="/news/archive-Q2-${year}.htm">Q2</a>&nbsp;
	  	<a href="/news/archive-Q3-${year}.htm">Q3</a>&nbsp;
	  	<a href="/news/archive-Q4-${year}.htm">Q4</a>&nbsp;
	  	</p>
	  </c:forEach>
  </div>
           </TD>
         </TR>
     </TABLE>