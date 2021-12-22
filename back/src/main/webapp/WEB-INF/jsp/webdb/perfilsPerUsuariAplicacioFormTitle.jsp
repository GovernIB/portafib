<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(perfilsPerUsuariAplicacioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(perfilsPerUsuariAplicacioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty perfilsPerUsuariAplicacioForm.titleCode}">
    <fmt:message key="${perfilsPerUsuariAplicacioForm.titleCode}" >
      <fmt:param value="${perfilsPerUsuariAplicacioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty perfilsPerUsuariAplicacioForm.entityNameCode}">
      <fmt:message var="entityname" key="perfilsPerUsuariAplicacio.perfilsPerUsuariAplicacio"/>
    </c:if>
    <c:if test="${not empty perfilsPerUsuariAplicacioForm.entityNameCode}">
      <fmt:message var="entityname" key="${perfilsPerUsuariAplicacioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${perfilsPerUsuariAplicacioForm.nou?'genapp.createtitle':(perfilsPerUsuariAplicacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty perfilsPerUsuariAplicacioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(perfilsPerUsuariAplicacioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(perfilsPerUsuariAplicacioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${perfilsPerUsuariAplicacioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>