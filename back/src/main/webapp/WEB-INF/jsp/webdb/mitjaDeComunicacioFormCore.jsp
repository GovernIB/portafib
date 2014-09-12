<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="MitjaDeComunicacioFields" className="es.caib.portafib.model.fields.MitjaDeComunicacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,MitjaDeComunicacioFields.MITJADECOMUNICACIOID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MitjaDeComunicacioFields.MITJADECOMUNICACIOID])?'mitjaDeComunicacio.mitjaDeComunicacioID':__theForm.labels[MitjaDeComunicacioFields.MITJADECOMUNICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[MitjaDeComunicacioFields.MITJADECOMUNICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[MitjaDeComunicacioFields.MITJADECOMUNICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="mitjaDeComunicacio.mitjaDeComunicacioID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,MitjaDeComunicacioFields.MITJADECOMUNICACIOID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,MitjaDeComunicacioFields.MITJADECOMUNICACIOID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="mitjaDeComunicacio.mitjaDeComunicacioID" />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MitjaDeComunicacioFields.ACTIU)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MitjaDeComunicacioFields.ACTIU])?'mitjaDeComunicacio.actiu':__theForm.labels[MitjaDeComunicacioFields.ACTIU]}" />
              <c:if test="${not empty __theForm.help[MitjaDeComunicacioFields.ACTIU]}">
              <i class="icon-info-sign" title="${__theForm.help[MitjaDeComunicacioFields.ACTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,MitjaDeComunicacioFields.ACTIU)}" >
              <form:errors path="mitjaDeComunicacio.actiu" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,MitjaDeComunicacioFields.ACTIU)? 'false' : 'true'}" path="mitjaDeComunicacio.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,MitjaDeComunicacioFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.mitjaDeComunicacio.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MitjaDeComunicacioFields.JAVACLASS)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MitjaDeComunicacioFields.JAVACLASS])?'mitjaDeComunicacio.javaClass':__theForm.labels[MitjaDeComunicacioFields.JAVACLASS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[MitjaDeComunicacioFields.JAVACLASS]}">
              <i class="icon-info-sign" title="${__theForm.help[MitjaDeComunicacioFields.JAVACLASS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="mitjaDeComunicacio.javaClass" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,MitjaDeComunicacioFields.JAVACLASS)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,MitjaDeComunicacioFields.JAVACLASS)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="mitjaDeComunicacio.javaClass" />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MitjaDeComunicacioFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[MitjaDeComunicacioFields.DESCRIPCIO])?'mitjaDeComunicacio.descripcio':__theForm.labels[MitjaDeComunicacioFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[MitjaDeComunicacioFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[MitjaDeComunicacioFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="mitjaDeComunicacio.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" readonly="${ gen:contains(__theForm.readOnlyFields ,MitjaDeComunicacioFields.DESCRIPCIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,MitjaDeComunicacioFields.DESCRIPCIO)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"   path="mitjaDeComunicacio.descripcio"  />
           </td>
        </tr>
        </c:if>
        
