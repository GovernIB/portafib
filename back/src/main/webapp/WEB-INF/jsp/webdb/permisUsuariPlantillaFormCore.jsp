<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PermisUsuariPlantillaFields" className="es.caib.portafib.model.fields.PermisUsuariPlantillaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PermisUsuariPlantillaFields.USUARIENTITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PermisUsuariPlantillaFields.USUARIENTITATID])?'permisUsuariPlantilla.usuariEntitatID':__theForm.labels[PermisUsuariPlantillaFields.USUARIENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PermisUsuariPlantillaFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[PermisUsuariPlantillaFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="permisUsuariPlantilla.usuariEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.USUARIENTITATID)}" >
          <form:hidden path="permisUsuariPlantilla.usuariEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.permisUsuariPlantilla.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.USUARIENTITATID)}" >
          <form:select id="permisUsuariPlantilla_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="input-xxlarge" path="permisUsuariPlantilla.usuariEntitatID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID])?'permisUsuariPlantilla.plantillaFluxDeFirmesID':__theForm.labels[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID]}">
              <i class="icon-info-sign" title="${__theForm.help[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="permisUsuariPlantilla.plantillaFluxDeFirmesID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <form:hidden path="permisUsuariPlantilla.plantillaFluxDeFirmesID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.permisUsuariPlantilla.plantillaFluxDeFirmesID,__theForm.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <form:select id="permisUsuariPlantilla_plantillaFluxDeFirmesID"  onchange="if(typeof onChangePlantillaFluxDeFirmesID == 'function') {  onChangePlantillaFluxDeFirmesID(this); };"  cssClass="input-xxlarge" path="permisUsuariPlantilla.plantillaFluxDeFirmesID">
            <c:forEach items="${__theForm.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
