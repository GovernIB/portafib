<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty propietatGlobalForm.entityNameCode}">
    <fmt:message var="entityname" key="propietatGlobal.propietatGlobal"/>
  </c:if>
  <c:if test="${not empty propietatGlobalForm.entityNameCode}">
    <fmt:message var="entityname" key="${propietatGlobalForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty propietatGlobalForm.titleCode}">
    <fmt:message key="${propietatGlobalForm.titleCode}" >
      <fmt:param value="${propietatGlobalForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty propietatGlobalForm.titleCode}">
    <c:set var="keytitle" value="${propietatGlobalForm.nou?'genapp.createtitle':(propietatGlobalForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty propietatGlobalForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(propietatGlobalForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(propietatGlobalForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${propietatGlobalForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>