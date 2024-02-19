
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="rebreAvisForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="rebreAvisFormTitle.jsp" %>
 
  <c:set var="contexte" value="${rebreAvisForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="rebreAvisFormCorePre.jsp" %>

  <%@include file="rebreAvisFormCore.jsp" %>

  <%@include file="rebreAvisFormCorePost.jsp" %>

  <%@include file="rebreAvisFormButtons.jsp" %>

  <c:if test="${not empty rebreAvisForm.sections}">
     <c:set var="__basename" value="rebreAvis" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${rebreAvisForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/rebreAvisFormModificable.jsp" %>
  </c:if>

</form:form>


