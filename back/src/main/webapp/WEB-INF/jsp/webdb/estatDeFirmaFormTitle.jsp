<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty estatDeFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="estatDeFirma.estatDeFirma"/>
  </c:if>
  <c:if test="${not empty estatDeFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="${estatDeFirmaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty estatDeFirmaForm.titleCode}">
    <fmt:message key="${estatDeFirmaForm.titleCode}" >
      <fmt:param value="${estatDeFirmaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty estatDeFirmaForm.titleCode}">
    <c:set var="keytitle" value="${estatDeFirmaForm.nou?'genapp.createtitle':(estatDeFirmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty estatDeFirmaForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${estatDeFirmaForm.subTitleCode}" /></h5>
  </c:if>
</div>