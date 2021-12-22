<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFavoritFields" className="es.caib.portafib.model.fields.UsuariEntitatFavoritFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFavoritFields.ORIGENID)}">
        <tr id="usuariEntitatFavorit_origenID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFavoritFields.ORIGENID])?'usuariEntitatFavorit.origenID':__theForm.labels[UsuariEntitatFavoritFields.ORIGENID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFavoritFields.ORIGENID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFavoritFields.ORIGENID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariEntitatFavorit.origenID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFavoritFields.ORIGENID)}" >
          <form:hidden path="usuariEntitatFavorit.origenID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitatFavorit.origenID,__theForm.listOfUsuariEntitatForOrigenID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFavoritFields.ORIGENID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariEntitatFavorit_origenID"  onchange="if(typeof onChangeOrigenID == 'function') {  onChangeOrigenID(this); };"  cssClass="form-control col-md-9-optional" path="usuariEntitatFavorit.origenID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForOrigenID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFavoritFields.FAVORITID)}">
        <tr id="usuariEntitatFavorit_favoritID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFavoritFields.FAVORITID])?'usuariEntitatFavorit.favoritID':__theForm.labels[UsuariEntitatFavoritFields.FAVORITID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFavoritFields.FAVORITID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFavoritFields.FAVORITID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariEntitatFavorit.favoritID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFavoritFields.FAVORITID)}" >
          <form:hidden path="usuariEntitatFavorit.favoritID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitatFavorit.favoritID,__theForm.listOfUsuariEntitatForFavoritID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFavoritFields.FAVORITID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariEntitatFavorit_favoritID"  onchange="if(typeof onChangeFavoritID == 'function') {  onChangeFavoritID(this); };"  cssClass="form-control col-md-9-optional" path="usuariEntitatFavorit.favoritID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForFavoritID}" var="tmp">
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
        
