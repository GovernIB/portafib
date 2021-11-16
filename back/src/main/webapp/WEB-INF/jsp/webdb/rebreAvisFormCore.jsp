<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RebreAvisFields" className="es.caib.portafib.model.fields.RebreAvisFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,RebreAvisFields.USUARIENTITATID)}">
        <tr id="rebreAvis_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RebreAvisFields.USUARIENTITATID])?'rebreAvis.usuariEntitatID':__theForm.labels[RebreAvisFields.USUARIENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RebreAvisFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RebreAvisFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="rebreAvis.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RebreAvisFields.USUARIENTITATID)}" >
          <form:hidden path="rebreAvis.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.rebreAvis.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RebreAvisFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="rebreAvis_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="rebreAvis.usuariEntitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RebreAvisFields.TIPUSNOTIFICACIOID)}">
        <tr id="rebreAvis_tipusNotificacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RebreAvisFields.TIPUSNOTIFICACIOID])?'rebreAvis.tipusNotificacioID':__theForm.labels[RebreAvisFields.TIPUSNOTIFICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RebreAvisFields.TIPUSNOTIFICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RebreAvisFields.TIPUSNOTIFICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="rebreAvis.tipusNotificacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RebreAvisFields.TIPUSNOTIFICACIOID)}" >
          <form:hidden path="rebreAvis.tipusNotificacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.rebreAvis.tipusNotificacioID,__theForm.listOfTipusNotificacioForTipusNotificacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RebreAvisFields.TIPUSNOTIFICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="rebreAvis_tipusNotificacioID"  onchange="if(typeof onChangeTipusNotificacioID == 'function') {  onChangeTipusNotificacioID(this); };"  cssClass="form-control col-md-9-optional" path="rebreAvis.tipusNotificacioID">
            <c:forEach items="${__theForm.listOfTipusNotificacioForTipusNotificacioID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RebreAvisFields.REBREAGRUPAT)}">
        <tr id="rebreAvis_rebreAgrupat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RebreAvisFields.REBREAGRUPAT])?'rebreAvis.rebreAgrupat':__theForm.labels[RebreAvisFields.REBREAGRUPAT]}" />
              <c:if test="${not empty __theForm.help[RebreAvisFields.REBREAGRUPAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RebreAvisFields.REBREAGRUPAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RebreAvisFields.REBREAGRUPAT)}" >
              <form:errors path="rebreAvis.rebreAgrupat" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,RebreAvisFields.REBREAGRUPAT)? 'false' : 'true'}" path="rebreAvis.rebreAgrupat" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RebreAvisFields.REBREAGRUPAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.rebreAvis.rebreAgrupat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
