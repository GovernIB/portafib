<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFields" className="es.caib.portafib.model.fields.UsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.USUARIENTITATID)}">
        <tr id="usuariEntitat_usuariEntitatID_rowid">
          <td id="usuariEntitat_usuariEntitatID_columnlabelid">
            <label for="usuariEntitat.usuariEntitatID">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.USUARIENTITATID])?'usuariEntitat.usuariEntitatID':__theForm.labels[UsuariEntitatFields.USUARIENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_usuariEntitatID_columnvalueid">
            <form:errors path="usuariEntitat.usuariEntitatID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIENTITATID)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIENTITATID)? ' uneditable-input' : ''}"  style="" maxlength="101" path="usuariEntitat.usuariEntitatID"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.CARREC)}">
        <tr id="usuariEntitat_carrec_rowid">
          <td id="usuariEntitat_carrec_columnlabelid">
            <label for="usuariEntitat.carrec">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.CARREC])?'usuariEntitat.carrec':__theForm.labels[UsuariEntitatFields.CARREC]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.CARREC]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.CARREC]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_carrec_columnvalueid">
            <form:errors path="usuariEntitat.carrec" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.CARREC)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.CARREC)? ' uneditable-input' : ''}"  style="" maxlength="150" path="usuariEntitat.carrec"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.USUARIPERSONAID)}">
        <tr id="usuariEntitat_usuariPersonaID_rowid">
          <td id="usuariEntitat_usuariPersonaID_columnlabelid">
            <label for="usuariEntitat.usuariPersonaID">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.USUARIPERSONAID])?'usuariEntitat.usuariPersonaID':__theForm.labels[UsuariEntitatFields.USUARIPERSONAID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.USUARIPERSONAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.USUARIPERSONAID]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_usuariPersonaID_columnvalueid">
          <form:errors path="usuariEntitat.usuariPersonaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIPERSONAID)}" >
          <form:hidden path="usuariEntitat.usuariPersonaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.usuariPersonaID,__theForm.listOfUsuariPersonaForUsuariPersonaID)}" aria-label="usuariEntitat.usuariPersonaID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIPERSONAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariEntitat.usuariPersonaID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeUsuariPersonaID == 'function') {  onChangeUsuariPersonaID(this); };">


            <c:forEach items="${__theForm.listOfUsuariPersonaForUsuariPersonaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.ENTITATID)}">
        <tr id="usuariEntitat_entitatID_rowid">
          <td id="usuariEntitat_entitatID_columnlabelid">
            <label for="usuariEntitat.entitatID">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.ENTITATID])?'usuariEntitat.entitatID':__theForm.labels[UsuariEntitatFields.ENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_entitatID_columnvalueid">
          <form:errors path="usuariEntitat.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <form:hidden path="usuariEntitat.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.entitatID,__theForm.listOfEntitatForEntitatID)}" aria-label="usuariEntitat.entitatID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariEntitat.entitatID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };">


            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.ACTIU)}">
        <tr id="usuariEntitat_actiu_rowid">
          <td id="usuariEntitat_actiu_columnlabelid">
            <label for="usuariEntitat.actiu">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.ACTIU])?'usuariEntitat.actiu':__theForm.labels[UsuariEntitatFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.ACTIU]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_actiu_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)}" >
              <form:errors  path="usuariEntitat.actiu" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)? 'false' : 'true'}" id="usuariEntitat.actiu" path="usuariEntitat.actiu"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariEntitat.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.EMAIL)}">
        <tr id="usuariEntitat_email_rowid">
          <td id="usuariEntitat_email_columnlabelid">
            <label for="usuariEntitat.email">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.EMAIL])?'usuariEntitat.email':__theForm.labels[UsuariEntitatFields.EMAIL]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.EMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.EMAIL]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_email_columnvalueid">
            <form:errors path="usuariEntitat.email" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.EMAIL)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.EMAIL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="usuariEntitat.email"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.LOGOSEGELLID)}">
        <tr id="usuariEntitat_logoSegellID_rowid">
          <td id="usuariEntitat_logoSegellID_columnlabelid">
            <label for="usuariEntitat.logoSegellID">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.LOGOSEGELLID])?'usuariEntitat.logoSegellID':__theForm.labels[UsuariEntitatFields.LOGOSEGELLID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.LOGOSEGELLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.LOGOSEGELLID]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_logoSegellID_columnvalueid">
              <form:errors path="usuariEntitat.logoSegellID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.LOGOSEGELLID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.usuariEntitat.logoSegell)}"/>">${__theForm.usuariEntitat.logoSegell.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.LOGOSEGELLID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <input id="usuariEntitat.logoSegellID" name="logoSegellID" class="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.LOGOSEGELLID)? ' uneditable-input' : ''}"  type="file"  />
                  <span id="logoSegellID-custom-file-label2" class="custom-file-label" >
                    <c:if test="${ empty __theForm.usuariEntitat.logoSegell}">
                    <fmt:message key="genapp.form.file.select"/>
                    </c:if>
                  </span>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.usuariEntitat.logoSegell}">
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logoSegellID-custom-file-label">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.usuariEntitat.logoSegell)}"/>">${__theForm.usuariEntitat.logoSegell.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" >
                        <form:checkbox id="logoSegellIDDelete" path="logoSegellIDDelete"/>
                        <label style="margin-bottom:0px" for="logoSegellIDDelete">
                        <small style="color:#000">&nbsp;<fmt:message key="genapp.form.file.delete"/></small>
                        </label>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logoSegellID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                </c:otherwise>
                </c:choose>
                <script>
					$('#usuariEntitat\\.logoSegellID').on('change', function(){
						var ruta = $('#usuariEntitat\\.logoSegellID').val(); 
						var rutaArray = ruta.split('\\');
						$('#logoSegellID-custom-file-label').css('display','block');
						$('#logoSegellID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
                       $('#logoSegellID-custom-file-label2').html(rutaArray[rutaArray.length - 1]);
                   });
				</script>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.PREDETERMINAT)}">
        <tr id="usuariEntitat_predeterminat_rowid">
          <td id="usuariEntitat_predeterminat_columnlabelid">
            <label for="usuariEntitat.predeterminat">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.PREDETERMINAT])?'usuariEntitat.predeterminat':__theForm.labels[UsuariEntitatFields.PREDETERMINAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.PREDETERMINAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.PREDETERMINAT]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_predeterminat_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.PREDETERMINAT)}" >
              <form:errors  path="usuariEntitat.predeterminat" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.PREDETERMINAT)? 'false' : 'true'}" id="usuariEntitat.predeterminat" path="usuariEntitat.predeterminat"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.PREDETERMINAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariEntitat.predeterminat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.REBRETOTSELSAVISOS)}">
        <tr id="usuariEntitat_rebreTotsElsAvisos_rowid">
          <td id="usuariEntitat_rebreTotsElsAvisos_columnlabelid">
            <label for="usuariEntitat.rebreTotsElsAvisos">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.REBRETOTSELSAVISOS])?'usuariEntitat.rebreTotsElsAvisos':__theForm.labels[UsuariEntitatFields.REBRETOTSELSAVISOS]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.REBRETOTSELSAVISOS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.REBRETOTSELSAVISOS]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_rebreTotsElsAvisos_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.REBRETOTSELSAVISOS)}" >
              <form:errors  path="usuariEntitat.rebreTotsElsAvisos" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.REBRETOTSELSAVISOS)? 'false' : 'true'}" id="usuariEntitat.rebreTotsElsAvisos" path="usuariEntitat.rebreTotsElsAvisos"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.REBRETOTSELSAVISOS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariEntitat.rebreTotsElsAvisos}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB)}">
        <tr id="usuariEntitat_politicaDePluginFirmaWeb_rowid">
          <td id="usuariEntitat_politicaDePluginFirmaWeb_columnlabelid">
            <label for="usuariEntitat.politicaDePluginFirmaWeb">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB])?'usuariEntitat.politicaDePluginFirmaWeb':__theForm.labels[UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_politicaDePluginFirmaWeb_columnvalueid">
          <form:errors path="usuariEntitat.politicaDePluginFirmaWeb" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB)}" >
          <form:hidden path="usuariEntitat.politicaDePluginFirmaWeb"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.politicaDePluginFirmaWeb,__theForm.listOfValuesForPoliticaDePluginFirmaWeb)}" aria-label="usuariEntitat.politicaDePluginFirmaWeb"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariEntitat.politicaDePluginFirmaWeb" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePoliticaDePluginFirmaWeb == 'function') {  onChangePoliticaDePluginFirmaWeb(this); };">


            <c:forEach items="${__theForm.listOfValuesForPoliticaDePluginFirmaWeb}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.POLITICACUSTODIA)}">
        <tr id="usuariEntitat_politicaCustodia_rowid">
          <td id="usuariEntitat_politicaCustodia_columnlabelid">
            <label for="usuariEntitat.politicaCustodia">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.POLITICACUSTODIA])?'usuariEntitat.politicaCustodia':__theForm.labels[UsuariEntitatFields.POLITICACUSTODIA]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.POLITICACUSTODIA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.POLITICACUSTODIA]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_politicaCustodia_columnvalueid">
          <form:errors path="usuariEntitat.politicaCustodia" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.POLITICACUSTODIA)}" >
          <form:hidden path="usuariEntitat.politicaCustodia"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.politicaCustodia,__theForm.listOfValuesForPoliticaCustodia)}" aria-label="usuariEntitat.politicaCustodia"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.POLITICACUSTODIA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariEntitat.politicaCustodia" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePoliticaCustodia == 'function') {  onChangePoliticaCustodia(this); };">


            <c:forEach items="${__theForm.listOfValuesForPoliticaCustodia}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.CUSTODIAINFOID)}">
        <tr id="usuariEntitat_custodiaInfoID_rowid">
          <td id="usuariEntitat_custodiaInfoID_columnlabelid">
            <label for="usuariEntitat.custodiaInfoID">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.CUSTODIAINFOID])?'usuariEntitat.custodiaInfoID':__theForm.labels[UsuariEntitatFields.CUSTODIAINFOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.CUSTODIAINFOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.CUSTODIAINFOID]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_custodiaInfoID_columnvalueid">
          <form:errors path="usuariEntitat.custodiaInfoID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.CUSTODIAINFOID)}" >
          <form:hidden path="usuariEntitat.custodiaInfoID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.custodiaInfoID,__theForm.listOfCustodiaInfoForCustodiaInfoID)}" aria-label="usuariEntitat.custodiaInfoID"   />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.CUSTODIAINFOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariEntitat.custodiaInfoID" cssClass="form-control col-md-9-optional"  onchange="if(typeof onChangeCustodiaInfoID == 'function') {  onChangeCustodiaInfoID(this); };">


            <c:forEach items="${__theForm.listOfCustodiaInfoForCustodiaInfoID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.usuariEntitat.custodiaInfoID }">
                  <option value="" selected></option>

              </c:if>
              <c:if test="${not empty __theForm.usuariEntitat.custodiaInfoID }">

                  <option value="" ></option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
