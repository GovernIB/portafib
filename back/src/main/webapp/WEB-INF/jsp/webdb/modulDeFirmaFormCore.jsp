<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModulDeFirmaFields" className="es.caib.portafib.model.fields.ModulDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaFields.NOMID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaFields.NOMID])?'modulDeFirma.nomID':__theForm.labels[ModulDeFirmaFields.NOMID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ModulDeFirmaFields.NOMID]}">
              <i class="icon-info-sign" title="${__theForm.help[ModulDeFirmaFields.NOMID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="modulDeFirma.nom" cssClass="errorField alert alert-error" />
       <div class="tabbable">
         <ul class="nav nav-tabs" style="margin-bottom: 3px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
               <li class="${(counter.index == 0)? 'active':''}"  ><a href="#${counter.index}_tab_nom_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a></li>
           </c:forEach>
           
         </ul>
         <div class="tab-content">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_nom_${idioma.idiomaID}">
               <form:errors path="modulDeFirma.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="modulDeFirma.nom.traduccions['${idioma.idiomaID}'].valor" readonly="${ gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.NOMID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.NOMID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaFields.DESCRIPCIOCURTAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaFields.DESCRIPCIOCURTAID])?'modulDeFirma.descripcioCurtaID':__theForm.labels[ModulDeFirmaFields.DESCRIPCIOCURTAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ModulDeFirmaFields.DESCRIPCIOCURTAID]}">
              <i class="icon-info-sign" title="${__theForm.help[ModulDeFirmaFields.DESCRIPCIOCURTAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="modulDeFirma.descripcioCurta" cssClass="errorField alert alert-error" />
       <div class="tabbable">
         <ul class="nav nav-tabs" style="margin-bottom: 3px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
               <li class="${(counter.index == 0)? 'active':''}"  ><a href="#${counter.index}_tab_descripcioCurta_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a></li>
           </c:forEach>
           
         </ul>
         <div class="tab-content">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_descripcioCurta_${idioma.idiomaID}">
               <form:errors path="modulDeFirma.descripcioCurta.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="modulDeFirma.descripcioCurta.traduccions['${idioma.idiomaID}'].valor" readonly="${ gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.DESCRIPCIOCURTAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.DESCRIPCIOCURTAID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaFields.CLASSE)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaFields.CLASSE])?'modulDeFirma.classe':__theForm.labels[ModulDeFirmaFields.CLASSE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ModulDeFirmaFields.CLASSE]}">
              <i class="icon-info-sign" title="${__theForm.help[ModulDeFirmaFields.CLASSE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="modulDeFirma.classe" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.CLASSE)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.CLASSE)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="modulDeFirma.classe"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaFields.PROPERTIESADMIN)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaFields.PROPERTIESADMIN])?'modulDeFirma.propertiesAdmin':__theForm.labels[ModulDeFirmaFields.PROPERTIESADMIN]}" />
              <c:if test="${not empty __theForm.help[ModulDeFirmaFields.PROPERTIESADMIN]}">
              <i class="icon-info-sign" title="${__theForm.help[ModulDeFirmaFields.PROPERTIESADMIN]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="modulDeFirma.propertiesAdmin" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="off" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.PROPERTIESADMIN)? 'true' : 'false'}" path="modulDeFirma.propertiesAdmin"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaFields.PROPERTIESENTITAT)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaFields.PROPERTIESENTITAT])?'modulDeFirma.propertiesEntitat':__theForm.labels[ModulDeFirmaFields.PROPERTIESENTITAT]}" />
              <c:if test="${not empty __theForm.help[ModulDeFirmaFields.PROPERTIESENTITAT]}">
              <i class="icon-info-sign" title="${__theForm.help[ModulDeFirmaFields.PROPERTIESENTITAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="modulDeFirma.propertiesEntitat" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="off" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.PROPERTIESENTITAT)? 'true' : 'false'}" path="modulDeFirma.propertiesEntitat"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaFields.ENTITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaFields.ENTITATID])?'modulDeFirma.entitatID':__theForm.labels[ModulDeFirmaFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[ModulDeFirmaFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[ModulDeFirmaFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="modulDeFirma.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.ENTITATID)}" >
          <form:hidden path="modulDeFirma.entitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.modulDeFirma.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.ENTITATID)}" >
          <form:select id="modulDeFirma_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="input-xxlarge" path="modulDeFirma.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaFields.ACTIU)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaFields.ACTIU])?'modulDeFirma.actiu':__theForm.labels[ModulDeFirmaFields.ACTIU]}" />
              <c:if test="${not empty __theForm.help[ModulDeFirmaFields.ACTIU]}">
              <i class="icon-info-sign" title="${__theForm.help[ModulDeFirmaFields.ACTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.ACTIU)}" >
              <form:errors path="modulDeFirma.actiu" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.ACTIU)? 'false' : 'true'}" path="modulDeFirma.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModulDeFirmaFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.modulDeFirma.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
