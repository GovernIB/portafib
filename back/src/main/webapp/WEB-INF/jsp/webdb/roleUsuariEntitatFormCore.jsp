<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RoleUsuariEntitatFields" className="es.caib.portafib.model.fields.RoleUsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleUsuariEntitatFields.ROLEID)}">
        <tr id="roleUsuariEntitat_roleID_rowid">
          <td id="roleUsuariEntitat_roleID_columnlabelid">
            <label for="roleUsuariEntitat.roleID">
              <fmt:message key="${(empty __theForm.labels[RoleUsuariEntitatFields.ROLEID])?'roleUsuariEntitat.roleID':__theForm.labels[RoleUsuariEntitatFields.ROLEID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[RoleUsuariEntitatFields.ROLEID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RoleUsuariEntitatFields.ROLEID]}" ></i>
              </c:if>
            </td>
          <td id="roleUsuariEntitat_roleID_columnvalueid">
          <form:errors path="roleUsuariEntitat.roleID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RoleUsuariEntitatFields.ROLEID)}" >
          <form:hidden path="roleUsuariEntitat.roleID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.roleUsuariEntitat.roleID,__theForm.listOfRoleForRoleID)}" aria-label="roleUsuariEntitat.roleID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RoleUsuariEntitatFields.ROLEID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="roleUsuariEntitat.roleID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeRoleID == 'function') {  onChangeRoleID(this); };">


            <c:forEach items="${__theForm.listOfRoleForRoleID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleUsuariEntitatFields.USUARIENTITATID)}">
        <tr id="roleUsuariEntitat_usuariEntitatID_rowid">
          <td id="roleUsuariEntitat_usuariEntitatID_columnlabelid">
            <label for="roleUsuariEntitat.usuariEntitatID">
              <fmt:message key="${(empty __theForm.labels[RoleUsuariEntitatFields.USUARIENTITATID])?'roleUsuariEntitat.usuariEntitatID':__theForm.labels[RoleUsuariEntitatFields.USUARIENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[RoleUsuariEntitatFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RoleUsuariEntitatFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="roleUsuariEntitat_usuariEntitatID_columnvalueid">
          <form:errors path="roleUsuariEntitat.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RoleUsuariEntitatFields.USUARIENTITATID)}" >
          <form:hidden path="roleUsuariEntitat.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.roleUsuariEntitat.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}" aria-label="roleUsuariEntitat.usuariEntitatID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RoleUsuariEntitatFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="roleUsuariEntitat.usuariEntitatID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };">


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
        
