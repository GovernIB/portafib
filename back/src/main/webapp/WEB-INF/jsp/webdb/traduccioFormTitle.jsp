<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty traduccioForm.entityNameCode}">
    <fmt:message var="entityname" key="traduccio.traduccio"/>
  </c:if>
  <c:if test="${not empty traduccioForm.entityNameCode}">
    <fmt:message var="entityname" key="${traduccioForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty traduccioForm.titleCode}">
    <fmt:message key="${traduccioForm.titleCode}" >
      <fmt:param value="${traduccioForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty traduccioForm.titleCode}">
    <c:set var="keytitle" value="${traduccioForm.nou?'genapp.createtitle':(traduccioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty traduccioForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${traduccioForm.subTitleCode}" /></h5>
  </c:if>
</div>