<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(plantillaFluxDeFirmesForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(plantillaFluxDeFirmesForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty plantillaFluxDeFirmesForm.titleCode}">
    <fmt:message key="${plantillaFluxDeFirmesForm.titleCode}" >
      <fmt:param value="${plantillaFluxDeFirmesForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty plantillaFluxDeFirmesForm.entityNameCode}">
      <fmt:message var="entityname" key="plantillaFluxDeFirmes.plantillaFluxDeFirmes"/>
    </c:if>
    <c:if test="${not empty plantillaFluxDeFirmesForm.entityNameCode}">
      <fmt:message var="entityname" key="${plantillaFluxDeFirmesForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${plantillaFluxDeFirmesForm.nou?'genapp.createtitle':(plantillaFluxDeFirmesForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty plantillaFluxDeFirmesForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(plantillaFluxDeFirmesForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(plantillaFluxDeFirmesForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${plantillaFluxDeFirmesForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>