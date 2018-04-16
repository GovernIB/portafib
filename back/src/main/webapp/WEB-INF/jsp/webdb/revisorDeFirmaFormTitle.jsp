<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(revisorDeFirmaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(revisorDeFirmaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty revisorDeFirmaForm.titleCode}">
    <fmt:message key="${revisorDeFirmaForm.titleCode}" >
      <fmt:param value="${revisorDeFirmaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty revisorDeFirmaForm.entityNameCode}">
      <fmt:message var="entityname" key="revisorDeFirma.revisorDeFirma"/>
    </c:if>
    <c:if test="${not empty revisorDeFirmaForm.entityNameCode}">
      <fmt:message var="entityname" key="${revisorDeFirmaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${revisorDeFirmaForm.nou?'genapp.createtitle':(revisorDeFirmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty revisorDeFirmaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(revisorDeFirmaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(revisorDeFirmaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${revisorDeFirmaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>