<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(codiBarresForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(codiBarresForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty codiBarresForm.titleCode}">
    <fmt:message key="${codiBarresForm.titleCode}" >
      <fmt:param value="${codiBarresForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty codiBarresForm.entityNameCode}">
      <fmt:message var="entityname" key="codiBarres.codiBarres"/>
    </c:if>
    <c:if test="${not empty codiBarresForm.entityNameCode}">
      <fmt:message var="entityname" key="${codiBarresForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${codiBarresForm.nou?'genapp.createtitle':(codiBarresForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty codiBarresForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(codiBarresForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(codiBarresForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${codiBarresForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>