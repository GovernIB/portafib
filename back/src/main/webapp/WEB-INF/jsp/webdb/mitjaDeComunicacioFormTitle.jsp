<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty mitjaDeComunicacioForm.entityNameCode}">
    <fmt:message var="entityname" key="mitjaDeComunicacio.mitjaDeComunicacio"/>
  </c:if>
  <c:if test="${not empty mitjaDeComunicacioForm.entityNameCode}">
    <fmt:message var="entityname" key="${mitjaDeComunicacioForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty mitjaDeComunicacioForm.titleCode}">
      <fmt:message key="${mitjaDeComunicacioForm.titleCode}" />
  </c:if>
  <c:if test="${empty mitjaDeComunicacioForm.titleCode}">
    <c:set var="keytitle" value="${mitjaDeComunicacioForm.nou?'genapp.createtitle':(mitjaDeComunicacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty mitjaDeComunicacioForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${mitjaDeComunicacioForm.subTitleCode}" /></h5>
  </c:if>
</div>