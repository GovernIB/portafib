<%@page import="es.caib.portafib.utils.Configuracio"%>
<%@include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="Constants" className="es.caib.portafib.utils.Constants"/>
<br>
<br>
<br>
<%
  String appUrl = "";
  String appletUrl = "";
  if ("https".equals(request.getScheme())) {
    appUrl = Configuracio.getAppUrl();
    appletUrl = appUrl + "/applet/";  
  }
  request.getSession().setAttribute("AppUrl", appUrl);
  request.getSession().setAttribute("AppletUrl", appletUrl );

%>
<script src="<c:url value="/js/deployJava.jsp"/>"></script>
<!--<script src="http://java.com/js/deployJava.js"></script>-->
<center>
<script>
  var attributes =
  {
    id: 'miniApplet',
    codebase:'<c:url value="/applet" />', // directory with the jar
    code:'es.caib.portafib.applet.SignApplet',
    archive:'${AppletUrl}portafib-applet-signed-<%=es.caib.portafib.versio.Versio.VERSIO%>.jar,${AppletUrl}miniapplet-full.jar',
    name: 'Applet de Firma de PortaFIB (Basat en el MiniApplet @firma)',
    type: 'application/x-java-applet',
    width: 475,
    height: 300,
    boxbgcolor: '#ffffff'
  };
  var parameters = {
      java_arguments:'-Xmx1024m',
      separate_jvm:'true',
      <c:forEach  var="fitxer"  items="${fitxers}" varStatus="status">
      ${Constants.APPLET_SOURCE}_${status.index}:'<c:url value="${AppUrl}${fitxer.source}" />',
      ${Constants.APPLET_DESTINATION}_${status.index}:'<c:url value="${AppUrl}${fitxer.destination}" />', 
      ${Constants.APPLET_IDNAME}_${status.index}:'${pfi:escapeJavaScript(fitxer.idname)}',
      ${Constants.APPLET_LOCATION_SIGN_TABLE}_${status.index}:'${fitxer.locationSignTable}',
      ${Constants.APPLET_REASON}_${status.index}:'${pfi:escapeJavaScript(fitxer.reason)}',
      ${Constants.APPLET_SIGN_NUMBER}_${status.index}:'${fitxer.signNumber}',
      ${Constants.APPLET_LANGUAGE_SIGN}_${status.index}:'${fitxer.languageSign}',
      ${Constants.APPLET_SIGN_TYPE}_${status.index}:'${fitxer.signType}',
      ${Constants.APPLET_SIGN_ALGORITHM}_${status.index}:'${fitxer.signAlgorithm}',
      ${Constants.APPLET_SIGN_MODE}_${status.index}:'${fitxer.signMode}',
      <c:if test="${not empty fitxer.signBoxRectangle}">
      ${Constants.APPLET_SIGN_BOX_RECTANGLE}_${status.index}:'${fitxer.signBoxRectangle}',
      </c:if>
	  </c:forEach>
      <c:if test="${not empty config.parametersToRead}">
      ${Constants.APPLET_PARAMETERS_TO_READ}_${status.index}:'${config.parametersToRead}',
      </c:if>
      ${Constants.APPLET_LANGUAGE_UI}:'${config.languageUI}',
      <c:url var="_redirect" value="${config.redirect}" />
      ${Constants.APPLET_REDIRECT}:'${pfi:escapeJavaScript(_redirect)}',
      ${Constants.APPLET_CERTIFICATE_FILTER}:'${pfi:escapeJavaScript(pfi:encodeHTML(config.filtreCertificats))}',
      <c:if test="${not empty config.policyIdentifier}">
      ${Constants.APPLET_POLICYIDENTIFIER}:'${pfi:escapeJavaScript(config.policyIdentifier)}',
      ${Constants.APPLET_POLICYIDENTIFIERHASH}:'${pfi:escapeJavaScript(config.policyIdentifierHash)}',
      ${Constants.APPLET_POLICYIDENTIFIERHASHALGORITHM}:'${pfi:escapeJavaScript(config.policyIdentifierHashAlgorithm)}',
      <c:if test="${not empty config.policyUrlDocument}">
      ${Constants.APPLET_POLICYURLDOCUMENT}:'${pfi:escapeJavaScript(config.policyUrlDocument)}',
      </c:if>
      </c:if>
      ${Constants.APPLET_SIGNERCLASS}:'${config.signerClass}'
	};
  var version = '1.6';

  deployJava.runApplet(attributes, parameters, version);
</script> 

</center>
