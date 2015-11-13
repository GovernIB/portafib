<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  
  <fmt:message key="plugindefirma.seleccio.title"/>
  <br/>
  <h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
  <fmt:message key="plugindefirma.seleccio.subtitle"/>
  </h5>
  
  <br/>
  <br/>
  
  <div class="well" style="max-width: 400px; margin: 0 auto 10px;">
  <c:forEach items="${moduls}" var="modul">
     <button type="button" class="btn btn-large btn-block btn-primary" onclick="location.href='<c:url value="/common/signmodule/showsignaturemodule/${modul.modulDeFirmaID}/${signaturesSetID}"/>'">
     <b>${modul.nom.traduccions[lang].valor}</b><br>
     <small>
     <i>${modul.descripcioCurta.traduccions[lang].valor}</i>
     </small>
     </button>
  </c:forEach>
  </div>
  
  <br/>
  
</div>

<script>
  $( document ).ready(function() {
    parent.alertsize(document.body.scrollHeight);
  });
</script>
