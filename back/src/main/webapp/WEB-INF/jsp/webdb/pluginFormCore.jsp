<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFields" className="es.caib.portafib.model.fields.PluginFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.NOMID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.NOMID])?'plugin.nomID':__theForm.labels[PluginFields.NOMID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.NOMID]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginFields.NOMID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="plugin.nom" cssClass="errorField alert alert-error" />
       <div class="tabbable">
         <ul class="nav nav-tabs" style="margin-bottom: 3px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
               <li class="${(counter.index == 0)? 'active':''}"  ><a href="#${counter.index}_tab_nom_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a></li>
           </c:forEach>
           
         </ul>
         <div class="tab-content">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_nom_${idioma.idiomaID}">
               <form:errors path="plugin.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="plugin.nom.traduccions['${idioma.idiomaID}'].valor" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.NOMID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PluginFields.NOMID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.DESCRIPCIOCURTAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.DESCRIPCIOCURTAID])?'plugin.descripcioCurtaID':__theForm.labels[PluginFields.DESCRIPCIOCURTAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.DESCRIPCIOCURTAID]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginFields.DESCRIPCIOCURTAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="plugin.descripcioCurta" cssClass="errorField alert alert-error" />
       <div class="tabbable">
         <ul class="nav nav-tabs" style="margin-bottom: 3px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
               <li class="${(counter.index == 0)? 'active':''}"  ><a href="#${counter.index}_tab_descripcioCurta_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a></li>
           </c:forEach>
           
         </ul>
         <div class="tab-content">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_descripcioCurta_${idioma.idiomaID}">
               <form:errors path="plugin.descripcioCurta.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="plugin.descripcioCurta.traduccions['${idioma.idiomaID}'].valor" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.DESCRIPCIOCURTAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PluginFields.DESCRIPCIOCURTAID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.CLASSE)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.CLASSE])?'plugin.classe':__theForm.labels[PluginFields.CLASSE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.CLASSE]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginFields.CLASSE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="plugin.classe" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.CLASSE)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PluginFields.CLASSE)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="plugin.classe"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.TIPUS)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.TIPUS])?'plugin.tipus':__theForm.labels[PluginFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.TIPUS]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="plugin.tipus" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.TIPUS)}" >
          <form:hidden path="plugin.tipus"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.plugin.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.TIPUS)}" >
          <form:select id="plugin_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="input-xxlarge" path="plugin.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.PROPERTIESADMIN)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.PROPERTIESADMIN])?'plugin.propertiesAdmin':__theForm.labels[PluginFields.PROPERTIESADMIN]}" />
              <c:if test="${not empty __theForm.help[PluginFields.PROPERTIESADMIN]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginFields.PROPERTIESADMIN]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="plugin.propertiesAdmin" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.PROPERTIESADMIN)? 'true' : 'false'}" path="plugin.propertiesAdmin"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.PROPERTIESENTITAT)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.PROPERTIESENTITAT])?'plugin.propertiesEntitat':__theForm.labels[PluginFields.PROPERTIESENTITAT]}" />
              <c:if test="${not empty __theForm.help[PluginFields.PROPERTIESENTITAT]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginFields.PROPERTIESENTITAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="plugin.propertiesEntitat" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.PROPERTIESENTITAT)? 'true' : 'false'}" path="plugin.propertiesEntitat"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.ENTITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.ENTITATID])?'plugin.entitatID':__theForm.labels[PluginFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[PluginFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="plugin.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.ENTITATID)}" >
          <form:hidden path="plugin.entitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.plugin.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.ENTITATID)}" >
          <form:select id="plugin_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="input-xxlarge" path="plugin.entitatID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.ACTIU)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.ACTIU])?'plugin.actiu':__theForm.labels[PluginFields.ACTIU]}" />
              <c:if test="${not empty __theForm.help[PluginFields.ACTIU]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginFields.ACTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)}" >
              <form:errors path="plugin.actiu" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)? 'false' : 'true'}" path="plugin.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.plugin.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
