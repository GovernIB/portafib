
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="mitjaDeComunicacioFormTitle.jsp" %>


<form:form modelAttribute="mitjaDeComunicacioForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${mitjaDeComunicacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="mitjaDeComunicacioFormCorePre.jsp" %>
  <%@include file="mitjaDeComunicacioFormCore.jsp" %>

  <%@include file="mitjaDeComunicacioFormCorePost.jsp" %>

  <%@include file="mitjaDeComunicacioFormButtons.jsp" %>

</form:form>


