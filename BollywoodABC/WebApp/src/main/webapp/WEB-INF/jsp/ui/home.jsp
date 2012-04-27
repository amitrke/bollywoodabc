<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
      <TR>
        <TD WIDTH="190" valign="top"><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">

          <TR>
            <TD><A HREF="/photogallery/main.htm"><img src="data:image/jpeg;base64,${page.data.mainPic.base64}" alt="${page.data.mainPic.caption}" WIDTH="214" HEIGHT="305" BORDER="0"/></A></TD>
          </TR>
          <TR>
            <TD HEIGHT="23" BACKGROUND="/images/hrdBG.gif"><A HREF="/photogallery/main.htm"><IMG SRC="/images/babeweekHrd.gif" WIDTH="122" HEIGHT="15" HSPACE="11" BORDER="0"></A></TD>
          </TR>
          <TR>
            <TD HEIGHT="116" ALIGN="center" BGCOLOR="#434945"><A HREF="/photogallery/main.htm"><IMG SRC="/images/hotgallImmg.jpg" WIDTH="156" HEIGHT="97" BORDER="0" CLASS="whiteBrdImg"></A></TD>
          </TR>

          <TR>
            <TD HEIGHT="2" BACKGROUND="/images/div3.gif"><IMG SRC="/images/spacer.gif" WIDTH="1" HEIGHT="1"></TD>
          </TR>
          <TR>
            <TD HEIGHT="22" BGCOLOR="#434945"><TABLE WIDTH="94%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0">
              <TR>
                <TD><IMG SRC="/images/arrowl.gif" WIDTH="6" HEIGHT="10"></TD>
                <TD ALIGN="center"><A HREF="/photogallery/main.htm"><IMG SRC="/images/hotgallHrd.gif" ALT="Sizzlers" WIDTH="128" HEIGHT="19" BORDER="0"></A></TD>
                <TD><IMG SRC="/images/arrowr.gif" WIDTH="6" HEIGHT="12"></TD>

              </TR>
            </TABLE></TD>
          </TR>
          <TR>
            <TD BACKGROUND="/images/mainBG.gif"><TABLE WIDTH="98%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0">
              <TR>
                <TD HEIGHT="28" COLSPAN="2" VALIGN="bottom" class="yellowtxt"><b>&nbsp;BABC Interactive</b></TD>
              </TR>
              <TR>

                <TD HEIGHT="2" COLSPAN="2" BACKGROUND="/images/div2.gif"><IMG SRC="/images/spacer.gif"></TD>
              </TR>
              
              <TR>
                <TD HEIGHT="6" COLSPAN="2"><IMG SRC="/images/spacer.gif" WIDTH="1" HEIGHT="1"></TD>
              </TR>
              <TR>
                <TD COLSPAN="2" CLASS="whitetxt" STYLE="padding-left:11px; padding-right:2px;">
                	Subscribe weekly newsletter <b>support@bollyabc.appspotmail.com</b> with subject "subscribe". 
                	Add <b>bollyabc@appspot.com</b> to your Google Talk messenger.
                </TD>
              </TR>

              <TR ALIGN="right">
                <TD COLSPAN="2" STYLE="padding-right:2px;">&nbsp;</TD>
              </TR>
              <TR ALIGN="right">
                <TD COLSPAN="2" STYLE="padding-right:2px;">&nbsp;</TD>
              </TR>
            </TABLE></TD>
          </TR>
        </TABLE></TD>

        <TD VALIGN="top"><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
          <TR VALIGN="TOP">
            <TD BACKGROUND="/images/mainBG.gif" STYLE="padding-left:12px; padding-right:12px;"><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
              
              <TR>
                <TD width="160"><h1>Top Stories</h1></TD>
                <TD HEIGHT="32" ALIGN="right" VALIGN="middle" CLASS="yellowtxt"><g:plusone></g:plusone> &nbsp;
                <A HREF='/feed/rss.htm' style="text-decoration:none">
                	<img width="28" height="28" src="/images/feed-icon-28x28.png" alt="RSS Feed" style="border-style: none"></A>
                 </TD>
              </TR>

			  <tr> 
			  	<td height="1" colspan="3">
			  	<div ID="storyListDiv">
			  		<c:forEach var="top3item" items="${page.data.homePageStories}">
			  		<hr>
			  		<div ID="storyListElement" onClick="window.location='/news/${top3item.id}/${top3item.titleUrl}.htm';">
                	<img src="data:image/jpeg;base64,${top3item.picture.thumbBase64}" alt="" style="border-style: none;" height="70" width="70">
                 	<h2><a href="/news/${top3item.id}/${top3item.titleUrl}.htm">${top3item.title}</a></h2>
                	${top3item.createDateAsString} : ${top3item.intro}
                	</div>
                	</c:forEach>
			  	</div>
			  	
			  	</td>
              </tr>	
			  <TR>
                <TD COLSPAN="2" ALIGN="RIGHT" CLASS="yellowtxt"><A HREF="/news/latest/1.htm" CLASS="yellowtxt">More Stories>></A>
                <br>
                </TD>
              </TR>
              <TR>
                <TD COLSPAN="2"></TD>
              </TR></TABLE></TD>
          </TR>
          <TR>
            <TD VALIGN="top" BGCOLOR="#434945"><TABLE WIDTH="98%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="1">
                  <TR><TD>
                  <div style="width: 595px; height: 137px; overflow: hidden;">
                  	<c:forEach var="recentPic" items="${page.data.recentPics}">
                    	<A href="/photogallery/wallpaper/${recentPic.urlCaption}/${recentPic.id}.htm" title="${recentPic.caption}" style="border-style: none">
                    	<img src="data:image/jpeg;base64,${recentPic.thumbBase64}" alt="${recentPic.caption}" /></A>
                    </c:forEach>
                    </div>
                    </TD>
                  </TR>
            </TABLE></TD>
          </TR>
        </TABLE></TD>
        <TD WIDTH="190" VALIGN="top" BGCOLOR="#D93E25">
            <div id="homeTweetsDiv">
        	<h1>Tweets</H1>
        	<hr>
        	<div id="homeTweetsContentDiv">
        	<c:forEach var="tweet" items="${page.data.twitterTimeline}">
        	<p>${tweet}</p>
        	</c:forEach>
        	</div>
        	<a href="/news/tweets/1.htm" class="whitelink">More Tweets&gt;&gt;</a>
        </div>
        
        <div id="homeTagCloud">
        	<h1>Tag Cloud</H1>
        	<hr>
        	<a href="/t/575001/Aishwarya-Rai.htm" class="whitetxt">Aishwarya Rai</a> &nbsp; 
        	<a href="/t/525002/Kareena-Kapoor.htm" class="whitetxt" style="font-size: large;">Kareena Kapoor</a> &nbsp;
        	<a href="/t/542001/Deepika-Padukone.htm" class="whitetxt" style="font-size: x-large;">Deepika Padukone</a> &nbsp;
        	<a href="/t/526001/Anushka-Sharma.htm" class="whitetxt" style="font-size: x-small;">Anushka Sharma </a> &nbsp; 
        </div>
          </TD>
      </TR>
    </TABLE>