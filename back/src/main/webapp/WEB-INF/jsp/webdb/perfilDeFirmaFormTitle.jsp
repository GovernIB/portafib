<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<span class="llistaformularititol">
 <c:choose>
  <c:when test="${fn:startsWith(perfilDeFirmaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(perfilDeFirmaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty perfilDeFirmaForm.titleCode}">
    <fmt:message key="${perfilDeFirmaForm.titleCode}" >
      <fmt:param value="${perfilDeFirmaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty perfilDeFirmaForm.entityNameCode}">
      <fmt:message var="entityname" key="perfilDeFirma.perfilDeFirma"/>
    </c:if>
    <c:if test="${not empty perfilDeFirmaForm.entityNameCode}">
      <fmt:message var="entityname" key="${perfilDeFirmaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${perfilDeFirmaForm.nou?'genapp.createtitle':(perfilDeFirmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></span>
  <c:if test="${not empty perfilDeFirmaForm.subTitleCode}">
<span class="llistaformularisubtitol">
<c:set var="subtitleTranslated" value="${fn:startsWith(perfilDeFirmaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(perfilDeFirmaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${perfilDeFirmaForm.subTitleCode}" />
</c:if>
</span>
  </c:if>
</div>