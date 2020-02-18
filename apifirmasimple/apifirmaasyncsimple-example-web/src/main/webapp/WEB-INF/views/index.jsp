
<%-- XYZ ZZZ
<%@page language="java" contentType="text/html;charset=UTF-8"%><%@page import="java.util.Properties"%><%@page
    language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/html_header.jsp"%>
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%><%@ include file="/WEB-INF/views/include.jsp"%>
<tags:template>
    <jsp:body>

<br />
<br />
<br />
<br />
<center>
    <h2>
      <fmt:message key="exemplefirmasimple" />
    </h2>
    <h4>
        <i>Firma Web Síncrona & Firma en Servidor</i>
    </h4>

    <table border="3" style="height: 100%; vertical-align: middle; margin-left: auto; margin-right: auto;" height="100%">
        <tr align="center">
            <td valign="middle">

                <table border="1px">
                    <tr>
                        <td align="center" style="padding: 20px"><a href="./firmaweb/form"
                                    class="btn btn-primary btn-lg active" role="button" aria-pressed="true"> <fmt:message
                                            key="firmaweb" />
                        </a></td>

                        <td align="center" style="padding: 20px"><a href="./web/configuracio"
                                    class="btn btn-info btn-lg active" role="button" aria-pressed="true"> <fmt:message
                                            key="configuracio.web" />
                        </a></td>

                    </tr>

                    <tr>
                        <td align="center" style="padding: 20px"><a href="./firmaenservidor/form"
                                    class="btn btn-primary btn-lg active" role="button" aria-pressed="true"> <fmt:message
                                            key="firmaservidor" />
                        </a></td>

                        <td align="center"><a href="./server/configuracio" class="btn btn-info btn-lg active"
                                    role="button" aria-pressed="true"> <fmt:message key="configuracio.servidor" />
                        </a></td>

                    </tr>

                </table>


            </td>
        </tr>
    </table>
    <br />
<b><i>

<table width="100%" style="height: 100%; vertical-align: middle; margin-left: auto; margin-right: auto;">

<tr>
   <td style="text-align: right;">Java:&nbsp;</td>
   <td>OpenJDK 11</td>
</tr>
<tr>
   <td style="text-align: right;">Maven:&nbsp;</td>
   <td>3.6.3</td>
</tr>
<tr>
   <td style="text-align: right;">JBoss:&nbsp;</td>
   <td>7.2</td>
</tr>
<tr>
   <td style="text-align: right;">FrameWork Java:&nbsp;</td>
   <td>Spring 5.0.0.RELEASE</td>
</tr>
<tr>
   <td style="text-align: right;">FrameWork HTML, CSS i JS:&nbsp;</td>
   <td>BootStrap 4.4.1</td>
</tr>
<tr>
  <td style="text-align: right;">Codi Font:&nbsp;</td>
  <td><a target="_blank" href="https://github.com/GovernIB/portafib/tree/portafib-2.0/apifirmasimple/apifirmaasyncsimple-example-web">
Forja GitHub del GovernIB</a></td>
</tr>
<tr>
<td style="text-align: right;">Documentaci&oacute;:&nbsp;</td>
<td><a target="_blank" href="https://github.com/GovernIB/portafib/blob/portafib-2.0/doc/Manual_Integracio_API_Firma_Simple_v1_0.odt?raw=true">
Manual_Integracio_API_Firma_Simple_v1_0.odt</a></td>
</tr>

</table>
 
<br />
<br />

</i></b>
</center>



 </jsp:body>
</tags:template>


<%--  XYZ ZZZ

<%@ include file="/WEB-INF/views/html_footer.jsp"%>
--%>
