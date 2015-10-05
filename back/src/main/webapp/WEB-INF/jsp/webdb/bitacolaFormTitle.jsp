<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty bitacolaForm.entityNameCode}">
    <fmt:message var="entityname" key="bitacola.bitacola"/>
  </c:if>
  <c:if test="${not empty bitacolaForm.entityNameCode}">
    <fmt:message var="entityname" key="${bitacolaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty bitacolaForm.titleCode}">
    <fmt:message key="${bitacolaForm.titleCode}" >
      <fmt:param value="${bitacolaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty bitacolaForm.titleCode}">
    <c:set var="keytitle" value="${bitacolaForm.nou?'genapp.createtitle':(bitacolaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty bitacolaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(bitacolaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(bitacolaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${bitacolaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>