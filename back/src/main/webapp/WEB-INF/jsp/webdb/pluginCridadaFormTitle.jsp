<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(pluginCridadaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(pluginCridadaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty pluginCridadaForm.titleCode}">
    <fmt:message key="${pluginCridadaForm.titleCode}" >
      <fmt:param value="${pluginCridadaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty pluginCridadaForm.entityNameCode}">
      <fmt:message var="entityname" key="pluginCridada.pluginCridada"/>
    </c:if>
    <c:if test="${not empty pluginCridadaForm.entityNameCode}">
      <fmt:message var="entityname" key="${pluginCridadaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${pluginCridadaForm.nou?'genapp.createtitle':(pluginCridadaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty pluginCridadaForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(pluginCridadaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(pluginCridadaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${pluginCridadaForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>