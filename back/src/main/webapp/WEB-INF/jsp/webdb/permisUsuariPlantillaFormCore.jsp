<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PermisUsuariPlantillaFields" className="es.caib.portafib.model.fields.PermisUsuariPlantillaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PermisUsuariPlantillaFields.USUARIENTITATID)}">
        <tr id="permisUsuariPlantilla_usuariEntitatID_rowid">
          <td id="permisUsuariPlantilla_usuariEntitatID_columnlabelid">
            <label for="permisUsuariPlantilla.usuariEntitatID">
              <fmt:message key="${(empty __theForm.labels[PermisUsuariPlantillaFields.USUARIENTITATID])?'permisUsuariPlantilla.usuariEntitatID':__theForm.labels[PermisUsuariPlantillaFields.USUARIENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PermisUsuariPlantillaFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PermisUsuariPlantillaFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="permisUsuariPlantilla_usuariEntitatID_columnvalueid">
          <form:errors path="permisUsuariPlantilla.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.USUARIENTITATID)}" >
          <form:hidden path="permisUsuariPlantilla.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.permisUsuariPlantilla.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}" aria-label="permisUsuariPlantilla.usuariEntitatID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="permisUsuariPlantilla.usuariEntitatID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };">


            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID)}">
        <tr id="permisUsuariPlantilla_plantillaFluxDeFirmesID_rowid">
          <td id="permisUsuariPlantilla_plantillaFluxDeFirmesID_columnlabelid">
            <label for="permisUsuariPlantilla.plantillaFluxDeFirmesID">
              <fmt:message key="${(empty __theForm.labels[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID])?'permisUsuariPlantilla.plantillaFluxDeFirmesID':__theForm.labels[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" ></i>
              </c:if>
            </td>
          <td id="permisUsuariPlantilla_plantillaFluxDeFirmesID_columnvalueid">
          <form:errors path="permisUsuariPlantilla.plantillaFluxDeFirmesID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <form:hidden path="permisUsuariPlantilla.plantillaFluxDeFirmesID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.permisUsuariPlantilla.plantillaFluxDeFirmesID,__theForm.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID)}" aria-label="permisUsuariPlantilla.plantillaFluxDeFirmesID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="permisUsuariPlantilla.plantillaFluxDeFirmesID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePlantillaFluxDeFirmesID == 'function') {  onChangePlantillaFluxDeFirmesID(this); };">


            <c:forEach items="${__theForm.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID}" var="tmp">
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
        
