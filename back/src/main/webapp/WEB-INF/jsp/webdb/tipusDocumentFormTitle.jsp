<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty tipusDocumentForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusDocument.tipusDocument"/>
  </c:if>
  <c:if test="${not empty tipusDocumentForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusDocumentForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty tipusDocumentForm.titleCode}">
    <fmt:message key="${tipusDocumentForm.titleCode}" >
      <fmt:param value="${tipusDocumentForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty tipusDocumentForm.titleCode}">
    <c:set var="keytitle" value="${tipusDocumentForm.nou?'genapp.createtitle':(tipusDocumentForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty tipusDocumentForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${tipusDocumentForm.subTitleCode}" /></h5>
  </c:if>
</div>