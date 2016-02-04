<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(tipusNotificacioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tipusNotificacioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tipusNotificacioForm.titleCode}">
    <fmt:message key="${tipusNotificacioForm.titleCode}" >
      <fmt:param value="${tipusNotificacioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tipusNotificacioForm.entityNameCode}">
      <fmt:message var="entityname" key="tipusNotificacio.tipusNotificacio"/>
    </c:if>
    <c:if test="${not empty tipusNotificacioForm.entityNameCode}">
      <fmt:message var="entityname" key="${tipusNotificacioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tipusNotificacioForm.nou?'genapp.createtitle':(tipusNotificacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty tipusNotificacioForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tipusNotificacioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tipusNotificacioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tipusNotificacioForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>