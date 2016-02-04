<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(tipusEstatDeFirmaFinalForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tipusEstatDeFirmaFinalForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tipusEstatDeFirmaFinalForm.titleCode}">
    <fmt:message key="${tipusEstatDeFirmaFinalForm.titleCode}" >
      <fmt:param value="${tipusEstatDeFirmaFinalForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tipusEstatDeFirmaFinalForm.entityNameCode}">
      <fmt:message var="entityname" key="tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinal"/>
    </c:if>
    <c:if test="${not empty tipusEstatDeFirmaFinalForm.entityNameCode}">
      <fmt:message var="entityname" key="${tipusEstatDeFirmaFinalForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tipusEstatDeFirmaFinalForm.nou?'genapp.createtitle':(tipusEstatDeFirmaFinalForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty tipusEstatDeFirmaFinalForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tipusEstatDeFirmaFinalForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tipusEstatDeFirmaFinalForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tipusEstatDeFirmaFinalForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>