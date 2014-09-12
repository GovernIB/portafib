<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GrupEntitatUsuariEntitatFields" className="es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupEntitatUsuariEntitatFields.USUARIENTITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupEntitatUsuariEntitatFields.USUARIENTITATID])?'grupEntitatUsuariEntitat.usuariEntitatID':__theForm.labels[GrupEntitatUsuariEntitatFields.USUARIENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GrupEntitatUsuariEntitatFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[GrupEntitatUsuariEntitatFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="grupEntitatUsuariEntitat.usuariEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.USUARIENTITATID)}" >
          <form:hidden path="grupEntitatUsuariEntitat.usuariEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.grupEntitatUsuariEntitat.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.USUARIENTITATID)}" >
          <form:select id="grupEntitatUsuariEntitat_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="input-xxlarge" path="grupEntitatUsuariEntitat.usuariEntitatID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupEntitatUsuariEntitatFields.GRUPENTITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupEntitatUsuariEntitatFields.GRUPENTITATID])?'grupEntitatUsuariEntitat.grupEntitatID':__theForm.labels[GrupEntitatUsuariEntitatFields.GRUPENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GrupEntitatUsuariEntitatFields.GRUPENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[GrupEntitatUsuariEntitatFields.GRUPENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="grupEntitatUsuariEntitat.grupEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.GRUPENTITATID)}" >
          <form:hidden path="grupEntitatUsuariEntitat.grupEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.grupEntitatUsuariEntitat.grupEntitatID,__theForm.listOfGrupEntitatForGrupEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GrupEntitatUsuariEntitatFields.GRUPENTITATID)}" >
          <form:select id="grupEntitatUsuariEntitat_grupEntitatID"  onchange="if(typeof onChangeGrupEntitatID == 'function') {  onChangeGrupEntitatID(this); };"  cssClass="input-xxlarge" path="grupEntitatUsuariEntitat.grupEntitatID">
            <c:forEach items="${__theForm.listOfGrupEntitatForGrupEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
