<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PropietatGlobalFields" className="es.caib.portafib.model.fields.PropietatGlobalFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.CLAU)}">
        <tr id="propietatGlobal_clau_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.CLAU])?'propietatGlobal.clau':__theForm.labels[PropietatGlobalFields.CLAU]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.CLAU]}">
              <i class="icon-info-sign" title="${__theForm.help[PropietatGlobalFields.CLAU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="propietatGlobal.clau" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.CLAU)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.CLAU)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="propietatGlobal.clau"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.VALOR)}">
        <tr id="propietatGlobal_valor_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.VALOR])?'propietatGlobal.valor':__theForm.labels[PropietatGlobalFields.VALOR]}" />
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.VALOR]}">
              <i class="icon-info-sign" title="${__theForm.help[PropietatGlobalFields.VALOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="propietatGlobal.valor" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.VALOR)? 'true' : 'false'}" path="propietatGlobal.valor"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.valor'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.valor'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.valor'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.ENTITATID)}">
        <tr id="propietatGlobal_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.ENTITATID])?'propietatGlobal.entitatID':__theForm.labels[PropietatGlobalFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[PropietatGlobalFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="propietatGlobal.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.ENTITATID)}" >
          <form:hidden path="propietatGlobal.entitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.propietatGlobal.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.ENTITATID)}" >
          <form:select id="propietatGlobal_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="input-xxlarge" path="propietatGlobal.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.DESCRIPCIO)}">
        <tr id="propietatGlobal_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.DESCRIPCIO])?'propietatGlobal.descripcio':__theForm.labels[PropietatGlobalFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[PropietatGlobalFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="propietatGlobal.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.DESCRIPCIO)? 'true' : 'false'}" path="propietatGlobal.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
