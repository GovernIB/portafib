<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty blocDeFirmesForm.entityNameCode}">
    <fmt:message var="entityname" key="blocDeFirmes.blocDeFirmes"/>
  </c:if>
  <c:if test="${not empty blocDeFirmesForm.entityNameCode}">
    <fmt:message var="entityname" key="${blocDeFirmesForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty blocDeFirmesForm.titleCode}">
    <fmt:message key="${blocDeFirmesForm.titleCode}" >
      <fmt:param value="${blocDeFirmesForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty blocDeFirmesForm.titleCode}">
    <c:set var="keytitle" value="${blocDeFirmesForm.nou?'genapp.createtitle':(blocDeFirmesForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty blocDeFirmesForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(blocDeFirmesForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(blocDeFirmesForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${blocDeFirmesForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>