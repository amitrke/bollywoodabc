<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<tiles:insertAttribute name="googleAnalytics"/>
	
	<TABLE WIDTH="1000" BORDER="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0" BGCOLOR="#FFFFFF">
		<TR>
		  <TD><tiles:insertAttribute name="header"/></TD>
		</TR>
    </TABLE>
	
	<tiles:insertAttribute name="body"/>
	
	<TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
		<tr>
			<td><tiles:insertAttribute name="footer"/></td>
		</tr>
	</TABLE>
</BODY>
</HTML>