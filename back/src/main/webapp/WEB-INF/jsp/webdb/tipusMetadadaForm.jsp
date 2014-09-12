
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="tipusMetadadaFormTitle.jsp" %>


<form:form modelAttribute="tipusMetadadaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${tipusMetadadaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tipusMetadadaFormCorePre.jsp" %>
  <%@include file="tipusMetadadaFormCore.jsp" %>

  <%@include file="tipusMetadadaFormCorePost.jsp" %>

  <%@include file="tipusMetadadaFormButtons.jsp" %>

  <c:if test="${tipusMetadadaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tipusMetadadaFormModificable.jsp" %>
  </c:if>

</form:form>


