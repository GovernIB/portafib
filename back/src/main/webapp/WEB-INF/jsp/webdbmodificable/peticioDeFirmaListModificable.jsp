<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<input type="hidden" id="motiuRebuig" name="motiuRebuig" value=""/>

</div> </form>




<%--  ======  AQUI COMENǇA MODAL DE SELECCIӓ D'USUARI ============ --%>

<style type="text/css">
.modal-body{overflow-y: inherit;}

.modal-open .dropdown-menu {
  z-index: 2050;
}
</style>

<div id="selectUserAppModal" class="modal hide fade">
  <form action="<%=request.getContextPath()%>/${contexte}/selectflux" method="get" name="seleccioUsuariAppForm">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      <h3><fmt:message key="selectflux.elegirusuariapp"></fmt:message></h3>
    </div>
    <div class="modal-body">

      <table>
       <tr>
         <td><fmt:message key="usuariAplicacio.usuariAplicacio"></fmt:message></td>
         <td>            
           <select  class="input-xlarge" id="usuariAplicacioID" name="usuariAplicacioID">
              <c:forEach items="${listOfUsuariAplicacio}" var="tmp">
                <option value="${tmp}">${tmp}</option>
              </c:forEach>
           </select>
         </td>
       </tr>
       <tr>
        <td><fmt:message key="peticioDeFirma.origenPeticioDeFirma"></fmt:message></td>
        <td>
          <select  class="input-xlarge" id="origenPeticioDeFirma" name="origenPeticioDeFirma">
            <c:forEach items="${listOfOrigenPeticioDeFirma}" var="tmp">
              <option value="${tmp.key}">${tmp.value}</option>
            </c:forEach>
          </select>
        </td>
        </tr>
       </table>

       <br/>
       <div align="center">
       <button id="continuar" type="submit" class="btn btn-primary" title="<fmt:message key="continuar" />" >
          <%--  <i class="fas fa-plus-circle icon-white"></i> --%>
          <fmt:message key="continuar"/>
      </button>
      </div>

    </div>
  </form>
</div>
<script type="text/javascript">

  function openSelectUserAppDialog() {
      $('#selectUserAppModal').modal('show');
  }

  
</script>

<%--  ==============  FINAL  MODAL DE SELECCIӓ D'USUARI  ================ --%>



<script type="text/javascript">


  function rebutjar(peticioID) {
    var reason = prompt("<fmt:message key="motiurebutjar"/>:", "");
    
    if (reason != null) {      
      document.getElementById("motiuRebuig").value=reason;
      document.peticioDeFirma.action = '<c:url value="${contexte}/rebutjar/"/>' + peticioID;
      document.peticioDeFirma.submit();
    }
  }

  function rebutjarseleccionades() {
    var x;
    var reason = prompt("<fmt:message key="motiurebutjar"/>","");
    
    if (reason!=null) {      
      document.getElementById("motiuRebuig").value=reason;
      document.peticioDeFirma.action = '<c:url value="${contexte}/rebutjarSeleccionades"/>';
      document.peticioDeFirma.submit();
    }
  }
  
  
  function rebutjarEsborrar(peticioID) {
    var reason = prompt("<fmt:message key="motiurebutjar"/>:", "");
    
    if (reason != null) {      
      document.getElementById("motiuRebuig").value=reason;
      openModalSubmit('<c:url value="${contexte}/esborrar/"/>' + peticioID ,'show', 'peticioDeFirmaFilterForm');
    }
  }

  function rebutjarEsborrarSeleccionades() {
    
    var reason = prompt("<fmt:message key="motiurebutjar"/>","");
    
    if (reason != null) {      
      document.getElementById("motiuRebuig").value=reason;
      openModalSubmit('<c:url value="${contexte}/esborrarSeleccionades"/>','show','peticioDeFirmaFilterForm');
    }
  }

  
  function esborrarAmbMotiu(peticioID) {
    var reason = prompt("<fmt:message key="motiuesborrar"/>:", "");
    
    if (reason != null) {      
      document.getElementById("motiuRebuig").value=reason;
      openModalSubmit('<c:url value="${contexte}/esborrar/"/>' + peticioID ,'show', 'peticioDeFirmaFilterForm');
    }
  }

  function esborrarAmbMotiuSeleccionades() {
    
    var reason = prompt("<fmt:message key="motiuesborrar"/>","");
    
    if (reason != null) {      
      document.getElementById("motiuRebuig").value=reason;
      openModalSubmit('<c:url value="${contexte}/esborrarSeleccionades"/>','show','peticioDeFirmaFilterForm');
    }
  }
  
  
</script>

 <form> <div>
