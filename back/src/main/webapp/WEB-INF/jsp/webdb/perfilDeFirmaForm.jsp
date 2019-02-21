
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="perfilDeFirmaFormTitle.jsp" %>


<form:form modelAttribute="perfilDeFirmaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${perfilDeFirmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="perfilDeFirmaFormCorePre.jsp" %>
  <%@include file="perfilDeFirmaFormCore.jsp" %>

  <%@include file="perfilDeFirmaFormCorePost.jsp" %>

  <%@include file="perfilDeFirmaFormButtons.jsp" %>

  <c:if test="${perfilDeFirmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/perfilDeFirmaFormModificable.jsp" %>
  </c:if>

</form:form>


