<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%--
<un:useConstants var="Constants" className="org.fundaciobit.plugins.signatureweb.exemple.utils.Constants" />
 --%>
 <un:useConstants var="StatusFirma" className="org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureStatus" />
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/css/bootstrap-responsive.css"/>" rel="stylesheet">
<link href="<c:url value="/css/bootstrap-fileupload.css"/>" rel="stylesheet">

<script src="<c:url value="/js/jquery.js"/>"></script>
</head>
<body>

<%@ include file="/WEB-INF/views/missatges.jsp"%>
