<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PosicioTaulaFirmesFields" className="es.caib.portafib.model.fields.PosicioTaulaFirmesFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID])?'posicioTaulaFirmes.posicioTaulaFirmesID':__theForm.labels[PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID]}">
              <i class="icon-info-sign" title="${__theForm.help[PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="posicioTaulaFirmes.posicioTaulaFirmesID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID)? 'input-large uneditable-input' : 'input-large'}"   path="posicioTaulaFirmes.posicioTaulaFirmesID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PosicioTaulaFirmesFields.NOM)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PosicioTaulaFirmesFields.NOM])?'posicioTaulaFirmes.nom':__theForm.labels[PosicioTaulaFirmesFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PosicioTaulaFirmesFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[PosicioTaulaFirmesFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="posicioTaulaFirmes.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PosicioTaulaFirmesFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PosicioTaulaFirmesFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="posicioTaulaFirmes.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PosicioTaulaFirmesFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PosicioTaulaFirmesFields.DESCRIPCIO])?'posicioTaulaFirmes.descripcio':__theForm.labels[PosicioTaulaFirmesFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[PosicioTaulaFirmesFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[PosicioTaulaFirmesFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="posicioTaulaFirmes.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PosicioTaulaFirmesFields.DESCRIPCIO)? 'true' : 'false'}" path="posicioTaulaFirmes.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PosicioTaulaFirmesFields.SUPORTADA)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PosicioTaulaFirmesFields.SUPORTADA])?'posicioTaulaFirmes.suportada':__theForm.labels[PosicioTaulaFirmesFields.SUPORTADA]}" />
              <c:if test="${not empty __theForm.help[PosicioTaulaFirmesFields.SUPORTADA]}">
              <i class="icon-info-sign" title="${__theForm.help[PosicioTaulaFirmesFields.SUPORTADA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PosicioTaulaFirmesFields.SUPORTADA)}" >
              <form:errors path="posicioTaulaFirmes.suportada" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,PosicioTaulaFirmesFields.SUPORTADA)? 'false' : 'true'}" path="posicioTaulaFirmes.suportada" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PosicioTaulaFirmesFields.SUPORTADA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.posicioTaulaFirmes.suportada}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
