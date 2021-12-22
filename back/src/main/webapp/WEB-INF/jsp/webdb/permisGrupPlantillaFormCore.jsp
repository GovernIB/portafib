<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PermisGrupPlantillaFields" className="es.caib.portafib.model.fields.PermisGrupPlantillaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PermisGrupPlantillaFields.GRUPENTITATID)}">
        <tr id="permisGrupPlantilla_grupEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PermisGrupPlantillaFields.GRUPENTITATID])?'permisGrupPlantilla.grupEntitatID':__theForm.labels[PermisGrupPlantillaFields.GRUPENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PermisGrupPlantillaFields.GRUPENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PermisGrupPlantillaFields.GRUPENTITATID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="permisGrupPlantilla.grupEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.GRUPENTITATID)}" >
          <form:hidden path="permisGrupPlantilla.grupEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.permisGrupPlantilla.grupEntitatID,__theForm.listOfGrupEntitatForGrupEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.GRUPENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="permisGrupPlantilla_grupEntitatID"  onchange="if(typeof onChangeGrupEntitatID == 'function') {  onChangeGrupEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="permisGrupPlantilla.grupEntitatID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID])?'permisGrupPlantilla.plantillaFluxDeFirmesID':__theForm.labels[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="permisGrupPlantilla.plantillaFluxDeFirmesID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <form:hidden path="permisGrupPlantilla.plantillaFluxDeFirmesID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.permisGrupPlantilla.plantillaFluxDeFirmesID,__theForm.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="permisGrupPlantilla_plantillaFluxDeFirmesID"  onchange="if(typeof onChangePlantillaFluxDeFirmesID == 'function') {  onChangePlantillaFluxDeFirmesID(this); };"  cssClass="form-control col-md-9-optional" path="permisGrupPlantilla.plantillaFluxDeFirmesID">
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
        
