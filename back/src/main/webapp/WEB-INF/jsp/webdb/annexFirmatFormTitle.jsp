<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty annexFirmatForm.entityNameCode}">
    <fmt:message var="entityname" key="annexFirmat.annexFirmat"/>
  </c:if>
  <c:if test="${not empty annexFirmatForm.entityNameCode}">
    <fmt:message var="entityname" key="${annexFirmatForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty annexFirmatForm.titleCode}">
    <fmt:message key="${annexFirmatForm.titleCode}" >
      <fmt:param value="${annexFirmatForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty annexFirmatForm.titleCode}">
    <c:set var="keytitle" value="${annexFirmatForm.nou?'genapp.createtitle':(annexFirmatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty annexFirmatForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(annexFirmatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(annexFirmatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${annexFirmatForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>