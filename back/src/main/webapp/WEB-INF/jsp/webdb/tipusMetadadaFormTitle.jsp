<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty tipusMetadadaForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusMetadada.tipusMetadada"/>
  </c:if>
  <c:if test="${not empty tipusMetadadaForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusMetadadaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty tipusMetadadaForm.titleCode}">
    <fmt:message key="${tipusMetadadaForm.titleCode}" >
      <fmt:param value="${tipusMetadadaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty tipusMetadadaForm.titleCode}">
    <c:set var="keytitle" value="${tipusMetadadaForm.nou?'genapp.createtitle':(tipusMetadadaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty tipusMetadadaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tipusMetadadaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tipusMetadadaForm.subTitleCode, '=')}"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tipusMetadadaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>