
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="usuariAplicacioFormTitle.jsp" %>


<form:form modelAttribute="usuariAplicacioForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${usuariAplicacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="usuariAplicacioFormCorePre.jsp" %>
  <%@include file="usuariAplicacioFormCore.jsp" %>

  <%@include file="usuariAplicacioFormCorePost.jsp" %>

  <%@include file="usuariAplicacioFormButtons.jsp" %>

  <c:if test="${usuariAplicacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/usuariAplicacioFormModificable.jsp" %>
  </c:if>

</form:form>


