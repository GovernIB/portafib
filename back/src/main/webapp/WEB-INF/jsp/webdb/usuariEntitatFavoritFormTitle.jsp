<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty usuariEntitatFavoritForm.entityNameCode}">
    <fmt:message var="entityname" key="usuariEntitatFavorit.usuariEntitatFavorit"/>
  </c:if>
  <c:if test="${not empty usuariEntitatFavoritForm.entityNameCode}">
    <fmt:message var="entityname" key="${usuariEntitatFavoritForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty usuariEntitatFavoritForm.titleCode}">
    <fmt:message key="${usuariEntitatFavoritForm.titleCode}" >
      <fmt:param value="${usuariEntitatFavoritForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty usuariEntitatFavoritForm.titleCode}">
    <c:set var="keytitle" value="${usuariEntitatFavoritForm.nou?'genapp.createtitle':(usuariEntitatFavoritForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty usuariEntitatFavoritForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${usuariEntitatFavoritForm.subTitleCode}" /></h5>
  </c:if>
</div>