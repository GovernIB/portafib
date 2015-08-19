
<%@ include file="/WEB-INF/jsp/webdb/peticioDeFirmaList.jsp"%>

<%@page import="es.caib.portafib.utils.Configuracio"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%--  ======  AQUI COMENÇA MODAL DE SELECCIÓ D'USUARI ============ --%>

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
       
       <select  class="input-xlarge" id="usuariAplicacioID" name="usuariAplicacioID">
          <c:forEach items="${listOfUsuariAplicacio}" var="tmp">
            <option value="${tmp}">${tmp}</option>
          </c:forEach>
        </select>
       
       <br/>
       <div align="center">
       <button id="continuar" type="submit" class="btn btn-primary" title="<fmt:message key="continuar" />" >
          <%--  <i class="icon-plus-sign icon-white"></i> --%>
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




<%--  ===================================== --%>

