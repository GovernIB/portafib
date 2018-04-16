
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="estadisticaFormTitle.jsp" %>


<form:form modelAttribute="estadisticaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${estadisticaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="estadisticaFormCorePre.jsp" %>
  <%@include file="estadisticaFormCore.jsp" %>

  <%@include file="estadisticaFormCorePost.jsp" %>

  <%@include file="estadisticaFormButtons.jsp" %>

  <c:if test="${estadisticaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/estadisticaFormModificable.jsp" %>
  </c:if>

</form:form>


