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

<div id="imgDisplay">
<div>
	<div style="float: left;clear: none;">
	<A HREF="/"><IMG SRC="/images/bollywoodabc.gif" ALT="Welcome to BollywoodABC" WIDTH="199" HEIGHT="90" VSPACE="4" BORDER="0"></A>
	</div>
	<div style="clear: none;">
			<script type="text/javascript"><!--
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
	</div>
	<div>
		<g:plusone></g:plusone>
	</div>
</div>

<div id="imgDisplayHead">
	<div style="float: left;clear: none;">
		<H1><c:out value="${page.data.picture.caption}"/></H1>
	</div>
   	<div align="right"> 
   		Keywords:<c:forEach var="tag" items="${page.data.tags.tags}">
        <A HREF='/t/${tag.tagId}/${tag.tagUrlEscape}.htm' title='${tag.description}'>${tag.tag}</A> &nbsp;
        </c:forEach>
	</div>
</div>

<div style="float:left;clear: none;">
	<img src="/photogallery/${page.data.picture.id}/${page.data.picture.urlFilename}.jpeg" alt="${page.description}"/>
</div>

<div style="height: 300px;" align="left">
	<c:forEach var="picture" items="${page.data.tags.relatedPictures}">
			<A HREF="/photogallery/wallpaper/${picture.urlCaption}/${picture.id}.htm" style="border-style: none"/>
   			<IMG SRC="data:image/jpeg;base64,${picture.thumbBase64}" HSPACE="5" VSPACE="5" ALIGN="left">
   			</A>
		</c:forEach>
</div>

<div>
	<div id="imgDisplayHead">
	Related Stories:
	</div>
		
			<c:forEach var="story" items="${page.data.tags.relatedStories}">
			<p>
				<A HREF='<c:out value="${story.storyUrl}" />'><c:out value="${story.title}" /></A><br>
				<c:out value="${story.intro}" />
			</p>
		</c:forEach>
</div>
</div>
<tiles:insertAttribute name="googleAnalytics"/>
</BODY>
</HTML>

