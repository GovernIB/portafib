<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(usuariAplicacioConfiguracioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(usuariAplicacioConfiguracioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty usuariAplicacioConfiguracioForm.titleCode}">
    <fmt:message key="${usuariAplicacioConfiguracioForm.titleCode}" >
      <fmt:param value="${usuariAplicacioConfiguracioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty usuariAplicacioConfiguracioForm.entityNameCode}">
      <fmt:message var="entityname" key="usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"/>
    </c:if>
    <c:if test="${not empty usuariAplicacioConfiguracioForm.entityNameCode}">
      <fmt:message var="entityname" key="${usuariAplicacioConfiguracioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${usuariAplicacioConfiguracioForm.nou?'genapp.createtitle':(usuariAplicacioConfiguracioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty usuariAplicacioConfiguracioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(usuariAplicacioConfiguracioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(usuariAplicacioConfiguracioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${usuariAplicacioConfiguracioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>