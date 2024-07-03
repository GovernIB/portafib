<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%--  ======  AQUI COMENÇA MODAL DE SELECCIÓ DE PLANTILLA ============ --%>

<style type="text/css">
.modal-body{overflow-y: inherit;}

.modal-open .dropdown-menu {
  z-index: 2050;
}
</style>

<div id="selectUserAppModal" class="modal hide fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                
                <h4>
                    <fmt:message key="${titolmodal}"></fmt:message>
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
      </button>
            </div>
            <div class="modal-body">
                <center>
                    <select class="" id="plantillamoduldefirmaid" name="plantillamoduldefirmaid">
                        <c:forEach items="${llistatDePlantillaDeModuls}" var="tmp">
                            <option value="${tmp.pluginID}">${tmp.nom.traduccions[lang].valor}</option>
                        </c:forEach>
                    </select>
                </center>
            </div>
            <div class="modal-footer">
                <div align="center">
                    <button id="continuar" type="button" class="btn btn-primary" onclick="selectPlantilla()"
                        title="<fmt:message key="continuar" />">
                        <%--  <i class="fas fa-plus-circle icon-white"></i> --%>
                        <fmt:message key="continuar" />
                    </button>
                </div>

            </div>

        </div>

    </div>

</div>
<script type="text/javascript">

  function openSelectModulDeFirmaDialog() {
      $('#selectUserAppModal').modal('show');
  }
  
  function selectPlantilla() {
      
      var selIndex = document.getElementById("plantillamoduldefirmaid").selectedIndex;
      var selValue = document.getElementById("plantillamoduldefirmaid").options[selIndex].value;
      
      window.location.href="<%=request.getContextPath()%>${contexte}/selectplantilla/" + selValue;

  }
  
</script>