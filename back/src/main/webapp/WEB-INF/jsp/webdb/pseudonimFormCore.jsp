<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PseudonimFields" className="es.caib.portafib.model.fields.PseudonimFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PseudonimFields.PSEUDONIM)}">
        <tr id="pseudonim_pseudonim_rowid">
          <td id="pseudonim_pseudonim_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PseudonimFields.PSEUDONIM])?'pseudonim.pseudonim':__theForm.labels[PseudonimFields.PSEUDONIM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PseudonimFields.PSEUDONIM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PseudonimFields.PSEUDONIM]}" ></i>
              </c:if>
            </td>
          <td id="pseudonim_pseudonim_columnvalueid">
            <form:errors path="pseudonim.pseudonim" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PseudonimFields.PSEUDONIM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PseudonimFields.PSEUDONIM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="pseudonim.pseudonim"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PseudonimFields.NIF)}">
        <tr id="pseudonim_nif_rowid">
          <td id="pseudonim_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PseudonimFields.NIF])?'pseudonim.nif':__theForm.labels[PseudonimFields.NIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PseudonimFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PseudonimFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="pseudonim_nif_columnvalueid">
            <form:errors path="pseudonim.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PseudonimFields.NIF)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PseudonimFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="255" path="pseudonim.nif"   />

           </td>
        </tr>
        </c:if>
        
