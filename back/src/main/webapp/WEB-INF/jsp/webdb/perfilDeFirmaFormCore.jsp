<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PerfilDeFirmaFields" className="es.caib.portafib.model.fields.PerfilDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.NOM)}">
        <tr id="perfilDeFirma_nom_rowid">
          <td id="perfilDeFirma_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.NOM])?'perfilDeFirma.nom':__theForm.labels[PerfilDeFirmaFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilDeFirmaFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="perfilDeFirma_nom_columnvalueid">
            <form:errors path="perfilDeFirma.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="perfilDeFirma.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CODI)}">
        <tr id="perfilDeFirma_codi_rowid">
          <td id="perfilDeFirma_codi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CODI])?'perfilDeFirma.codi':__theForm.labels[PerfilDeFirmaFields.CODI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilDeFirmaFields.CODI]}" ></i>
              </c:if>
            </td>
          <td id="perfilDeFirma_codi_columnvalueid">
            <form:errors path="perfilDeFirma.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CODI)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="100" path="perfilDeFirma.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.DESCRIPCIO)}">
        <tr id="perfilDeFirma_descripcio_rowid">
          <td id="perfilDeFirma_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.DESCRIPCIO])?'perfilDeFirma.descripcio':__theForm.labels[PerfilDeFirmaFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilDeFirmaFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="perfilDeFirma_descripcio_columnvalueid">
              <form:errors path="perfilDeFirma.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.DESCRIPCIO)? 'true' : 'false'}" path="perfilDeFirma.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONDICIO)}">
        <tr id="perfilDeFirma_condicio_rowid">
          <td id="perfilDeFirma_condicio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONDICIO])?'perfilDeFirma.condicio':__theForm.labels[PerfilDeFirmaFields.CONDICIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONDICIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilDeFirmaFields.CONDICIO]}" ></i>
              </c:if>
            </td>
          <td id="perfilDeFirma_condicio_columnvalueid">
              <form:errors path="perfilDeFirma.condicio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONDICIO)? 'true' : 'false'}" path="perfilDeFirma.condicio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_condicio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_condicio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.condicio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.condicio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('perfilDeFirma.condicio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_condicio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_condicio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_condicio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID)}">
        <tr id="perfilDeFirma_configuracioDeFirma1ID_rowid">
          <td id="perfilDeFirma_configuracioDeFirma1ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID])?'perfilDeFirma.configuracioDeFirma1ID':__theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID]}" ></i>
              </c:if>
            </td>
          <td id="perfilDeFirma_configuracioDeFirma1ID_columnvalueid">
          <form:errors path="perfilDeFirma.configuracioDeFirma1ID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID)}" >
          <form:hidden path="perfilDeFirma.configuracioDeFirma1ID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.perfilDeFirma.configuracioDeFirma1ID,__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="perfilDeFirma_configuracioDeFirma1ID"  onchange="if(typeof onChangeConfiguracioDeFirma1ID == 'function') {  onChangeConfiguracioDeFirma1ID(this); };"  cssClass="form-control col-md-9-optional" path="perfilDeFirma.configuracioDeFirma1ID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID)}">
        <tr id="perfilDeFirma_configuracioDeFirma2ID_rowid">
          <td id="perfilDeFirma_configuracioDeFirma2ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID])?'perfilDeFirma.configuracioDeFirma2ID':__theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID]}" ></i>
              </c:if>
            </td>
          <td id="perfilDeFirma_configuracioDeFirma2ID_columnvalueid">
          <form:errors path="perfilDeFirma.configuracioDeFirma2ID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID)}" >
          <form:hidden path="perfilDeFirma.configuracioDeFirma2ID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.perfilDeFirma.configuracioDeFirma2ID,__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="perfilDeFirma_configuracioDeFirma2ID"  onchange="if(typeof onChangeConfiguracioDeFirma2ID == 'function') {  onChangeConfiguracioDeFirma2ID(this); };"  cssClass="form-control col-md-9-optional" path="perfilDeFirma.configuracioDeFirma2ID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.perfilDeFirma.configuracioDeFirma2ID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.perfilDeFirma.configuracioDeFirma2ID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID)}">
        <tr id="perfilDeFirma_configuracioDeFirma3ID_rowid">
          <td id="perfilDeFirma_configuracioDeFirma3ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID])?'perfilDeFirma.configuracioDeFirma3ID':__theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID]}" ></i>
              </c:if>
            </td>
          <td id="perfilDeFirma_configuracioDeFirma3ID_columnvalueid">
          <form:errors path="perfilDeFirma.configuracioDeFirma3ID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID)}" >
          <form:hidden path="perfilDeFirma.configuracioDeFirma3ID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.perfilDeFirma.configuracioDeFirma3ID,__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="perfilDeFirma_configuracioDeFirma3ID"  onchange="if(typeof onChangeConfiguracioDeFirma3ID == 'function') {  onChangeConfiguracioDeFirma3ID(this); };"  cssClass="form-control col-md-9-optional" path="perfilDeFirma.configuracioDeFirma3ID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.perfilDeFirma.configuracioDeFirma3ID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.perfilDeFirma.configuracioDeFirma3ID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID)}">
        <tr id="perfilDeFirma_configuracioDeFirma4ID_rowid">
          <td id="perfilDeFirma_configuracioDeFirma4ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID])?'perfilDeFirma.configuracioDeFirma4ID':__theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID]}" ></i>
              </c:if>
            </td>
          <td id="perfilDeFirma_configuracioDeFirma4ID_columnvalueid">
          <form:errors path="perfilDeFirma.configuracioDeFirma4ID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID)}" >
          <form:hidden path="perfilDeFirma.configuracioDeFirma4ID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.perfilDeFirma.configuracioDeFirma4ID,__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="perfilDeFirma_configuracioDeFirma4ID"  onchange="if(typeof onChangeConfiguracioDeFirma4ID == 'function') {  onChangeConfiguracioDeFirma4ID(this); };"  cssClass="form-control col-md-9-optional" path="perfilDeFirma.configuracioDeFirma4ID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.perfilDeFirma.configuracioDeFirma4ID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.perfilDeFirma.configuracioDeFirma4ID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID)}">
        <tr id="perfilDeFirma_configuracioDeFirma5ID_rowid">
          <td id="perfilDeFirma_configuracioDeFirma5ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID])?'perfilDeFirma.configuracioDeFirma5ID':__theForm.labels[PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID]}" ></i>
              </c:if>
            </td>
          <td id="perfilDeFirma_configuracioDeFirma5ID_columnvalueid">
          <form:errors path="perfilDeFirma.configuracioDeFirma5ID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID)}" >
          <form:hidden path="perfilDeFirma.configuracioDeFirma5ID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.perfilDeFirma.configuracioDeFirma5ID,__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="perfilDeFirma_configuracioDeFirma5ID"  onchange="if(typeof onChangeConfiguracioDeFirma5ID == 'function') {  onChangeConfiguracioDeFirma5ID(this); };"  cssClass="form-control col-md-9-optional" path="perfilDeFirma.configuracioDeFirma5ID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.perfilDeFirma.configuracioDeFirma5ID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.perfilDeFirma.configuracioDeFirma5ID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PerfilDeFirmaFields.URLBASE)}">
        <tr id="perfilDeFirma_urlBase_rowid">
          <td id="perfilDeFirma_urlBase_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PerfilDeFirmaFields.URLBASE])?'perfilDeFirma.urlBase':__theForm.labels[PerfilDeFirmaFields.URLBASE]}" />
             </label>
              <c:if test="${not empty __theForm.help[PerfilDeFirmaFields.URLBASE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PerfilDeFirmaFields.URLBASE]}" ></i>
              </c:if>
            </td>
          <td id="perfilDeFirma_urlBase_columnvalueid">
            <form:errors path="perfilDeFirma.urlBase" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.URLBASE)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PerfilDeFirmaFields.URLBASE)? ' uneditable-input' : ''}"  style="" maxlength="255" path="perfilDeFirma.urlBase"   />

           </td>
        </tr>
        </c:if>
        
