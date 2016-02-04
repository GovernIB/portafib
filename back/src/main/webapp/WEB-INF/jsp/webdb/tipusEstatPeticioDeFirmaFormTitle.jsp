<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(tipusEstatPeticioDeFirmaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tipusEstatPeticioDeFirmaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tipusEstatPeticioDeFirmaForm.titleCode}">
    <fmt:message key="${tipusEstatPeticioDeFirmaForm.titleCode}" >
      <fmt:param value="${tipusEstatPeticioDeFirmaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tipusEstatPeticioDeFirmaForm.entityNameCode}">
      <fmt:message var="entityname" key="tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirma"/>
    </c:if>
    <c:if test="${not empty tipusEstatPeticioDeFirmaForm.entityNameCode}">
      <fmt:message var="entityname" key="${tipusEstatPeticioDeFirmaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tipusEstatPeticioDeFirmaForm.nou?'genapp.createtitle':(tipusEstatPeticioDeFirmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty tipusEstatPeticioDeFirmaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tipusEstatPeticioDeFirmaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tipusEstatPeticioDeFirmaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tipusEstatPeticioDeFirmaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>