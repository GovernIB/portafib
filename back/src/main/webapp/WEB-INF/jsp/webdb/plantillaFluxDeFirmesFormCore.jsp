<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PlantillaFluxDeFirmesFields" className="es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PlantillaFluxDeFirmesFields.FLUXDEFIRMESID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PlantillaFluxDeFirmesFields.FLUXDEFIRMESID])?'plantillaFluxDeFirmes.fluxDeFirmesID':__theForm.labels[PlantillaFluxDeFirmesFields.FLUXDEFIRMESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PlantillaFluxDeFirmesFields.FLUXDEFIRMESID]}">
              <i class="icon-info-sign" title="${__theForm.help[PlantillaFluxDeFirmesFields.FLUXDEFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="plantillaFluxDeFirmes.fluxDeFirmesID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.FLUXDEFIRMESID)}" >
          <form:hidden path="plantillaFluxDeFirmes.fluxDeFirmesID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.plantillaFluxDeFirmes.fluxDeFirmesID,__theForm.listOfFluxDeFirmesForFluxDeFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.FLUXDEFIRMESID)}" >
          <form:select id="plantillaFluxDeFirmes_fluxDeFirmesID"  onchange="if(typeof onChangeFluxDeFirmesID == 'function') {  onChangeFluxDeFirmesID(this); };"  cssClass="input-xxlarge" path="plantillaFluxDeFirmes.fluxDeFirmesID">
            <c:forEach items="${__theForm.listOfFluxDeFirmesForFluxDeFirmesID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PlantillaFluxDeFirmesFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PlantillaFluxDeFirmesFields.DESCRIPCIO])?'plantillaFluxDeFirmes.descripcio':__theForm.labels[PlantillaFluxDeFirmesFields.DESCRIPCIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PlantillaFluxDeFirmesFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[PlantillaFluxDeFirmesFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="plantillaFluxDeFirmes.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.DESCRIPCIO)? 'true' : 'false'}" path="plantillaFluxDeFirmes.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('plantillaFluxDeFirmes.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('plantillaFluxDeFirmes.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('plantillaFluxDeFirmes.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PlantillaFluxDeFirmesFields.USUARIENTITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PlantillaFluxDeFirmesFields.USUARIENTITATID])?'plantillaFluxDeFirmes.usuariEntitatID':__theForm.labels[PlantillaFluxDeFirmesFields.USUARIENTITATID]}" />
              <c:if test="${not empty __theForm.help[PlantillaFluxDeFirmesFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[PlantillaFluxDeFirmesFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="plantillaFluxDeFirmes.usuariEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.USUARIENTITATID)}" >
          <form:hidden path="plantillaFluxDeFirmes.usuariEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.plantillaFluxDeFirmes.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.USUARIENTITATID)}" >
          <form:select id="plantillaFluxDeFirmes_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="input-xxlarge" path="plantillaFluxDeFirmes.usuariEntitatID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PlantillaFluxDeFirmesFields.USUARIAPLICACIOID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PlantillaFluxDeFirmesFields.USUARIAPLICACIOID])?'plantillaFluxDeFirmes.usuariAplicacioID':__theForm.labels[PlantillaFluxDeFirmesFields.USUARIAPLICACIOID]}" />
              <c:if test="${not empty __theForm.help[PlantillaFluxDeFirmesFields.USUARIAPLICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[PlantillaFluxDeFirmesFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="plantillaFluxDeFirmes.usuariAplicacioID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.USUARIAPLICACIOID)}" >
          <form:hidden path="plantillaFluxDeFirmes.usuariAplicacioID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.plantillaFluxDeFirmes.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.USUARIAPLICACIOID)}" >
          <form:select id="plantillaFluxDeFirmes_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="input-xxlarge" path="plantillaFluxDeFirmes.usuariAplicacioID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PlantillaFluxDeFirmesFields.COMPARTIR)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PlantillaFluxDeFirmesFields.COMPARTIR])?'plantillaFluxDeFirmes.compartir':__theForm.labels[PlantillaFluxDeFirmesFields.COMPARTIR]}" />
              <c:if test="${not empty __theForm.help[PlantillaFluxDeFirmesFields.COMPARTIR]}">
              <i class="icon-info-sign" title="${__theForm.help[PlantillaFluxDeFirmesFields.COMPARTIR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PlantillaFluxDeFirmesFields.COMPARTIR)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeCompartir == 'function') {  onChangeCompartir(this); };"  path="plantillaFluxDeFirmes.compartir">
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
        
