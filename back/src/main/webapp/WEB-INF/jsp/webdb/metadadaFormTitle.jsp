<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(metadadaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(metadadaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty metadadaForm.titleCode}">
    <fmt:message key="${metadadaForm.titleCode}" >
      <fmt:param value="${metadadaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty metadadaForm.entityNameCode}">
      <fmt:message var="entityname" key="metadada.metadada"/>
    </c:if>
    <c:if test="${not empty metadadaForm.entityNameCode}">
      <fmt:message var="entityname" key="${metadadaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${metadadaForm.nou?'genapp.createtitle':(metadadaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty metadadaForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(metadadaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(metadadaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${metadadaForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>