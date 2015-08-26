<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty posicioPaginaForm.entityNameCode}">
    <fmt:message var="entityname" key="posicioPagina.posicioPagina"/>
  </c:if>
  <c:if test="${not empty posicioPaginaForm.entityNameCode}">
    <fmt:message var="entityname" key="${posicioPaginaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty posicioPaginaForm.titleCode}">
    <fmt:message key="${posicioPaginaForm.titleCode}" >
      <fmt:param value="${posicioPaginaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty posicioPaginaForm.titleCode}">
    <c:set var="keytitle" value="${posicioPaginaForm.nou?'genapp.createtitle':(posicioPaginaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty posicioPaginaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(posicioPaginaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(posicioPaginaForm.subTitleCode, '=')}"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${posicioPaginaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>