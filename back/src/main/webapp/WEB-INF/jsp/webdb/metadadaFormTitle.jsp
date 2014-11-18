<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty metadadaForm.entityNameCode}">
    <fmt:message var="entityname" key="metadada.metadada"/>
  </c:if>
  <c:if test="${not empty metadadaForm.entityNameCode}">
    <fmt:message var="entityname" key="${metadadaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty metadadaForm.titleCode}">
    <fmt:message key="${metadadaForm.titleCode}" >
      <fmt:param value="${metadadaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty metadadaForm.titleCode}">
    <c:set var="keytitle" value="${metadadaForm.nou?'genapp.createtitle':(metadadaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty metadadaForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${metadadaForm.subTitleCode}" /></h5>
  </c:if>
</div>