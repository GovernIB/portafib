<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty prioritatForm.entityNameCode}">
    <fmt:message var="entityname" key="prioritat.prioritat"/>
  </c:if>
  <c:if test="${not empty prioritatForm.entityNameCode}">
    <fmt:message var="entityname" key="${prioritatForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty prioritatForm.titleCode}">
    <fmt:message key="${prioritatForm.titleCode}" >
      <fmt:param value="${prioritatForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty prioritatForm.titleCode}">
    <c:set var="keytitle" value="${prioritatForm.nou?'genapp.createtitle':(prioritatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty prioritatForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${prioritatForm.subTitleCode}" /></h5>
  </c:if>
</div>