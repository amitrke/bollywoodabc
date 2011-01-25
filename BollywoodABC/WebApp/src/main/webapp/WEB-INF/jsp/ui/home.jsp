<%@page isELIgnored="false" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib 
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib 
uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">
      <TR>
        <TD WIDTH="190"><TABLE WIDTH="100%"  border="0" CELLSPACING="0" CELLPADDING="0">

          <TR>
            <TD><A HREF="/photogallery/main.htm"><IMG SRC="/dimage/14001.htm" WIDTH="190" HEIGHT="295" BORDER="0"></A></TD>
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
                <TD HEIGHT="28" COLSPAN="2" VALIGN="bottom"><A HREF="/forum/"><IMG SRC="/images/moviereviHrd.gif" WIDTH="94" HEIGHT="14" HSPACE="11" VSPACE="4" BORDER="0"></A></TD>
              </TR>
              <TR>

                <TD HEIGHT="2" COLSPAN="2" BACKGROUND="/images/div2.gif"><IMG SRC="/images/spacer.gif"></TD>
              </TR>
              
              <TR>
                <TD HEIGHT="6" COLSPAN="2"><IMG SRC="/images/spacer.gif" WIDTH="1" HEIGHT="1"></TD>
              </TR>
              <TR>
                <TD COLSPAN="2" CLASS="whitetxt" STYLE="padding-left:11px; padding-right:2px;"><SPAN CLASS="whitetxt" STYLE="padding-left:11px; padding-right:2px;"><SPAN STYLE="padding-left:11px;"><A HREF="/forum/"><IMG SRC="/images/movierevImg.jpg" WIDTH="83" HEIGHT="57" BORDER="0" ALIGN="LEFT"></A></SPAN></SPAN>Share your most  thrilling experiences of make-ups and break-ups through this exciting forum which may be in true sense 'one man's pain is another man's entertainment'.  </TD>
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
                <TD HEIGHT="32" ALIGN="right" VALIGN="top" CLASS="yellowtxt">&nbsp;</TD>
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
                	<img width="70" height="70" src="/dimage/thumb/${top3item.pictureId}.htm" alt="" style="border-style: none"></A>
                </TD>
                <TD VALIGN="top" CLASS="whitetxt"><A HREF='/news/${top3item.id}/${top3item.titleUrl}.htm' CLASS="whitetxt">${top3item.intro}</A></TD>
              </TR>
              </c:forEach>
              
			  <TR>
                <TD COLSPAN="2" ALIGN="RIGHT" CLASS="yellowtxt"><A HREF="news/latest/1.htm" CLASS="yellowtxt">More >></A></TD>
              </TR>
              <TR>
                <TD COLSPAN="2"></TD>

              </TR></TABLE></TD>
          </TR>
          <TR>
            <TD VALIGN="top" BGCOLOR="#434945"><TABLE WIDTH="98%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0">
              <TR>
                <TD HEIGHT="28" VALIGN="bottom"><IMG SRC="/images/weeklycolHrd.gif" WIDTH="135" HEIGHT="18" HSPACE="11" VSPACE="3"></TD>
              </TR>
              <TR>
                <TD HEIGHT="2" BACKGROUND="/images/div3.gif"><IMG SRC="/images/spacer.gif"></TD>

              </TR>
              <TR>
                <TD HEIGHT="136" VALIGN="top"><TABLE WIDTH="94%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0">
                  <TR>
                    <TD WIDTH="75%" CLASS="whitetxt"><STRONG><A HREF='/news/${page.data.babcExtra.id}/${page.data.babcExtra.titleUrl}.htm' CLASS='yellowtxt'>${page.data.babcExtra.title}</A></STRONG></TD>
                    <TD WIDTH="25%"></TD>
                  </TR>
                  <TR>

                    <TD CLASS="whitetxt"><A HREF='/news/${page.data.babcExtra.id}/${page.data.babcExtra.titleUrl}.htm' CLASS='whitetxt'>${page.data.babcExtra.intro}</A><P>&nbsp;</P></TD>
                    <TD ALIGN="right"><A HREF='/news/${page.data.babcExtra.id}/${page.data.babcExtra.titleUrl}.htm' style="text-decoration:none">
                	<img width="70" height="70" src="/dimage/thumb/${page.data.babcExtra.pictureId}.htm" alt="" style="border-style: none"></A></TD>
                  </TR>
                  <TR>
                    <TD>&nbsp;</TD>
                    <TD>&nbsp;</TD>
                  </TR>
                  
                </TABLE></TD>

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
                <TD HEIGHT="28" VALIGN="bottom"><IMG SRC="/images/bollywoodnewsHrd.gif" WIDTH="110" HEIGHT="19" HSPACE="11" VSPACE="3"></TD>
              </TR>
              <TR>
                <TD HEIGHT="2" BACKGROUND="/images/div3.gif"><IMG SRC="/images/spacer.gif"></TD>

              </TR>
              
              <TR>
                <TD HEIGHT="228" VALIGN="top"><TABLE WIDTH="96%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0">
                  <tr>
                    <td width="15" valign="top"><img src="/images/yellowArrow.gif" width="12" height="7" vspace="4"></td>
                    <td height="30"><a href="http://www.bollywoodabc.com/story.php?id=391" class="whitelink">Simon Cowell has the 'worst celebrity hair'</a></td>
                  </tr><tr>
                    <td width="15" valign="top"><img src="/images/yellowArrow.gif" width="12" height="7" vspace="4"></td>

                    <td height="30"><a href="http://www.bollywoodabc.com/story.php?id=337" class="whitelink">Selina goes topless</a></td>
                  </tr><tr>
                    <td width="15" valign="top"><img src="/images/yellowArrow.gif" width="12" height="7" vspace="4"></td>
                    <td height="30"><a href="http://www.bollywoodabc.com/story.php?id=335" class="whitelink">Rumour has it Kareena, Shah Rukh out</a></td>
                  </tr><tr>
                    <td width="15" valign="top"><img src="/images/yellowArrow.gif" width="12" height="7" vspace="4"></td>
                    <td height="30"><a href="http://www.bollywoodabc.com/story.php?id=334" class="whitelink">Sameera Reddy wants to be tough Bollywood babe</a></td>

                  </tr><tr>
                    <td width="15" valign="top"><img src="/images/yellowArrow.gif" width="12" height="7" vspace="4"></td>
                    <td height="30"><a href="http://www.bollywoodabc.com/story.php?id=333" class="whitelink">When Mika kissed Rakhi, but that is her version</a></td>
                  </tr><tr>
                    <td width="15" valign="top"><img src="/images/yellowArrow.gif" width="12" height="7" vspace="4"></td>
                    <td height="30"><a href="http://www.bollywoodabc.com/story.php?id=339" class="whitelink">Once a hot date Dino, hooks off now</a></td>
                  </tr>
				  <TR>

                    <TD WIDTH="15" VALIGN="top"></TD>
                    <TD HEIGHT="30" ALIGN="RIGHT"><A HREF="bollywoodlinks-more.php" CLASS="whitelink">More >></A></TD>
                  </TR>
                </TABLE></TD>
              </TR>
            </TABLE></TD>
          </TR>
          <TR>

            <TD BACKGROUND="/images/mainBG.gif"><TABLE WIDTH="98%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0">
              <TR>
                <TD HEIGHT="28" VALIGN="bottom"><IMG SRC="/images/hollynewsHrd.gif" WIDTH="110" HEIGHT="18" HSPACE="11" VSPACE="3"></TD>
              </TR>
              <TR>
                <TD HEIGHT="2" BACKGROUND="/images/div2.gif"><IMG SRC="/images/spacer.gif"></TD>
              </TR>
              <TR>
                <TD VALIGN="top"><TABLE WIDTH="96%"  border="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0">

                    <tr>
                      <td width="15" valign="top"><img src="/images/yellowArroow1.gif" width="12" height="7" vspace="4"></td>
                      <td height="30"><a href="http://people.aol.com/people/galleries/0,19884,1203580,00.html" class="whitelink">Britney's Miami Retreat</a></td>
                    </tr><tr>
                      <td width="15" valign="top"><img src="/images/yellowArroow1.gif" width="12" height="7" vspace="4"></td>
                      <td height="30"><a href="http://people.aol.com/people/articles/0,19736,1205279,00.html" class="whitelink">Jolie: 'Terrified' During Birth</a></td>
                    </tr><tr>
                      <td width="15" valign="top"><img src="/images/yellowArroow1.gif" width="12" height="7" vspace="4"></td>

                      <td height="30"><a href="http://people.aol.com/people/galleries/0,19884,1201344_1203645,00.html" class="whitelink">Hottest Bachelors</a></td>
                    </tr>
					<TR>
                      <TD WIDTH="15" VALIGN="top"></TD>
                      <TD HEIGHT="30" ALIGN="RIGHT"><A HREF="hollywoodlinks-more.php" CLASS="whitelink">More >></A></TD>
                    </TR>
                </TABLE></TD>
              </TR>

            </TABLE></TD>
          </TR>
        </TABLE></TD>
      </TR>
    </TABLE>