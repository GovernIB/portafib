<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty peticioDeFirmaUsuariEntitatForm.entityNameCode}">
    <fmt:message var="entityname" key="peticioDeFirmaUsuariEntitat.peticioDeFirmaUsuariEntitat"/>
  </c:if>
  <c:if test="${not empty peticioDeFirmaUsuariEntitatForm.entityNameCode}">
    <fmt:message var="entityname" key="${peticioDeFirmaUsuariEntitatForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty peticioDeFirmaUsuariEntitatForm.titleCode}">
      <fmt:message key="${peticioDeFirmaUsuariEntitatForm.titleCode}" />
  </c:if>
  <c:if test="${empty peticioDeFirmaUsuariEntitatForm.titleCode}">
    <c:set var="keytitle" value="${peticioDeFirmaUsuariEntitatForm.nou?'genapp.createtitle':(peticioDeFirmaUsuariEntitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty peticioDeFirmaUsuariEntitatForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${peticioDeFirmaUsuariEntitatForm.subTitleCode}" /></h5>
  </c:if>
</div>