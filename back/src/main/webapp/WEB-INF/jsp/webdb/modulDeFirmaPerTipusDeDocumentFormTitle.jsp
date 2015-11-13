<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty modulDeFirmaPerTipusDeDocumentForm.entityNameCode}">
    <fmt:message var="entityname" key="modulDeFirmaPerTipusDeDocument.modulDeFirmaPerTipusDeDocument"/>
  </c:if>
  <c:if test="${not empty modulDeFirmaPerTipusDeDocumentForm.entityNameCode}">
    <fmt:message var="entityname" key="${modulDeFirmaPerTipusDeDocumentForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty modulDeFirmaPerTipusDeDocumentForm.titleCode}">
    <fmt:message key="${modulDeFirmaPerTipusDeDocumentForm.titleCode}" >
      <fmt:param value="${modulDeFirmaPerTipusDeDocumentForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty modulDeFirmaPerTipusDeDocumentForm.titleCode}">
    <c:set var="keytitle" value="${modulDeFirmaPerTipusDeDocumentForm.nou?'genapp.createtitle':(modulDeFirmaPerTipusDeDocumentForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty modulDeFirmaPerTipusDeDocumentForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(modulDeFirmaPerTipusDeDocumentForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(modulDeFirmaPerTipusDeDocumentForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${modulDeFirmaPerTipusDeDocumentForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>