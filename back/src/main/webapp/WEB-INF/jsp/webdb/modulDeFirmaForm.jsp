
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="modulDeFirmaFormTitle.jsp" %>


<form:form modelAttribute="modulDeFirmaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${modulDeFirmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="modulDeFirmaFormCorePre.jsp" %>
  <%@include file="modulDeFirmaFormCore.jsp" %>

  <%@include file="modulDeFirmaFormCorePost.jsp" %>

  <%@include file="modulDeFirmaFormButtons.jsp" %>

  <c:if test="${modulDeFirmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/modulDeFirmaFormModificable.jsp" %>
  </c:if>

</form:form>


