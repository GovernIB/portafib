<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="MetadadaFields" className="es.caib.portafib.model.fields.MetadadaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,MetadadaFields.NOM)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MetadadaFields.NOM])?'metadada.nom':__theForm.labels[MetadadaFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[MetadadaFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[MetadadaFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="metadada.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,MetadadaFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,MetadadaFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="metadada.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MetadadaFields.VALOR)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MetadadaFields.VALOR])?'metadada.valor':__theForm.labels[MetadadaFields.VALOR]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[MetadadaFields.VALOR]}">
              <i class="icon-info-sign" title="${__theForm.help[MetadadaFields.VALOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="metadada.valor" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="off" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,MetadadaFields.VALOR)? 'true' : 'false'}" path="metadada.valor"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MetadadaFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MetadadaFields.DESCRIPCIO])?'metadada.descripcio':__theForm.labels[MetadadaFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[MetadadaFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[MetadadaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="metadada.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="off" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,MetadadaFields.DESCRIPCIO)? 'true' : 'false'}" path="metadada.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MetadadaFields.PETICIODEFIRMAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MetadadaFields.PETICIODEFIRMAID])?'metadada.peticioDeFirmaID':__theForm.labels[MetadadaFields.PETICIODEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[MetadadaFields.PETICIODEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[MetadadaFields.PETICIODEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="metadada.peticioDeFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,MetadadaFields.PETICIODEFIRMAID)}" >
          <form:hidden path="metadada.peticioDeFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.metadada.peticioDeFirmaID,__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,MetadadaFields.PETICIODEFIRMAID)}" >
          <form:select id="metadada_peticioDeFirmaID"  onchange="if(typeof onChangePeticioDeFirmaID == 'function') {  onChangePeticioDeFirmaID(this); };"  cssClass="input-xxlarge" path="metadada.peticioDeFirmaID">
            <c:forEach items="${__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MetadadaFields.TIPUSMETADADAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MetadadaFields.TIPUSMETADADAID])?'metadada.tipusMetadadaID':__theForm.labels[MetadadaFields.TIPUSMETADADAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[MetadadaFields.TIPUSMETADADAID]}">
              <i class="icon-info-sign" title="${__theForm.help[MetadadaFields.TIPUSMETADADAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="metadada.tipusMetadadaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,MetadadaFields.TIPUSMETADADAID)}" >
          <form:hidden path="metadada.tipusMetadadaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.metadada.tipusMetadadaID,__theForm.listOfTipusMetadadaForTipusMetadadaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,MetadadaFields.TIPUSMETADADAID)}" >
          <form:select id="metadada_tipusMetadadaID"  onchange="if(typeof onChangeTipusMetadadaID == 'function') {  onChangeTipusMetadadaID(this); };"  cssClass="input-xxlarge" path="metadada.tipusMetadadaID">
            <c:forEach items="${__theForm.listOfTipusMetadadaForTipusMetadadaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
