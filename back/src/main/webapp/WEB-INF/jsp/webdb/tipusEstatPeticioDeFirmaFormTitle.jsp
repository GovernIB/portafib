<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty tipusEstatPeticioDeFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirma"/>
  </c:if>
  <c:if test="${not empty tipusEstatPeticioDeFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusEstatPeticioDeFirmaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty tipusEstatPeticioDeFirmaForm.titleCode}">
    <fmt:message key="${tipusEstatPeticioDeFirmaForm.titleCode}" >
      <fmt:param value="${tipusEstatPeticioDeFirmaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty tipusEstatPeticioDeFirmaForm.titleCode}">
    <c:set var="keytitle" value="${tipusEstatPeticioDeFirmaForm.nou?'genapp.createtitle':(tipusEstatPeticioDeFirmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty tipusEstatPeticioDeFirmaForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${tipusEstatPeticioDeFirmaForm.subTitleCode}" /></h5>
  </c:if>
</div>