<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty custodiaInfoForm.entityNameCode}">
    <fmt:message var="entityname" key="custodiaInfo.custodiaInfo"/>
  </c:if>
  <c:if test="${not empty custodiaInfoForm.entityNameCode}">
    <fmt:message var="entityname" key="${custodiaInfoForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty custodiaInfoForm.titleCode}">
    <fmt:message key="${custodiaInfoForm.titleCode}" >
      <fmt:param value="${custodiaInfoForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty custodiaInfoForm.titleCode}">
    <c:set var="keytitle" value="${custodiaInfoForm.nou?'genapp.createtitle':(custodiaInfoForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty custodiaInfoForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${custodiaInfoForm.subTitleCode}" /></h5>
  </c:if>
</div>