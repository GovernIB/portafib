<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty tipusEstatDeFirmaFinalForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinal"/>
  </c:if>
  <c:if test="${not empty tipusEstatDeFirmaFinalForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusEstatDeFirmaFinalForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty tipusEstatDeFirmaFinalForm.titleCode}">
    <fmt:message key="${tipusEstatDeFirmaFinalForm.titleCode}" >
      <fmt:param value="${tipusEstatDeFirmaFinalForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty tipusEstatDeFirmaFinalForm.titleCode}">
    <c:set var="keytitle" value="${tipusEstatDeFirmaFinalForm.nou?'genapp.createtitle':(tipusEstatDeFirmaFinalForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty tipusEstatDeFirmaFinalForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${tipusEstatDeFirmaFinalForm.subTitleCode}" /></h5>
  </c:if>
</div>