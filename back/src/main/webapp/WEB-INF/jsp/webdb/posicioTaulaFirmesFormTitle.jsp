<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty posicioTaulaFirmesForm.entityNameCode}">
    <fmt:message var="entityname" key="posicioTaulaFirmes.posicioTaulaFirmes"/>
  </c:if>
  <c:if test="${not empty posicioTaulaFirmesForm.entityNameCode}">
    <fmt:message var="entityname" key="${posicioTaulaFirmesForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty posicioTaulaFirmesForm.titleCode}">
    <fmt:message key="${posicioTaulaFirmesForm.titleCode}" >
      <fmt:param value="${posicioTaulaFirmesForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty posicioTaulaFirmesForm.titleCode}">
    <c:set var="keytitle" value="${posicioTaulaFirmesForm.nou?'genapp.createtitle':(posicioTaulaFirmesForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty posicioTaulaFirmesForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${posicioTaulaFirmesForm.subTitleCode}" /></h5>
  </c:if>
</div>