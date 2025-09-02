
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="correuAgrupatForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="correuAgrupatFormTitle.jsp" %>
 
  <c:set var="contexte" value="${correuAgrupatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="correuAgrupatFormCorePre.jsp" %>

  <%@include file="correuAgrupatFormCore.jsp" %>

  <%@include file="correuAgrupatFormCorePost.jsp" %>

  <%@include file="correuAgrupatFormButtons.jsp" %>

  <c:if test="${not empty correuAgrupatForm.sections}">
     <c:set var="__basename" value="correuAgrupat" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${correuAgrupatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/correuAgrupatFormModificable.jsp" %>
  </c:if>

</form:form>


