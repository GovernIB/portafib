<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty algorismeDeFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="algorismeDeFirma.algorismeDeFirma"/>
  </c:if>
  <c:if test="${not empty algorismeDeFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="${algorismeDeFirmaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty algorismeDeFirmaForm.titleCode}">
    <fmt:message key="${algorismeDeFirmaForm.titleCode}" >
      <fmt:param value="${algorismeDeFirmaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty algorismeDeFirmaForm.titleCode}">
    <c:set var="keytitle" value="${algorismeDeFirmaForm.nou?'genapp.createtitle':(algorismeDeFirmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty algorismeDeFirmaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(algorismeDeFirmaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(algorismeDeFirmaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${algorismeDeFirmaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>