<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%><%
 String home= request.getParameter("RutaBase");
 if (home == null) {
   home = "http://localhost/portafib";
 } else {
   if (home.endsWith("/")) {
      home = home.substring(0, home.length() -1);
   }
 }
 
 //session.setAttribute("index.jsp", home + "/index.jsp");
 
 String signerClass = request.getParameter("signerClass");
 
 
%><HTML>
<TITLE>Applet de firma electrónica</TITLE>
<BODY>



<%
if (signerClass == null) {

%>

<center><H1>Seleccioni la Llibreria </H1></center>
<form>
<input type="radio" name="signerClass" value="es.caib.portafib.applet.signers.IBKeySigner" > IB-KEY <br/>
<input type="radio" name="signerClass" value="es.caib.portafib.applet.signers.AfirmaSigner" checked > @firma<br/>
<input type="submit" name="submit" value="OK">
</form>

<%
} else {
%>

<center><H1>Applet de firma digital</H1></center>

<script src="./deployJava.js"></script>

<center>
<script>
  var attributes =
  {
    id: 'SignApplet',
    codebase:'.', // directory with the jar
    code:'es.caib.portafib.applet.SignApplet',
    archive:'portafib-applet-1.0-signed.jar,miniapplet-full.jar',
    width: 475, height: 300,
    boxbgcolor: '#ffffff'
  };
  var parameters = {      
		    source_0:'<%=home%>/hola.pdf',
		    destination_0:'<%=home%>/receive.jsp', 
		    idname_0:'hola.pdf',
		    location_sign_table_0:'1',
		    reason_0:'Un motiu',
		    sign_number_0:'1',
		    languageSign_0:'ca',
	  
		    languageUI:'ca',
		    redirect:'<%=home%>/signat.jsp',
		    signatura_api_properties:'<%=home%>/signatura_api.properties',
		    signerClass:'<%=signerClass%>'
	      };
  var version = '1.6';

  deployJava.runApplet(attributes, parameters, version);
</script>

</center>

<%
};
%>


<%--


<object classid="clsid:CAFEEFAC-0015-0000-FFFF-ABCDEFFEDCBA"
    width="400" height="250" 
    align="baseline" 
    codebase="http://java.sun.com/update/1.5_0/jinstall-1_5_0_12-windows-i586.cab" >
    <param name="code" value="es.caib.portafib.applet.SignApplet">
    <param name="archive" value="portafib-applet-1.0.jar">
    <param name="source_0" value="<%=home%>/hola.pdf" />
    <param name="destination_0" value="<%=home%>/receive.jsp" />
	<param name="idname_0" value="hola.pdf" />
	<param name="location_sign_table_0" value="2" />
	<param name="reason_0" value="UN motiu" />
	<param name="sign_number_0" value="0" />
	<param name="language" value="ca" />
	<param name="redirect" value="<%=home%>/signat.jsp" />
	<param name="signatura_api_properties" value="<%=home%>/signatura_api.properties" />
    <comment>
        <embed  width="400" height="250" align="baseline" 
    	   code="es.caib.portafib.applet.SignApplet"
           archive="portafib-applet-1.0.jar"
		   source_0="<%=home%>/hola.pdf"
		   destination_0="<%=home%>/receive.jsp"
		   location_sign_table_0="2"
	       reason_0="UN motiu"	
		   idname_0="hola.pdf"
		   sign_number_0="0"
           redirect="<%=home%>/signat.jsp"
		   language="ca"
           signatura_api_properties="<%=home%>/signatura_api.properties"
           type="application/x-java-applet;version=1.5"
           pluginspage="http://java.sun.com/j2se/1.5_0/download.html"
           cache_option="No" />
           <noembed>
              No te suport per applets Java 2 SDK, Standard Edition v 1.5 ! !
           </noembed>
        </embed>
    </comment>
</object>

</center>
--%>
</BODY>
</HTML>