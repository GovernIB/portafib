
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="estadisticaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="estadisticaFormTitle.jsp" %>
 
  <c:set var="contexte" value="${estadisticaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="estadisticaFormCorePre.jsp" %>

  <%@include file="estadisticaFormCore.jsp" %>

  <%@include file="estadisticaFormCorePost.jsp" %>

  <%@include file="estadisticaFormButtons.jsp" %>

  <c:if test="${not empty estadisticaForm.sections}">
     <c:set var="__basename" value="estadistica" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${estadisticaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/estadisticaFormModificable.jsp" %>
  </c:if>

</form:form>


