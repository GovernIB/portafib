<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusDocumentFields" className="es.caib.portafib.model.fields.TipusDocumentFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentFields.TIPUSDOCUMENTID)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusDocumentFields.TIPUSDOCUMENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentFields.NOMID)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusDocumentFields.NOMID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentFields.TIPUSDOCUMENTBASEID)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusDocumentFields.TIPUSDOCUMENTBASEID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusDocumentFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentFields.USUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusDocumentFields.USUARIAPLICACIOID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

