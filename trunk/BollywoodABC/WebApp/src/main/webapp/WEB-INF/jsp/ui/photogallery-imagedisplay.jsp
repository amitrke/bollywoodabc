<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<HTML>
<HEAD>
	<TITLE>${page.title} ${page.keywords}</TITLE>
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
	<meta name="author" content='${page.author}'/>
	<meta name="email" content="cj.AT.bollywoodabc.DOT.com"/>
	<meta name="description" content="${page.description} ${page.keywords}"/>
	<meta name="keywords" content='${page.keywords}'/> 
	<meta HTTP-EQUIV="EXPIRES" CONTENT='${page.expires}'>
	<META NAME="ROBOTS" CONTENT="ALL"> 
	
	<LINK HREF="/style.css" REL="stylesheet" TYPE="text/css">
	<link rel="alternate" type="application/rss+xml" href="/feed/rss.htm">
</HEAD>
<BODY>
<table bgcolor="#FFFFFF" width="100%" cellpadding="0" cellspacing="0">
  <tr>
    <th align="left"><A HREF="/"><IMG SRC="/images/bollywoodabc.gif" ALT="Welcome to BollywoodABC" WIDTH="199" HEIGHT="90" VSPACE="4" BORDER="0"></A></th>
    <th><script type="text/javascript"><!--
google_ad_client = "ca-pub-1068930101706447";
/* babc-head */
google_ad_slot = "2098611663";
google_ad_width = 728;
google_ad_height = 90;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
    </th>
  </tr>
  <tr>
    <td align="right" colspan="2">
    <b><c:out value="${page.data.picture.caption}"/></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Keywords:<c:forEach var="tag" items="${page.data.tags.tags}">
                  	<A HREF='/t/${tag.tagId}/${tag.tagUrlEscape}.htm' title='${tag.description}'>${tag.tag}</A> &nbsp;
                  </c:forEach>
    <g:plusone></g:plusone></td>
  </tr>
</table>

<div align="center"><img src="/photogallery/${page.data.picture.id}/${page.data.picture.urlFilename}.jpeg" alt="${page.description}"/></div>
<table bgcolor="#FFFFFF" width="100%" cellpadding="0" cellspacing="4">
	<tr>
		<td>
		<c:forEach var="picture" items="${page.data.tags.relatedPictures}">
			<A HREF="/photogallery/wallpaper/${picture.urlCaption}/${picture.id}.htm" style="border-style: none"/>
   			<IMG SRC="data:image/jpeg;base64,${picture.thumbBase64}" HSPACE="5" VSPACE="5" ALIGN="left">
   			</A>
		</c:forEach>
		<p>
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
		</td>
	</tr>
	<tr>
		<td>
		Related Stories:<br>
		<c:forEach var="story" items="${page.data.tags.relatedStories}">
			<p>
				<A HREF='<c:out value="${story.storyUrl}" />'><c:out value="${story.title}" /></A><br>
				<c:out value="${story.intro}" />
			</p>
		</c:forEach>
		</td>
	</tr>
</table>
<a href="javascript: history.go(-1)">Back</a>

<tiles:insertAttribute name="googleAnalytics"/>
</BODY>
</HTML>

