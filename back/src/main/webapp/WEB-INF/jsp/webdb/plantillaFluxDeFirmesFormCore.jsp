<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PlantillaFluxDeFirmesFields" className="es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PlantillaFluxDeFirmesFields.FLUXDEFIRMESID)}">
        <tr id="plantillaFluxDeFirmes_fluxDeFirmesID_rowid">
          <td id="plantillaFluxDeFirmes_fluxDeFirmesID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PlantillaFluxDeFirmesFields.FLUXDEFIRMESID])?'plantillaFluxDeFirmes.fluxDeFirmesID':__theForm.labels[PlantillaFluxDeFirmesFields.FLUXDEFIRMESID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PlantillaFluxDeFirmesFields.FLUXDEFIRMESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PlantillaFluxDeFirmesFields.FLUXDEFIRMESID]}" ></i>
              </c:if>
            </td>
          <td id="plantillaFluxDeFirmes_fluxDeFirmesID_columnvalueid">
          <form:errors path="plantillaFluxDeFirmes.fluxDeFirmesID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.FLUXDEFIRMESID)}" >
          <form:hidden path="plantillaFluxDeFirmes.fluxDeFirmesID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.plantillaFluxDeFirmes.fluxDeFirmesID,__theForm.listOfFluxDeFirmesForFluxDeFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.FLUXDEFIRMESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="plantillaFluxDeFirmes_fluxDeFirmesID"  onchange="if(typeof onChangeFluxDeFirmesID == 'function') {  onChangeFluxDeFirmesID(this); };"  cssClass="form-control col-md-9-optional" path="plantillaFluxDeFirmes.fluxDeFirmesID">
            <c:forEach items="${__theForm.listOfFluxDeFirmesForFluxDeFirmesID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PlantillaFluxDeFirmesFields.DESCRIPCIO)}">
        <tr id="plantillaFluxDeFirmes_descripcio_rowid">
          <td id="plantillaFluxDeFirmes_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PlantillaFluxDeFirmesFields.DESCRIPCIO])?'plantillaFluxDeFirmes.descripcio':__theForm.labels[PlantillaFluxDeFirmesFields.DESCRIPCIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PlantillaFluxDeFirmesFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PlantillaFluxDeFirmesFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="plantillaFluxDeFirmes_descripcio_columnvalueid">
              <form:errors path="plantillaFluxDeFirmes.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.DESCRIPCIO)? 'true' : 'false'}" path="plantillaFluxDeFirmes.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('plantillaFluxDeFirmes.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('plantillaFluxDeFirmes.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('plantillaFluxDeFirmes.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PlantillaFluxDeFirmesFields.USUARIENTITATID)}">
        <tr id="plantillaFluxDeFirmes_usuariEntitatID_rowid">
          <td id="plantillaFluxDeFirmes_usuariEntitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PlantillaFluxDeFirmesFields.USUARIENTITATID])?'plantillaFluxDeFirmes.usuariEntitatID':__theForm.labels[PlantillaFluxDeFirmesFields.USUARIENTITATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PlantillaFluxDeFirmesFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PlantillaFluxDeFirmesFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="plantillaFluxDeFirmes_usuariEntitatID_columnvalueid">
          <form:errors path="plantillaFluxDeFirmes.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.USUARIENTITATID)}" >
          <form:hidden path="plantillaFluxDeFirmes.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.plantillaFluxDeFirmes.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="plantillaFluxDeFirmes_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="plantillaFluxDeFirmes.usuariEntitatID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.plantillaFluxDeFirmes.usuariEntitatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.plantillaFluxDeFirmes.usuariEntitatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PlantillaFluxDeFirmesFields.USUARIAPLICACIOID)}">
        <tr id="plantillaFluxDeFirmes_usuariAplicacioID_rowid">
          <td id="plantillaFluxDeFirmes_usuariAplicacioID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PlantillaFluxDeFirmesFields.USUARIAPLICACIOID])?'plantillaFluxDeFirmes.usuariAplicacioID':__theForm.labels[PlantillaFluxDeFirmesFields.USUARIAPLICACIOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PlantillaFluxDeFirmesFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PlantillaFluxDeFirmesFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
            </td>
          <td id="plantillaFluxDeFirmes_usuariAplicacioID_columnvalueid">
          <form:errors path="plantillaFluxDeFirmes.usuariAplicacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.USUARIAPLICACIOID)}" >
          <form:hidden path="plantillaFluxDeFirmes.usuariAplicacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.plantillaFluxDeFirmes.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.USUARIAPLICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="plantillaFluxDeFirmes_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="form-control col-md-9-optional" path="plantillaFluxDeFirmes.usuariAplicacioID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.plantillaFluxDeFirmes.usuariAplicacioID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.plantillaFluxDeFirmes.usuariAplicacioID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PlantillaFluxDeFirmesFields.COMPARTIR)}">
        <tr id="plantillaFluxDeFirmes_compartir_rowid">
          <td id="plantillaFluxDeFirmes_compartir_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PlantillaFluxDeFirmesFields.COMPARTIR])?'plantillaFluxDeFirmes.compartir':__theForm.labels[PlantillaFluxDeFirmesFields.COMPARTIR]}" />
             </label>
              <c:if test="${not empty __theForm.help[PlantillaFluxDeFirmesFields.COMPARTIR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PlantillaFluxDeFirmesFields.COMPARTIR]}" ></i>
              </c:if>
            </td>
          <td id="plantillaFluxDeFirmes_compartir_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.COMPARTIR)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeCompartir == 'function') {  onChangeCompartir(this); };"  path="plantillaFluxDeFirmes.compartir">
                <form:option value=""><fmt:message key="compartirplantilla." /></form:option>
                <form:option value="true" ><fmt:message key="compartirplantilla.true" /></form:option>
                <form:option value="false" ><fmt:message key="compartirplantilla.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.COMPARTIR)}" >
                <fmt:message key="compartirplantilla.${__theForm.plantillaFluxDeFirmes.compartir}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
