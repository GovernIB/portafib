<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PermisUsuariPlantillaFields" className="es.caib.portafib.model.fields.PermisUsuariPlantillaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PermisUsuariPlantillaFields.USUARIENTITATID)}">
        <tr id="permisUsuariPlantilla_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PermisUsuariPlantillaFields.USUARIENTITATID])?'permisUsuariPlantilla.usuariEntitatID':__theForm.labels[PermisUsuariPlantillaFields.USUARIENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PermisUsuariPlantillaFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PermisUsuariPlantillaFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="permisUsuariPlantilla.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.USUARIENTITATID)}" >
          <form:hidden path="permisUsuariPlantilla.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.permisUsuariPlantilla.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="permisUsuariPlantilla_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="permisUsuariPlantilla.usuariEntitatID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID])?'permisUsuariPlantilla.plantillaFluxDeFirmesID':__theForm.labels[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="permisUsuariPlantilla.plantillaFluxDeFirmesID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <form:hidden path="permisUsuariPlantilla.plantillaFluxDeFirmesID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.permisUsuariPlantilla.plantillaFluxDeFirmesID,__theForm.listOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="permisUsuariPlantilla_plantillaFluxDeFirmesID"  onchange="if(typeof onChangePlantillaFluxDeFirmesID == 'function') {  onChangePlantillaFluxDeFirmesID(this); };"  cssClass="form-control col-md-9-optional" path="permisUsuariPlantilla.plantillaFluxDeFirmesID">
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
        
