
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="perfilDeFirmaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="perfilDeFirmaFormTitle.jsp" %>
 
  <c:set var="contexte" value="${perfilDeFirmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="perfilDeFirmaFormCorePre.jsp" %>

  <%@include file="perfilDeFirmaFormCore.jsp" %>

  <%@include file="perfilDeFirmaFormCorePost.jsp" %>

  <%@include file="perfilDeFirmaFormButtons.jsp" %>

  <c:if test="${not empty perfilDeFirmaForm.sections}">
     <c:set var="__basename" value="perfilDeFirma" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${perfilDeFirmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/perfilDeFirmaFormModificable.jsp" %>
  </c:if>

</form:form>


