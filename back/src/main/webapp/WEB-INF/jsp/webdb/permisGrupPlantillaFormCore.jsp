<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PermisGrupPlantillaFields" className="es.caib.portafib.model.fields.PermisGrupPlantillaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PermisGrupPlantillaFields.GRUPENTITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PermisGrupPlantillaFields.GRUPENTITATID])?'permisGrupPlantilla.grupEntitatID':__theForm.labels[PermisGrupPlantillaFields.GRUPENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PermisGrupPlantillaFields.GRUPENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[PermisGrupPlantillaFields.GRUPENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="permisGrupPlantilla.grupEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.GRUPENTITATID)}" >
          <form:hidden path="permisGrupPlantilla.grupEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.permisGrupPlantilla.grupEntitatID,__theForm.listOfGrupEntitatForGrupEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.GRUPENTITATID)}" >
          <form:select id="permisGrupPlantilla_grupEntitatID"  onchange="if(typeof onChangeGrupEntitatID == 'function') {  onChangeGrupEntitatID(this); };"  cssClass="input-xxlarge" path="permisGrupPlantilla.grupEntitatID">
            <c:forEach items="${__theForm.listOfGrupEntitatForGrupEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID])?'permisGrupPlantilla.plantillaFluxDeFirmesID':__theForm.labels[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID]}">
              <i class="icon-info-sign" title="${__theForm.help[PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="permisGrupPlantilla.plantillaFluxDeFirmesID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <form:hidden path="permisGrupPlantilla.plantillaFluxDeFirmesID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.permisGrupPlantilla.plantillaFluxDeFirmesID,__theForm.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <form:select id="permisGrupPlantilla_plantillaFluxDeFirmesID"  onchange="if(typeof onChangePlantillaFluxDeFirmesID == 'function') {  onChangePlantillaFluxDeFirmesID(this); };"  cssClass="input-xxlarge" path="permisGrupPlantilla.plantillaFluxDeFirmesID">
            <c:forEach items="${__theForm.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
