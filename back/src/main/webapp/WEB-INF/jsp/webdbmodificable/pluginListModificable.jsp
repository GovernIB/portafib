<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%--  ======  AQUI COMENÇA MODAL DE SELECCIÓ DE PLANTILLA ============ --%>

<style type="text/css">
.modal-body{overflow-y: inherit;}

.modal-open .dropdown-menu {
  z-index: 2050;
}
</style>

<div id="selectUserAppModal" class="modal hide fade">

    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      <h3><fmt:message key="moduldefirma.elegirmodul"></fmt:message></h3>
    </div>
    <div class="modal-body">
       
       <select  class="input-xxlarge" id="plantillamoduldefirmaid" name="plantillamoduldefirmaid">
          <c:forEach items="${llistatDePlantillaDeModuls}" var="tmp">
            <option value="${tmp.pluginID}">${tmp.nom.traduccions[lang].valor}</option>
          </c:forEach>
        </select>
       <br/>
       <br/>
       <br/>
       <div align="center">
       <button id="continuar" type="button"  class="btn btn-primary" onclick="selectPlantilla()" title="<fmt:message key="continuar" />" >
          <%--  <i class="icon-plus-sign icon-white"></i> --%>
          <fmt:message key="continuar"/>
      </button>
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