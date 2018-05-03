<%@page import="es.caib.portafib.utils.Build"%>
<%@page import="es.caib.portafib.logic.utils.LogicUtils"%>
<%@page import="es.caib.portafib.back.security.LoginInfo"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="es.caib.portafib.utils.Configuracio"%>
<%@page import="es.caib.portafib.utils.Constants"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<%!

public static final Map<String, String> mapping;


static {
  //Mapping to existent path
  mapping = new HashMap<String, String>();
  

  mapping.put("solicituddefirma.llistat.totes.plural", Constants.CONTEXT_DEST_ESTATFIRMA + "/list");
  
  mapping.put("solicituddefirma.llistat.pendent.plural", Constants.CONTEXT_DEST_ESTATFIRMA_PENDENT + "/list");
  mapping.put("solicituddefirma.llistat.acceptada.plural", Constants.CONTEXT_DEST_ESTATFIRMA_FIRMAT + "/list");
  mapping.put("solicituddefirma.llistat.noacceptada.plural", Constants.CONTEXT_DEST_ESTATFIRMA_REBUTJAT + "/list");

  mapping.put("colaboracio.gestio", "/dest/colaborador/list"); //Llistat de Col·laboradors

  mapping.put("delegacio.gestio", "/dest/delegat/list"); //Llista delegació

}

%><%

session.setAttribute("mapping", mapping);

%>

<un:useConstants var="LoginInfo" className="es.caib.portafib.back.security.LoginInfo" />
<un:useConstants var="Constants" className="es.caib.portafib.utils.Constants" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<c:out value="${pageContext.response.locale.language}"/>"  lang="<c:out value="${pageContext.response.locale.language}"/>">
<head>
    <!-- Required meta tags-->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!-- Your app title -->
   <title>PortaFib</title>

    <%@ include file="/WEB-INF/jsp/moduls/imports_mobile.jsp"%>
  </head>
  <body class="theme-orange">
    <!-- Status bar overlay for full screen mode (PhoneGap) -->
    <div class="statusbar-overlay"></div>
    <!-- Panels overlay-->
    <div class="panel-overlay"></div>
    <!-- Left panel with reveal effect-->
    <div class="panel panel-left panel-reveal layout-dark">
    
        <div class="list-block">
            <ul>
                <li>
                    <a data-href="<c:url value="/canviarPipella"/>" class="item-link out-link">
                        <div class="item-content">
                            <div class="item-media"><i class="f7-icons">home</i></div>
                            <div class="item-inner">
                                <div class="item-title"><fmt:message key="menuinici"/></div>
                            </div>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
    
        <div class="content-block-title"><fmt:message key="solicituddefirma.llistat.totes.plural"/></div>
        <div class="list-block">
            <ul>
                <li>
                    <a href="<c:url value="${mapping['solicituddefirma.llistat.pendent.plural']}"/>" class="item-link close-panel">
                        <div class="item-content">
                            <div class="item-media"><i class="f7-icons">list</i></div>
                            <div class="item-inner">
                                <div class="item-title"><fmt:message key="solicituddefirma.llistat.pendent.short.plural"/></div>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="${mapping['solicituddefirma.llistat.acceptada.plural']}"/>" class="item-link close-panel">
                        <div class="item-content">
                            <div class="item-media"><i class="f7-icons">list</i></div>
                            <div class="item-inner">
                                <div class="item-title"><fmt:message key="solicituddefirma.llistat.acceptada.plural"/></div>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="${mapping['solicituddefirma.llistat.noacceptada.plural']}"/>" class="item-link close-panel">
                        <div class="item-content">
                            <div class="item-media"><i class="f7-icons size-22">list</i></div>
                            <div class="item-inner">
                                <div class="item-title"><fmt:message key="solicituddefirma.llistat.noacceptada.plural"/></div>
                            </div>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        
<%--         <div class="content-block-title"><fmt:message key="ROLE_DEST.gestio"/></div> --%>
<!--         <div class="list-block"> -->
<!--             <ul> -->
<!--                 <li> -->
<%--                     <a href="<c:url value="/dest/colaborador/list"/>" class="item-link close-panel"> --%>
<!--                         <div class="item-content"> -->
<!--                             <div class="item-media"><i class="f7-icons">person</i></div> -->
<!--                             <div class="item-inner"> -->
<%--                                 <div class="item-title"><fmt:message key="colaboracio.gestio"/></div> --%>
<!--                             </div> -->
<!--                         </div> -->
<!--                     </a> -->
<!--                 </li> -->
<!--                 <li> -->
<%--                     <a href="<c:url value="/dest/delegat/list"/>" class="item-link close-panel"> --%>
<!--                         <div class="item-content"> -->
<!--                             <div class="item-media"><i class="f7-icons">person</i></div> -->
<!--                             <div class="item-inner"> -->
<%--                                 <div class="item-title"><fmt:message key="delegacio.gestio"/></div> --%>
<!--                             </div> -->
<!--                         </div> -->
<!--                     </a> -->
<!--                 </li> -->
<!--             </ul> -->
<!--         </div> -->
    </div>
    <!-- Views -->
    <div class="views">
      <!-- Your main view, should have "view-main" class -->
      <div class="view view-main">
        
        <!-- YIELD CONTINGUT -->
        <jsp:include page="/WEB-INF/jsp/moduls/missatges_mobile.jsp" />
	    <tiles:insertAttribute name="contingut">
	    	<tiles:putAttribute name="contingut_title" value="${contingut_tile}"  />
	    </tiles:insertAttribute>
        
	    <!-- FI YEILD CONTINGUT -->

        <!-- Bottom Toolbar-->
        <div class="toolbar thin">
          <div class="toolbar-inner">
              <b title="Build: <%=Build.BUILD%>"><fmt:message key="app.nom" /> v<%=LogicUtils.getVersio()%></b>
          </div>
        </div>
      </div>
    </div>


   <script src="<c:url value="/js/jquery.js" />"></script>
    <!-- Path to Framework7 Library JS-->
    <script src="<c:url value="/js/framework7.min.js" />"></script>
    <!-- Path to your app js-->
    <script src="<c:url value="/js/my-app.js" />"></script>
  </body>
</html>