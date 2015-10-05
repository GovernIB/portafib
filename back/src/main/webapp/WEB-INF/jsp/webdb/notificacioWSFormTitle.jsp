<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty notificacioWSForm.entityNameCode}">
    <fmt:message var="entityname" key="notificacioWS.notificacioWS"/>
  </c:if>
  <c:if test="${not empty notificacioWSForm.entityNameCode}">
    <fmt:message var="entityname" key="${notificacioWSForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty notificacioWSForm.titleCode}">
    <fmt:message key="${notificacioWSForm.titleCode}" >
      <fmt:param value="${notificacioWSForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty notificacioWSForm.titleCode}">
    <c:set var="keytitle" value="${notificacioWSForm.nou?'genapp.createtitle':(notificacioWSForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty notificacioWSForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(notificacioWSForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(notificacioWSForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${notificacioWSForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>