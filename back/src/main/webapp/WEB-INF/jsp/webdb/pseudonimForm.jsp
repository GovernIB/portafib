
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="pseudonimForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="pseudonimFormTitle.jsp" %>
 
  <c:set var="contexte" value="${pseudonimForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pseudonimFormCorePre.jsp" %>

  <%@include file="pseudonimFormCore.jsp" %>

  <%@include file="pseudonimFormCorePost.jsp" %>

  <%@include file="pseudonimFormButtons.jsp" %>

  <c:if test="${not empty pseudonimForm.sections}">
     <c:set var="__basename" value="pseudonim" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${pseudonimForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pseudonimFormModificable.jsp" %>
  </c:if>

</form:form>


