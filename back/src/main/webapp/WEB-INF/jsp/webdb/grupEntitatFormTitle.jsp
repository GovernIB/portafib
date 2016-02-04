<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(grupEntitatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(grupEntitatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty grupEntitatForm.titleCode}">
    <fmt:message key="${grupEntitatForm.titleCode}" >
      <fmt:param value="${grupEntitatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty grupEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="grupEntitat.grupEntitat"/>
    </c:if>
    <c:if test="${not empty grupEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="${grupEntitatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${grupEntitatForm.nou?'genapp.createtitle':(grupEntitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty grupEntitatForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(grupEntitatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(grupEntitatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${grupEntitatForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>