<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty permisGrupPlantillaForm.entityNameCode}">
    <fmt:message var="entityname" key="permisGrupPlantilla.permisGrupPlantilla"/>
  </c:if>
  <c:if test="${not empty permisGrupPlantillaForm.entityNameCode}">
    <fmt:message var="entityname" key="${permisGrupPlantillaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty permisGrupPlantillaForm.titleCode}">
    <fmt:message key="${permisGrupPlantillaForm.titleCode}" >
      <fmt:param value="${permisGrupPlantillaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty permisGrupPlantillaForm.titleCode}">
    <c:set var="keytitle" value="${permisGrupPlantillaForm.nou?'genapp.createtitle':(permisGrupPlantillaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty permisGrupPlantillaForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${permisGrupPlantillaForm.subTitleCode}" /></h5>
  </c:if>
</div>