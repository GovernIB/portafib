
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="bitacolaFormTitle.jsp" %>


<form:form modelAttribute="bitacolaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${bitacolaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="bitacolaFormCorePre.jsp" %>

  <%@include file="bitacolaFormCore.jsp" %>

  <%@include file="bitacolaFormCorePost.jsp" %>

  <%@include file="bitacolaFormButtons.jsp" %>

  <c:if test="${not empty bitacolaForm.sections}">
     <c:set var="__basename" value="bitacola" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${bitacolaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/bitacolaFormModificable.jsp" %>
  </c:if>

</form:form>


