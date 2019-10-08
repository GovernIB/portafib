<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<br>
<br>
<br>
<br>
<br>
<br>

<center>
  <div class="alert alert-success">
    <h2>
      <fmt:message key="usuariextern.token.finalprocesdefirma" />
    </h2>

    <br />
    <h4>${message}</h4>
    <br /> 
    <a href="<c:url value="${vistacompletaurl}" />" class="btn btn-primary" style="color: white;">
      <fmt:message key="usuariextern.token.anarveurevistacompletafirma" />
    </a>
  </div>