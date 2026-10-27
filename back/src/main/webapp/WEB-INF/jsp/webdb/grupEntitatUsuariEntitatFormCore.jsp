<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GrupEntitatUsuariEntitatFields" className="es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupEntitatUsuariEntitatFields.USUARIENTITATID)}">
        <tr id="grupEntitatUsuariEntitat_usuariEntitatID_rowid">
          <td id="grupEntitatUsuariEntitat_usuariEntitatID_columnlabelid">
            <label for="grupEntitatUsuariEntitat.usuariEntitatID">
              <fmt:message key="${(empty __theForm.labels[GrupEntitatUsuariEntitatFields.USUARIENTITATID])?'grupEntitatUsuariEntitat.usuariEntitatID':__theForm.labels[GrupEntitatUsuariEntitatFields.USUARIENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[GrupEntitatUsuariEntitatFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GrupEntitatUsuariEntitatFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="grupEntitatUsuariEntitat_usuariEntitatID_columnvalueid">
          <form:errors path="grupEntitatUsuariEntitat.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.USUARIENTITATID)}" >
          <form:hidden path="grupEntitatUsuariEntitat.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.grupEntitatUsuariEntitat.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}" aria-label="grupEntitatUsuariEntitat.usuariEntitatID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="grupEntitatUsuariEntitat.usuariEntitatID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };">


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
            <label for="grupEntitatUsuariEntitat.grupEntitatID">
              <fmt:message key="${(empty __theForm.labels[GrupEntitatUsuariEntitatFields.GRUPENTITATID])?'grupEntitatUsuariEntitat.grupEntitatID':__theForm.labels[GrupEntitatUsuariEntitatFields.GRUPENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[GrupEntitatUsuariEntitatFields.GRUPENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GrupEntitatUsuariEntitatFields.GRUPENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="grupEntitatUsuariEntitat_grupEntitatID_columnvalueid">
          <form:errors path="grupEntitatUsuariEntitat.grupEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.GRUPENTITATID)}" >
          <form:hidden path="grupEntitatUsuariEntitat.grupEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.grupEntitatUsuariEntitat.grupEntitatID,__theForm.listOfGrupEntitatForGrupEntitatID)}" aria-label="grupEntitatUsuariEntitat.grupEntitatID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.GRUPENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="grupEntitatUsuariEntitat.grupEntitatID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeGrupEntitatID == 'function') {  onChangeGrupEntitatID(this); };">


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
        
