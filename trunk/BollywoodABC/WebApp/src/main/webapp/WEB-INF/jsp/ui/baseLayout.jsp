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
              				
              				<tiles:insertAttribute name="content" />
              				
              			</TD>
            		</TR>
        		</TABLE>
        	</TD>
        	<TD WIDTH="120" ALIGN="center" VALIGN="top" BGCOLOR="#E9F0F2">
	        	
					
	    					<script type="text/javascript"><!--
							google_ad_client = "ca-pub-1068930101706447";
							/* babc-right */
							google_ad_slot = "2591682899";
							google_ad_width = 120;
							google_ad_height = 600;
							//-->
							</script>
							<script type="text/javascript"
							src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
							</script>

	    					</TD>
						</TR>
			
	</TABLE>