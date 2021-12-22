<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(grupEntitatUsuariEntitatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(grupEntitatUsuariEntitatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty grupEntitatUsuariEntitatForm.titleCode}">
    <fmt:message key="${grupEntitatUsuariEntitatForm.titleCode}" >
      <fmt:param value="${grupEntitatUsuariEntitatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty grupEntitatUsuariEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="grupEntitatUsuariEntitat.grupEntitatUsuariEntitat"/>
    </c:if>
    <c:if test="${not empty grupEntitatUsuariEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="${grupEntitatUsuariEntitatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${grupEntitatUsuariEntitatForm.nou?'genapp.createtitle':(grupEntitatUsuariEntitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty grupEntitatUsuariEntitatForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(grupEntitatUsuariEntitatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(grupEntitatUsuariEntitatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${grupEntitatUsuariEntitatForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>