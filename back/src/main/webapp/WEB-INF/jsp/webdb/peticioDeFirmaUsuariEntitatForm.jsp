
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="peticioDeFirmaUsuariEntitatFormTitle.jsp" %>


<form:form modelAttribute="peticioDeFirmaUsuariEntitatForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${peticioDeFirmaUsuariEntitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="peticioDeFirmaUsuariEntitatFormCorePre.jsp" %>
  <%@include file="peticioDeFirmaUsuariEntitatFormCore.jsp" %>

  <%@include file="peticioDeFirmaUsuariEntitatFormCorePost.jsp" %>

  <%@include file="peticioDeFirmaUsuariEntitatFormButtons.jsp" %>

</form:form>


