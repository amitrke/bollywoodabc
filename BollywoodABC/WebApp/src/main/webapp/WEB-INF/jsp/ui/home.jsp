<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
      <TR>
        <TD WIDTH="190"><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">

          <TR>
            <TD><A HREF="/photogallery/main.htm"><IMG SRC="/dimage/14001.jpeg" WIDTH="190" HEIGHT="295" BORDER="0"></A></TD>
          </TR>
          <TR>
            <TD HEIGHT="23" BACKGROUND="/images/hrdBG.gif"><A HREF="/photogallery/main.htm"><IlatestnewsHrd.gifMG SRC="/images/babeweekHrd.gif" WIDTH="122" HEIGHT="15" HSPACE="11" BORDER="0"></A></TD>
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
            <TD HEIGHT="458" BACKGROUND="/images/mainBG.gif" STYLE="padding-left:12px; padding-right:12px;"><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
              
              <TR>
                <TD HEIGHT="32" VALIGN="bottom" width="10"><IMG SRC="/images/latestnewsHrd.gif" WIDTH="85" HEIGHT="16" VSPACE="3"></TD>
                <TD HEIGHT="32" ALIGN="right" VALIGN="middle" CLASS="yellowtxt"><g:plusone></g:plusone> &nbsp;
                <A HREF='/feed/rss.htm' style="text-decoration:none">
                	<img width="28" height="28" src="/images/feed-icon-28x28.png" alt="RSS Feed" style="border-style: none"></A>
                 </TD>
              </TR>

			 <c:forEach var="top3item" items="${page.data.homePageStories}">
			
			  <tr> 
			  	<td height="1" colspan="2" background="/images/dotHorizontal.gif"></td>
              </tr>			
              <TR>
                <TD HEIGHT="31" COLSPAN="2" CLASS="yellowtxt"><STRONG><A HREF='/news/${top3item.id}/${top3item.titleUrl}.htm' CLASS='yellowtxt'>${top3item.title}</A></STRONG></TD>
              </TR>
              <TR>
                <TD WIDTH="10%"><A HREF='/news/${top3item.id}/${top3item.titleUrl}.htm' style="text-decoration:none">
                	<img width="70" height="70" src="/dimage/thumb/${top3item.pictureId}.jpeg" alt="" style="border-style: none"></A>
                </TD>
                <TD VALIGN="top" CLASS="whitetxt"><A HREF='/news/${top3item.id}/${top3item.titleUrl}.htm' CLASS="whitetxt">${top3item.intro}</A></TD>
              </TR>
              </c:forEach>
              
			  <TR>
                <TD COLSPAN="2" ALIGN="RIGHT" CLASS="yellowtxt"><A HREF="news/latest/1.htm" CLASS="yellowtxt">More Stories>></A></TD>
              </TR>
              <TR>
                <TD COLSPAN="2"></TD>

              </TR></TABLE></TD>
          </TR>
          <TR>
            <TD VALIGN="top" BGCOLOR="#434945"><TABLE WIDTH="98%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="1">
                  <TR><TD>
                  	<c:forEach var="recentPic" items="${page.data.recentPics}">
                    	<A href="/photogallery/wallpaper/${recentPic.urlCaption}/${recentPic.id}.htm" style="border-style: none"><img src="/dimage/thumb/${recentPic.id}.jpeg" alt="${recentPic.caption}" /></A>
                    </c:forEach></TD>
                  </TR>
            </TABLE></TD>
          </TR>
        </TABLE></TD>
        <TD WIDTH="190" VALIGN="top" BGCOLOR="#D93E25"><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
          <TR>
            <TD><A HREF="/photogallery/main.htm"><IMG SRC="/images/showcaseImg.jpg" WIDTH="190" HEIGHT="177" BORDER="0"></A></TD>
          </TR>
          <TR>

            <TD HEIGHT="23" ALIGN="right" BACKGROUND="/images/hrdBG.gif"><A HREF="/photogallery/main.htm"><IMG SRC="/images/showcaseBtn.gif" ALT="BollywoodABC Gallery" WIDTH="170" HEIGHT="19" HSPACE="4" BORDER="0"></A></TD>
          </TR>
          <TR>
            <TD BGCOLOR="#434945"><TABLE WIDTH="98%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0">
              <TR>
                <TD HEIGHT="28" VALIGN="bottom" CLASS="yellowtxt">&nbsp;<b>Bollywood Tweets</b></TD>
              </TR>
              <TR>
                <TD HEIGHT="2" BACKGROUND="/images/div3.gif"><IMG SRC="/images/spacer.gif"></TD>

              </TR>
              
              <TR>
                <TD HEIGHT="228" VALIGN="top"><TABLE WIDTH="96%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0">
                  <c:forEach var="tweet" items="${page.data.twitterTimeline}">
                  <tr>
                    <td width="15" valign="top"><img src="/images/yellowArrow.gif" width="12" height="7" vspace="4"></td>
                    <td height="30" CLASS="yellowtxt">${tweet}</td>
                  </tr>
                  </c:forEach>

                    <TD WIDTH="15" VALIGN="top"></TD>
                    <TD HEIGHT="30" ALIGN="RIGHT"><A HREF="/news/tweets/1.htm" CLASS="whitelink">More Tweets>></A></TD>
                  </TR>
                </TABLE></TD>
              </TR>
            </TABLE></TD>
          </TR>
          <TR>

            <TD BACKGROUND="/images/mainBG.gif"><TABLE WIDTH="98%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0">
              <TR>
                <TD HEIGHT="28" VALIGN="bottom" class="yellowtxt"><b>Tag Cloud</b></TD>
              </TR>
              <TR>
                <TD HEIGHT="2" BACKGROUND="/images/div2.gif"><IMG SRC="/images/spacer.gif"></TD>
              </TR>
              <TR>
                <TD VALIGN="top" class="whitetxt">
                	<A href="/t/575001/Aishwarya-Rai.htm" class="whitetxt">Aishwarya Rai</A> &nbsp; 
                	<A href="/t/525002/Kareena-Kapoor.htm" class="whitetxt" style="font-size: large;">Kareena Kapoor</A> &nbsp;
                	<A href="/t/542001/Deepika-Padukone.htm" class="whitetxt" style="font-size: x-large;">Deepika Padukone</A> &nbsp;
                	<A href="/t/526001/Anushka-Sharma.htm" class="whitetxt" style="font-size: x-small;">Anushka Sharma </A> &nbsp;  
                </TD>
              </TR>

            </TABLE></TD>
          </TR>
        </TABLE></TD>
      </TR>
    </TABLE>