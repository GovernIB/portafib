<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty permisUsuariPlantillaForm.entityNameCode}">
    <fmt:message var="entityname" key="permisUsuariPlantilla.permisUsuariPlantilla"/>
  </c:if>
  <c:if test="${not empty permisUsuariPlantillaForm.entityNameCode}">
    <fmt:message var="entityname" key="${permisUsuariPlantillaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty permisUsuariPlantillaForm.titleCode}">
    <fmt:message key="${permisUsuariPlantillaForm.titleCode}" >
      <fmt:param value="${permisUsuariPlantillaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty permisUsuariPlantillaForm.titleCode}">
    <c:set var="keytitle" value="${permisUsuariPlantillaForm.nou?'genapp.createtitle':(permisUsuariPlantillaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty permisUsuariPlantillaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(permisUsuariPlantillaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(permisUsuariPlantillaForm.subTitleCode, '=')}"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${permisUsuariPlantillaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>