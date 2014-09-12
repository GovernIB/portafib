
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="roleUsuariAplicacioFormTitle.jsp" %>


<form:form modelAttribute="roleUsuariAplicacioForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${roleUsuariAplicacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="roleUsuariAplicacioFormCorePre.jsp" %>
  <%@include file="roleUsuariAplicacioFormCore.jsp" %>

  <%@include file="roleUsuariAplicacioFormCorePost.jsp" %>

  <%@include file="roleUsuariAplicacioFormButtons.jsp" %>

  <c:if test="${roleUsuariAplicacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/roleUsuariAplicacioFormModificable.jsp" %>
  </c:if>

</form:form>


