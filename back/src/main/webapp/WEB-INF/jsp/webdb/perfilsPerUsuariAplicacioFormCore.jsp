<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PerfilsPerUsuariAplicacioFields" className="es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID)}">
        <tr id="perfilsPerUsuariAplicacio_perfilDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID])?'perfilsPerUsuariAplicacio.perfilDeFirmaID':__theForm.labels[PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="perfilsPerUsuariAplicacio.perfilDeFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID)}" >
          <form:hidden path="perfilsPerUsuariAplicacio.perfilDeFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.perfilsPerUsuariAplicacio.perfilDeFirmaID,__theForm.listOfPerfilDeFirmaForPerfilDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="perfilsPerUsuariAplicacio_perfilDeFirmaID"  onchange="if(typeof onChangePerfilDeFirmaID == 'function') {  onChangePerfilDeFirmaID(this); };"  cssClass="form-control col-md-9-optional" path="perfilsPerUsuariAplicacio.perfilDeFirmaID">
            <c:forEach items="${__theForm.listOfPerfilDeFirmaForPerfilDeFirmaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID)}">
        <tr id="perfilsPerUsuariAplicacio_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID])?'perfilsPerUsuariAplicacio.usuariAplicacioID':__theForm.labels[PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="perfilsPerUsuariAplicacio.usuariAplicacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID)}" >
          <form:hidden path="perfilsPerUsuariAplicacio.usuariAplicacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.perfilsPerUsuariAplicacio.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="perfilsPerUsuariAplicacio_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="form-control col-md-9-optional" path="perfilsPerUsuariAplicacio.usuariAplicacioID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
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
        
