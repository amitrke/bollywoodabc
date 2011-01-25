<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
    	<TR>
        	<TD WIDTH="170" HEIGHT="500" VALIGN="top" BACKGROUND="/images/mainBG.gif" BGCOLOR="#DB3F26" STYLE="background-repeat:repeat-x;">
        		<tiles:insertAttribute name="leftMenu"/>
        	</TD>
        	<TD WIDTH="2" ALIGN="center" VALIGN="top" bgcolor="#BB0000">&nbsp;</TD>
        	<TD valign="top">
        		<TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
            		<TR>
              			<TD  VALIGN="top" bgcolor='<tiles:insertAttribute name="backgroundColor"/>' STYLE="padding-left:12px; padding-right:12px;">
              				<div id='<tiles:insertAttribute name="contentStyle"/>'>
              				<tiles:insertAttribute name="content" />
              				</div>
              			</TD>
            		</TR>
        		</TABLE>
        	</TD>
        	<TD VALIGN="top" WIDTH="12">&nbsp;</TD>
        	<TD WIDTH="170" ALIGN="center" VALIGN="top" BGCOLOR="#E9F0F2">
	        	<DIV ALIGN="center">
	        		<SPAN CLASS="help">Advertisment</SPAN>
					<TABLE WIDTH="120" BORDER="0" CELLSPACING="0" CELLPADDING="0">
	  					<TR>
	    					<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD>&nbsp;</TD>
						</TR>
					</TABLE>
				</DIV>
			</TD>
		</TR>
	</TABLE>