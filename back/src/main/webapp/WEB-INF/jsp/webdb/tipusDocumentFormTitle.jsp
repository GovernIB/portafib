<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tipusDocumentForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tipusDocumentForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tipusDocumentForm.titleCode}">
    <fmt:message key="${tipusDocumentForm.titleCode}" >
      <fmt:param value="${tipusDocumentForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tipusDocumentForm.entityNameCode}">
      <fmt:message var="entityname" key="tipusDocument.tipusDocument"/>
    </c:if>
    <c:if test="${not empty tipusDocumentForm.entityNameCode}">
      <fmt:message var="entityname" key="${tipusDocumentForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tipusDocumentForm.nou?'genapp.createtitle':(tipusDocumentForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tipusDocumentForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tipusDocumentForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tipusDocumentForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tipusDocumentForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>