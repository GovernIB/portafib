
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="perfilsPerUsuariAplicacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="perfilsPerUsuariAplicacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${perfilsPerUsuariAplicacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="perfilsPerUsuariAplicacioFormCorePre.jsp" %>

  <%@include file="perfilsPerUsuariAplicacioFormCore.jsp" %>

  <%@include file="perfilsPerUsuariAplicacioFormCorePost.jsp" %>

  <%@include file="perfilsPerUsuariAplicacioFormButtons.jsp" %>

  <c:if test="${not empty perfilsPerUsuariAplicacioForm.sections}">
     <c:set var="__basename" value="perfilsPerUsuariAplicacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${perfilsPerUsuariAplicacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/perfilsPerUsuariAplicacioFormModificable.jsp" %>
  </c:if>

</form:form>


