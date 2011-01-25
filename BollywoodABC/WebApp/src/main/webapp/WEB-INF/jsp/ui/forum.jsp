<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<script src="http://ajax.googleapis.com/ajax/libs/dojo/1.5/dojo/dojo.xd.js" type="text/javascript"></script>
<script>
	function postData(){
		 dojo.xhrPost({
		     form:"forum",
		     url: "/jsonrpc/forum.htm", 
		     handle: function(dataOrError, ioArgs){
		        if(dojo.isString(dataOrError)){
		           alert(dataOrError);
		        }else{
		           alert('An error ocured while posting data to server.');
		        }
		     }
		 });	
	}
</script>
<TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
            <TR>
              <TD  VALIGN="top" BACKGROUND="/images/mainBG.gif" STYLE="padding-left:12px; padding-right:12px;">
              
              <TABLE WIDTH="100%"  BORDER="0" CELLSPACING="0" CELLPADDING="2">
                <TR>
                  <TD HEIGHT="32" VALIGN="bottom" COLSPAN="2"><h1>Forum</h1></TD>
                </TR>
                <TR>
                  <TD HEIGHT="2" COLSPAN="2" BACKGROUND="/images/div2.gif"><IMG SRC="/images/spacer.gif" WIDTH="1" HEIGHT="1"></TD>
                </TR>
                <TR>
                  <TD HEIGHT="2" COLSPAN="2" BACKGROUND="/images/div2.gif">
                  <c:choose>
                  	<c:when test="${empty userPrincipal}">
                  		<p>Please <a href="${loginURL}">sign in</a>.</p>
                  	</c:when>
                  	<c:otherwise>
                  		<form name='forum'>
	                  		<textarea name='message' rows="4" cols="60"></textarea>
	                  		<input type="button" value="post" onClick='postData()'>
	                  	</form>
                  	</c:otherwise>
                  </c:choose>
                  	
                  </TD>
                </TR>
                
                		      
		<c:forEach var="story" items="${page.storyList}">
  		<tr>
			<td height="31" colspan="2" class="yellowtxt"><strong><A HREF='/news/${story.id}/${story.titleUrl}.htm' CLASS='yellowtxt'>${story.title}</A></strong></td>
		</tr>
		<tr>
			<td width="1"><img src="/dimage/thumb/${story.pictureId}.htm" alt="" /></td>
			<td valign="top" class="whitetxt">${story.intro}</td>
		</tr>
		</c:forEach>
  <TR >
    <TD HEIGHT="1" COLSPAN="2" BACKGROUND="/images/dotHorizontal.gif"></TD>
  </TR>
  <TR>
    <TD COLSPAN="2">&nbsp;</TD>
  </TR>

  <TR>
    <TD COLSPAN="2">
    	<c:if test="${page.paging.previousPageNo > 0}">
    		<A href='/news/latest/<c:out value="${page.paging.previousPageNo}" />.htm' class="whitetxt"> Previous </A>
    	</c:if>
    	&nbsp; Showing <c:out value="${page.paging.startRecCount}" /> to <c:out value="${page.paging.endRecCount}" /> of total <c:out value="${page.paging.totalRecCount}" /> Articles 
    	
    	<c:if test="${page.paging.nextPageNo > 0}">
    		<A href='/news/latest/<c:out value="${page.paging.nextPageNo}" />.htm' class="whitetxt"> Next </A>
    	</c:if>
    	
    	</TD>
    	
    	
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
              
              </TD>
            </TR>
        </TABLE>