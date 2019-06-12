<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioConfiguracioFields" className="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.NOM)}">
        <tr id="usuariAplicacioConfiguracio_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.NOM])?'usuariAplicacioConfiguracio.nom':__theForm.labels[UsuariAplicacioConfiguracioFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacioConfiguracio.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="usuariAplicacioConfiguracio.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.ENTITATID)}">
        <tr id="usuariAplicacioConfiguracio_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.ENTITATID])?'usuariAplicacioConfiguracio.entitatID':__theForm.labels[UsuariAplicacioConfiguracioFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ENTITATID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.entitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ENTITATID)}" >
          <form:select id="usuariAplicacioConfiguracio_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaApiSimpleServidor_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR])?'usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)}" >
              <form:errors path="usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)? 'false' : 'true'}" path="usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaApiSimpleWeb_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB])?'usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)}" >
              <form:errors path="usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)? 'false' : 'true'}" path="usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaWeb_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAWEB])?'usuariAplicacioConfiguracio.usEnFirmaWeb':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAWEB]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAWEB]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAWEB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)}" >
              <form:errors path="usuariAplicacioConfiguracio.usEnFirmaWeb" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)? 'false' : 'true'}" path="usuariAplicacioConfiguracio.usEnFirmaWeb" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaWeb}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaWS1_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAWS1])?'usuariAplicacioConfiguracio.usEnFirmaWS1':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAWS1]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAWS1]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAWS1]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)}" >
              <form:errors path="usuariAplicacioConfiguracio.usEnFirmaWS1" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)? 'false' : 'true'}" path="usuariAplicacioConfiguracio.usEnFirmaWS1" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaWS1}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaAsyncRest2_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2])?'usuariAplicacioConfiguracio.usEnFirmaAsyncRest2':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)}" >
              <form:errors path="usuariAplicacioConfiguracio.usEnFirmaAsyncRest2" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)? 'false' : 'true'}" path="usuariAplicacioConfiguracio.usEnFirmaAsyncRest2" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaAsyncRest2}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaPassarelaServidor_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR])?'usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)}" >
              <form:errors path="usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)? 'false' : 'true'}" path="usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)}">
        <tr id="usuariAplicacioConfiguracio_usEnFirmaPassarelaWeb_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB])?'usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb':__theForm.labels[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)}" >
              <form:errors path="usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)? 'false' : 'true'}" path="usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)}">
        <tr id="usuariAplicacioConfiguracio_filtreCertificats_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS])?'usuariAplicacioConfiguracio.filtreCertificats':__theForm.labels[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="usuariAplicacioConfiguracio.filtreCertificats" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)? 'true' : 'false'}" path="usuariAplicacioConfiguracio.filtreCertificats"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.filtreCertificats'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.filtreCertificats'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.filtreCertificats'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_tipusOperacioFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA])?'usuariAplicacioConfiguracio.tipusOperacioFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.tipusOperacioFirma" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.tipusOperacioFirma"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.tipusOperacioFirma,__theForm.listOfValuesForTipusOperacioFirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}" >
          <form:select id="usuariAplicacioConfiguracio_tipusOperacioFirma"  onchange="if(typeof onChangeTipusOperacioFirma == 'function') {  onChangeTipusOperacioFirma(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.tipusOperacioFirma">
            <c:forEach items="${__theForm.listOfValuesForTipusOperacioFirma}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}">
        <tr id="usuariAplicacioConfiguracio_tipusFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID])?'usuariAplicacioConfiguracio.tipusFirmaID':__theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.tipusFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.tipusFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.tipusFirmaID,__theForm.listOfValuesForTipusFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}" >
          <form:select id="usuariAplicacioConfiguracio_tipusFirmaID"  onchange="if(typeof onChangeTipusFirmaID == 'function') {  onChangeTipusFirmaID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.tipusFirmaID">
            <c:forEach items="${__theForm.listOfValuesForTipusFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}">
        <tr id="usuariAplicacioConfiguracio_algorismeDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID])?'usuariAplicacioConfiguracio.algorismeDeFirmaID':__theForm.labels[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.algorismeDeFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.algorismeDeFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.algorismeDeFirmaID,__theForm.listOfValuesForAlgorismeDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}" >
          <form:select id="usuariAplicacioConfiguracio_algorismeDeFirmaID"  onchange="if(typeof onChangeAlgorismeDeFirmaID == 'function') {  onChangeAlgorismeDeFirmaID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.algorismeDeFirmaID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfValuesForAlgorismeDeFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_modeDeFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.MODEDEFIRMA])?'usuariAplicacioConfiguracio.modeDeFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeModeDeFirma == 'function') {  onChangeModeDeFirma(this); };"  path="usuariAplicacioConfiguracio.modeDeFirma">
                <form:option value="true" ><fmt:message key="modefirma.true" /></form:option>
                <form:option value="false" ><fmt:message key="modefirma.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}" >
                <fmt:message key="modefirma.${__theForm.usuariAplicacioConfiguracio.modeDeFirma}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_usPoliticaDeFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA])?'usuariAplicacioConfiguracio.usPoliticaDeFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.usPoliticaDeFirma" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.usPoliticaDeFirma"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.usPoliticaDeFirma,__theForm.listOfValuesForUsPoliticaDeFirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}" >
          <form:select id="usuariAplicacioConfiguracio_usPoliticaDeFirma"  onchange="if(typeof onChangeUsPoliticaDeFirma == 'function') {  onChangeUsPoliticaDeFirma(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.usPoliticaDeFirma">
            <c:forEach items="${__theForm.listOfValuesForUsPoliticaDeFirma}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifier_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER])?'usuariAplicacioConfiguracio.policyIdentifier':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifier" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="usuariAplicacioConfiguracio.policyIdentifier"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifierHash_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH])?'usuariAplicacioConfiguracio.policyIdentifierHash':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifierHash" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="256" path="usuariAplicacioConfiguracio.policyIdentifierHash"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifierHashAlgorithm_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM])?'usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
        <tr id="usuariAplicacioConfiguracio_policyUrlDocument_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT])?'usuariAplicacioConfiguracio.policyUrlDocument':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacioConfiguracio.policyUrlDocument" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="usuariAplicacioConfiguracio.policyUrlDocument"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}">
        <tr id="usuariAplicacioConfiguracio_politicaTaulaFirmes_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES])?'usuariAplicacioConfiguracio.politicaTaulaFirmes':__theForm.labels[UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.politicaTaulaFirmes" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}" >
          <form:hidden path="usuariAplicacioConfiguracio.politicaTaulaFirmes"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.politicaTaulaFirmes,__theForm.listOfValuesForPoliticaTaulaFirmes)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}" >
          <form:select id="usuariAplicacioConfiguracio_politicaTaulaFirmes"  onchange="if(typeof onChangePoliticaTaulaFirmes == 'function') {  onChangePoliticaTaulaFirmes(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.politicaTaulaFirmes">
            <c:forEach items="${__theForm.listOfValuesForPoliticaTaulaFirmes}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}">
        <tr id="usuariAplicacioConfiguracio_posicioTaulaFirmesID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID])?'usuariAplicacioConfiguracio.posicioTaulaFirmesID':__theForm.labels[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.posicioTaulaFirmesID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.posicioTaulaFirmesID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.posicioTaulaFirmesID,__theForm.listOfValuesForPosicioTaulaFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}" >
          <form:select id="usuariAplicacioConfiguracio_posicioTaulaFirmesID"  onchange="if(typeof onChangePosicioTaulaFirmesID == 'function') {  onChangePosicioTaulaFirmesID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.posicioTaulaFirmesID">
            <c:forEach items="${__theForm.listOfValuesForPosicioTaulaFirmesID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}">
        <tr id="usuariAplicacioConfiguracio_firmatPerFormatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID])?'usuariAplicacioConfiguracio.firmatPerFormatID':__theForm.labels[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="usuariAplicacioConfiguracio.firmatPerFormat" cssClass="errorField alert alert-error" />
       <div class="tabbable">
         <ul class="nav nav-tabs" style="margin-bottom: 3px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
               <li class="${(counter.index == 0)? 'active':''}"  ><a href="#${counter.index}_tab_firmatPerFormat_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a></li>
           </c:forEach>
           
         </ul>
         <div class="tab-content">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_firmatPerFormat_${idioma.idiomaID}">
               <form:errors path="usuariAplicacioConfiguracio.firmatPerFormat.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="usuariAplicacioConfiguracio.firmatPerFormat.traduccions['${idioma.idiomaID}'].valor" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}">
        <tr id="usuariAplicacioConfiguracio_motiuDelegacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID])?'usuariAplicacioConfiguracio.motiuDelegacioID':__theForm.labels[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="usuariAplicacioConfiguracio.motiuDelegacio" cssClass="errorField alert alert-error" />
       <div class="tabbable">
         <ul class="nav nav-tabs" style="margin-bottom: 3px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
               <li class="${(counter.index == 0)? 'active':''}"  ><a href="#${counter.index}_tab_motiuDelegacio_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a></li>
           </c:forEach>
           
         </ul>
         <div class="tab-content">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_motiuDelegacio_${idioma.idiomaID}">
               <form:errors path="usuariAplicacioConfiguracio.motiuDelegacio.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="usuariAplicacioConfiguracio.motiuDelegacio.traduccions['${idioma.idiomaID}'].valor" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES)}">
        <tr id="usuariAplicacioConfiguracio_propietatsTaulaFirmes_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES])?'usuariAplicacioConfiguracio.propietatsTaulaFirmes':__theForm.labels[UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="usuariAplicacioConfiguracio.propietatsTaulaFirmes" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES)? 'true' : 'false'}" path="usuariAplicacioConfiguracio.propietatsTaulaFirmes"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.propietatsTaulaFirmes'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.propietatsTaulaFirmes'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.propietatsTaulaFirmes'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}">
        <tr id="usuariAplicacioConfiguracio_politicaSegellatDeTemps_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS])?'usuariAplicacioConfiguracio.politicaSegellatDeTemps':__theForm.labels[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.politicaSegellatDeTemps" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}" >
          <form:hidden path="usuariAplicacioConfiguracio.politicaSegellatDeTemps"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.politicaSegellatDeTemps,__theForm.listOfValuesForPoliticaSegellatDeTemps)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}" >
          <form:select id="usuariAplicacioConfiguracio_politicaSegellatDeTemps"  onchange="if(typeof onChangePoliticaSegellatDeTemps == 'function') {  onChangePoliticaSegellatDeTemps(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.politicaSegellatDeTemps">
            <c:forEach items="${__theForm.listOfValuesForPoliticaSegellatDeTemps}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}">
        <tr id="usuariAplicacioConfiguracio_pluginSegellatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID])?'usuariAplicacioConfiguracio.pluginSegellatID':__theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.pluginSegellatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.pluginSegellatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.pluginSegellatID,__theForm.listOfPluginForPluginSegellatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}" >
          <form:select id="usuariAplicacioConfiguracio_pluginSegellatID"  onchange="if(typeof onChangePluginSegellatID == 'function') {  onChangePluginSegellatID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.pluginSegellatID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfPluginForPluginSegellatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)}">
        <tr id="usuariAplicacioConfiguracio_htmlPerLlistarPluginsFirmaWeb_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB])?'usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb':__theForm.labels[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)? 'true' : 'false'}" path="usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
        <tr id="usuariAplicacioConfiguracio_pluginFirmaServidorID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID])?'usuariAplicacioConfiguracio.pluginFirmaServidorID':__theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.pluginFirmaServidorID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.pluginFirmaServidorID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.pluginFirmaServidorID,__theForm.listOfPluginForPluginFirmaServidorID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}" >
          <form:select id="usuariAplicacioConfiguracio_pluginFirmaServidorID"  onchange="if(typeof onChangePluginFirmaServidorID == 'function') {  onChangePluginFirmaServidorID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.pluginFirmaServidorID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfPluginForPluginFirmaServidorID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}">
        <tr id="usuariAplicacioConfiguracio_upgradeSignFormat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT])?'usuariAplicacioConfiguracio.upgradeSignFormat':__theForm.labels[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.upgradeSignFormat" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}" >
          <form:hidden path="usuariAplicacioConfiguracio.upgradeSignFormat"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.upgradeSignFormat,__theForm.listOfValuesForUpgradeSignFormat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}" >
          <form:select id="usuariAplicacioConfiguracio_upgradeSignFormat"  onchange="if(typeof onChangeUpgradeSignFormat == 'function') {  onChangeUpgradeSignFormat(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.upgradeSignFormat">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfValuesForUpgradeSignFormat}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)}">
        <tr id="usuariAplicacioConfiguracio_loginCertificateID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID])?'usuariAplicacioConfiguracio.loginCertificateID':__theForm.labels[UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="usuariAplicacioConfiguracio.loginCertificateID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)? 'input uneditable-input' : 'input'}"  path="loginCertificateID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.usuariAplicacioConfiguracio.loginCertificate}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)}" >
                    <span class="add-on">
                        <form:checkbox path="loginCertificateIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.usuariAplicacioConfiguracio.loginCertificate)}"/>">${__theForm.usuariAplicacioConfiguracio.loginCertificate.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_validarFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARFIRMA])?'usuariAplicacioConfiguracio.validarFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeValidarFirma == 'function') {  onChangeValidarFirma(this); };"  path="usuariAplicacioConfiguracio.validarFirma">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT])?'usuariAplicacioConfiguracio.checkCanviatDocFirmat':__theForm.labels[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeCheckCanviatDocFirmat == 'function') {  onChangeCheckCanviatDocFirmat(this); };"  path="usuariAplicacioConfiguracio.checkCanviatDocFirmat">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA])?'usuariAplicacioConfiguracio.comprovarNifFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeComprovarNifFirma == 'function') {  onChangeComprovarNifFirma(this); };"  path="usuariAplicacioConfiguracio.comprovarNifFirma">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT])?'usuariAplicacioConfiguracio.validarCertificat':__theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeValidarCertificat == 'function') {  onChangeValidarCertificat(this); };"  path="usuariAplicacioConfiguracio.validarCertificat">
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
        
