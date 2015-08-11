<%@page import="es.caib.portafib.utils.Configuracio"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%--  ======  AQUI COMENÇA MODAL DE SELECCIÓ D'USUARI ============ --%>
<%

int max = Configuracio.getMaxItemsToShowInAutocomplete();

int val = Math.max(114, 114 + Math.max(0,max - 3) * 28);

session.setAttribute("MaxItemsToShowInAutocomplete", val );
%>
<div id="selectUserModal" class="modal hide fade" style="width:640px;">
  <form:form modelAttribute="seleccioUsuariForm" action="${theURL}" method="${method}" name="seleccioUsuariForm">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      <h3><fmt:message key="${seleccioUsuariForm.titol}"></fmt:message></h3>
    </div>
    <div class="modal-body" style="height: ${MaxItemsToShowInAutocomplete}px">
      
       <%@ include file="/WEB-INF/jsp/common/seleccioUsuariField.jsp"%>
       <br/>
       <div align="center">
       <button type="button" class="btn btn-primary" onclick="selectedUserFromModal()" title="<fmt:message key="afegir" />" >
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
    // alert(" PRESUBMIT Valor de ID ]" + id + "[")
    
    if (id) {
      document.getElementById("seleccioUsuariForm").submit();
    } else {
      alert("<fmt:message key="formselectionby.error.emptyid"/>");
    }

}

</script>

<%--  ===================================== --%>