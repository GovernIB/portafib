<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(usuariPersonaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(usuariPersonaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty usuariPersonaForm.titleCode}">
    <fmt:message key="${usuariPersonaForm.titleCode}" >
      <fmt:param value="${usuariPersonaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty usuariPersonaForm.entityNameCode}">
      <fmt:message var="entityname" key="usuariPersona.usuariPersona"/>
    </c:if>
    <c:if test="${not empty usuariPersonaForm.entityNameCode}">
      <fmt:message var="entityname" key="${usuariPersonaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${usuariPersonaForm.nou?'genapp.createtitle':(usuariPersonaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty usuariPersonaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(usuariPersonaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(usuariPersonaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${usuariPersonaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>