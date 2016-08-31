<%@page import="org.fundaciobit.plugins.signature.exemple.utils.HtmlUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:if test="${not empty missatges}">
  
  <c:forEach items="${missatges}" var="tipusList" varStatus="status">
    
      <c:forEach items="${tipusList.value}" var="msg" >
      <div class="alert alert-${tipusList.key}">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      ${msg}
      </div>
      </c:forEach>
    
  </c:forEach>
</c:if>
<c:remove var="missatges" scope="session" />


<div class="spacer"></div>