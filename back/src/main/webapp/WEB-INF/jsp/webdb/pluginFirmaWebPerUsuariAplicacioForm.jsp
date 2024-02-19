
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="pluginFirmaWebPerUsuariAplicacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="pluginFirmaWebPerUsuariAplicacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${pluginFirmaWebPerUsuariAplicacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pluginFirmaWebPerUsuariAplicacioFormCorePre.jsp" %>

  <%@include file="pluginFirmaWebPerUsuariAplicacioFormCore.jsp" %>

  <%@include file="pluginFirmaWebPerUsuariAplicacioFormCorePost.jsp" %>

  <%@include file="pluginFirmaWebPerUsuariAplicacioFormButtons.jsp" %>

  <c:if test="${not empty pluginFirmaWebPerUsuariAplicacioForm.sections}">
     <c:set var="__basename" value="pluginFirmaWebPerUsuariAplicacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${pluginFirmaWebPerUsuariAplicacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pluginFirmaWebPerUsuariAplicacioFormModificable.jsp" %>
  </c:if>

</form:form>


