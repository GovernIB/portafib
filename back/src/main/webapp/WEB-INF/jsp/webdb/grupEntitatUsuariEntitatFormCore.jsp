<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GrupEntitatUsuariEntitatFields" className="es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupEntitatUsuariEntitatFields.USUARIENTITATID)}">
        <tr id="grupEntitatUsuariEntitat_usuariEntitatID_rowid">
          <td id="grupEntitatUsuariEntitat_usuariEntitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupEntitatUsuariEntitatFields.USUARIENTITATID])?'grupEntitatUsuariEntitat.usuariEntitatID':__theForm.labels[GrupEntitatUsuariEntitatFields.USUARIENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[GrupEntitatUsuariEntitatFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GrupEntitatUsuariEntitatFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="grupEntitatUsuariEntitat_usuariEntitatID_columnvalueid">
          <form:errors path="grupEntitatUsuariEntitat.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.USUARIENTITATID)}" >
          <form:hidden path="grupEntitatUsuariEntitat.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.grupEntitatUsuariEntitat.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="grupEntitatUsuariEntitat_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="grupEntitatUsuariEntitat.usuariEntitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupEntitatUsuariEntitatFields.GRUPENTITATID)}">
        <tr id="grupEntitatUsuariEntitat_grupEntitatID_rowid">
          <td id="grupEntitatUsuariEntitat_grupEntitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupEntitatUsuariEntitatFields.GRUPENTITATID])?'grupEntitatUsuariEntitat.grupEntitatID':__theForm.labels[GrupEntitatUsuariEntitatFields.GRUPENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[GrupEntitatUsuariEntitatFields.GRUPENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GrupEntitatUsuariEntitatFields.GRUPENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="grupEntitatUsuariEntitat_grupEntitatID_columnvalueid">
          <form:errors path="grupEntitatUsuariEntitat.grupEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.GRUPENTITATID)}" >
          <form:hidden path="grupEntitatUsuariEntitat.grupEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.grupEntitatUsuariEntitat.grupEntitatID,__theForm.listOfGrupEntitatForGrupEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.GRUPENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="grupEntitatUsuariEntitat_grupEntitatID"  onchange="if(typeof onChangeGrupEntitatID == 'function') {  onChangeGrupEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="grupEntitatUsuariEntitat.grupEntitatID">
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
        
