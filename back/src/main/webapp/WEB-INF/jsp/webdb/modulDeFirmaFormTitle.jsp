<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty modulDeFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="modulDeFirma.modulDeFirma"/>
  </c:if>
  <c:if test="${not empty modulDeFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="${modulDeFirmaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty modulDeFirmaForm.titleCode}">
    <fmt:message key="${modulDeFirmaForm.titleCode}" >
      <fmt:param value="${modulDeFirmaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty modulDeFirmaForm.titleCode}">
    <c:set var="keytitle" value="${modulDeFirmaForm.nou?'genapp.createtitle':(modulDeFirmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty modulDeFirmaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(modulDeFirmaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(modulDeFirmaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${modulDeFirmaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>