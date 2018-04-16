<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(estadisticaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(estadisticaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty estadisticaForm.titleCode}">
    <fmt:message key="${estadisticaForm.titleCode}" >
      <fmt:param value="${estadisticaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty estadisticaForm.entityNameCode}">
      <fmt:message var="entityname" key="estadistica.estadistica"/>
    </c:if>
    <c:if test="${not empty estadisticaForm.entityNameCode}">
      <fmt:message var="entityname" key="${estadisticaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${estadisticaForm.nou?'genapp.createtitle':(estadisticaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty estadisticaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(estadisticaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(estadisticaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${estadisticaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>