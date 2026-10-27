<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioConfiguracioFields" className="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.NOM)}">
        <tr id="usuariAplicacioConfiguracio_nom_rowid">
          <td id="usuariAplicacioConfiguracio_nom_columnlabelid">
            <label for="usuariAplicacioConfiguracio.nom">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.NOM])?'usuariAplicacioConfiguracio.nom':__theForm.labels[UsuariAplicacioConfiguracioFields.NOM]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_nom_columnvalueid">
            <form:errors path="usuariAplicacioConfiguracio.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.NOM)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuariAplicacioConfiguracio.nom"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.ENTITATID)}">
        <tr id="usuariAplicacioConfiguracio_entitatID_rowid">
          <td id="usuariAplicacioConfiguracio_entitatID_columnlabelid">
            <label for="usuariAplicacioConfiguracio.entitatID">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.ENTITATID])?'usuariAplicacioConfiguracio.entitatID':__theForm.labels[UsuariAplicacioConfiguracioFields.ENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_entitatID_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ENTITATID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.entitatID,__theForm.listOfEntitatForEntitatID)}" aria-label="usuariAplicacioConfiguracio.entitatID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.entitatID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };">


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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaApiSimpleServidor_rowid">
          <td id="usuariAplicacioConfiguracio_usEnFirmaApiSimpleServidor_columnlabelid">
            <label for="usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR])?'usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_usEnFirmaApiSimpleServidor_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)}" >
              <form:errors  path="usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)? 'false' : 'true'}" id="usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor" path="usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaApiSimpleWeb_rowid">
          <td id="usuariAplicacioConfiguracio_usEnFirmaApiSimpleWeb_columnlabelid">
            <label for="usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB])?'usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_usEnFirmaApiSimpleWeb_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)}" >
              <form:errors  path="usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)? 'false' : 'true'}" id="usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb" path="usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaWeb_rowid">
          <td id="usuariAplicacioConfiguracio_usEnFirmaWeb_columnlabelid">
            <label for="usuariAplicacioConfiguracio.usEnFirmaWeb">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAWEB])?'usuariAplicacioConfiguracio.usEnFirmaWeb':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAWEB]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAWEB]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_usEnFirmaWeb_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)}" >
              <form:errors  path="usuariAplicacioConfiguracio.usEnFirmaWeb" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)? 'false' : 'true'}" id="usuariAplicacioConfiguracio.usEnFirmaWeb" path="usuariAplicacioConfiguracio.usEnFirmaWeb"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaWeb}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaWS1_rowid">
          <td id="usuariAplicacioConfiguracio_usEnFirmaWS1_columnlabelid">
            <label for="usuariAplicacioConfiguracio.usEnFirmaWS1">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAWS1])?'usuariAplicacioConfiguracio.usEnFirmaWS1':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAWS1]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAWS1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAWS1]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_usEnFirmaWS1_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)}" >
              <form:errors  path="usuariAplicacioConfiguracio.usEnFirmaWS1" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)? 'false' : 'true'}" id="usuariAplicacioConfiguracio.usEnFirmaWS1" path="usuariAplicacioConfiguracio.usEnFirmaWS1"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaWS1}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaAsyncRest2_rowid">
          <td id="usuariAplicacioConfiguracio_usEnFirmaAsyncRest2_columnlabelid">
            <label for="usuariAplicacioConfiguracio.usEnFirmaAsyncRest2">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2])?'usuariAplicacioConfiguracio.usEnFirmaAsyncRest2':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_usEnFirmaAsyncRest2_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)}" >
              <form:errors  path="usuariAplicacioConfiguracio.usEnFirmaAsyncRest2" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)? 'false' : 'true'}" id="usuariAplicacioConfiguracio.usEnFirmaAsyncRest2" path="usuariAplicacioConfiguracio.usEnFirmaAsyncRest2"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaAsyncRest2}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaPassarelaServidor_rowid">
          <td id="usuariAplicacioConfiguracio_usEnFirmaPassarelaServidor_columnlabelid">
            <label for="usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR])?'usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_usEnFirmaPassarelaServidor_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)}" >
              <form:errors  path="usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)? 'false' : 'true'}" id="usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor" path="usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaPassarelaWeb_rowid">
          <td id="usuariAplicacioConfiguracio_usEnFirmaPassarelaWeb_columnlabelid">
            <label for="usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB])?'usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_usEnFirmaPassarelaWeb_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)}" >
              <form:errors  path="usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)? 'false' : 'true'}" id="usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb" path="usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)}">
        <tr id="usuariAplicacioConfiguracio_filtreCertificats_rowid">
          <td id="usuariAplicacioConfiguracio_filtreCertificats_columnlabelid">
            <label for="usuariAplicacioConfiguracio.filtreCertificats">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS])?'usuariAplicacioConfiguracio.filtreCertificats':__theForm.labels[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_filtreCertificats_columnvalueid">
              <form:errors path="usuariAplicacioConfiguracio.filtreCertificats" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)? 'readonly' : ''}" path="usuariAplicacioConfiguracio.filtreCertificats"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_filtreCertificats" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_filtreCertificats" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.filtreCertificats'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.filtreCertificats'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.filtreCertificats'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
			$('#dropdownMenuButton_filtreCertificats').on('click', function(){
					var valor = ($('#dropdownMenuContainer_filtreCertificats').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_filtreCertificats').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_tipusOperacioFirma_rowid">
          <td id="usuariAplicacioConfiguracio_tipusOperacioFirma_columnlabelid">
            <label for="usuariAplicacioConfiguracio.tipusOperacioFirma">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA])?'usuariAplicacioConfiguracio.tipusOperacioFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_tipusOperacioFirma_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.tipusOperacioFirma" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.tipusOperacioFirma"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.tipusOperacioFirma,__theForm.listOfValuesForTipusOperacioFirma)}" aria-label="usuariAplicacioConfiguracio.tipusOperacioFirma"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.tipusOperacioFirma" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeTipusOperacioFirma == 'function') {  onChangeTipusOperacioFirma(this); };">


            <c:forEach items="${__theForm.listOfValuesForTipusOperacioFirma}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}">
        <tr id="usuariAplicacioConfiguracio_tipusFirmaID_rowid">
          <td id="usuariAplicacioConfiguracio_tipusFirmaID_columnlabelid">
            <label for="usuariAplicacioConfiguracio.tipusFirmaID">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID])?'usuariAplicacioConfiguracio.tipusFirmaID':__theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_tipusFirmaID_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.tipusFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.tipusFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.tipusFirmaID,__theForm.listOfValuesForTipusFirmaID)}" aria-label="usuariAplicacioConfiguracio.tipusFirmaID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.tipusFirmaID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeTipusFirmaID == 'function') {  onChangeTipusFirmaID(this); };">


            <c:forEach items="${__theForm.listOfValuesForTipusFirmaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}">
        <tr id="usuariAplicacioConfiguracio_algorismeDeFirmaID_rowid">
          <td id="usuariAplicacioConfiguracio_algorismeDeFirmaID_columnlabelid">
            <label for="usuariAplicacioConfiguracio.algorismeDeFirmaID">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID])?'usuariAplicacioConfiguracio.algorismeDeFirmaID':__theForm.labels[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_algorismeDeFirmaID_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.algorismeDeFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.algorismeDeFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.algorismeDeFirmaID,__theForm.listOfValuesForAlgorismeDeFirmaID)}" aria-label="usuariAplicacioConfiguracio.algorismeDeFirmaID"   />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.algorismeDeFirmaID" cssClass="form-control col-md-9-optional"  onchange="if(typeof onChangeAlgorismeDeFirmaID == 'function') {  onChangeAlgorismeDeFirmaID(this); };">


            <c:forEach items="${__theForm.listOfValuesForAlgorismeDeFirmaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.usuariAplicacioConfiguracio.algorismeDeFirmaID }">
                  <option value="" selected></option>

              </c:if>
              <c:if test="${not empty __theForm.usuariAplicacioConfiguracio.algorismeDeFirmaID }">

                  <option value="" ></option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_modeDeFirma_rowid">
          <td id="usuariAplicacioConfiguracio_modeDeFirma_columnlabelid">
            <label for="usuariAplicacioConfiguracio.modeDeFirma">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.MODEDEFIRMA])?'usuariAplicacioConfiguracio.modeDeFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_modeDeFirma_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.modeDeFirma" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.modeDeFirma"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.modeDeFirma,__theForm.listOfValuesForModeDeFirma)}" aria-label="usuariAplicacioConfiguracio.modeDeFirma"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.modeDeFirma" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeModeDeFirma == 'function') {  onChangeModeDeFirma(this); };">


            <c:forEach items="${__theForm.listOfValuesForModeDeFirma}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_usPoliticaDeFirma_rowid">
          <td id="usuariAplicacioConfiguracio_usPoliticaDeFirma_columnlabelid">
            <label for="usuariAplicacioConfiguracio.usPoliticaDeFirma">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA])?'usuariAplicacioConfiguracio.usPoliticaDeFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_usPoliticaDeFirma_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.usPoliticaDeFirma" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.usPoliticaDeFirma"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.usPoliticaDeFirma,__theForm.listOfValuesForUsPoliticaDeFirma)}" aria-label="usuariAplicacioConfiguracio.usPoliticaDeFirma"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.usPoliticaDeFirma" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeUsPoliticaDeFirma == 'function') {  onChangeUsPoliticaDeFirma(this); };">


            <c:forEach items="${__theForm.listOfValuesForUsPoliticaDeFirma}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifier_rowid">
          <td id="usuariAplicacioConfiguracio_policyIdentifier_columnlabelid">
            <label for="usuariAplicacioConfiguracio.policyIdentifier">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER])?'usuariAplicacioConfiguracio.policyIdentifier':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_policyIdentifier_columnvalueid">
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifier" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)? ' uneditable-input' : ''}"  style="" maxlength="100" path="usuariAplicacioConfiguracio.policyIdentifier"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifierHash_rowid">
          <td id="usuariAplicacioConfiguracio_policyIdentifierHash_columnlabelid">
            <label for="usuariAplicacioConfiguracio.policyIdentifierHash">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH])?'usuariAplicacioConfiguracio.policyIdentifierHash':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_policyIdentifierHash_columnvalueid">
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifierHash" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)? ' uneditable-input' : ''}"  style="" maxlength="256" path="usuariAplicacioConfiguracio.policyIdentifierHash"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifierHashAlgorithm_rowid">
          <td id="usuariAplicacioConfiguracio_policyIdentifierHashAlgorithm_columnlabelid">
            <label for="usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM])?'usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_policyIdentifierHashAlgorithm_columnvalueid">
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)? 'readonly' : ''}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)? ' uneditable-input' : ''}"  style="" maxlength="50" path="usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
        <tr id="usuariAplicacioConfiguracio_policyUrlDocument_rowid">
          <td id="usuariAplicacioConfiguracio_policyUrlDocument_columnlabelid">
            <label for="usuariAplicacioConfiguracio.policyUrlDocument">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT])?'usuariAplicacioConfiguracio.policyUrlDocument':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_policyUrlDocument_columnvalueid">
            <form:errors path="usuariAplicacioConfiguracio.policyUrlDocument" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuariAplicacioConfiguracio.policyUrlDocument"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}">
        <tr id="usuariAplicacioConfiguracio_motiuDelegacioID_rowid">
          <td id="usuariAplicacioConfiguracio_motiuDelegacioID_columnlabelid">
            <label id="usuariAplicacioConfiguracio_motiuDelegacioID_labelid">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID])?'usuariAplicacioConfiguracio.motiuDelegacioID':__theForm.labels[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_motiuDelegacioID_columnvalueid">
       <form:errors path="usuariAplicacioConfiguracio.motiuDelegacio" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#tab_${counter.index}_motiuDelegacio_${idioma.idiomaID}" lang="${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="tab_${counter.index}_motiuDelegacio_${idioma.idiomaID}">
               <form:errors path="usuariAplicacioConfiguracio.motiuDelegacio.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input aria-labelledby="usuariAplicacioConfiguracio_motiuDelegacioID_labelid" path="usuariAplicacioConfiguracio.motiuDelegacio.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}">
        <tr id="usuariAplicacioConfiguracio_politicaTaulaFirmes_rowid">
          <td id="usuariAplicacioConfiguracio_politicaTaulaFirmes_columnlabelid">
            <label for="usuariAplicacioConfiguracio.politicaTaulaFirmes">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES])?'usuariAplicacioConfiguracio.politicaTaulaFirmes':__theForm.labels[UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_politicaTaulaFirmes_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.politicaTaulaFirmes" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}" >
          <form:hidden path="usuariAplicacioConfiguracio.politicaTaulaFirmes"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.politicaTaulaFirmes,__theForm.listOfValuesForPoliticaTaulaFirmes)}" aria-label="usuariAplicacioConfiguracio.politicaTaulaFirmes"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.politicaTaulaFirmes" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePoliticaTaulaFirmes == 'function') {  onChangePoliticaTaulaFirmes(this); };">


            <c:forEach items="${__theForm.listOfValuesForPoliticaTaulaFirmes}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}">
        <tr id="usuariAplicacioConfiguracio_posicioTaulaFirmesID_rowid">
          <td id="usuariAplicacioConfiguracio_posicioTaulaFirmesID_columnlabelid">
            <label for="usuariAplicacioConfiguracio.posicioTaulaFirmesID">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID])?'usuariAplicacioConfiguracio.posicioTaulaFirmesID':__theForm.labels[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_posicioTaulaFirmesID_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.posicioTaulaFirmesID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.posicioTaulaFirmesID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.posicioTaulaFirmesID,__theForm.listOfValuesForPosicioTaulaFirmesID)}" aria-label="usuariAplicacioConfiguracio.posicioTaulaFirmesID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.posicioTaulaFirmesID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePosicioTaulaFirmesID == 'function') {  onChangePosicioTaulaFirmesID(this); };">


            <c:forEach items="${__theForm.listOfValuesForPosicioTaulaFirmesID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}">
        <tr id="usuariAplicacioConfiguracio_firmatPerFormatID_rowid">
          <td id="usuariAplicacioConfiguracio_firmatPerFormatID_columnlabelid">
            <label id="usuariAplicacioConfiguracio_firmatPerFormatID_labelid">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID])?'usuariAplicacioConfiguracio.firmatPerFormatID':__theForm.labels[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_firmatPerFormatID_columnvalueid">
       <form:errors path="usuariAplicacioConfiguracio.firmatPerFormat" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#tab_${counter.index}_firmatPerFormat_${idioma.idiomaID}" lang="${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="tab_${counter.index}_firmatPerFormat_${idioma.idiomaID}">
               <form:errors path="usuariAplicacioConfiguracio.firmatPerFormat.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input aria-labelledby="usuariAplicacioConfiguracio_firmatPerFormatID_labelid" path="usuariAplicacioConfiguracio.firmatPerFormat.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES)}">
        <tr id="usuariAplicacioConfiguracio_propietatsTaulaFirmes_rowid">
          <td id="usuariAplicacioConfiguracio_propietatsTaulaFirmes_columnlabelid">
            <label for="usuariAplicacioConfiguracio.propietatsTaulaFirmes">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES])?'usuariAplicacioConfiguracio.propietatsTaulaFirmes':__theForm.labels[UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_propietatsTaulaFirmes_columnvalueid">
              <form:errors path="usuariAplicacioConfiguracio.propietatsTaulaFirmes" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES)? 'readonly' : ''}" path="usuariAplicacioConfiguracio.propietatsTaulaFirmes"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_propietatsTaulaFirmes" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_propietatsTaulaFirmes" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.propietatsTaulaFirmes'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.propietatsTaulaFirmes'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.propietatsTaulaFirmes'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
			$('#dropdownMenuButton_propietatsTaulaFirmes').on('click', function(){
					var valor = ($('#dropdownMenuContainer_propietatsTaulaFirmes').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_propietatsTaulaFirmes').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}">
        <tr id="usuariAplicacioConfiguracio_politicaSegellatDeTemps_rowid">
          <td id="usuariAplicacioConfiguracio_politicaSegellatDeTemps_columnlabelid">
            <label for="usuariAplicacioConfiguracio.politicaSegellatDeTemps">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS])?'usuariAplicacioConfiguracio.politicaSegellatDeTemps':__theForm.labels[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_politicaSegellatDeTemps_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.politicaSegellatDeTemps" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}" >
          <form:hidden path="usuariAplicacioConfiguracio.politicaSegellatDeTemps"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.politicaSegellatDeTemps,__theForm.listOfValuesForPoliticaSegellatDeTemps)}" aria-label="usuariAplicacioConfiguracio.politicaSegellatDeTemps"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.politicaSegellatDeTemps" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePoliticaSegellatDeTemps == 'function') {  onChangePoliticaSegellatDeTemps(this); };">


            <c:forEach items="${__theForm.listOfValuesForPoliticaSegellatDeTemps}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}">
        <tr id="usuariAplicacioConfiguracio_pluginSegellatID_rowid">
          <td id="usuariAplicacioConfiguracio_pluginSegellatID_columnlabelid">
            <label for="usuariAplicacioConfiguracio.pluginSegellatID">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID])?'usuariAplicacioConfiguracio.pluginSegellatID':__theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_pluginSegellatID_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.pluginSegellatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.pluginSegellatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.pluginSegellatID,__theForm.listOfPluginForPluginSegellatID)}" aria-label="usuariAplicacioConfiguracio.pluginSegellatID"   />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.pluginSegellatID" cssClass="form-control col-md-9-optional"  onchange="if(typeof onChangePluginSegellatID == 'function') {  onChangePluginSegellatID(this); };">


            <c:forEach items="${__theForm.listOfPluginForPluginSegellatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.usuariAplicacioConfiguracio.pluginSegellatID }">
                  <option value="" selected></option>

              </c:if>
              <c:if test="${not empty __theForm.usuariAplicacioConfiguracio.pluginSegellatID }">

                  <option value="" ></option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)}">
        <tr id="usuariAplicacioConfiguracio_htmlPerLlistarPluginsFirmaWeb_rowid">
          <td id="usuariAplicacioConfiguracio_htmlPerLlistarPluginsFirmaWeb_columnlabelid">
            <label for="usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB])?'usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb':__theForm.labels[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_htmlPerLlistarPluginsFirmaWeb_columnvalueid">
              <form:errors path="usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)? 'readonly' : ''}" path="usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_htmlPerLlistarPluginsFirmaWeb" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_htmlPerLlistarPluginsFirmaWeb" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
			$('#dropdownMenuButton_htmlPerLlistarPluginsFirmaWeb').on('click', function(){
					var valor = ($('#dropdownMenuContainer_htmlPerLlistarPluginsFirmaWeb').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_htmlPerLlistarPluginsFirmaWeb').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
        <tr id="usuariAplicacioConfiguracio_pluginFirmaServidorID_rowid">
          <td id="usuariAplicacioConfiguracio_pluginFirmaServidorID_columnlabelid">
            <label for="usuariAplicacioConfiguracio.pluginFirmaServidorID">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID])?'usuariAplicacioConfiguracio.pluginFirmaServidorID':__theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_pluginFirmaServidorID_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.pluginFirmaServidorID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.pluginFirmaServidorID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.pluginFirmaServidorID,__theForm.listOfPluginForPluginFirmaServidorID)}" aria-label="usuariAplicacioConfiguracio.pluginFirmaServidorID"   />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.pluginFirmaServidorID" cssClass="form-control col-md-9-optional"  onchange="if(typeof onChangePluginFirmaServidorID == 'function') {  onChangePluginFirmaServidorID(this); };">


            <c:forEach items="${__theForm.listOfPluginForPluginFirmaServidorID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.usuariAplicacioConfiguracio.pluginFirmaServidorID }">
                  <option value="" selected></option>

              </c:if>
              <c:if test="${not empty __theForm.usuariAplicacioConfiguracio.pluginFirmaServidorID }">

                  <option value="" ></option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}">
        <tr id="usuariAplicacioConfiguracio_upgradeSignFormat_rowid">
          <td id="usuariAplicacioConfiguracio_upgradeSignFormat_columnlabelid">
            <label for="usuariAplicacioConfiguracio.upgradeSignFormat">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT])?'usuariAplicacioConfiguracio.upgradeSignFormat':__theForm.labels[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_upgradeSignFormat_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.upgradeSignFormat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}" >
          <form:hidden path="usuariAplicacioConfiguracio.upgradeSignFormat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.upgradeSignFormat,__theForm.listOfValuesForUpgradeSignFormat)}" aria-label="usuariAplicacioConfiguracio.upgradeSignFormat"   />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariAplicacioConfiguracio.upgradeSignFormat" cssClass="form-control col-md-9-optional"  onchange="if(typeof onChangeUpgradeSignFormat == 'function') {  onChangeUpgradeSignFormat(this); };">


            <c:forEach items="${__theForm.listOfValuesForUpgradeSignFormat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.usuariAplicacioConfiguracio.upgradeSignFormat }">
                  <option value="" selected></option>

              </c:if>
              <c:if test="${not empty __theForm.usuariAplicacioConfiguracio.upgradeSignFormat }">

                  <option value="" ></option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_validarFirma_rowid">
          <td id="usuariAplicacioConfiguracio_validarFirma_columnlabelid">
            <label for="usuariAplicacioConfiguracio.validarFirma">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARFIRMA])?'usuariAplicacioConfiguracio.validarFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_validarFirma_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeValidarFirma == 'function') {  onChangeValidarFirma(this); };"  path="usuariAplicacioConfiguracio.validarFirma">
                <form:option value=""><fmt:message key="definitenentitat." /></form:option>
                <form:option value="true" ><fmt:message key="definitenentitat.true" /></form:option>
                <form:option value="false" ><fmt:message key="definitenentitat.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}" >
                <fmt:message key="definitenentitat.${__theForm.usuariAplicacioConfiguracio.validarFirma}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}">
        <tr id="usuariAplicacioConfiguracio_checkCanviatDocFirmat_rowid">
          <td id="usuariAplicacioConfiguracio_checkCanviatDocFirmat_columnlabelid">
            <label for="usuariAplicacioConfiguracio.checkCanviatDocFirmat">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT])?'usuariAplicacioConfiguracio.checkCanviatDocFirmat':__theForm.labels[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_checkCanviatDocFirmat_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeCheckCanviatDocFirmat == 'function') {  onChangeCheckCanviatDocFirmat(this); };"  path="usuariAplicacioConfiguracio.checkCanviatDocFirmat">
                <form:option value=""><fmt:message key="definitenentitat." /></form:option>
                <form:option value="true" ><fmt:message key="definitenentitat.true" /></form:option>
                <form:option value="false" ><fmt:message key="definitenentitat.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}" >
                <fmt:message key="definitenentitat.${__theForm.usuariAplicacioConfiguracio.checkCanviatDocFirmat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_comprovarNifFirma_rowid">
          <td id="usuariAplicacioConfiguracio_comprovarNifFirma_columnlabelid">
            <label for="usuariAplicacioConfiguracio.comprovarNifFirma">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA])?'usuariAplicacioConfiguracio.comprovarNifFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_comprovarNifFirma_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeComprovarNifFirma == 'function') {  onChangeComprovarNifFirma(this); };"  path="usuariAplicacioConfiguracio.comprovarNifFirma">
                <form:option value=""><fmt:message key="definitenentitat." /></form:option>
                <form:option value="true" ><fmt:message key="definitenentitat.true" /></form:option>
                <form:option value="false" ><fmt:message key="definitenentitat.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}" >
                <fmt:message key="definitenentitat.${__theForm.usuariAplicacioConfiguracio.comprovarNifFirma}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}">
        <tr id="usuariAplicacioConfiguracio_validarCertificat_rowid">
          <td id="usuariAplicacioConfiguracio_validarCertificat_columnlabelid">
            <label for="usuariAplicacioConfiguracio.validarCertificat">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT])?'usuariAplicacioConfiguracio.validarCertificat':__theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_validarCertificat_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeValidarCertificat == 'function') {  onChangeValidarCertificat(this); };"  path="usuariAplicacioConfiguracio.validarCertificat">
                <form:option value=""><fmt:message key="definitenentitat." /></form:option>
                <form:option value="true" ><fmt:message key="definitenentitat.true" /></form:option>
                <form:option value="false" ><fmt:message key="definitenentitat.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}" >
                <fmt:message key="definitenentitat.${__theForm.usuariAplicacioConfiguracio.validarCertificat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.ESDEPETICIO)}">
        <tr id="usuariAplicacioConfiguracio_esDePeticio_rowid">
          <td id="usuariAplicacioConfiguracio_esDePeticio_columnlabelid">
            <label for="usuariAplicacioConfiguracio.esDePeticio">
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.ESDEPETICIO])?'usuariAplicacioConfiguracio.esDePeticio':__theForm.labels[UsuariAplicacioConfiguracioFields.ESDEPETICIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.ESDEPETICIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.ESDEPETICIO]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_esDePeticio_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ESDEPETICIO)}" >
              <form:errors  path="usuariAplicacioConfiguracio.esDePeticio" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ESDEPETICIO)? 'false' : 'true'}" id="usuariAplicacioConfiguracio.esDePeticio" path="usuariAplicacioConfiguracio.esDePeticio"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ESDEPETICIO)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.esDePeticio}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
