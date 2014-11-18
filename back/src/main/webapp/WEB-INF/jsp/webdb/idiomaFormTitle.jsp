<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty idiomaForm.entityNameCode}">
    <fmt:message var="entityname" key="idioma.idioma"/>
  </c:if>
  <c:if test="${not empty idiomaForm.entityNameCode}">
    <fmt:message var="entityname" key="${idiomaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty idiomaForm.titleCode}">
    <fmt:message key="${idiomaForm.titleCode}" >
      <fmt:param value="${idiomaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty idiomaForm.titleCode}">
    <c:set var="keytitle" value="${idiomaForm.nou?'genapp.createtitle':(idiomaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty idiomaForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${idiomaForm.subTitleCode}" /></h5>
  </c:if>
</div>