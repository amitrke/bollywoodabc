<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<HTML>
<HEAD>
	<TITLE>${page.title}</TITLE>
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
	<meta name="author" content='${page.author}'/>
	<meta name="email" content="cj.AT.bollywoodabc.DOT.com"/>
	<meta name="description" content="${page.description}"/>
	<meta name="keywords" content='${page.keywords}'/> 
	<meta HTTP-EQUIV="EXPIRES" CONTENT='${page.expires}'>
	<META NAME="ROBOTS" CONTENT="ALL"> 
	
	<LINK HREF="/style.css" REL="stylesheet" TYPE="text/css">
	<link rel="alternate" type="application/rss+xml" href="/feed/rss.htm">
</HEAD>
<BODY>
<table bgcolor="#FFFFFF" width="100%" cellpadding="0" cellspacing="0">
  <tr>
    <th align="left"><A HREF="/"><IMG SRC="/images/bollywoodabc.gif" ALT="Welcome to BollywoodABC" WIDTH="225" HEIGHT="102" VSPACE="4" BORDER="0"></A></th>
    <th><c:out value="${page.data.caption}"/></th>
  </tr>
</table>
<div align="center"><img src="/photogallery/${page.data.id}/${page.data.urlFilename}.jpeg" alt="${page.description}"/></div>
<a href="javascript: history.go(-1)">Back</a>

<tiles:insertAttribute name="googleAnalytics"/>
</BODY>
</HTML>

