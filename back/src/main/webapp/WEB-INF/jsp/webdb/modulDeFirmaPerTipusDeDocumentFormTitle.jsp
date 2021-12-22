<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(modulDeFirmaPerTipusDeDocumentForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(modulDeFirmaPerTipusDeDocumentForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty modulDeFirmaPerTipusDeDocumentForm.titleCode}">
    <fmt:message key="${modulDeFirmaPerTipusDeDocumentForm.titleCode}" >
      <fmt:param value="${modulDeFirmaPerTipusDeDocumentForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty modulDeFirmaPerTipusDeDocumentForm.entityNameCode}">
      <fmt:message var="entityname" key="modulDeFirmaPerTipusDeDocument.modulDeFirmaPerTipusDeDocument"/>
    </c:if>
    <c:if test="${not empty modulDeFirmaPerTipusDeDocumentForm.entityNameCode}">
      <fmt:message var="entityname" key="${modulDeFirmaPerTipusDeDocumentForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${modulDeFirmaPerTipusDeDocumentForm.nou?'genapp.createtitle':(modulDeFirmaPerTipusDeDocumentForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty modulDeFirmaPerTipusDeDocumentForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(modulDeFirmaPerTipusDeDocumentForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(modulDeFirmaPerTipusDeDocumentForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${modulDeFirmaPerTipusDeDocumentForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>