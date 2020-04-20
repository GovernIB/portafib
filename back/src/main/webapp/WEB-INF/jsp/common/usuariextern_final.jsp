<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<br/>


<center>
  <div class="alert ${(status eq 2)? 'alert-success':'alert-error'}">
    <h2>
      <fmt:message key="usuariextern.token.${(status eq 2)? 'finalprocesdefirma':'.error.titol'}" />
    </h2>
    
    <c:if test="${ not empty errorcode}" >
    <h4> <fmt:message key="${errorcode}" >
           <fmt:param value="${param0}" />
           <fmt:param value="${param1}" />
           <fmt:param value="${param2}" />
           <fmt:param value="${param3}" />
           <fmt:param value="${param4}" />
           <fmt:param value="${param5}" />
         </fmt:message>
    </h4>
    </c:if>
    <br> TOKEN: <b>${token}</b> <br>
  </div>
</center>

<br/>