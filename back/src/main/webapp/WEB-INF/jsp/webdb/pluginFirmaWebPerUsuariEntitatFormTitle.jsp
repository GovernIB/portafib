<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(pluginFirmaWebPerUsuariEntitatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(pluginFirmaWebPerUsuariEntitatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty pluginFirmaWebPerUsuariEntitatForm.titleCode}">
    <fmt:message key="${pluginFirmaWebPerUsuariEntitatForm.titleCode}" >
      <fmt:param value="${pluginFirmaWebPerUsuariEntitatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty pluginFirmaWebPerUsuariEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsuariEntitat"/>
    </c:if>
    <c:if test="${not empty pluginFirmaWebPerUsuariEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="${pluginFirmaWebPerUsuariEntitatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${pluginFirmaWebPerUsuariEntitatForm.nou?'genapp.createtitle':(pluginFirmaWebPerUsuariEntitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty pluginFirmaWebPerUsuariEntitatForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(pluginFirmaWebPerUsuariEntitatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(pluginFirmaWebPerUsuariEntitatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${pluginFirmaWebPerUsuariEntitatForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>