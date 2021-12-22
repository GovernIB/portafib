<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariPersonaFields" className="es.caib.portafib.model.fields.UsuariPersonaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.USUARIPERSONAID)}">
        <tr id="usuariPersona_usuariPersonaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.USUARIPERSONAID])?'usuariPersona.usuariPersonaID':__theForm.labels[UsuariPersonaFields.USUARIPERSONAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.USUARIPERSONAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariPersonaFields.USUARIPERSONAID]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariPersona.usuariPersonaID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.USUARIPERSONAID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.USUARIPERSONAID)? ' uneditable-input' : ''}"  style="" maxlength="50" path="usuariPersona.usuariPersonaID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.NOM)}">
        <tr id="usuariPersona_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.NOM])?'usuariPersona.nom':__theForm.labels[UsuariPersonaFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariPersonaFields.NOM]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariPersona.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="50" path="usuariPersona.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.LLINATGES)}">
        <tr id="usuariPersona_llinatges_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.LLINATGES])?'usuariPersona.llinatges':__theForm.labels[UsuariPersonaFields.LLINATGES]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.LLINATGES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariPersonaFields.LLINATGES]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariPersona.llinatges" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.LLINATGES)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.LLINATGES)? ' uneditable-input' : ''}"  style="" maxlength="100" path="usuariPersona.llinatges"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.EMAIL)}">
        <tr id="usuariPersona_email_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.EMAIL])?'usuariPersona.email':__theForm.labels[UsuariPersonaFields.EMAIL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.EMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariPersonaFields.EMAIL]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariPersona.email" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.EMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.EMAIL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="usuariPersona.email"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.NIF)}">
        <tr id="usuariPersona_nif_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.NIF])?'usuariPersona.nif':__theForm.labels[UsuariPersonaFields.NIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariPersonaFields.NIF]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariPersona.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.NIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="9" path="usuariPersona.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.IDIOMAID)}">
        <tr id="usuariPersona_idiomaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.IDIOMAID])?'usuariPersona.idiomaID':__theForm.labels[UsuariPersonaFields.IDIOMAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.IDIOMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariPersonaFields.IDIOMAID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariPersona.idiomaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.IDIOMAID)}" >
          <form:hidden path="usuariPersona.idiomaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariPersona.idiomaID,__theForm.listOfIdiomaForIdiomaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.IDIOMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariPersona_idiomaID"  onchange="if(typeof onChangeIdiomaID == 'function') {  onChangeIdiomaID(this); };"  cssClass="form-control col-md-9-optional" path="usuariPersona.idiomaID">
            <c:forEach items="${__theForm.listOfIdiomaForIdiomaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.RUBRICAID)}">
        <tr id="usuariPersona_rubricaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.RUBRICAID])?'usuariPersona.rubricaID':__theForm.labels[UsuariPersonaFields.RUBRICAID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.RUBRICAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariPersonaFields.RUBRICAID]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="usuariPersona.rubricaID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.RUBRICAID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(rubricaID.rubricaID)}"/>">${rubricaID.rubricaID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.RUBRICAID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.RUBRICAID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.RUBRICAID)? ' uneditable-input' : ''}"   path="rubricaID" type="file" />
                  <label class="custom-file-label" for="rubricaID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.usuariPersona.rubrica}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.usuariPersona.rubrica)}"/>">${__theForm.usuariPersona.rubrica.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="rubricaIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="rubricaID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#rubricaID').on('change', function(){
						var ruta = $('#rubricaID').val(); 
						var rutaArray = ruta.split('\\');
						$('#rubricaID-custom-file-label').css('display','block');
						$('#rubricaID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.USUARIINTERN)}">
        <tr id="usuariPersona_usuariIntern_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.USUARIINTERN])?'usuariPersona.usuariIntern':__theForm.labels[UsuariPersonaFields.USUARIINTERN]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.USUARIINTERN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariPersonaFields.USUARIINTERN]}" ></i>
              </c:if>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.USUARIINTERN)}" >
              <form:errors path="usuariPersona.usuariIntern" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.USUARIINTERN)? 'false' : 'true'}" path="usuariPersona.usuariIntern" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.USUARIINTERN)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariPersona.usuariIntern}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.CONTRASENYA)}">
        <tr id="usuariPersona_contrasenya_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.CONTRASENYA])?'usuariPersona.contrasenya':__theForm.labels[UsuariPersonaFields.CONTRASENYA]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.CONTRASENYA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariPersonaFields.CONTRASENYA]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariPersona.contrasenya" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.CONTRASENYA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.CONTRASENYA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuariPersona.contrasenya"   />

           </td>
        </tr>
        </c:if>
        
