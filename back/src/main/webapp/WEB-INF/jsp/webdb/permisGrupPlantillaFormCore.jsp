<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PermisGrupPlantillaFields" className="es.caib.portafib.model.fields.PermisGrupPlantillaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PermisGrupPlantillaFields.GRUPENTITATID)}">
        <tr id="permisGrupPlantilla_grupEntitatID_rowid">
          <td id="permisGrupPlantilla_grupEntitatID_columnlabelid">
            <label for="permisGrupPlantilla.grupEntitatID">
              <fmt:message key="${(empty __theForm.labels[PermisGrupPlantillaFields.GRUPENTITATID])?'permisGrupPlantilla.grupEntitatID':__theForm.labels[PermisGrupPlantillaFields.GRUPENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PermisGrupPlantillaFields.GRUPENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PermisGrupPlantillaFields.GRUPENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="permisGrupPlantilla_grupEntitatID_columnvalueid">
          <form:errors path="permisGrupPlantilla.grupEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.GRUPENTITATID)}" >
          <form:hidden path="permisGrupPlantilla.grupEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.permisGrupPlantilla.grupEntitatID,__theForm.listOfGrupEntitatForGrupEntitatID)}" aria-label="permisGrupPlantilla.grupEntitatID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.GRUPENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="permisGrupPlantilla.grupEntitatID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeGrupEntitatID == 'function') {  onChangeGrupEntitatID(this); };">


            <c:forEach items="${__theForm.listOfGrupEntitatForGrupEntitatID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID)}">
        <tr id="permisGrupPlantilla_plantillaFluxDeFirmesID_rowid">
          <td id="permisGrupPlantilla_plantillaFluxDeFirmesID_columnlabelid">
            <label for="permisGrupPlantilla.plantillaFluxDeFirmesID">
              <fmt:message key="${(empty __theForm.labels[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID])?'permisGrupPlantilla.plantillaFluxDeFirmesID':__theForm.labels[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" ></i>
              </c:if>
            </td>
          <td id="permisGrupPlantilla_plantillaFluxDeFirmesID_columnvalueid">
          <form:errors path="permisGrupPlantilla.plantillaFluxDeFirmesID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <form:hidden path="permisGrupPlantilla.plantillaFluxDeFirmesID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.permisGrupPlantilla.plantillaFluxDeFirmesID,__theForm.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID)}" aria-label="permisGrupPlantilla.plantillaFluxDeFirmesID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="permisGrupPlantilla.plantillaFluxDeFirmesID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePlantillaFluxDeFirmesID == 'function') {  onChangePlantillaFluxDeFirmesID(this); };">


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
        
