
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="usuariAplicacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="usuariAplicacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${usuariAplicacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="usuariAplicacioFormCorePre.jsp" %>

  <%@include file="usuariAplicacioFormCore.jsp" %>

  <%@include file="usuariAplicacioFormCorePost.jsp" %>

  <%@include file="usuariAplicacioFormButtons.jsp" %>

  <c:if test="${not empty usuariAplicacioForm.sections}">
     <c:set var="__basename" value="usuariAplicacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${usuariAplicacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/usuariAplicacioFormModificable.jsp" %>
  </c:if>

</form:form>


