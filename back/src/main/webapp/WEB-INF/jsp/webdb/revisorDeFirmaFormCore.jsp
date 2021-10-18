<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RevisorDeFirmaFields" className="es.caib.portafib.model.fields.RevisorDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,RevisorDeFirmaFields.USUARIENTITATID)}">
        <tr id="revisorDeFirma_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RevisorDeFirmaFields.USUARIENTITATID])?'revisorDeFirma.usuariEntitatID':__theForm.labels[RevisorDeFirmaFields.USUARIENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RevisorDeFirmaFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RevisorDeFirmaFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="revisorDeFirma.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.USUARIENTITATID)}" >
          <form:hidden path="revisorDeFirma.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.revisorDeFirma.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="revisorDeFirma_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="form-control col-md-8" path="revisorDeFirma.usuariEntitatID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RevisorDeFirmaFields.FIRMAID])?'revisorDeFirma.firmaID':__theForm.labels[RevisorDeFirmaFields.FIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RevisorDeFirmaFields.FIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RevisorDeFirmaFields.FIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="revisorDeFirma.firmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.FIRMAID)}" >
          <form:hidden path="revisorDeFirma.firmaID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.revisorDeFirma.firmaID,__theForm.listOfFirmaForFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.FIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="revisorDeFirma_firmaID"  onchange="if(typeof onChangeFirmaID == 'function') {  onChangeFirmaID(this); };"  cssClass="form-control col-md-8" path="revisorDeFirma.firmaID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RevisorDeFirmaFields.OBLIGATORI])?'revisorDeFirma.obligatori':__theForm.labels[RevisorDeFirmaFields.OBLIGATORI]}" />
              <c:if test="${not empty __theForm.help[RevisorDeFirmaFields.OBLIGATORI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RevisorDeFirmaFields.OBLIGATORI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.OBLIGATORI)}" >
              <form:errors path="revisorDeFirma.obligatori" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="form-control" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.OBLIGATORI)? 'false' : 'true'}" path="revisorDeFirma.obligatori"  style="width:1%"/>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.OBLIGATORI)}" >
                <fmt:message key="genapp.checkbox.${__theForm.revisorDeFirma.obligatori}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
