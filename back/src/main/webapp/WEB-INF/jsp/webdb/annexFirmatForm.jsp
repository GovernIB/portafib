
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="annexFirmatForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="annexFirmatFormTitle.jsp" %>
 
  <c:set var="contexte" value="${annexFirmatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="annexFirmatFormCorePre.jsp" %>

  <%@include file="annexFirmatFormCore.jsp" %>

  <%@include file="annexFirmatFormCorePost.jsp" %>

  <%@include file="annexFirmatFormButtons.jsp" %>

  <c:if test="${not empty annexFirmatForm.sections}">
     <c:set var="__basename" value="annexFirmat" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${annexFirmatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/annexFirmatFormModificable.jsp" %>
  </c:if>

</form:form>


