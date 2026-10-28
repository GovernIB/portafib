<%@page import="es.caib.portafib.back.utils.IsAccessibleFromMoreThanOneModule"%>
<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<div>
<br/>
<div style="text-align:center;">
<img src="<c:url value="/img/app-logo.png"/>"  alt="PortaFIB" />

<br/>
<br/>

PAGINA PUBLICA <br/>

This page is generated automatically. Please edit.

<br/>
<br/>
<div>
<a href="https://governdigital.caib.es/" target="_blank">
<img src="<c:url value="/img/fundaciobit.png"/>"  alt="IBDigital - Govern Digital" />
</a>
</div>
<br/>
</div>
 
</div>

<br/>

LOGIN ANONIM <br/>
Locale = <%=LocaleContextHolder.getLocale() %> <br/>
lang = ${lang} <br/>
<br/>

<c:if test="${pfi:isDesenvolupament()}">
Only in Development Mode<br/>

</c:if>
