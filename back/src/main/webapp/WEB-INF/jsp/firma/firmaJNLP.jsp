<%@ page contentType="application/x-java-jnlp-file" 
%><%@page import="es.caib.portafib.utils.Configuracio"
%><%@include file="/WEB-INF/jsp/moduls/includes.jsp"
%><%
  String appUrl = "";
  String appletUrl = "";
  {
    appUrl = pageContext.getRequest().getScheme() + "://" + pageContext.getRequest().getServerName() + ":" 
        + pageContext.getRequest().getServerPort() +  request.getContextPath();
  }
  appletUrl = appUrl + "/applet/";
  request.getSession().setAttribute("AppUrl", appUrl);
  request.getSession().setAttribute("AppletUrl", appletUrl );

%><un:useConstants var="Constants" className="es.caib.portafib.utils.Constants"/>
<%-- JNLP File for Aplicació de Firma de PortaFIB (Basat en el MiniApplet @firma) --%>
<?xml version="1.0" encoding="utf-8"?> 
<jnlp spec="1.0+" codebase="<c:url value="/applet" />" >
    <information>
        <title>PortaFIB Applet</title>
        <vendor>PortaFIB</vendor>
        <homepage href="http://www.fundaciobit.org/" />
        <description>Aplicaci\u00F3 de Firma de PortaFIB (Basat en el MiniApplet @firma)</description>
        <icon href="<c:url value="/img/portafib.ico" />" />
    </information>
    <security>
        <all-permissions/>
    </security>
    <resources>
        <j2se version="1.6+" java-vm-args="-Xmx1024m" />
        <jar href="${AppletUrl}portafib-applet-signed-<%=es.caib.portafib.versio.Versio.VERSIO%>.jar" main="true" />
        <jar href="${AppletUrl}miniapplet-full.jar" />
    </resources>
    <applet-desc
      documentBase="<c:url value="/applet" />"
      name="Aplicaci\u00F3 de Firma de PortaFIB (Basat en el MiniApplet @firma)"
      main-class="es.caib.portafib.applet.SignApplet"
      width="475"
      height="300">

      <c:forEach  var="fitxer"  items="${fitxers}" varStatus="status">
      <param name="${Constants.APPLET_IDNAME}_${status.index}" value="${fn:escapeXml(fitxer.idname)}"/>
      <c:set var="sourceurl" value="${AppUrl}${fitxer.source}"/>
      <param name="${Constants.APPLET_SOURCE}_${status.index}" value="<c:url value="${fn:escapeXml(sourceurl)}" />"/>
      <c:set var="desturl" value="${AppUrl}${fitxer.destination}"/>
      <param name="${Constants.APPLET_DESTINATION}_${status.index}" value="<c:url value="${fn:escapeXml(desturl)}" />"/> 
      <param name="${Constants.APPLET_LOCATION_SIGN_TABLE}_${status.index}" value="${fitxer.locationSignTable}"/>
      <param name="${Constants.APPLET_REASON}_${status.index}" value="${fn:escapeXml(fitxer.reason)}"/>
      <param name="${Constants.APPLET_FIRMATPERFORMAT}_${status.index}" value="${fn:escapeXml(fitxer.firmatPerFormat)}"/>
      <param name="${Constants.APPLET_SIGN_NUMBER}_${status.index}" value="${fitxer.signNumber}"/>
      <param name="${Constants.APPLET_LANGUAGE_SIGN}_${status.index}" value="${fitxer.languageSign}"/>
      <param name="${Constants.APPLET_SIGN_TYPE}_${status.index}" value="${fitxer.signType}"/>
      <param name="${Constants.APPLET_SIGN_ALGORITHM}_${status.index}" value="${fitxer.signAlgorithm}"/>
      <param name="${Constants.APPLET_SIGN_MODE}_${status.index}" value="${fitxer.signMode}"/>
      <c:if test="${not empty fitxer.signBoxRectangle}">
      <param name="${Constants.APPLET_SIGN_BOX_RECTANGLE}_${status.index}" value="${fitxer.signBoxRectangle}"/>
      </c:if>
      </c:forEach>
      <c:if test="${not empty config.parametersToRead}">
      <param name="${Constants.APPLET_PARAMETERS_TO_READ}_${status.index}" value="${config.parametersToRead}"/>
      </c:if>
      <param name="${Constants.APPLET_LANGUAGE_UI}" value="${config.languageUI}"/>
      <c:if test="${not empty config.redirect}">
      <param name="${Constants.APPLET_REDIRECT}" value="${AppUrl}${config.redirect}"/>
      </c:if>
      <param name="${Constants.APPLET_CERTIFICATE_FILTER}" value="${fn:escapeXml(pfi:encodeHTML(config.filtreCertificats))}"/>
      <c:if test="${not empty config.policyIdentifier}">
      <param name="${Constants.APPLET_POLICYIDENTIFIER}" value="${config.policyIdentifier}"/>
      <param name="${Constants.APPLET_POLICYIDENTIFIERHASH}" value="${config.policyIdentifierHash}"/>
      <param name="${Constants.APPLET_POLICYIDENTIFIERHASHALGORITHM}" value="${config.policyIdentifierHashAlgorithm}"/>
      <c:if test="${not empty config.policyUrlDocument}">
      <param name="${Constants.APPLET_POLICYURLDOCUMENT}" value="${config.policyUrlDocument}"/>
      </c:if>
      </c:if>
      <c:if test="${not empty config.signerClass}">
      <param name="${Constants.APPLET_SIGNERCLASS}" value="${config.signerClass}"/>
      </c:if>
      <param name="${Constants.APPLET_ISJNLP}" value="true"/>

    </applet-desc>
</jnlp>
<%
 
   response.setContentType("application/x-java-jnlp-file");
   response.setHeader("Content-Disposition", "filename=\"Firma.jnlp\"");

%>