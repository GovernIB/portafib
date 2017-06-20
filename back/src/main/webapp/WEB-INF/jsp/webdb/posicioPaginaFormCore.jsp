<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PosicioPaginaFields" className="es.caib.portafib.model.fields.PosicioPaginaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PosicioPaginaFields.POSICIOPAGINAID)}">
        <tr id="posicioPagina_posicioPaginaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PosicioPaginaFields.POSICIOPAGINAID])?'posicioPagina.posicioPaginaID':__theForm.labels[PosicioPaginaFields.POSICIOPAGINAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PosicioPaginaFields.POSICIOPAGINAID]}">
              <i class="icon-info-sign" title="${__theForm.help[PosicioPaginaFields.POSICIOPAGINAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="posicioPagina.posicioPaginaID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PosicioPaginaFields.POSICIOPAGINAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PosicioPaginaFields.POSICIOPAGINAID)? 'input-large uneditable-input' : 'input-large'}"   path="posicioPagina.posicioPaginaID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PosicioPaginaFields.NOM)}">
        <tr id="posicioPagina_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PosicioPaginaFields.NOM])?'posicioPagina.nom':__theForm.labels[PosicioPaginaFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PosicioPaginaFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[PosicioPaginaFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="posicioPagina.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PosicioPaginaFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PosicioPaginaFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="posicioPagina.nom"   />

           </td>
        </tr>
        </c:if>
        
