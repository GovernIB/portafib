<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PropietatGlobalFields" className="es.caib.portafib.model.fields.PropietatGlobalFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.CLAU)}">
        <tr id="propietatGlobal_clau_rowid">
          <td id="propietatGlobal_clau_columnlabelid">
            <label for="propietatGlobal.clau">
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.CLAU])?'propietatGlobal.clau':__theForm.labels[PropietatGlobalFields.CLAU]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.CLAU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PropietatGlobalFields.CLAU]}" ></i>
              </c:if>
            </td>
          <td id="propietatGlobal_clau_columnvalueid">
            <form:errors path="propietatGlobal.clau" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.CLAU)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.CLAU)? ' uneditable-input' : ''}"  style="" maxlength="255" path="propietatGlobal.clau"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.VALOR)}">
        <tr id="propietatGlobal_valor_rowid">
          <td id="propietatGlobal_valor_columnlabelid">
            <label for="propietatGlobal.valor">
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.VALOR])?'propietatGlobal.valor':__theForm.labels[PropietatGlobalFields.VALOR]}" />
             </label>
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.VALOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PropietatGlobalFields.VALOR]}" ></i>
              </c:if>
            </td>
          <td id="propietatGlobal_valor_columnvalueid">
              <form:errors path="propietatGlobal.valor" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.VALOR)? 'readonly' : ''}" path="propietatGlobal.valor"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_valor" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_valor" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.valor'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.valor'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.valor'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
			$('#dropdownMenuButton_valor').on('click', function(){
					var valor = ($('#dropdownMenuContainer_valor').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_valor').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.ENTITATID)}">
        <tr id="propietatGlobal_entitatID_rowid">
          <td id="propietatGlobal_entitatID_columnlabelid">
            <label for="propietatGlobal.entitatID">
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.ENTITATID])?'propietatGlobal.entitatID':__theForm.labels[PropietatGlobalFields.ENTITATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PropietatGlobalFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="propietatGlobal_entitatID_columnvalueid">
          <form:errors path="propietatGlobal.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.ENTITATID)}" >
          <form:hidden path="propietatGlobal.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.propietatGlobal.entitatID,__theForm.listOfEntitatForEntitatID)}" aria-label="propietatGlobal.entitatID"   />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="propietatGlobal.entitatID" cssClass="form-control col-md-9-optional"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };">


            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.propietatGlobal.entitatID }">
                  <option value="" selected></option>

              </c:if>
              <c:if test="${not empty __theForm.propietatGlobal.entitatID }">

                  <option value="" ></option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.DESCRIPCIO)}">
        <tr id="propietatGlobal_descripcio_rowid">
          <td id="propietatGlobal_descripcio_columnlabelid">
            <label for="propietatGlobal.descripcio">
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.DESCRIPCIO])?'propietatGlobal.descripcio':__theForm.labels[PropietatGlobalFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PropietatGlobalFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="propietatGlobal_descripcio_columnvalueid">
              <form:errors path="propietatGlobal.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.DESCRIPCIO)? 'readonly' : ''}" path="propietatGlobal.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
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
        
