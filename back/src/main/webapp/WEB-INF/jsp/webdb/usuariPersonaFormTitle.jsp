<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty usuariPersonaForm.entityNameCode}">
    <fmt:message var="entityname" key="usuariPersona.usuariPersona"/>
  </c:if>
  <c:if test="${not empty usuariPersonaForm.entityNameCode}">
    <fmt:message var="entityname" key="${usuariPersonaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty usuariPersonaForm.titleCode}">
    <fmt:message key="${usuariPersonaForm.titleCode}" >
      <fmt:param value="${usuariPersonaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty usuariPersonaForm.titleCode}">
    <c:set var="keytitle" value="${usuariPersonaForm.nou?'genapp.createtitle':(usuariPersonaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty usuariPersonaForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${usuariPersonaForm.subTitleCode}" /></h5>
  </c:if>
</div>