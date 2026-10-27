<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusDocumentColaboracioDelegacioFields" className="es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID)}">
        <tr id="tipusDocumentColaboracioDelegacio_colaboracioDelegacioID_rowid">
          <td id="tipusDocumentColaboracioDelegacio_colaboracioDelegacioID_columnlabelid">
            <label for="tipusDocumentColaboracioDelegacio.colaboracioDelegacioID">
              <fmt:message key="${(empty __theForm.labels[TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID])?'tipusDocumentColaboracioDelegacio.colaboracioDelegacioID':__theForm.labels[TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID]}" ></i>
              </c:if>
            </td>
          <td id="tipusDocumentColaboracioDelegacio_colaboracioDelegacioID_columnvalueid">
          <form:errors path="tipusDocumentColaboracioDelegacio.colaboracioDelegacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID)}" >
          <form:hidden path="tipusDocumentColaboracioDelegacio.colaboracioDelegacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tipusDocumentColaboracioDelegacio.colaboracioDelegacioID,__theForm.listOfColaboracioDelegacioForColaboracioDelegacioID)}" aria-label="tipusDocumentColaboracioDelegacio.colaboracioDelegacioID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="tipusDocumentColaboracioDelegacio.colaboracioDelegacioID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeColaboracioDelegacioID == 'function') {  onChangeColaboracioDelegacioID(this); };">


            <c:forEach items="${__theForm.listOfColaboracioDelegacioForColaboracioDelegacioID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID)}">
        <tr id="tipusDocumentColaboracioDelegacio_tipusDocumentID_rowid">
          <td id="tipusDocumentColaboracioDelegacio_tipusDocumentID_columnlabelid">
            <label for="tipusDocumentColaboracioDelegacio.tipusDocumentID">
              <fmt:message key="${(empty __theForm.labels[TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID])?'tipusDocumentColaboracioDelegacio.tipusDocumentID':__theForm.labels[TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID]}" ></i>
              </c:if>
            </td>
          <td id="tipusDocumentColaboracioDelegacio_tipusDocumentID_columnvalueid">
          <form:errors path="tipusDocumentColaboracioDelegacio.tipusDocumentID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID)}" >
          <form:hidden path="tipusDocumentColaboracioDelegacio.tipusDocumentID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tipusDocumentColaboracioDelegacio.tipusDocumentID,__theForm.listOfTipusDocumentForTipusDocumentID)}" aria-label="tipusDocumentColaboracioDelegacio.tipusDocumentID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="tipusDocumentColaboracioDelegacio.tipusDocumentID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeTipusDocumentID == 'function') {  onChangeTipusDocumentID(this); };">


            <c:forEach items="${__theForm.listOfTipusDocumentForTipusDocumentID}" var="tmp">
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
        
