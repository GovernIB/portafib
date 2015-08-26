<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty peticioDeFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="peticioDeFirma.peticioDeFirma"/>
  </c:if>
  <c:if test="${not empty peticioDeFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="${peticioDeFirmaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty peticioDeFirmaForm.titleCode}">
    <fmt:message key="${peticioDeFirmaForm.titleCode}" >
      <fmt:param value="${peticioDeFirmaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty peticioDeFirmaForm.titleCode}">
    <c:set var="keytitle" value="${peticioDeFirmaForm.nou?'genapp.createtitle':(peticioDeFirmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty peticioDeFirmaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(peticioDeFirmaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(peticioDeFirmaForm.subTitleCode, '=')}"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${peticioDeFirmaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>