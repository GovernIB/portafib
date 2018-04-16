<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(pluginFirmaWebPerUsuariAplicacioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(pluginFirmaWebPerUsuariAplicacioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty pluginFirmaWebPerUsuariAplicacioForm.titleCode}">
    <fmt:message key="${pluginFirmaWebPerUsuariAplicacioForm.titleCode}" >
      <fmt:param value="${pluginFirmaWebPerUsuariAplicacioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty pluginFirmaWebPerUsuariAplicacioForm.entityNameCode}">
      <fmt:message var="entityname" key="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebPerUsuariAplicacio"/>
    </c:if>
    <c:if test="${not empty pluginFirmaWebPerUsuariAplicacioForm.entityNameCode}">
      <fmt:message var="entityname" key="${pluginFirmaWebPerUsuariAplicacioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${pluginFirmaWebPerUsuariAplicacioForm.nou?'genapp.createtitle':(pluginFirmaWebPerUsuariAplicacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty pluginFirmaWebPerUsuariAplicacioForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(pluginFirmaWebPerUsuariAplicacioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(pluginFirmaWebPerUsuariAplicacioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${pluginFirmaWebPerUsuariAplicacioForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>