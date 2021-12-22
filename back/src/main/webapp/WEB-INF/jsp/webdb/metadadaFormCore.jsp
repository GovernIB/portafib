<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="MetadadaFields" className="es.caib.portafib.model.fields.MetadadaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,MetadadaFields.NOM)}">
        <tr id="metadada_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MetadadaFields.NOM])?'metadada.nom':__theForm.labels[MetadadaFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[MetadadaFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MetadadaFields.NOM]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="metadada.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,MetadadaFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,MetadadaFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="50" path="metadada.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MetadadaFields.VALOR)}">
        <tr id="metadada_valor_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MetadadaFields.VALOR])?'metadada.valor':__theForm.labels[MetadadaFields.VALOR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[MetadadaFields.VALOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MetadadaFields.VALOR]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="metadada.valor" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,MetadadaFields.VALOR)? 'true' : 'false'}" path="metadada.valor"  />
      <div id="dropdownMenuButton_valor" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_valor" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('metadada.valor'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('metadada.valor'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('metadada.valor'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_valor').on('click', function(){
					var valor = ($('#dropdownMenuContainer_valor').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_valor').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MetadadaFields.DESCRIPCIO)}">
        <tr id="metadada_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MetadadaFields.DESCRIPCIO])?'metadada.descripcio':__theForm.labels[MetadadaFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[MetadadaFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MetadadaFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="metadada.descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,MetadadaFields.DESCRIPCIO)? 'true' : 'false'}" path="metadada.descripcio"  />
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('metadada.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('metadada.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('metadada.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MetadadaFields.PETICIODEFIRMAID)}">
        <tr id="metadada_peticioDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MetadadaFields.PETICIODEFIRMAID])?'metadada.peticioDeFirmaID':__theForm.labels[MetadadaFields.PETICIODEFIRMAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[MetadadaFields.PETICIODEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MetadadaFields.PETICIODEFIRMAID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="metadada.peticioDeFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,MetadadaFields.PETICIODEFIRMAID)}" >
          <form:hidden path="metadada.peticioDeFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.metadada.peticioDeFirmaID,__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,MetadadaFields.PETICIODEFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="metadada_peticioDeFirmaID"  onchange="if(typeof onChangePeticioDeFirmaID == 'function') {  onChangePeticioDeFirmaID(this); };"  cssClass="form-control col-md-9-optional" path="metadada.peticioDeFirmaID">
            <c:forEach items="${__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MetadadaFields.TIPUSMETADADAID)}">
        <tr id="metadada_tipusMetadadaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MetadadaFields.TIPUSMETADADAID])?'metadada.tipusMetadadaID':__theForm.labels[MetadadaFields.TIPUSMETADADAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[MetadadaFields.TIPUSMETADADAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MetadadaFields.TIPUSMETADADAID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="metadada.tipusMetadadaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,MetadadaFields.TIPUSMETADADAID)}" >
          <form:hidden path="metadada.tipusMetadadaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.metadada.tipusMetadadaID,__theForm.listOfValuesForTipusMetadadaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,MetadadaFields.TIPUSMETADADAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="metadada_tipusMetadadaID"  onchange="if(typeof onChangeTipusMetadadaID == 'function') {  onChangeTipusMetadadaID(this); };"  cssClass="form-control col-md-9-optional" path="metadada.tipusMetadadaID">
            <c:forEach items="${__theForm.listOfValuesForTipusMetadadaID}" var="tmp">
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
        
