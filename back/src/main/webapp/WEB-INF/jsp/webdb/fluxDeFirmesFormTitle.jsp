<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty fluxDeFirmesForm.entityNameCode}">
    <fmt:message var="entityname" key="fluxDeFirmes.fluxDeFirmes"/>
  </c:if>
  <c:if test="${not empty fluxDeFirmesForm.entityNameCode}">
    <fmt:message var="entityname" key="${fluxDeFirmesForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty fluxDeFirmesForm.titleCode}">
    <fmt:message key="${fluxDeFirmesForm.titleCode}" >
      <fmt:param value="${fluxDeFirmesForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty fluxDeFirmesForm.titleCode}">
    <c:set var="keytitle" value="${fluxDeFirmesForm.nou?'genapp.createtitle':(fluxDeFirmesForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty fluxDeFirmesForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${fluxDeFirmesForm.subTitleCode}" /></h5>
  </c:if>
</div>