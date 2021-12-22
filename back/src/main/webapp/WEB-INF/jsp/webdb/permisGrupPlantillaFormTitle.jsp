<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(permisGrupPlantillaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(permisGrupPlantillaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty permisGrupPlantillaForm.titleCode}">
    <fmt:message key="${permisGrupPlantillaForm.titleCode}" >
      <fmt:param value="${permisGrupPlantillaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty permisGrupPlantillaForm.entityNameCode}">
      <fmt:message var="entityname" key="permisGrupPlantilla.permisGrupPlantilla"/>
    </c:if>
    <c:if test="${not empty permisGrupPlantillaForm.entityNameCode}">
      <fmt:message var="entityname" key="${permisGrupPlantillaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${permisGrupPlantillaForm.nou?'genapp.createtitle':(permisGrupPlantillaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty permisGrupPlantillaForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(permisGrupPlantillaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(permisGrupPlantillaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${permisGrupPlantillaForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>