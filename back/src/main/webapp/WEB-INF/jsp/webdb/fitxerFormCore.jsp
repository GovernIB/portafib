<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FitxerFields" className="es.caib.portafib.model.fields.FitxerFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FitxerFields.NOM)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FitxerFields.NOM])?'fitxer.nom':__theForm.labels[FitxerFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[FitxerFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[FitxerFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="fitxer.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FitxerFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FitxerFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="fitxer.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FitxerFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FitxerFields.DESCRIPCIO])?'fitxer.descripcio':__theForm.labels[FitxerFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[FitxerFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[FitxerFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="fitxer.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="off" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,FitxerFields.DESCRIPCIO)? 'true' : 'false'}" path="fitxer.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FitxerFields.TAMANY)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FitxerFields.TAMANY])?'fitxer.tamany':__theForm.labels[FitxerFields.TAMANY]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[FitxerFields.TAMANY]}">
              <i class="icon-info-sign" title="${__theForm.help[FitxerFields.TAMANY]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="fitxer.tamany" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FitxerFields.TAMANY)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FitxerFields.TAMANY)? 'input-mini uneditable-input' : 'input-mini'}"   path="fitxer.tamany"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FitxerFields.MIME)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FitxerFields.MIME])?'fitxer.mime':__theForm.labels[FitxerFields.MIME]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[FitxerFields.MIME]}">
              <i class="icon-info-sign" title="${__theForm.help[FitxerFields.MIME]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="fitxer.mime" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FitxerFields.MIME)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FitxerFields.MIME)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="fitxer.mime"   />

           </td>
        </tr>
        </c:if>
        
