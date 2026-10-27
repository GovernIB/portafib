<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RevisorDeFirmaFields" className="es.caib.portafib.model.fields.RevisorDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,RevisorDeFirmaFields.USUARIENTITATID)}">
        <tr id="revisorDeFirma_usuariEntitatID_rowid">
          <td id="revisorDeFirma_usuariEntitatID_columnlabelid">
            <label for="revisorDeFirma.usuariEntitatID">
              <fmt:message key="${(empty __theForm.labels[RevisorDeFirmaFields.USUARIENTITATID])?'revisorDeFirma.usuariEntitatID':__theForm.labels[RevisorDeFirmaFields.USUARIENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[RevisorDeFirmaFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RevisorDeFirmaFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="revisorDeFirma_usuariEntitatID_columnvalueid">
          <form:errors path="revisorDeFirma.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.USUARIENTITATID)}" >
          <form:hidden path="revisorDeFirma.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.revisorDeFirma.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}" aria-label="revisorDeFirma.usuariEntitatID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="revisorDeFirma.usuariEntitatID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };">


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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RevisorDeFirmaFields.FIRMAID)}">
        <tr id="revisorDeFirma_firmaID_rowid">
          <td id="revisorDeFirma_firmaID_columnlabelid">
            <label for="revisorDeFirma.firmaID">
              <fmt:message key="${(empty __theForm.labels[RevisorDeFirmaFields.FIRMAID])?'revisorDeFirma.firmaID':__theForm.labels[RevisorDeFirmaFields.FIRMAID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[RevisorDeFirmaFields.FIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RevisorDeFirmaFields.FIRMAID]}" ></i>
              </c:if>
            </td>
          <td id="revisorDeFirma_firmaID_columnvalueid">
          <form:errors path="revisorDeFirma.firmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.FIRMAID)}" >
          <form:hidden path="revisorDeFirma.firmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.revisorDeFirma.firmaID,__theForm.listOfFirmaForFirmaID)}" aria-label="revisorDeFirma.firmaID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.FIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="revisorDeFirma.firmaID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeFirmaID == 'function') {  onChangeFirmaID(this); };">


            <c:forEach items="${__theForm.listOfFirmaForFirmaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RevisorDeFirmaFields.OBLIGATORI)}">
        <tr id="revisorDeFirma_obligatori_rowid">
          <td id="revisorDeFirma_obligatori_columnlabelid">
            <label for="revisorDeFirma.obligatori">
              <fmt:message key="${(empty __theForm.labels[RevisorDeFirmaFields.OBLIGATORI])?'revisorDeFirma.obligatori':__theForm.labels[RevisorDeFirmaFields.OBLIGATORI]}" />
             </label>
              <c:if test="${not empty __theForm.help[RevisorDeFirmaFields.OBLIGATORI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RevisorDeFirmaFields.OBLIGATORI]}" ></i>
              </c:if>
            </td>
          <td id="revisorDeFirma_obligatori_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.OBLIGATORI)}" >
              <form:errors  path="revisorDeFirma.obligatori" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.OBLIGATORI)? 'false' : 'true'}" id="revisorDeFirma.obligatori" path="revisorDeFirma.obligatori"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.OBLIGATORI)}" >
                <fmt:message key="genapp.checkbox.${__theForm.revisorDeFirma.obligatori}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
