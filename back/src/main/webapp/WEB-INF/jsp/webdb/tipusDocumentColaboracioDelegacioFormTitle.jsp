<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty tipusDocumentColaboracioDelegacioForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusDocumentColaboracioDelegacio.tipusDocumentColaboracioDelegacio"/>
  </c:if>
  <c:if test="${not empty tipusDocumentColaboracioDelegacioForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusDocumentColaboracioDelegacioForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty tipusDocumentColaboracioDelegacioForm.titleCode}">
    <fmt:message key="${tipusDocumentColaboracioDelegacioForm.titleCode}" >
      <fmt:param value="${tipusDocumentColaboracioDelegacioForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty tipusDocumentColaboracioDelegacioForm.titleCode}">
    <c:set var="keytitle" value="${tipusDocumentColaboracioDelegacioForm.nou?'genapp.createtitle':(tipusDocumentColaboracioDelegacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty tipusDocumentColaboracioDelegacioForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${tipusDocumentColaboracioDelegacioForm.subTitleCode}" /></h5>
  </c:if>
</div>