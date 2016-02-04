<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(fluxDeFirmesForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(fluxDeFirmesForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty fluxDeFirmesForm.titleCode}">
    <fmt:message key="${fluxDeFirmesForm.titleCode}" >
      <fmt:param value="${fluxDeFirmesForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty fluxDeFirmesForm.entityNameCode}">
      <fmt:message var="entityname" key="fluxDeFirmes.fluxDeFirmes"/>
    </c:if>
    <c:if test="${not empty fluxDeFirmesForm.entityNameCode}">
      <fmt:message var="entityname" key="${fluxDeFirmesForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${fluxDeFirmesForm.nou?'genapp.createtitle':(fluxDeFirmesForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty fluxDeFirmesForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(fluxDeFirmesForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(fluxDeFirmesForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${fluxDeFirmesForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>