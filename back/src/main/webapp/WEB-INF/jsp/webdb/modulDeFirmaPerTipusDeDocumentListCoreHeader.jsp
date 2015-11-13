<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModulDeFirmaPerTipusDeDocumentFields" className="es.caib.portafib.model.fields.ModulDeFirmaPerTipusDeDocumentFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaPerTipusDeDocumentFields.ID)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaPerTipusDeDocumentFields.ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaPerTipusDeDocumentFields.MODULDEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaPerTipusDeDocumentFields.MODULDEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaPerTipusDeDocumentFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaPerTipusDeDocumentFields.NOM)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

