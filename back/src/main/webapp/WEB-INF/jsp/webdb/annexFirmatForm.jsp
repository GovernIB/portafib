
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="annexFirmatFormTitle.jsp" %>


<form:form modelAttribute="annexFirmatForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${annexFirmatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="annexFirmatFormCorePre.jsp" %>
  <%@include file="annexFirmatFormCore.jsp" %>

  <%@include file="annexFirmatFormCorePost.jsp" %>

  <%@include file="annexFirmatFormButtons.jsp" %>

  <c:if test="${annexFirmatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/annexFirmatFormModificable.jsp" %>
  </c:if>

</form:form>


