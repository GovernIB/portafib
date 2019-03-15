<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PerfilDeFirmaFields" className="es.caib.portafib.model.fields.PerfilDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.NOM)}">
        <tr id="perfilDeFirma_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.NOM])?'perfilDeFirma.nom':__theForm.labels[PerfilDeFirmaFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[PerfilDeFirmaFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="perfilDeFirma.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="perfilDeFirma.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CODI)}">
        <tr id="perfilDeFirma_codi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CODI])?'perfilDeFirma.codi':__theForm.labels[PerfilDeFirmaFields.CODI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CODI]}">
              <i class="icon-info-sign" title="${__theForm.help[PerfilDeFirmaFields.CODI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="perfilDeFirma.codi" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CODI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CODI)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="perfilDeFirma.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.DESCRIPCIO)}">
        <tr id="perfilDeFirma_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.DESCRIPCIO])?'perfilDeFirma.descripcio':__theForm.labels[PerfilDeFirmaFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[PerfilDeFirmaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="perfilDeFirma.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.DESCRIPCIO)? 'true' : 'false'}" path="perfilDeFirma.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONDICIO)}">
        <tr id="perfilDeFirma_condicio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONDICIO])?'perfilDeFirma.condicio':__theForm.labels[PerfilDeFirmaFields.CONDICIO]}" />
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONDICIO]}">
              <i class="icon-info-sign" title="${__theForm.help[PerfilDeFirmaFields.CONDICIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="perfilDeFirma.condicio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONDICIO)? 'true' : 'false'}" path="perfilDeFirma.condicio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.condicio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.condicio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.condicio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID)}">
        <tr id="perfilDeFirma_configuracioDeFirma1ID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID])?'perfilDeFirma.configuracioDeFirma1ID':__theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID]}">
              <i class="icon-info-sign" title="${__theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="perfilDeFirma.configuracioDeFirma1ID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID)}" >
          <form:hidden path="perfilDeFirma.configuracioDeFirma1ID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.perfilDeFirma.configuracioDeFirma1ID,__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID)}" >
          <form:select id="perfilDeFirma_configuracioDeFirma1ID"  onchange="if(typeof onChangeConfiguracioDeFirma1ID == 'function') {  onChangeConfiguracioDeFirma1ID(this); };"  cssClass="input-xxlarge" path="perfilDeFirma.configuracioDeFirma1ID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID)}">
        <tr id="perfilDeFirma_configuracioDeFirma2ID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID])?'perfilDeFirma.configuracioDeFirma2ID':__theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID]}" />
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID]}">
              <i class="icon-info-sign" title="${__theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="perfilDeFirma.configuracioDeFirma2ID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID)}" >
          <form:hidden path="perfilDeFirma.configuracioDeFirma2ID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.perfilDeFirma.configuracioDeFirma2ID,__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID)}" >
          <form:select id="perfilDeFirma_configuracioDeFirma2ID"  onchange="if(typeof onChangeConfiguracioDeFirma2ID == 'function') {  onChangeConfiguracioDeFirma2ID(this); };"  cssClass="input-xxlarge" path="perfilDeFirma.configuracioDeFirma2ID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID)}">
        <tr id="perfilDeFirma_configuracioDeFirma3ID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID])?'perfilDeFirma.configuracioDeFirma3ID':__theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID]}" />
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID]}">
              <i class="icon-info-sign" title="${__theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="perfilDeFirma.configuracioDeFirma3ID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID)}" >
          <form:hidden path="perfilDeFirma.configuracioDeFirma3ID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.perfilDeFirma.configuracioDeFirma3ID,__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID)}" >
          <form:select id="perfilDeFirma_configuracioDeFirma3ID"  onchange="if(typeof onChangeConfiguracioDeFirma3ID == 'function') {  onChangeConfiguracioDeFirma3ID(this); };"  cssClass="input-xxlarge" path="perfilDeFirma.configuracioDeFirma3ID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID)}">
        <tr id="perfilDeFirma_configuracioDeFirma4ID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID])?'perfilDeFirma.configuracioDeFirma4ID':__theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID]}" />
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID]}">
              <i class="icon-info-sign" title="${__theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="perfilDeFirma.configuracioDeFirma4ID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID)}" >
          <form:hidden path="perfilDeFirma.configuracioDeFirma4ID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.perfilDeFirma.configuracioDeFirma4ID,__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID)}" >
          <form:select id="perfilDeFirma_configuracioDeFirma4ID"  onchange="if(typeof onChangeConfiguracioDeFirma4ID == 'function') {  onChangeConfiguracioDeFirma4ID(this); };"  cssClass="input-xxlarge" path="perfilDeFirma.configuracioDeFirma4ID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID)}">
        <tr id="perfilDeFirma_configuracioDeFirma5ID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID])?'perfilDeFirma.configuracioDeFirma5ID':__theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID]}" />
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID]}">
              <i class="icon-info-sign" title="${__theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="perfilDeFirma.configuracioDeFirma5ID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID)}" >
          <form:hidden path="perfilDeFirma.configuracioDeFirma5ID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.perfilDeFirma.configuracioDeFirma5ID,__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID)}" >
          <form:select id="perfilDeFirma_configuracioDeFirma5ID"  onchange="if(typeof onChangeConfiguracioDeFirma5ID == 'function') {  onChangeConfiguracioDeFirma5ID(this); };"  cssClass="input-xxlarge" path="perfilDeFirma.configuracioDeFirma5ID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.URLBASE)}">
        <tr id="perfilDeFirma_urlBase_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.URLBASE])?'perfilDeFirma.urlBase':__theForm.labels[PerfilDeFirmaFields.URLBASE]}" />
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.URLBASE]}">
              <i class="icon-info-sign" title="${__theForm.help[PerfilDeFirmaFields.URLBASE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="perfilDeFirma.urlBase" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.URLBASE)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.URLBASE)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="perfilDeFirma.urlBase"   />

           </td>
        </tr>
        </c:if>
        
