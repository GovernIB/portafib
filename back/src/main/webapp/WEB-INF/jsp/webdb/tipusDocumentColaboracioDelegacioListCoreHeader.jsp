<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusDocumentColaboracioDelegacioFields" className="es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentColaboracioDelegacioFields.ID)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusDocumentColaboracioDelegacioFields.ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

