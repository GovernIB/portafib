<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<br>
<br>
<br>
<br>
<br>
<br>

<center>
  <div class="alert alert-error">
    <h2>
      <fmt:message key="usuariextern.token.error.titol" />
    </h2>
    <c:if test="${ not empty error}" >
    <h4>${error}</h4>
    </c:if>
    <c:if test="${ not empty errorcode}" >
    <h4> <fmt:message key="${errorcode}" /></h4>
    </c:if>
    <br> TOKEN: <b>${token}</b> <br>
  </div>
</center>

