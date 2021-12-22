<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(blocDeFirmesForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(blocDeFirmesForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty blocDeFirmesForm.titleCode}">
    <fmt:message key="${blocDeFirmesForm.titleCode}" >
      <fmt:param value="${blocDeFirmesForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty blocDeFirmesForm.entityNameCode}">
      <fmt:message var="entityname" key="blocDeFirmes.blocDeFirmes"/>
    </c:if>
    <c:if test="${not empty blocDeFirmesForm.entityNameCode}">
      <fmt:message var="entityname" key="${blocDeFirmesForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${blocDeFirmesForm.nou?'genapp.createtitle':(blocDeFirmesForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty blocDeFirmesForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(blocDeFirmesForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(blocDeFirmesForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${blocDeFirmesForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>