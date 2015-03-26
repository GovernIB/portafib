<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PrioritatFields" className="es.caib.portafib.model.fields.PrioritatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PrioritatFields.PRIORITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PrioritatFields.PRIORITATID])?'prioritat.prioritatID':__theForm.labels[PrioritatFields.PRIORITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PrioritatFields.PRIORITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[PrioritatFields.PRIORITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="prioritat.prioritatID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PrioritatFields.PRIORITATID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PrioritatFields.PRIORITATID)? 'input-small uneditable-input' : 'input-small'}"   path="prioritat.prioritatID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PrioritatFields.NOM)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PrioritatFields.NOM])?'prioritat.nom':__theForm.labels[PrioritatFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PrioritatFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[PrioritatFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="prioritat.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PrioritatFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PrioritatFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="prioritat.nom"   />

           </td>
        </tr>
        </c:if>
        
