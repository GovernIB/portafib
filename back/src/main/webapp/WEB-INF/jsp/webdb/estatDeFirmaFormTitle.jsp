<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(estatDeFirmaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(estatDeFirmaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty estatDeFirmaForm.titleCode}">
    <fmt:message key="${estatDeFirmaForm.titleCode}" >
      <fmt:param value="${estatDeFirmaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty estatDeFirmaForm.entityNameCode}">
      <fmt:message var="entityname" key="estatDeFirma.estatDeFirma"/>
    </c:if>
    <c:if test="${not empty estatDeFirmaForm.entityNameCode}">
      <fmt:message var="entityname" key="${estatDeFirmaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${estatDeFirmaForm.nou?'genapp.createtitle':(estatDeFirmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty estatDeFirmaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(estatDeFirmaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(estatDeFirmaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${estatDeFirmaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>