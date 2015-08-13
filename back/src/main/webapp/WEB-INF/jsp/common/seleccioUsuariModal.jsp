<%@page import="es.caib.portafib.utils.Configuracio"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%--  ======  AQUI COMENÇA MODAL DE SELECCIÓ D'USUARI ============ --%>

<style type="text/css">
.modal-body{overflow-y: inherit;}

.modal-open .dropdown-menu {
  z-index: 2050;
}
</style>

<div id="selectUserModal" class="modal hide fade" style="width:640px;">
  <form:form modelAttribute="seleccioUsuariForm" action="${theURL}" method="${method}" name="seleccioUsuariForm">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      <h3><fmt:message key="${seleccioUsuariForm.titol}"></fmt:message></h3>
    </div>
    <div class="modal-body">
      
       <%@ include file="/WEB-INF/jsp/common/seleccioUsuariField.jsp"%>
       <br/>
       <div align="center">
       <button id="afegirUsuariModal" type="button" class="btn btn-primary" onclick="selectedUserFromModal()" title="<fmt:message key="afegir" />" >
          <i class="icon-plus-sign icon-white"></i>
          <fmt:message key="afegir"/>
      </button>
      </div>
      
    </div>
  </form:form>
</div>
<script type="text/javascript">

  function openSelectUserDialog() {
      $('#selectUserModal').modal('show');
  }
  
</script>


<script type="text/javascript">

function selectedUserFromModal() {
    var id = $('#id').val();
    <%-- alert(" PRESUBMIT Valor de ID ]" + id + "[") --%>
    
    if (id) {
      document.getElementById("seleccioUsuariForm").submit();
    } else {
      alert("<fmt:message key="formselectionby.error.emptyid"/>");
    }

}

</script>

<%--  ===================================== --%>