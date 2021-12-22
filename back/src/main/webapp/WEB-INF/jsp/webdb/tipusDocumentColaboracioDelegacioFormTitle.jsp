<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tipusDocumentColaboracioDelegacioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tipusDocumentColaboracioDelegacioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tipusDocumentColaboracioDelegacioForm.titleCode}">
    <fmt:message key="${tipusDocumentColaboracioDelegacioForm.titleCode}" >
      <fmt:param value="${tipusDocumentColaboracioDelegacioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tipusDocumentColaboracioDelegacioForm.entityNameCode}">
      <fmt:message var="entityname" key="tipusDocumentColaboracioDelegacio.tipusDocumentColaboracioDelegacio"/>
    </c:if>
    <c:if test="${not empty tipusDocumentColaboracioDelegacioForm.entityNameCode}">
      <fmt:message var="entityname" key="${tipusDocumentColaboracioDelegacioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tipusDocumentColaboracioDelegacioForm.nou?'genapp.createtitle':(tipusDocumentColaboracioDelegacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tipusDocumentColaboracioDelegacioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tipusDocumentColaboracioDelegacioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tipusDocumentColaboracioDelegacioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tipusDocumentColaboracioDelegacioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>