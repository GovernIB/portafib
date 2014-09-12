<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AlgorismeDeFirmaFields" className="es.caib.portafib.model.fields.AlgorismeDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID])?'algorismeDeFirma.algorismeDeFirmaID':__theForm.labels[AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="algorismeDeFirma.algorismeDeFirmaID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID)? 'input-large uneditable-input' : 'input-large'}"   path="algorismeDeFirma.algorismeDeFirmaID" />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AlgorismeDeFirmaFields.NOM)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AlgorismeDeFirmaFields.NOM])?'algorismeDeFirma.nom':__theForm.labels[AlgorismeDeFirmaFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AlgorismeDeFirmaFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[AlgorismeDeFirmaFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="algorismeDeFirma.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AlgorismeDeFirmaFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,AlgorismeDeFirmaFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="algorismeDeFirma.nom" />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AlgorismeDeFirmaFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AlgorismeDeFirmaFields.DESCRIPCIO])?'algorismeDeFirma.descripcio':__theForm.labels[AlgorismeDeFirmaFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[AlgorismeDeFirmaFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[AlgorismeDeFirmaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="algorismeDeFirma.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" readonly="${ gen:contains(__theForm.readOnlyFields ,AlgorismeDeFirmaFields.DESCRIPCIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,AlgorismeDeFirmaFields.DESCRIPCIO)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"   path="algorismeDeFirma.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AlgorismeDeFirmaFields.SUPORTAT)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AlgorismeDeFirmaFields.SUPORTAT])?'algorismeDeFirma.suportat':__theForm.labels[AlgorismeDeFirmaFields.SUPORTAT]}" />
              <c:if test="${not empty __theForm.help[AlgorismeDeFirmaFields.SUPORTAT]}">
              <i class="icon-info-sign" title="${__theForm.help[AlgorismeDeFirmaFields.SUPORTAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AlgorismeDeFirmaFields.SUPORTAT)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeSuportat == 'function') {  onChangeSuportat(this); };"  path="algorismeDeFirma.suportat">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AlgorismeDeFirmaFields.SUPORTAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.algorismeDeFirma.suportat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
